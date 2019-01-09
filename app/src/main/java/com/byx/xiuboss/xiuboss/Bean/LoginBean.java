package com.byx.xiuboss.xiuboss.Bean;


import java.util.List;

public class LoginBean {


    /**
     * code : 2000
     * message : 登陆成功
     * data : {"id":"674","uniacid":"2","title":"小宋","nickname":"glider","openid":"o8kgz0qCBAohswj3Yp7J2UvAaDjw","mobile":"18612754039","password":"2f9925f733ec92dece7616e565a4bd08","salt":"wMJ6JN","status":"1","addtime":"1544836193","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/vP8UTjY7wag9NHf015xvGhNsiapIbcPtKLMncsTIZKxhcaXl8eegFUm0hOdctWg9kwPKo5nB3Rw6RJbQibH3DJ4w/132","token":"a8a8j9pbTxZMoRaM1qfv9XxsgTUffbuemaGh9RZJs2s","updatetime":0,"openid_wxapp":"oeh8u5TQr_EGBWFaSwdR_HVg3ZwM","agentid":0,"jgpush":"1","mobile2":0,"phone_type":0,"phone_token":0,"hash":"2f9925f733ec92dece7616e565a4bd08","mycookie":"eyJpZCI6IjY3NCIsInVuaWFjaWQiOiIyIiwidGl0bGUiOiJcdTVjMGZcdTViOGIiLCJuaWNrbmFtZSI6ImdsaWRlciIsIm9wZW5pZCI6Im84a2d6MHFDQkFvaHN3ajNZcDdKMlV2QWFEanciLCJtb2JpbGUiOiIxODYxMjc1NDAzOSIsInBhc3N3b3JkIjoiMmY5OTI1ZjczM2VjOTJkZWNlNzYxNmU1NjVhNGJkMDgiLCJzYWx0Ijoid01KNkpOIiwic3RhdHVzIjoiMSIsImFkZHRpbWUiOiIxNTQ0ODM2MTkzIiwiYXZhdGFyIjoiaHR0cDpcL1wvdGhpcmR3eC5xbG9nby5jblwvbW1vcGVuXC92aV8zMlwvdlA4VVRqWTd3YWc5TkhmMDE1eHZHaE5zaWFwSWJjUHRLTE1uY3NUSVpLeGhjYVhsOGVlZ0ZVbTBoT2RjdFdnOWt3UEtvNW5CM1J3NlJKYlFpYkgzREo0d1wvMTMyIiwidG9rZW4iOiJjMmMxN19nNy1XVnJtTWJhb2xlTDJsdGhWOE9BVUplTkp6S1F5ZW13aEx3IiwidXBkYXRldGltZSI6IjAiLCJvcGVuaWRfd3hhcHAiOiJvZWg4dTVUUXJfRUdCV0ZhU3dkUl9IVmczWndNIiwiYWdlbnRpZCI6IjAiLCJqZ3B1c2giOiIxIiwibW9iaWxlMiI6IiIsInBob25lX3R5cGUiOiIiLCJwaG9uZV90b2tlbiI6IiIsImhhc2giOiIyZjk5MjVmNzMzZWM5MmRlY2U3NjE2ZTU2NWE0YmQwOCJ9","sid":[{"sid":"128","managerMobile":"18062266301","title":"雅斯餐饮部","mobile":"15707225671","openKey":"242dGgDVy_Orc7ZtbLicgUzWJuYhbIhKg3L0Eo5GZuQ"},{"sid":"98","managerMobile":"17771234299","title":"雅斯超市","mobile":"0710-3780521","openKey":"ac5fs5Hhgm_NHLe7_l49ZahppX-ylIwUfUKEFZaPDg"},{"sid":"111","managerMobile":"13051786112","title":"醉清风1","mobile":"15971097126","openKey":"77b1QmDobABdJ8qAb2QcdbiGSgiW0i9dX1HUyTSW5Yk"},{"sid":"718","managerMobile":"17771241213","title":"海华副食批发","mobile":"18371004268","openKey":"f511x3Wcg6qxAvNbVuAsBjQ4f-pP6FZ85pMx8Yaz4A4"}]}
     * IM : {"code":2000,"message":"用户已被注册","data":{"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/vP8UTjY7wag9NHf015xvGhNsiapIbcPtKLMncsTIZKxhcaXl8eegFUm0hOdctWg9kwPKo5nB3Rw6RJbQibH3DJ4w/132"}}
     */

