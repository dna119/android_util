package co.kr.makeutil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStream;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

/**
 * @author dev-rna1
 *
 */
public class UT_STORAGE {
	final static String TAG="UT_PHONE";
	
	
	
	/** 현재 내폰안에있는 동영상 정보들을 arraylist- String으로 표현한다.
	 * @param con - Context
	 * @param This_Act - This Activity
	 * @return
	 */
	public static  ArrayList<String> getPhoneMovieLISTS(Context con,Activity This_Act){
		Cursor mVideoCursor;
		ArrayList<String> arr=new ArrayList<String>();
		String[] proj = { MediaStore.Video.Media._ID,
				MediaStore.Video.Media.DATA, 
				MediaStore.Video.Media.DISPLAY_NAME,
				MediaStore.Video.Media.SIZE ,
				MediaStore.Video.Media.TITLE,
				MediaStore.Video.Media.DURATION,
				MediaStore.Video.Media.DATE_ADDED,
				MediaStore.Video.Media.RESOLUTION
		};


		mVideoCursor = This_Act.managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);

		if(mVideoCursor!=null && mVideoCursor.moveToFirst())
		{
			
			do
			{
				
				String id =   mVideoCursor.getString(mVideoCursor.getColumnIndex(MediaStore.Video.Media._ID));
				String  size = mVideoCursor.getString(mVideoCursor   . getColumnIndex(MediaStore.Video.Media.SIZE));
				String  title = mVideoCursor.getString(mVideoCursor   . getColumnIndex(MediaStore.Video.Media.TITLE));
				String  data = mVideoCursor.getString(mVideoCursor   . getColumnIndex(MediaStore.Video.Media.DATA));
				String  duration = mVideoCursor.getString(mVideoCursor . getColumnIndex(MediaStore.Video.Media.DURATION));
				
				arr.add(id+","+size+","+title+","+data+","+duration);




				Log.e("UT_Phone","id : "+id+", size :"+size+", title : "+title+", data : "+data+", duration : "+duration);

				

			}while(mVideoCursor.moveToNext());

			mVideoCursor  = null;

		}
		return arr;

	}
	
	/**
	 * @param bitmap Bitmpa 객체
	 * @param FileFolderName 파일 폴더 이름(새로생성 or 있는거)
	 * @param FilePath_NameOnly 파일이름만 ,  확장자는 jpg 정해짐
	 * @return
	 */
	@SuppressLint("SdCardPath")
	public static boolean SaveBitmapToSdCard(Bitmap bitmap,String FileFolderName, String FilePath_NameOnly){

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+FileFolderName);
        if (!file.exists()) {
            file.mkdirs();
        }
        File fileCacheItem = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+FileFolderName+"/"+FilePath_NameOnly+".jpg");
        OutputStream out = null;
        try {
            fileCacheItem.createNewFile();
            out = new FileOutputStream(fileCacheItem);
 
            bitmap.compress(CompressFormat.JPEG, 100, out);
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
        return true;
        
	}
	
	public static String[] getTitlelist(final String EXT){
		try 
		{
			FilenameFilter fileFilter = new FilenameFilter()  //이부분은 특정 확장자만 가지고 오고 싶을 경우 사용하시면 됩니다.
			{
				public boolean accept(File dir, String name) 
				{
				return name.endsWith(EXT); //이 부분에 사용하고 싶은 확장자를 넣으시면 됩니다.
				} //end accept
			};
			File file = new File("/sdcard/A"); //경로를 SD카드로 잡은거고 그 안에 있는 A폴더 입니다. 입맛에 따라 바꾸세요.
			File[] files = file.listFiles(fileFilter);//위에 만들어 두신 필터를 넣으세요. 만약 필요치 않으시면 fileFilter를 지우세요.
			String [] titleList = new String [files.length]; //파일이 있는 만큼 어레이 생성했구요
			for(int i = 0;i < files.length;i++) 
			{   
				titleList[i] = files[i].getName();	//루프로 돌면서 어레이에 하나씩 집어 넣습니다.
			}//end for
			return titleList;
		} catch( Exception e ) 
		{
			return null;
		}
		
	}
	

	/**
	 * @param intent
	 * @param context
	 * @return string[2] ,0 = 타이틀 ,1 = 패쓰
	 *  @auto dev-rna1 
	 *  @since 2017. 3. 10.
	 */
	public static String[] getMusicTitleAndPath(final Intent intent,Context context){
	   String[] str=new String[2];
	
	
	   ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
       // 핸드폰의 Local DB 에서 필요한 데이터를 가져 오는 부분
       Cursor mediaCursor = contentResolver.query(
               MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,    // from table
               null,   // select col, col, col
               null,   // where col=
               null,   // args
               null);  // order by

       if (mediaCursor == null) {
           Log.e(TAG,"Cursor.query Error");
       } else if (mediaCursor.moveToFirst()){
           Log.e(TAG, "Media count:"+ mediaCursor.getCount());

           // ColumnIndex 설정
           int titleIndex = mediaCursor.getColumnIndex(MediaStore.MediaColumns.TITLE);
           int musicid = mediaCursor.getColumnIndex(String.valueOf(MediaStore.MediaColumns.DATA));
           // mediaCursor 에서 원하는 데이터를 가져오는 Loop
               String title  = mediaCursor.getString(titleIndex);
               String musicpath  = mediaCursor.getString(musicid);
               str[0]=title;
               str[1]=musicpath;

			mediaCursor.close();
	
       }
       return str;
	}
	
}
