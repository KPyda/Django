/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eltharis.wsn;

import eltharis.wsn.classes.User;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import eltharis.wsn.classes.UserArray;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.simpleframework.xml.*;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author eltharis
 */
public class showAllActivity extends ListActivity {

    private ArrayAdapter<User> adapter;
    private User[] users;

    private String executeGET() throws Exception {
        HttpClient httpclient = new DefaultHttpClient(); //Korzystamy z Apache, który jest w Android
        HttpResponse response = httpclient.execute(new HttpGet("https://agile-depths-5530.herokuapp.com/usershow/")); //każemy mu wykonać GETa
        StatusLine statusline = response.getStatusLine(); //sprawdzamy status
        Toast toast = Toast.makeText(getApplicationContext(), "HTTP Response: " + Integer.toString(statusline.getStatusCode()), Toast.LENGTH_LONG);
        toast.show(); //prosty Toast z HttpStatusCode
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        response.getEntity().writeTo(out); //zapisujemy dane HTTP do ByteArrayOutputStream
        String responseString = out.toString();
        out.close(); //zamykamy OutputStream
        return responseString;
    }

    private void showAll(String httpResponse) {
//        TextView tv = (TextView)findViewById(R.id.tv);
//        tv.setText(httpResponse);
        Serializer ser = new Persister();
        try {
            UserArray ua = ser.read(UserArray.class, httpResponse);
            users = ua.getUsers();
        } catch (Exception ex) {
            Logger.getLogger(showAllActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        try {
            String response = executeGET();
            showAll(response);
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG); //do debuggowania
            toast.show();
        }
        adapter = new ArrayAdapter<User>(this,
                android.R.layout.simple_list_item_1, users);
        setListAdapter(adapter);
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() { //nasłuchiwanie, czy jakiś z użytkowników był naciśnięty

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String username = ((TextView) view).getText().toString();
                Toast.makeText(getBaseContext(), username, Toast.LENGTH_SHORT).show();
                User selected = null;
                for (User u : users) {
                    if (u.getUsername().equals(username)) {
                        selected = u;
                        break;
                    }
                }
                if (selected != null) {
                    Intent intent = new Intent(getBaseContext(), showIDActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", selected.getId()); //dodajemy jako Extra do intentu. Są to tak jakby parametry
                    getBaseContext().startActivity(intent); //zaczynamy intent
                }
            }
        });
    }

}
