package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

public class BillTestSecondBean {

    /**
     * code : 2000
     * data : [{"addtime":"1544174316","order":{"orderId":"0"},"paybill":{"orderId":"10"},"price":"6431.29","returnMoney":10,"stat_day":"20181207","sum":10},{"addtime":"1544083674","order":{"orderId":"0"},"paybill":{"orderId":"2"},"price":"1300.02","returnMoney":2,"stat_day":"20181206","sum":4},{"addtime":"1543977060","order":{"orderId":"0"},"paybill":{"orderId":"0"},"price":"0.03","returnMoney":0,"stat_day":"20181205","sum":3},{"addtime":"1543457758","order":{"orderId":"0"},"paybill":{"orderId":"0"},"price":"1080.80","returnMoney":0,"stat_day":"20181129","sum":1},{"addtime":"1543395599","order":{"orderId":"0"},"paybill":{"orderId":"0"},"price":"25865.06","returnMoney":0,"stat_day":"20181128","sum":17}]
     * message : 请求成功
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
         * addtime : 1544174316
         * order : {"orderId":"0"}
         * paybill : {"orderId":"10"}
         * price : 6431.29
         * returnMoney : 10
         * stat_day : 20181207
         * sum : 10
         */

        private String addtime;
        private OrderBean order;
        private PaybillBean paybill;
        private String price;
        private String returnMoney;
        private String stat_day;
        private String sum;

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getReturnMoney() {
            return returnMoney;
        }

        public void setReturnMoney(String returnMoney) {
            this.returnMoney = returnMoney;
        }

        public String getStat_day() {
            return stat_day;
        }

        public void setStat_day(String stat_day) {
            this.stat_day = stat_day;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
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
             * orderId : 10
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
