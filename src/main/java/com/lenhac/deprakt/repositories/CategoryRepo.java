package com.lenhac.deprakt.repositories;

import com.lenhac.deprakt.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lee N on 07, Sat,Oct,2023.
 */

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findByNameContainingIgnoreCase(String searchText);

}
