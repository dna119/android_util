package co.kr.activitiy;

import java.util.Calendar;

import co.kr.makeutil.MainActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.Toast;


// 이 액티비티를 상속받게 되면 뒤로가기 버튼 눌렀을떄 종료가 뜨게된다
public abstract class BaseActivity extends Activity {

	public static final int COMMON_TITLE_FLAG_MAIN = 0,
							COMMON_TITLE_FLAG_TOP_LOGINED = 1, COMMON_TITLE_FLAG_ALLMENU = 2,
							COMMON_TITLE_FLAG_CLOSE_AND_LOGO = 3,
							COMMON_TITLE_FLAG_CLOSE_AND_TITLE = 4,
							COMMON_TITLE_FLAG_NORMAL = 5;

	private static final int MSG_TIMER_EXPIRED = 7777;
	private static final int BACK_KEY_TIMEOUT = 2000;

	private boolean bMasterActivity = false;
	private boolean bFinalActivity = false;
	private boolean bBackKeyPressed = false;
	private long currentTimeInMillis = 0;
	
	

	protected final Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		if (bFinalActivity == true) {
			if (intent.getBooleanExtra("FINISH_SELF", false) == true) {
				finish();
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (bFinalActivity == true) {
			System.exit(0);
		}
	}

	@Override
	public void onBackPressed() {
		if (bMasterActivity == true) {
			if (bBackKeyPressed == false) {
				bBackKeyPressed = true;

				currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
				Toast.makeText(this, "끝내시겟습니까??",
						Toast.LENGTH_SHORT).show();
				startTimer();
			} else {
				bBackKeyPressed = false;
				if (Calendar.getInstance().getTimeInMillis() <= (currentTimeInMillis + (BACK_KEY_TIMEOUT))) {
					Intent clearIntent = new Intent(BaseActivity.this,
							MainActivity.class);
					clearIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					clearIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					clearIntent.putExtra("FINISH_SELF", true);
					startActivity(clearIntent);
				}
			}
		} else {
			super.onBackPressed();

			overridePendingTransition(0, 0);
		}
	}

	private void startTimer() {
		timerHander
				.sendEmptyMessageDelayed(MSG_TIMER_EXPIRED, BACK_KEY_TIMEOUT);
	}

	private Handler timerHander = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_TIMER_EXPIRED:
				bBackKeyPressed = false;
				break;
			}
		}
	};

	protected void setMasterActivity(boolean flag) {
		bMasterActivity = flag;
	}

	protected void setFinalActivity(boolean flag) {
		bFinalActivity = flag;
	}

	

	

}
