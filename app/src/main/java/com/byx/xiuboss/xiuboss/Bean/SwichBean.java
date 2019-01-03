package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by wangwenjie001 on 2018/10/5.
 */

public class SwichBean {

    /**
     * code : 2000
     * message : 请求成功
     * data : [{"title":"醉清风","logo":"https://img.ourdaidai.com/images/2/2018/05/oz0agabSvwY4zb00Zv5u5v0MtUk5a6.jpg","telephone":"13177226191","id":"111","mobile":"13681363157","name":"测试BD"},{"title":"雅斯餐饮部","logo":"https://img.ourdaidai.com/images/2/2018/06/OwwrZDfj3W0ffFJBbddwWb10EbBG33.jpg","telephone":"18062266301","id":"128","mobile":"18034103322","name":"李振虎"}]
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
         * title : 醉清风
         * logo : https://img.ourdaidai.com/images/2/2018/05/oz0agabSvwY4zb00Zv5u5v0MtUk5a6.jpg
         * telephone : 13177226191
         * id : 111
         * mobile : 13681363157
         * name : 测试BD
         */

        private String title;
        private String logo;
        private String telephone;
        private String id;
        private String mobile;
        private String name;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
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

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
