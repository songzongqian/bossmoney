package com.byx.xiuboss.xiuboss.Bean;



public class HomeBean {


    /**
     * code : 2000
     * message : 请求成功
     * data : {"fee":{"orderTotalFee":"0.00","payBillTotalFee":"2.00","orderCustomer":"0","payBillCustomer":"1","returnTotalFee":"2.00","total_fee":"2.00","customer":"1"},"todaySum":1,"todayFee":"2","yesterSum":0,"yesterFee":"0","currentGrade":{"f2":"200","f3":"0.0009","f4":"0.3","id":"1","fee":"200"},"prevGrade":{"id":"0","f2":"0","f3":"0","f4":"0"},"signtime":"0","nextGrade":{"f2":"500","f3":"0.00093","f4":"0.465","id":"2","fee":"500"},"amount":{"amount":"300.00","signtime":"0","amountFee":"300.00"},"monthMoney":"26748.50","monthFee":"26,748.5","tixian_fee":"1","shoukuan_fee":"1000","nextDifference":"200.00","storeBonus":0,"pop_up_text":"1.1.3","pop_up_status":1,"pop_up_settles":"优化首页签到和等级展示。\r\n 新增登录后语音提示领取奖励。 \r\n新增提现到账后推送语音提醒。 \r\n新增新入驻的商户可以领取新手大礼包。\r\n 新增删除提现方式功能。\r\n 修改新的手续费扣除方案。"}
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
         * fee : {"orderTotalFee":"0.00","payBillTotalFee":"2.00","orderCustomer":"0","payBillCustomer":"1","returnTotalFee":"2.00","total_fee":"2.00","customer":"1"}
         * todaySum : 1
         * todayFee : 2
         * yesterSum : 0
         * yesterFee : 0
         * currentGrade : {"f2":"200","f3":"0.0009","f4":"0.3","id":"1","fee":"200"}
         * prevGrade : {"id":"0","f2":"0","f3":"0","f4":"0"}
         * signtime : 0
         * nextGrade : {"f2":"500","f3":"0.00093","f4":"0.465","id":"2","fee":"500"}
         * amount : {"amount":"300.00","signtime":"0","amountFee":"300.00"}
         * monthMoney : 26748.50
         * monthFee : 26,748.5
         * tixian_fee : 1
         * shoukuan_fee : 1000
         * nextDifference : 200.00
         * storeBonus : 0
         * pop_up_text : 1.1.3
         * pop_up_status : 1
         * pop_up_settles : 优化首页签到和等级展示。
         新增登录后语音提示领取奖励。
         新增提现到账后推送语音提醒。
         新增新入驻的商户可以领取新手大礼包。
         新增删除提现方式功能。
         修改新的手续费扣除方案。
         */

        private FeeBean fee;
        private String todaySum;
        private String todayFee;
        private String yesterSum;
        private String yesterFee;
        private CurrentGradeBean currentGrade;
        private PrevGradeBean prevGrade;
        private String signtime;
        private NextGradeBean nextGrade;
        private AmountBean amount;
        private String monthMoney;
        private String monthFee;
        private String tixian_fee;
        private String shoukuan_fee;
        private String nextDifference;
        private String storeBonus;
        private String pop_up_text;
        private String pop_up_status;
        private String pop_up_settles;

        public FeeBean getFee() {
            return fee;
        }

        public void setFee(FeeBean fee) {
            this.fee = fee;
        }

        public String getTodaySum() {
            return todaySum;
        }

        public void setTodaySum(String todaySum) {
            this.todaySum = todaySum;
        }

        public String getTodayFee() {
            return todayFee;
        }

        public void setTodayFee(String todayFee) {
            this.todayFee = todayFee;
        }

        public String getYesterSum() {
            return yesterSum;
        }

        public void setYesterSum(String yesterSum) {
            this.yesterSum = yesterSum;
        }

        public String getYesterFee() {
            return yesterFee;
        }

        public void setYesterFee(String yesterFee) {
            this.yesterFee = yesterFee;
        }

        public CurrentGradeBean getCurrentGrade() {
            return currentGrade;
        }

        public void setCurrentGrade(CurrentGradeBean currentGrade) {
            this.currentGrade = currentGrade;
        }

        public PrevGradeBean getPrevGrade() {
            return prevGrade;
        }

        public void setPrevGrade(PrevGradeBean prevGrade) {
            this.prevGrade = prevGrade;
        }

        public String getSigntime() {
            return signtime;
        }

        public void setSigntime(String signtime) {
            this.signtime = signtime;
        }

        public NextGradeBean getNextGrade() {
            return nextGrade;
        }

        public void setNextGrade(NextGradeBean nextGrade) {
            this.nextGrade = nextGrade;
        }

        public AmountBean getAmount() {
            return amount;
        }

        public void setAmount(AmountBean amount) {
            this.amount = amount;
        }

        public String getMonthMoney() {
            return monthMoney;
        }

        public void setMonthMoney(String monthMoney) {
            this.monthMoney = monthMoney;
        }

        public String getMonthFee() {
            return monthFee;
        }

