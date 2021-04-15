package com.proiect.lamunca.mappers;

import com.proiect.lamunca.entity.db1.Category;
import com.proiect.lamunca.entity.db1.Review;
import com.proiect.lamunca.entity.db1.UserCategory;
import com.proiect.lamunca.entity.db1.UserJob;
import com.proiect.lamunca.entity.db1.User;
import com.proiect.lamunca.models.JobModel;
import com.proiect.lamunca.models.ReviewModel;
import com.proiect.lamunca.models.ShortJobModel;
import com.proiect.lamunca.models.ShortUserModel;


public class Mapper {
    public static UserCategory mapFromJobModelToUserCategory(JobModel model, Category category)
    {
        UserCategory userCategory = new UserCategory();
        userCategory.setId(0);
        userCategory.setTitle(model.getTitle());
        userCategory.setDescription(model.getDescription());
        userCategory.setCategory(category);

        return userCategory;
    }

    public static Review mapFromReviewModelToReview(ReviewModel model){
        Review review = new Review();
        review.setId_review(0);
        review.setComment(model.getComment());
        review.setStars(model.getStars());
        review.setWriter_username(model.getWriter_username());
        review.setUsername(new User(model.getReceiver_username()));

        return review;
    }

    public static JobModel mapFromUserCategoryToJobModel(UserCategory model, String username)
    {
        JobModel jobModel = new JobModel();
        jobModel.setId(model.getId());
        jobModel.setTitle(model.getTitle());
        jobModel.setDescription(model.getDescription());
        jobModel.setCategoryName(model.getCategory().getName());
        jobModel.setUsername(username);
        return jobModel;
    }

    public static ShortJobModel mapFromJobModelToShortJobModel(JobModel model)
    {
        ShortJobModel shortJobModel = new ShortJobModel();
        shortJobModel.setId(model.getId());
        shortJobModel.setTitle(model.getTitle());
        shortJobModel.setCategoryName(model.getCategoryName());

        return shortJobModel;
    }

    public static ShortUserModel mapFromUserToShortUserModel(User user)
    {
        ShortUserModel shortUserModel = new ShortUserModel();
        shortUserModel.setUsername(user.getUsername());
        shortUserModel.setPhone(user.getPhone());
        shortUserModel.setStars_average(user.getStars_average());

        return shortUserModel;
    }

    public static UserJob mapFromJobModelToUserJob(JobModel jobModel, int owner)
    {
        UserJob userJob = new UserJob();
       // UserCategory userCategory = mapFromJobModelToUserCategory(jobModel);
       // userJob.setUser_category(userCategory);
        userJob.setUser(new User(jobModel.getUsername()));
        userJob.setOwner(owner);

        return userJob;
    }

}
