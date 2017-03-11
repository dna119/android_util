package co.kr.textview;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class UT_TEXTVIEW {
	
	/**
	 * @param textview 적용할 텍스트뷰
	 * @param context 
	 * @param FontName asset/fonts/  폴더안에 폴더 이름
	 * @param text 
	 * return void
	 * date 2017. 3. 11.
	
		
	 */
	public static void TextView(final TextView textview, Context context,String FontName,String text){
		
		Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/"+FontName);
		textview.setTextSize(30f);
		textview.setTextColor(Color.BLACK);
		textview.setShadowLayer(5f, 0, 0, Color.WHITE);
		textview.setGravity(Gravity.CENTER);
		textview.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					textview.setTextColor(Color.WHITE);
					textview.setShadowLayer(9f, 0, 0, Color.BLACK);
				} else {
					textview.setTextColor(Color.BLACK);
					textview.setShadowLayer(7f, 0, 0, Color.WHITE);
				}
			}
		});
		textview.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent e) {
				if (e.getAction() == MotionEvent.ACTION_DOWN) {
					textview.setTextColor(Color.WHITE);
					textview.setShadowLayer(9f, 0, 0, Color.BLACK);
					return true;
				} else if (e.getAction() == MotionEvent.ACTION_UP) {
					textview.setTextColor(Color.BLACK);
					textview.setShadowLayer(7f, 0, 0, Color.WHITE);
					return false;
				}
				else{
					return false;
				}
				
			}
		});
		textview.setText(text);
		
	}
	

}
