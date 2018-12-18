package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by wangwenjie001 on 2018/8/31.
 */

public class XiangQingBean {


    /**
     * code : 2000
     * message : 请求成功
     * data : [{"id":"3886","addtime":"1535696072","pay_type":"credit","price":"33","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLmBYq5IkXzE3C0miaickQ1J4xcVd4PAV1ItcrDKWPSmsqxQoYzbOPMWx0b0SUWDlVRAulBdW17fgQA/132","nickname":"呼啦 呼啦 啦 啦 啦","cid":"841","money_total":"6.60"}]
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
         * id : 3886
         * addtime : 1535696072
         * pay_type : credit
         * price : 33
         * avatar : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLmBYq5IkXzE3C0miaickQ1J4xcVd4PAV1ItcrDKWPSmsqxQoYzbOPMWx0b0SUWDlVRAulBdW17fgQA/132
         * nickname : 呼啦 呼啦 啦 啦 啦
         * cid : 841
         * money_total : 6.60
         */

        private String id;
        private String addtime;
        private String pay_type;
        private String price;
        private String avatar;
        private String nickname;
        private String cid;
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

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
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
