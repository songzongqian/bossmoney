package com.byx.xiuboss.xiuboss.Utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.byx.xiuboss.xiuboss.R;




public class ConfirmDialog extends Dialog {

    Callback callback;
    private WebView wbVersionUpdate;
    private Button ivVersionUpdate;

    public ConfirmDialog(Context context, Callback callback) {
        super(context, R.style.cusDialog);
        this.callback = callback;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_version_update, null);
        wbVersionUpdate = view.findViewById(R.id.wb_version_update);
        ivVersionUpdate = view.findViewById(R.id.btn_sure);
        Button btnCancle=view.findViewById(R.id.btn_cancel);

        WebSettings settings = wbVersionUpdate.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportZoom(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setTextSize(WebSettings.TextSize.SMALLER);

        wbVersionUpdate.setWebChromeClient(new WebChromeClient());

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callback(0);
                ConfirmDialog.this.cancel();
            }
        });


        ivVersionUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callback(1);
                ConfirmDialog.this.cancel();
            }
        });

        super.setContentView(view);
    }

    public ConfirmDialog setContent(String s){
        //加载内容 解决乱码问题
        wbVersionUpdate.loadData(s,"text/html; charset=UTF-8", null);
        return this;
    }

}
