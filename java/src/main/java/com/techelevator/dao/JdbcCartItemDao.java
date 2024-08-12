package com.techelevator.dao;

import com.techelevator.model.Cake;
import com.techelevator.model.CartItem;
import com.techelevator.model.User;
import com.techelevator.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCartItemDao implements CartItemDao {

    private static final String SQL_SELECT_CART_ITEM = "SELECT cart_item.cart_item_id, " +
        "users.user_id, users.username, users.yourname, users.email, users.phone_number, " +
        "cart_item_cake.cake_id, cake.cake_name, cake_style.style_name, cake_size.size_name, " +
        "cake_flavor.flavor_name, cake_filling.filling_name, cake_frosting.frosting_name, " +
        "cake.cake_type, cake.has_writing, cake.custom_text, cart_item_cake.quantity, " +
        "cart_item.status_id, cart_item.pickup_date, cart_item.pickup_time, " +
        "cake.amount_available, cake.image_name " +
        "FROM cart_item " +
        "INNER JOIN users ON cart_item.user_id = users.user_id " +
        "INNER JOIN cart_item_cake ON cart_item.cart_item_id = cart_item_cake.cart_item_id " +
        "INNER JOIN cake ON cart_item_cake.cake_id = cake.cake_id " +
        "INNER JOIN cake_style ON cake.cake_style = cake_style.cake_style_id " +
        "INNER JOIN cake_size ON cake.cake_size = cake_size.cake_size_id " +
        "LEFT JOIN cake_filling ON cake.cake_filling = cake_filling.cake_filling_id " +
        "INNER JOIN cake_flavor ON cake.cake_flavor = cake_flavor.cake_flavor_id " +
        "LEFT JOIN cake_frosting ON cake.cake_frosting = cake_frosting.cake_frosting_id ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcCartItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CartItem getCartItemById(int cartItemId) {
        String sql = SQL_SELECT_CART_ITEM + " WHERE cart_item.cart_item_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cartItemId);
            if (results.next()) {
                return mapRowToCartItem(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return null;
    }

    @Override
    public CartItem getCartItemByCakeIdAndUserId(int cakeId, int userId) {
        String sql = SQL_SELECT_CART_ITEM + " WHERE cart_item_cake.cake_id = ? AND cart_item.user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cakeId, userId);
            if (results.next()) {
                return mapRowToCartItem(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return null;
    }

    @Override
    public List<CartItem> getCartItemsByUserId(int userId) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = SQL_SELECT_CART_ITEM + " WHERE cart_item.user_id = ? ORDER BY cart_item.cart_item_id";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                CartItem item = mapRowToCartItem(results);
                cartItems.add(item);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cartItems;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem, int userId) {
        CartItem newCartItem = null;
        String sql = "INSERT INTO cart_item(user_id, status_id, pickup_date, pickup_time) " +
                "VALUES (?, ?, ?, ?) RETURNING cart_item_id;";

        try {
            int newCartItemId = jdbcTemplate.queryForObject(sql, int.class, userId,
                    1, cartItem.getPickupDate(), cartItem.getPickupTime());

            String sqlCartItemCake = "INSERT INTO cart_item_cake(cart_item_id, cake_id, price) " +
                    "VALUES (?, ?, ?);";
            jdbcTemplate.update(sqlCartItemCake, newCartItemId, cartItem.getCake().getCakeId(),
                    cartItem.getCake().getPrice());

            newCartItem = getCartItemById(newCartItemId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCartItem;
    }

    @Override
    public CartItem rejectCartItemByCakeId(CartItem cartItem, int cakeId) {
        return updateCartItemStatusByCakeId(cartItem, cakeId, cartItem.getCartItemStatus());
    }

    @Override
    public CartItem cancelCartItemByCakeId(CartItem cartItem, int cakeId) {
        return updateCartItemStatusByCakeId(cartItem, cakeId, cartItem.getCartItemStatus());
    }

    @Override
    public CartItem rejectCartItemByUserId(CartItem cartItem, int userId) {
        return updateCartItemStatusByUserId(cartItem, userId, cartItem.getCartItemStatus());
    }

    @Override
    public CartItem cancelCartItemByUserId(CartItem cartItem, int userId) {
        return updateCartItemStatusByUserId(cartItem, userId, cartItem.getCartItemStatus());
    }

    @Override
    public CartItem deleteCartItemsByUserId(int userId) {
        String sql = "DELETE FROM cart_item WHERE user_id = ?;";
        try {
            jdbcTemplate.update(sql, userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return null;
    }

    @Override
    public CartItem deleteCartItemById(int cartItemId) {
        String sql = "DELETE FROM cart_item WHERE cart_item_id = ?;";
        try {
            jdbcTemplate.update(sql, cartItemId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return null;
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        String sql = "UPDATE cart_item " +
                "SET status_id = ?, pickup_date = ?, pickup_time = ?, quantity = ? " +
                "WHERE cart_item_id = ?;";
        try {
            int statusId = mapStatusNameToId(cartItem.getCartItemStatus());
            jdbcTemplate.update(sql, statusId, cartItem.getPickupDate(),
                    cartItem.getPickupTime(), cartItem.getQuantity(), cartItem.getCartItemId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getCartItemById(cartItem.getCartItemId());
    }

    @Override
    public List<CartItem> getAllOrdersForBaker() {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = SQL_SELECT_CART_ITEM + " ORDER BY cart_item.pickup_date, cart_item.pickup_time";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                CartItem item = mapRowToCartItem(results);
                cartItems.add(item);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cartItems;
    }

    private CartItem updateCartItemStatusByCakeId(CartItem cartItem, int cakeId, String status) {
        String sql = "UPDATE cart_item SET status_id = " +
                "(SELECT cart_item_status_id FROM cart_item_status WHERE status_name = ?) " +
                "WHERE cart_item_id IN (SELECT cart_item_id FROM cart_item_cake WHERE cake_id = ?);";
        try {
            int numberOfRows = jdbcTemplate.update(sql, status, cakeId);
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getCartItemById(cartItem.getCartItemId());
    }

    private CartItem updateCartItemStatusByUserId(CartItem cartItem, int userId, String status) {
        String sql = "UPDATE cart_item SET status_id = " +
                "(SELECT cart_item_status_id FROM cart_item_status WHERE status_name = ?) " +
                "WHERE user_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, status, userId);
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getCartItemById(cartItem.getCartItemId());
    }

    private CartItem mapRowToCartItem(SqlRowSet rs) {
        CartItem item = new CartItem();

        // Create and populate a User object
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setYourName(rs.getString("yourname"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phone_number"));

        // Create and populate a Cake object
        Cake cake = new Cake();
        cake.setCakeId(rs.getInt("cake_id"));
        cake.setCakeName(rs.getString("cake_name"));
        cake.setCakeStyle(rs.getString("style_name"));
        cake.setCakeSize(rs.getString("size_name"));
        cake.setCakeFlavor(rs.getString("flavor_name"));
        cake.setCakeFilling(rs.getString("filling_name"));
        cake.setCakeFrosting(rs.getString("frosting_name"));
        cake.setCakeType(rs.getString("cake_type"));
        cake.setHasWriting(rs.getBoolean("has_writing"));
        cake.setCustomText(rs.getString("custom_text"));
        cake.setPrice(rs.getBigDecimal("price"));
        cake.setAmountAvailable(rs.getInt("amount_available"));
        cake.setImageName(rs.getString("image_name"));

        // Populate the CartItem object with the User, Cake, and other details
        item.setCartItemId(rs.getInt("cart_item_id"));
        item.setUser(user);
        item.setCake(cake);
        item.setQuantity(rs.getInt("quantity"));
        item.setCartItemStatus(mapStatusIdToName(rs.getInt("status_id")));
        if (rs.getDate("pickup_date") != null) {
            item.setPickupDate(rs.getDate("pickup_date").toLocalDate());
        }
        if (rs.getTime("pickup_time") != null) {
            item.setPickupTime(rs.getTime("pickup_time").toLocalTime());
        }
        return item;
    }

    // Mapping functions
    private int mapStatusNameToId(String statusName) {
        switch (statusName) {
            case "Pending":
                return 1;
            case "Rejected by bakery":
                return 2;
            case "Cancelled by customer":
                return 3;
            case "Ready for pick-up":
                return 4;
            case "Order Completed":
                return 5;
            default:
                throw new IllegalArgumentException("Invalid status name: " + statusName);
        }
    }

    private String mapStatusIdToName(int statusId) {
        switch (statusId) {
            case 1:
                return "Pending";
            case 2:
                return "Rejected by bakery";
            case 3:
                return "Cancelled by customer";
            case 4:
                return "Ready for pick-up";
            case 5:
                return "Order Completed";
            default:
                throw new IllegalArgumentException("Invalid status ID: " + statusId);
        }
    }
}
