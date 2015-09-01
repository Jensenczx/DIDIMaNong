package dhelper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by chenjensen on 15/6/1.
 */
public class SingleQueue {
    private static SingleQueue instance;
    private RequestQueue queue;
    private static Context mcontext;
    private SingleQueue(Context context){
        mcontext = context;
        queue = getRequestQueue();
    }
    public static synchronized SingleQueue getInstance(Context context){
        if(instance==null)
            instance = new SingleQueue(context);
        return instance;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
    public RequestQueue getRequestQueue(){
        if (queue==null)
            queue = Volley.newRequestQueue(mcontext.getApplicationContext());
        return queue;
    }
}
