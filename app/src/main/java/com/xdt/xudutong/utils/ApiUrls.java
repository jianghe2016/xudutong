/**
 * 创建日期:2016年10月8日下午3:46:27
 * 作者:Administrator
 * 描述:TODO
 */
package com.xdt.xudutong.utils;

/***

 */
public class ApiUrls {

    public static final String HOST = "http://app.xudutong.com/xudutongapp/";
    public static final String HOST2 = "http://app.xudutong.com/";
    //public static final String HOST = "http://192.168.10.124:8080/xudutongapp/";
//    public static final String HOST = "http://192.168.10.107:8082/xudutongapp/";
    // public static final String HOST = "http://app.xcsjmykt.com:8001/xudutongapp/";
    //测试外网
//    public static final String HOST = "http://lsa.xudutong.com/";
    //测试服务器
    // public static final String HOST = "http://app.xcsjmykt.com/xudutongapp/";
    // http://192.168.70.119:8080/xudutongapp
    //家庭住址的选填，主要用于家庭医生和健康许昌用，注册让用户选填
    public static final String FINDHUJIDIS = HOST + "hujidi/findHujidis";
    /*	public static final String HOST="http://192.168.2.180:8080/xudutongapp/";*/
    //获取更新的版本号
    /*public static final String GETLATESTVERSION ="http://192.168.2.65:8081/xudutongapp/version/getLatestApp";*/
    public static final String GETLATESTVERSION = HOST + "version/getLatestVersion";
    //public static final String GETLATESTVERSION ad= HOST + "version/getLatestApp";
    //查询天气预报信息
    public static final String GETWEATHERLIST = HOST + "weather/getWeatherList";
    //查询当天的天气预报信息
    public static final String GETWEATHER = HOST + "weather/getWeather";
    //查询空气质量信息
    public static final String GETAIRQUALITY = HOST + "weather/getAirQuality";
    //查询天气预报城市列表
    public static final String FINDCITY = HOST + "weather/findcity";
    //模糊查询天气预报城市
    public static final String LIKECITY = HOST + "weather/likecity";
    //验证卡号和查询密码
    public static final String VERIFICATION = HOST + "city/checkSearchPwd";
    //消费接口
    public static final String CONSUMPTION = HOST + "city/searchSales";
    //充值接口
    public static final String RECHARGE = HOST + "city/searchRecharge";
    //卡管理解绑接口
    public static final String UNBINDCARD = HOST + "city/unbindCard";
    //卡挂失接口
    public static final String CARDLOSS = HOST + "city/cardLoss";
    //更新用户密码查詢姓名和身份證是否存在checkName
    public static final String CHECKNAME = HOST + "buscard/checkName";
    //更新用户密码用户修改密码
    public static final String UPDATEPWD = HOST + "vip/updatePwd";
    //查询余额
    public static final String LOADBlANCE = HOST + "buscard/loadBlance";
    //查询电子现金余额
    public static final String LOADCARDBlADNCE = HOST + "buscard/loadCardBlance";
    //查询普通卡乘车记录
    public static final String GETRECORDBYNAME = HOST + "buscard/getRecordByName";
    //电子乘车
    public static final String GETRECORDBYTHIRDCARDNO = HOST + "buscard/getRecordByThirdCardNo";
    //列车查询
    public static final String GETTRAINTICKET = HOST + "trainTicket/getTrainTicket";
    //公交城市的选择
    public static final String SELECTCITY = HOST + "mobilebus/selectCity";
    //公交路线查询
    public static final String QUERYBUSLINEINFO = HOST + "mobilebus/queryBusLineInfo";
    //跑马灯字体
    public static final String GETACTIVITYTITLE = HOST + "activity/getActivityTitle";
    //跑马灯点击条目进去的地址
    public static final String GETACTIVITY = HOST + "activity/getActivity";
    //社保查询
    public static final String GETXCSOCIALSECURITY = HOST + "socialSecurity/getXcSocialSecurity";
    //公积金查询
    public static final String GETXCPUBFUND = HOST + "pubfund/getXcPubfund";
    //违章查询
    public static final String GETXCWEIZHANG = HOST + "peccancy/getXcWeizhang";
    //物业查询
    public static final String GETCOMMUNITYS = HOST + "zwjfee/getCommunitys";
    //物业查询楼宇查询
    public static final String GETBUILDINGS = HOST + "zwjfee/getBuildings";
    //物业查询单元查询
    public static final String GETUNITS = HOST + "zwjfee/getUnits";
    //物业查询房间查询
    public static final String GETHOUSES = HOST + "zwjfee/getHouses";
    //物业查询欠费信息
    public static final String GETBILLINFOS = HOST + "zwjfee/getBillInfos";
    //快递查询详情
    public static final String QUERYKUAIDIINFO = HOST + "kuaidi/queryKuaidiInfo";
    //图书查询
    public static final String QUERYBOOKS = HOST + "library/querybooks";
    //图书详情查询
    public static final String GETBOOKINFO = HOST + "library/getbookinfo";
    //政务查询
    public static final String SELECTYELLOWPAGE = HOST + "yellowPage/selectYellowPage";
    //风景查询
    public static final String SELECTVIEW = HOST + "view/selectView";
    //本地生活吗，景点显示
    //public static final String JINGDIANGETVIEW = "http://172.18.33.238:8080/xudutongapp/view/getView";
    public static final String JINGDIANGETVIEW = "http://app.xcsjmykt.com/xudutongapp/view/getView";
    //周边查询
    //本地服务6个按钮
    public static final String SELECTCOMPANY = HOST + "yellowPage/selectCompany";
    //本地生活新闻查询
    public static final String SELECTPRESS = HOST + "press/selectPress";
    //本地生活新闻收藏
    public static final String COLLECTPRESS = HOST + "press/collectPress";
    //本地生活新闻取消收藏
    public static final String DELETECOLLECTPRESS = HOST + "press/deleteCollectPress";
    //登录功能
    public static final String AUTH = HOST + "token/auth";
    // public static final String AUTH ="http://192.168.10.107:8082/xudutongapp/token/auth";
    //忘记密码，查询用户是否存在
    public static final String ISUSEREXIST = HOST + "vip/isUserExist";
    //修改用户密码
    public static final String UPDATEPSW = HOST + "vip/updatePsw";
    //用户注册验证码获取
/*    public static final String VERIFYCODE = HOST + "verifyCode/get";*/
    public static final String VERIFYCODE = HOST + "verifyCode/getMsg";
    //用户忘记密码验证码获取
    public static final String MSGVERIFY = HOST + "vip/MsgVerify";
    //解绑许都通卡验证码接口(由于未知原因，暂未使用)
    public static final String SENDMSG = HOST + "verifyCode/sendMsg";
    //修改用户密码——校验原密码是否一致
    //public static final String CHECKPWD = HOST + "vip/checkPwd";
    public static final String CHECKPWD = HOST + "vip/checkPwd";
    //用户注册
    public static final String REGISTER = HOST + "vip/register";
    //个人中心页面昵称显示的获取
    public static final String LOADUSERINFO = HOST + "vip/loadUserInfo";
    //用户收藏新闻
    public static final String SELECTCOLLECTPRESS = HOST + "press/selectCollectPress";

