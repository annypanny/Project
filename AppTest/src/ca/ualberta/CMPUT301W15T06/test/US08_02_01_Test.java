package ca.ualberta.CMPUT301W15T06.test;

import android.app.Activity;
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
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.CMPUT301W15T06.AppSingleton;
import ca.ualberta.CMPUT301W15T06.ApproverClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.User;

public class US08_02_01_Test extends ActivityInstrumentationTestCase2<MainActivity>  {

	public US08_02_01_Test() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}

	Button ApproverButton;
	Button ClaimantButton;
	Button UserButton;
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
	EditText claimant_name;
	EditText claimant_starting_date;
	EditText claimant_ending_date;
	Button FinishButton;

	User u;

	
	//set up
	protected void setUp() throws Exception {
		super.setUp();
		AppSingleton.getInstance().setTest(true);
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(false);
		ApproverButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		UserButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
		intent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
		u = AppSingleton.getInstance().getCurrentUser();

	}

	public void test080201() {
		
		/*
		 * Test for US 08.02.01 Basic Flow 1
		 */
		//test "Approver" button layout
		final View decorView = activity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView, ApproverButton);
		
		final ViewGroup.LayoutParams layoutParams =
				ApproverButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);

		/*
		 * Test for US 08.02.01 Basic Flow 2
		 */
		//click "Approver" button and create next activity
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ApproverClaimListActivity.class.getName(), null, false);
		MainActivity myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
			}
		});
		
		// next activity is opened and captured.
		ApproverClaimListActivity nextActivity = (ApproverClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		assertNotNull(nextActivity);
		
		/*
		 * Test for US 08.02.01 Basic Flow 3
		 */
		ListView al =  (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		final View decorView1 = nextActivity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView1, al);
	}
}