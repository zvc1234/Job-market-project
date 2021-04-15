package com.proiect.lamunca.controller;


import com.proiect.lamunca.entity.db1.Category;
import com.proiect.lamunca.entity.db1.UserCategory;
import com.proiect.lamunca.exception.ApplicationException;
import com.proiect.lamunca.mappers.Mapper;
import com.proiect.lamunca.models.JobModel;
import com.proiect.lamunca.models.ShortJobModel;
import com.proiect.lamunca.service.UserCategoryService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/UserCategory")
public class UserCategoryController {
    @Autowired
    UserCategoryService userCategoryService;

    @RequestMapping(value = "/getAllJobs", method = RequestMethod.GET, produces = "application/json")
    public List<ShortJobModel> getAllShortJobs(){
        List<ShortJobModel> shortJobModels;
        List<UserCategory> userCategories = userCategoryService.getAll();

        shortJobModels = userCategories.stream()
                .map(userCategory ->
                Mapper.mapFromJobModelToShortJobModel(
                    Mapper.mapFromUserCategoryToJobModel(
                        userCategory,
                        userCategoryService.getJobClient(userCategory.getId()))))
                .collect(Collectors.toList());

        return shortJobModels;
    }

    @RequestMapping(value = "/getJobsFilteredByCategories", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public List<ShortJobModel> getShortJobs(@RequestBody List<Category> categories){
        List<ShortJobModel> shortJobModels;
        List<UserCategory> userCategories;

        userCategories = categories.stream()
                .flatMap(category -> userCategoryService.getAllJobsByCategory(category.getName()).stream())
                .collect(Collectors.toList());

        shortJobModels = userCategories.stream()
                .map(userCategory -> Mapper.mapFromJobModelToShortJobModel(
                        Mapper.mapFromUserCategoryToJobModel(
                                userCategory,
                                userCategoryService.getJobClient(userCategory.getId()))))
                .collect(Collectors.toList());

        return shortJobModels;
    }

    @RequestMapping(value = "/updateJob", method = RequestMethod.PUT)
    public ResponseEntity<?> updateJob(@RequestBody UserCategory job) throws ApplicationException {
        if(!userCategoryService.getById(job.getId())){
            throw new ApplicationException("Job with this id doesn't exist", Response.SC_NOT_FOUND);
        }
        else {
            if(userCategoryService.update(job)) {
                return new ResponseEntity<>("Job updated", HttpStatus.OK);
            }
            return new ResponseEntity<>("Job not updated", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/getJob", method = RequestMethod.GET)
    public ResponseEntity<?> getJob(@RequestParam(value = "idJob") Integer idJob) throws ApplicationException {
        if(!userCategoryService.getById(idJob)){
            throw new ApplicationException("Job with this idJob doesn't exist", Response.SC_NOT_FOUND);
        }
        else {
            JobModel job = Mapper.mapFromUserCategoryToJobModel(
                    userCategoryService.getJobById(idJob),
                    userCategoryService.getJobClient(idJob));
            return new ResponseEntity<>(job, HttpStatus.OK);        }
    }

    @RequestMapping(value = "/getClientJobs", method = RequestMethod.GET)
    public List<ShortJobModel> getUsersByJobIdApply2(@RequestParam(value = "clientName") String clientName) throws ApplicationException {
        List<UserCategory> userCategories = userCategoryService.getClientJobs(clientName);

        List<ShortJobModel> shortJobModels = userCategories.stream()
                .map(userCategory -> Mapper.mapFromJobModelToShortJobModel(
                        Mapper.mapFromUserCategoryToJobModel(
                                userCategory,
                                clientName)))
                .collect(Collectors.toList());

        return shortJobModels;
    }
}
