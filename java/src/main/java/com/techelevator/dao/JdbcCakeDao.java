package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Cake;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCakeDao implements CakeDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCakeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Cake> getCakes() {
        List<Cake> cakes = new ArrayList<>();
        String sql = "SELECT * FROM cake ORDER BY cakeid";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
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
        String sql = "SELECT * FROM cake WHERE cakeid = ?";
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
        String sql = "SELECT * FROM cake WHERE cakeid IN (SELECT cakeid FROM cart_item WHERE userid = ?)";
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
    public List<Cake> getCakesByStyle(String style) {
        List<Cake> cakes = new ArrayList<>();
        String sql = "Select * FROM cake WHERE cakestyle = ?";
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
        String sql = "Select * FROM cake WHERE caketype = ?";
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
    public Cake updateAvailableCakeAmountsById(Cake cake) {
        Cake updatedCake = null;
        String sql = "UPDATE cake SET amountAvailable = ?, WHERE cakeid = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, cake.getAmountAvailable(), cake.getCakeId());

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
        String sql = "SELECT * FROM cake WHERE cakename = ?;";
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
        cake.setCakeId(rs.getInt("cakeid"));
        cake.setCakeName(rs.getString("cakename"));
        cake.setCakeStyle(rs.getInt("cakestyle"));
        cake.setCakeSize(rs.getInt("cakesize"));
        cake.setCakeFlavor(rs.getInt("cakeflavor"));
        cake.setCakeFilling(rs.getInt("cakefilling"));
        cake.setCakeFrosting(rs.getInt("cakefrosting"));
        cake.setCakeType(rs.getString("caketype"));
        cake.setHasWriting(rs.getBoolean("haswriting"));
        cake.setCustomText(rs.getString("customtext"));
        cake.setAmountAvailable(rs.getInt("amountavailable"));
        cake.setPrice(rs.getBigDecimal("price"));

        return cake;
    }
}
