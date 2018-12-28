package com.byx.xiuboss.xiuboss.Bean;

public class VersionBean {


    /**
     * code : 2000
     * msg : 获取新版本信息成功！
     * data : {"enable":2,"msg":"检测到有新版本，是否下载安装？检测到有新版本，请下载升级！","url":"https://img.ourdaidai.com/apk/public/2018122111510515453642657165.apk"}
     * time : 1545393787
     */

    private int code;
    private String msg;
    private DataBean data;
    private String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class DataBean {
        /**
         * enable : 2
         * msg : 检测到有新版本，是否下载安装？检测到有新版本，请下载升级！
         * url : https://img.ourdaidai.com/apk/public/2018122111510515453642657165.apk
         */

        private String enable;
        private String msg;
        private String url;

        public String getEnable() {
            return enable;
        }

        public void setEnable(String enable) {
            this.enable = enable;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
