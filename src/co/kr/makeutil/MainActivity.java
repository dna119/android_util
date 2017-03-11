package co.kr.makeutil;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import co.kr.activitiy.BaseActivity;

public class MainActivity extends BaseActivity {
	ListView li1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		li1=(ListView) findViewById(R.id.li1);
//		ArrayList<USER_data> arr_str=new ArrayList<USER_data>();
//		ArrayList<String> arr_str=new ArrayList<String>();
//		arr_str.add(new USER_data("가나", "안가나"));
//		arr_str.add(new USER_data("가나1", "안가나1"));
//		arr_str.add(new USER_data("가나2", "안가나2"));
		
		
//		WarningPopup pop=new WarningPopup(this);
//		pop.getclass(MainActivity.class);
//		pop.setContent("위험", "메시지1");
//		pop.show();
//		UT.Notification_BigNotification(this, MainActivity.class, R.drawable.bg_login_bot, "하이", "타이틀1","텍스트1", "상세1", "상세내용"	, "더보기");
		//_log.e(UT_view.chkTouchInside(li1, 100, 100)+"");
		
		//UT_Phone.getPhoneMovieLISTS(this, MainActivity.this);
		
//		UT_view.AlertDialLog_Basic(this, "얼렛타이틀", "얼렛텍스트", "확인", "취소", new OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// TODO Auto-generated method stub
//			UT.Toast("지금", getApplicationContext());	
//			}
//		}).show();
		
//		View vi=UT.LayoutInflater(this, R.layout.basic_1text_center);
//		TextView tx1=(TextView) vi.findViewById(R.id.tx1);
//		tx1.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				UT.Toast(getApplicationContext(), "성공");
//			}
//		});
//		UT_view.AlertDialLog_Xml(vi,this).show();;
		
//		UT.Alarm_MoveToActivity_Register(getApplicationContext(), Intro.class, 1, 10, 58, false);
//		UT.Alarm_MoveToActivity_Realease(getApplicationContext(), Intro.class, 1);

//		UT.Notification_BasicNotification(getApplicationContext(), Intro.class, R.drawable.ic_launcher, "tickeer", "Title", "contentText");
//		UT.Notification_BasicNotification(this, Intro.class, R.drawable.ic_launcher, "tickeer", "Title", "contentText");
		
//		String str = "";
//		double time = Double.parseDouble("30000")/1000;
//		double hours = time / 3600;//시 공식
//		double minute = time % 3600 / 60;//분을 구하기위해서 입력되고 남은값에서 또 60을 나눈다.
//		double second = time % 3600 % 60;//마지막 남은 시간에서 분을 뺀 나머지 시간을 초로 계산함
//		Log.e("CHECK" , "hours -> " + hours);
//		Log.e("CHECK" , "minute -> " + minute);
//		Log.e("CHECK" , "second -> " + second);
//
//		if (hours >= 1) {
//			str = str  + Math.floor(hours)  + "시";
//		}
//		if (minute >= 1) {
//			str = str + Math.floor(minute) + "분";
//		}
//		if (second >= 0) {
//			str = str + Math.floor(second) + "초";
//		}
		
		
//		BitmapDrawable bitmap=(BitmapDrawable) getResources().getDrawable(R.drawable.btn_dlg_ok);
//		Bitmap bits=bitmap.getBitmap();
		
		
//		boolean a=UT_Phone.SaveBitmapToSdCard(bits, Environment.getExternalStorageDirectory().getAbsolutePath()+"/aaa.jpg");
//		Log.e("a", a+"");
		
//	    ((ImageView)findViewById(R.id.img1)).setImageResource(R.drawable.btn_dlg_ok);
//		UT_Phone.SaveBitmapToSdCard(bits, "test", "a.jpg");
	    
	    
//	        File fileCacheItem = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/cc.jpg");
//	        OutputStream out = null;
//	        try {
//	            fileCacheItem.createNewFile();
//	            out = new FileOutputStream(fileCacheItem);
//	 
//	            bits.compress(Bitmap.CompressFormat.JPEG, 100, out);
//	            out.close();
//	            
//	        } catch (Exception e) {
//	        	Log.e("err", "err");
//	            e.printStackTrace();
//	        } 
		
//		arr_str.add("가나다1");
//		arr_str.add("가나다3");
//		arr_str.add("가나다2");
//		ArrayAdapter<String> adp=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,arr_str);
//		li1.setAdapter(adp);
//		
//		UT.ArrayList_sort(arr_str);
//		 adp.notifyDataSetChanged();
		
//		UT_INTENT.Intent_Map(getApplicationContext(), "37.552445", "127.13054");
		
//		Log.e("return",UT.IsUrl(this,"http://dothomes.co.kr")+"");
		
		Bitmap bit=UT.Image_web(getApplicationContext(), "http://blog.jinbo.net/attach/615/200937431.jpg");
		bit=UT.Image_Rotation(getApplicationContext(), bit, 90);
		
		((ImageView)findViewById(R.id.img1)).setImageBitmap(bit);
		
		
		

		
	}

	

}
