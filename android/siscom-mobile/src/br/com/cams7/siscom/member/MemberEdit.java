/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.cams7.siscom.member;

import java.io.IOException;
import java.io.InputStream;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.cams7.siscom.member.vo.Member;
import br.com.cams7.siscom.util.RestUtil;

public final class MemberEdit extends Activity {
	private static final String STRING_EMPTY = "";

	private static final String TAG = "MemberEdit";

	private static final String URL = "http://192.168.0.160:8080/siscom/rest/member/add";

	private EditText etName;
	private EditText etEmail;
	private EditText etPhone;

	private Button bSave;

	private Member member;

	/**
	 * Called when the activity is first created. Responsible for initializing
	 * the UI.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "Activity State: onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.member_edit);

		etName = (EditText) findViewById(R.id.etName);

		etPhone = (EditText) findViewById(R.id.etPhone);
		etEmail = (EditText) findViewById(R.id.etEmail);

		addListenerOnSave();
	}

	public void addListenerOnSave() {

		bSave = (Button) findViewById(R.id.bSave);

		bSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
				// .parse("http://www.mkyong.com"));
				// startActivity(browserIntent);
				Log.v(TAG, "Save button clicked");

				if (validate()) {
					new HttpAsyncTask().execute(URL);

					Toast.makeText(getBaseContext(),
							getString(R.string.msg_enter_some_data),
							Toast.LENGTH_LONG).show();

					finish();
				}
			}
		});

	}

	private boolean validate() {
		StringBuffer warnMsg = new StringBuffer();
		if (etName.getText().toString().trim().equals(STRING_EMPTY))
			warnMsg.append(getString(R.string.member_name) + ": ").append(
					getString(R.string.msg_name_empty));

		if (etPhone.getText().toString().trim().equals(STRING_EMPTY))
			warnMsg.append(
					getStringNewLine(warnMsg)
							+ getString(R.string.member_phoneNumber) + ": ")
					.append(getString(R.string.msg_phone_empty));
		if (etEmail.getText().toString().trim().equals(STRING_EMPTY))
			warnMsg.append(
					getStringNewLine(warnMsg)
							+ getString(R.string.member_email) + ": ").append(
					getString(R.string.msg_email_empty));

		boolean validate = warnMsg.length() == 0;
		if (!validate)
			Toast.makeText(getBaseContext(), warnMsg.toString(),
					Toast.LENGTH_LONG).show();

		return validate;
	}

	private static String getStringNewLine(StringBuffer sb) {
		final String STRING_NEW_LINE = "\n";

		if (sb.length() > 0)
			return STRING_NEW_LINE;
		return STRING_EMPTY;
	}

	private static String POST(String url, Member member, String errorMsg) {

		// 1. create HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		try {
			// 2. make POST request to the given URL
			HttpPost httpPost = new HttpPost(url);

			// 3. build jsonObject
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("id", member.getId());
			jsonObject.accumulate("name", member.getName());
			jsonObject.accumulate("email", member.getEmail());
			jsonObject.accumulate("phoneNumber", member.getPhoneNumber());

			// 4. convert JSONObject to JSON to String
			String json = jsonObject.toString();

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
			InputStream inputStream = httpResponse.getEntity().getContent();

			if (inputStream == null) {
				Log.d(RestUtil.TAG_INPUT_STREAM, "InputStream is null");
				return errorMsg;
			}

			// 10. convert inputstream to string
			return RestUtil.convertInputStreamToString(inputStream);

		} catch (UnsupportedEncodingException e) {
			Log.d(RestUtil.TAG_INPUT_STREAM, e.getLocalizedMessage(),
					e.getCause());
		} catch (ClientProtocolException e) {
			Log.d(RestUtil.TAG_INPUT_STREAM, e.getLocalizedMessage(),
					e.getCause());
		} catch (IOException e) {
			Log.d(RestUtil.TAG_INPUT_STREAM, e.getLocalizedMessage(),
					e.getCause());
		} catch (JSONException e) {
			Log.d(RestUtil.TAG_INPUT_STREAM, e.getLocalizedMessage(),
					e.getCause());
		}

		// 11. return result
		return errorMsg;
	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			String name = etName.getText().toString();
			String phone = etPhone.getText().toString();
			String email = etEmail.getText().toString();

			member = new Member();
			member.setName(name);
			member.setEmail(email);
			member.setPhoneNumber(phone);

			return POST(urls[0], member, getString(R.string.msg_error));
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getBaseContext(), getString(R.string.msg_data_sent),
					Toast.LENGTH_LONG).show();
		}
	}

}
