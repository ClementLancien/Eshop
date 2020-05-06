package com.infotel.eshop.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
//@SuiteClasses(value={CatalogServiceTest.class,
//					 UserServiceTest.class})
@SuiteClasses({
	SearchProductSpec.class,
	AuthenticationSpec.class,
	RegisterCustomerSpec.class,
	OrderSpec.class,
	LoadImageTestSpec.class
})
public class AllServiceTest {
	
}