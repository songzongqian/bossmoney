package com.byx.xiuboss.xiuboss.Mvp.presenter;






import com.byx.xiuboss.xiuboss.Mvp.model.AfbModel;

import java.io.IOException;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class LoginPresenter implements LoginContract.LoginIPresenter {


    private AfbModel afbModel;
    private LoginContract.LoginIView loginIView;

    public LoginPresenter(LoginContract.LoginIView loginIView) {
        this.loginIView = loginIView;
        afbModel = new AfbModel();
    }

    @Override
    public void loadData(String url, Map<String, Object> params) {
        afbModel.login(url,params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            loginIView.getData(string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginIView.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
