package com.techelevator.controller;

import com.techelevator.dao.CakeDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Cake;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/instock")
public class InStockController {


    private CakeDao cakedao;

    public InStockController(CakeDao cakedao){
        this.cakedao = cakedao;
    }

    @RequestMapping(path ="", method= RequestMethod.GET)
    public List<Cake> allCake(){
        try{
            return cakedao.getCakes();
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Cake getById(@PathVariable int id){
        try{
            Cake cake = cakedao.getCakeById(id);
            if(cake == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The cake with id=%d was not found.", id));
            }
            return cake;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }


}
