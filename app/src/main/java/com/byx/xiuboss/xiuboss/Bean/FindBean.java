package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

public class FindBean {

    /**
     * code : 2000
     * data : [{"icon":"http://upload.mnw.cn/2017/0814/1502698443566.jpg","title":"克博最帅！","url":"https://www.baidu.com/"}]
     * message : ok
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
         * icon : http://upload.mnw.cn/2017/0814/1502698443566.jpg
         * title : 克博最帅！
         * url : https://www.baidu.com/
         */

        private String icon;
        private String title;
        private String url;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
