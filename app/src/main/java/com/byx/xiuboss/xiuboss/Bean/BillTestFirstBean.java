package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

public class BillTestFirstBean {


    /**
     * code : 2000
     * message : 请求成功
     * data : [{"id":"12907","addtime":"1544236280","pay_type":"credit","price":"447.43","avatar":"http://thirdwx.qlogo.cn/mmopen/GpLragm6RLsz3YWNdLkz7Nv6yyciboib0Tb7iajuyRR21ibzae4icZ300WgCBeNcIx1XjLcYX9bACbWP6HN61H2Uy9A/132","nickname":"不恋那首歌","cid":"62","money_total":"0.00"},{"id":"12905","addtime":"1544236176","pay_type":"credit","price":"1","avatar":"http://thirdwx.qlogo.cn/mmopen/GpLragm6RLsz3YWNdLkz7Nv6yyciboib0Tb7iajuyRR21ibzae4icZ300WgCBeNcIx1XjLcYX9bACbWP6HN61H2Uy9A/132","nickname":"不恋那首歌","cid":"61","money_total":"0.00"},{"id":"12899","addtime":"1544236093","pay_type":"credit","price":"1000","avatar":"http://thirdwx.qlogo.cn/mmopen/GpLragm6RLsz3YWNdLkz7Nv6yyciboib0Tb7iajuyRR21ibzae4icZ300WgCBeNcIx1XjLcYX9bACbWP6HN61H2Uy9A/132","nickname":"不恋那首歌","cid":"60","money_total":"0.00"},{"id":"12897","addtime":"1544236001","pay_type":"credit","price":"100","avatar":"http://thirdwx.qlogo.cn/mmopen/GpLragm6RLsz3YWNdLkz7Nv6yyciboib0Tb7iajuyRR21ibzae4icZ300WgCBeNcIx1XjLcYX9bACbWP6HN61H2Uy9A/132","nickname":"不恋那首歌","cid":"59","money_total":"0.00"}]
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
         * id : 12907
         * addtime : 1544236280
         * pay_type : credit
         * price : 447.43
         * avatar : http://thirdwx.qlogo.cn/mmopen/GpLragm6RLsz3YWNdLkz7Nv6yyciboib0Tb7iajuyRR21ibzae4icZ300WgCBeNcIx1XjLcYX9bACbWP6HN61H2Uy9A/132
         * nickname : 不恋那首歌
         * cid : 62
         * money_total : 0.00
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
