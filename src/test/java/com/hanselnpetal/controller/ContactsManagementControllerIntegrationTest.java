package com.hanselnpetal.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.hanselnpetal.domain.CustomerContact;

@RunWith(SpringRunner.class)
//our wen enviroment is not longer none, here we want to similate a real web engine enviroment with a real servelet engine runing
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContactsManagementControllerIntegrationTest {
	//this is an integration test
	//we are testing what happens when are real controller access a  real contact
	//service component which inturn access a real repository that access a data source

	//first we inject in the controller that we want to test
	@Autowired
	ContactsManagementController contactsManagementController;
	
	@Test
	public void testAddContactHappyPath() {
		
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("Jenny");
		aContact.setLastName("Johnson");
		
		// POST our CustomerContact form bean to the controller; check the outcome
		String outcome = contactsManagementController.processAddContactSubmit(aContact);
		
		// Assert THAT the outcome is as expected
		assertThat(outcome, is(equalTo("success")));
	}
	
	@Test
	public void testAddContactFirstNameMissing() {
		CustomerContact aContact = new CustomerContact();
		
		// POST our CustomerContact form bean to the controller; check the outcome
		String outcome = contactsManagementController.processAddContactSubmit(aContact);

		//this can help detect flaws in production level codes
		// Assert THAT the outcome is as expected
		assertThat(outcome, is(equalTo("failure")));
		
	}
}
