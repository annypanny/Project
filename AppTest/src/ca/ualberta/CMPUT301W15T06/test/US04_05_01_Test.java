package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.AppSingleton;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantEditDestinationActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantEditItemActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemDetailActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;

@SuppressLint("CutPasteId")
public class US04_05_01_Test extends
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
	User u;


	public US04_05_01_Test() {
		super(MainActivity.class);
	}

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
	
	public void test040501() {

		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				// click user button
				assertTrue(UserButton.performClick());	

			}	
		});


		// click "Claimant" button and create next activity
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
		
		// get current activity			
		MainActivity myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
			}
		});

		// start next activity
		final ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		// next activity is opened and captured.
		assertNotNull(nextActivity);

		// view which is expected to be present on the screen			
		final View decorView1 = nextActivity.getWindow().getDecorView();
		// layout of claim list
		listView = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView1, listView);
		
		// check whether the Button object's width and height attributes match the expected values
		final ViewGroup.LayoutParams layoutParams11 = listView.getLayoutParams();
		assertEquals(layoutParams11.width, WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams11.height, WindowManager.LayoutParams.WRAP_CONTENT);	 
		
		final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		
		
		
		/*
		 * Test for US 04.04.01 Basic Flow 2
		 */
		//get next activity

		
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				// click the list open next activity.
				ActivityMonitor am = getInstrumentation().addMonitor(ClaimantItemListActivity.class.getName(), null, false);
				claimList.getChildAt(0).performClick();
				ClaimantItemListActivity thirdActivity = (ClaimantItemListActivity) getInstrumentation().waitForMonitorWithTimeout(am, 10000);
				assertNotNull(thirdActivity);
				
				Claim claim = new Claim();
				int count_before = claim.getItemList().size();
				
				/*
				 * Test for US 04.05.01 Basic Flow 1
				 */
				final View decorView2 = nextActivity.getWindow().getDecorView();
				ListView ilv = (ListView) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView2, ilv);
				
				
				/*
				 * Test for US 04.05.01 Basic Flow 2
				 */
				// get item list layout
				final ListView itemlist = (ListView) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				thirdActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// click list view
						itemlist.getChildAt(0).performClick();		
					}
				});
				
				
				/*
				 * Test for US 04.05.01 Basic Flow 3
				 */
				//check click
				AlertDialog d = (AlertDialog) thirdActivity.getDialog();
				assertNotNull(d);
				// check option array layout
				ListView choose = (ListView) ilv.findViewById(ca.ualberta.CMPUT301W15T06.R.array.item_dialog_array);
				assertNotNull(choose);
				
				/*
				 * Test for US 04.05.01 Basic Flow 4
				 */
				choose.getChildAt(2).performClick();
				// start claimant item detail activity
				ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantItemDetailActivity.class.getName(), null, false);
				ClaimantItemDetailActivity ccc = (ClaimantItemDetailActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
				// check if the activity is started
				assertNotNull(ccc);
				
				/*
				 * Test for US 04.05.01 Basic Flow 5
				 */
				final View ddvv = ccc.getWindow().getDecorView();
				TextView Date = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemDateTextView);
				ViewAsserts.assertOnScreen(ddvv, Date);
				assertNotNull(Date.getVisibility());
				
				TextView dv = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemDateVTextView);
				ViewAsserts.assertOnScreen(ddvv, dv);
				assertNotNull(dv.getVisibility());
				
				TextView c = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemCategoryTextView);
				ViewAsserts.assertOnScreen(ddvv, c);
				assertNotNull(c.getVisibility());
				
				TextView cv = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemCategoryVTextView);
				ViewAsserts.assertOnScreen(ddvv, cv);
				assertNotNull(cv.getVisibility());
				
				TextView de = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemDescriptionTextView);
				ViewAsserts.assertOnScreen(ddvv, de);
				assertNotNull(de.getVisibility());
				
				TextView dev = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemDescriptionVTextView);
				ViewAsserts.assertOnScreen(ddvv, dev);
				assertNotNull(dev.getVisibility());
				
				TextView a = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemAmountTextView);
				ViewAsserts.assertOnScreen(ddvv, a);
				assertNotNull(a.getVisibility());
				
				TextView av = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemAmountVTextView);
				ViewAsserts.assertOnScreen(ddvv, av);
				assertNotNull(av.getVisibility());
				
				TextView u = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemCurrencyTextView);
				ViewAsserts.assertOnScreen(ddvv, u);
				assertNotNull(u.getVisibility());
				
				TextView uv = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemCurrencyVTextView);
				ViewAsserts.assertOnScreen(ddvv, uv);
				assertNotNull(uv.getVisibility());
				
				ccc.finish();
				
				thirdActivity.finish();
			}
		});
		
		nextActivity.finish();
		activity.finish();
	}
	
}

				