    private int code;
    private String message;
    private DataBean data;
    private IMBean IM;

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

    public IMBean getIM() {
        return IM;
    }

    public void setIM(IMBean IM) {
        this.IM = IM;
    }

    public static class DataBean {
        /**
         * id : 674
         * uniacid : 2
         * title : 小宋
         * nickname : glider
         * openid : o8kgz0qCBAohswj3Yp7J2UvAaDjw
         * mobile : 18612754039
         * password : 2f9925f733ec92dece7616e565a4bd08
         * salt : wMJ6JN
         * status : 1
         * addtime : 1544836193
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/vP8UTjY7wag9NHf015xvGhNsiapIbcPtKLMncsTIZKxhcaXl8eegFUm0hOdctWg9kwPKo5nB3Rw6RJbQibH3DJ4w/132
         * token : a8a8j9pbTxZMoRaM1qfv9XxsgTUffbuemaGh9RZJs2s
         * updatetime : 0
         * openid_wxapp : oeh8u5TQr_EGBWFaSwdR_HVg3ZwM
         * agentid : 0
         * jgpush : 1
         * mobile2 : 0
         * phone_type : 0
         * phone_token : 0
         * hash : 2f9925f733ec92dece7616e565a4bd08
         * mycookie : eyJpZCI6IjY3NCIsInVuaWFjaWQiOiIyIiwidGl0bGUiOiJcdTVjMGZcdTViOGIiLCJuaWNrbmFtZSI6ImdsaWRlciIsIm9wZW5pZCI6Im84a2d6MHFDQkFvaHN3ajNZcDdKMlV2QWFEanciLCJtb2JpbGUiOiIxODYxMjc1NDAzOSIsInBhc3N3b3JkIjoiMmY5OTI1ZjczM2VjOTJkZWNlNzYxNmU1NjVhNGJkMDgiLCJzYWx0Ijoid01KNkpOIiwic3RhdHVzIjoiMSIsImFkZHRpbWUiOiIxNTQ0ODM2MTkzIiwiYXZhdGFyIjoiaHR0cDpcL1wvdGhpcmR3eC5xbG9nby5jblwvbW1vcGVuXC92aV8zMlwvdlA4VVRqWTd3YWc5TkhmMDE1eHZHaE5zaWFwSWJjUHRLTE1uY3NUSVpLeGhjYVhsOGVlZ0ZVbTBoT2RjdFdnOWt3UEtvNW5CM1J3NlJKYlFpYkgzREo0d1wvMTMyIiwidG9rZW4iOiJjMmMxN19nNy1XVnJtTWJhb2xlTDJsdGhWOE9BVUplTkp6S1F5ZW13aEx3IiwidXBkYXRldGltZSI6IjAiLCJvcGVuaWRfd3hhcHAiOiJvZWg4dTVUUXJfRUdCV0ZhU3dkUl9IVmczWndNIiwiYWdlbnRpZCI6IjAiLCJqZ3B1c2giOiIxIiwibW9iaWxlMiI6IiIsInBob25lX3R5cGUiOiIiLCJwaG9uZV90b2tlbiI6IiIsImhhc2giOiIyZjk5MjVmNzMzZWM5MmRlY2U3NjE2ZTU2NWE0YmQwOCJ9
         * sid : [{"sid":"128","managerMobile":"18062266301","title":"雅斯餐饮部","mobile":"15707225671","openKey":"242dGgDVy_Orc7ZtbLicgUzWJuYhbIhKg3L0Eo5GZuQ"},{"sid":"98","managerMobile":"17771234299","title":"雅斯超市","mobile":"0710-3780521","openKey":"ac5fs5Hhgm_NHLe7_l49ZahppX-ylIwUfUKEFZaPDg"},{"sid":"111","managerMobile":"13051786112","title":"醉清风1","mobile":"15971097126","openKey":"77b1QmDobABdJ8qAb2QcdbiGSgiW0i9dX1HUyTSW5Yk"},{"sid":"718","managerMobile":"17771241213","title":"海华副食批发","mobile":"18371004268","openKey":"f511x3Wcg6qxAvNbVuAsBjQ4f-pP6FZ85pMx8Yaz4A4"}]
         */

