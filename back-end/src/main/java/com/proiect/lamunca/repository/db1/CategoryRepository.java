package com.proiect.lamunca.repository.db1;

import com.proiect.lamunca.entity.db1.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query(value = "SELECT * FROM category c WHERE c.name = ?1",nativeQuery = true)
    Category getByName(String name);

}
