package com.techelevator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.techelevator.model.JDBCUserDAO;


public class JDBCUserDAOIntegrationTest  extends DAOIntegrationTest{

	@Autowired 
	private JDBCUserDAO userDAO;
	
	
	@Before
	public void initializeTsst() {
		userDAO = new JDBCUserDAO(getDataSource());
	}
	
	@Test
	public void returns_true_if_userName_and_password_match() {
		userDAO.saveUser("spiderman", "uncleBen");
		boolean result = userDAO.searchForUsernameAndPassword("spiderman", "uncleBen");
		assertThat(result, equalTo(true));
	}

	@Test
	public void returns_false_if_userName_does_not_exist() {
		userDAO.saveUser("spiderman", "uncleBen");
		boolean result = userDAO.searchForUsernameAndPassword("antman", "uncleBen");
		assertThat(result, equalTo(false));
	}

	@Test
	public void returns_false_if_userName_exists_but_password_is_incorrect() {
		userDAO.saveUser("spiderman", "uncleBen");
		boolean result = userDAO.searchForUsernameAndPassword("spiderman", "uncleBob");
		assertThat(result, equalTo(false));
	}
	
	@Test
	public void userName_is_not_case_sensitive() {
		userDAO.saveUser("SpiderMan", "uncleBen");
		boolean result = userDAO.searchForUsernameAndPassword("sPIDERmAN", "uncleBen");
		assertThat(result, equalTo(true));
	}
	
	@Test
	public void password_is_case_sensitive() {
		userDAO.saveUser("spiderman", "uncleBen");
		boolean result = userDAO.searchForUsernameAndPassword("spiderman", "uncleben");
		assertThat(result, equalTo(false));
	}
	
	

}
