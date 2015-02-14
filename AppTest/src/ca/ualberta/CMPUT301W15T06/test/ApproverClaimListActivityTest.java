/*
UA CMPUT 301 Project Group: CMPUT301W15T06

Copyright {2015} {Jingjiao Ni

              Tianqi Xiao

              Jiafeng Wu

              Xinyi Pan 

              Xinyi Wu

              Han Wang}
Licensed under the Apache License, Version 2.0 (the "License");

you may not use this file except in compliance with the License.

You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under 
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 
ANY KIND, either express or implied. See the License for the specific language 
governing permissions and limitations under the License.

 */

package ca.ualberta.CMPUT301W15T06.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import ca.ualberta.CMPUT301W15T06.ApproverClaimListActivity;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimList;
import ca.ualberta.CMPUT301W15T06.Item;

public class ApproverClaimListActivityTest extends ActivityInstrumentationTestCase2<ApproverClaimListActivity>{
	
	Activity activity;
	
	public ApproverClaimListActivityTest() {
		super(ApproverClaimListActivity.class);
	}

	// test for use case 08.01.01
	// System promotes claim list and part of details
	public void testSubmittedClaimList() {

		// build claim list
		ClaimList cList = new ClaimList();
		
		// build new claim
		Claim test = new Claim("A");
		
		// set start date
		String d1 = "2012-03-27";
		test.setBeginDate(d1);
		
		// set approver name
		test.setApprover("Approver1");
		
		// set state
		test.setStatus("submitted");
		
		// set new expense item
		Item new_item = new Item("bus");
		new_item.setAmount(10);
		new_item.setCategory("traffic");
		new_item.setDate(d1);
		new_item.setCurrency("CAD");
		test.addItem(new_item);
		
		// add claim
		cList.addClaim(test);
		
		// get claim from list
		Claim ec = cList.getClaimList().get(0);
		
		// check values saved in expense claim
		// It's an error now because out function returns null
		assertFalse("expenseClaim should not be null", ec.equals(null));
		assertTrue("claiment's name should be 'A'", ec.getName().equals("A"));
		assertTrue("start date should be 2012-03-27", ec.getBeginDate().equals("2012-03-27"));
		assertTrue("approver name should be Approver1", ec.getApprover().equals("Approver1"));
		assertTrue("state should be submitted", ec.getStatus().equals("submitted"));
		assertTrue("expense item should not be null", ec.getItemList().get(0).equals(new_item));
		
		// test list on UI
		View v = activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.ApproverClaimList);
		// It's an error now because our function returns null
		assertTrue("This list is not null", v.equals(cList));
	}
	
	// test for use case 08.02.01
	// Claims list is sorted by date, from the oldest one to the nearest one
	public void testSortClaim() {
		
		// 4 new dates
		String d1 = "2012-03-27";
		String d2 = "2012-03-27";
		String d3 = "2014-05-12";
		String d4 = "2013-12-01";
		
		// build new claim list
		ClaimList cList = new ClaimList();
		
		// add new claim, date is d1
		Claim test1 = new Claim("A");
		cList.getClaimList().get(0).setBeginDate(d1);
		cList.addClaim(test1);
		
		// add new claim, date is d2
		Claim test2 = new Claim("B");
		cList.getClaimList().get(1).setBeginDate(d2);
		cList.addClaim(test2);
		
		// add new claim, date is d3
		Claim test3 = new Claim("C");
		cList.getClaimList().get(2).setBeginDate(d3);
		cList.addClaim(test3);
		
		// add new claim, date is d4
		Claim test4 = new Claim("D");
		cList.getClaimList().get(3).setBeginDate(d4);
		cList.addClaim(test4);
		
		// get length of claim list
		int length = cList.getClaimList().size();
		// initialize date
		String last_date = cList.getClaimList().get(0).getBeginDate();
		String new_date = last_date;
		// date should be sorted from the oldest one to the nearest one
		int i = 0;
		while (i < length){
			new_date = cList.getClaimList().get(i).getBeginDate();
			// It's an error now because our function returns null
			assertTrue("new date is larger than or equal to old date", new_date.compareTo(last_date)>=0);
			i++;
		}
		
		// test list on UI
		View v = activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.ApproverClaimList);
		// It's an error now because our function returns null
		assertTrue("This list is not null", v.equals(cList));
	}


	// test for use case 08.06.01
	// approver add comment for claim
	public void testAddComment() {
		// build a claim list
		ClaimList cList = new ClaimList();

		// add claim
		Claim test = new Claim("A");
		cList.addClaim(test);
		
		// add comment 
		test.setComment("Bussiness");
		
		// It's an error now because our function returns null
		assertTrue("Comment should be same with 'Bussiness'", test.getComment().equals("Bussiness"));
	}
	
	// test for use case 08.07.01
	// change the state of one claim to "return"
	public void testReturnClaim() {
		// build a claim list
		ClaimList cList = new ClaimList();

		// build new claim
		Claim test = new Claim("A");
		
		// set state as "submitted"
		test.setStatus("submitted");
		
		// add claim
		cList.addClaim(test);
		// check state, it should be set to 'submitted'
		// It's an error now because our function returns null
		assertTrue("state should be changed to 'submitted'", cList.getClaimList().get(0).getStatus().equals("submitted"));
		
		// change state to "returned"
		cList.getClaimList().get(0).setStatus("returned");
		// It's an error now because our function returns null
		assertTrue("state should be changed to 'returned'", cList.getClaimList().get(0).getStatus().equals("returned"));
	}
	
	// test for use case 08.08.01
	// change the state of one claim to "approved"
	public void testApproveClaim() {
		// build a claim list
		ClaimList cList = new ClaimList();

		// build new claim
		Claim test = new Claim("A");
		
		// set state as "submitted"
		test.setStatus("submitted");
		
		// add claim
		cList.addClaim(test);
		// check state, it should be set to 'submitted'
		// It's an error now because our function returns null
		assertTrue("state should be changed to 'submitted'", cList.getClaimList().get(0).getStatus().equals("submitted"));
		
		// check user's roll
		
		// change state to "approved"
		cList.getClaimList().get(0).setStatus("approved");
		assertTrue("state should be changed to 'approved'", cList.getClaimList().get(0).getStatus().equals("approved"));
		
		// set approver name
		cList.getClaimList().get(0).setApprover("Approver01");
		assertTrue("Approver name should be 'Approver01'", cList.getClaimList().get(0).getApprover().equals("Approver01"));
	}
	
	// test for use case 08.09.01
	// refuse claimants' request of changing claim status
	public void testRefuseClaimantChangingState () {
		// build a claim list
		ClaimList cList = new ClaimList();

		// build new claim
		Claim test = new Claim("A");
		
		// set state as "submitted"
		test.setStatus("submitted");
		
		// add claim
		cList.addClaim(test);
		// check state, it should be set to 'submitted'
		// It's an error now because our function returns null
		assertTrue("state should be changed to 'submitted'", cList.getClaimList().get(0).getStatus().equals("submitted"));
		
		// check user's roll
		
		// state should not be changed
		// It's an error now because our function returns null
		assertTrue("state should be changed to 'submitted'", cList.getClaimList().get(0).getStatus().equals("submitted"));
	}
}