package com.proiect.lamunca.service;

import com.proiect.lamunca.entity.db1.UserCategory;
import com.proiect.lamunca.repository.db1.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCategoryService implements IService<UserCategory,Integer> {

    @Autowired
    UserCategoryRepository userCategoryRepository;


    @Override
    public boolean add(UserCategory element){
        try {
            userCategoryRepository.addUserCategory(element.getDescription(), element.getTitle(), element.getCategory().getId());
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public boolean update(UserCategory element) {
        try {
            userCategoryRepository.save(element);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public List<UserCategory> getAll() {
        try {
            return userCategoryRepository.findAll();
        }
        catch (Exception ex){
            return null;
        }
    }

    @Override
    public boolean getById(Integer idJob) { return userCategoryRepository.existsById(idJob);    }

    public boolean delete(Integer idJob){
        try{
            userCategoryRepository.deleteById(idJob);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public List<UserCategory> getAllJobsByCategory(String category){
        List<UserCategory> userCategoriesList;
        userCategoriesList = userCategoryRepository.getAllJobsByCategory(category);
        return userCategoriesList;
    }

    public String getJobClient(int idJob){
        return userCategoryRepository.getJobClient(idJob);
    }

    public UserCategory getJobById(int idJob)
    {
        return userCategoryRepository.getJobById(idJob);
    }

    public List<UserCategory> getClientJobs(String clientName)
    {
        return userCategoryRepository.getClientJobs(clientName);
    }

    public UserCategory get(String description, String title, Long category){
        try {
            return userCategoryRepository.get(description, title, category);
        }catch (Exception ex){
            return null;
        }

    }
}
