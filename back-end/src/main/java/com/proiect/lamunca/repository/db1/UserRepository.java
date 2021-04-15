package com.proiect.lamunca.repository.db1;

import com.proiect.lamunca.entity.db1.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

//@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select count(u) from User u where u.username = ?1 and u.password = ?2")
    int getUserByUsernameAndPassword(String username, String password);

    @Query(value = "update user u set u.password=?1, u.phone=?2, u.first_name=?3, u.last_name=?4, u.stars_average=?5, u.email=?6, u.date_of_birth=?7, u.bio=?8 where u.username=?9", nativeQuery = true)
    void updateUser(String password, String phone, String first_name, String last_name, double stars_average, String email, Date date_of_birth, String bio, String username);

    @Query(value = "select * from user where username in (select user_job from user_jobs j where j.job_category=?1 and owner = 0)", nativeQuery = true)
    List<User> getJobProviders(int id);

    @Query("select u from User u where u.username = ?1")
    User exists(String username);

    @Query("delete from User u where u.username = ?1")
    void deleteByUsername(String username);
}
