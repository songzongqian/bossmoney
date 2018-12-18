package com.byx.xiuboss.xiuboss.Mvp.presenter;

import java.util.Map;

public interface LoginContract {


    interface LoginIView {
        void getData(String message);

        void error(String error);
    }

    interface LoginIPresenter {
        void loadData(String url, Map<String, Object> params);
    }
}
