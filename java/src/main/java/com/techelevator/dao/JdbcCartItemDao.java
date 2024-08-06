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
        String sql = "SELECT * FROM cart_item WHERE cart_itemid = ?";
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
        String sql = "SELECT * FROM cart_item WHERE cakeid = ? AND userid = ?";
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
        String sql = "SELECT * FROM cart_item WHERE userid = ? ORDER BY cart_itemid";
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
        String sql = "INSERT INTO cart_item(userid, cakeid, quantity) VALUES (?, ?, ?) RETURNING cart_itemid";
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
        String sql = "UPDATE cart_item SET quantity = ? WHERE cart_itemid = ?";
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
    public String rejectCartItemById(int cartItemId) {
        return null;
    }

    @Override
    public String cancelCartItemById(int cartItemId) {
        return null;
    }

    @Override
    public String rejectCartItemsByUserId(int userId) {
        return null;
    }

    @Override
    public String cancelCartItemsByUserId(int userId) {
        return null;
    }

    @Override
    public int deleteCartItemById(int cartItemId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM cart_item WHERE cart_itemid = ?";
        try {
            numberOfRows = jdbcTemplate.update(sql, cartItemId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    @Override
    public int deleteCartItemsByUserId(int userId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM cart_item WHERE userid = ?";
        try {
            numberOfRows = jdbcTemplate.update(sql, userId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private CartItem mapRowToCartItem(SqlRowSet rs) {
        CartItem item = new CartItem();
        item.setCartItemId(rs.getInt("cart_itemid"));
        item.setUserId(rs.getInt("userid"));
        item.setCakeId(rs.getInt("cakeid"));
        item.setPickupDate(rs.getDate("pickupdate").toLocalDate());
        item.setPickupTime(rs.getTime("pickuptime").toLocalTime());
        item.setQuantity(rs.getInt("quantity"));
        return item;
    }


}

