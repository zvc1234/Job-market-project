package com.proiect.lamunca;

import com.proiect.lamunca.entity.db1.*;
import com.proiect.lamunca.entity.db2.Notification;
import com.proiect.lamunca.repository.db1.*;
import com.proiect.lamunca.repository.db2.NotificationRepository;
import com.proiect.lamunca.repository.db1.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Calendar;

@SpringBootApplication()
//@EnableJpaRepositories("com.proiect.lamunca.repository")
public class LaMuncaApplication {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserCategoryRepository userCategoryRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserJobRepository userJobRepository;

    @Autowired
    NotificationRepository notificationRepository;

//   @PostConstruct
//    private void populateDb(){
//
//        //users
//        User u1 = new User("user1","test","0745187254","user1@gmail.com","Catalin","Ion","bio biob biob ", Calendar.getInstance().getTime(),1);
//        User u2 = new User("user2","test","0721478645","user2@gmail.com","Daniel","Popescu","un alt bio",Calendar.getInstance().getTime(),1);
//        User u3 = new User("user3","test","0721677695","user3@gmail.com","Ovidiu","Pop","etc etc",Calendar.getInstance().getTime(),4);
//
//
//        userRepository.save(u1);
//        userRepository.save(u2);
//        userRepository.save(u3);
//
//
//        //categories
//        Category c1 = new Category("casa si gradina");
//        Category c2 = new Category("animale");
//        Category c3 = new Category("copii");
//        Category c4 = new Category("IT");
//        Category c5 = new Category("cursuri si meditatie");
//        Category c6 = new Category("gatit");
//        Category c7 = new Category("muzica");
//        Category c8 = new Category("sport");
//        Category c9 = new Category("organizare evenimente");
//        Category c10 = new Category("transport");
//        Category c11 = new Category("cumparaturi si livrari");
//        Category c12 = new Category("consultatii medicale");
//        Category c13 = new Category("infrumusetare");
//
//        categoryRepository.save(c1);
//        categoryRepository.save(c2);
//        categoryRepository.save(c3);
//        categoryRepository.save(c4);
//        categoryRepository.save(c5);
//        categoryRepository.save(c6);
//        categoryRepository.save(c7);
//        categoryRepository.save(c8);
//        categoryRepository.save(c9);
//        categoryRepository.save(c10);
//        categoryRepository.save(c11);
//        categoryRepository.save(c12);
//        categoryRepository.save(c13);
//
//        //joburi
//        UserCategory uc1 = new UserCategory(c2,"caut pe cineva sa imi plimbe cainele","caut pe cineva sa imi plimbe cainele intre orele x si y");
//        UserCategory uc2 = new UserCategory(c3,"caut babysitter","caut pe cineva sa stea cu copilul meu intre orele x si y");
//        UserCategory uc3 = new UserCategory(c2,"spalati-mi pisica !!! ","vreau pe cineva capabil sa spele o pisica");
//
//        userCategoryRepository.save(uc1);
//        userCategoryRepository.save(uc2);
//        userCategoryRepository.save(uc3);
//
//
//        //user_jobs
//        //clienti care au postat joburi
//        userJobRepository.save(new UserJob(u1,uc1,1));
//        userJobRepository.save(new UserJob(u2,uc2,1));
//        userJobRepository.save(new UserJob(u2,uc3,1));
//
//
//        //provider care au aplicat la joburi
//
//        userJobRepository.save(new UserJob(u1, uc2,0));
//
//        //reviews
//        reviewRepository.save(new Review("m-am bucurat sa il am prin preajma copilului meu","user2",u1,4));
//        reviewRepository.save(new Review(" NESERIOS !!!","user1",u2,1));
//        reviewRepository.save(new Review("si-a facut treaba foarte bine. recomand","user1",u2,5));
//
//        //notifications
//        notificationRepository.save(new Notification("user1", "user2", "[profile/user2] RECOMANDARE : user1 vi l-a recomandat pe user3"));
//        notificationRepository.save(new Notification("user1", "user2", "[profile/user2] RECOMANDARE : user1 vi l-a recomandat pe user3"));
//
//
//        //update stars
//        reviewRepository.updateUserStars(u1.getId(), u1.getUsername());
//        reviewRepository.updateUserStars(u2.getId(), u2.getUsername());
//
//
//
//    }

    public static void main(String[] args) {

        SpringApplication.run(LaMuncaApplication.class, args);
    }


}
