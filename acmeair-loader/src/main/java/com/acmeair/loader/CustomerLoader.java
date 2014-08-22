/*******************************************************************************
* Copyright (c) 2013 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*******************************************************************************/
package com.acmeair.loader;

import com.acmeair.entities.Customer;
import com.acmeair.entities.CustomerAddress;
import com.acmeair.entities.Customer.PhoneType;
import com.acmeair.service.CustomerService;
import com.netflix.config.DynamicPropertyFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CustomerLoader {
	private static final int NUM_CUSTOMERS = DynamicPropertyFactory.getInstance().getIntProperty("com.acmeair.loader.numCustomers", 10000).get();

	@Inject
	private CustomerService customerService;

	public void loadCustomers() {
		int numCustomers = NUM_CUSTOMERS;
		
		CustomerAddress address = new CustomerAddress("123 Main St.", null, "Anytown", "NC", "USA", "27617");
		for (long ii = 0; ii < numCustomers; ii++) {
			customerService.createCustomer("uid"+ii+"@email.com", "password", Customer.MemberShipStatus.GOLD, 1000000, 1000, "919-123-4567", PhoneType.BUSINESS, address);
		}
	}

}