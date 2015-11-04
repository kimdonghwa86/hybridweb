package com.hybrid.hybridweb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class HibridWebActivity extends Activity {
	
	WebView myweb; // 웹뷰선언
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hibrid_web);
		
		myweb = (WebView) findViewById(R.id.myweb);
		
		WebSettings settings = myweb.getSettings();	
		settings.setJavaScriptEnabled(true);
		
		myweb.addJavascriptInterface(new MyJavascriptInterface(), "android");  
		myweb.setWebViewClient(new MyWebViewClient()); 				 //a href 별도웹브라우저가 뜨지 않게함.
		myweb.setWebChromeClient(new WebChromeClient()); //alert()
		
		myweb.loadUrl("http://192.168.10.28:8080/web/index.jsp");
		//myweb.loadUrl("http://localhost");
		//myweb.loadUrl("http://www.soen.kr");
	}
	
	class MyJavascriptInterface {
		@JavascriptInterface
		public void showToast(String msg){
			Log.i("###", msg);
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT); 
		}
		
	}
	
	class MyWebViewClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.i("###", "url = " + url);
			view.loadUrl(url);
			return true;
		}
	}

	@Override //메뉴관련
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hibrid_web, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
