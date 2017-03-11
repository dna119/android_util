package co.kr.makeutil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class UT_INTENT {

	
	public static void Intent_Map(Context ThisContext,String lat,String lng){
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:"+lat+","+lng));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ThisContext.startActivity(intent);
	}
	public static void Intent_WebView(Context ThisContext,String url){
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ThisContext.startActivity(intent);
	}
	public static void Intent_Music(Context ThisContext,String FilePath_Name){
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(FilePath_Name));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ThisContext.startActivity(intent);
	}
	public static void Intent_Dial(Context ThisContext,String url){
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ThisContext.startActivity(intent);
	}
	
	public static void Intent_Emaile(Context ThisContext,String email_address){
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(email_address));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ThisContext.startActivity(intent);
	}
	
	
	
	
	
	
	
	
	




}
