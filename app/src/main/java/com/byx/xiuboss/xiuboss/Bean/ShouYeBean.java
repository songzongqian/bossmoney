package com.byx.xiuboss.xiuboss.Bean;

/**
 * Created by TauchMe on 2018/7/29.
 */

public class ShouYeBean {


    /**
     * data : {"up":"0.00","down":"10711.00","sum":"10711.00","title":"醉清风","is_in_business":"1"}
     * mes : 成功
     * status : 1
     */

    private DataBean data;
    private String mes;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * up : 0.00
         * down : 10711.00
         * sum : 10711.00
         * title : 醉清风
         * is_in_business : 1
         */

        private String up;
        private String down;
        private String sum;
        private String title;
        private String is_in_business;

        public String getUp() {
            return up;
        }

        public void setUp(String up) {
            this.up = up;
        }

        public String getDown() {
            return down;
        }

        public void setDown(String down) {
            this.down = down;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIs_in_business() {
            return is_in_business;
        }

        public void setIs_in_business(String is_in_business) {
            this.is_in_business = is_in_business;
        }
    }
}
