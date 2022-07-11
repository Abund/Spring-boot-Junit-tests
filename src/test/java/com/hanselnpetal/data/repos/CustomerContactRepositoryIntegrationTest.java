package com.hanselnpetal.data.repos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.hanselnpetal.domain.CustomerContact;

@RunWith(SpringRunner.class)
//this gives us a lot of the things that we need for testing jpa codes
//added a whole lots of annotation including the transactional
@DataJpaTest
//this enables us to set up which type of datebase that we want to use for our testing
//we have the option of using an emmbede database or using the database that we have already configured
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CustomerContactRepositoryIntegrationTest {

	//not unit testing is need for the data access layer except u made changes to it other than what was give my spring
	//we want to see how well or data access lay put data into the data base or retrives it
	@Autowired
    private TestEntityManager entityManager;

	@Autowired
	private CustomerContactRepository customerContactRepository;
	
	@Test
    public void testFindByEmail() {
		
		// setup data scenario
		CustomerContact aNewContact = new CustomerContact();
		aNewContact.setEmail("fredj@myemail.com");
        
		// save test data
		entityManager.persist(aNewContact);

        // Find an inserted record
        CustomerContact foundContact = customerContactRepository.findByEmail("fredj@myemail.com");
        
        assertThat(foundContact.getEmail(), is(equalTo("fredj@myemail.com")));
    }
	
}