    //更新用户信息
    public static final String UPDATEUSERINFO = HOST + "vip/updateUserInfo";
    //查询用户已绑定卡信息
    public static final String GETUSERCARDS = HOST + "city/getUserCards";

    public static final String GETBALANCE = HOST + "city/getBalance";
    //用户绑定一卡通										  city/userBindCard
    public static final String USERBINDCARDS = HOST + "city/userBindCard";
    //实名认证信息提交
    public static final String CERTIFICATIONS = HOST + "idcard/certification";
    //实名认证查询
    public static final String GETCERT = HOST + "idcard/getCert";
    //实名认证更新
    public static final String UPDATECERT = HOST + "idcard/updateCert";
    //上传头像
    public static final String UPLOADHEADIMG = HOST + "vip/uploadHeadImg";
    //个人公积金基本信息查询接口
    public static final String GETHOUSINGFUND = HOST + "dsep/getHousingFund";
    //个人养老保险信息查询接口
    public static final String GETENDOWMENTINSURANCE = HOST + "dsep/getEndowmentInsurance";
    //个人医疗保险信息查询接口
    public static final String GETMEDICALINSURANCE = HOST + "dsep/getMedicalInsurance";
    //个人工伤保险信息查询接口
    public static final String GETEMPLOYMENTINJURYINSURANCE = HOST + "dsep/getEmploymentInjuryInsurance";
    //个人失业保险信息查询接口
    public static final String GETUNEMPLOYMENTINSURANCE = HOST + "dsep/getUnemploymentInsurance";
    //个人工伤保险信息查询接口
    public static final String GETMATERNITYINSURANCE = HOST + "dsep/getMaternityInsurance";
    //机关单位基本信息查询接口
    public static final String GETGOVERNMENTUNIT = HOST + "dsep/getGovernmentUnit";
    //事业单位基本信息查询接口
    public static final String GETINSTITUTIONBEAN = HOST + "dsep/getInstitutionBean";
    //中考成绩信息查询接口
    public static final String GETSENIORENTRANCEEXAM = HOST + "dsep/getSeniorEntranceExam";
    //企业基本信息查询接口
    public static final String GETENTERPRISE = HOST + "dsep/getEnterprise";
    //个体工商户基本信息查询接口
    public static final String GETPRIVATELY = HOST + "dsep/getPrivately";
    //个人参保信息查询接口
    public static final String GETSOCIALINSURANCE = HOST + "dsep/getSocialInsurance";
    //小绿接口
    public static final String GETGREENLIST = HOST + "littlegreen/getLittleGreen";
    //小绿记录
    //public static final String MAKECARDRECORD = "http://192.168.70.181:8080/ws-bike/makecard/record";
    //public static final String WSBIKEAPPSCANCODESCANCODE = "http://117.158.96.124:8081/ws-bike/appScanCode/scanCode";
    public static final String WSBIKEAPPSCANCODESCANCODE = HOST2 + "ws-bike/appScanCode/scanCode";
    //小绿计费
    public static final String WSBIKEAPPSCANCODEPAYMENT = HOST2 + "ws-bike/appScanCode/payment";
    //校验不良租车记录
    public static final String WSBIKEAPPSCANCODECHECKCREDITRECORD = HOST2 + "ws-bike/appScanCode/checkCreditrecord";
    //小绿开锁接口
    public static final String APPSCANCODEOPENLOCK = HOST2 + "ws-bike/appScanCode/openLock";
    //校验不良租车记录
    public static final String WSBIKEAPPSCANCODECYCLINGRECORD = HOST2 + "ws-bike/appScanCode/cyclingRecord";
    //小绿骑行记录
    public static final String MAKECARDRECORD = HOST + "littlegreen/getLittleGreenRec";
    //小绿异常订单
    public static final String XIAO_LV_EXCEPTION_ORDER = HOST2+"ws-bike/appScanCode/exceptionOrder";
    //激活网点查询branch/getBranchlist
    public static final String GETBRANCHLIST = HOST + "branch/getBranchlist";
    //全部网点分页查询
    public static final String GETDISTANCELISTBYPAGE = HOST + "branch/getDistanceListBypage";
    //注册协议
    public static final String GETPROTOCOL = HOST + "protocol/getProtocol";
    //信息反馈
    public static final String ADDFEEDBACK = HOST + "feedback/addfeedback";
    //设置里的消息推送的设置
    public static final String FINDPUSH = HOST + "push/findPush";
    //主页面搜索站内应用
    public static final String LOADAPPIMG = HOST + "apply/loadAppimg";
    //token刷新接口
    public static final String REFRESHTOKEN = HOST + "token/refreshtoken";

