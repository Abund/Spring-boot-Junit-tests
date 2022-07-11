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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.hanselnpetal.domain.CustomerContact;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
   TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
//this is an xml file that was created with the test datasets
@DatabaseSetup("classpath:test-datasets.xml")

public class CustomerContactRepositoryDbUnitTest {

    //dbunit is a framework that helps developer manage the no trival task of keeping
    // the data store in a know state before and after testing
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private CustomerContactRepository customerContactRepository;
	
	@Test
    public void testFindByEmail() {
		
        // Find an inserted record
        CustomerContact foundContact = customerContactRepository.findByEmail("elaine@myemail.com");
        
        assertThat(foundContact.getEmail(), is(equalTo("elaine@myemail.com")));
    }
	
	@Test
    public void testFindSpecificContactByIdBypassReposClass() {
		
        // Find an inserted record
        CustomerContact foundContact = entityManager.find(CustomerContact.class, new Long("2"));
        
        assertThat(foundContact.getEmail(), is(equalTo("elaine@myemail.com")));
    }
}
