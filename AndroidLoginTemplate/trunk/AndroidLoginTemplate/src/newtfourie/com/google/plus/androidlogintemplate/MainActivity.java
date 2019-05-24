package newtfourie.com.google.plus.androidlogintemplate;

import newtfourie.com.google.plus.androidlogintemplate.login.ManageLogin;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Handle menu selection
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId())
		{
		case R.id.action_logout:
			ManageLogin.getInstance(getApplicationContext()).logoutUser();
			ManageLogin.getInstance(getApplicationContext()).dispose();
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);		
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		//Clean up memory lying around
		ManageLogin.getInstance(getApplicationContext()).dispose();
	}
	
	

}
