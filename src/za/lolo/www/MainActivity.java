package za.lolo.www;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView mListView;
	ArrayList<Station> mystations = new ArrayList<Station>();
	ArrayList<Station> search_stations = new ArrayList<Station>();
	StationAdapter stationadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		try
		{
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
		LoadStations();
		
		mListView = (ListView) findViewById(android.R.id.list);
		stationadapter = new StationAdapter(this, R.layout.stationitem,
				search_stations);
		
		mListView.setAdapter(stationadapter);
		
		AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextViewReg);
		
		
		
		   mListView.setOnItemClickListener(new OnItemClickListener() {
				
			   public void onItemClick(AdapterView<?> parent,
	    		        View view, int pos, long id)  {
		//		   	DetailActivity.setStation(search_stations.get(pos)); 
	    //    		Intent intent = new Intent(MainActivity.this, DetailActivity.class);
				   Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
						   Uri.parse("geo:0,0?q="+search_stations.get(pos).location.lon+","+
								   	search_stations.get(pos).location.lat
								   ));
						   startActivity(intent);
				   //startActivityForResult(intent,0);
	        		startActivity(intent);
				}
			});
		
        textView.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				search_stations.clear();
				
				if(s.toString() != "")
					FilterStations(mystations, s.toString());
				
				stationadapter.notifyDataSetChanged();
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,	int after) 
			{
			}
			
			public void afterTextChanged(Editable s) 
			{	
			}
		});
		}
		catch(Exception ex)
		{
			Log.e("Main Activity error", "Error in main activity", ex);
			Toast.makeText(this, "error in mapactivity" + ex.getMessage(),
					Toast.LENGTH_LONG).show();

		}
	}
	public void FilterStations(ArrayList<Station> c,String s)
	{
		for(Iterator<Station> i = c.iterator(); i.hasNext();)
		{
			Station temp = i.next();
			String temp_filter = temp.name +temp.id +temp.status;
			if( temp_filter.toUpperCase().indexOf(s.toUpperCase()) >= 0)
			{
				search_stations.add(temp);
			}
		}
	}

	private void LoadStations() {

		try {
			URL url = new URL(
					"http://opm.rheosystems.cloudbees.net/fakeapi/location.json");
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			c.connect();

			if (c.getResponseCode() == HttpURLConnection.HTTP_OK) {

				InputStream is = c.getInputStream();

				DataInputStream in = new DataInputStream(is);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));

				StringBuffer buf = new StringBuffer();

				String line;
				while ((line = br.readLine()) != null) {
					buf.append(line);
				}

				JSONArray arr = new JSONArray(buf.toString());
				for (int i = 0; i < arr.length(); i++) {
					JSONObject obj = arr.getJSONObject(i);

					Station var = new Station();
					var.id = obj.getInt("id");
					var.name = obj.getString("description");
					var.location.lon = obj.getString("longitude");
					var.location.lat = obj.getString("latitude");
					var.status = obj.getString("status");

					mystations.add(var);
					search_stations.add(var);
				}
				stationadapter.notifyDataSetChanged();
			}

		} catch (Exception e) {
			Log.i("TAG", "what? " + e);
		}

	}
}
