package com.proiect.lamunca.repository.db1;

import com.proiect.lamunca.entity.db1.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Integer> {

    @Query(value = "select * from user_category u where u.name_category=(select c.id_category from category c where c.name=?1)", nativeQuery = true)
    List<UserCategory> getAllJobsByCategory(String category);

    @Query(value = "select user_job from user_jobs u where u.job_category=?1 and u.owner=1", nativeQuery = true)
    String getJobClient(int jobId);

    @Query(value = "select * from user_category u where u.id_usercategory=?1", nativeQuery = true)
    UserCategory getJobById(int id);

    @Query(value = "select * from user_category uc where uc.id_usercategory in (select uj.job_category from user_jobs uj where owner=1 and user_job=(select id_user from user where username=?1))", nativeQuery = true)
    List<UserCategory> getClientJobs(String client);

    @Query(value = "insert into user_category(description, title, name_category) values(?1, ?2, ?3)", nativeQuery = true)
    @Modifying
    @Transactional
    void addUserCategory(String description, String title, Long category);

    @Query(value = "select * from user_category u where u.description = ?1 and u.title = ?2 and name_category = ?3", nativeQuery = true)
    UserCategory get(String description, String title, Long category);

}