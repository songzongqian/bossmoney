package com.byx.xiuboss.xiuboss.Bean;


public class RewardBean {


    /**
     * code : 2000
     * message : 微信提现每天只有十次机会，奖励金额已领取到余额。
     * data : {"type":"sendnumerror","date":1545208522,"moneytype":"credit","time":"2"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : sendnumerror
         * date : 1545208522
         * moneytype : credit
         * time : 2
         */

        private String type;
        private int date;
        private String moneytype;
        private String time;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public String getMoneytype() {
            return moneytype;
        }

        public void setMoneytype(String moneytype) {
            this.moneytype = moneytype;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
