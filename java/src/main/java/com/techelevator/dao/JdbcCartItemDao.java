
package com.techelevator.dao;


import com.techelevator.model.CartItem;
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
            "cart_item.user_id, cart_item.cake_id, cart_item_status.status_name,\n" +
            "cart_item.pickup_date, cart_item.pickup_time\n" +
            "FROM cart_item\n" +
            "INNER JOIN cart_item_status ON cart_item.status_id = " +
            "cart_item_status.cart_item_status_id ";
    private final JdbcTemplate jdbcTemplate;

    public JdbcCartItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CartItem getCartItemById(int cartItemId) {
        CartItem cartItem = null;
        String sql = SQL_SELECT_CART_ITEM +
                " WHERE cart_item_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cartItemId);
            if (results.next()) {
                cartItem = mapRowToCartItem(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cartItem;
    }

    @Override
    public CartItem getCartItemByCakeIdAndUserId(int cakeId, int userId) {
        CartItem cartItem = null;
        String sql = SQL_SELECT_CART_ITEM +
                "WHERE cake_id = ? AND user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cakeId, userId);
            if (results.next()) {
                cartItem = mapRowToCartItem(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cartItem;
    }

    @Override
    public List<CartItem> getCartItemsByUserId(int userId) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = SQL_SELECT_CART_ITEM +
                "WHERE user_id = ? ORDER BY cart_item_id";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                CartItem item = mapRowToCartItem(results);
                cartItems.add(item);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cartItems;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        CartItem newCartItem = null;
        String sql = "INSERT INTO cart_item(user_id, cake_id, " +
                "cart_item_status.status_name, pickup_date, pickup_time ) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING cart_item_id";

        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, cartItem.getUserId(),
                    cartItem.getCakeId(), cartItem.getCartItemStatus(), cartItem.getPickupDate(),
                    cartItem.getPickupTime());
            newCartItem = getCartItemById(newId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCartItem;

    }

    @Override
    public CartItem rejectCartItemByCakeId(CartItem cartItem, int cakeId) {
        CartItem rejectedCake = null;
        String sql = "UPDATE cart_item_status\n" +
                "SET status_name = ?\n" +
                "WHERE cart_item_status_id IN (\n" +
                "    SELECT status_id\n" +
                "    FROM cart_item\n" +
                "    WHERE cake_id = ?);";
        try {
            int numberOfRows = jdbcTemplate.update(sql,cartItem.getCartItemStatus(), cakeId);
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
            rejectedCake = getCartItemById(cartItem.getCartItemId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rejectedCake;
    }

    @Override
    public CartItem cancelCartItemByCakeId(CartItem cartItem, int cakeId) {
        CartItem cancelledCake = null;
        String sql = "UPDATE cart_item_status\n" +
                "SET status_name = ?\n" +
                "WHERE cart_item_status_id IN (\n" +
                "    SELECT status_id\n" +
                "    FROM cart_item\n" +
                "    WHERE cake_id = ?);";

        try {
            int numberOfRows = jdbcTemplate.update(sql, cartItem.getCartItemStatus(), cakeId);
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
            cancelledCake = getCartItemById(cartItem.getCartItemId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return cancelledCake;
    }
// this method lets the baker employee reject a whole order from user.
    @Override
    public CartItem rejectCartItemByUserId(CartItem cartItem, int userId) {
        CartItem rejectedEntireCart = null;
        String sql = "UPDATE cart_item_status\n" +
                "SET status_name = ?\n" +
                "WHERE cart_item_status_id IN (\n" +
                "    SELECT status_id\n" +
                "    FROM cart_item\n" +
                "    WHERE user_id = ?);";
        try {
            int numberOfRows = jdbcTemplate.update(sql, cartItem.getCartItemStatus(), userId);
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
            rejectedEntireCart = getCartItemById(cartItem.getCartItemId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return rejectedEntireCart;

    }
//This method allows the user to cancel their entire order
// if they have multiple cakes in one order
    @Override
    public CartItem cancelCartItemByUserId(CartItem cartItem, int userId) {
        CartItem cancelEntireOrder = null;
        String sql = "UPDATE cart_item_status\n" +
                "SET status_name = ?\n" +
                "WHERE cart_item_status_id IN (\n" +
                "    SELECT status_id\n" +
                "    FROM cart_item\n" +
                "    WHERE user_id = ?);";

        try {
            int numberOfRows = jdbcTemplate.update(sql,cartItem.getCartItemStatus(), userId);
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
            cancelEntireOrder = getCartItemById(cartItem.getCartItemId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return cancelEntireOrder;
    }
    //TWO NEED METHODS ADD

    public CartItem deleteCartItemsByUserId(int userId){

        return null;
    }

    public CartItem deleteCartItemById(int cartItem){
        return null;
    }

    public CartItem updateCartItem(CartItem cartItem){
        return null;
    }


    private CartItem mapRowToCartItem(SqlRowSet rs) {
        CartItem item = new CartItem();
        item.setCartItemId(rs.getInt("cart_item_id"));
        item.setUserId(rs.getInt("user_id"));
        item.setCakeId(rs.getInt("cake_id"));
        item.setCartItemStatus(rs.getString("status_name"));
        item.setPickupDate(rs.getDate("pickup_date").toLocalDate());
        item.setPickupTime(rs.getTime("pickupt_time").toLocalTime());
        return item;
    }


}


