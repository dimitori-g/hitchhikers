package com.example.kanji_san.hitchhikersguidetothegalaxy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.txt);
        final ListView mainText = findViewById(R.id.mainText);

        final String[] japText = getResources().getStringArray(R.array.japText);
        final String[] rusText = getResources().getStringArray(R.array.rusText);
        final String[] engText = getResources().getStringArray(R.array.engText);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, japText);
        mainText.setAdapter(adapter);

        mainText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(rusText[position] + "\n" + "--" + "\n" + engText[position]);
    /*            if (!textView.toString().equals(rusText[position]))
                      { textView.setText(rusText[position]); }
                else  { textView.setText(engText[position]); }*/
            }
        });

    }
}