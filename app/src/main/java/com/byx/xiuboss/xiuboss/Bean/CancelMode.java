package com.byx.xiuboss.xiuboss.Bean;

/**
 * Created by night_slight on 2018/11/19.
 */

public class CancelMode {
    private String title;
    private boolean isSelect;

    public CancelMode() {
    }

    public CancelMode(String title, boolean isSelect) {
        this.title = title;
        this.isSelect = isSelect;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
