package com.proiect.lamunca.service;

import com.proiect.lamunca.entity.db1.User;
import com.proiect.lamunca.entity.db1.UserCategory;
import com.proiect.lamunca.entity.db1.UserJob;
import com.proiect.lamunca.repository.db1.UserJobRepository;
import com.proiect.lamunca.repository.db1.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

@Service
public class UserJobService implements IService<UserJob,Integer> {

    @Autowired
    UserJobRepository userJobRepository;

    @Autowired
    UserRepository userRepository;


    public boolean addJob(UserJob element) {
        try {
            userJobRepository.addJob(element.getOwner(), element.getUser().getId(), element.getUser_category().getId());

            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean add(UserJob element) {
        try {
            userJobRepository.save(element);

            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public List<UserJob> getAll() {
        try {
            return userJobRepository.findAll();
        }
        catch (Exception ex){
            return null;
        }
    }

    @Override
    public boolean getById(Integer id) {
        return userJobRepository.existsById(id);
    }

    public void deleteJob(int idJob)
    {
        userJobRepository.deleteJob(idJob);
    }
}
