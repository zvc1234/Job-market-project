package com.proiect.lamunca.service;


import com.proiect.lamunca.entity.db2.Notification;
import com.proiect.lamunca.repository.db2.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements IService<Notification, Integer>{

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public boolean add(Notification element) {
        try {
            notificationRepository.save(element);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public boolean getById(Integer integer) {
        return false;
    }

    public List<Notification> getAllNotificationsByFrom(String from){
        return notificationRepository.getAllNotificationsByFrom(from);
    }

    public List<Notification> getAllNotificationsByTo(String to){
        return notificationRepository.getAllNotificationByTo(to);
    }
}
