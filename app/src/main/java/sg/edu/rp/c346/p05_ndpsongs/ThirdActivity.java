package sg.edu.rp.c346.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView tvID;
    EditText etTitle, etSinger, etYear;
    Button btnUpdate, btnDelete;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //initialize the variables with UI here
        tvID = findViewById(R.id.tvID);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);


        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        tvID.setText("ID: " + data.getId());
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(data.getYear());
        int radio = data.getStars();
        final RadioButton radioButton = (RadioButton) findViewById(radio);
        radioButton.setChecked(true);






        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSinger.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                data.setStars(Integer.parseInt(radioButton.getText().toString()));
                dbh.updateSong(data);
                dbh.close();

                Intent i = new Intent();
                i.putExtra("data", etTitle.getText().toString());
                setResult(RESULT_OK, i);

                finish();
            }
        });




    }
}
