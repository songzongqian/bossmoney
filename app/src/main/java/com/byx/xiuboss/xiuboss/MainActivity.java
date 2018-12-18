package com.byx.xiuboss.xiuboss;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Mvp.activity.BaseActivity;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.BillFragment;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.BillTestFragment;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.HomeFragment;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.IndexFragment;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.MyFragment;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.Orderragment;
import com.byx.xiuboss.xiuboss.Mvp.view.AutoRadioGroup;
import com.byx.xiuboss.xiuboss.NetUrl.Contast;
import com.byx.xiuboss.xiuboss.Utils.NotificationsUtils;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private int currentPosition = -1;
    private long firstTime = 0;
    @BindView(R.id.fram)
    FrameLayout fram;

    @BindViews({R.id.home, R.id.order, R.id.bill, R.id.mine})
    AutoRelativeLayout[] layouts;
    @BindViews({R.id.home_img, R.id.order_img, R.id.bill_img, R.id.mine_img})
    ImageView[] imageViews;
    @BindViews({R.id.home_title, R.id.order_title, R.id.bill_title, R.id.mine_title})
    TextView[] textViews;

    @BindView(R.id.order_num)
    ImageView order_num;

    private BaseFragment[] fragments = {new IndexFragment(), new Orderragment(),new BillTestFragment(), new MyFragment()};
    private PopupWindow window;
    private NiftyDialogBuilder dialogBuilder;
    private OnEventReceive receive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter(Contast.ONRECEIVE);
        receive = new OnEventReceive();
        registerReceiver(receive, filter);
        ButterKnife.bind(this);
        order_num.setVisibility(View.INVISIBLE);
        initView();
        onEvent(0x111);
    }


    @Override
    protected void onResume() {
        super.onResume();
        initNotifications();
    }

    private void initNotifications() {
        if (!NotificationsUtils.isNotificationEnabled(this)) {
            showPopupWindow();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receive != null) {
            unregisterReceiver(receive);
        }
    }

    private void initView() {
        currentPosition = 1;
        addFm(R.id.fram, fragments[1]);
        setCurrentStatus(0);
        setListener();
    }

    private void showPopupWindow() {
        dialogBuilder = NiftyDialogBuilder.getInstance(this);
        dialogBuilder
                .withTitle("提示")
                .withMessage("检测到您未开启通知权限，可能影响收款播报")
                .withEffect(Effectstype.Fall)
                .withButton1Text("忽略")
                .withButton2Text("打开")
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.cancel();

                    }
                }).setButton2Click(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //去设置中心
                dialogBuilder.cancel();
                Intent localIntent = new Intent();
                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (Build.VERSION.SDK_INT >= 9) {
                    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    localIntent.setData(Uri.fromParts("package", MainActivity.this.getPackageName(), null));
                } else if (Build.VERSION.SDK_INT <= 8) {
                    localIntent.setAction(Intent.ACTION_VIEW);
                    localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                    localIntent.putExtra("com.android.settings.ApplicationPkgName", MainActivity.this.getPackageName());
                }
                startActivity(localIntent);
            }
        }).show();
    }

    private void setListener() {
        for (int i = 0; i < layouts.length; i++) {
            layouts[i].setOnClickListener(this);
        }
    }

    public void goToFm(int postion) {
        if (currentPosition != postion) {
            goToFm(R.id.fram, fragments[postion], currentPosition > postion);
            currentPosition = postion;
            setCurrentStatus(postion);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
            }
        }
        return keyCode == KeyEvent.KEYCODE_BACK;
    }

    public void setCurrentStatus(int index) {
        goToFm(index);
        for (int i = 0; i < layouts.length; i++) {
            if (i == index) {
                imageViews[i].setSelected(true);
                textViews[i].setSelected(true);
            } else {
                imageViews[i].setSelected(false);
                textViews[i].setSelected(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home:
                setCurrentStatus(0);
                break;
            case R.id.order:
                setCurrentStatus(1);
                break;
            case R.id.bill:
                setCurrentStatus(2);
                break;
            case R.id.mine:
                setCurrentStatus(3);
                break;
        }
    }

    public void onEvent(int event) {
        if (event == 0x111) {
            int orderNum = SPUtils.getInstance(MainActivity.this).getInt("orderNum", 0);
            if (orderNum > 0) {
                order_num.setVisibility(View.VISIBLE);
            } else {
                order_num.setVisibility(View.GONE);
            }
        }
    }

    class OnEventReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            onEvent(0x111);
            if (fragments.length>0 && fragments[1]!=null){
                ((Orderragment)fragments[1]).setRequestData();
            }
        }

    }
}
