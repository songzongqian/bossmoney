package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by wangwenjie001 on 2018/9/3.
 */

public class LoginBean {


    /**
     * code : 2000
     * message : 登陆成功
     * data : {"id":"339","uniacid":"2","title":"王文杰","nickname":"一厢情愿就得愿赌服输","openid":"o8kgz0mISiElLVlFXO_qt_Exd1js","mobile":"18301194611","password":"1a16e4dbc08e0eac3ddffb01e64322f6","salt":"iBXpPS","status":"1","addtime":"1535248028","avatar":"http://thirdwx.qlogo.cn/mmopen/GpLragm6RLum7KU5Jc5uWyo9NZPTee6bVosAnbmiaicMKibUbEZDdG6JyPibEjuictOLFMa4Q9XdUZAZwWS9HlAgAHA/132","token":"ogzftGRRNt1zYTMwAMRYffagwMFzGIMz","updatetime":0,"openid_wxapp":0,"agentid":0,"jgpush":"1","hash":"1a16e4dbc08e0eac3ddffb01e64322f6","mycookie":"eyJpZCI6IjMzOSIsInVuaWFjaWQiOiIyIiwidGl0bGUiOiJcdTczOGJcdTY1ODdcdTY3NzAiLCJuaWNrbmFtZSI6Ilx1NGUwMFx1NTNhMlx1NjBjNVx1NjEzZlx1NWMzMVx1NWY5N1x1NjEzZlx1OGQ0Y1x1NjcwZFx1OGY5MyIsIm9wZW5pZCI6Im84a2d6MG1JU2lFbExWbEZYT19xdF9FeGQxanMiLCJtb2JpbGUiOiIxODMwMTE5NDYxMSIsInBhc3N3b3JkIjoiMWExNmU0ZGJjMDhlMGVhYzNkZGZmYjAxZTY0MzIyZjYiLCJzYWx0IjoiaUJYcFBTIiwic3RhdHVzIjoiMSIsImFkZHRpbWUiOiIxNTM1MjQ4MDI4IiwiYXZhdGFyIjoiaHR0cDpcL1wvdGhpcmR3eC5xbG9nby5jblwvbW1vcGVuXC9HcExyYWdtNlJMdW03S1U1SmM1dVd5bzlOWlBUZWU2YlZvc0FuYm1pYWljTUtpYlViRVpEZEc2SnlQaWJFanVpY3RPTEZNYTRROVhkVVpBWndXUzlIbEFnQUhBXC8xMzIiLCJ0b2tlbiI6Im9nemZ0R1JSTnQxellUTXdBTVJZZmZhZ3dNRnpHSU16IiwidXBkYXRldGltZSI6IjAiLCJvcGVuaWRfd3hhcHAiOiIiLCJhZ2VudGlkIjoiMCIsImpncHVzaCI6IjEiLCJoYXNoIjoiMWExNmU0ZGJjMDhlMGVhYzNkZGZmYjAxZTY0MzIyZjYifQ==","sid":[{"sid":"111","mobile":"17771234299","title":"醉清风","managerMobile":"13681363157"},{"sid":"128","mobile":"18062266301","title":"雅斯餐饮部","managerMobile":"18034103322"}]}
     * IM : {"code":2000,"message":"用户已被注册","data":{"avatar":"http://thirdwx.qlogo.cn/mmopen/GpLragm6RLum7KU5Jc5uWyo9NZPTee6bVosAnbmiaicMKibUbEZDdG6JyPibEjuictOLFMa4Q9XdUZAZwWS9HlAgAHA/132"}}
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
         * id : 339
         * uniacid : 2
         * title : 王文杰
         * nickname : 一厢情愿就得愿赌服输
         * openid : o8kgz0mISiElLVlFXO_qt_Exd1js
         * mobile : 18301194611
         * password : 1a16e4dbc08e0eac3ddffb01e64322f6
         * salt : iBXpPS
         * status : 1
         * addtime : 1535248028
         * avatar : http://thirdwx.qlogo.cn/mmopen/GpLragm6RLum7KU5Jc5uWyo9NZPTee6bVosAnbmiaicMKibUbEZDdG6JyPibEjuictOLFMa4Q9XdUZAZwWS9HlAgAHA/132
         * token : ogzftGRRNt1zYTMwAMRYffagwMFzGIMz
         * updatetime : 0
         * openid_wxapp : 0
         * agentid : 0
         * jgpush : 1
         * hash : 1a16e4dbc08e0eac3ddffb01e64322f6
         * mycookie : eyJpZCI6IjMzOSIsInVuaWFjaWQiOiIyIiwidGl0bGUiOiJcdTczOGJcdTY1ODdcdTY3NzAiLCJuaWNrbmFtZSI6Ilx1NGUwMFx1NTNhMlx1NjBjNVx1NjEzZlx1NWMzMVx1NWY5N1x1NjEzZlx1OGQ0Y1x1NjcwZFx1OGY5MyIsIm9wZW5pZCI6Im84a2d6MG1JU2lFbExWbEZYT19xdF9FeGQxanMiLCJtb2JpbGUiOiIxODMwMTE5NDYxMSIsInBhc3N3b3JkIjoiMWExNmU0ZGJjMDhlMGVhYzNkZGZmYjAxZTY0MzIyZjYiLCJzYWx0IjoiaUJYcFBTIiwic3RhdHVzIjoiMSIsImFkZHRpbWUiOiIxNTM1MjQ4MDI4IiwiYXZhdGFyIjoiaHR0cDpcL1wvdGhpcmR3eC5xbG9nby5jblwvbW1vcGVuXC9HcExyYWdtNlJMdW03S1U1SmM1dVd5bzlOWlBUZWU2YlZvc0FuYm1pYWljTUtpYlViRVpEZEc2SnlQaWJFanVpY3RPTEZNYTRROVhkVVpBWndXUzlIbEFnQUhBXC8xMzIiLCJ0b2tlbiI6Im9nemZ0R1JSTnQxellUTXdBTVJZZmZhZ3dNRnpHSU16IiwidXBkYXRldGltZSI6IjAiLCJvcGVuaWRfd3hhcHAiOiIiLCJhZ2VudGlkIjoiMCIsImpncHVzaCI6IjEiLCJoYXNoIjoiMWExNmU0ZGJjMDhlMGVhYzNkZGZmYjAxZTY0MzIyZjYifQ==
         * sid : [{"sid":"111","mobile":"17771234299","title":"醉清风","managerMobile":"13681363157"},{"sid":"128","mobile":"18062266301","title":"雅斯餐饮部","managerMobile":"18034103322"}]
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
        private int updatetime;
        private String openid_wxapp;
        private int agentid;
        private String jgpush;
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

        public int getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(int updatetime) {
            this.updatetime = updatetime;
        }

        public String getOpenid_wxapp() {
            return openid_wxapp;
        }

        public void setOpenid_wxapp(String openid_wxapp) {
            this.openid_wxapp = openid_wxapp;
        }

        public int getAgentid() {
            return agentid;
        }

        public void setAgentid(int agentid) {
            this.agentid = agentid;
        }

        public String getJgpush() {
            return jgpush;
        }

        public void setJgpush(String jgpush) {
            this.jgpush = jgpush;
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
             * sid : 111
             * mobile : 17771234299
             * title : 醉清风
             * managerMobile : 13681363157
             */

            private String sid;
            private String mobile;
            private String title;
            private String managerMobile;

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getManagerMobile() {
                return managerMobile;
            }

            public void setManagerMobile(String managerMobile) {
                this.managerMobile = managerMobile;
            }
        }
    }

    public static class IMBean {
        /**
         * code : 2000
         * message : 用户已被注册
         * data : {"avatar":"http://thirdwx.qlogo.cn/mmopen/GpLragm6RLum7KU5Jc5uWyo9NZPTee6bVosAnbmiaicMKibUbEZDdG6JyPibEjuictOLFMa4Q9XdUZAZwWS9HlAgAHA/132"}
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
             * avatar : http://thirdwx.qlogo.cn/mmopen/GpLragm6RLum7KU5Jc5uWyo9NZPTee6bVosAnbmiaicMKibUbEZDdG6JyPibEjuictOLFMa4Q9XdUZAZwWS9HlAgAHA/132
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
