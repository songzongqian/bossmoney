package com.byx.xiuboss.xiuboss.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangwenjie001 on 2018/10/4.
 */

public class MyBalanceBean {


    /**
     * code : 2000
     * message : 查询成功
     * data : {"amount":"144,556.78","data":[{"channel":"银行卡","get_fee":"0.03","status":"2","registerTime":"2018-10-10 16:39:20","addtime":"1539160760","endTime":"0"},{"channel":"银行卡","get_fee":"12.00","status":"2","registerTime":"2018-10-10 16:39:03","addtime":"1539160743","endTime":"0"},{"channel":"weixin","get_fee":"7000.00","status":"3","registerTime":"2018-10-10 15:02:03","addtime":"1539154923","endTime":"2018-10-10 15:45:35"},{"get_fee":"150","addtime":"1539150181","id":"10","channel":"签到奖励","status":0,"registerTime":"2018-10-10 13:43:01"},{"get_fee":"20","addtime":"1539150054","id":"9","channel":"签到奖励","status":0,"registerTime":"2018-10-10 13:40:54"},{"channel":"银行卡","get_fee":"1.00","status":"2","registerTime":"2018-10-10 09:46:49","addtime":"1539136009","endTime":"0"},{"get_fee":"87","addtime":"1539084455","id":"8","channel":"签到奖励","status":0,"registerTime":"2018-10-09 19:27:35"},{"get_fee":"39.9","addtime":"1539081967","id":"7","channel":"签到奖励","status":0,"registerTime":"2018-10-09 18:46:07"},{"channel":"支付宝","get_fee":"1.00","status":"2","registerTime":"2018-10-09 18:42:27","addtime":"1539081747","endTime":"0"},{"channel":"银行卡","get_fee":"1.00","status":"2","registerTime":"2018-10-09 18:09:37","addtime":"1539079777","endTime":"0"},{"channel":"银行卡","get_fee":"1.00","status":"2","registerTime":"2018-10-09 18:04:55","addtime":"1539079495","endTime":"0"},{"channel":"银行卡","get_fee":"12.00","status":"2","registerTime":"2018-10-09 18:02:21","addtime":"1539079341","endTime":"0"},{"channel":"银行卡","get_fee":"11.00","status":"2","registerTime":"2018-10-09 18:02:03","addtime":"1539079323","endTime":"0"},{"channel":"银行卡","get_fee":"0.01","status":"2","registerTime":"2018-10-09 17:53:25","addtime":"1539078805","endTime":"0"},{"get_fee":"39.9","addtime":"1538995757","id":"6","channel":"签到奖励","status":0,"registerTime":"2018-10-08 18:49:17"},{"get_fee":"39.9","addtime":"1538995318","id":"5","channel":"签到奖励","status":0,"registerTime":"2018-10-08 18:41:58"},{"get_fee":"12","addtime":"1538979802","id":"3","channel":"签到奖励","status":0,"registerTime":"2018-10-08 14:23:22"},{"get_fee":"12","addtime":"1538979766","id":"2","channel":"签到奖励","status":0,"registerTime":"2018-10-08 14:22:46"},{"get_fee":"12","addtime":"1538970263","id":"1","channel":"签到奖励","status":0,"registerTime":"2018-10-08 11:44:23"}]}
     */

    private int code;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * amount : 144,556.78
         * data : [{"channel":"银行卡","get_fee":"0.03","status":"2","registerTime":"2018-10-10 16:39:20","addtime":"1539160760","endTime":"0"},{"channel":"银行卡","get_fee":"12.00","status":"2","registerTime":"2018-10-10 16:39:03","addtime":"1539160743","endTime":"0"},{"channel":"weixin","get_fee":"7000.00","status":"3","registerTime":"2018-10-10 15:02:03","addtime":"1539154923","endTime":"2018-10-10 15:45:35"},{"get_fee":"150","addtime":"1539150181","id":"10","channel":"签到奖励","status":0,"registerTime":"2018-10-10 13:43:01"},{"get_fee":"20","addtime":"1539150054","id":"9","channel":"签到奖励","status":0,"registerTime":"2018-10-10 13:40:54"},{"channel":"银行卡","get_fee":"1.00","status":"2","registerTime":"2018-10-10 09:46:49","addtime":"1539136009","endTime":"0"},{"get_fee":"87","addtime":"1539084455","id":"8","channel":"签到奖励","status":0,"registerTime":"2018-10-09 19:27:35"},{"get_fee":"39.9","addtime":"1539081967","id":"7","channel":"签到奖励","status":0,"registerTime":"2018-10-09 18:46:07"},{"channel":"支付宝","get_fee":"1.00","status":"2","registerTime":"2018-10-09 18:42:27","addtime":"1539081747","endTime":"0"},{"channel":"银行卡","get_fee":"1.00","status":"2","registerTime":"2018-10-09 18:09:37","addtime":"1539079777","endTime":"0"},{"channel":"银行卡","get_fee":"1.00","status":"2","registerTime":"2018-10-09 18:04:55","addtime":"1539079495","endTime":"0"},{"channel":"银行卡","get_fee":"12.00","status":"2","registerTime":"2018-10-09 18:02:21","addtime":"1539079341","endTime":"0"},{"channel":"银行卡","get_fee":"11.00","status":"2","registerTime":"2018-10-09 18:02:03","addtime":"1539079323","endTime":"0"},{"channel":"银行卡","get_fee":"0.01","status":"2","registerTime":"2018-10-09 17:53:25","addtime":"1539078805","endTime":"0"},{"get_fee":"39.9","addtime":"1538995757","id":"6","channel":"签到奖励","status":0,"registerTime":"2018-10-08 18:49:17"},{"get_fee":"39.9","addtime":"1538995318","id":"5","channel":"签到奖励","status":0,"registerTime":"2018-10-08 18:41:58"},{"get_fee":"12","addtime":"1538979802","id":"3","channel":"签到奖励","status":0,"registerTime":"2018-10-08 14:23:22"},{"get_fee":"12","addtime":"1538979766","id":"2","channel":"签到奖励","status":0,"registerTime":"2018-10-08 14:22:46"},{"get_fee":"12","addtime":"1538970263","id":"1","channel":"签到奖励","status":0,"registerTime":"2018-10-08 11:44:23"}]
         */

        private String amount;
        private List<DataBean> data;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{
            /**
             * channel : 银行卡
             * get_fee : 0.03
             * status : 2
             * registerTime : 2018-10-10 16:39:20
             * addtime : 1539160760
             * endTime : 0
             * id : 10
             */

            private String channel;
            private String get_fee;
            private String status;
            private String registerTime;
            private String addtime;
            private String endTime;
            private String id;

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public String getGet_fee() {
                return get_fee;
            }

            public void setGet_fee(String get_fee) {
                this.get_fee = get_fee;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getRegisterTime() {
                return registerTime;
            }

            public void setRegisterTime(String registerTime) {
                this.registerTime = registerTime;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
