package com.byx.xiuboss.xiuboss.NetUrl;

public class AppUrl {

    static String testUrl = "https://dev.ourdaidai.com";//测试环境
    static String OnlineUrl = "https://www.ourdaidai.com";//线上环境

    //static String BaseUrl = OnlineUrl;//正式环境
    static String BaseUrl = testUrl;//测试环境


    /**
     * 登录接口
     **/
    public static final String LOGIN_URL = BaseUrl + "/CI/index.php/Login/login";
    /**
     * 首页请求
     */
    public static final String HOME_URL = BaseUrl + "/CI/index.php/StoreMy/storeHome";
    /**
     * 签到成功请求
     */
    public static final String SIGN_IN_URL = BaseUrl + "/CI/index.php/StoreMy/reward";
    /**
     * 今日收款
     */
    public static final String TodayMoney_URL = BaseUrl + "/CI/index.php/Store/todayMoney";
    /**
     * 每日收入明细
     */
    public static final String EVERYDAY_URL = BaseUrl + "/CI/index.php/Store/storeMoneySum";
    /**
     * 我的页面请求数据
     */
    public static final String MYPAGE_URL = BaseUrl + "/CI/index.php/StoreMy/myHome";

    /**
     * 收款页面商家扣款
     */
    public static final String COLLECT_URL = BaseUrl + "/CI/index.php/StoreMy/collectPay ";
    /**
     * 商品管理页面加载H5
     */
    public static final String MANAGE_GOODS_URL = BaseUrl + "/app/index.php?i=2&c=entry&ctrl=manage&ac=goods&op=index&do=mobile&m=we7_wmall";
    /**
     * 钱包（账户余额页面）
     */
    public static final String ACCOUNT_BALANCE = BaseUrl + "/CI/index.php/StoreMy/carryLog";
    /**
     * 显示收款码页面
     */
    public static final String QRCODE_URL = BaseUrl + "/CI/index.php/StoreMy/qrcode";
    /**
     * 查看今日账单
     */
    public static final String TODAYBILL_URL = BaseUrl + "/CI/index.php/Store/todayMoney";
    /**
     * 调取H5页面提现页面
     */
    public static final String H5_URL = BaseUrl + "/lizhenhu/android_app_v2/withdraw.html";
    /**
     * 开启播报
     */
    public static final String OPEN_SWITCH = BaseUrl + "/CI/index.php/StoreMy/broadcast";
    /**
     * 管理店铺开启状态
     */
    public static final String STORE_MANAGER = BaseUrl + "/CI/index.php/Store/business";
    /**
     * 切换店铺页面
     */
    public static final String SWITCH_SHOP_URL = BaseUrl + "/CI/index.php/StoreMy/switchStore";
    /**
     * 订单页标题
     */
    public static final String ORDER_LIST = BaseUrl + "/CI/index.php/order/cart";
    /**
     * 订单组列表
     */
    public static final String ORDER_GROUP = BaseUrl + "/CI/index.php/order/orderLog";
    /**
     * 获取订单自提数据
     */
    public static final String ORDER_MEGAVE = BaseUrl + "/CI/index.php/order/orderType";

    /**
     * 点击确定或取消订单按钮按钮
     */
    public static final String ORDER_COMFIM = BaseUrl + "/CI/index.php/Order/orderStatus";
    /**
     * 加载webView路径
     */
    public static final String ORDER_WEBURL = BaseUrl + "/app/index.php?i=2&c=entry&ctrl=manage&ac=order&op=takeout&ta=detail&do=mobile&m=we7_wmall&id=";
    /**
     * 订单商户同意和拒绝退单
     */
    public static final String ORDER_CMRR = BaseUrl + "/CI/index.php/order/orderRefund";

    /**
     * 订单商户取消用户订单
     */
    public static final String ORDER_CANCEL = BaseUrl + "/CI/index.php/order/updOrder";

    /**
     * 首页新人领取鼓励金接口
     */
    public static final String newPerson = BaseUrl + "/CI/index.php/StoreMy/StoreNewEncouragement";

    /**
     * 商家帮助中心页面
     **/

    public static final String HELP_URL = BaseUrl + "/lizhenhu/app_feedback/index.html";


    /**
     * 验证码登录获取验证码接口
     */
    public static final String GET_LOGINCODE = BaseUrl + "/CI/index.php/StoreMy/proving";

    /**
     * 检测应用是否更新的接口
     */
    public static final String GET_VERSIONCODE = "https://www.ourdaidai.com/api/checkVersion";

    /**
     * 首页数据
     */
    public static final String INDEXDATA_URL = BaseUrl + "/CI/ST12.php/StoreInfo/Home";

    /**
     * 收款记录
     */
    public static final String ORDERLIST_URL = BaseUrl + "/CI/ST12.php/Customer/OrderList";

    /**
     * 返现接口数据
     */
    public static final String GETCASH_URL = BaseUrl + "/CI/ST12.php/StoreInfo/GetCash";

    /**
     * 提现记录
     **/
    public static final String CASHRECORD_URL = BaseUrl + "/CI/ST12.php/StoreInfo/GetCashList";

   /**我的页面**/

   public static final String NEWMY_URL = BaseUrl+"/CI/ST12.php/StoreMy/myHome";



    /**
     * 奖励荣誉分规则
     **/
    public static final String RETURNCASH_URL = BaseUrl + "/CI/ST12.php/StoreInfo/returnCashSteps";

    /**
     * 结算记录
     **/
    public static final String RESULTSETTLE_URL = BaseUrl + "/CI/ST12.php/Customer/settleAccounts";

    /** 新版登录**/

    public static final String LOGINNEW_URL = BaseUrl + "/CI/ST12.php/Login/login";

    /**设置比例**/

    public static final String CASHRADIO_URL = BaseUrl + "/CI/ST12.php/StoreInfo/Set";


    /**发现页面**/

    public static final String FIND_URL = BaseUrl + "/CI/ST12.php/StoreInfo/Finds";




}
