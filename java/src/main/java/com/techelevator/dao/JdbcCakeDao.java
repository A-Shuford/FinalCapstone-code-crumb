package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Cake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCakeDao implements CakeDao {

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final String SQL_SELECT_CAKE = "SELECT cake.cake_id, cake.cake_name, " +
            "cake_style.style_name, cake_size.size_name, " +
            "cake_flavor.flavor_name, cake_filling.filling_name, " +
            "cake_frosting.frosting_name, cake.cake_type, cake.has_writing, " +
            "cake.custom_text, cake.amount_available, cake.image_name, " +
            "cake_price.price " +
            "FROM cake " +
            "INNER JOIN cake_style ON cake.cake_style = cake_style.cake_style_id " +
            "INNER JOIN cake_size ON cake.cake_size = cake_size.cake_size_id " +
            "LEFT JOIN cake_filling ON cake.cake_filling = cake_filling.cake_filling_id " +
            "INNER JOIN cake_flavor ON cake.cake_flavor = cake_flavor.cake_flavor_id " +
            "LEFT JOIN cake_frosting ON cake.cake_frosting = cake_frosting.cake_frosting_id " +
            "INNER JOIN cake_price ON cake.cake_style = " +
            "cake_price.cake_style_id AND cake.cake_size = cake_price.cake_size_id";;

    private final JdbcTemplate jdbcTemplate;



    public JdbcCakeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Cake> getCakes() {
        List<Cake> cakes = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(SQL_SELECT_CAKE);
        try {
            while (results.next()) {
                Cake cake = mapRowToCake(results);
                cakes.add(cake);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cakes;
    }

    @Override
    public Cake getCakeById(int cakeId) {
        Cake cake = null;
        String sql =  SQL_SELECT_CAKE + " WHERE cake_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cakeId);
            if (results.next()) {
                return mapRowToCake(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cake;
    }

    @Override
    public List<Cake> getCakesByUserId(int userId) {
        List<Cake> cakes = new ArrayList<>();
        String sql = SQL_SELECT_CAKE +
                " WHERE cake.cake_id IN (SELECT cake_id FROM cart_item_cake WHERE cart_item_id IN " +
                "(SELECT cart_item_id FROM cart_item WHERE user_id = ?));";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Cake cake = mapRowToCake(results);
                cakes.add(cake);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cakes;
    }
    @Override
    public Cake createNewCake (Cake cake){
        Cake newCake = null;
        String sql = "WITH style AS (SELECT cake_style_id FROM cake_style WHERE style_name = ?), " +
                "size AS (SELECT cake_size_id FROM cake_size WHERE size_name = ?), " +
                "flavor AS (SELECT cake_flavor_id FROM cake_flavor WHERE flavor_name = ?), " +
                "filling AS (SELECT cake_filling_id FROM cake_filling WHERE filling_name = ?), " +
                "frosting AS (SELECT cake_frosting_id FROM cake_frosting WHERE frosting_name = ?) " +
                "INSERT INTO cake (cake_name, cake_style, cake_size, cake_flavor, " +
                "cake_filling, cake_frosting, cake_type, has_writing, " +
                "custom_text, amount_available) " +
                "VALUES ( " +
                "    ?, " + // cake_name
                "    (SELECT cake_style_id FROM style), " +
                "    (SELECT cake_size_id FROM size), " +
                "    (SELECT cake_flavor_id FROM flavor), " +
                "    (SELECT cake_filling_id FROM filling), " +
                "    (SELECT cake_frosting_id FROM frosting), " +
                "    ?, " + // cake_type
                "    ?, " + // has_writing
                "    ?, " + // custom_text
                "    ?, " + // amount_available
                ") " +
                "RETURNING cake_id;";

        try {
            int newCakeId = jdbcTemplate.queryForObject(sql, int.class,cake.getCakeStyle(),
                    cake.getCakeSize(), cake.getCakeFlavor(), cake.getCakeFilling(),
                    cake.getCakeFrosting(), cake.getCakeName(), cake.getCakeType(),
                    cake.hasWriting(), cake.getCustomText(),
                    cake.getAmountAvailable(), cake.getPrice());
            log.debug("Created new Standard Cake with Id: " +newCakeId);
            newCake = getCakeById(newCakeId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCake;

    }


    @Override
    public List<Cake> getCakesByStyle(String style) {
        List<Cake> cakes = new ArrayList<>();
        String sql = SQL_SELECT_CAKE +
                "WHERE cake_style.style_name = ?;";
        //NOTE does the style string need to be case sensitive? How do we ensure that it reads?
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql,style);
            while(results.next()){
                Cake cake = mapRowToCake(results);
                cakes.add(cake);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cakes;
    }

    @Override
    public List<Cake> getCakesByType(String type) {
        List<Cake> cakes = new ArrayList<>();
        String sql = SQL_SELECT_CAKE +
                "WHERE cake.cake_type = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql,type);
            while(results.next()){
                Cake cake = mapRowToCake(results);
                cakes.add(cake);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cakes;
    }

    @Override
    public List <Cake> getAllStandardCakes (String cakeType){
        List<Cake> cakes = new ArrayList<>();
        String sql = SQL_SELECT_CAKE +
                "WHERE cake.cake_type = '?';";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cakeType);
            while(results.next()){
                Cake cake = mapRowToCake(results);
                cakes.add(cake);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cakes;
    }

    @Override
    public  Cake updateCakeDetails (Cake cake){
        Cake updatedCake = null;
        String sql = "WITH style AS (SELECT cake_style_id FROM cake_style WHERE style_name = ?), " +
                "size AS (SELECT cake_size_id FROM cake_size WHERE size_name = ?), " +
                "flavor AS (SELECT cake_flavor_id FROM cake_flavor WHERE flavor_name = ?), " +
                "filling AS (SELECT cake_filling_id FROM cake_filling WHERE filling_name = ?), " +
                "frosting AS (SELECT cake_frosting_id FROM cake_frosting " +
                "WHERE frosting_name = ?) " +
                "UPDATE cake " +
                "SET " +
                "    cake_style = (SELECT cake_style_id FROM style), " +
                "    cake_size = (SELECT cake_size_id FROM size), " +
                "    cake_flavor = (SELECT cake_flavor_id FROM flavor), " +
                "    cake_filling = (SELECT cake_filling_id FROM filling), " +
                "    cake_frosting = (SELECT cake_frosting_id FROM frosting), " +
                "    has_writing = ?, " +
                "    custom_text = ?, " +
                "WHERE cake_name = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, cake.getCakeStyle(), cake.getCakeSize(),
                    cake.getCakeFlavor(), cake.getCakeFilling(), cake.getCakeFrosting(),
                    cake.hasWriting(),cake.getCustomText(), cake.getCakeName());
            if (rowsAffected == 0){
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedCake = getCakeById(cake.getCakeId());

        }  catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedCake;
    }

    @Override
    public Cake updateAvailableCakeAmountsByName(Cake cake) {
        Cake updatedCake = null;
        String sql = "UPDATE cake SET amount_available = ?, WHERE cake_name = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, cake.getAmountAvailable(), cake.getCakeName());

            if (numberOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least one.");
            } else {
                updatedCake = getCakeById(cake.getCakeId());
            }
        }  catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedCake;
    }

    @Override
    public List<Cake> getCakesByName(String cakeName) {
        List<Cake> cakes = new ArrayList<>();
        String sql = SQL_SELECT_CAKE +
                "WHERE cake_name = ?;";

        //Again, how do we ensure the string is case sensitive and correct spacing?
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cakeName);
            while (results.next()) {
                Cake cake = mapRowToCake(results);
                cakes.add(cake);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cakes;
    }

    private Cake mapRowToCake(SqlRowSet rs) {
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
        cake.setAmountAvailable(rs.getInt("amount_available"));
        cake.setPrice(rs.getBigDecimal("price"));
        cake.setImageName(rs.getString("image_name"));

        return cake;
    }
}
