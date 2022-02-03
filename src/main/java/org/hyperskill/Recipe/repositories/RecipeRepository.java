package org.hyperskill.Recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.hyperskill.Recipe.model.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    public List<Recipe> findByCategoryEqualsIgnoreCaseOrderByDateDesc(String category);
    public List<Recipe> findByNameContainingIgnoreCaseOrderByDateDesc(String name);
}
