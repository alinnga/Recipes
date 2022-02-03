package org.hyperskill.Recipe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.hyperskill.Recipe.model.Recipe;
import org.hyperskill.Recipe.model.RecipeId;
import org.hyperskill.Recipe.model.User;
import org.hyperskill.Recipe.repositories.RecipeRepository;
import org.hyperskill.Recipe.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService{

    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository){

        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeId addNewRecipe(Recipe recipe, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        recipe.setUser(user);
        recipe.setDate(LocalDateTime.now());
        recipeRepository.save(recipe);
        return new RecipeId(recipe.getId());
    }

    @Override
    public Recipe getRecipeById(Long id) {
        try {
            return recipeRepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteRecipeById(Long id, String userEmail) {
            Optional<Recipe> recipeOpt = recipeRepository.findById(id);
            if(recipeOpt.isPresent()){
                Recipe recipe = recipeOpt.get();
                if(recipe.getUser().getEmail()==userEmail){
                    recipeRepository.deleteById(id);
                    throw new ResponseStatusException(HttpStatus.NO_CONTENT);
                }
                else{
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
    }

    @Override
    public void updateRecipe(Recipe newRecipe, Long id, String userEmail) {
        Optional<Recipe> oldRecipeOpt = recipeRepository.findById(id);

        if(oldRecipeOpt.isPresent()){
            Recipe oldRecipe = oldRecipeOpt.get();

            if(oldRecipe.getUser().getEmail()==userEmail){
                oldRecipe.setCategory(newRecipe.getCategory());
                oldRecipe.setDescription(newRecipe.getDescription());
                oldRecipe.setDirections(newRecipe.getDirections());
                oldRecipe.setIngredients(newRecipe.getIngredients());
                oldRecipe.setName(newRecipe.getName());
                oldRecipe.setDate(LocalDateTime.now());
                recipeRepository.save(oldRecipe);
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            }
            else{
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Recipe> searchRecipe(Map<String, String> nameOrCategory) {
        List<Recipe> resultRecipes = new ArrayList<>();
        if(nameOrCategory.size()!=1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else if(nameOrCategory.containsKey("category")){
            String category = nameOrCategory.get("category");
            resultRecipes = recipeRepository.findByCategoryEqualsIgnoreCaseOrderByDateDesc(category);
        }
        else if(nameOrCategory.containsKey("name")){
            String name = nameOrCategory.get("name");
            resultRecipes =  recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return resultRecipes;
    }
}
