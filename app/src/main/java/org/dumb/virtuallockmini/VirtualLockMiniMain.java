package org.dumb.virtuallockmini;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.widget.Toast;


public class VirtualLockMiniMain extends Activity {


    private static final int ADMIN_INTENT = 15;
    private static final String description = "Device Administrator permission to Virtual Lock allow its main functionality, Turning Off and Locking the screen to function";
    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mComponentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_lock_mini_main);

        mDevicePolicyManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        mComponentName = new ComponentName(this, VirtualLockAdminReceiver.class);

        boolean isAdmin = mDevicePolicyManager.isAdminActive(mComponentName);
        if (isAdmin) {

            mDevicePolicyManager.lockNow();
            finish();

        }else{

            Toast.makeText(getApplicationContext(), "Not Registered as admin : Trying to Register...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,description);
            startActivityForResult(intent, ADMIN_INTENT);

            finish();

        }

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_virtual_lock_mini_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
