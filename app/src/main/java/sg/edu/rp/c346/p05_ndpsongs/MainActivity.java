package sg.edu.rp.c346.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    RadioGroup rg;
    Button btnInsert, btnShow;
    ArrayList<String> al;

    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);

        btnInsert = findViewById(R.id.buttonInsert);
        btnShow = findViewById(R.id.buttonShow);

        rg = findViewById(R.id.rg);

        al = new ArrayList<String>();


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int genid=rg.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(genid);



                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int star = Integer.parseInt(radioButton.getText().toString());
                DBHelper dbh = new DBHelper(MainActivity.this);
                long row_affected = dbh.insertSong(title,singer, year,star);
                dbh.close();

                if (row_affected != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i = new Intent(getBaseContext(), SecondActivity.class);
                startActivity(i);


            }
        });


    }
}
