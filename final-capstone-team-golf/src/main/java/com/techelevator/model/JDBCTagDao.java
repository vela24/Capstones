package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JDBCTagDao implements TagDao {
	
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCTagDao(DataSource data) {
		this.jdbcTemplate = new JdbcTemplate(data);
	}

	@Override
	public void submitTag(long tag_id, long recipe_id) {
		String sql = "INSERT INTO tag_recipe(tag_id, recipe_id) VALUES(?, ?)";
		jdbcTemplate.update(sql, tag_id,recipe_id);
		

	}
	@Override
	public Tag getTagIdByName(String tagName) {
		Tag tag = new Tag();
		String sqlSelectRecipe = "SELECT tag_id,tag_name\n" + 
				"FROM tag\n" + 
				"WHERE tag_name ILIKE ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectRecipe,tagName);
		while(results.next()) {
			tag = mapRowToTag(results);
		}
		return tag;
    }
	
	@Override
	public void createTag(Tag tag) {
			long id = getNextId();
			tag.setTagId(id);
			String sqlInsertRecipe = "Insert into tag (tag_id,tag_name)values (?,?)";
			jdbcTemplate.update(sqlInsertRecipe,id,tag.getTagName());
			
		}
	
	@Override
	public List<Tag> getTagsByRecipeId(long recipeId) {
		List<Tag> tags = new ArrayList<>();
		String sqlSelectTag = "SELECT tag.tag_id,tag.tag_name\n" + 
				"FROM tag\n" + 
				"JOIN tag_recipe\n" + 
				"ON tag_recipe.tag_id = tag.tag_id\n" + 
				"JOIN recipe\n" + 
				"ON recipe.recipe_id = tag_recipe.recipe_id\n" + 
				"WHERE recipe.recipe_id =?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectTag,recipeId);
		while(results.next()) {
			tags.add(mapRowToTag(results));
		}
		return tags;
    }

	
	private Tag mapRowToTag(SqlRowSet results) {
		Tag tag = new Tag();
		tag.setTag(results.getLong("tag_id"));
		tag.setTagName(results.getString("tag_name"));
		
		return tag;
	}
	
	@Override
	public boolean searchForTag(String tag) {
		String sqlSearchForUser = "SELECT * FROM tag WHERE tag_name = ?;";
		
		SqlRowSet tag1 = jdbcTemplate.queryForRowSet(sqlSearchForUser,tag);
		if(tag1.next()) {
			String tagName = tag1.getString("tag_name");
			return tagName.equals(tag);
	
		} else {
			return false;
		}
	}
	
	
	
	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_tag_id')";
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
