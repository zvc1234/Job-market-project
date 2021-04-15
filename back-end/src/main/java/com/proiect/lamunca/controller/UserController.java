package com.proiect.lamunca.controller;


import com.proiect.lamunca.entity.db1.User;
import com.proiect.lamunca.exception.ApplicationException;
import com.proiect.lamunca.mappers.Mapper;
import com.proiect.lamunca.models.LoginModel;
import com.proiect.lamunca.models.ShortUserModel;
import com.proiect.lamunca.service.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/User")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllUsers() throws ApplicationException {
        List<User> result = userService.getAll();
        if (result==null || result.isEmpty()) {
            throw new ApplicationException("Not found entities", Response.SC_NOT_FOUND);
        } else {
            List<ShortUserModel> shortUserModels = result.stream()
                    .map(Mapper::mapFromUserToShortUserModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(shortUserModels);
        }
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createAccount(@RequestBody User user) throws ApplicationException {
        if(userService.getById(user.getUsername())){
            throw new ApplicationException("User with this username exists", Response.SC_BAD_REQUEST);
        }
        else {
            userService.add(user);
            return new ResponseEntity<>("Successful create account", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginModel loginModel) throws ApplicationException {
        if(!loginValidate(loginModel.getUsername(), loginModel.getPassword())){
            throw new ApplicationException("Empty fields", Response.SC_BAD_REQUEST);
        }

        try {
            if(userService.getUserByUsernameAndPassword(loginModel.getUsername(), loginModel.getPassword())){
                return new ResponseEntity<>("Successful login", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Failed login",HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            throw new ApplicationException(e.getMessage(), Response.SC_UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
    public ResponseEntity<?> deleteAccount(@RequestParam(value = "username", required = true) String username) throws ApplicationException {
        if(userService.getById(username) ==  false){
            throw new ApplicationException("User with this username not exists", Response.SC_NOT_FOUND);
        }
        else {
            userService.deleteUser(username);
            return new ResponseEntity<>("Successful delete account", HttpStatus.OK);
        }
    }

    private static boolean loginValidate(String username, String password){
        if(username.isEmpty() || username.equals(""))
            return false;
        if(password.isEmpty() || password.equals(""))
            return false;
        return true;
    }

    @RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody User user) throws ApplicationException {
        if(userService.getById(user.getUsername()) == false){
            throw new ApplicationException("Account with this username doesn't exist", Response.SC_NOT_FOUND);
        }
        else {
            if (userService.update(user)){
                return new ResponseEntity<>("Successful update account user", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Faild update account user", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestParam(value = "username", required = true) String username) throws ApplicationException {
        if(userService.getUser(username) ==  null){
            throw new ApplicationException("User with this username not exists", Response.SC_NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getJobProviders", method = RequestMethod.GET)
    public List<ShortUserModel> getUsersByJobIdApply(@RequestParam(value = "idJob") Integer idJob) throws ApplicationException {
            List<User> users = userService.getJobProviders(idJob);
            if(users == null)
                throw new ApplicationException("Nu exista utilizatori care au aplicat", org.apache.catalina.connector.Response.SC_NOT_FOUND);
            else {
                List<ShortUserModel> shortUserModels = users.stream()
                        .map(Mapper::mapFromUserToShortUserModel)
                        .collect(Collectors.toList());
                return shortUserModels;
            }
        }
}
