package com.byx.xiuboss.xiuboss.Bean;

/**
 * Created by night_slight on 2019/1/4.
 */

public class SettleInfo {

    /**
     * code : 2000
     * message : ok
     * data : {"totalIncome":"39262949.28","returnOrderTotal":"30177.49","actualIncome":"39232771.79"}
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
         * totalIncome : 39262949.28
         * returnOrderTotal : 30177.49
         * actualIncome : 39232771.79
         */

        private String totalIncome;
        private String returnOrderTotal;
        private String actualIncome;
        private String mobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getTotalIncome() {
            return totalIncome;
        }

        public void setTotalIncome(String totalIncome) {
            this.totalIncome = totalIncome;
        }

        public String getReturnOrderTotal() {
            return returnOrderTotal;
        }

        public void setReturnOrderTotal(String returnOrderTotal) {
            this.returnOrderTotal = returnOrderTotal;
        }

        public String getActualIncome() {
            return actualIncome;
        }

        public void setActualIncome(String actualIncome) {
            this.actualIncome = actualIncome;
        }
    }
}
