package atherakber.com.viewcamera;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView)findViewById(R.id.webview);
        webView();
    }

    private void webView() {
        //enable Js for youtube vids etc
        webview.getSettings().setJavaScriptEnabled(true);
        //Handling Page Navigation
        webview.setWebViewClient(new MyWebViewClient());
        //my rpi cam url to load on webview
        webview.loadUrl("http://192.168.100.16/html/min.php");
    }

    @Override public void onBackPressed() {
        if(webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }

  
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("http://192.168.100.16/html/min.php")) { //Force to open the url in webview
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

    }
