package uach.djtetor.antenasmapas;

import com.google.android.maps.GeoPoint;

public class GeoPointAntena {
	public GeoPointAntena(String latitud, String longitud, String title, String text,Boolean con){
		double la = Double.parseDouble(latitud)*1000000;
		double lo = Double.parseDouble(longitud)*1000000;
		
		punto = new GeoPoint((int)la, (int)lo);
		titulo = title;
		texto = text;
		conectada = con;
	}
	public GeoPoint punto;
	public String titulo;
	public String texto;
	public Boolean conectada;
}
