package newtfourie.com.google.plus.androidlogintemplate.common;

import newtfourie.com.google.plus.androidlogintemplate.MainActivity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * This class is used for the common tasks found in android such as switching between activities and showing toasts.
 * It is merely an assister class
 * @author Newt
 *
 */
public class CommonAndroidTasks {

	/**
	 * This class handles simple activity switching. It will launch the appropriate activity with no extras passed in the intent
	 * @author Newt
	 *
	 */
	public static class SimpleActivitySwitch
	{
		/**
		 * Go to the main activity
		 * @param currentActivity - the activity you are calling this from
		 */
		public static void mainScreen(Context currentActivity)
		{
			Intent intent = new Intent(currentActivity, MainActivity.class);
			currentActivity.startActivity(intent);
		}
		
	}
	
	/**
	 * This will display the message as a long toast
	 * @param currentActivity - the activity you are calling this from
	 * @param message - the message to display
	 */
	public static void toastLong(Context currentActivity, String message)
	{
		Toast.makeText(currentActivity, message, Toast.LENGTH_LONG).show();
	}
	
	/**
	 * This will display the message as a short toast
	 * @param currentActivity - the activity you are calling this from
	 * @param message - the message to display
	 */
	public static void toastShort(Context currentActivity, String message)
	{
		Toast.makeText(currentActivity, message, Toast.LENGTH_SHORT).show();
	}
	
}
