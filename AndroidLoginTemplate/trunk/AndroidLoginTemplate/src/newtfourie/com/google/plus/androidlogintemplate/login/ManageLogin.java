package newtfourie.com.google.plus.androidlogintemplate.login;

import newtfourie.com.google.plus.androidlogintemplate.MainActivity;
import newtfourie.com.google.plus.androidlogintemplate.common.IDisposable;
import newtfourie.com.google.plus.androidlogintemplate.common.SettingNames;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Singleton class to manage login with shared preferences
 * @author Newt
 *
 */
public class ManageLogin implements IDisposable {

	/**********************************************************/
	/*
	 * Singleton
	 */
	/*********************************************************/
	
	/**
	 * This constructor has been made protected to allow it to be extended and still maintain the singleton design pattern
	 */
	protected ManageLogin()
	{
		
	}
	
	private static ManageLogin thisObj = null;
	
	public static ManageLogin getInstance(Context context)
	{
		if (thisObj == null)
		{
			thisObj = new ManageLogin();
		}
		
		currentContext = context;
		
		return thisObj;
	}
	/**********************************************************/
	/*********************************************************/
	
	/**********************************************************/
	/*
	 * Attributes
	 */
	/*********************************************************/
	
	private static Context currentContext;
	
	/**********************************************************/
	/*********************************************************/
	
	/**********************************************************/
	/*
	 * Methods
	 */
	/*********************************************************/
	
	/**
	 * Will check if there is a user logged in on the phone
	 * @param context - insert getApplicationContext() here whenever possible
	 * @return true if there is a user logged in
	 */
	public boolean isLoggedIn()
	{
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(currentContext);
		return settings.getBoolean(SettingNames.isLoggedIn, false);
	}
	
	/**
	 * To be called from the login activity only, this will check if the user must log in or not
	 * @param context - insert getApplicationContext() here whenever possible
	 */
	public boolean mustLogin()
	{
		if (isLoggedIn())
		{
			//Go to home screen
			Intent intent = new Intent(currentContext, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			currentContext.startActivity(intent);
			return false;
		}
		//else the login screen will show
		return true;
	}
	
	/**
	 * This will save the user's login credentials to the phone
	 * 
	 * Why is the password saved?
	 * The password and username do not necessary need to be saved, but if you are working
	 * with sensitive data, then it would be better to save the password to the phone (hashed)
	 * and when sensitive transaction are performed you can simply query that value.
	 * If required you must force log out if the user changes password outside the app
	 * 
	 * @param context - insert getApplicationContext() here whenever possible
	 * @param params - a string array of the username and password (in that order), the password will be hashed here
	 */
	public void saveLoggedInUser(String... params)
	{
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(currentContext);
		Editor settingsEditor = settings.edit();
		
		settingsEditor.putString(SettingNames.username, params[0]);
		settingsEditor.putString(SettingNames.password, hashPassword(params[1]));
		settingsEditor.putBoolean(SettingNames.isLoggedIn, true);
		
		settingsEditor.commit();
	}
	
	/**
	 * Hash password so that it cannot be read back in plain text
	 * @param password - the password you want to hash
	 * @return the hashed password
	 */
	public String hashPassword(String password)
	{
		//TODO hash password
		return password;
	}
	
	/**
	 * This will clear the user's login preferences
	 * @param context - insert getApplicationContext() here whenever possible
	 */
	public void logoutUser()
	{
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(currentContext);
		Editor settingsEditor = settings.edit();
		
		settingsEditor.putString(SettingNames.username, "");
		settingsEditor.putString(SettingNames.password, "");
		settingsEditor.putBoolean(SettingNames.isLoggedIn, false);
		
		settingsEditor.commit();
	}
	
	/**
	 * This method will clean up this object
	 */
	public void dispose()
	{
		thisObj = null;
	}
	
	/**********************************************************/
	/*********************************************************/
}
