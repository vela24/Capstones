package com.techelevator;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.JDBCRecipeDAO;
import com.techelevator.model.Recipe;

public class JDBCRecipeDAOTest extends DAOIntegrationTest{
	
	private JDBCRecipeDAO dao = new JDBCRecipeDAO(getDataSource());
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());


	@Before
	public void truncateTables() {
		JdbcTemplate template = new JdbcTemplate(getDataSource());
		int numberOfRowsAffected = template.update("TRUNCATE TABLE recipe,tag,diet,meal_plans,users, category CASCADE;");
		System.out.printf("%d records have been truncated.", numberOfRowsAffected);
	}
	
	@Before
	public void setup() {
		String insertRecipe = "insert into recipe (recipe_id,name, serving_size, preptime, instructions,ingredient_list,user_created,admin_created) values(1,'Baby Spinach, Avacado, Grapefruit Salad with Balsamin Vinaigrette', 10, 15, '1. combine the vinegars, mustard, salt, pepper, and sugar ina  small bowl. \n" + 
				"\n" + 
				"2. Gradually whisk in the oil a little at a time until the dressing is emulsified . \n" + 
				"\n" + 
				"3. In a larger bowl, add the spinach, avacado, and grapefruit segments. Drizzle the balsamic dressing and toss. Season with Salt and Pepper','Test Ingredient List',false,true);"; 
		
		String insertTag = "Insert into tag (tag_id,tag_name) values (1,'High Veggie Content')";
		String insertRecipeTag = "Insert into tag_recipe (recipe_id,tag_id) values(1,1)";
		String insertCategory = "Insert into category(category_id, category_name) values(1,'Keto')";
		String insertRecipeCategory = "Insert into cat_recipe(category_id,recipe_id) values(1,1)";
		String insertRecipFromUser = "Insert into recipe (recipe_id,name, serving_size, preptime, ingredient_list, instructions,user_created,admin_created) \n" + 
				"values(2,'Baby Spinach, Avacado, Grapefruit Salad with Balsamin Vinaigrette', 10, 15, '2 medium avacados (sliced), 12oz baby spinach, 3 grapefruits, cut into segments, \n" + 
				"2oz red wine vinegar, 2oz balsmic vinegar, 1 teaspoon Dijon mustard, 1/2 teaspoon sugar, \n" + 
				"12oz olive oil, salt and pepper', '1. combine the vinegars, mustard, salt, pepper, \n" + 
				"and sugar ina  small bowl. 2. Gradually whisk in the oil a little at a time until the dressing is emulsified. \n" + 
				"3. In a larger bowl, add the spinach, avacado, and grapefruit segments. Drizzle the balsamic dressing and toss. Season with Salt and Pepper',true,false);";
		
		jdbcTemplate.update(insertRecipe);
		jdbcTemplate.update(insertTag);
		jdbcTemplate.update(insertRecipeTag);
		jdbcTemplate.update(insertCategory);
		jdbcTemplate.update(insertRecipeCategory);
		jdbcTemplate.update(insertRecipFromUser);
		
		
	}
	
	@Test
	public void testGetAllRecipes() {
		List<Recipe> myRecipes = dao.getAllRecipes();
		assertNotNull(myRecipes);
		assertEquals(2, myRecipes.size());
		
	}
	
	@Test
	public void getRecipesBySearchCategory() {
		String search = "Keto";
		List<Recipe> recipes = dao.getRecipesBySearch(search);
		assertNotNull(recipes);
		assertEquals(1, recipes.size());
		
	}
	
	@Test
	public void getRecipesBySearchName() {
		String search = "Baby Spinach, Avacado, Grapefruit Salad with Balsamin Vinaigrette";
		List<Recipe> recipes = dao.getRecipesBySearch(search);
		assertNotNull(recipes);
		assertEquals(2, recipes.size());
	}
	
	@Test
	public void getRecipesBySearchTag() {
		String search = "High Veggie Content";
		List<Recipe> recipes = dao.getRecipesBySearch(search);
		assertNotNull(recipes);
		assertEquals(1, recipes.size());
		
	}
	///////////////////////////////
	@Test
	public void testGetRecipesByTag() {
		List<Recipe> myRecipes = dao.getRecipeByTag(1);
		assertNotNull(myRecipes);
		assertEquals(1, myRecipes.size());
		
	}
	
	@Test
	public void testGetRecipesByCategory() {
		List<Recipe> myRecipes = dao.getRecipeByCategory(1);
		assertNotNull(myRecipes);
		assertEquals(1, myRecipes.size());
	}
	@Test
	public void testGetRecipeInstructionsByRecipeId() {
		Recipe myRecipe = dao.getRecipeById(1);
		
		assertNotNull(myRecipe);
		assertEquals("Baby Spinach, Avacado, Grapefruit Salad with Balsamin Vinaigrette",myRecipe.getRecipeName());
		assertEquals(Integer.valueOf(15),myRecipe.getPrepTime());
		assertEquals(Integer.valueOf(10),myRecipe.getServingSize());
		assertEquals("1. combine the vinegars, mustard, salt, pepper, and sugar ina  small bowl. \n" + 
				"\n" + 
				"2. Gradually whisk in the oil a little at a time until the dressing is emulsified . \n" + 
				"\n" + 
				"3. In a larger bowl, add the spinach, avacado, and grapefruit segments. Drizzle the balsamic dressing and toss. Season with Salt and Pepper",myRecipe.getInstructions());
		assertEquals("Test Ingredient List",myRecipe.getIngredients());

	}
	
	
	@Test
	public void testCreateRecipe() {
		Recipe myRecipe = new Recipe();
		
		myRecipe.setRecipeName("Golf teams recipe");
		myRecipe.setServingSize(3);
		myRecipe.setPrepTime(15);
		myRecipe.setInstructions("1.Pick up the phone,2.Order pizza");
		myRecipe.setIngredients("1 Phone, 10 Dollars, 3 friends");
		dao.createRecipe(myRecipe);
		Recipe myNewRecipe = dao.getRecipeById(myRecipe.getRecipeId());
		assertNotNull(myNewRecipe);
		assertEquals(myRecipe.getRecipeId(),myNewRecipe.getRecipeId());
		assertEquals("Golf teams recipe",myNewRecipe.getRecipeName());
		assertEquals(Integer.valueOf(3),myNewRecipe.getServingSize());
		assertEquals(Integer.valueOf(15),myNewRecipe.getPrepTime());
		assertEquals("1.Pick up the phone,2.Order pizza",myNewRecipe.getInstructions());
		assertEquals("1 Phone, 10 Dollars, 3 friends",myNewRecipe.getIngredients());
		
	}
	
	@Test
	public void testGetAllUnapprovedRecipes() {
		List<Recipe> myRecipes = dao.getUnapprovedRecipes();
		assertNotNull(myRecipes);
		assertEquals(1, myRecipes.size());
	}
	@Test
	public void testApproveRecipes() {
		dao.approveRecipe(2);
		List<Recipe> myRecipes = dao.getUnapprovedRecipes();
		assertEquals(0, myRecipes.size());
	}
	
	@Test
	public void testUpdateImagePath() {
		dao.updateImagePath(String.valueOf(3), 1);
		Recipe myRecipe = dao.getRecipeById(1);
		assertEquals(String.valueOf(3), myRecipe.getImagePath());
		
		
	}
	

}
