/**
 *  @title 
 *  @packagename co.kr.makeutil 
 *  @fielname UT_GPS.java
 *  @auto dev-rna1 
 *  @since 2017. 3. 21.
 */
package co.kr.makeutil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import utilclass.GpsInfo;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

/**
 * @author dev-rna1
 *
 */
public class UT_GPS {

	public static StringBuffer getAddress(Context context){
		
		  StringBuffer mAddress = new StringBuffer();
		  Geocoder geoCoder=new Geocoder(context, Locale.KOREA);
		  GpsInfo gps=new GpsInfo(context);
		   try {
		    // 위도,경도를 이용하여 현재 위치의 주소를 가져온다. 
		    List<Address> addresses;
		    addresses = geoCoder.getFromLocation(gps.getLatitude(), gps.getLongitude(), 1);
		    for(Address addr: addresses){
		     int index = addr.getMaxAddressLineIndex();
		     for(int i=0;i<=index;i++){
		      mAddress.append(addr.getAddressLine(i));
		      mAddress.append(" ");
		     }
		     mAddress.append("\n");
		    }
		   } catch (IOException e) {
		    e.printStackTrace();
		   }
		   
			return mAddress;
		  }
}
