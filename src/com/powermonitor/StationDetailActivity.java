package com.powermonitor;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class StationDetailActivity extends Activity {
	
	public static Station station = null;
	
	public static void SetStation(Station my_station)
	{
		station = my_station; 
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		TextView txtStationName = (TextView) findViewById(R.id.txtStationName);
		TextView txtStationLocation = (TextView) findViewById(R.id.txtLocation);
		
		txtStationName.setText(station.name);
		txtStationLocation.setText(station.location.name);
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
