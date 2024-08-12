package com.techelevator.controller;

import com.techelevator.dao.CakeDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Cake;
import com.techelevator.service.CakeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class CakeController {

    private CakeDao cakeDao;

    private CakeService cakeService;

    public CakeController(CakeDao cakeDao, CakeService cakeService) {
        this.cakeDao = cakeDao;
        this.cakeService = cakeService;
    }



    @RequestMapping(path = "/cakes", method = RequestMethod.GET)
    public List<Cake> getList(@RequestParam(required = false) String sku, @RequestParam(required = false) String name) {
        try {
            if (sku == null && name == null) {
                return cakeDao.getCakes();
            } else {
                return cakeDao.getCakesByName(name);
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @RequestMapping(path = "/cakes/{id}", method = RequestMethod.GET)
    public Cake getById(@PathVariable int id) {
        try {
            Cake cake = cakeDao.getCakeById(id);
            if (cake == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The cake with id=%d was not found.", id));
            }
            return cake;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @RequestMapping(path="/customorder", method = RequestMethod.POST)
    public Cake addingCaking(@RequestBody Cake cake, Principal principal){
        try{
            return cakeService.addingCustomCake(cake, principal);
        }catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

}


