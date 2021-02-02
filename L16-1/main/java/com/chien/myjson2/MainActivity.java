package com.chien.myjson2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //宣告JSON字串
    String jsonString = "{       \n" +
            "\ttitle:JSONParserTutorial,       \n" +
            "\tarray:[       \n" +
            "  {       \n" +
            "\tcompany:Google           \n" +
            "  },       \n" +
            "  {       \n" +
            "\tcompany:Facebook           \n" +
            "  },       \n" +
            "  {       \n" +
            "\tcompany:LinkedIn           \n" +
            "  },       \n" +
            "  {       \n" +
            "\tcompany:Microsoft           \n" +
            "  },       \n" +
            "  {       \n" +
            "\tcompany:Apple           \n" +
            "  }       \n" +
            "  ],       \n" +
            "\tnested:{       \n" +
            "\tflag:true,       \n" +
            "\trandom_number:1       \n" +
            "  }       \n" +
            "}";
    ListView listView;
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        items = new ArrayList<>();
        //字串轉JSON物件
        try {
            JSONObject object = new JSONObject(jsonString);
            //轉好之後實作成 array
            JSONArray array = object.getJSONArray("array");
            //再利用for迴圈提取裡面key是 company 的資料並加入到 items
            for(int i=0; i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                items.add(obj.getString("company"));
            }
            //取得其他key的內容
            JSONObject nestedObj = object.getJSONObject("nested");
            String str = nestedObj.getString("flag");
            boolean bool = nestedObj.getBoolean("flag");
            Toast.makeText(MainActivity.this, str+" / "+bool, Toast.LENGTH_SHORT).show();
        }
        catch (JSONException e){
            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (MainActivity.this,
                android.R.layout.simple_list_item_1,
                items);
        //ListView
        listView.setAdapter(adapter);
    }
}