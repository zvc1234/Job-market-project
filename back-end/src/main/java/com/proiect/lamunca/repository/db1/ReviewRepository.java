package com.proiect.lamunca.repository.db1;

import com.proiect.lamunca.entity.db1.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = "SELECT * FROM review WHERE review.receiver_username = (SELECT username FROM user WHERE username = ?1 )",nativeQuery = true)
    List<Review> getReviewsByUsernameReceiver(String usernameReceiver);

    @Query(value = "SELECT count(*) FROM review WHERE review.writer_username = (SELECT username FROM user WHERE username = ?1 )",nativeQuery = true)
    int getReviewsByUsernameWriter(String usernameWriter);

    @Query(value = "UPDATE user SET stars_average=IFNULL((SELECT avg(stars) FROM review where receiver_username=?1), 0) where username=?2", nativeQuery = true)
    @Modifying
    @Transactional
    void updateUserStars(Long id_user, String username);
}
