package com.espinoza.espinoza_labactivity3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private EditText skul1,skul2,skul3,skul4,skul5,skul6,skul7,skul8;
    private Button save, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeWidget();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<String> set = new HashSet<>();

                set.add(String.valueOf(skul1.getText()));
                set.add(String.valueOf(skul2.getText()));
                set.add(String.valueOf(skul3.getText()));
                set.add(String.valueOf(skul4.getText()));
                set.add(String.valueOf(skul5.getText()));
                set.add(String.valueOf(skul6.getText()));
                set.add(String.valueOf(skul7.getText()));
                set.add(String.valueOf(skul8.getText()));
                insertData(convertSetString(set));
                Toast.makeText(view.getContext(), "Data is successfully saved.", Toast.LENGTH_LONG).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(Main2Activity.class);
            }
        });
    }

    private String convertSetString(Set<String> set){
        StringBuilder sb = new StringBuilder();
        for (String s: set) {
            sb.append(s).append("-");
        }
        return sb.toString();
    }

    private void openActivity(Class activityClass){
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    private void initializeWidget(){
        skul1 = findViewById(R.id.skul1);
        skul2 = findViewById(R.id.skul2);
        skul3 = findViewById(R.id.skul3);
        skul4 = findViewById(R.id.skul4);
        skul5 = findViewById(R.id.skul5);
        skul6 = findViewById(R.id.skul6);
        skul7 = findViewById(R.id.skul7);
        skul8 = findViewById(R.id.skul8);
        save = findViewById(R.id.saveData);
        next = findViewById(R.id.button2);
    }


    private void insertData(String data){
        FileOutputStream stream = null;
        try {
            String filename = "data.txt";
            stream = openFileOutput(filename, Context.MODE_PRIVATE);
            stream.write(data.getBytes());
        } catch (FileNotFoundException e) {
            Log.d("error", "File not found. Please try again.");
        } catch (IOException e) {
            Log.d("error", "IO error. Restart the app.");
        }finally {
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
