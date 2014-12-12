package br.com.cams7.siscom.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class RestUtil {
	public static final String TAG_INPUT_STREAM = "InputStream";

	public static boolean isConnected(Activity activity) {
		ConnectivityManager connMgr = (ConnectivityManager) activity
				.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
			return true;

		return false;
	}

	// convert inputstream to string
	public static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = null;
		String result = "";

		try {
			while ((line = bufferedReader.readLine()) != null)
				result += line;
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				Log.d(TAG_INPUT_STREAM, e.getLocalizedMessage(), e.getCause());
			}
		}
		return result;
	}

}
