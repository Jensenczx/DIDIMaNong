package com.example.chenjensen.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initView();
    }
    public void initView (){
        ListView list = (ListView)findViewById(R.id.user_comment);
        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.listview,
                new String[]{"name","comment","img"},
                new int[]{R.id.uesr_name,R.id.user_comment,R.id.ic_photo});
        list.setAdapter(adapter);
    }
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "邻家小妹");
        map.put("comment", "电脑修的很好");
        map.put("img", R.drawable.ic_meizi);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "楼上姐姐");
        map.put("comment", "小弟弟电脑修的很好");
        map.put("img", R.drawable.ic_meizi);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "学姐");
        map.put("comment", "帮助我齐心协力完成毕设");
        map.put("img", R.drawable.ic_meizi);
        list.add(map);

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_person, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
