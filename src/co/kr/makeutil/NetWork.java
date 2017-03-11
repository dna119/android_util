package co.kr.makeutil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import co.kr.makeutil.dialog.WaitDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


public class NetWork {
	String url;
	Context mContext;
	WaitDialog waitDialog = null;

	public NetWork(String url, Context context) {
		this.url = url;
		this.mContext = context;
	}

	public JSONArray getJSONArrayByPOST(List<NameValuePair> nameValuePairs) throws Exception {
		return new JSONArrayTask(nameValuePairs).execute().get();
	}

	public String getStringByPOST(List<NameValuePair> nameValuePairs) throws Exception {
		return new StringTask(nameValuePairs).execute().get();
	}

	public JSONArray getPUSHByPOST(List<NameValuePair> nameValuePairs) throws Exception {
		return new PUSHTask(nameValuePairs).execute().get();

	}

	public String getPUSHStringByPOST(List<NameValuePair> nameValuePairs) throws Exception {
		return new StringPUSHTask(nameValuePairs).execute().get();

	}

	private class JSONArrayTask extends AsyncTask<Void, Void, JSONArray> {
		List<NameValuePair> nameValuePair;
		InputStream is = null;
		StringBuilder sb = null;
		JSONArray jsonArray;

		public JSONArrayTask(List<NameValuePair> nameValuePair) {
			this.nameValuePair = nameValuePair;
		}

		@Override
		protected void onPreExecute() {
			if (waitDialog == null) {
				waitDialog = new WaitDialog(mContext);
				waitDialog.start();
			}

			super.onPreExecute();
		}

		@Override
		protected JSONArray doInBackground(Void... arg0) {
			try {

				Log.e("url", url);

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpParams params = httpClient.getParams();
				HttpConnectionParams.setConnectionTimeout(params, 10000);
				HttpConnectionParams.setSoTimeout(params, 10000);

				HttpPost httpPost = new HttpPost(url);

				if (nameValuePair != null) {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePair, "euc-kr");
					httpPost.setEntity(entity);
				}

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "euc-kr"), 8);
				sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				_log.e(sb.toString());

				JSONObject json = new JSONObject(sb.toString());
				jsonArray = JSONUtil.getSafeJSONArray(json, "data");

			} catch (Exception e) {

			}

			return jsonArray;
		}

		@Override
		protected void onPostExecute(JSONArray result) {
			WaitDialog.stop(waitDialog);
		}

	}

	private class StringTask extends AsyncTask<Void, Void, String> {
		List<NameValuePair> nameValuePair;
		InputStream is = null;
		StringBuilder sb = null;

		public StringTask(List<NameValuePair> nameValuePair) {
			this.nameValuePair = nameValuePair;
		}

		@Override
		protected void onPreExecute() {

			if (waitDialog == null) {
				waitDialog = new WaitDialog(mContext);
				waitDialog.start();
				super.onPreExecute();
			}
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {

				Log.e("url", url);

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);

				if (nameValuePair != null) {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePair, "euc-kr");
					httpPost.setEntity(entity);
				}
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "euc-kr"), 8);
				sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				_log.e(sb.toString());

			} catch (Exception e) {

			}

			return sb.toString();
		}

		@Override
		protected void onPostExecute(String result) {
			WaitDialog.stop(waitDialog);
		}

	}

	private class PUSHTask extends AsyncTask<Void, Void, JSONArray> {
		List<NameValuePair> nameValuePair;
		InputStream is = null;
		StringBuilder sb = null;
		JSONArray jsonArray;

		public PUSHTask(List<NameValuePair> nameValuePair) {
			this.nameValuePair = nameValuePair;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected JSONArray doInBackground(Void... arg0) {
			try {

				Log.e("url", url);

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpParams params = httpClient.getParams();
				HttpConnectionParams.setConnectionTimeout(params, 10000);
				HttpConnectionParams.setSoTimeout(params, 10000);

				HttpPost httpPost = new HttpPost(url);

				if (nameValuePair != null) {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePair, "euc-kr");
					httpPost.setEntity(entity);
				}

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "euc-kr"), 8);
				sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				_log.e(sb.toString());

				JSONObject json = new JSONObject(sb.toString());
				jsonArray = JSONUtil.getSafeJSONArray(json, "data");

			} catch (Exception e) {

			}

			return jsonArray;
		}

		@Override
		protected void onPostExecute(JSONArray result) {

		}

	}

	private class StringPUSHTask extends AsyncTask<Void, Void, String> {
		List<NameValuePair> nameValuePair;
		InputStream is = null;
		StringBuilder sb = null;

		public StringPUSHTask(List<NameValuePair> nameValuePair) {
			this.nameValuePair = nameValuePair;
		}

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {

				Log.e("url", url);

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);

				if (nameValuePair != null) {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePair, "euc-kr");
					httpPost.setEntity(entity);
				}
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "euc-kr"), 8);
				sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				_log.e(sb.toString());

			} catch (Exception e) {

			}

			return sb.toString();
		}

		@Override
		protected void onPostExecute(String result) {

		}

	}

}
