package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantEditDestinationActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.ShowLocationActivity;
import ca.ualberta.CMPUT301W15T06.User;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;

@SuppressLint("CutPasteId")
public class US01_07_01_Test<Final> extends
			ActivityInstrumentationTestCase2<MainActivity> {

	Button ApproverButton;
	Button ClaimantButton;
	Button UserButton;
	Instrumentation instrumentation;
	MainActivity activity;
	EditText textInput;
	Intent intent;
	TextView input_name;
	TextView input_start;
	TextView input_end;
	ListView listView;
	Menu menu;
	View View1;
	EditText claimant_name;
	EditText claimant_starting_date;
	EditText claimant_ending_date;
	Button FinishButton;
	ClaimantClaimListController cclc;
	User u;


	public US01_07_01_Test() {
		super(MainActivity.class);
	}

	//set up
	protected void setUp() throws Exception {
		super.setUp();
	instrumentation = getInstrumentation();
	activity = getActivity();
	setActivityInitialTouchMode(false);
	ApproverButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
	ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
	UserButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
	intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);	
	u = new User("t");
	cclc = new ClaimantClaimListController(u);


	}

	public void testUS010201() {
		/*
		* Test for US01.07.01 Basic Flow 1
		*/
		// test button layouts
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton));
 
		//test "Approver" button layout
		final View decorView = activity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, ApproverButton);

		final ViewGroup.LayoutParams layoutParams =
				ApproverButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
   
		Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		assertEquals("Incorrect label of the button", "Approver", view.getText());
		
		//test "Claimant" button layout
		ViewAsserts.assertOnScreen(decorView, ClaimantButton);

		final ViewGroup.LayoutParams layoutParams1 =
				ClaimantButton.getLayoutParams();
		assertNotNull(layoutParams1);
		assertEquals(layoutParams1.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams1.height, WindowManager.LayoutParams.WRAP_CONTENT);
   
		Button view1 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		assertEquals("Incorrect label of the button", "Claimant", view1.getText());
		
		//test "Change User" button layout
		ViewAsserts.assertOnScreen(decorView, ClaimantButton);

		final ViewGroup.LayoutParams layoutParams2 =
				UserButton.getLayoutParams();
		assertNotNull(layoutParams1);
		assertEquals(layoutParams2.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams2.height, WindowManager.LayoutParams.WRAP_CONTENT);
   
		Button view2 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
		assertEquals("Incorrect label of the button", "Change User", view2.getText());


		//User click "Change User"

		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {

				/*
				* Test for US 01.02.01 Basic Flow 2
				*/
				// click button to start another activity
				assertTrue(UserButton.performClick());	
				
				/*
				 * Test for US 01.02.01 Basic Flow 3
				 */
				//test opening a dialog
		    	// access the alert dialog using the getDialog() method created in the activity
				AlertDialog d = (AlertDialog) activity.getDialog();

//				// check layout
//		    	assertTrue(d.isShowing());
//		    	
//		    	Button p = d.getButton(AlertDialog.BUTTON_POSITIVE);
//		    	Button n = d.getButton(AlertDialog.BUTTON_NEGATIVE);
//		    	
//		    	final View decorView = activity.getWindow().getDecorView();
//				ViewAsserts.assertOnScreen(decorView, p);
//				ViewAsserts.assertOnScreen(decorView, n);
//				
//				/*
//				 * Test for US 01.02.01 Basic Flow 4 
//				 */
//				// set text
//				EditText et = activity.getInputField();
//				assertNotNull(et);
//				
//				et.setText("NewUser");
//				
//				assertTrue(p.performClick());

				
				}	
		});

		//click "Claimant" button and create next activity
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
		//open current activity			
		MainActivity myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
				}
		});

		ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		// next activity is opened and captured.
		assertNotNull(nextActivity);
		

		/*
		 * Test Case for US01.02.01 Basic Flow 7	
		 */
		
		// view which is expected to be present on the screen			
		final View decorView1 = nextActivity.getWindow().getDecorView();
	 
		listView = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView1, listView);
		// check whether the Button object's width and height attributes match the expected values
		final ViewGroup.LayoutParams layoutParams11 = listView.getLayoutParams();
		/*assertNotNull(layoutParams);*/
		assertEquals(layoutParams11.width, WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams11.height, WindowManager.LayoutParams.WRAP_CONTENT);	 
		

		final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		
		//get next activity
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click the list open next activity.
				ActivityMonitor am = getInstrumentation().addMonitor(ClaimantItemListActivity.class.getName(), null, false);
				claimList.getChildAt(0).performClick();
				ClaimantItemListActivity thirdActivity = (ClaimantItemListActivity) getInstrumentation().waitForMonitorWithTimeout(am, 10000);
				assertNotNull(thirdActivity);
	
				ListView ilv = (ListView) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView1, ilv);

				// Click the menu option;
				getInstrumentation().invokeMenuActionSync(thirdActivity,ca.ualberta.CMPUT301W15T06.R.id.detail, 1);
				//open the forth activity
				Activity forthActivity =   getInstrumentation().waitForMonitorWithTimeout(am, 10000);
				assertNotNull(forthActivity);
				
				/*
				 * Test for US 01.07.01 Basic Flow 2
				 */			

				//test 'Add a Destination' button layout
				Button addDestination = (Button) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
				final View dv = forthActivity.getWindow().getDecorView();
				ViewAsserts.assertOnScreen(dv, addDestination);
				final ViewGroup.LayoutParams lp =addDestination.getLayoutParams();
				assertNotNull(lp);
				assertEquals(lp.width, WindowManager.LayoutParams.WRAP_CONTENT);
				assertEquals(lp.height, WindowManager.LayoutParams.WRAP_CONTENT);
			
				assertEquals("Incorrect label of the button", "Add a destination", addDestination.getText());
				
				Claim claim = new Claim();
				int count1 = claim.getDestinationList().size();
				
				//test click "Add a Destination" button						
				// open next activity.
				final Button desButton = (Button) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
				forthActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						ActivityMonitor activityMonitor1 = getInstrumentation().addMonitor(ClaimantEditDestinationActivity.class.getName(), null, false);
						// click button and open next activity.
						desButton.performClick();
						// next activity is opened and captured.
						ClaimantEditDestinationActivity fifthActivity = (ClaimantEditDestinationActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor1, 1000);
						assertNotNull(fifthActivity);
						
						
					/*
					 * test US01.07.01 Basic Flow 3
					 */
						//test interface
						EditText claimant_des = ((EditText) fifthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.DestinationEditText));
						EditText claimant_reason = ((EditText) fifthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.ReasonEditText));
	
						final View dv1 = fifthActivity.getWindow().getDecorView();
						ViewAsserts.assertOnScreen(dv1, claimant_des);
						assertTrue(View.GONE == claimant_des.getVisibility());
	
						ViewAsserts.assertOnScreen(dv1, claimant_reason);
						assertTrue(View.GONE == claimant_reason.getVisibility());	
						
						
					/*
					 * test US01.07.01 Basic Flow 4
					 */
						//fill blank
						String claimantDes = "a";
						String claimantReason = "b";
						claimant_des.setText(claimantDes);
						claimant_reason.setText(claimantReason);
						
						
					/*
					 * test US01.07.01 Basic Flow 5
					 */
						//finish the activity and go back
						fifthActivity.finish();
					}
				});
				int count2 = claim.getDestinationList().size();
				assertEquals(count1,count2-1);
				
				/*
				 * test US01.07.01 Basic Flow 6,7,,9
				 */
				final ListView detailListView = (ListView) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.detailListView);
				forthActivity.runOnUiThread(new Runnable() {
					//test add button works
					@Override
					public void run() {
						ListView geoArray = (ListView) detailListView.findViewById(ca.ualberta.CMPUT301W15T06.R.array.dest_dialog_array);
						assertNotNull(geoArray);
						geoArray.getChildAt(0).performClick();
						ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ShowLocationActivity.class.getName(), null, false);
						ShowLocationActivity aaa = (ShowLocationActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
						assertNotNull(aaa);
					}
				});
				
			}
		});

	}
}