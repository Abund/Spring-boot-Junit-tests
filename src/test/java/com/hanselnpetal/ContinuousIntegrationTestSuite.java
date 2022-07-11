package com.hanselnpetal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hanselnpetal.controller.ContactsManagementControllerIntegrationTest;
import com.hanselnpetal.data.repos.CustomerContactRepositoryIntegrationTest;
import com.hanselnpetal.service.ContactsManagementServiceIntegrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses ({ DatastoreSystemsHealthTest.class, 
	ContactsManagementControllerIntegrationTest.class })

public class ContinuousIntegrationTestSuite {

	// intentionally empty - Test Suite Setup (annotations) is sufficient
	// this are smoke test that are ran during the continous integration process to
	//ensure that everthing works well to the basic minimum
}
