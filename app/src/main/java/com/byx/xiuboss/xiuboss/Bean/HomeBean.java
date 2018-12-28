package com.byx.xiuboss.xiuboss.Bean;



public class HomeBean {


    /**
     * code : 2000
     * message : 请求成功
     * data : {"fee":{"orderFinalFee":"19.8","payBillFinalFee":"2070","orderTotalFee":"19.80","payBillTotalFee":"1,834.60","orderCustomer":"1","payBillCustomer":"9","returnTotalFee":"1854.40","total_fee":"1,854.40","final_fee":2089.8,"customer":"10"},"todaySum":10,"todayFee":"1,854.4","yesterSum":25,"yesterFee":"1,338.32","currentGrade":{"f2":"3000","f3":"0.00106","f4":"3.18","id":"6","fee":"3,000"},"prevGrade":{"f2":"2000","f3":"0.00103","f4":"2.06","id":"5","fee":"2,000"},"signtime":"1545199745","nextGrade":{"f2":"5000","f3":"0.00109","f4":"5.45","id":"7","fee":"5,000"},"amount":{"amount":"3183.79","signtime":"1545199745","amountFee":"3,183.79"},"monthMoney":"0.00","monthFee":0,"tixian_fee":"1","shoukuan_fee":"1000","nextDifference":"1,816.21","storeBonus":0,"pop_up_text":"1.1.2","pop_up_status":1,"pop_up_settles":"优化首页签到和等级展示。\r\n新增登录后语音提示领取奖励。 \r\n新增提现到账后推送语音提醒。 \r\n新增新入驻的商户可以领取新手大礼包。\r\n新增删除提现方式功能。\r\n修改新的手续费扣除方案。"}
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
         * fee : {"orderFinalFee":"19.8","payBillFinalFee":"2070","orderTotalFee":"19.80","payBillTotalFee":"1,834.60","orderCustomer":"1","payBillCustomer":"9","returnTotalFee":"1854.40","total_fee":"1,854.40","final_fee":2089.8,"customer":"10"}
         * todaySum : 10
         * todayFee : 1,854.4
         * yesterSum : 25
         * yesterFee : 1,338.32
         * currentGrade : {"f2":"3000","f3":"0.00106","f4":"3.18","id":"6","fee":"3,000"}
         * prevGrade : {"f2":"2000","f3":"0.00103","f4":"2.06","id":"5","fee":"2,000"}
         * signtime : 1545199745
         * nextGrade : {"f2":"5000","f3":"0.00109","f4":"5.45","id":"7","fee":"5,000"}
         * amount : {"amount":"3183.79","signtime":"1545199745","amountFee":"3,183.79"}
         * monthMoney : 0.00
         * monthFee : 0
         * tixian_fee : 1
         * shoukuan_fee : 1000
         * nextDifference : 1,816.21
         * storeBonus : 0
         * pop_up_text : 1.1.2
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
             * orderFinalFee : 19.8
             * payBillFinalFee : 2070
             * orderTotalFee : 19.80
             * payBillTotalFee : 1,834.60
             * orderCustomer : 1
             * payBillCustomer : 9
             * returnTotalFee : 1854.40
             * total_fee : 1,854.40
             * final_fee : 2089.8
             * customer : 10
             */

            private String orderFinalFee;
            private String payBillFinalFee;
            private String orderTotalFee;
            private String payBillTotalFee;
            private String orderCustomer;
            private String payBillCustomer;
            private String returnTotalFee;
            private String total_fee;
            private String final_fee;
            private String customer;

            public String getOrderFinalFee() {
                return orderFinalFee;
            }

            public void setOrderFinalFee(String orderFinalFee) {
                this.orderFinalFee = orderFinalFee;
            }

            public String getPayBillFinalFee() {
                return payBillFinalFee;
            }

            public void setPayBillFinalFee(String payBillFinalFee) {
                this.payBillFinalFee = payBillFinalFee;
            }

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

            public String getFinal_fee() {
                return final_fee;
            }

            public void setFinal_fee(String final_fee) {
                this.final_fee = final_fee;
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
             * f2 : 3000
             * f3 : 0.00106
             * f4 : 3.18
             * id : 6
             * fee : 3,000
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
             * f2 : 2000
             * f3 : 0.00103
             * f4 : 2.06
             * id : 5
             * fee : 2,000
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

        public static class NextGradeBean {
            /**
             * f2 : 5000
             * f3 : 0.00109
             * f4 : 5.45
             * id : 7
             * fee : 5,000
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
             * amount : 3183.79
             * signtime : 1545199745
             * amountFee : 3,183.79
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
