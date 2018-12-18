package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by night_slight on 2018/11/15.
 */

public class ConfimOrderBean {

    /**
     * status : 1
     * message : 成功
     * data : []
     */

    private int status;
    private String message;
    private String code;
    private List<?> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
