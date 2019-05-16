package sg.edu.rp.c346.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ArrayList<Song> al;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<Song> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        DBHelper db = new DBHelper(this);
        db.getWritableDatabase();
        db.close();

        al = new ArrayList<Song>();

        lv = findViewById(R.id.lv);

        al = db.getAllSongs();

        final ArrayList<Song> data =  db.getAllSongs();




        db.close();

        aa = new CustomAdapter(this, R.layout.row, data);
        lv.setAdapter(aa);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {


                Intent i = new Intent(SecondActivity.this,
                        ThirdActivity.class);


                Song datas = data.get(position);


                String id = (datas).toString().split(",")[0].split(":")[1];
                Log.d("IDD",id);
                String title = (datas).toString().split(",")[1].trim();
                String singer = (datas).toString().split(",")[2].trim();
                String year = (datas).toString().split(",")[3].split(":")[4];
                String star = (datas).toString().split(",")[4].split(":")[4];

                Song target = new Song(Integer.parseInt(id), title, singer, Integer.parseInt(year), Integer.parseInt(star));
                i.putExtra("data", target);

                startActivity(i);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            lv.performClick();
        }
    }


}
