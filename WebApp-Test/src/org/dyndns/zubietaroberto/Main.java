package org.dyndns.zubietaroberto;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main extends Activity {
	private static final String URL = "www.samesub.com";
	private WebView wv;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        wv = (WebView) this.findViewById(R.id.webview);
        wv.setWebViewClient(new MyWebViewClient());
        wv.loadUrl("http://" + URL);
        
        //Activate Javascript
        wv.getSettings().setJavaScriptEnabled(true);
        
        //Activate Flash (Not Working Yet)
        wv.getSettings().setPluginsEnabled(true);
        
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    private class MyWebViewClient extends WebViewClient {
        @Override
        
        //If the user clicks a hyperlink it will be opened in an external browser
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals(URL)) {
                return false;
            }
            final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
    
}