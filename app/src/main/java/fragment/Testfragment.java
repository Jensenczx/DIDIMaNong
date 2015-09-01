package fragment;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.example.chenjensen.testapp.PersonActivity;
import com.example.chenjensen.testapp.QuestionActivity;
import com.example.chenjensen.testapp.R;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;

/**
 * Created by chenjensen on 15/6/6.
 */
public class Testfragment extends Fragment implements LocationSource,AMapLocationListener
        ,OnMarkerClickListener,OnInfoWindowClickListener,View.OnClickListener {
    private AMap aMap;
    private MapView myMapView;
    private View view;
    private OnLocationChangedListener mListener;
    private LocationManagerProxy mAMapLocationManager;
    private double baseLagtitude=30.513017;
    private double baseLatitude=114.429732;
    private Marker marker2;
    private Button bt_java;
    private Button bt_python;
    private Button bt_ruby;
    private EditText input_question;
    final int JAVA = 0;
    final int PYTHON = 1;
    final int RUBY = 2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        initView();
        myMapView.onCreate(savedInstanceState);
        getmap();
        return view;
    }

    public void initView (){
        myMapView = (MapView)view.findViewById(R.id.map);
        bt_java = (Button)view.findViewById(R.id.bt_java);
        bt_java.setOnClickListener(this);
        bt_python = (Button)view.findViewById(R.id.bt_python);
        bt_python.setOnClickListener(this);
        bt_ruby = (Button)view.findViewById(R.id.bt_ruby);
        bt_ruby.setOnClickListener(this);
        input_question = (EditText)view.findViewById(R.id.input_question);
        input_question.setOnClickListener(this);
    }
    public void getmap(){
        if(aMap==null){
            aMap = myMapView.getMap();
            setUpMap();
        }
    }
    public void addMaker(int type,double longtitude,double latitude){
        MarkerOptions markerOption = new MarkerOptions();
        LatLng lat = new LatLng(longtitude,latitude);
        markerOption.position(lat);
        markerOption.draggable(true);
        markerOption.title("隔壁老王");
        markerOption.snippet("专修电脑各种问题");
        switch (type){
            case JAVA:
                markerOption.icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_java));
                break;
            case PYTHON:
                markerOption.icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_python));
                break;
            case RUBY:
                markerOption.icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_ruby));
                break;
            default:
                break;
        }
        marker2 = aMap.addMarker(markerOption);
        aMap.setOnInfoWindowClickListener(this);
        // marker旋转90度
        //marker2.setRotateAngle(90);
    }
    private void setMarker(int type){
        for(int i=0; i<12;i++){
            if(i<2)
            addMaker(type,baseLagtitude+i*0.0001,baseLatitude+i*0.0001);
            else if(i<4)
                addMaker(type,baseLagtitude-i*0.0001,baseLatitude-i*0.0001);
            else if(i<6)
                addMaker(type,baseLagtitude+i*0.0001,baseLatitude-i*0.0001);
            else if(i<8)
                addMaker(type,baseLagtitude-i*0.0001,baseLatitude+i*0.0001);
            else if(i<10)
                addMaker(type,baseLagtitude-i*0.0002,baseLatitude+i*0.0001);
        }
    }
    private void setUpMap() {
        setMarker(JAVA);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_people));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.alpha(0));
        myLocationStyle.radiusFillColor(Color.alpha(0));// 设置圆形的填充颜色
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
    }
    public void skipActivity(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), PersonActivity.class);
        startActivity(intent);
    }
    public void addQuestion(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), QuestionActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_java:
                aMap.clear();
                setMarker(JAVA);
            break;
            case R.id.bt_python:
                aMap.clear();
                setMarker(PYTHON);
            break;
            case R.id.bt_ruby:
                aMap.clear();
                setMarker(RUBY);
                break;
            case R.id.input_question:
                addQuestion();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker==marker2){

        }
        return false;
    }
    @Override
    public void onInfoWindowClick(Marker marker) {

        skipActivity();
    }

    @Override
    public void onPause() {
        super.onPause();
        myMapView.onPause();
        deactivate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        myMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        myMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(myMapView!=null);
            aMap=null;
            myMapView.onDestroy();
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mAMapLocationManager == null) {
            mAMapLocationManager = LocationManagerProxy.getInstance(getActivity());
            mAMapLocationManager.requestLocationUpdates(
                    LocationProviderProxy.AMapNetwork, 2000, 10, this);
        }
    }
    @Override
    public void deactivate() {
        mListener = null;
        if (mAMapLocationManager != null) {
            mAMapLocationManager.removeUpdates(this);
            mAMapLocationManager.destory();
        }
        mAMapLocationManager = null;
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation aLocation) {
        if (mListener != null && aLocation != null) {
            Log.i("al", aLocation.getLatitude() + "weidu" + aLocation.getLongitude());
            mListener.onLocationChanged(aLocation);// 显示系统小蓝点
        }
    }




}
