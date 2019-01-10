package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by night_slight on 2019/1/3.
 */

public class ReceipeInfo {

    /**
     * code : 2000
     * msg : ok
     * data : {"customerName":"8658","customerAvatar":"头像","totalIncome":"¥9000.00","returnOrderTotal":"23","orderList":[{"info":"第4次消费","total":"+24.5","returnCash":"返现12元","datetime":"18:12","orderSn":"111"},{"info":"第3次消费","total":"+24.5","returnCash":"返现12元","datetime":"18:12","orderSn":"111"},{"info":"第2次消费","total":"+24.5","returnCash":"返现12元","datetime":"18:12","orderSn":"111"},{"info":"第1次消费","total":"+24.5","returnCash":"返现12元","datetime":"18:12","orderSn":"111"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public static class DataBean {
        /**
         * customerName : 8658
         * customerAvatar : 头像
         * totalIncome : ¥9000.00
         * returnOrderTotal : 23
         * orderList : [{"info":"第4次消费","total":"+24.5","returnCash":"返现12元","datetime":"18:12","orderSn":"111"},{"info":"第3次消费","total":"+24.5","returnCash":"返现12元","datetime":"18:12","orderSn":"111"},{"info":"第2次消费","total":"+24.5","returnCash":"返现12元","datetime":"18:12","orderSn":"111"},{"info":"第1次消费","total":"+24.5","returnCash":"返现12元","datetime":"18:12","orderSn":"111"}]
         */

        private String customerName;
        private String customerAvatar;
        private String totalIncome;
        private String returnOrderTotal;
        private List<OrderListBean> orderList;
        private String payType;

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerAvatar() {
            return customerAvatar;
        }

        public void setCustomerAvatar(String customerAvatar) {
            this.customerAvatar = customerAvatar;
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

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListBean {
            /**
             * info : 第4次消费
             * total : +24.5
             * returnCash : 返现12元
             * datetime : 18:12
             * orderSn : 111
             */

            private String info;
            private String total;
            private String returnCash;
            private String datetime;
            private String orderSn;

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getReturnCash() {
                return returnCash;
            }

            public void setReturnCash(String returnCash) {
                this.returnCash = returnCash;
            }

            public String getDatetime() {
                return datetime;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }

            public String getOrderSn() {
                return orderSn;
            }

            public void setOrderSn(String orderSn) {
                this.orderSn = orderSn;
            }
        }
    }
}
