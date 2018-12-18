package com.byx.xiuboss.xiuboss.Bean;

/**
 * Created by wangwenjie001 on 2018/10/3.
 */

public class MyFragmentBean {


    /**
     * code : 2000
     * data : {"amount":"487.24","broadcast":"1","is_in_business":"1","logo":"https://img.ourdaidai.com/images/2/2018/06/P4p2PzoPHhYpLr2OwDMlPdE411PZP4.png","mobile":"15926881137","role":"店员","title":"索记乡巴佬熟食城","username":"简海燕"}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * amount : 487.24
         * broadcast : 1
         * is_in_business : 1
         * logo : https://img.ourdaidai.com/images/2/2018/06/P4p2PzoPHhYpLr2OwDMlPdE411PZP4.png
         * mobile : 15926881137
         * role : 店员
         * title : 索记乡巴佬熟食城
         * username : 简海燕
         */

        private String amount;
        private String broadcast;
        private String is_in_business;
        private String logo;
        private String mobile;
        private String role;
        private String title;
        private String username;

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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
