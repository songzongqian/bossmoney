package com.byx.xiuboss.xiuboss.Bean;


import java.io.Serializable;
import java.util.List;

/**
 * 新版提现记录的Bean
 */


public class MyBalanceBean {


    /**
     * code : 2000
     * message : 请求成功
     * data : [{"status":"2","info":"wechat","serviceCharge":"0.00","value":"1.00","addtime":"2018-12-27 14:31:26","endtime":"0","statusSn":"申请中"},{"status":"2","info":"支付宝","serviceCharge":"0.00","value":"1.00","addtime":"2018-12-26 16:50:58","endtime":"0","statusSn":"申请中"},{"status":"2","info":"支付宝","serviceCharge":"0.00","value":"1.00","addtime":"2018-12-26 16:50:42","endtime":"0","statusSn":"申请中"},{"status":"2","info":"支付宝","serviceCharge":"0.00","value":"1.00","addtime":"2018-12-26 16:47:45","endtime":"0","statusSn":"申请中"},{"status":"2","info":"支付宝","serviceCharge":"0.00","value":"1.00","addtime":"2018-12-26 16:47:36","endtime":"0","statusSn":"申请中"},{"status":"2","info":"支付宝","serviceCharge":"0.00","value":"1.00","addtime":"2018-12-26 16:47:25","endtime":"0","statusSn":"申请中"},{"status":"2","info":"支付宝","serviceCharge":"0.00","value":"1.00","addtime":"2018-12-26 16:44:07","endtime":"0","statusSn":"申请中"},{"status":"2","info":"支付宝","serviceCharge":"0.00","value":"12.00","addtime":"2018-12-26 15:50:19","endtime":"0","statusSn":"申请中"},{"status":"2","info":"支付宝","serviceCharge":"0.00","value":"11.00","addtime":"2018-12-26 15:49:58","endtime":"0","statusSn":"申请中"},{"status":"2","info":"支付宝","serviceCharge":"0.00","value":"11.00","addtime":"2018-12-26 15:49:51","endtime":"0","statusSn":"申请中"}]
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

    public static class DataBean implements Serializable {
        /**
         * status : 2
         * info : wechat
         * serviceCharge : 0.00
         * value : 1.00
         * addtime : 2018-12-27 14:31:26
         * endtime : 0
         * statusSn : 申请中
         */

        private String status;
        private String info;
        private String serviceCharge;
        private String value;
        private String addtime;
        private String endtime;
        private String statusSn;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getServiceCharge() {
            return serviceCharge;
        }

        public void setServiceCharge(String serviceCharge) {
            this.serviceCharge = serviceCharge;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getStatusSn() {
            return statusSn;
        }

        public void setStatusSn(String statusSn) {
            this.statusSn = statusSn;
        }
    }
}
