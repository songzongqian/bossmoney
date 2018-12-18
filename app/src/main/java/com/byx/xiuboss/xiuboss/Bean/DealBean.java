package com.byx.xiuboss.xiuboss.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by night_slight on 2018/11/14.
 */

public class DealBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3217
         * uniacid : 2
         * acid : 2
         * sid : 111
         * uid : 393638
         * groupid : 0
         * order_type : 1
         * is_pay : 1
         * ordersn : 20181112120148415664
         * code : 6754
         * openid : oeh8u5WhhB_O-QIGoyPeE9TJgdBk
         * username : aefa
         * sex : 先生
         * mobile : 19876544322
         * address : 武商量贩(老河口店)122
         * location_x : 32.38671
         * location_y : 111.67507
         * price : 9.90
         * num : 1
         * delivery_day : 11-12
         * delivery_time : 立即送出
         * pay_type : credit
         * addtime : 1541995307
         * paytime : 1541995331
         * delivery_assign_time : 1541995362
         * delivery_success_time : 0
         * status : 4
         * delivery_status : 7
         * delivery_type : 2
         * is_comment : 0
         * prString_nums : 0
         * delivery_fee : 1053.00
         * pack_fee : 0.00
         * discount_fee : 0
         * total_fee : 1062.9
         * final_fee : 1062.90
         * vip_free_delivery_fee : 0
         * is_remind : 0
         * deliveryer_id : 17
         * is_refund : 0
         * person_num : 1
         * table_id : 0
         * table_cid : 0
         * reserve_type : 0
         * reserve_time : 0
         * agentid : 3
         * spread1 : 0
         * spread2 : 0
         * spreadbalance : 1
         * mall_first_order : 0
         * order_channel : wxapp
         * serial_sn : 3
         * box_price : 0
         * handletime : 1541995362
         * clerk_notify_collect_time : 1541995362
         * endtime : 0
         * is_timeout : 0
         * delivery_handle_type : wechat
         * deliveryingtime : 0
         * delivery_instore_time : 0
         * deliverysuccesstime : 0
         * refund_status : 0
         * distance : 1056
         * store_final_fee : 7.72
         * store_discount_fee : 0
         * plateform_discount_fee : 0
         * plateform_serve : {"fee_type":1,"fee_rate":22,"fee":2.18,"note":"(商品费用 ￥9.9 + 餐盒费 ￥0 + 包装费 ￥0.00) x 22%"}
         * plateform_serve_rate : 22
         * plateform_serve_fee : 2.18
         * plateform_delivery_fee : 1053.00
         * plateform_deliveryer_fee : 0
         * agent_serve : {"fee_type":1,"fee_rate":0,"fee":0,"note":"() x 0%","final":"(代理商抽取佣金 ￥2.18 + 平台(代理)配送模式下商户额外承担配送费 ￥0 - 平台服务佣金 ￥0 - 代理商补贴 ￥0 + 代理商配送费 ￥1053.00 - 代理商支付给配送员配送费 ￥0)"}
         * agent_final_fee : 1055.18
         * agent_serve_fee : 0
         * agent_discount_fee : 0
         * refund_fee : 0
         * out_trade_no : 2018111212014967664620464680
         * stat_year : 2018
         * stat_month : 201811
         * stat_day : 20181112
         * last_notify_deliveryer_time : 0
         * last_notify_clerk_time : 1541995331
         * notify_deliveryer_total : 0
         * notify_clerk_total : 1
         * elemeDowngraded : 0
         * eleme_store_final_fee : 0.00
         * meituanOrderId : 0
         * meituan_store_final_fee : 0.00
         * order_plateform : we7_wmall
         * deliveryinstoretime : 0
         * delivery_takegoods_time : 0
         * extra_fee : 0
         * is_delete : 0
         * prString_sn : 0
         * stat_week : 1
         * meals_cn : lunch
         * delivery_collect_type : 1
         * transfer_deliveryer_id : 0
         * transfer_delivery_status : 0
         * title : 李振虎
         * deliveryerMobile : 18034103322
         * avatar : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLmBYq5IkXzE3C0miaickQ1J4xcVd4PAV1ItcrDKWPSmsqxQoYzbOPMWx0b0SUWDlVRAulBdW17fgQA/132
         * cart : [{"title":"siyi润滑油250ml 情趣成人用品","goods_id":8519,"options":[{"cid":"392","child_id":"393","goods_id":8519,"thumb":"https://img.ourdaidai.com/images/2/2018/05/Bl2pzdB0C0ezl6pe5b0F8nDLg5gS7eNn.jpg","title":"siyi润滑油250ml 情趣成人用品","option_title":"","num":1,"price":"9.9","discount_price":"9.9","discount_num":0,"price_num":1,"total_price":10,"total_discount_price":9.9,"bargain_id":0}]}]
         */

        private String id;
        private String uniacid;
        private String acid;
        private String sid;
        private String uid;
        private String groupid;
        private String order_type;
        private String is_pay;
        private String ordersn;
        private String code;
        private String openid;
        private String username;
        private String sex;
        private String mobile;
        private String address;
        private String location_x;
        private String location_y;
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
        private String is_comment;
        private String prString_nums;
        private String delivery_fee;
        private String pack_fee;
        private String discount_fee;
        private String total_fee;
        private String final_fee;
        private String vip_free_delivery_fee;
        private String is_remind;
        private String deliveryer_id;
        private String is_refund;
        private String person_num;
        private String table_id;
        private String table_cid;
        private String reserve_type;
        private String reserve_time;
        private String agentid;
        private String spread1;
        private String spread2;
        private String spreadbalance;
        private String mall_first_order;
        private String order_channel;
        private String serial_sn;
        private String box_price;
        private String handletime;
        private String clerk_notify_collect_time;
        private String endtime;
        private String is_timeout;
        private String delivery_handle_type;
        private String deliveryingtime;
        private String delivery_instore_time;
        private String deliverysuccesstime;
        private String refund_status;
        private String distance;
        private String store_final_fee;
        private String store_discount_fee;
        private String plateform_discount_fee;
        private PlateformServeBean plateform_serve;
        private String plateform_serve_rate;
        private String plateform_serve_fee;
        private String plateform_delivery_fee;
        private String plateform_deliveryer_fee;
        private AgentServeBean agent_serve;
        private String agent_final_fee;
        private String agent_serve_fee;
        private String agent_discount_fee;
        private String refund_fee;
        private String out_trade_no;
        private String stat_year;
        private String stat_month;
        private String stat_day;
        private String last_notify_deliveryer_time;
        private String last_notify_clerk_time;
        private String notify_deliveryer_total;
        private String notify_clerk_total;
        private String elemeDowngraded;
        private String eleme_store_final_fee;
        private String meituanOrderId;
        private String meituan_store_final_fee;
        private String order_plateform;
        private String deliveryinstoretime;
        private String delivery_takegoods_time;
        private String extra_fee;
        private String is_delete;
        private String prString_sn;
        private String stat_week;
        private String meals_cn;
        private String delivery_collect_type;
        private String transfer_deliveryer_id;
        private String transfer_delivery_status;
        private String title;
        private String deliveryerMobile;
        private String avatar;
        private String countdowntime;
        private String note;
        private String stationPhone;

        public String getStationPhone() {
            return stationPhone;
        }

        public void setStationPhone(String stationPhone) {
            this.stationPhone = stationPhone;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        private List<CartBean> cart;

        public String getCountdowntime() {
            return countdowntime;
        }

        public void setCountdowntime(String countdowntime) {
            this.countdowntime = countdowntime;
        }

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

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
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

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        public String getPrString_nums() {
            return prString_nums;
        }

        public void setPrString_nums(String prString_nums) {
            this.prString_nums = prString_nums;
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

        public String getDiscount_fee() {
            return discount_fee;
        }

        public void setDiscount_fee(String discount_fee) {
            this.discount_fee = discount_fee;
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

        public String getVip_free_delivery_fee() {
            return vip_free_delivery_fee;
        }

        public void setVip_free_delivery_fee(String vip_free_delivery_fee) {
            this.vip_free_delivery_fee = vip_free_delivery_fee;
        }

        public String getIs_remind() {
            return is_remind;
        }

        public void setIs_remind(String is_remind) {
            this.is_remind = is_remind;
        }

        public String getDeliveryer_id() {
            return deliveryer_id;
        }

        public void setDeliveryer_id(String deliveryer_id) {
            this.deliveryer_id = deliveryer_id;
        }

        public String getIs_refund() {
            return is_refund;
        }

        public void setIs_refund(String is_refund) {
            this.is_refund = is_refund;
        }

        public String getPerson_num() {
            return person_num;
        }

        public void setPerson_num(String person_num) {
            this.person_num = person_num;
        }

        public String getTable_id() {
            return table_id;
        }

        public void setTable_id(String table_id) {
            this.table_id = table_id;
        }

        public String getTable_cid() {
            return table_cid;
        }

        public void setTable_cid(String table_cid) {
            this.table_cid = table_cid;
        }

        public String getReserve_type() {
            return reserve_type;
        }

        public void setReserve_type(String reserve_type) {
            this.reserve_type = reserve_type;
        }

        public String getReserve_time() {
            return reserve_time;
        }

        public void setReserve_time(String reserve_time) {
            this.reserve_time = reserve_time;
        }

        public String getAgentid() {
            return agentid;
        }

        public void setAgentid(String agentid) {
            this.agentid = agentid;
        }

        public String getSpread1() {
            return spread1;
        }

        public void setSpread1(String spread1) {
            this.spread1 = spread1;
        }

        public String getSpread2() {
            return spread2;
        }

        public void setSpread2(String spread2) {
            this.spread2 = spread2;
        }

        public String getSpreadbalance() {
            return spreadbalance;
        }

        public void setSpreadbalance(String spreadbalance) {
            this.spreadbalance = spreadbalance;
        }

        public String getMall_first_order() {
            return mall_first_order;
        }

        public void setMall_first_order(String mall_first_order) {
            this.mall_first_order = mall_first_order;
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

        public String getBox_price() {
            return box_price;
        }

        public void setBox_price(String box_price) {
            this.box_price = box_price;
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

        public String getIs_timeout() {
            return is_timeout;
        }

        public void setIs_timeout(String is_timeout) {
            this.is_timeout = is_timeout;
        }

        public String getDelivery_handle_type() {
            return delivery_handle_type;
        }

        public void setDelivery_handle_type(String delivery_handle_type) {
            this.delivery_handle_type = delivery_handle_type;
        }

        public String getDeliveryingtime() {
            return deliveryingtime;
        }

        public void setDeliveryingtime(String deliveryingtime) {
            this.deliveryingtime = deliveryingtime;
        }

        public String getDelivery_instore_time() {
            return delivery_instore_time;
        }

        public void setDelivery_instore_time(String delivery_instore_time) {
            this.delivery_instore_time = delivery_instore_time;
        }

        public String getDeliverysuccesstime() {
            return deliverysuccesstime;
        }

        public void setDeliverysuccesstime(String deliverysuccesstime) {
            this.deliverysuccesstime = deliverysuccesstime;
        }

        public String getRefund_status() {
            return refund_status;
        }

        public void setRefund_status(String refund_status) {
            this.refund_status = refund_status;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getStore_final_fee() {
            return store_final_fee;
        }

        public void setStore_final_fee(String store_final_fee) {
            this.store_final_fee = store_final_fee;
        }

        public String getStore_discount_fee() {
            return store_discount_fee;
        }

        public void setStore_discount_fee(String store_discount_fee) {
            this.store_discount_fee = store_discount_fee;
        }

        public String getPlateform_discount_fee() {
            return plateform_discount_fee;
        }

        public void setPlateform_discount_fee(String plateform_discount_fee) {
            this.plateform_discount_fee = plateform_discount_fee;
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

        public String getAgent_serve_fee() {
            return agent_serve_fee;
        }

        public void setAgent_serve_fee(String agent_serve_fee) {
            this.agent_serve_fee = agent_serve_fee;
        }

        public String getAgent_discount_fee() {
            return agent_discount_fee;
        }

        public void setAgent_discount_fee(String agent_discount_fee) {
            this.agent_discount_fee = agent_discount_fee;
        }

        public String getRefund_fee() {
            return refund_fee;
        }

        public void setRefund_fee(String refund_fee) {
            this.refund_fee = refund_fee;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
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

        public String getElemeDowngraded() {
            return elemeDowngraded;
        }

        public void setElemeDowngraded(String elemeDowngraded) {
            this.elemeDowngraded = elemeDowngraded;
        }

        public String getEleme_store_final_fee() {
            return eleme_store_final_fee;
        }

        public void setEleme_store_final_fee(String eleme_store_final_fee) {
            this.eleme_store_final_fee = eleme_store_final_fee;
        }

        public String getMeituanOrderId() {
            return meituanOrderId;
        }

        public void setMeituanOrderId(String meituanOrderId) {
            this.meituanOrderId = meituanOrderId;
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

        public String getDeliveryinstoretime() {
            return deliveryinstoretime;
        }

        public void setDeliveryinstoretime(String deliveryinstoretime) {
            this.deliveryinstoretime = deliveryinstoretime;
        }

        public String getDelivery_takegoods_time() {
            return delivery_takegoods_time;
        }

        public void setDelivery_takegoods_time(String delivery_takegoods_time) {
            this.delivery_takegoods_time = delivery_takegoods_time;
        }

        public String getExtra_fee() {
            return extra_fee;
        }

        public void setExtra_fee(String extra_fee) {
            this.extra_fee = extra_fee;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getPrString_sn() {
            return prString_sn;
        }

        public void setPrString_sn(String prString_sn) {
            this.prString_sn = prString_sn;
        }

        public String getStat_week() {
            return stat_week;
        }

        public void setStat_week(String stat_week) {
            this.stat_week = stat_week;
        }

        public String getMeals_cn() {
            return meals_cn;
        }

        public void setMeals_cn(String meals_cn) {
            this.meals_cn = meals_cn;
        }

        public String getDelivery_collect_type() {
            return delivery_collect_type;
        }

        public void setDelivery_collect_type(String delivery_collect_type) {
            this.delivery_collect_type = delivery_collect_type;
        }

        public String getTransfer_deliveryer_id() {
            return transfer_deliveryer_id;
        }

        public void setTransfer_deliveryer_id(String transfer_deliveryer_id) {
            this.transfer_deliveryer_id = transfer_deliveryer_id;
        }

        public String getTransfer_delivery_status() {
            return transfer_delivery_status;
        }

        public void setTransfer_delivery_status(String transfer_delivery_status) {
            this.transfer_delivery_status = transfer_delivery_status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDeliveryerMobile() {
            return deliveryerMobile;
        }

        public void setDeliveryerMobile(String deliveryerMobile) {
            this.deliveryerMobile = deliveryerMobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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
             * fee : 2.18
             * note : (商品费用 ￥9.9 + 餐盒费 ￥0 + 包装费 ￥0.00) x 22%
             */

            private String fee_type;
            private String fee_rate;
            private double fee;
            private String note;

            public String getFee_type() {
                return fee_type;
            }

            public void setFee_type(String fee_type) {
                this.fee_type = fee_type;
            }

            public String getFee_rate() {
                return fee_rate;
            }

            public void setFee_rate(String fee_rate) {
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
             * fee_type : 1
             * fee_rate : 0
             * fee : 0
             * note : () x 0%
             * final : (代理商抽取佣金 ￥2.18 + 平台(代理)配送模式下商户额外承担配送费 ￥0 - 平台服务佣金 ￥0 - 代理商补贴 ￥0 + 代理商配送费 ￥1053.00 - 代理商支付给配送员配送费 ￥0)
             */

            private String fee_type;
            private String fee_rate;
            private String fee;
            private String note;
            @SerializedName("final")
            private String finalX;

            public String getFee_type() {
                return fee_type;
            }

            public void setFee_type(String fee_type) {
                this.fee_type = fee_type;
            }

            public String getFee_rate() {
                return fee_rate;
            }

            public void setFee_rate(String fee_rate) {
                this.fee_rate = fee_rate;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public String getFinalX() {
                return finalX;
            }

            public void setFinalX(String finalX) {
                this.finalX = finalX;
            }
        }

        public static class CartBean {
            /**
             * title : siyi润滑油250ml 情趣成人用品
             * goods_id : 8519
             * options : [{"cid":"392","child_id":"393","goods_id":8519,"thumb":"https://img.ourdaidai.com/images/2/2018/05/Bl2pzdB0C0ezl6pe5b0F8nDLg5gS7eNn.jpg","title":"siyi润滑油250ml 情趣成人用品","option_title":"","num":1,"price":"9.9","discount_price":"9.9","discount_num":0,"price_num":1,"total_price":10,"total_discount_price":9.9,"bargain_id":0}]
             */

            private String title;
            private String goods_id;
            private List<OptionsBean> options;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
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
                 * cid : 392
                 * child_id : 393
                 * goods_id : 8519
                 * thumb : https://img.ourdaidai.com/images/2/2018/05/Bl2pzdB0C0ezl6pe5b0F8nDLg5gS7eNn.jpg
                 * title : siyi润滑油250ml 情趣成人用品
                 * option_title :
                 * num : 1
                 * price : 9.9
                 * discount_price : 9.9
                 * discount_num : 0
                 * price_num : 1
                 * total_price : 10
                 * total_discount_price : 9.9
                 * bargain_id : 0
                 */

                private String cid;
                private String child_id;
                private String goods_id;
                private String thumb;
                private String title;
                private String option_title;
                private String num;
                private String price;
                private String discount_price;
                private String discount_num;
                private String price_num;
                private String total_price;
                private double total_discount_price;
                private String bargain_id;

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

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
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

                public String getOption_title() {
                    return option_title;
                }

                public void setOption_title(String option_title) {
                    this.option_title = option_title;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
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

                public String getDiscount_num() {
                    return discount_num;
                }

                public void setDiscount_num(String discount_num) {
                    this.discount_num = discount_num;
                }

                public String getPrice_num() {
                    return price_num;
                }

                public void setPrice_num(String price_num) {
                    this.price_num = price_num;
                }

                public String getTotal_price() {
                    return total_price;
                }

                public void setTotal_price(String total_price) {
                    this.total_price = total_price;
                }

                public double getTotal_discount_price() {
                    return total_discount_price;
                }

                public void setTotal_discount_price(double total_discount_price) {
                    this.total_discount_price = total_discount_price;
                }

                public String getBargain_id() {
                    return bargain_id;
                }

                public void setBargain_id(String bargain_id) {
                    this.bargain_id = bargain_id;
                }
            }
        }
    }
}
