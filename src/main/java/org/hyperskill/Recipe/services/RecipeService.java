package org.hyperskill.Recipe.services;

import org.hyperskill.Recipe.model.Recipe;
import org.hyperskill.Recipe.model.RecipeId;

import java.util.List;
import java.util.Map;


public interface RecipeService {

    public RecipeId addNewRecipe(Recipe recipe, String userEmail);
    public Recipe getRecipeById(Long id);
    public void deleteRecipeById(Long id, String username);
    public void updateRecipe(Recipe newRecipe, Long id, String userEmail);
    public List<Recipe> searchRecipe(Map<String, String> nameOrCategory);
}
