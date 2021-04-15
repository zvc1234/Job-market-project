package com.proiect.lamunca.repository.db2;


import com.proiect.lamunca.entity.db2.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query(value = "select * from notification where from_user=?1", nativeQuery = true)
    public List<Notification> getAllNotificationsByFrom(String from);

    @Query(value = "select * from notification where to_user=?1", nativeQuery = true)
    public List<Notification> getAllNotificationByTo(String to);

}
