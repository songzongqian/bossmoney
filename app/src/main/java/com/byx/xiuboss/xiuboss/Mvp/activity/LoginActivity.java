package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Jgim.utils.BitmapLoader;
import com.byx.xiuboss.xiuboss.Jgim.utils.LoginController;
import com.byx.xiuboss.xiuboss.Jgim.utils.SharePreferenceManager;
import com.byx.xiuboss.xiuboss.Jgim.utils.SoftKeyBoardStateHelper;
import com.byx.xiuboss.xiuboss.Mvp.view.ClearWriteEditText;
import com.byx.xiuboss.xiuboss.R;

import java.lang.reflect.Method;

import cn.jpush.im.android.api.JMessageClient;


/**
 * 用户登录页面
 */
public class LoginActivity extends BaseActivity implements View.OnFocusChangeListener, View.OnClickListener {
    public ClearWriteEditText mLogin_userName;
    public ClearWriteEditText mLogin_passWord;
    public Button mBtn_login;
    public Button mBtn_logins;
    public TextView mLogin_register;
    private LoginController mLoginController;
    private ImageView mDe_login_logo;
    private RelativeLayout mBackground;
    private LinearLayout mLl_name_psw;
    private boolean mLogoShow = true;
    public TextView mLogin_desc;
    private ImageView mLogin_userLogo;
    private ImageView mLogin_pswLogo;
    private View mUserLine;
    //内部测试环境使用,发布时会置为false;此处对开发者来说即使打开也是没有效果的.
    private boolean isTestVisibility = true;
    private ImageView imgfinsh;
    private RelativeLayout translate;
    private TextView titleText;
    private TextView forgetPassword;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    initView();
    initData();
    mLoginController = new LoginController(this);
    mBtn_login.setOnClickListener(mLoginController);
    mLogin_register.setOnClickListener(mLoginController);
}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_userName:
            case R.id.login_passWord:
                if (getLogoShow()) {
                    mLl_name_psw.startAnimation(moveToView(0.0f, 0.0f, 0.32f, 0.0f));
                    setLogoShow(false);
                }
                break;
            default:
                break;
        }
    }



    public void setLogoShow(boolean isLogoShow) {
        mLogoShow = isLogoShow;
    }

    public boolean getLogoShow() {
        return mLogoShow;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.login_userName:
                if (hasFocus) {
                    //mLogin_pswLogo.setImageResource(R.drawable.login_psw_press);
                    mBtn_login.setVisibility(View.GONE);
                    mBtn_logins.setVisibility(View.VISIBLE);
                } else {
                    //mLogin_pswLogo.setImageResource(R.drawable.login_psw_normal);
                    mBtn_login.setVisibility(View.GONE);
                    mBtn_logins.setVisibility(View.VISIBLE);
                }
                if (hasFocus && getLogoShow()) {
                    setLogoShow(false);
                }
                break;
            case R.id.login_passWord:
                if (hasFocus) {
                    //mLogin_pswLogo.setImageResource(R.drawable.login_psw_press);
                    mBtn_login.setVisibility(View.VISIBLE);
                    mBtn_logins.setVisibility(View.GONE);
                } else {
                    //mLogin_pswLogo.setImageResource(R.drawable.login_psw_normal);
                    mBtn_login.setVisibility(View.VISIBLE);
                    mBtn_logins.setVisibility(View.GONE);
                }
                if (hasFocus && getLogoShow()) {
                    setLogoShow(false);
                }
                break;
            case R.id.title_back_image:
                finish();
                break;
        }
    }

    private void initData() {
        mLogin_userName.setOnFocusChangeListener(this);
        mLogin_passWord.setOnFocusChangeListener(this);
        mLogin_userName.setOnClickListener(this);
        mLogin_passWord.setOnClickListener(this);
        SoftKeyBoardStateHelper helper = new SoftKeyBoardStateHelper(findViewById(R.id.ll_name_psw));
        helper.addSoftKeyboardStateListener(new SoftKeyBoardStateHelper.SoftKeyboardStateListener() {
            @Override
            public void onSoftKeyboardOpened(int keyboardHeightInPx) {
                //软键盘弹起
            }

            @Override
            public void onSoftKeyboardClosed() {
                //软键盘关闭

            }
        });

    }


    public TranslateAnimation moveToView(float a, float b, float c, float d) {
        TranslateAnimation mHiddenAction = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, a,
                Animation.RELATIVE_TO_SELF, b,
                Animation.RELATIVE_TO_SELF, c,
                Animation.RELATIVE_TO_SELF, d);
        mHiddenAction.setDuration(250);
        return mHiddenAction;
    }


    private void initView() {
        ivBack = findViewById(R.id.title_back_image);
        mLogin_userName = (ClearWriteEditText) findViewById(R.id.login_userName);
        mLogin_passWord = (ClearWriteEditText) findViewById(R.id.login_passWord);
        mBtn_login = (Button) findViewById(R.id.btn_login);
        mBtn_logins = (Button) findViewById(R.id.btn_logins);
        mDe_login_logo = (ImageView) findViewById(R.id.de_login_logo);
        mLogin_register = (TextView) findViewById(R.id.login_register);
        mLl_name_psw = (LinearLayout) findViewById(R.id.ll_name_psw);
        translate = (RelativeLayout) findViewById(R.id.translate);
        titleText = findViewById(R.id.title_text);
        forgetPassword = findViewById(R.id.forgetPassword);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this,Login_RegisterActivity.class);
                startActivity(intent);
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCallPopupWindow("0330-3576321");
            }
        });
        mUserLine = findViewById(R.id.user_line);
        titleText.setText("登录");
        //退出登录重现上次的账号以及头像
        String userName =   SharePreferenceManager.getCachedUsername();
        String userAvatar = SharePreferenceManager.getCachedAvatarPath();
        Bitmap bitmap = BitmapLoader.getBitmapFromFile(userAvatar, mAvatarSize, mAvatarSize);
        if (bitmap != null) {
            mDe_login_logo.setImageBitmap(bitmap);
        } else {
            mDe_login_logo.setImageResource(R.mipmap.logo_sp);
        }
        mLogin_userName.setText(userName);
        if (userName != null)
            mLogin_userName.setSelection(userName.length());//设置光标位置

        //当把用户名删除后头像要换成默认的
        mLogin_userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDe_login_logo.setImageResource(R.mipmap.logo_sp);
                if (mLogin_userName.getText().length() == 0 || mLogin_passWord.getText().length() == 0) {
                    mBtn_login.setEnabled(false);
                } else {
                    mBtn_login.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        mLogin_passWord.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mLogin_userName.getText().length() == 0 || mLogin_passWord.getText().length() == 0) {
                    mBtn_login.setEnabled(false);
                } else {
                    mBtn_login.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public String getUserId() {
        return mLogin_userName.getText().toString().trim();
    }

    public String getPassword() {
        return mLogin_passWord.getText().toString().trim();
    }

    public static Boolean invokeIsTestEvn() {
        try {
            Method method = JMessageClient.class.getDeclaredMethod("isTestEnvironment");
            Object result = method.invoke(null);
            return (Boolean) result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void swapEnvironment(Context context, boolean isTest) {
        try {
            Method method = JMessageClient.class.getDeclaredMethod("swapEnvironment", Context.class, Boolean.class);
            method.invoke(null, context, isTest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showCallPopupWindow(final String call){
        View contentView = LayoutInflater.from(LoginActivity.this).inflate(R.layout.popup_call, null, false);
       final PopupWindow window = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView callName = contentView.findViewById(R.id.call_name);
        final TextView callNumber = contentView.findViewById(R.id.call_number);
        TextView callDial = contentView.findViewById(R.id.call_dial);
        TextView callCancel = contentView.findViewById(R.id.call_cancel);
        callName.setVisibility(View.GONE);
        callNumber.setText(call);
        callDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               call(call);
            }
        });
        callCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        backgroundAlpha(0.5f);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        window.showAtLocation(LayoutInflater.from(LoginActivity.this).inflate(R.layout.activity_login, null), Gravity.CENTER, 0, 0);
    }
    /**
     * 调用拨号界面	 * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        startActivity(intent);
    }
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

}
