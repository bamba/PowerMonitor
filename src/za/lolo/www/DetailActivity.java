package za.lolo.www;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailActivity extends Activity {
	static Station station = new Station();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mapview);
		
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        WebView wb = (WebView)findViewById(R.id.my_webview);
        wb.setWebViewClient(new WebViewClient() {
        	@Override
        	public boolean shouldOverrideUrlLoading(WebView view, String url) {
        	view.loadUrl(url);
        	return true;
        	}
        	});
        	wb.loadUrl("https://maps.google.co.za/?ll=-25.983759,28.121395&spn=0.009837,0.021136&t=m&z=16&iwloc=lyrftr:m,0x1e956e2308cb81fb:0xe9dba4c2a0b90332,-25.983663,28.121223");
        	}
        
        /*
		try {
			
			mMapView = (MapView) findViewById(R.id.mapView);
			mMapView.displayZoomControls(true);
			mMapView.setSatellite(false);
			mMapView.setBuiltInZoomControls(true);

			// Getting a handle to the map controller
			mMapController = mMapView.getController();
			mMapController.setZoom(17);
		} catch (Exception ex) {
			Log.e("MapError", "Error in map activity", ex);
			Toast.makeText(this, "error in mapactivity" + ex.getMessage(),
					Toast.LENGTH_LONG).show();

		}*/

	
	
	public static void setStation(Station name) {
		station = new Station();
		station = name;
	}
}
