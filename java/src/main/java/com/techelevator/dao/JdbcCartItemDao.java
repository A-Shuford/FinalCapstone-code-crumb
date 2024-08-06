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

    private final JdbcTemplate jdbcTemplate;

    public JdbcCartItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CartItem getCartItemById(int cartItemId) {
        CartItem cartItem = null;
        String sql = "SELECT * FROM cart_item WHERE cart_item_id = ?";
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
        String sql = "SELECT * FROM cart_item WHERE cake_id = ? AND user_id = ?";
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
        String sql = "SELECT * FROM cart_item WHERE user_id = ? ORDER BY cart_item_id";
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
        String sql = "INSERT INTO cart_item(user_id, cake_id, quantity) VALUES (?, ?, ?) RETURNING cart_item_id";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, cartItem.getUserId(), cartItem.getCakeId(), cartItem.getQuantity());
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
    public CartItem updateCartItem(CartItem cartItem) {
        CartItem updatedCartItem = null;
        // The only thing that can be updated is Quantity
        String sql = "UPDATE cart_item SET quantity = ? WHERE cart_item_id = ?";
        try {
            int rowAffected = jdbcTemplate.update(sql, cartItem.getQuantity(), cartItem.getCartItemId());
            if (rowAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedCartItem = getCartItemById(cartItem.getCartItemId());
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedCartItem;
    }

    @Override
    public int rejectCartItemByCakeId(int cakeId) {
        String sql = "UPDATE from cart_item SET status_id = ? WHERE cake_id = ?;";
        int numberOfRows;
        try {
            numberOfRows = jdbcTemplate.update(sql, //NEED STATUS ID FOR REJECTED #//
                    , cakeId);
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    @Override
    public int cancelCartItemByCakeId(int cakeId) {
        String sql = "UPDATE from cart_item SET status_id = ? WHERE cake_id = ?;";
        int numberOfRows;
        try {
            numberOfRows = jdbcTemplate.update(sql, //NEED STATUS ID FOR CANCELLED #//
                    , cakeId);
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    @Override
    public int rejectCartItemsByUserId(int userId) {
        String sql = "UPDATE from cart_item SET status_id = ? WHERE user_id = ?;";
        int numberOfRows;
        try {
            numberOfRows = jdbcTemplate.update(sql, //NEED STATUS ID FOR REJECTED #//
                    , userId);
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;

    }

    @Override
    public int cancelCartItemsByUserId(int userId) {
        String sql = "UPDATE from cart_item SET status_id = ? WHERE user_id = ?;";
        int numberOfRows;
        try {
            numberOfRows = jdbcTemplate.update(sql, //NEED STATUS ID FOR CANCELLED #//
                    , userId);
            if (numberOfRows == 0) {
                throw new DaoException("Zero rowsw affected, expected at least one.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }


    private CartItem mapRowToCartItem(SqlRowSet rs) {
        CartItem item = new CartItem();
        item.setCartItemId(rs.getInt("cart_item_id"));
        item.setUserId(rs.getInt("user_id"));
        item.setCakeId(rs.getInt("cake_id"));
        item.setPickupDate(rs.getDate("pickup_date").toLocalDate());
        item.setPickupTime(rs.getTime("pickupt_time").toLocalTime());
        item.setQuantity(rs.getInt("quantity"));
        item.setCartItemStatus((rs.getInt("status_id")));
        return item;
    }


}

