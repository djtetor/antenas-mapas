package uach.djtetor.antenasmapas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.Menu;
import com.google.android.maps.*;

public class MapaActivity extends MapActivity {
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
	    MarcadoresAntenas itemizedoverlay = new MarcadoresAntenas(drawable, this);
	   
	    ArrayList<GeoPointAntena> antenas = new ArrayList<GeoPointAntena>();
	    try {
			antenas = new DatosAntenas(getApplicationContext()).antenas();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   	   
		for(GeoPointAntena x : antenas){
			OverlayItem overlayitem = new OverlayItem(x.punto, x.titulo, x.texto);
			itemizedoverlay.addOverlay(overlayitem);
		}
	    mapOverlays.add(itemizedoverlay);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_map, menu);
		return true;
	}

}
