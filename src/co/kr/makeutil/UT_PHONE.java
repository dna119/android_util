/**
 *  @title 
 *  @packagename co.kr.activitiy 
 *  @fielname UT_PHONE.java
 *  @auto dev-rna1 
 *  @since 2017. 3. 21.
 */
package co.kr.makeutil;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * @author dev-rna1
 *
 */
public class UT_PHONE {
	
	/**
	 * 내폰안에 전화번호 모두 가져오기
	 * @param context
	 * @return Arraylist<String[]>
	 *  @auto dev-rna1 
	 *  @since 2017. 3. 21.
	 */
	public static ArrayList<String[]> getContactList(Context context) {

		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

		String[] projection = new String[] {
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
				ContactsContract.CommonDataKinds.Phone.NUMBER,
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME };

		String[] selectionArgs = null;

		String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
				+ " COLLATE LOCALIZED ASC";

		Cursor contactCursor = ((Activity)context).managedQuery(uri, projection, null,
				selectionArgs, sortOrder);

		ArrayList<String[]> contactlist = new ArrayList<String[]>();

		if (contactCursor.moveToFirst()) {
			do {
				String phonenumber = contactCursor.getString(1).replaceAll("-",
						"");
				if (phonenumber.length() == 10) {
					phonenumber = phonenumber.substring(0, 3) + "-"
							+ phonenumber.substring(3, 6) + "-"
							+ phonenumber.substring(6);
				} else if (phonenumber.length() > 8) {
					phonenumber = phonenumber.substring(0, 3) + "-"
							+ phonenumber.substring(3, 7) + "-"
							+ phonenumber.substring(7);
				}

				String[] acontact = new String[2];
				acontact[0]=(phonenumber);
				acontact[1]=(contactCursor.getString(2));
				
				Log.e("phonennumber", phonenumber+"");
				Log.e("phonennumber", contactCursor.getString(2));
				contactlist.add(acontact);
			} while (contactCursor.moveToNext());
		}

		return contactlist;

	}
	
	
	
	/** 전화번호로 바로 전화하기
	 * @param conetxt
	 * @param callNum
	 *  @auto dev-rna1 
	 *  @since 2017. 3. 21.
	 */
	public static void CallNum(Context conetxt,String callNum){ 
		conetxt.startActivity(new Intent("android.intent.action.CALL",Uri.parse("tel:"+callNum)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
	}

}
