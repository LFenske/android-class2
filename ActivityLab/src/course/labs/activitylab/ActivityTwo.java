package course.labs.activitylab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTwo extends Activity {

	private static final String RESTART_KEY = "restart";
	private static final String  RESUME_KEY = "resume" ;
	private static final String   START_KEY = "start"  ;
	private static final String  CREATE_KEY = "create" ;

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityTwo";

	// Lifecycle counters

	// Create counter variables for onCreate(), onRestart(), onStart() and
	// onResume(), called mCreate, etc.
	// You will need to increment these variables' values when their
	// corresponding lifecycle methods get called
	private int mCreate  = 0;
	private int mRestart = 0;
	private int mStart   = 0;
	private int mResume  = 0;
	private boolean no_restore = false;

	private TextView mTvCreate ;
	private TextView mTvRestart;
	private TextView mTvStart  ;
	private TextView mTvResume ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);

		// Assign the appropriate TextViews to the TextView variables
		// Hint: Access the TextView by calling Activity's findViewById()
		// textView1 = (TextView) findViewById(R.id.textView1);
		mTvCreate  = (TextView) findViewById(R.id.create );
		mTvRestart = (TextView) findViewById(R.id.restart);
		mTvStart   = (TextView) findViewById(R.id.start  );
		mTvResume  = (TextView) findViewById(R.id.resume );

		Button closeButton = (Button) findViewById(R.id.bClose); 
		closeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// This function closes Activity Two
				finish();
			}
		});

		// Check for previously saved state
		onRestoreInstanceState(savedInstanceState);

		// Update the appropriate count variable
		// Update the user interface via the displayCounts() method
		mCreate++;
		onSaveInstanceState(savedInstanceState);
		displayCounts();

		// Emit LogCat message
		Log.i(TAG, "Entered the onCreate() method");
	}

	// Lifecycle callback overrides

	@Override
	public void onStart() {
		super.onStart();

		// Update the appropriate count variable
		// Update the user interface
		mStart++;
		//onSaveInstanceState(savedInstanceState);
		displayCounts();

		// Emit LogCat message
		Log.i(TAG, "Entered the onStart() method");
		
		no_restore = true;
	}

	@Override
	public void onResume() {
		super.onResume();

		// Update the appropriate count variable
		// Update the user interface
		mResume++;
		//onSaveInstanceState(savedInstanceState);
		displayCounts();

		// Emit LogCat message
		Log.i(TAG, "Entered the onResume() method");
	}

	@Override
	public void onPause() {
		super.onPause();

		// Emit LogCat message
		Log.i(TAG, "Entered the onPause() method");
	}

	@Override
	public void onStop() {
		super.onStop();

		// Emit LogCat message
		Log.i(TAG, "Entered the onStop() method");
	}

	@Override
	public void onRestart() {
		super.onRestart();

		// Update the appropriate count variable
		// Update the user interface
		mRestart++;
		//onSaveInstanceState(savedInstanceState);
		displayCounts();

		// Emit LogCat message
		Log.i(TAG, "Entered the onRestart() method");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		// Emit LogCat message
		Log.i(TAG, "Entered the onDestroy() method");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save state information with a collection of key-value pairs
		if (savedInstanceState != null) {
			savedInstanceState.putInt(CREATE_KEY,  mCreate );
			savedInstanceState.putInt(RESTART_KEY, mRestart);
			savedInstanceState.putInt(START_KEY,   mStart  );
			savedInstanceState.putInt(RESUME_KEY,  mResume );
			//Log.i(TAG, "onSaveInstanceState");
		}
		no_restore = false;
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Check for previously saved state
		if (savedInstanceState != null && ! no_restore) {
			// Restore value of counters from saved state
			mCreate  = savedInstanceState.getInt(CREATE_KEY );
			mRestart = savedInstanceState.getInt(RESTART_KEY);
			mStart   = savedInstanceState.getInt(START_KEY  );
			mResume  = savedInstanceState.getInt(RESUME_KEY );
			displayCounts();
			//Log.i(TAG, "onRestoreInstanceState");
		}
	}

	// Updates the displayed counters
	public void displayCounts() {
		mTvCreate .setText("onCreate() calls: "  + mCreate );
		mTvStart  .setText("onStart() calls: "   + mStart  );
		mTvResume .setText("onResume() calls: "  + mResume );
		mTvRestart.setText("onRestart() calls: " + mRestart);
	}
}
