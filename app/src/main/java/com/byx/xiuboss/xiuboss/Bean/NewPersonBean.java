package com.byx.xiuboss.xiuboss.Bean;



public class NewPersonBean {


    /**
     * code : 2000
     * message : 修改余额成功
     * data : {"currentGrade":{"f2":"12500","f3":"0.00119","f4":"14.875","id":"10","fee":"12,500"},"prevGrade":{"f2":"10000","f3":"0.00115","f4":"11.5","id":"9","fee":"10,000"},"nextGrade":{"f2":"15000","f3":"0.00122","f4":"18.3","id":"11","fee":"15,000"}}
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
         * currentGrade : {"f2":"12500","f3":"0.00119","f4":"14.875","id":"10","fee":"12,500"}
         * prevGrade : {"f2":"10000","f3":"0.00115","f4":"11.5","id":"9","fee":"10,000"}
         * nextGrade : {"f2":"15000","f3":"0.00122","f4":"18.3","id":"11","fee":"15,000"}
         */

        private CurrentGradeBean currentGrade;
        private PrevGradeBean prevGrade;
        private NextGradeBean nextGrade;

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

        public NextGradeBean getNextGrade() {
            return nextGrade;
        }

        public void setNextGrade(NextGradeBean nextGrade) {
            this.nextGrade = nextGrade;
        }

        public static class CurrentGradeBean {
            /**
             * f2 : 12500
             * f3 : 0.00119
             * f4 : 14.875
             * id : 10
             * fee : 12,500
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
             * f2 : 10000
             * f3 : 0.00115
             * f4 : 11.5
             * id : 9
             * fee : 10,000
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
             * f2 : 15000
             * f3 : 0.00122
             * f4 : 18.3
             * id : 11
             * fee : 15,000
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
    }
}
