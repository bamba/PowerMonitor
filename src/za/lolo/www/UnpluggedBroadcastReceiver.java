package za.lolo.www;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class UnpluggedBroadcastReceiver extends BroadcastReceiver {
  private static String TAG = UnpluggedBroadcastReceiver.class.getSimpleName();
	@Override
	public void onReceive(Context ctx, Intent intent) {
	  Bundle broadcatesBundle = (Bundle)intent.getExtras();
	  Log.i(TAG,"Unplugged-Broadcast Received \n STATE= "+broadcatesBundle.getShort("state"));
      Toast.makeText(ctx, "Unplugged-Broadcast Received \n STATE= "+broadcatesBundle.getShort("state"), Toast.LENGTH_LONG).show();
	}

}
