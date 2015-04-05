package ca.ualberta.CMPUT301W15T06.test;

import java.lang.reflect.Array;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantTagListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.User;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.DialogInterface;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class US03_02_01_Test extends
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
	Object test_tag;
	Object update_list;

	public US03_02_01_Test() {
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
	test_tag = null;
	}


	/*
	* Test for US03.02.01 Basic Flow 1
	*/
	// test button exists
	public void test030201() {

		//User click "Change User"
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
		//User click "Change User"
		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				assertTrue(UserButton.performClick());	

				//test opening a dialog
		    	// access the alert dialog using the getDialog() method created in the activity
				AlertDialog d = (AlertDialog) activity.getDialog();
				
			}	
		});
		


		// get current activity			
		MainActivity myActivity = getActivity();
		// click "Claimant" button and create next activity
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ActivityMonitor activityMonitor00 = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
				// click button and open next activity.
				ClaimantButton.performClick();
				ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor00, 10000);
				// next activity is opened and captured.
				assertNotNull(nextActivity);
				
				/*
				 * Test for US03.02.01 Basic Flow 1
				 */
				// view which is expected to be present on the screen			
				final View decorView1 = nextActivity.getWindow().getDecorView();
				// layout of claim list
				listView = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView1, listView);
				// check whether the Button object's width and height attributes match the expected values
				final ViewGroup.LayoutParams layoutParams11 = listView.getLayoutParams();
				/*assertNotNull(layoutParams);*/
				assertEquals(layoutParams11.width, WindowManager.LayoutParams.MATCH_PARENT);
				assertEquals(layoutParams11.height, WindowManager.LayoutParams.WRAP_CONTENT);	
				
				/*
				 * Test for US03.02.01 Basic Flow 2,3,4
				 */
				// Click the menu option
				ActivityMonitor am = getInstrumentation().addMonitor(ClaimantTagListActivity.class.getName(), null, false);
				getInstrumentation().invokeMenuActionSync(nextActivity,ca.ualberta.CMPUT301W15T06.R.id.manage_tag, 1);
				
				/*
				 * Test for US03.02.01 Basic Flow 5
				 */	
				// open tag list page
				ClaimantTagListActivity thirdActivity = (ClaimantTagListActivity) getInstrumentation().waitForMonitorWithTimeout(am,10000);
				assertNotNull(thirdActivity);
				
				// view which is expected to be present on the screen			
				final View decorView2 = nextActivity.getWindow().getDecorView();
				// layout of tag list
				final ListView itemlv = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView2, itemlv);
				
				// check whether the Button object's width and height attributes match the expected values
				final ViewGroup.LayoutParams lp = listView.getLayoutParams();
				/*assertNotNull(layoutParams);*/
				assertEquals(lp.width, WindowManager.LayoutParams.MATCH_PARENT);
				assertEquals(lp.height, WindowManager.LayoutParams.WRAP_CONTENT);

				/*
				 * Test for US03.02.01 Basic Flow 5
				 */	
				
				/*
				 *  5a: test "add" button layout
				 */
				final Button add = (Button) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addTagButton);
				assertNotNull(add);
				final View dv = thirdActivity.getWindow().getDecorView();
				ViewAsserts.assertOnScreen(dv, add);

				final ViewGroup.LayoutParams layoutParams =add.getLayoutParams();
				assertNotNull(layoutParams);
				assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
				assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
				assertEquals("Incorrect label of the button", "Add", add.getText());
				
				// "Add" button activity
				// set tag
				final String tag = "a";
				EditText add_tag_text = ((EditText) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addTagEditText));
				add_tag_text.setText(tag);
				
				// check if the tag is saved
				final int count_before = u.getTagList().size();
				thirdActivity.runOnUiThread(new Runnable() {
					//test add button works
					@Override
					public void run() {
						add.performClick();
						int count_after = u.getTagList().size();
						// length of tag list should increase by 1
						assertEquals(count_before,count_after-1);
					}
				});
				
				/*
				 * 5b: test rename function
				 */
				//click item on item list
				thirdActivity.runOnUiThread(new Runnable() {
					//test add button works
					@Override
					public void run() {
						itemlv.getChildAt(0).performClick();
						ListView tagArray  = (ListView) itemlv.findViewById(ca.ualberta.CMPUT301W15T06.R.array.tag_dialog_array);
						assertNotNull(tagArray);
						tagArray.getChildAt(0).performClick();						
					}
				});
				
				// test alert dialog layout
				AlertDialog d = (AlertDialog) thirdActivity.getDialog();
				assertNotNull(d);
				
				//OK button
				Button positiveButton = d.getButton(DialogInterface.BUTTON_POSITIVE);
				assertNotNull(positiveButton);
				positiveButton.performClick();
				int count_re = u.getTagList().size();
				assertEquals(count_before,count_re);
				
				//cancel button
				Button negativeButton = d.getButton(DialogInterface.BUTTON_NEGATIVE);
				assertNotNull(negativeButton);
				negativeButton.performClick();
				assertNotNull(thirdActivity);
				
				/*
				 * 5c: test delete function
				 */
				thirdActivity.runOnUiThread(new Runnable() {
					//test add button works
					@Override
					public void run() {
						itemlv.getChildAt(0).performClick();
						ListView tagArray  = (ListView) itemlv.findViewById(ca.ualberta.CMPUT301W15T06.R.array.tag_dialog_array);
						assertNotNull(tagArray);
						tagArray.getChildAt(1).performClick();	
						int count_d = u.getTagList().size();
						
						// length of tag list should decrease by 1
						assertEquals(count_before,count_d-1);
						
					}
				});
				
				//finish activity
				thirdActivity.finish();
				nextActivity.finish();
			}

		});
		activity.finish();
	}
}