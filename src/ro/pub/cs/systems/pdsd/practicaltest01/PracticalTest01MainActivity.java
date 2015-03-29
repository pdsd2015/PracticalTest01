package ro.pub.cs.systems.pdsd.practicaltest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01MainActivity extends Activity {
	
	private final static int LINEAR_LAYOUT_DISPLAY   = R.layout.activity_practical_test01_main_ll;
	private final static int RELATIVE_LAYOUT_DISPLAY = R.layout.activity_practical_test01_main_rl;
	
	private final static int PREFERRED_LAYOUT        = RELATIVE_LAYOUT_DISPLAY;
	
	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
	private final static String TAG = "PracticalTest01MainActivity";
	
	private ButtonClickListener buttonClickListener = new ButtonClickListener();
	
	private class ButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			EditText leftEditText = (EditText)PracticalTest01MainActivity.this.findViewById(R.id.left_edit_text);
			EditText rightEditText = (EditText)PracticalTest01MainActivity.this.findViewById(R.id.right_edit_text);
			int leftButtonClickedNumber = Integer.parseInt(leftEditText.getText().toString());
			int rightButtonClickedNumber = Integer.parseInt(rightEditText.getText().toString());
			switch(view.getId()) {
				case R.id.navigate_to_secondary_activity_button:
					Intent intent = new Intent("ro.pub.cs.systems.pdsd.intent.action.PracticalTest01SecondaryActivity");
					intent.putExtra("number_of_clicks", 
							String.valueOf(
									Integer.parseInt(leftEditText.getText().toString())
									+ Integer.parseInt(rightEditText.getText().toString())
							));
					startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
					break;
				case R.id.left_button:
					leftButtonClickedNumber++;
					leftEditText.setText(String.valueOf(leftButtonClickedNumber));
					break;
				case R.id.right_button:
					rightButtonClickedNumber++;
					rightEditText.setText(String.valueOf(rightButtonClickedNumber));
					break;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate() was invoked");
		super.onCreate(savedInstanceState);
		setContentView(PREFERRED_LAYOUT);
		
		EditText leftEditText = (EditText)findViewById(R.id.left_edit_text);
		EditText rightEditText = (EditText)findViewById(R.id.right_edit_text);
		
		if (savedInstanceState != null) {
			String leftCount = savedInstanceState.getString("leftCount");
			if (leftCount != null) {
				leftEditText.setText(leftCount);
			} else {
				leftEditText.setText(String.valueOf(0));
			}
			String rightCount = savedInstanceState.getString("rightCount");
			if (rightCount != null) {
				rightEditText.setText(rightCount);
			} else {
				rightEditText.setText(String.valueOf(0));
			}
		} else {
			leftEditText.setText(String.valueOf(0));
			rightEditText.setText(String.valueOf(0));
		}
		
		Button navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
		navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);
		Button leftButton = (Button)findViewById(R.id.left_button);
		leftButton.setOnClickListener(buttonClickListener);
		Button rightButton = (Button)findViewById(R.id.right_button);
		rightButton.setOnClickListener(buttonClickListener);		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart() was invoked");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume() was invoked");
	}	
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		Log.d(TAG, "onSaveInstanceState() was invoked");
		EditText leftEditText = (EditText)findViewById(R.id.left_edit_text);
		EditText rightEditText = (EditText)findViewById(R.id.right_edit_text);
		savedInstanceState.putString("leftCount", leftEditText.getText().toString());
		savedInstanceState.putString("rightCount", rightEditText.getText().toString());
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause() was invoked");
	}	
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop() was invoked");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy() was invoked");
	}	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Toast.makeText(this, "The activity returned with result "+resultCode, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
