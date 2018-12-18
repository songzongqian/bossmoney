package com.byx.xiuboss.xiuboss.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TauchMe on 2018/7/20.
 * 待确认
 */

public class OrderConfirmBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 48
         * uniacid : 2
         * acid : 2
         * sid : 98
         * uid : 111111111
         * order_type : 1
         * is_pay : 1
         * ordersn : 20180601120825946840
         * code : 8261
         * username : 珊珊
         * mobile : 18972086913
         * address : 融晟外滩普罗旺斯小区1号楼1502室
         * note : 外加一提抽纸
         * price : 15.18
         * num : 2
         * delivery_day : 2018-06-01
         * delivery_time : 尽快送达
         * pay_type : delivery
         * addtime : 1527826104
         * paytime : 1527826104
         * delivery_assign_time : 1527826211
         * delivery_success_time : 1527828238
         * status : 5
         * delivery_status : 5
         * delivery_type : 2
         * delivery_fee : 0.00
         * pack_fee : 0.00
         * total_fee : 15.18
         * final_fee : 15.18
         * deliveryer_id : 6
         * person_num : 1
         * agentid : 3
         * spreadbalance : 1
         * order_channel : plateformCreate
         * serial_sn : 1
         * handletime : 1527826174
         * clerk_notify_collect_time : 1527826181
         * endtime : 1527828238
         * delivery_handle_type : wechat
         * delivery_instore_time : 1527827241
         * store_final_fee : 11.84
         * plateform_serve : {"fee_type":1,"fee_rate":22,"fee":3.34,"note":"(商品费用 ￥15.18 + 餐盒费 ￥0 + 包装费 ￥0.00) x 22%"}
         * plateform_serve_rate : 22
         * plateform_serve_fee : 3.34
         * plateform_delivery_fee : 0.00
         * plateform_deliveryer_fee : 1.5
         * agent_serve : {"final":"(代理商抽取佣金 ￥3.34 - 平台服务佣金 ￥ - 代理商补贴 ￥0 + 代理商配送费 ￥0.00 - 代理商支付给配送员配送费 ￥1.5)"}
         * agent_final_fee : 1.84
         * stat_year : 2018
         * stat_month : 201806
         * stat_day : 20180601
         * last_notify_deliveryer_time : 1527826181
         * last_notify_clerk_time : 1527826104
         * notify_deliveryer_total : 1
         * notify_clerk_total : 1
         * eleme_store_final_fee : 0.00
         * meituan_store_final_fee : 0.00
         * order_plateform : we7_wmall
         * delivery_takegoods_time : 1527827248
         * title : 曾雨洋
         * avatar : https://www.ourdaidai.com/img/WechatIMG21.png
         * cart : [{"title":"五号白菜500g","goods_id":5754,"options":[{"cid":"212","child_id":"214","goods_id":5754,"thumb":"https://img.ourdaidai.com/images/2/merch/98/kVyZo2c78YYyJYp5jVo4vocjvJp577.jpg","title":"五号白菜500g","num":1,"price":"4.28","discount_price":"4.28","discount_num":0,"price_num":1,"total_price":4.28,"total_discount_price":4.28,"bargain_id":0}]},{"title":"洁柔天然无香3层135抽抽取式面巾纸3包装","goods_id":6585,"options":[{"cid":"283","child_id":"285","goods_id":6585,"thumb":"https://img.ourdaidai.com/images/2/2018/03/uJ3v4BBy4T4Sjb4mdJAadJDC44SYtJA4.jpg","title":"洁柔天然无香3层135抽抽取式面巾纸3包装","num":1,"price":"10.9","discount_price":"10.9","discount_num":0,"price_num":1,"total_price":10.9,"total_discount_price":10.9,"bargain_id":0}]}]
         * openid : oeh8u5XHdFXhmKApfnOzlOHlTL9E
         * sex : 女士
         * location_x : 32.37395
         * location_y : 111.67571
         * transaction_id : 4200000137201806011712440235
         * mall_first_order : 1
         * is_timeout : 1
         * distance : 1.032
         * out_trade_no : 2018060112364867722028888862
         * is_remind : 1
         */

        private String id;
        private String uniacid;
        private String acid;
        private String sid;
        private String uid;
        private String order_type;
        private String is_pay;
        private String ordersn;
        private String code;
        private String username;
        private String mobile;
        private String address;
        private String note;
        private String price;
        private String num;
        private String delivery_day;
        private String delivery_time;
        private String pay_type;
        private String addtime;
        private String paytime;
        private String delivery_assign_time;
        private String delivery_success_time;
        private String status;
        private String delivery_status;
        private String delivery_type;
        private String delivery_fee;
        private String pack_fee;
        private String total_fee;
        private String final_fee;
        private String deliveryer_id;
        private String person_num;
        private String agentid;
        private String spreadbalance;
        private String order_channel;
        private String serial_sn;
        private String handletime;
        private String clerk_notify_collect_time;
        private String endtime;
        private String delivery_handle_type;
        private String delivery_instore_time;
        private String store_final_fee;
        private PlateformServeBean plateform_serve;
        private String plateform_serve_rate;
        private String plateform_serve_fee;
        private String plateform_delivery_fee;
        private String plateform_deliveryer_fee;
        private AgentServeBean agent_serve;
        private String agent_final_fee;
        private String stat_year;
        private String stat_month;
        private String stat_day;
        private String last_notify_deliveryer_time;
        private String last_notify_clerk_time;
        private String notify_deliveryer_total;
        private String notify_clerk_total;
        private String eleme_store_final_fee;
        private String meituan_store_final_fee;
        private String order_plateform;
        private String delivery_takegoods_time;
        private String title;
        private String avatar;
        private String openid;
        private String sex;
        private String location_x;
        private String location_y;
        private String transaction_id;
        private String mall_first_order;
        private String is_timeout;
        private String distance;
        private String out_trade_no;
        private String is_remind;
        private List<CartBean> cart;

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

        public String getAcid() {
            return acid;
        }

        public void setAcid(String acid) {
            this.acid = acid;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(String is_pay) {
            this.is_pay = is_pay;
        }

        public String getOrdersn() {
            return ordersn;
        }

        public void setOrdersn(String ordersn) {
            this.ordersn = ordersn;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getDelivery_day() {
            return delivery_day;
        }

        public void setDelivery_day(String delivery_day) {
            this.delivery_day = delivery_day;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getDelivery_assign_time() {
            return delivery_assign_time;
        }

        public void setDelivery_assign_time(String delivery_assign_time) {
            this.delivery_assign_time = delivery_assign_time;
        }

        public String getDelivery_success_time() {
            return delivery_success_time;
        }

        public void setDelivery_success_time(String delivery_success_time) {
            this.delivery_success_time = delivery_success_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDelivery_status() {
            return delivery_status;
        }

        public void setDelivery_status(String delivery_status) {
            this.delivery_status = delivery_status;
        }

        public String getDelivery_type() {
            return delivery_type;
        }

        public void setDelivery_type(String delivery_type) {
            this.delivery_type = delivery_type;
        }

        public String getDelivery_fee() {
            return delivery_fee;
        }

        public void setDelivery_fee(String delivery_fee) {
            this.delivery_fee = delivery_fee;
        }

        public String getPack_fee() {
            return pack_fee;
        }

        public void setPack_fee(String pack_fee) {
            this.pack_fee = pack_fee;
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

        public String getDeliveryer_id() {
            return deliveryer_id;
        }

        public void setDeliveryer_id(String deliveryer_id) {
            this.deliveryer_id = deliveryer_id;
        }

        public String getPerson_num() {
            return person_num;
        }

        public void setPerson_num(String person_num) {
            this.person_num = person_num;
        }

        public String getAgentid() {
            return agentid;
        }

        public void setAgentid(String agentid) {
            this.agentid = agentid;
        }

        public String getSpreadbalance() {
            return spreadbalance;
        }

        public void setSpreadbalance(String spreadbalance) {
            this.spreadbalance = spreadbalance;
        }

        public String getOrder_channel() {
            return order_channel;
        }

        public void setOrder_channel(String order_channel) {
            this.order_channel = order_channel;
        }

        public String getSerial_sn() {
            return serial_sn;
        }

        public void setSerial_sn(String serial_sn) {
            this.serial_sn = serial_sn;
        }

        public String getHandletime() {
            return handletime;
        }

        public void setHandletime(String handletime) {
            this.handletime = handletime;
        }

        public String getClerk_notify_collect_time() {
            return clerk_notify_collect_time;
        }

        public void setClerk_notify_collect_time(String clerk_notify_collect_time) {
            this.clerk_notify_collect_time = clerk_notify_collect_time;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getDelivery_handle_type() {
            return delivery_handle_type;
        }

        public void setDelivery_handle_type(String delivery_handle_type) {
            this.delivery_handle_type = delivery_handle_type;
        }

        public String getDelivery_instore_time() {
            return delivery_instore_time;
        }

        public void setDelivery_instore_time(String delivery_instore_time) {
            this.delivery_instore_time = delivery_instore_time;
        }

        public String getStore_final_fee() {
            return store_final_fee;
        }

        public void setStore_final_fee(String store_final_fee) {
            this.store_final_fee = store_final_fee;
        }

        public PlateformServeBean getPlateform_serve() {
            return plateform_serve;
        }

        public void setPlateform_serve(PlateformServeBean plateform_serve) {
            this.plateform_serve = plateform_serve;
        }

        public String getPlateform_serve_rate() {
            return plateform_serve_rate;
        }

        public void setPlateform_serve_rate(String plateform_serve_rate) {
            this.plateform_serve_rate = plateform_serve_rate;
        }

        public String getPlateform_serve_fee() {
            return plateform_serve_fee;
        }

        public void setPlateform_serve_fee(String plateform_serve_fee) {
            this.plateform_serve_fee = plateform_serve_fee;
        }

        public String getPlateform_delivery_fee() {
            return plateform_delivery_fee;
        }

        public void setPlateform_delivery_fee(String plateform_delivery_fee) {
            this.plateform_delivery_fee = plateform_delivery_fee;
        }

        public String getPlateform_deliveryer_fee() {
            return plateform_deliveryer_fee;
        }

        public void setPlateform_deliveryer_fee(String plateform_deliveryer_fee) {
            this.plateform_deliveryer_fee = plateform_deliveryer_fee;
        }

        public AgentServeBean getAgent_serve() {
            return agent_serve;
        }

        public void setAgent_serve(AgentServeBean agent_serve) {
            this.agent_serve = agent_serve;
        }

        public String getAgent_final_fee() {
            return agent_final_fee;
        }

        public void setAgent_final_fee(String agent_final_fee) {
            this.agent_final_fee = agent_final_fee;
        }

        public String getStat_year() {
            return stat_year;
        }

        public void setStat_year(String stat_year) {
            this.stat_year = stat_year;
        }

        public String getStat_month() {
            return stat_month;
        }

        public void setStat_month(String stat_month) {
            this.stat_month = stat_month;
        }

        public String getStat_day() {
            return stat_day;
        }

        public void setStat_day(String stat_day) {
            this.stat_day = stat_day;
        }

        public String getLast_notify_deliveryer_time() {
            return last_notify_deliveryer_time;
        }

        public void setLast_notify_deliveryer_time(String last_notify_deliveryer_time) {
            this.last_notify_deliveryer_time = last_notify_deliveryer_time;
        }

        public String getLast_notify_clerk_time() {
            return last_notify_clerk_time;
        }

        public void setLast_notify_clerk_time(String last_notify_clerk_time) {
            this.last_notify_clerk_time = last_notify_clerk_time;
        }

        public String getNotify_deliveryer_total() {
            return notify_deliveryer_total;
        }

        public void setNotify_deliveryer_total(String notify_deliveryer_total) {
            this.notify_deliveryer_total = notify_deliveryer_total;
        }

        public String getNotify_clerk_total() {
            return notify_clerk_total;
        }

        public void setNotify_clerk_total(String notify_clerk_total) {
            this.notify_clerk_total = notify_clerk_total;
        }

        public String getEleme_store_final_fee() {
            return eleme_store_final_fee;
        }

        public void setEleme_store_final_fee(String eleme_store_final_fee) {
            this.eleme_store_final_fee = eleme_store_final_fee;
        }

        public String getMeituan_store_final_fee() {
            return meituan_store_final_fee;
        }

        public void setMeituan_store_final_fee(String meituan_store_final_fee) {
            this.meituan_store_final_fee = meituan_store_final_fee;
        }

        public String getOrder_plateform() {
            return order_plateform;
        }

        public void setOrder_plateform(String order_plateform) {
            this.order_plateform = order_plateform;
        }

        public String getDelivery_takegoods_time() {
            return delivery_takegoods_time;
        }

        public void setDelivery_takegoods_time(String delivery_takegoods_time) {
            this.delivery_takegoods_time = delivery_takegoods_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getLocation_x() {
            return location_x;
        }

        public void setLocation_x(String location_x) {
            this.location_x = location_x;
        }

        public String getLocation_y() {
            return location_y;
        }

        public void setLocation_y(String location_y) {
            this.location_y = location_y;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getMall_first_order() {
            return mall_first_order;
        }

        public void setMall_first_order(String mall_first_order) {
            this.mall_first_order = mall_first_order;
        }

        public String getIs_timeout() {
            return is_timeout;
        }

        public void setIs_timeout(String is_timeout) {
            this.is_timeout = is_timeout;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getIs_remind() {
            return is_remind;
        }

        public void setIs_remind(String is_remind) {
            this.is_remind = is_remind;
        }

        public List<CartBean> getCart() {
            return cart;
        }

        public void setCart(List<CartBean> cart) {
            this.cart = cart;
        }

        public static class PlateformServeBean {
            /**
             * fee_type : 1
             * fee_rate : 22
             * fee : 3.34
             * note : (商品费用 ￥15.18 + 餐盒费 ￥0 + 包装费 ￥0.00) x 22%
             */

            private int fee_type;
            private int fee_rate;
            private double fee;
            private String note;

            public int getFee_type() {
                return fee_type;
            }

            public void setFee_type(int fee_type) {
                this.fee_type = fee_type;
            }

            public int getFee_rate() {
                return fee_rate;
            }

            public void setFee_rate(int fee_rate) {
                this.fee_rate = fee_rate;
            }

            public double getFee() {
                return fee;
            }

            public void setFee(double fee) {
                this.fee = fee;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }
        }

        public static class AgentServeBean {
            /**
             * final : (代理商抽取佣金 ￥3.34 - 平台服务佣金 ￥ - 代理商补贴 ￥0 + 代理商配送费 ￥0.00 - 代理商支付给配送员配送费 ￥1.5)
             */

            @SerializedName("final")
            private String finalX;

            public String getFinalX() {
                return finalX;
            }

            public void setFinalX(String finalX) {
                this.finalX = finalX;
            }
        }

        public static class CartBean {
            /**
             * title : 五号白菜500g
             * goods_id : 5754
             * options : [{"cid":"212","child_id":"214","goods_id":5754,"thumb":"https://img.ourdaidai.com/images/2/merch/98/kVyZo2c78YYyJYp5jVo4vocjvJp577.jpg","title":"五号白菜500g","num":1,"price":"4.28","discount_price":"4.28","discount_num":0,"price_num":1,"total_price":4.28,"total_discount_price":4.28,"bargain_id":0}]
             */

            private String title;
            private int goods_id;
            private List<OptionsBean> options;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public List<OptionsBean> getOptions() {
                return options;
            }

            public void setOptions(List<OptionsBean> options) {
                this.options = options;
            }

            public static class OptionsBean {
                /**
                 * cid : 212
                 * child_id : 214
                 * goods_id : 5754
                 * thumb : https://img.ourdaidai.com/images/2/merch/98/kVyZo2c78YYyJYp5jVo4vocjvJp577.jpg
                 * title : 五号白菜500g
                 * num : 1
                 * price : 4.28
                 * discount_price : 4.28
                 * discount_num : 0
                 * price_num : 1
                 * total_price : 4.28
                 * total_discount_price : 4.28
                 * bargain_id : 0
                 */

                private String cid;
                private String child_id;
                private int goods_id;
                private String thumb;
                private String title;
                private int num;
                private String price;
                private String discount_price;
                private int discount_num;
                private int price_num;
                private double total_price;
                private double total_discount_price;
                private int bargain_id;

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getChild_id() {
                    return child_id;
                }

                public void setChild_id(String child_id) {
                    this.child_id = child_id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getDiscount_price() {
                    return discount_price;
                }

                public void setDiscount_price(String discount_price) {
                    this.discount_price = discount_price;
                }

                public int getDiscount_num() {
                    return discount_num;
                }

                public void setDiscount_num(int discount_num) {
                    this.discount_num = discount_num;
                }

                public int getPrice_num() {
                    return price_num;
                }

                public void setPrice_num(int price_num) {
                    this.price_num = price_num;
                }

                public double getTotal_price() {
                    return total_price;
                }

                public void setTotal_price(double total_price) {
                    this.total_price = total_price;
                }

                public double getTotal_discount_price() {
                    return total_discount_price;
                }

                public void setTotal_discount_price(double total_discount_price) {
                    this.total_discount_price = total_discount_price;
                }

                public int getBargain_id() {
                    return bargain_id;
                }

                public void setBargain_id(int bargain_id) {
                    this.bargain_id = bargain_id;
                }
            }
        }
    }
}
