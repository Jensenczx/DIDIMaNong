package fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.chenjensen.testapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiverFragment extends Fragment {
    private View view;

    public ReceiverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_receiver, container, false);
        return view;
    }
    public void initView (){
        ListView list = (ListView)view.findViewById(R.id.receiver_list);
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),getData(),R.layout.listview,
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



}