        private String id;
        private String uniacid;
        private String title;
        private String nickname;
        private String openid;
        private String mobile;
        private String password;
        private String salt;
        private String status;
        private String addtime;
        private String avatar;
        private String token;
        private String updatetime;
        private String openid_wxapp;
        private String agentid;
        private String jgpush;
        private String mobile2;
        private String phone_type;
        private String phone_token;
        private String hash;
        private String mycookie;
        private List<SidBean> sid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUniacid() {
            return uniacid;
        }

        public void setUniacid(String uniacid) {
            this.uniacid = uniacid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getOpenid_wxapp() {
            return openid_wxapp;
        }

        public void setOpenid_wxapp(String openid_wxapp) {
            this.openid_wxapp = openid_wxapp;
        }

        public String getAgentid() {
            return agentid;
        }

        public void setAgentid(String agentid) {
            this.agentid = agentid;
        }

        public String getJgpush() {
            return jgpush;
        }

        public void setJgpush(String jgpush) {
            this.jgpush = jgpush;
        }

        public String getMobile2() {
            return mobile2;
        }

        public void setMobile2(String mobile2) {
            this.mobile2 = mobile2;
        }

        public String getPhone_type() {
            return phone_type;
        }

        public void setPhone_type(String phone_type) {
            this.phone_type = phone_type;
        }

        public String getPhone_token() {
            return phone_token;
        }

        public void setPhone_token(String phone_token) {
            this.phone_token = phone_token;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getMycookie() {
            return mycookie;
        }

        public void setMycookie(String mycookie) {
            this.mycookie = mycookie;
        }

        public List<SidBean> getSid() {
            return sid;
        }

        public void setSid(List<SidBean> sid) {
            this.sid = sid;
        }

        public static class SidBean {
            /**
             * sid : 128
             * managerMobile : 18062266301
             * title : 雅斯餐饮部
             * mobile : 15707225671
             * openKey : 242dGgDVy_Orc7ZtbLicgUzWJuYhbIhKg3L0Eo5GZuQ
             */

            private String sid;
            private String managerMobile;
            private String title;
            private String mobile;
            private String openKey;

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getManagerMobile() {
                return managerMobile;
            }

            public void setManagerMobile(String managerMobile) {
                this.managerMobile = managerMobile;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getOpenKey() {
                return openKey;
            }

            public void setOpenKey(String openKey) {
                this.openKey = openKey;
            }
        }
    }

    public static class IMBean {
        /**
         * code : 2000
         * message : 用户已被注册
         * data : {"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/vP8UTjY7wag9NHf015xvGhNsiapIbcPtKLMncsTIZKxhcaXl8eegFUm0hOdctWg9kwPKo5nB3Rw6RJbQibH3DJ4w/132"}
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
             * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/vP8UTjY7wag9NHf015xvGhNsiapIbcPtKLMncsTIZKxhcaXl8eegFUm0hOdctWg9kwPKo5nB3Rw6RJbQibH3DJ4w/132
             */

            private String avatar;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
