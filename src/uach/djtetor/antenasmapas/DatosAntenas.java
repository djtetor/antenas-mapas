package uach.djtetor.antenasmapas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

import android.content.Context;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

public class DatosAntenas {

	private TelephonyManager tm;
	private Context mContext;
	private static String url = "http://www.open-electronics.org/celltrack/celltxt.php";
	 
	public DatosAntenas(Context c){
		this.mContext = c;
	}
	public ArrayList<GeoPointAntena> antenas() throws IOException{
		ArrayList<GeoPointAntena> resultado = new ArrayList<GeoPointAntena>();
		URL url = new URL(construirUrl());
        URLConnection con = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(
			                            con.getInputStream()));
        String inputLine;
        inputLine = br.readLine(); 
       	String datos = inputLine.substring(0,inputLine.indexOf("<br>"));
    	String[] antenas = datos.split("&");
    	int contador = 1;
    	boolean conectada = true;
    	for(String x : antenas){
    		String[] puntos = x.split(",");
    		if(conectada == true)
    			resultado.add(new GeoPointAntena(puntos[0],puntos[1],"Celula: Conectada","Alcance: "+puntos[2]+"m",conectada));
			else
    			resultado.add(new GeoPointAntena(puntos[0],puntos[1],"Celula: "+contador,"Alcance: "+puntos[2]+"m",conectada));
    		conectada = false;
    		contador++;
    	}
        
        br.close();
		return resultado;
	}
    /*parameters 
     * 
     * ?hex=0&mcc=222&mnc=10&lac=00010012&cid=00039309 
     * &lac0=00010012&cid0=00005188 
     * &lac1=00010012&cid1=00017252 
     * &lac2=00010012&cid2=00039303 
     * &lac3=00010012&cid3=00039301 
     * &lac4=00010012&cid4=00039307
     * 
     * */       
    public String construirUrl(){
    	//defaults values
    	String mcc = "334";
    	String mnc = "020";
    	String lac = "";
    	StringBuilder parametros = new StringBuilder("?hex=0");
    	tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
    	try{
    		mcc = tm.getNetworkOperator().substring(0,3);
    		mnc = tm.getNetworkOperator().substring(3,6);
    	}catch(StringIndexOutOfBoundsException e){
    		//TODO: nothing to do
    	}
    	parametros.append("&mcc="+mcc);
    	parametros.append("&mnc="+mnc);
    	  	    	
    	GsmCellLocation cl = (GsmCellLocation)tm.getCellLocation();
    	
    	parametros.append("&lac="+cl.getLac());
    	parametros.append("&cid="+cl.getCid());
    	lac = cl.getLac()+"";
    	
        List<NeighboringCellInfo> neighbours = tm.getNeighboringCellInfo();
        int contador = 0;
        for (NeighboringCellInfo neighboringCellInfo : neighbours) {
        	if(neighboringCellInfo.getLac() == 0 || neighboringCellInfo.getLac() == -1)
        		parametros.append("&lac"+contador+"="+lac);
        	else
        		parametros.append("&lac"+contador+"="+neighboringCellInfo.getLac());
        	
        	parametros.append("&cid"+contador+"="+neighboringCellInfo.getCid());
        	contador++;
        	if(contador > 4) break;
		}
        return url+parametros.toString();
    }
    
    
}
