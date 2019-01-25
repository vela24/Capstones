package com.techelevator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.JDBCRecipeDAO;
import com.techelevator.model.JDBCTagDao;
import com.techelevator.model.Recipe;
import com.techelevator.model.Tag;


public class JDBCTagDAOTest extends DAOIntegrationTest{
	
	private JDBCTagDao dao = new JDBCTagDao(getDataSource());
	private JDBCRecipeDAO dao2 = new JDBCRecipeDAO(getDataSource());
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

@Before
public void truncateTables() {
	JdbcTemplate template = new JdbcTemplate(getDataSource());
	int numberOfRowsAffected = template.update("TRUNCATE TABLE recipe,tag,diet,meal_plans,users, category CASCADE;");
	System.out.printf("%d records have been truncated.", numberOfRowsAffected);
}

@Before
public void setup() {
	
	String insertRecipe = "insert into recipe (recipe_id,name, serving_size, preptime, instructions,ingredient_list) values(1,'Baby Spinach, Avacado, Grapefruit Salad with Balsamin Vinaigrette', 10, 15, '1. combine the vinegars, mustard, salt, pepper, and sugar ina  small bowl. \n" + 
			"\n" + 
			"2. Gradually whisk in the oil a little at a time until the dressing is emulsified . \n" + 
			"\n" + 
			"3. In a larger bowl, add the spinach, avacado, and grapefruit segments. Drizzle the balsamic dressing and toss. Season with Salt and Pepper','Test Ingredient List');"; 
	String insertTag = "INSERT INTO tag(tag_id, tag_name) values (1, 'lowfat') " ;
	String insertTag1 = "INSERT INTO category(category_id, category_name) VALUES (1, 'Keto')";
	String insertTag2 = "INSERT INTO cat_recipe(category_id, recipe_id) VALUES (1, 1) " ;
	
	jdbcTemplate.update(insertRecipe);
	jdbcTemplate.update(insertTag);
	jdbcTemplate.update(insertTag1);
	jdbcTemplate.update(insertTag2);

	
}
	@Test
	public void testSubmitTags() {
		dao.submitTag(1, 1);
		List<Recipe> myRecipes = dao2.getRecipeByTag(1);
		assertNotNull(myRecipes);
		assertEquals(1, myRecipes.size());
		
	}
	
	@Test
	public void testGetTagIdByName() {
		Tag mytag = dao.getTagIdByName("lowfat");
		assertEquals(Long.valueOf(1), mytag.getTagId());
		
	}
	
	@Test
	public void testCreateTag() {
		Tag mytag = new Tag();
		mytag.setTag(2L);
		mytag.setTagName("Vegan");
		dao.createTag(mytag);
		Tag myNewTag = dao.getTagIdByName("Vegan");
		assertEquals(mytag.getTagId(), myNewTag.getTagId());
	}
	
	@Test
	public void returns_true_if_tag_match() {
		Tag mytag = new Tag();
		mytag.setTag(3L);
		mytag.setTagName("vegan");
		dao.createTag(mytag);
		boolean result = dao.searchForTag(mytag.getTagName());
		assertThat(result, equalTo(true));
	}
	
	@Test
	public void returns_true_if_tag_do_not_match() {
		Tag mytag = new Tag();
		mytag.setTag(3L);
		mytag.setTagName("vegan");
		dao.createTag(mytag);
		boolean result = dao.searchForTag("Healthy");
		assertThat(result, equalTo(false));
	}
	
	@Test
	public void testGetTagNameByRecipeId() {
		dao.submitTag(1, 1);
		List<Tag> myTags = dao.getTagsByRecipeId(1);
		assertNotNull(myTags);
		assertEquals(1,myTags.size());
		
	}
	
	
	
	

}
