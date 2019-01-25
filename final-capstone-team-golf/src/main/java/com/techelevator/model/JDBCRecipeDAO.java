package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCRecipeDAO implements RecipeDAO{
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCRecipeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Recipe> getAllRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		String sqlSelectRecipes = "SELECT recipe_id,name,serving_size,preptime,instructions,ingredient_list,admin_created,user_created,image_path,user_uploaded_image FROM recipe";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectRecipes);
		while(results.next()) {
			recipes.add(mapRowToRecipe(results));
		}
		return recipes;
    }


	@Override
	public void createRecipe(Recipe recipe) {
		long id = getNextId();
		recipe.setRecipeId(id);
		String sqlInsertRecipe = "Insert into recipe (recipe_id,name,serving_size,preptime,instructions,ingredient_list,admin_created,user_created) "+
				"values (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sqlInsertRecipe, recipe.getRecipeId(),recipe.getRecipeName(),recipe.getServingSize(),recipe.getPrepTime(),recipe.getInstructions(),recipe.getIngredients(),recipe.isAdminCreated(),recipe.isUserCreated());
		
	}
	@Override
	public List<Recipe> getRecipesBySearch(String search) {
		List<Recipe> recipes = new ArrayList<>();
		String sqlSelectRecipes = "SELECT recipe.recipe_id, recipe.name, recipe.serving_size, recipe.preptime, recipe.instructions, recipe.user_created, recipe.admin_created, recipe.ingredient_list,recipe.image_path,recipe.user_uploaded_image\n" + 
				"FROM recipe\n" + 
				"WHERE recipe.name iLIKE ?\n" + 
				"UNION\n" + 
				"SELECT recipe.recipe_id, recipe.name, recipe.serving_size, recipe.preptime, recipe.instructions, recipe.user_created, recipe.admin_created, recipe.ingredient_list,recipe.image_path,recipe.user_uploaded_image\n" + 
				"FROM recipe\n" + 
				"INNER JOIN cat_recipe\n" + 
				"ON recipe.recipe_id = cat_recipe.recipe_id\n" + 
				"INNER JOIN category\n" + 
				"ON cat_recipe.category_id = category.category_id\n" + 
				"WHERE category.category_name iLIKE ?\n" + 
				"UNION\n" + 
				"SELECT recipe.recipe_id, recipe.name, recipe.serving_size, recipe.preptime, recipe.instructions, recipe.user_created, recipe.admin_created, recipe.ingredient_list,recipe.image_path,recipe.user_uploaded_image\n" + 
				"FROM recipe\n" + 
				"INNER JOIN tag_recipe\n" + 
				"ON recipe.recipe_id = tag_recipe.recipe_id\n" + 
				"JOIN tag\n" + 
				"ON tag.tag_id = tag_recipe.tag_id\n" + 
				"WHERE tag.tag_name iLIKE ?;";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectRecipes, search, search, search);
			while(results.next()) {
				recipes.add(mapRowToRecipe(results));
			}
			return recipes;
	}// added this

	@Override
	public List<Recipe> getRecipeByTag(int tagId) {
		List<Recipe> recipes = new ArrayList<>();
		String sqlSelectRecipes = "SELECT recipe.recipe_id,recipe.name,recipe.serving_size,recipe.preptime,recipe.instructions, recipe.ingredient_list,recipe.admin_created,recipe.user_created,recipe.image_path,recipe.user_uploaded_image\n" + 
				"FROM recipe\n" + 
				"JOIN tag_recipe\n" + 
				"ON tag_recipe.recipe_id = recipe.recipe_id\n" + 
				"JOIN tag "+
				"ON tag.tag_id = tag_recipe.tag_id "+
				"WHERE tag.tag_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectRecipes,tagId);
		while(results.next()) {
			recipes.add(mapRowToRecipe(results));
		}
		return recipes;
    }
	@Override
	public Recipe getRecipeByName(String name) {
		Recipe recipe = new Recipe();
		String sqlSelectRecipes = "SELECT recipe_id,name,serving_size,preptime,instructions,ingredient_list,admin_created,user_created,image_path,user_uploaded_image\n" + 
				"FROM recipe\n" + 
				"WHERE name iLIKE ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectRecipes,name);
		while(results.next()) {
			recipe = (mapRowToRecipe(results));
		}
		return recipe;
	}//

	@Override
	public List<Recipe> getRecipeByCategory(int categoryId) {
		List<Recipe> recipes = new ArrayList<>();
		String sqlSelectProducts = "SELECT recipe.recipe_id,recipe.name,recipe.serving_size,recipe.preptime,recipe.instructions,recipe.ingredient_list,admin_created,user_created,recipe.image_path,recipe.user_uploaded_image\n" + 
				"FROM recipe\n" + 
				"JOIN cat_recipe\n" + 
				"ON cat_recipe.recipe_id = recipe.recipe_id\n" + 
				"JOIN category "+
				"ON category.category_id = cat_recipe.category_id "+
				"WHERE category.category_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectProducts,categoryId);
		while(results.next()) {
			recipes.add(mapRowToRecipe(results));
		}
		return recipes;
    }
	
	@Override
	public Recipe getRecipeById(long recipeId) {
		Recipe recipe = new Recipe();
		String sqlSelectRecipe = "SELECT recipe_id,name,serving_size,preptime,instructions,ingredient_list,admin_created,user_created,image_path,user_uploaded_image\n" + 
				"FROM recipe\n" + 
				"WHERE recipe_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectRecipe,recipeId);
		while(results.next()) {
			recipe = mapRowToRecipe(results);
		}
		return recipe;
    }
	
	@Override
	public List<Recipe> getUnapprovedRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		String sqlSelectRecipes = "SELECT recipe_id,name,serving_size,preptime,instructions,user_created,admin_created,ingredient_list,image_path,user_uploaded_image\n" + 
				"FROM recipe\n" + 
				"WHERE user_created = true;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectRecipes);
		while(results.next()) {
			recipes.add(mapRowToRecipe(results));
		}
		return recipes;
    }
	
	@Override
	public void approveRecipe(long recipeId) {
		jdbcTemplate.update("UPDATE recipe \n" + 
				"SET user_created = false, admin_created = true\n" + 
				"WHERE recipe_id = ?;",recipeId);
	}
	
	@Override
	public void updateImagePath(String imagePath,long recipeId) {
		jdbcTemplate.update("UPDATE recipe\n" + 
				"SET image_path = ?,user_uploaded_image = true\n" + 
				"WHERE recipe_id = ?;",imagePath,recipeId);
	}
	
	private Recipe mapRowToRecipe(SqlRowSet results) {
		Recipe recipe = new Recipe();
		recipe.setRecipeId(results.getLong("recipe_id"));
		recipe.setRecipeName(results.getString("name"));
		recipe.setPrepTime(results.getInt("preptime"));
		recipe.setServingSize(results.getInt("serving_size"));
		recipe.setInstructions(results.getString("instructions"));
		recipe.setIngredients(results.getString("ingredient_list"));
		recipe.setAdminCreated(results.getBoolean("admin_created"));
		recipe.setUserCreated(results.getBoolean("user_created"));
		recipe.setImagePath(results.getString("image_path"));
		recipe.setUserUploadedImage(results.getBoolean("user_uploaded_image"));
		
		return recipe;
	}
	
	
	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_recipe_id')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		int id = 0;
		if(results.next()) {
			id = results.getInt(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next recipe ID from sequence");
		}
		return id;
	}





}
