/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eltharis.wsn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author eltharis
 */
public class showIDActivity extends Activity {

    private String executeGET(int id) throws Exception{
        HttpClient httpclient = new DefaultHttpClient(); //tutaj jest podobnie jak w showAllActivity
        HttpResponse response = httpclient.execute(new HttpGet("https://agile-depths-5530.herokuapp.com/usershow/" + Integer.toString(id)));
        StatusLine statusline = response.getStatusLine();
        Toast toast = Toast.makeText(getApplicationContext(), "HTTP Response: " + Integer.toString(statusline.getStatusCode()), Toast.LENGTH_LONG);
        toast.show();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        response.getEntity().writeTo(out);
        String responseString = out.toString();
        out.close();
        return responseString;
    }
    
    private void showId(String httpresponse){
        TextView tv = (TextView)findViewById(R.id.tv);
        tv.setText(httpresponse);
    }
    
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.showid_layout);
        int id = getIntent().getIntExtra("id", -1); //pobieramy z Extra parametr id
        try{
            String httpresponse = executeGET(id);
            showId(httpresponse);
        }catch(Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG);
            toast.show();
        }
        // ToDo add your GUI initialization code here        
    }
    
}
