package co.kr.makeutil.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import co.kr.makeutil.R;


public class WarningPopup extends Dialog implements View.OnClickListener {
	public boolean bUseEnableBackKey = true;

	public interface OnClickListener {
		public void onIdentifyClick();
	}

	private WarningPopup.OnClickListener listener = null;
	
	// 인터페이스 객체화

	public WarningPopup(Context thiscontext) {

		super(thiscontext, android.R.style.Theme_Translucent_NoTitleBar);

		WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
		lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		lpWindow.dimAmount = 0.8f;
		getWindow().setAttributes(lpWindow);

		setContentView(R.layout.popup_general);
		
		//윈도우 매니저를 이용하여 사이즈 조절이 가능하다. 
	}

	public void getclass(Class<?> cls){
		
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View view = findViewById(R.id.btn_identify);
		View btn_exit = findViewById(R.id.btn_exit);
		view.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
	}

	public void setContent(String title, String message) {
		TextView textView = (TextView) findViewById(R.id.tv_title);
		textView.setText(title);

		textView = (TextView) findViewById(R.id.tv_content);
		textView.setText(message);
	}

	@Override
	public void onBackPressed() {
		if (bUseEnableBackKey == true) {
			super.onBackPressed();
		}
	}

	public void onClick(View view) {

		Log.e("id", "" + view.getId());
		
		int ids=view.getId();
		if(ids==R.id.btn_identify){
			dismiss();
			if (listener != null) {
				listener.onIdentifyClick();
			}
			
		}
		else if(ids==R.id.btn_identify){
			dismiss();
			if (listener != null) {
				listener.onIdentifyClick();
			}
		}
			
	}

	public void setOnClickListener(WarningPopup.OnClickListener listener) {
		this.listener = listener;
	}
}
