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
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.cams7.siscom.member.vo.Member;
import br.com.cams7.siscom.util.RestUtil;

public final class MemberList extends Activity {

	private static final String TAG = "MemberList";

	private static final String URL = "http://192.168.0.160:8080/siscom/rest/member/list";

	private Button bAddMember;
	private ListView lvMembers;
	private CheckBox cbShowInvisible;

	private boolean showInvisible;

	private TextView tvIsConnected;
	private TextView tvResponse;

	private List<Member> members;

	/**
	 * Called when the activity is first created. Responsible for initializing
	 * the UI.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "Activity State: onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.member_list);

		// Obtain handles to UI objects
		bAddMember = (Button) findViewById(R.id.bAddMember);
		lvMembers = (ListView) findViewById(R.id.lvMembers);
		cbShowInvisible = (CheckBox) findViewById(R.id.cbShowInvisible);

		tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);
		tvResponse = (TextView) findViewById(R.id.tvResponse);

		// Initialize class properties
		showInvisible = false;
		cbShowInvisible.setChecked(showInvisible);

		// Register handler for UI elements
		bAddMember.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d(TAG, "bAddMember clicked");
				launchMemberEdit();
			}
		});
		cbShowInvisible
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						Log.d(TAG, "cbShowInvisible changed: " + isChecked);
						showInvisible = isChecked;
						populateMembers();
					}
				});

		// check if you are connected or not
		if (RestUtil.isConnected(this)) {
			tvIsConnected.setBackgroundColor(0xFF00CC00);
			tvIsConnected.setText(R.string.msg_connected);
		} else {
			tvIsConnected.setText(R.string.msg_not_connected);
		}

		// call AsynTask to perform network operation on separate thread
		new HttpAsyncTask().execute(URL);
	}

	/**
	 * Populate the contact list based on account currently selected in the
	 * account spinner.
	 */
	private void populateMembers() {
		final MemberArrayAdapter adapter;

		if (showInvisible)
			adapter = new MemberArrayAdapter(this, members);
		else {
			List<Member> membersActive = new ArrayList<Member>();
			for (Member member : members)
				if (member.isActive())
					membersActive.add(member);

			adapter = new MemberArrayAdapter(this, membersActive);
		}

		lvMembers.setAdapter(adapter);
	}

	/**
	 * Launches the ContactAdder activity to add a new contact to the selected
	 * accont.
	 */
	private void launchMemberEdit() {
		Intent intent = new Intent(this, MemberEdit.class);
		startActivity(intent);
	}

	private static String GET(String url, String errorMsg) {
		// create HttpClient
		HttpClient httpclient = new DefaultHttpClient();

		try {
			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
			// receive response as inputStream
			InputStream inputStream = httpResponse.getEntity().getContent();
			if (inputStream == null) {
				Log.d(RestUtil.TAG_INPUT_STREAM, "InputStream is null");
				return errorMsg;
			}

			return RestUtil.convertInputStreamToString(inputStream);
		} catch (ClientProtocolException e) {
			Log.d(RestUtil.TAG_INPUT_STREAM, e.getLocalizedMessage(),
					e.getCause());
		} catch (IOException e) {
			Log.d(RestUtil.TAG_INPUT_STREAM, e.getLocalizedMessage(),
					e.getCause());
		}

		return errorMsg;
	}

	private class MemberArrayAdapter extends ArrayAdapter<Member> {

		private Context context;
		private List<Member> members;

		public MemberArrayAdapter(Context context, List<Member> members) {
			super(context, R.layout.member_entry, members);

			this.context = context;
			this.members = members;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
		 * android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.member_entry, parent,
					false);
			TextView tvName = (TextView) rowView.findViewById(R.id.rowTvName);
			tvName.setText(members.get(position).getName());

			// TextView tvEmail = (TextView) rowView
			// .findViewById(R.id.emailEntryText);
			// tvEmail.setText(members.get(position).getEmail());

			return rowView;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.ArrayAdapter#getItemId(int)
		 */
		@Override
		public long getItemId(int position) {
			Member item = getItem(position);
			return item.getId();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.BaseAdapter#hasStableIds()
		 */
		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
		 */
		protected String doInBackground(String... urls) {
			return GET(urls[0], getString(R.string.msg_error));
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getBaseContext(), getString(R.string.msg_received),
					Toast.LENGTH_LONG).show();

			String errorMsg = getString(R.string.msg_error);
			members = new ArrayList<Member>();

			if (!errorMsg.equals(result)) {
				try {
					JSONArray array = new JSONArray(result);

					if (array.length() > 0) {

						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);

							Member member = new Member();
							member.setId(object.getLong("id"));
							member.setName(object.getString("name"));
							member.setEmail(object.getString("email"));
							member.setPhoneNumber(object
									.getString("phoneNumber"));
							member.setActive(true);

							members.add(member);
						}

						lvMembers.setVisibility(View.VISIBLE);
					} else {
						tvResponse.setVisibility(View.VISIBLE);
						tvResponse.setText(R.string.msg_list_empty);
					}
				} catch (JSONException e) {
					Log.d(TAG, e.getLocalizedMessage(), e.getCause());
				}
			} else {
				tvResponse.setVisibility(View.VISIBLE);
				tvResponse.setText(errorMsg);
			}
			// Populate the contact list
			populateMembers();
		}
	}
}
