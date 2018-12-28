package com.example.kanji_san.hitchhikersguidetothegalaxy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final int[] chapters = {0, 24, 270, 337, 479, 590, 756, 865, 1042, 1061, 1155, 1169, 1295, 1388, 1470, 1541, 1555, 1632, 1761, 1801, 1840, 1944, 1980, 2047, 2055, 2119, 2202, 2208, 2259, 2291, 2358, 2386, 2481, 2542, 2569, 2595};

        final TextView textView = findViewById(R.id.txt);
        final ListView mainText = findViewById(R.id.mainText);

        // Reading from txt files into string array
        int numOfLines = 2603;
        final String[] japText = new String[numOfLines];
        final String[] rusText = new String[numOfLines];
        final String[] engText = new String[numOfLines];

        InputStream inputStreamLoader;
        BufferedReader bufferedReaderLoader;

        inputStreamLoader = this.getResources().openRawResource(R.raw.jp);
        bufferedReaderLoader = new BufferedReader(new InputStreamReader(inputStreamLoader));

        try {
            for (int i = 0; i < numOfLines; i++) {
                japText[i] = bufferedReaderLoader.readLine();
            }
        }catch (Exception e) {e.printStackTrace();}

        inputStreamLoader = this.getResources().openRawResource(R.raw.en);
        bufferedReaderLoader = new BufferedReader(new InputStreamReader(inputStreamLoader));

        try {
            for (int i = 0; i < numOfLines; i++) {
                engText[i] = bufferedReaderLoader.readLine();
            }
        }catch (Exception e) {e.printStackTrace();}

        inputStreamLoader = this.getResources().openRawResource(R.raw.ru);
        bufferedReaderLoader = new BufferedReader(new InputStreamReader(inputStreamLoader));

        try {
            for (int i = 0; i < numOfLines; i++) {
                rusText[i] = bufferedReaderLoader.readLine();
            }
        }catch (Exception e) {e.printStackTrace();}

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, japText);
        mainText.setAdapter(adapter);

        //mainText.setSelection(2600);

        mainText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(rusText[position] + "\n" + "--" + "\n" + engText[position]);
            }
        });

        mainText.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(
                        MainActivity.this);
                adb.setTitle("Chapter");
                final EditText chap = new EditText(MainActivity.this);

                chap.setInputType(InputType.TYPE_CLASS_NUMBER);
                chap.setHint("1 - 35");
                adb.setView(chap);
                adb.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        if(Integer.parseInt(chap.getText().toString()) > 0 && Integer.parseInt(chap.getText().toString()) <= 35){
                            mainText.setSelection(chapters[Integer.parseInt(chap.getText().toString())] - 1);
                        }
                        else{

                            Toast toast = Toast.makeText(MainActivity.this, "Number must be between 1 and 35", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.show();
                        }
                    }
                });
                adb.show();

                return false;
            }
        });

    }
}