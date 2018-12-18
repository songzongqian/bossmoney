package com.byx.xiuboss.xiuboss.Mvp.activity.businessinfo;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.byx.xiuboss.xiuboss.Mvp.activity.BaseActivity;
import com.byx.xiuboss.xiuboss.Mvp.view.ClearWriteEditText;
import com.byx.xiuboss.xiuboss.R;
import com.lljjcoder.citypickerview.widget.CityPicker;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreAddressActivity extends BaseActivity implements LocationSource, AMapLocationListener {
    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.rl_finish)
    RelativeLayout rlFinish;
    @BindView(R.id.tv_storeChoose)
    TextView tvStoreChoose;
    @BindView(R.id.button_image_one)
    ImageView buttonImageOne;
    @BindView(R.id.rl_storeName)
    RelativeLayout rlStoreName;
    @BindView(R.id.et_storeAddress)
    ClearWriteEditText etStoreAddress;
    @BindView(R.id.addressLocation)
    ImageView addressLocation;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_useAddress)
    TextView tvUseAddress;
    @BindView(R.id.iv_refresh)
    ImageView ivRefresh;
    @BindView(R.id.iv_map)
    MapView mapView;
    private AMap aMap;
    private AMapLocationClient mlocationClient;
    OnLocationChangedListener mListener;
    private String chooseAddress;

    /**
     * 商家位置地图
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {

        //初始化地图控制器对象
        if(aMap==null){
            aMap = mapView.getMap();
        }

        //设置地图的放缩级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        // 设置定位监听
        aMap.setLocationSource(this);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        //蓝点初始化
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        myLocationStyle.showMyLocation(true);
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

            }
        });
    }


    @OnClick({R.id.title_back_image, R.id.rl_finish, R.id.rl_storeName, R.id.addressLocation, R.id.tv_useAddress, R.id.iv_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_image:
                finish();
                break;
            case R.id.rl_finish:
                break;
            case R.id.rl_storeName:
                //选择省市区
                selectAddress();
                break;
            case R.id.addressLocation:
                //定位图标
                break;
            case R.id.tv_useAddress:
                //使用此地址
                break;
            case R.id.iv_refresh:
                //使用刷新按钮

                break;
        }
    }

    private void selectAddress() {
        CityPicker cityPicker = new CityPicker.Builder(StoreAddressActivity.this)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("北京市")
                .city("北京市")
                .district("海淀区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                String province = citySelected[0];
                String city = citySelected[1];
                String district = citySelected[2];
                String cityName = province.trim()+city.trim()+district.trim();
                tvStoreChoose.setText(province.trim()+"-"+city.trim()+"-"+district.trim());
                getLong(cityName);
            }
        });


    }

    private void getLong( String cityName) {
        GeocodeSearch geocodeSearch=new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

                if (i==1000){
                    if (geocodeResult!=null && geocodeResult.getGeocodeAddressList()!=null &&
                            geocodeResult.getGeocodeAddressList().size()>0){
                        GeocodeAddress geocodeAddress = geocodeResult.getGeocodeAddressList().get(0);
                        double latitude = geocodeAddress.getLatLonPoint().getLatitude();//纬度
                        double longititude = geocodeAddress.getLatLonPoint().getLongitude();//经度


                        Log.e("地理编码", geocodeAddress.getAdcode()+"");
                        Log.e("纬度latitude",latitude+"");
                        Log.e("经度longititude",longititude+"");

                        LatLng latLng = new LatLng(latitude, longititude);
                        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 18, 30, 0));
                        aMap.moveCamera(mCameraUpdate);
                        //aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 18));
                        deactivate();

                    }else {
                        Log.e("定位AmapErr", "地名错误");
                    }
                }
            }
        });

        GeocodeQuery geocodeQuery=new GeocodeQuery(cityName.trim(),"29");
        geocodeSearch.getFromLocationNameAsyn(geocodeQuery);




    }


    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            AMapLocationClientOption  mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);
                String province = aMapLocation.getProvince();
                String city = aMapLocation.getCity();
                String district = aMapLocation.getDistrict();//城区信息
                String street = aMapLocation.getStreet();//街道信息
                String streetNum = aMapLocation.getStreetNum();//街道门牌号信息
                String currentAddress = aMapLocation.getAddress();
                System.out.println("当前位置"+province+city+district+street+streetNum);
                tvStoreChoose.setText(province.trim()+"-"+city.trim()+"-"+district.trim());
                etStoreAddress.setText(street+streetNum);
                tvLocation.setText(street);
                System.out.println("当前精确位置"+currentAddress);
                deactivate();
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("定位AmapErr", errText);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }




}
