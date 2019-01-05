package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by night_slight on 2019/1/4.
 */

public class Level {

    /**
     * code : 2000
     * msg : ok
     * data : [{"level":"Lv1","value":"200分","returnCash":"0.3元"},{"level":"Lv2","value":"500分","returnCash":"0.46元"},{"level":"Lv3","value":"1000分","returnCash":"0.96元"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * level : Lv1
         * value : 200分
         * returnCash : 0.3元
         */

        private String level;
        private String value;
        private String returnCash;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getReturnCash() {
            return returnCash;
        }

        public void setReturnCash(String returnCash) {
            this.returnCash = returnCash;
        }
    }
}
