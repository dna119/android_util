/**
 *  @title 
 *  @packagename co.kr.makeutil 
 *  @fielname UT_LISTENER.java
 *  @auto dev-rna1 
 *  @since 2017. 3. 10.
 */
package co.kr.makeutil;

import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

/**
 * @author dev-rna1
 *
 */
public class UT_LISTENER {
	public static Chronometer chr=null;
	
	
	
	/**
	 * 크로노 타이머 작동 리스너  : 리스너가 입력된 즉시 실행된다.
	 * @param cr
	 * @return
	 *  @auto dev-rna1 
	 *  @since 2017. 3. 10.
	 */
	public static OnChronometerTickListener OnChronometerTickListener(final Chronometer Chronometer){
		chr=Chronometer;
		OnChronometerTickListener lis=null;
		lis=new OnChronometerTickListener() {
			
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				// TODO Auto-generated method stub
					
				   long time = SystemClock.elapsedRealtime() - Chronometer.getBase();
		            int h   = (int)(time /3600000);
		            int m = (int)(time - h*3600000)/60000;
		            int s= (int)(time - h*3600000- m*60000)/1000 ;
		            String hh = h < 10 ? "0"+h: h+"";
		            String mm = m < 10 ? "0"+m: m+"";
		            String ss = s < 10 ? "0"+s: s+"";
		            Chronometer.setText(hh+":"+mm+":"+ss);
				
				
				
			}
		};
		  chr.setBase(SystemClock.elapsedRealtime());
		  chr.start();
		
		
		return lis;
		
		
		
	}

}
