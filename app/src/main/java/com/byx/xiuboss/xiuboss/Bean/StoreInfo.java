package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by night_slight on 2019/1/2.
 */

public class StoreInfo {


    /**
     * code : 2000
     * message : ok
     * data : {"totalIncome":"1.00","storeName":"醉清风1","orderCount":"1","returnOrderCount":"0","totalReturnRatio":"0%","orderList":[{"customerAvatar":null,"nickName":null,"total":"1","returnCash":null,"datetime":"10:14","orderSn":"20190107101259084266","uid":"7230"}],"pop_up_text":"1.1.2","pop_up_status":1,"pop_up_settles":"优化首页签到和等级展示。\r\n新增登录后语音提示领取奖励。 \r\n新增提现到账后推送语音提醒。 \r\n新增新入驻的商户可以领取新手大礼包。\r\n新增删除提现方式功能。\r\n修改新的手续费扣除方案。"}
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
         * totalIncome : 1.00
         * storeName : 醉清风1
         * orderCount : 1
         * returnOrderCount : 0
         * totalReturnRatio : 0%
         * orderList : [{"customerAvatar":null,"nickName":null,"total":"1","returnCash":null,"datetime":"10:14","orderSn":"20190107101259084266","uid":"7230"}]
         * pop_up_text : 1.1.2
         * pop_up_status : 1
         * pop_up_settles : 优化首页签到和等级展示。
         新增登录后语音提示领取奖励。
         新增提现到账后推送语音提醒。
         新增新入驻的商户可以领取新手大礼包。
         新增删除提现方式功能。
         修改新的手续费扣除方案。
         */

        private String totalIncome;
        private String storeName;
        private String orderCount;
        private String returnOrderCount;
        private String totalReturnRatio;
        private String pop_up_text;
        private String pop_up_status;
        private String pop_up_settles;
        private List<OrderListBean> orderList;

        public String getTotalIncome() {
            return totalIncome;
        }

        public void setTotalIncome(String totalIncome) {
            this.totalIncome = totalIncome;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
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

        public String getTotalReturnRatio() {
            return totalReturnRatio;
        }

        public void setTotalReturnRatio(String totalReturnRatio) {
            this.totalReturnRatio = totalReturnRatio;
        }

        public String getPop_up_text() {
            return pop_up_text;
        }

        public void setPop_up_text(String pop_up_text) {
            this.pop_up_text = pop_up_text;
        }

        public String getPop_up_status() {
            return pop_up_status;
        }

        public void setPop_up_status(String pop_up_status) {
            this.pop_up_status = pop_up_status;
        }

        public String getPop_up_settles() {
            return pop_up_settles;
        }

        public void setPop_up_settles(String pop_up_settles) {
            this.pop_up_settles = pop_up_settles;
        }

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListBean {
            /**
             * customerAvatar : null
             * nickName : null
             * total : 1
             * returnCash : null
             * datetime : 10:14
             * orderSn : 20190107101259084266
             * uid : 7230
             */

            private String customerAvatar;
            private String nickName;
            private String total;
            private String returnCash;
            private String datetime;
            private String orderSn;
            private String uid;

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

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }
        }
    }
}
