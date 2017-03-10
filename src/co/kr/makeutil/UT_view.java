package co.kr.makeutil;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsListView;

public class UT_view {
	/**
	 * @param view View안에 찍히는지 확인
	 * @param x View의 가로 사이즈
	 * @param y View의 세로 사이즈
	 * @return
	 */
	public static boolean chkTouchInside(View view, int x, int y) {
		 int[] location = new int[2];
		 view.getLocationOnScreen(location);
		 if (x >= location[0]) {
		  if (x <= location[0] + view.getWidth()) {
		   if (y >= location[1]) {
		    if (y <= location[1] + view.getHeight()) {
		     return true;
		    }
		   }
		  }
		 }
		 return false;
		}
	
	/**
	 * @param ThisContext -Context
	 * @param Title -타이틀
	 * @param Content - 내용
	 * @param Yes - yes대신 쓸말
	 * @param NO - no 대신 쓸말
	 * @param onclickYes - yes click시에 할말
	 * @return
	 */
	public static Dialog AlertDialLog_Basic(Context ThisContext,String Title,String Content,String Yes,String NO,OnClickListener onclickYes){
			AlertDialog dlg=new AlertDialog.Builder(ThisContext)
		   .setTitle(Title)
		   .setMessage(Content)
		   .setPositiveButton(Yes,onclickYes)
		   .setNegativeButton(NO, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) {
		    	 dialog.dismiss();
		    }
		   	})
		   .create();
			return dlg;
		
		
	}
	/**
	 * @param View_setClickEvented - 클릭이벤트가 포함된 View 를 넣어준다.
	 * @param ThisContext - This Context
	 * @return
	 */
	public static Dialog AlertDialLog_Xml(View View_setClickEvented,Context ThisContext){
		AlertDialog dlg=new AlertDialog.Builder(ThisContext).create();
		dlg.setView(View_setClickEvented);
		dlg.setCancelable(false);
		return dlg;
	}
	
	/** 소스중에 리소스안에 drawalbe 을 Bitmap 파일로 변환 하기
	 * @param Thiscontext -This context
	 * @param Drawable - Bitmap 으로 변환할부분
	 * @return
	 */
	public static Bitmap DrawableToBitmap(Context Thiscontext, int Drawable){
		BitmapDrawable bitmap=(BitmapDrawable)Thiscontext.getResources().getDrawable(Drawable);
		Bitmap bits=bitmap.getBitmap();
		return bits;
	}
	

	/**
	 * @param ThisContext Context
	 * @param dlg 다이얼로그
	 * @param width width-dp
	 * @param height height-dp
	 */
	public static void AlertDLG_SetAttribute(Context ThisContext,AlertDialog dlg,int width,int height){
		android.view.WindowManager.LayoutParams params = dlg.getWindow().getAttributes(); 
        DisplayMetrics dm=ThisContext.getResources().getDisplayMetrics();
        int widthLocal=Math.round(width*dm.density);
        int heightLocal=Math.round(height*dm.density);
        params.width = widthLocal;
        params.height=heightLocal;
        dlg.getWindow().setAttributes(params);
	}
	
	
}
