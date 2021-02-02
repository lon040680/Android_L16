package com.chien.mygson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    TextView show, result;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        show = findViewById(R.id.show);
        result = findViewById(R.id.result);
        input = findViewById(R.id.input);
    }

    public void onClick(View view) {
        //取得全部資料
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader
                (new InputStreamReader(getResources().openRawResource(R.raw.person)));
        //有關係到 JAVA IO 讀取存寫 都要try-catch
        String line;
        String all;
        try{
            while((line = br.readLine()) != null){
                sb.append(line);
            }
        }
        catch(IOException e) {
            System.out.println("存取錯誤");
        }
        all = sb.toString();

        //GSON分析
        Gson gson = new GsonBuilder().create();
        ArrayList<Person> list = gson.fromJson(all, new TypeToken<ArrayList<Person>>(){}.getType());
        //gson.fromJson() 拿到all 的資料 放進去 Person.java 比對看看由沒有這個變數, 有這個變數就放到 ArrayList
        //記得 Person.java(GETTER) 那邊只要一直送資料進去 就會有一種類似迴圈的方式送出資料 得以做成 ArrayList

        String data = "";
        for(Person p:list){
            data += p.toString()+"\n";
        }

        //顯示結果
        show.setText(data);

        //查詢
        String q = input.getText().toString().trim();
        result.setText("");
        for(Person p:list){
            if(p.getName().equals(q)){
                result.setText(p.toString());
            }
        }
    }
}