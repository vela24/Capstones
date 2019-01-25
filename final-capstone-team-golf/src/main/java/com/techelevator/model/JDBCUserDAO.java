package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCUserDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveUser(String userName, String password) {
		
		jdbcTemplate.update("INSERT INTO users(email, password) VALUES (?, ?)",
				userName, password);
	}

	@Override
	public boolean searchForUsernameAndPassword(String userName, String password) {
		String sqlSearchForUser = "SELECT * "+
							      "FROM users "+
							      "WHERE UPPER(email) = ? ";
		
		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if(user.next()) {
			String dbHashedPassword = user.getString("password");
			return dbHashedPassword.equals(password);
	
		} else {
			return false;
		}
	}

	@Override
	public void updatePassword(String userName, String password) {
		jdbcTemplate.update("UPDATE users SET password = ? WHERE email = ?", password, userName);
	}

	@Override
	public Object getUserByUserName(String userName) {
		String sqlSearchForUsername ="SELECT * "+
		"FROM users "+
		"WHERE UPPER(email) = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUsername, userName.toUpperCase()); 
		User thisUser = null;
		if(user.next()) {
			thisUser = new User();
			thisUser.setUserName(user.getString("email"));
			thisUser.setPassword(user.getString("password"));
			thisUser.setAdmin(user.getBoolean("admin"));
		}

		return thisUser;
	}

}
