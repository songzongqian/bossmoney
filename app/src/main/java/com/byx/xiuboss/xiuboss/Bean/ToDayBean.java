package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by wangwenjie001 on 2018/10/12.
 */

public class ToDayBean {

    /**
     * code : 2000
     * message : 请求成功
     * data : [{"id":"8056","addtime":"1539311826","pay_type":"credit","price":"0.01","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKUA1Nles5pr4kxSWZiaiaRTlZvwXxZHZT1KtzD1DzCGtX1DpxRygbk8caf8suXUgHEiaFfXjficFzmHQ/132","nickname":"success_flower","cid":null,"money_total":"0"}]
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
         * id : 8056
         * addtime : 1539311826
         * pay_type : credit
         * price : 0.01
         * avatar : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKUA1Nles5pr4kxSWZiaiaRTlZvwXxZHZT1KtzD1DzCGtX1DpxRygbk8caf8suXUgHEiaFfXjficFzmHQ/132
         * nickname : success_flower
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