    //本地生活图片请求
    public static final String GETLOCALIMG = "http://app.xcsjmykt.com/xudutongapp/slides/getLocalImg";
    //主页面天健请求单位角色
    //public static final String TIANJIANACTION = "http://app.medvision.com.cn:18081/SignDocServer/xztAction.do";
    public static final String TIANJIANACTION = "http://222.143.158.84:8082/SignDocServer/xztAction.do";
    //商户系统查询
    public static final String MERCHANTLOGIN = HOST + "merchant/login";
    // public static final String MERCHANTLOGIN = "http://app.xcsjmykt.com:8001/xudutongapp/merchant/login";
    //商户选择交易网点
    public static final String GETBASEEMPLOYEE = HOST + "merchant/getBaseEmployee";
    //验证用户是否存在
    public static final String VIPCHECKPHONE = HOST + "vip/checkPhone";
    //更新手机号
    public static final String VIPUPDATEPHONE = HOST + "vip/updatePhone";
    //更换手机号发送验证码接口
    public static final String MODIFYPHONEMSGTEST = HOST + "verifyCode/modifyPhoneMsg";
    // public static final String MODIFYPHONEMSGTEST = HOST+"verifyCode/modifyPhoneMsgTest";
    //手机运营商和归属地查询
    public static final String CHECKAREA = HOST + "dxboss/checkArea";
    //电信手机号余额查询
    public static final String GETTELBALANCE = HOST + "dxboss/getTelbalance";
    //订单接口文档
    public static final String ACPINDENTTOTEMPORAY = "https://172.18.33.237:8443/xudutong-order/acpIndent/toTemporay";
    //消费交易接口文档（订单平台发送订单推送请求至中国银联全渠道平台）
    public static final String APPCONSUMERGETTN = "https://order.xudutong.com/order/appConsumer/getTn";
    //查询用户所有消息
    public static final String PUSHMESSAGEMANAGEMENT = HOST + "push/messageManagement";
    //查询消息详情
    public static final String DETAILEDINFORMATION = HOST + "push/detailedInformation";
    //将信息设为已读
    public static final String PUSHREAD = HOST + "push/read";
    //删除消息
    public static final String DELMESSAGEMANAGEMENT = HOST + "push/delMessagemanagement";
    //惠民宝功能地址
    public static final String  PAGEINFOFUNDPAGE= "http://192.168.10.124:8020/xudutongWeb/nativeInteraction/fundIndex.html";
    //工商开户接口，发送短信接口
    public static final String ACCOUNTOPEN = "http://192.168.10.175:8083/xudutong-icbc/settlement/accountOpen";
    //信息确认提交接口
    public static final String ACCOUNTSCODEVERIFY = "http://192.168.10.175:8083/xudutong-icbc/settlement/ accountSCodeVerify";
    //工商银行二类户结算账户绑卡接口
    public static final String ACCOUNTBINDING = "http://192.168.10.175:8080/xudutong-icbc/settlement/accountBinding";
    //工商银行二类户结算账户解绑接口
    public static final String ACCOUNTUNBINDING = "http://192.168.10.175:8080/xudutong-icbc/settlement/accountUnbinding";
    //工商银行二类户结算账户充值接口
    public static final String ACCOUNTRECHARGE = "http://192.168.10.175:8080/xudutong-icbc/settlement/accountRecharge";
    //工商银行二类户结算账户提现接口
    public static final String ACCOUNTWITHDRAW = "http://192.168.10.175:8080/xudutong-icbc/settlement/accountWithdraw";
    //工商银行二类户结算账户短信验证码发送接口(超过60s走的短信接口)
    public static final String ACCOUNTSCODESEND = "http://192.168.10.175:8080/xudutong-icbc/settlement/accountSCodeSend";
    //工商银行二类户结算账户交易申请单查询接口
    public static final String ACCOUNTAPPLYQUERY = "http://192.168.10.175:8080/xudutong-icbc/settlement/accountApplyQuery";
    //工商银行二类户结算账户开户或绑卡申请查询接口
    public static final String ACCOUNTOPENQUERY = "http://192.168.10.175:8080/xudutong-icbc/settlement/accountOpenQuery";
    //工商银行二类户结算账户绑卡信息查询接口
    public static final String ACCOUNTBINDINGQUERY = "http://192.168.10.175:8080/xudutong-icbc/settlement/accountBindingQuery";
    //工商银行二类户结算账户余额查询接口
    public static final String ACCOUNBALANCEQUERY = "http://192.168.10.175:8080/xudutong-icbc/settlement/accounBalanceQuery";
    //工商银行二类户结算账户余额查询接口
    public static final String ACCOUNDETAILQUERY = "http://192.168.10.175:8080/xudutong-icbc/settlement/accounDetailQuery";
    //工商银行二类户结算账户信息查询接口
    public static final String ACCOUNINFOQUERY = "http://192.168.10.175:8080/xudutong-icbc/settlement/accounInfoQuery";
    //工商银行二类户结算账户保留接口
    public static final String ACCOUNUNHOLD = "http://192.168.10.175:8080/xudutong-icbc/settlement/accounUnhold";
    //工商银行二类户结算账户解除保留接口
    public static final String ACCOUNHOLD = "http://192.168.10.175:8080/xudutong-icbc/settlement/accounHold";
    //居民电子健康卡http接口
    public static final String DIANZIJUMINKA ="http://192.168.10.252:8080/xudutong-erhcm/erhcmAccount/erhcmAccountRegister";
    //居民电子健康卡https接口
    public static final String DIANZIJUMINKA_HTTPS ="https://app.xudutong.com/xudutong-erhcm/erhcmAccount/erhcmAccountRegister";//https://192.168.10.252:8443/xudutong-erhcm/erhcmAccount/erhcmAccountRegister
    //电子居民健康卡动态付款二维码接口
    public static final String DYNAMIC_PAYMENT ="https://app.xudutong.com/xudutong-erhcm/erhcmQRCodeCreate/erhcmQRCodePay";

