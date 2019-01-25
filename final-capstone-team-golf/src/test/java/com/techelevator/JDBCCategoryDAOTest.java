package com.techelevator;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Category;
import com.techelevator.model.JDBCCategoryDAO;
import com.techelevator.model.Recipe;

public class JDBCCategoryDAOTest extends DAOIntegrationTest{

	private JDBCCategoryDAO dao = new JDBCCategoryDAO(getDataSource());
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());


	@Before
	public void truncateTables() {
		JdbcTemplate template = new JdbcTemplate(getDataSource());
		int numberOfRowsAffected = template.update("TRUNCATE TABLE category CASCADE;");
		System.out.printf("%d records have been truncated.", numberOfRowsAffected);
	}
	@Before
	public void setup() {
		String insertCategory = "Insert into category(category_id, category_name) values(1,'Keto')";
		String insertCategory2 = "Insert into category(category_id, category_name) values(2,'Vegan')";
		String insertCategory3 = "Insert into category(category_id, category_name) values(3,'Vegetarian')";
		
		jdbcTemplate.update(insertCategory);
		jdbcTemplate.update(insertCategory2);
		jdbcTemplate.update(insertCategory3);
		
	}
	
	@Test
	public void testGetAllRecipes() {
		List<Category> myCategories = dao.getAllCategories();
		assertNotNull(myCategories);
		assertEquals(3, myCategories.size());
		
	}

}
