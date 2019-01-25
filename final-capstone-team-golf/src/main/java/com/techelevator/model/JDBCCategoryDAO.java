package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCategoryDAO implements CategoryDAO{
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCCategoryDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<>();
		String sqlSelectProducts = "SELECT category_id,category_name FROM category";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectProducts);
		while(results.next()) {
			categories.add(mapRowToCategory(results));
		}
		return categories;
		
	}
	
	private Category mapRowToCategory(SqlRowSet results) {
		Category category = new Category();
		category.setCategoryId(results.getLong("category_id"));
		category.setCategoryName(results.getString("category_name"));
		
		
		return category;
	}

}
