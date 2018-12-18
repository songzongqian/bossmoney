package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by wwj on 2018/9/1.
 */

public class TurnoverData {


    /**
     * code : 2000
     * message : 请求成功
     * data : [{"id":"3971","addtime":"1535732089","pay_type":"credit","price":"30","avatar":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqZJ75ERpTVcqmxQ163SxiajPX1hdqpgPxKjYcw4SMZ9wiav36AeHLw8Kjb7SToQ0vLbgEWsC9T7TQA/132","nickname":"一厢情愿就得愿赌服输","cid":null,"money_total":"0"},{"id":"3970","addtime":"1535731923","pay_type":"credit","price":"20","avatar":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqZJ75ERpTVcqmxQ163SxiajPX1hdqpgPxKjYcw4SMZ9wiav36AeHLw8Kjb7SToQ0vLbgEWsC9T7TQA/132","nickname":"一厢情愿就得愿赌服输","cid":null,"money_total":"0"},{"id":"3969","addtime":"1535731895","pay_type":"credit","price":"20","avatar":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqZJ75ERpTVcqmxQ163SxiajPX1hdqpgPxKjYcw4SMZ9wiav36AeHLw8Kjb7SToQ0vLbgEWsC9T7TQA/132","nickname":"一厢情愿就得愿赌服输","cid":null,"money_total":"0"}]
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
         * id : 3971
         * addtime : 1535732089
         * pay_type : credit
         * price : 30
         * avatar : https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqZJ75ERpTVcqmxQ163SxiajPX1hdqpgPxKjYcw4SMZ9wiav36AeHLw8Kjb7SToQ0vLbgEWsC9T7TQA/132
         * nickname : 一厢情愿就得愿赌服输
         * cid : null
         * money_total : 0
         */

        private String id;
        private String addtime;
        private String pay_type;
        private String price;
        private String avatar;
        private String nickname;
        private Object cid;
        private String money_total;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getCid() {
            return cid;
        }

        public void setCid(Object cid) {
            this.cid = cid;
        }

        public String getMoney_total() {
            return money_total;
        }

        public void setMoney_total(String money_total) {
            this.money_total = money_total;
        }
    }
}
