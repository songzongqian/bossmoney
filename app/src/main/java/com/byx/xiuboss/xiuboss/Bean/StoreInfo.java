package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by night_slight on 2019/1/2.
 */

public class StoreInfo {

    /**
     * code : 2000
     * message : ok
     * data : {"storeName":"醉清风","totalIncome":"¥9000.00","totalReturnRatio":"20%","orderCount":"173","returnOrderCount":"23","orderList":[{"customerAvatar":"头像","nickName":"Yx","total":"+24.5","returnCash":"返现1元","datetime":"18:12","orderSn":"111"},{"customerAvatar":"头像","nickName":"Yx","total":"+24.5","returnCash":"","datetime":"18:12","orderSn":"111"}]}
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
         * storeName : 醉清风
         * totalIncome : ¥9000.00
         * totalReturnRatio : 20%
         * orderCount : 173
         * returnOrderCount : 23
         * orderList : [{"customerAvatar":"头像","nickName":"Yx","total":"+24.5","returnCash":"返现1元","datetime":"18:12","orderSn":"111"},{"customerAvatar":"头像","nickName":"Yx","total":"+24.5","returnCash":"","datetime":"18:12","orderSn":"111"}]
         */

        private String storeName;
        private String totalIncome;
        private String totalReturnRatio;
        private String orderCount;
        private String returnOrderCount;
        private List<OrderListBean> orderList;

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getTotalIncome() {
            return totalIncome;
        }

        public void setTotalIncome(String totalIncome) {
            this.totalIncome = totalIncome;
        }

        public String getTotalReturnRatio() {
            return totalReturnRatio;
        }

        public void setTotalReturnRatio(String totalReturnRatio) {
            this.totalReturnRatio = totalReturnRatio;
        }

        public String getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(String orderCount) {
            this.orderCount = orderCount;
        }

        public String getReturnOrderCount() {
            return returnOrderCount;
        }

        public void setReturnOrderCount(String returnOrderCount) {
            this.returnOrderCount = returnOrderCount;
        }

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListBean {
            /**
             * customerAvatar : 头像
             * nickName : Yx
             * total : +24.5
             * returnCash : 返现1元
             * datetime : 18:12
             * orderSn : 111
             */

            private String customerAvatar;
            private String nickName;
            private String total;
            private String returnCash;
            private String datetime;
            private String orderSn;

            public String getCustomerAvatar() {
                return customerAvatar;
            }

            public void setCustomerAvatar(String customerAvatar) {
                this.customerAvatar = customerAvatar;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
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
