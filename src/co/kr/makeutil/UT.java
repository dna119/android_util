package co.kr.makeutil;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;

public class UT {
	
	/** 이미지 스트링을 받아서 base64비트맵 값으로 리턴
	 * @param String_img
	 * @return Bitmap
	 */
	public static Bitmap decodeImage(String String_img){
		byte[] decodedString = Base64.decode(String_img, Base64.DEFAULT);
		Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
		return decodedByte;
	}
	/**비트맵을 받아서 base64 문자열값으로 리턴
	 * @param bitmap
	 * @return encodeString
	 */
	public static String encodeImage(Bitmap bitmap){
		  ByteArrayOutputStream stream = new ByteArrayOutputStream(); 
		  bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); 
		  byte[] imageInByte = stream.toByteArray();
		  String encodeString=android.util.Base64.encodeToString(imageInByte, android.util.Base64.DEFAULT);
		  return encodeString;
	}
	/** 큰 이미지와 함께하는 노티피케이션
	 * @param ThisContext thisContext
	 * @param MovingClass 이동할 클래스
	 * @param R_Icon 내부 아이콘 리소스 R시작
	 * @param Ticker 알림올때 상단에 잠시 보이는 부분
	 * @param Title 알림올때 상단에 잠시 보이는 부분 타이틀
	 * @param contentText 알림올때 상단에 잠시 보이는 부분 내용
	 * @param DetailTitle 알람을 내렸을때 타이틀
	 * @param DetailcontentText 알림을 내렸을때 내용
	 * @param MoreText 알림을 내렸을때 상세 정보
	 */
	public static void Notification_BigNotification(Context ThisContext,Class<?>MovingClass,int R_Icon,String Ticker,String Title,String contentText,String DetailTitle,String DetailcontentText,String MoreText){
    	NotificationManager nm = (NotificationManager)ThisContext.getSystemService(Context.NOTIFICATION_SERVICE);
    	PendingIntent pendingIntent = PendingIntent.getActivity(ThisContext, 0, new Intent(ThisContext, MovingClass), PendingIntent.FLAG_UPDATE_CURRENT);
    	 
    	Notification.Builder mBuilder = new Notification.Builder(ThisContext);
    	mBuilder.setSmallIcon(R_Icon);
    	mBuilder.setTicker(Ticker);
    	mBuilder.setWhen(System.currentTimeMillis());
    	mBuilder.setNumber(10);
    	//메세지 갯수처럼 표시하는 부분
    	mBuilder.setContentTitle(Title);
    	mBuilder.setContentText(contentText);
    	mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
    	mBuilder.setContentIntent(pendingIntent);
    	mBuilder.setAutoCancel(true);
    	mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
    	//상단의 잠깐 나올때 설명해주는 부분
    	
    	
    	Notification.BigTextStyle style = new Notification.BigTextStyle(mBuilder);
    	style.setSummaryText(MoreText);
    	style.setBigContentTitle(DetailTitle);
    	style.bigText(DetailcontentText);
    	mBuilder.setStyle(style);
    	//상단의 noti를 열었을때 설정해주는 부분
    	
    	 
    	nm.notify(111, mBuilder.build());
		
		
	}
	/** 기본 노티피케이션
	 * @param ThisContext -context
	 * @param MovingClass - 이동할 클래스
	 * @param R_Icon - 
	 * @param Ticker
	 * @param Title
	 * @param contentText
	 */
	public static void Notification_BasicNotification(Context ThisContext,Class<?>MovingClass,int R_Icon,String Ticker,String Title,String contentText){
	 	Uri notifica = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    	Ringtone r = RingtoneManager.getRingtone(ThisContext, notifica);
    	r.play();
    	NotificationManager nm = (NotificationManager)ThisContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R_Icon, Ticker, System.currentTimeMillis());
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        PendingIntent pendingIntent = PendingIntent.getActivity(ThisContext, 0, new Intent(ThisContext,MovingClass), PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(ThisContext, Title, contentText, pendingIntent);
        nm.notify(1234, notification);
	        
	}	
	/**-토스트를 띄워준다.
	 * @param text - 할말
	 * @param context -text
	 */
	public static void Toast(Context context,String text){
		android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_SHORT).show();
	}
	
	/**레이아웃 인플레이터
	 * @param ThisContext -This context
	 * @param Resource -xml에서 View로 리턴값을 가질 부분
	 * @return
	 */
	public static View LayoutInflater(Context ThisContext,int Resource){
		View vi=((android.view.LayoutInflater)ThisContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(Resource, null);
		return vi;
		
	}
	
	/** 기본 알람  - 시간 지정
	 * @param ThisContext -Context
	 * @param MoveToActivityClass - 알람이 울리면 이동할 클래스
	 * @param Reg_Num - 알람 번호 - 이걸로 알람을 삭제할수있다.
	 * @param Hour_of_day - 24시간중에 시간
	 * @param minute - 분
	 * @param Repeat - 반복 여부
	 */
	@SuppressLint("NewApi")
	public static void Alarm_MoveToActivity_Register(Context ThisContext,Class<?> MoveToActivityClass,int Reg_Num,int Hour_of_day,int minute,boolean Repeat){
		long triggerTime=0;
		Intent intent = new Intent(ThisContext, MoveToActivityClass);
		PendingIntent sender = PendingIntent.getActivity(ThisContext, Reg_Num, intent, PendingIntent.FLAG_UPDATE_CURRENT);
     	long atime = System.currentTimeMillis();
	    // timepicker
	    Calendar curTime = Calendar.getInstance();
	    curTime.set(Calendar.HOUR_OF_DAY, Hour_of_day);
	    curTime.set(Calendar.MINUTE, minute);
	    curTime.set(Calendar.SECOND, 0);
	    curTime.set(Calendar.MILLISECOND, 0);
	    long btime = curTime.getTimeInMillis();
	    triggerTime = btime;
	    if (atime > btime)
	        triggerTime += 1000 * 60 * 60 * 24;
	     
	     // 주석을 풀면 해당 날 시간부터 확인 된다.  주석이 있으면 그전날
		AlarmManager alarm=(AlarmManager) ThisContext.getSystemService(Context.ALARM_SERVICE);
	    try
	    {    	if(Repeat){
	    		alarm.setRepeating(AlarmManager.RTC_WAKEUP, triggerTime, 24*60 * 60 * 1000, sender);
	    		//반복 실행
	    		}
	    		else{
	    		alarm.setExact(AlarmManager.RTC_WAKEUP, triggerTime, sender);
	    		//한번만 실행
	    		}
	    } catch (Exception e)
	    {       e.printStackTrace();
	    }
	}
	
	/** 저장한 알람 해제하기 RegNum을 잘 사용해야한다.
	 * @param ThisContext -Thiscontext
	 * @param MoveToActivityClass -이동할 클래스
	 * @param Reg_Num -해제할 번호
	 */
	public static void Alarm_MoveToActivity_Realease(Context ThisContext,Class<?> MoveToActivityClass,int Reg_Num){
			AlarmManager alarmManager = (AlarmManager)ThisContext.getSystemService(Context.ALARM_SERVICE);
		    Intent intent = new Intent(ThisContext, MoveToActivityClass);
		    PendingIntent sender = PendingIntent.getActivity(ThisContext,Reg_Num, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		    alarmManager.cancel(sender);
		    sender.cancel();
		
	}
	
	/**  기본음 사운드 한번 들려주기
	 * @param ThisContext-사운드 내기
	 */
	public static void Sound_BasicRingStone(Context ThisContext){
		Uri notifica = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    	Ringtone r = RingtoneManager.getRingtone(ThisContext, notifica);
    	r.play();
	}
	
	/**  현재 실행중인 앱 패키지명 가져오기
	 * @param ThisContext -ThisContext
	 * @return
	 */
	public static ArrayList<String> App_RunningList(Context ThisContext){
		ActivityManager am = (ActivityManager)ThisContext.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> packageName=am.getRunningAppProcesses();
		ArrayList<String> arr=new ArrayList<String>();
		for (int i = 0; i < packageName.size(); i++) {
			arr.add(packageName.get(i).processName);
		}
		return arr;
		
		
	}
	
	/** 문자열인지 판다.
	 * @param str 판단할 객체
	 * @return
	 */
	public static boolean IsString(String str){
			boolean result=java.util.regex.Pattern.matches("^[0-9]*$", str);
			return result;
	}
	
	
	
	/**  arraylist String 값을 배열로 정렬 응용하면 다른 것도 정렬 가능하다.
	 * @param arr 배열 String
	 * @return
	 */
	public static ArrayList<String> ArrayList_sort(ArrayList<String> arr){
		ArrayList<String> arr1=arr;
		Collections.sort(arr1,new Comparator<String>() {

			@Override
			public int compare(String lhs, String rhs) {
				// TODO Auto-generated method stub
				return lhs.compareTo(rhs);
				//왼쪽 꺼랑 오른쪽꺼랑 비교해서 - > 오름차순 정렬
				//오른쪽 꺼랑 왼쪽 꺼랑  - >내림차순 정렬
			}
		});
		return arr1;
		
	}
	
	/**
	 * @param Thiscontext context
	 * @param url 확인할 주소
	 * 웹주소에 대한 페이지 유무를 판단할수있다.
	 * @return 
	 */
	public static boolean IsUrl(Context Thiscontext,String url){
		 /**
		 * @author dev-rna1
		 * 주소 확인을 하기 위한 클래스 생성
		 *
		 */
		class Data_Sync extends AsyncTask<String, Boolean, Boolean>{
			@Override
			protected Boolean doInBackground(String... params) {
				// TODO Auto-generated method stub
				 URLConnection con;
				 String isurl = null;
					try {
						con = new URL(params[0]).openConnection();
						 HttpURLConnection exitCode = (HttpURLConnection)con;
						 Log.e("",exitCode.getResponseCode()+"");
						 isurl=exitCode.getResponseCode()+"";
						 if(isurl.equals("200")){
								return true;
							}
							else{
								return false;
							}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
			}
			}
		 
		 try {
			return new Data_Sync().execute(url).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("페이지구별오류", e.toString()+"");
			return false;
		} 
	}
	
	/**
	 * @param ThisContext
	 * @param url 웹에서 받을 주소 이미지
	 * @return
	 */
	public static Bitmap Image_web(Context ThisContext,String url){
		  class WebGetImage extends AsyncTask<String, Void, Bitmap> {
			    @Override
			    protected Bitmap doInBackground(String... params) {
			      // 네트워크에 접속해서 데이터를 가져옴
			      try {
			        //웹사이트에 접속 (사진이 있는 주소로 접근)
			        URL Url = new URL(params[0]);
			        // 웹사이트에 접속 설정
			        URLConnection urlcon = Url.openConnection();
			        // 연결하시오
			        urlcon.connect();
			        // 이미지 길이 불러옴
			        int imagelength = urlcon.getContentLength();
			        // 스트림 클래스를 이용하여 이미지를 불러옴
			        BufferedInputStream bis = new BufferedInputStream(urlcon.getInputStream(), imagelength);
			        // 스트림을 통하여 저장된 이미지를 이미지 객체에 넣어줌
			        Bitmap bit = BitmapFactory.decodeStream(bis);
			        return bit;
			      } catch (Exception e) {
			        e.printStackTrace();
			        return null;
			      }
			    }
		  }
		  try {
			return new WebGetImage().execute(url).get();
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return null;
		  } 
	}
	
	public static Bitmap Image_Rotation(Context ThisContext,Bitmap bitmap,int RotationAngle){
		
		Matrix mat=new Matrix();
		mat.postRotate(-90);
		bitmap=Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mat, true);
		return bitmap;
		
	}
	
	
	
	
	
	
	

}
