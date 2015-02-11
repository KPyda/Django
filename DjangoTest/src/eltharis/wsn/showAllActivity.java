/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eltharis.wsn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

/**
 *
 * @author eltharis
 */
public class showAllActivity extends Activity {

    private String executeGET() throws Exception{
        HttpClient httpclient = new DefaultHttpClient(); //Korzystamy z Apache, który jest w Android
        HttpResponse response = httpclient.execute(new HttpGet("http://10.0.2.2:8000/usershow/")); //każemy mu wykonać GETa
        StatusLine statusline = response.getStatusLine(); //sprawdzamy status
        Toast toast = Toast.makeText(getApplicationContext(), "HTTP Response: " + Integer.toString(statusline.getStatusCode()), Toast.LENGTH_LONG);
        toast.show(); //prosty Toast z HttpStatusCode
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        response.getEntity().writeTo(out); //zapisujemy dane HTTP do ByteArrayOutputStream
        String responseString = out.toString(); 
        out.close(); //zamykamy OutputStream
        return responseString;
    }
    
    private void showAll(String httpResponse){
        TextView tv = (TextView)findViewById(R.id.tv); //szukamy TextView
        tv.setText(httpResponse); //ustawiamy jego tekst jako httpResponse
    }
    
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.showall_layout);
        try{
            String response = executeGET();
            showAll(response);
        }catch(Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG); //do debuggowania
            toast.show();
        }
        
    }
    
}
