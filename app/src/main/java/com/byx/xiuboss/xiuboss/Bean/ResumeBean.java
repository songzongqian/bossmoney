package com.byx.xiuboss.xiuboss.Bean;

/**
 * Created by night_slight on 2018/11/21.
 */

public class ResumeBean {

    /**
     * code : -1
     * message : 退款失败
     */

    private int code;
    private String message;
    private int status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
}
