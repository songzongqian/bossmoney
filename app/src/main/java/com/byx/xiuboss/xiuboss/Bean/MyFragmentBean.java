package com.byx.xiuboss.xiuboss.Bean;



public class MyFragmentBean {


    /**
     * code : 2000
     * message : 请求成功
     * data : {"username":"王文杰","mobile":"18301194611","role":"店员","title":"醉清风","logo":"https://img.ourdaidai.com/images/2/2018/05/oz0agabSvwY4zb00Zv5u5v0MtUk5a6.jpg","amount":"11,358.04","broadcast":"1","is_in_business":"1","returnratio":"56"}
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
         * username : 王文杰
         * mobile : 18301194611
         * role : 店员
         * title : 醉清风
         * logo : https://img.ourdaidai.com/images/2/2018/05/oz0agabSvwY4zb00Zv5u5v0MtUk5a6.jpg
         * amount : 11,358.04
         * broadcast : 1
         * is_in_business : 1
         * returnratio : 56
         */

        private String username;
        private String mobile;
        private String role;
        private String title;
        private String logo;
        private String amount;
        private String broadcast;
        private String is_in_business;
        private String returnratio;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBroadcast() {
            return broadcast;
        }

        public void setBroadcast(String broadcast) {
            this.broadcast = broadcast;
        }

        public String getIs_in_business() {
            return is_in_business;
        }

        public void setIs_in_business(String is_in_business) {
            this.is_in_business = is_in_business;
        }

        public String getReturnratio() {
            return returnratio;
        }

        public void setReturnratio(String returnratio) {
            this.returnratio = returnratio;
        }
    }
}