    public static final String TEST_SERVICE = "http://192.168.10.107:8082/xudutongapp/vip/loadUserInfo";
    //设置交易密码
    public static final String SETTING_PAY_PSD = HOST+ "vip/paypassword";
    //校验交易密码
    public static final String Verification_PAY_PSD =HOST+ "vip/paymentVerification";
    //基金余额接口
    public static final String HUI_MIN_BAO_YU_E= "http://192.168.10.175:8080/xudutong-icbc/fund/fundBalancequery";
    //工商银行基金明细
    public static final String HUI_MIN_BAO_JI_JIN_MINGXI= "http://192.168.10.175:8080/xudutong-icbc/fund/fundDetailsquery";
    //基金收益查询
    public static final String HUI_MIN_BAO_JI_JIN_EARNINGS= "http://192.168.10.175:8080/xudutong-icbc/fund/fundIncomequery";
    //基金可疑订单查询
    public static final String HUI_MIN_BAO_JI_JIN_ORDER= "http://192.168.10.175:8080/xudutong-icbc/fund/fundOrderquery";
    //基金净值查询
    public static final String HUI_MIN_BAO_JI_JIN_JINGZHI= "http://192.168.10.175:8080/xudutong-icbc/fund/fundPricequery";
    //基金购买
    public static final String HUI_MIN_BAO_JI_JIN_BUY= "http://192.168.10.175:8080/xudutong-icbc/fund/fundPurchase";
    //基金赎回
    public static final String HUI_MIN_BAO_JI_JIN_SHUHUI= "http://192.168.10.175:8080/xudutong-icbc/fund/fundRedeem";
    //基金净值折线图
    public static final String HUI_MIN_BAO_JI_JIN_ZHEXIANTU= "http://192.168.10.107:8081/xudutong-icbc/fund/selectYearRate";
    //充值缴费
    public static final String JIAO_FEI = "https://order.xudutong.com/order/acpIndent/toTemporay";
}