        public void setMonthFee(String monthFee) {
            this.monthFee = monthFee;
        }

        public String getTixian_fee() {
            return tixian_fee;
        }

        public void setTixian_fee(String tixian_fee) {
            this.tixian_fee = tixian_fee;
        }

        public String getShoukuan_fee() {
            return shoukuan_fee;
        }

        public void setShoukuan_fee(String shoukuan_fee) {
            this.shoukuan_fee = shoukuan_fee;
        }

        public String getNextDifference() {
            return nextDifference;
        }

        public void setNextDifference(String nextDifference) {
            this.nextDifference = nextDifference;
        }

        public String getStoreBonus() {
            return storeBonus;
        }

        public void setStoreBonus(String storeBonus) {
            this.storeBonus = storeBonus;
        }

        public String getPop_up_text() {
            return pop_up_text;
        }

        public void setPop_up_text(String pop_up_text) {
            this.pop_up_text = pop_up_text;
        }

        public String getPop_up_status() {
            return pop_up_status;
        }

        public void setPop_up_status(String pop_up_status) {
            this.pop_up_status = pop_up_status;
        }

        public String getPop_up_settles() {
            return pop_up_settles;
        }

        public void setPop_up_settles(String pop_up_settles) {
            this.pop_up_settles = pop_up_settles;
        }

        public static class FeeBean {
            /**
             * orderTotalFee : 0.00
             * payBillTotalFee : 2.00
             * orderCustomer : 0
             * payBillCustomer : 1
             * returnTotalFee : 2.00
             * total_fee : 2.00
             * customer : 1
             */

            private String orderTotalFee;
            private String payBillTotalFee;
            private String orderCustomer;
            private String payBillCustomer;
            private String returnTotalFee;
            private String total_fee;
            private String customer;

            public String getOrderTotalFee() {
                return orderTotalFee;
            }

            public void setOrderTotalFee(String orderTotalFee) {
                this.orderTotalFee = orderTotalFee;
            }

            public String getPayBillTotalFee() {
                return payBillTotalFee;
            }

            public void setPayBillTotalFee(String payBillTotalFee) {
                this.payBillTotalFee = payBillTotalFee;
            }

            public String getOrderCustomer() {
                return orderCustomer;
            }

            public void setOrderCustomer(String orderCustomer) {
                this.orderCustomer = orderCustomer;
            }

            public String getPayBillCustomer() {
                return payBillCustomer;
            }

            public void setPayBillCustomer(String payBillCustomer) {
                this.payBillCustomer = payBillCustomer;
            }

            public String getReturnTotalFee() {
                return returnTotalFee;
            }

            public void setReturnTotalFee(String returnTotalFee) {
                this.returnTotalFee = returnTotalFee;
            }

            public String getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(String total_fee) {
                this.total_fee = total_fee;
            }

            public String getCustomer() {
                return customer;
            }

            public void setCustomer(String customer) {
                this.customer = customer;
            }
        }

        public static class CurrentGradeBean {
            /**
             * f2 : 200
             * f3 : 0.0009
             * f4 : 0.3
             * id : 1
             * fee : 200
             */

            private String f2;
            private String f3;
            private String f4;
            private String id;
            private String fee;

            public String getF2() {
                return f2;
            }

            public void setF2(String f2) {
                this.f2 = f2;
            }

            public String getF3() {
                return f3;
            }

            public void setF3(String f3) {
                this.f3 = f3;
            }

            public String getF4() {
                return f4;
            }

            public void setF4(String f4) {
                this.f4 = f4;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }
        }

        public static class PrevGradeBean {
            /**
             * id : 0
             * f2 : 0
             * f3 : 0
             * f4 : 0
             */

            private String id;
            private String f2;
            private String f3;
            private String f4;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getF2() {
                return f2;
            }

            public void setF2(String f2) {
                this.f2 = f2;
            }

            public String getF3() {
                return f3;
            }

            public void setF3(String f3) {
                this.f3 = f3;
            }

            public String getF4() {
                return f4;
            }

            public void setF4(String f4) {
                this.f4 = f4;
            }
        }

        public static class NextGradeBean {
            /**
             * f2 : 500
             * f3 : 0.00093
             * f4 : 0.465
             * id : 2
             * fee : 500
             */

            private String f2;
            private String f3;
            private String f4;
            private String id;
            private String fee;

            public String getF2() {
                return f2;
            }

            public void setF2(String f2) {
                this.f2 = f2;
            }

            public String getF3() {
                return f3;
            }

            public void setF3(String f3) {
                this.f3 = f3;
            }

            public String getF4() {
                return f4;
            }

            public void setF4(String f4) {
                this.f4 = f4;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }
        }

        public static class AmountBean {
            /**
             * amount : 300.00
             * signtime : 0
             * amountFee : 300.00
             */

            private String amount;
            private String signtime;
            private String amountFee;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getSigntime() {
                return signtime;
            }

            public void setSigntime(String signtime) {
                this.signtime = signtime;
            }

            public String getAmountFee() {
                return amountFee;
            }

            public void setAmountFee(String amountFee) {
                this.amountFee = amountFee;
            }
        }
    }
}
