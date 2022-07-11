package com.hanselnpetal.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.hanselnpetal.domain.CustomerContact;
import com.hanselnpetal.service.ContactsManagementService;


@RunWith(SpringRunner.class)
//this let our test enviroment know that we will unit testing and mvc controller
@WebMvcTest(ContactsManagementController.class)
public class ContactsManagementControllerUnitTest {


	//here we want to isolate our test to just the contact management controller
	//which means we are going to mock our contact management service

	@Autowired
	private MockMvc mockMvc;

	//we are adding a mock bean for our contact service
	@MockBean
	private ContactsManagementService contactsManagementService;

	//this annotation allows us to inject mock on start up or on use
	@InjectMocks
	private ContactsManagementController contactsManagementController;

	//here we are just initialzing out mocks
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);	
	}
	
	@Test
	public void testAddContactHappyPath() throws Exception {
		// setup mock Contact returned the mock service component
		CustomerContact mockCustomerContact = new CustomerContact();
		mockCustomerContact.setFirstName("Fred");
		
		when(contactsManagementService.add(any(CustomerContact.class)))
			.thenReturn(mockCustomerContact);
		
		// simulate the form bean that would POST from the web page
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("Fred");
		aContact.setEmail("fredj@myemail.com");
		
		// simulate the form submit (POST)
		mockMvc
			.perform(post("/addContact", aContact))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void testAddContactBizServiceRuleNotSatisfied() throws Exception {
		// setup a mock response of NULL object returned from the mock service component
		when(contactsManagementService.add(any(CustomerContact.class)))
			.thenReturn(null);
		
		// simulate the form bean that would POST from the web page
		CustomerContact aContact = new CustomerContact();
		aContact.setLastName("Johnson");
		
		// simulate the form submit (POST)
		mockMvc
			.perform(post("/addContact", aContact))
			.andExpect(status().is(302))
			.andReturn();
	}
	
	@Test
	public void testAddContactOccasionHappyPath() throws Exception {
		// implement this
	}
}
