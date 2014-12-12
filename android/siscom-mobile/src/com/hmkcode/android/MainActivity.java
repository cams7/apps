package com.hmkcode.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hmkcode.android.vo.Member;

public class MainActivity extends Activity implements OnClickListener {

	private static final String STRING_EMPTY = "";
	private static final String URL_POST = "http://192.168.0.160:8080/siscom/rest/member/add";

	private TextView tvIsConnected;

	private EditText etName;
	private EditText etEmail;
	private EditText etPhoneNumber;

	private Button btnPost;

	private Member member;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get reference to the views
		tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);
		etName = (EditText) findViewById(R.id.etName);
		etEmail = (EditText) findViewById(R.id.etEmail);
		etPhoneNumber = (EditText) findViewById(R.id.etPhone);
		btnPost = (Button) findViewById(R.id.btnPost);

		// check if you are connected or not
		if (isConnected()) {
			tvIsConnected.setBackgroundColor(0xFF00CC00);
			tvIsConnected.setText(R.string.msg_connected);
		} else {
			tvIsConnected.setText(R.string.msg_not_connected);
		}

		// add click listener to Button "POST"
		btnPost.setOnClickListener(this);

	}

	private static String POST(String url, Member person, String errorMsg) {
		final String STRING_INPUT_STREAM = "InputStream";

		InputStream inputStream = null;
		String result = STRING_EMPTY;
		try {

			// 1. create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// 2. make POST request to the given URL
			HttpPost httpPost = new HttpPost(url);

			String json = STRING_EMPTY;

			// 3. build jsonObject
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("id", person.getId());
			jsonObject.accumulate("name", person.getName());
			jsonObject.accumulate("email", person.getEmail());
			jsonObject.accumulate("phoneNumber", person.getPhoneNumber());

			// 4. convert JSONObject to JSON to String
			json = jsonObject.toString();

			// ** Alternative way to convert Person object to JSON string usin
			// Jackson Lib
			// ObjectMapper mapper = new ObjectMapper();
			// json = mapper.writeValueAsString(person);

			// 5. set json to StringEntity
			StringEntity se = new StringEntity(json);

			// 6. set httpPost Entity
			httpPost.setEntity(se);

			// 7. Set some headers to inform server about the type of the
			// content
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			// 8. Execute POST request to the given URL
			HttpResponse httpResponse = httpclient.execute(httpPost);

			// 9. receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// 10. convert inputstream to string
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = errorMsg;

		} catch (UnsupportedEncodingException e) {
			Log.d(STRING_INPUT_STREAM, e.getLocalizedMessage());
		} catch (ClientProtocolException e) {
			Log.d(STRING_INPUT_STREAM, e.getLocalizedMessage());
		} catch (IOException e) {
			Log.d(STRING_INPUT_STREAM, e.getLocalizedMessage());
		} catch (JSONException e) {
			Log.d(STRING_INPUT_STREAM, e.getLocalizedMessage());
		}

		// 11. return result
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnPost:
			if (!validate())
				Toast.makeText(getBaseContext(),
						getString(R.string.msg_enter_some_data),
						Toast.LENGTH_LONG).show();
			// call AsynTask to perform network operation on separate thread
			new HttpAsyncTask().execute(URL_POST);
			break;
		}
	}

	private boolean isConnected() {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
			return true;

		return false;
	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			member = new Member();
			member.setName(etName.getText().toString());
			member.setEmail(etEmail.getText().toString());
			member.setPhoneNumber(etPhoneNumber.getText().toString());

			return POST(urls[0], member, getString(R.string.msg_error));
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getBaseContext(), getString(R.string.msg_data_sent),
					Toast.LENGTH_LONG).show();
		}
	}

	private boolean validate() {
		if (etName.getText().toString().trim().equals(STRING_EMPTY))
			return false;
		if (etEmail.getText().toString().trim().equals(STRING_EMPTY))
			return false;
		if (etPhoneNumber.getText().toString().trim().equals(STRING_EMPTY))
			return false;

		return true;
	}

	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = STRING_EMPTY;
		String result = STRING_EMPTY;
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

}
