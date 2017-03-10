package co.kr.makeutil.dialog;

import android.content.Context;
import android.os.Looper;

public class WaitDialog extends Thread {

	
	Context mContext;
	Looper mLooper;

	public WaitDialog(Context context) {
		mContext = context;
		setDaemon(true);
	}

	@Override
	public void run() {
		Looper.prepare();
		
		CDlog.showLoading(mContext);
	
		mLooper = Looper.myLooper();
		Looper.loop();
	}

	public static void stop(WaitDialog dlg) {
		if (dlg != null) {
		
			CDlog.hideLoading();

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dlg.mLooper.quit();
		}
	}

}
