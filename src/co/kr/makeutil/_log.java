package co.kr.makeutil;

import android.util.Log;

public class _log {
	public static void withThrowable(String descrition){
		Log.e("apphow", descrition, new ThrowableWithLog());
	}

	public static void e(String str){

		StackTraceElement[] e = new Exception().getStackTrace();
		Log.e("apphow", str + " at " + e[1]);
	}

	public static void memory(){
		String total, max, free, alloc;

		total = Runtime.getRuntime().totalMemory() / (1024 * 1024) + "MB ";
		max = Runtime.getRuntime().maxMemory() / (1024 * 1024) + "MB ";
		free = Runtime.getRuntime().freeMemory() / (1024 * 1024) + "MB ";
		alloc = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + "MB";
		
		String str = total + max + free + alloc;

		StackTraceElement[] e = new Exception().getStackTrace();
		Log.e("apphow", str +  " : " + e[1]);
	}

	public static void simple(String str){

		Log.e("apphow", str);
	}

	public static void inCatch(String descrition){
		Log.e("apphow", "catch {");
		Log.e("apphow", descrition, new Throwable());
		Log.e("apphow", "}");
	}
	
	
//	public static void apphow(String str){
//
//		StackTraceElement[] e = new Exception().getStackTrace();
//		Log.e("apphow", str + " at " + e[1]);
//	}

}
