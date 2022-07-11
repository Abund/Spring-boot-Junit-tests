package com.hanselnpetal.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.hanselnpetal.data.repos.CustomerContactRepository;
import com.hanselnpetal.domain.CustomerContact;

//this lets the junit enviroment know that we will be usng mock objects
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ContactsManagementServiceUnitTest {
	//we are writing unit test with the purpose of just testing the contact management service
	// since we are just testing the the service, we are going to mock our data access components
	// we will be using the mockito frameworks that works nicely within in the junit framework

	@Mock
	private CustomerContactRepository customerContactRepository;

	//this lets the junit know to inject the mocks that we need to run the service
	@InjectMocks
	private ContactsManagementService contactsManagementService;
	
	//this means that this code will be run before any of our test cases
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddContactHappyPath() {
		
		// Create a contact
		CustomerContact aMockContact = new CustomerContact();
		aMockContact.setFirstName("Jenny");
		aMockContact.setLastName("Johnson");

		//this means that when the customer repository recieves a call on its save method, for any customer
		//contact class then simply return the mock contact
		when(customerContactRepository.save(any(CustomerContact.class))).thenReturn(aMockContact);
				
		// Save the contact
		CustomerContact newContact = contactsManagementService.add(null);
		
		
		// Verify the save
		assertEquals("Jenny", newContact.getFirstName());
	}
}
