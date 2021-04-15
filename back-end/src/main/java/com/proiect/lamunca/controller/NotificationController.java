
package com.proiect.lamunca.controller;


import com.proiect.lamunca.entity.db2.Notification;
import com.proiect.lamunca.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/getAllNotifications", method = RequestMethod.GET, produces = "application/json")
    public List<Notification> getAllNotifications(){
        return notificationService.getAll();
    }

    @RequestMapping(value = "/addNotification", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> addNotification(@RequestBody Notification notification){
        try {
            notificationService.add(notification);
            return new ResponseEntity<>("Successful add notification", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Faild add notification", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getAllNotificationsByFrom", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllNotificationsByFrom(@RequestParam(value = "fromNotification", required = true) String from){
        try {
            List<Notification> notifications = new ArrayList<>();
            notifications = notificationService.getAllNotificationsByFrom(from);
            if(notifications != null){
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(notifications);
            }
            else {
                return new ResponseEntity<>("No notificatios found",HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex){
            return new ResponseEntity<>("Faild found notification", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/getAllNotificationsByTo", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllNotificationsByTo(@RequestParam(value = "toNotification", required = true) String to){
        try {
            List<Notification> notifications = new ArrayList<>();
            notifications = notificationService.getAllNotificationsByTo(to);
            if(notifications != null){
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(notifications);
            }
            else {
                return new ResponseEntity<>("No notificatios found",HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex){
            return new ResponseEntity<>("Faild found notification", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
