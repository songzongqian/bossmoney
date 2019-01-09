package com.byx.xiuboss.xiuboss.Bean;

import java.util.List;

/**
 * Created by night_slight on 2019/1/2.
 */

public class RewardInfo {

    /**
     * code : 2000
     * message : ok
     * data : {"stepTitle":"x11","creditScore":"350","returnCash":"18.30元","nextStepTitle":"x12","nextCreditScore":"400","nextReturnCash":"25元","shareTasks":[{"returnCash":"20¥","storeName":"张亮麻辣烫","storeAddress":"学院路3号","ssid":"111"},{"returnCash":"20¥","storeName":"张亮麻辣烫","storeAddress":"学院路3号","ssid":"111"}]}
     * result : true
     * msg : ok
     */

    private int code;
    private String message;
    private DataBean data;
    private String result;
    private String msg;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * stepTitle : x11
         * creditScore : 350
         * returnCash : 18.30元
         * nextStepTitle : x12
         * nextCreditScore : 400
         * nextReturnCash : 25元
         * shareTasks : [{"returnCash":"20¥","storeName":"张亮麻辣烫","storeAddress":"学院路3号","ssid":"111"},{"returnCash":"20¥","storeName":"张亮麻辣烫","storeAddress":"学院路3号","ssid":"111"}]
         */
        private String reciveStatus;
        private String curScore;
        private String shareReturnCash;
        private String signtime;
        private String stepTitle;
        private String creditScore;
        private String returnCash;
        private String nextStepTitle;
        private String nextCreditScore;
        private String nextReturnCash;
        private String nextGetCashTime;
        private String storeBonus;
        private List<ShareTasksBean> shareTasks;

        public String getStoreBonus() {
            return storeBonus;
        }

        public void setStoreBonus(String storeBonus) {
            this.storeBonus = storeBonus;
        }

        public String getNextGetCashTime() {
            return nextGetCashTime;
        }

        public void setNextGetCashTime(String nextGetCashTime) {
            this.nextGetCashTime = nextGetCashTime;
        }

        public String getSigntime() {
            return signtime;
        }

        public void setSigntime(String signtime) {
            this.signtime = signtime;
        }

        public String getReciveStatus() {
            return reciveStatus;
        }

        public void setReciveStatus(String reciveStatus) {
            this.reciveStatus = reciveStatus;
        }

        public String getCurScore() {
            return curScore;
        }

        public void setCurScore(String curScore) {
            this.curScore = curScore;
        }

        public String getShareReturnCash() {
            return shareReturnCash;
        }

        public void setShareReturnCash(String shareReturnCash) {
            this.shareReturnCash = shareReturnCash;
        }

        public String getStepTitle() {
            return stepTitle;
        }

        public void setStepTitle(String stepTitle) {
            this.stepTitle = stepTitle;
        }

        public String getCreditScore() {
            return creditScore;
        }

        public void setCreditScore(String creditScore) {
            this.creditScore = creditScore;
        }

        public String getReturnCash() {
            return returnCash;
        }

        public void setReturnCash(String returnCash) {
            this.returnCash = returnCash;
        }

        public String getNextStepTitle() {
            return nextStepTitle;
        }

        public void setNextStepTitle(String nextStepTitle) {
            this.nextStepTitle = nextStepTitle;
        }

        public String getNextCreditScore() {
            return nextCreditScore;
        }

        public void setNextCreditScore(String nextCreditScore) {
            this.nextCreditScore = nextCreditScore;
        }

        public String getNextReturnCash() {
            return nextReturnCash;
        }

        public void setNextReturnCash(String nextReturnCash) {
            this.nextReturnCash = nextReturnCash;
        }

        public List<ShareTasksBean> getShareTasks() {
            return shareTasks;
        }

        public void setShareTasks(List<ShareTasksBean> shareTasks) {
            this.shareTasks = shareTasks;
        }

        public static class ShareTasksBean {
            /**
             * returnCash : 20¥
             * storeName : 张亮麻辣烫
             * storeAddress : 学院路3号
             * ssid : 111
             */

            private String returnCash;
            private String storeName;
            private String storeAddress;
            private String ssid;
            private String openKey;

            public String getOpenKey() {
                return openKey;
            }

            public void setOpenKey(String openKey) {
                this.openKey = openKey;
            }

            public String getReturnCash() {
                return returnCash;
            }

            public void setReturnCash(String returnCash) {
                this.returnCash = returnCash;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStoreAddress() {
                return storeAddress;
            }

            public void setStoreAddress(String storeAddress) {
                this.storeAddress = storeAddress;
            }

            public String getSsid() {
                return ssid;
            }

            public void setSsid(String ssid) {
                this.ssid = ssid;
            }
        }
    }
}
