package com.proiect.lamunca.controller;

import com.proiect.lamunca.entity.db1.User;
import com.proiect.lamunca.entity.db1.UserCategory;
import com.proiect.lamunca.entity.db1.UserJob;
import com.proiect.lamunca.exception.ApplicationException;
import com.proiect.lamunca.mappers.Mapper;
import com.proiect.lamunca.models.JobApplyModel;
import com.proiect.lamunca.models.JobModel;
import com.proiect.lamunca.service.CategoryService;
import com.proiect.lamunca.service.UserCategoryService;
import com.proiect.lamunca.service.UserJobService;
import com.proiect.lamunca.service.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/UserJob")
public class UserJobController {
    @Autowired
    UserJobService userJobService;

    @Autowired
    UserCategoryService userCategoryService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/addJob", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> addJob(@RequestBody JobModel job) {

        UserCategory userCategory = new UserCategory(categoryService.getByName(job.getCategoryName()), job.getTitle(), job.getDescription());
        userCategoryService.add(userCategory);

        UserJob userJob = new UserJob(userService.getUser(job.getUsername()),
                userCategoryService.get(userCategory.getDescription(), userCategory.getTitle(), userCategory.getCategory().getId()), 1);

        if(userJobService.addJob(userJob)) {
            return new ResponseEntity<>("Job added with succes", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not added", HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/registerForJob", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> addJob2(@RequestBody JobApplyModel job) {
        if(userJobService.addJob(new UserJob(userService.getUser(job.getUsername()), new UserCategory(job.getJobId()), 0))){
            return new ResponseEntity<>("Applied for job with succes", HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not apply", HttpStatus.NOT_ACCEPTABLE);
    }


    @RequestMapping(value = "/deleteJob", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteJob(@RequestParam(value = "idJob") Integer idJob) throws ApplicationException {
        if(!userCategoryService.getById(idJob)){
            throw new ApplicationException("Job with this idJob doesn't exist", Response.SC_NOT_FOUND);
        }
        else {
            userJobService.deleteJob(idJob);
            if(userCategoryService.delete(idJob))
            {
                return new ResponseEntity<>("Job deleted", HttpStatus.OK);
            }
            return new ResponseEntity<>("Job not deleted", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
