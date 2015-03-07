package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;

public class US01_01_01_Test_1 extends
		ActivityInstrumentationTestCase2<MainActivity> {

	Button ApproverButton;
	Button ClaimantButton;
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	Intent intent;
	TextView input_name;
	TextView input_start;
	TextView input_end;
	ListView listView;
	Menu menu;
	View View1;

	
	public US01_01_01_Test_1() {
		super(MainActivity.class);
	}

	//set up
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		ApproverButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);	
	}


	/*
	 * Test for US01.01.01 Basic Flow 1
	 */
	
	// test button exists
	public void testLayout() {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton));
	}
	 
	//test Approver button layout
	public void testApproverButtonlayout() {
	    final View decorView = activity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, ApproverButton);

	    final ViewGroup.LayoutParams layoutParams =
	           ApproverButton.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
	    assertEquals("Incorrect label of the button", "Approver", view.getText());
	}

	/*
	 * Test for US 01.01.01 Basic Flow 2
	 */
	
	//test Claimant Button layout
	public void testClaimantButtonlayout() {


	    final View decorView = activity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, ClaimantButton);

	    final ViewGroup.LayoutParams layoutParams =
	           ClaimantButton.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
	    assertEquals("Incorrect label of the button", "Claimant", view.getText());
	}
	
	// test if the button can create next activity
	public void testOpenNextActivity() {
		  // register next activity that need to be monitored.
		  ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);

		  // open current activity.
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
	  * Test Case for US01.01.01 Basic Flow 3
	  */

		  // view which is expected to be present on the screen
		  final View decorView = nextActivity.getWindow().getDecorView();
		  
		  listView = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		  // check if it is on screen
		  ViewAsserts.assertOnScreen(decorView, listView);
		  // check whether the Button object's width and height attributes match the expected values
		  final ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
		  /*assertNotNull(layoutParams);*/
		  assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
		  assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);		    
		
	
	/*
	 * Test for US01.01.01 Basic Flow 4
	 * Test for US01.01.01 Basic Flow 5
	 */
	
	//test options menu 
		ActivityMonitor am = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);

     /*	
      * Test for US01.01.01 Basic Flow 6
      */
		 
		// Click the menu option
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
		getInstrumentation().invokeMenuActionSync(nextActivity,ca.ualberta.CMPUT301W15T06.R.id.add_new_claim, 0);

		Activity a = getInstrumentation().waitForMonitorWithTimeout(am, 1000);
		/*assertEquals(true, getInstrumentation().checkMonitorHit(am, 1));
		a.finish();*/
	}
}

	
