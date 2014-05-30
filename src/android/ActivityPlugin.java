package com.kalengo.weathermeida.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;

import com.kalengo.weathermeida.ui.TBActivity;
import com.kalengo.weathermeida.ui.TrendDetailActivity;
import com.kalengo.weathermeida.util.Constants;
import com.kalengo.weathermeida.util.UrlUtil;

/**
 * @author Administrator 打开新页面的插件
 */
public class ActivityPlugin extends CordovaPlugin {
	public static String OPEN = "open";

	public boolean execute(String action, JSONArray data,
			CallbackContext callbackContext) throws JSONException {
		if (OPEN.equals(action)) {
			open(data.getString(0), data.getBoolean(1), data.getString(2),
					data.getInt(3), callbackContext);
		}
		return false;
	}

	public synchronized void open(String baseurl, boolean flag, String title,
			int model, CallbackContext callbackContext) {
		if (this.cordova.getActivity().getParent() == null) {
			UrlUtil.info("but", "1");
		} else {
			UrlUtil.info("but", "2");
		}
		Intent intent = new Intent();

		UrlUtil.info("david", "model---" + model);
		if (model == 0) {
			intent = new Intent(this.cordova.getActivity(), TBActivity.class);
		} else {
			intent = new Intent(this.cordova.getActivity(),
					TrendDetailActivity.class);
		}

		if (model == 2) {
			intent = new Intent(this.cordova.getActivity(),
					TrendDetailActivity.class);
			intent.putExtra("isShare", true);
		}
		intent.putExtra("baseurl", baseurl);
		intent.putExtra("isShow", flag);
		intent.putExtra("title", title);
		cordova.getActivity().startActivityForResult(intent,
				Constants.LOGIN_REQUEST);

	}

}
