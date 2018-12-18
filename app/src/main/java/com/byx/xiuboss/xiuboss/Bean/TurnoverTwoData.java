package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by wwj on 2018/9/1.
 */

public class TurnoverTwoData {


    /**
     * code : 2000
     * message : 请求成功
     * data : [{"sum":20,"addtime":"1535696072","price":"1566.00","stat_day":"20180831","order":{"orderId":"0"},"paybill":{"orderId":"4"},"returnMoney":4},{"sum":2,"addtime":"1535596929","price":"123.00","stat_day":"20180830","order":{"orderId":"0"},"paybill":{"orderId":"2"},"returnMoney":2},{"sum":26,"addtime":"1535503523","price":"851.22","stat_day":"20180829","order":{"orderId":"3"},"paybill":{"orderId":"10"},"returnMoney":13},{"sum":38,"addtime":"1535416962","price":"2272.94","stat_day":"20180828","order":{"orderId":"0"},"paybill":{"orderId":"0"},"returnMoney":0},{"sum":120,"addtime":"1535331270","price":"963955.04","stat_day":"20180827","order":{"orderId":"0"},"paybill":{"orderId":"1"},"returnMoney":1}]
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
         * sum : 20
         * addtime : 1535696072
         * price : 1566.00
         * stat_day : 20180831
         * order : {"orderId":"0"}
         * paybill : {"orderId":"4"}
         * returnMoney : 4
         */

        private int sum;
        private String addtime;
        private String price;
        private String stat_day;
        private OrderBean order;
        private PaybillBean paybill;
        private int returnMoney;

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

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public PaybillBean getPaybill() {
            return paybill;
        }

        public void setPaybill(PaybillBean paybill) {
            this.paybill = paybill;
        }

        public int getReturnMoney() {
            return returnMoney;
        }

        public void setReturnMoney(int returnMoney) {
            this.returnMoney = returnMoney;
        }

        public static class OrderBean {
            /**
             * orderId : 0
             */

            private String orderId;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }
        }

        public static class PaybillBean {
            /**
             * orderId : 4
             */

            private String orderId;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }
        }
    }
}
