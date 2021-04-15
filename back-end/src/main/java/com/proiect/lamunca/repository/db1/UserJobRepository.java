package com.proiect.lamunca.repository.db1;

import com.proiect.lamunca.entity.db1.UserJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

//@Repository
public interface UserJobRepository extends JpaRepository<UserJob,Integer> {

    @Query(value = "delete from user_jobs where job_category = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteJob(int idJob);

    @Query(value = "insert into user_jobs(owner, user_job, job_category) values(?1, ?2, ?3)", nativeQuery = true)
    @Modifying
    @Transactional
    void addJob(int owner, Long user_job, int category_job);

}
