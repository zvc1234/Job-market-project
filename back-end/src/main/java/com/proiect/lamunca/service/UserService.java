package com.proiect.lamunca.service;

import com.proiect.lamunca.entity.db1.User;
import com.proiect.lamunca.repository.db1.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean add(User element) {
        try {
            userRepository.save(element);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public List<User> getAll() {
        try {
            return userRepository.findAll();
        }
        catch (Exception ex){
            return null;
        }
    }

    public boolean getUserByUsernameAndPassword(String username, String password){
        if(userRepository.getUserByUsernameAndPassword(username, password) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean getById(String username){
        return userRepository.exists(username) != null;
    }


    public User getUser(String username) {
        return userRepository.exists(username);
    }

    public boolean deleteUser(String username){
        try {
            userRepository.deleteByUsername(username);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public boolean update(User user){
        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public List<User> getJobProviders(int jobId)
    {
        return userRepository.getJobProviders(jobId);
    }

}
