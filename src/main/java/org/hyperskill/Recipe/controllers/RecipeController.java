package org.hyperskill.Recipe.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.hyperskill.Recipe.model.Recipe;
import org.hyperskill.Recipe.model.RecipeId;
import org.hyperskill.Recipe.services.RecipeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService){

        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable Long id){

        return recipeService.getRecipeById(id);
    }

    @PostMapping("/new")
    public RecipeId createNewRecipe(@Valid @RequestBody Recipe recipe, @AuthenticationPrincipal UserDetails userDetails){

        return recipeService.addNewRecipe(recipe, userDetails.getUsername());
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
            recipeService.deleteRecipeById(id, userDetails.getUsername());
    }

    @PutMapping("/{id}")
    public void updateRecipe(@RequestBody @Valid Recipe recipe, @PathVariable Long id,
                             @AuthenticationPrincipal UserDetails userDetails){
            recipeService.updateRecipe(recipe, id, userDetails.getUsername());

    }

    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam Map<String, String> param){
        return recipeService.searchRecipe(param);
    }
}
