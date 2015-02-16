package eltharis.wsn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{    
    public void showall_function(){
        Intent intent = new Intent(this, showAllActivity.class); //Tworzymy Intent, który będzie nową aktywnością
        this.startActivity(intent); //zaczynamy intent
    }
    
    public void showid_function(){
        try{
            Intent intent = new Intent(this, showIDActivity.class); //tworzymy intent
            int id_field = Integer.parseInt((((EditText)findViewById(R.id.tf)).getText()).toString()); //pobieramy ID do wyświetlenia
            intent.putExtra("id", id_field); //dodajemy jako Extra do intentu. Są to tak jakby parametry
            this.startActivity(intent); //zaczynamy intent
        }catch(Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Błędne dane", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); //pobierz layout main.xml

        Button showall = (Button)findViewById(R.id.showall); //znajdź button showall
        showall.setOnClickListener(new View.OnClickListener() { //nadajemy ClickListener do buttona showall

            public void onClick(View v) {
                showall_function();
            }
        });
        Button showid = (Button)findViewById(R.id.showid); //znajdź button showid
        showid.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showid_function();
            }
        });
        
    }
}
