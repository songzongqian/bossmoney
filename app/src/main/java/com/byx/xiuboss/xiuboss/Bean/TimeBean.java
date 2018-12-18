package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by wangwenjie001 on 2018/8/29.
 */

public class TimeBean {

    /**
     * code : 2000
     * message : 请求成功
     * data : [{"sum":22,"addtime":"1535416962","price":"1199.94","stat_day":"20180828","returnMoney":"0"},{"sum":95,"addtime":"1535331270","price":"960436.44","stat_day":"20180827","returnMoney":"0"},{"sum":25,"addtime":"1535243965","price":"8107.33","stat_day":"20180826","returnMoney":"0"},{"sum":35,"addtime":"1535071224","price":"13323.00","stat_day":"20180824","returnMoney":"0"},{"sum":6,"addtime":"1535009282","price":"8279.00","stat_day":"20180823","returnMoney":"0"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sum : 22
         * addtime : 1535416962
         * price : 1199.94
         * stat_day : 20180828
         * returnMoney : 0
         */

        private int sum;
        private String addtime;
        private String price;
        private String stat_day;
        private String returnMoney;

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStat_day() {
            return stat_day;
        }

        public void setStat_day(String stat_day) {
            this.stat_day = stat_day;
        }

        public String getReturnMoney() {
            return returnMoney;
        }

        public void setReturnMoney(String returnMoney) {
            this.returnMoney = returnMoney;
        }
    }
}
