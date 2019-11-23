package com.zhowin.study.voide.config;

import android.content.Context;
import android.text.TextUtils;

import com.zhowin.study.BuildConfig;
import com.zhowin.study.base.BaseApplication;
import com.zhowin.study.utils.PreferenceUtils;
import com.zhowin.study.voide.bean.ConfigBean;

import java.util.Objects;

/**
 * author Z_B
 * date :2019/11/23 11:55
 * description:
 */
public class AppConfig {
    public static final String TAG = "roamer";
    public static final boolean DEBUG = true;
    /* 应用程序包名 */
    // 这个用于过滤广播，必须不同应用使用不同字符串，否则广播串线，
    public static final String sPackageName = BuildConfig.APPLICATION_ID;
    public static final String apiKey = "";

    /* 分页的Size */
    public static final int PAGE_SIZE = 50;
    /* 请求配置的接口 */
//    public static String CONFIG_URL = "http://imapi.shiku.co/config";// 正式的服务器
    public static String CONFIG_URL = "https://121.40.224.82:8443/123123";// 惠敏的服务器
    /* 基本地址 */
    public String apiUrl;// Api的服务器地址
    public String uploadUrl;// 上传的服务器地址
    public String downloadAvatarUrl;// 头像下载地址
    public String XMPPHost;  // Xmpp服务器地址
    public int mXMPPPort = 5222;
    public String XMPPDomain;
    public int xmppPingTime; // 每隔xmppPingTime秒ping一次服务器
    public int XMPPTimeOut;  // Xmpp超时时长(服务器针对客户端的超时时长)
    public String JitsiServer;// jitsi前缀地址
    /* Api地址--》衍生地址 */
    /* 注册登录 */
    public String USER_LOGIN;// 登陆
    public String USER_REGISTER;// 注册
    public String USER_PASSWORD_RESET;// 用户重置密码
    public String USER_LOGIN_AUTO;// 检测Token是否过期 0未更换 1更换过
    public String USER_GETCODE_IMAGE; // 获取图形验证码
    public String SEND_AUTH_CODE;// 获取手机验证码
    public String VERIFY_TELEPHONE;// 验证手机号有没有被注册
    /* 用户 */
    public String USER_UPDATE; // 用户资料修改
    public String USER_GET_URL;// 获取用户资料，更新本地数据的接口
    public String USER_PHOTO_LIST;// 获取相册列表
    public String USER_QUERY;// 查询用户列表
    public String USER_NEAR; // 查询搜索列表
    public String USER_SET_PRIVACY_SETTING;// 设置用户隐私设置
    public String USER_GET_PRIVACY_SETTING;// 获取用户隐私设置
    public String USER_GET_PUBLIC_MENU; // 获取公众号菜单
    public String USER_DEL_CHATMESSAGE; // 删除聊天记录
    public String USER_ADD_COURSE; // 添加课程
    public String USER_QUERY_COURSE; // 查询课程
    public String USER_EDIT_COURSE;  // 修改课程
    public String USER_DEL_COURSE;   // 删除课程
    public String USER_COURSE_DATAILS; // 课程详情
    /* 附近 */
    public String NEARBY_USER;// 获取附近的用户
    /* 商务圈 */
    public String USER_CIRCLE_MESSAGE;// 获取某个人的商务圈消息
    public String DOWNLOAD_CIRCLE_MESSAGE;// 下载商务圈消息
    /* 好友 */
    public String FRIENDS_ATTENTION_LIST;// 获取关注列表
    public String FRIENDS_REMARK;// 备注好友
    public String FRIENDS_BLACKLIST_ADD;// 拉黑
    public String FRIENDS_ATTENTION_DELETE;// 取消关注
    public String FRIENDS_DELETE;// 删除好友
    public String FRIENDS_ATTENTION_ADD;// 加关注
    public String Add_friends;
    public String FRIENDS_BLACKLIST_DELETE;// 取消拉黑
    public String FRIENDS_UPDATE; // 消息保存天数
    public String FRIENDS_NOPULL_MSG; // 屏蔽好友消息
    /* 群聊 */
    public String ROOM_ADD;// 创建群组
    public String ROOM_DELETE;// 删除群组
    public String ROOM_MEMBER_DELETE;// 删除成员
    public String ROOM_UPDATE;// 设置群组
    public String ROOM_GET;// 获取群组
    public String ROOM_LIST;// 获取群组列表
    public String ROOM_MEMBER_UPDATE;// 设置群员
    public String ROOM_MEMBER_GET;// 获取成员
    public String ROOM_MEMBER_LIST;// 获取成员列表
    public String ROOM_LIST_HIS;// 获取某个用户已经加入的房间列表
    public String ROOM_JOIN;// 加入房间
    public String ROOM_MANAGER;// 指定管理员
    public String ROOM_UPDATE_ROLE;// 指定隐身人，监控人，
    public String ROOM_DISTURB;// 消息免打扰
    public String ROOM_TRANSFER;// 转让群组
    public String ROOM_DELETE_NOTICE;// 删除群公告
    public String ROOM_GET_ROOM;// 获取自己的成员信息以及群属性
    /* 商务圈 */
    public String MSG_ADD_URL;// 发布一条公共消息的接口
    public String MSG_LIST;// 获取公共消息的接口
    public String MSG_USER_LIST;// 获取某个用户的最新公共消息
    public String MSG_GETS;// 根据IDS批量获取公共消息的接口(我的商务圈使用)
    public String MSG_GET;// 根据ID获取公共消息
    public String CIRCLE_MSG_DELETE;// 删除一条商务圈消息
    public String MSG_PRAISE_ADD;// 赞
    public String MSG_PRAISE_DELETE;// 取消赞
    public String MSG_COLLECT_DELETE;//  取消朋友圈收藏，
    public String CIRCLE_MSG_LATEST;// 获取人才榜最新消息
    public String CIRCLE_MSG_HOT;// 最热人才榜
    public String MSG_COMMENT_ADD;// 增加一条评论
    public String MSG_COMMENT_DELETE;// 删除一条评论
    public String MSG_COMMENT_LIST;// 获取评论列表
    /* 上传 的服务器地址--》衍生地址 */
    public String UPLOAD_URL;// 上传图片接口
    public String AVATAR_UPLOAD_URL;// 上传头像接口
    public String UPLOAD_MUC_FILE_ADD;// 上传群文件接口
    public String UPLOAD_MUC_FILE_FIND_ALL;// 查询所有文件
    public String UPLOAD_MUC_FILE_FIND;// 查询单个文件
    public String UPLOAD_MUC_FILE_DEL;//  删除单个群文件
    /* 头像下载地址--》衍生地址 */
    public String AVATAR_ORIGINAL_PREFIX;// 头像原图前缀地址
    public String AVATAR_THUMB_PREFIX;   // 头像缩略图前缀地址
    /* 登出地址 */
    public String USER_LOGOUT;
    /* 保存最后一次在线时间地址 */
    public String USER_OUTTIME;
    /* 红包相关的URL变量*/
    public String RECHARGE_ADD; // 余额充值
    public String RECHARGE_GET; // 余额查询
    public String REDPACKET_SEND; // 发送红包
    public String RENDPACKET_GET; // 获取红包详情
    public String REDPACKET_OPEN; // 打开红包
    public String SEND_REDPACKET_LIST_GET; // 获取发出的红包
    public String RECIVE_REDPACKET_LIST_GET; // 获取收到的红包
    public String CONSUMERECORD_GET; // 获取消费记录列表
    public String VX_RECHARGE;    // 微信 充值
    public String VX_UPLOAD_CODE; // 微信 上传
    public String VX_TRANSFER_PAY;// 微信 提现
    /* 抖音视频获取接口 */
    public String GET_TRILL_LIST;// 获取视频列表
    public String GET_MUSIC_LIST;// 获取音乐列表 http://47.91.232.3:8092/music/list?access_token=20bc3cbda93241a8903a999a5ad71625&pageIndex=0&pageSize=20&keyword=94
    /* 直播 */
    public String GET_LIVE_ROOM_LIST;// 获取直播间列表
    public String CREATE_LIVE_ROOM;// 创建直播间
    public String JOIN_LIVE_ROOM;// 加入直播间
    public String EXIT_LIVE_ROOM;// 退出直播间
    public String DELETE_LIVE_ROOM;// 删除直播间
    public String LIVE_ROOM_DETAIL;// 直播间详情
    public String LIVE_ROOM_MEMBER_LIST;// 直播间成员列表
    public String LIVE_ROOM_DANMU;// 发送弹幕
    public String GET_LIVE_GIFT_LIST;// 获取礼物列表
    public String LIVE_ROOM_GIFT;// 发送礼物
    public String LIVE_ROOM_PRAISE;// 发送爱心
    public String LIVE_ROOM_GET_IDENTITY;// 获取身份信息
    public String LIVE_ROOM_UPDATE;// 修改直播间
    public String LIVE_ROOM_SET_MANAGER;// 设置管理员
    public String LIVE_ROOM_SHUT_UP;// 禁言/取消禁言
    public String LIVE_ROOM_KICK;// 踢人
    public String LIVE_ROOM_STATE;// 正在直播 || 未开启直播
    public String LIVE_GET_LIVEROOM;// 获取自己的直播间
    public String EMPTY_SERVER_MESSAGE;// 清空服务端数据
    /* 组织架构 */
    public String AUTOMATIC_SEARCH_COMPANY;//自动查询公司
    public String CREATE_COMPANY; // 创建公司
    public String SET_COMPANY_MANAGER; // 指定管理者
    public String MODIFY_COMPANY_NAME; // 修改公司名称
    public String CHANGE_COMPANY_NOTIFICATION; // 更改公司公告
    public String SEARCH_COMPANY; // 查找公司
    public String DELETE_COMPANY; // 删除公司
    public String CREATE_DEPARTMENT; // 创建部门
    public String MODIFY_DEPARTMENT_NAME; // 修改部门名称
    public String DELETE_DEPARTMENT; // 删除部门
    public String ADD_EMPLOYEE; // 添加员工
    public String DELETE_EMPLOYEE; // 删除员工
    public String MODIFY_EMPLOYEE_DEPARTMENT; // 更改员工部门
    public String COMPANY_LIST; // 公司列表
    public String DEPARTMENT_LIST; // 部门列表
    public String EMPLOYEE_LIST; // 员工列表
    public String GET_COMPANY_DETAIL; // 获取公司详情
    public String GET_EMPLOYEE_DETAIL; // 获取员工详情
    public String GET_DEPARTMENT_DETAIL; // 获取部门详情
    public String GET_EMPLOYEE_NUMBER_OF_COMPANY; // 获取公司员工人数
    public String EXIT_COMPANY;// 退出公司
    public String CHANGE_EMPLOYEE_IDENTITY;// 修改身份公司
    /* 标签 */
    public String FRIENDGROUP_LIST;// 获取标签列表
    public String FRIENDGROUP_ADD;// 添加标签
    public String FRIENDGROUP_DELETE;// 删除标签
    public String FRIENDGROUP_UPDATE;// 修改标签名
    public String FRIENDGROUP_UPDATEGROUPUSERLIST;// 标签下的好友增、删
    public String FRIENDGROUP_UPDATEFRIEND;
    /* 消息漫游 */
    public String GET_LAST_CHAT_LIST; // 获取最近一条的聊天记录列表
    public String GET_CHAT_MSG; // 获取单聊漫游的消息
    public String GET_CHAT_MSG_MUC; // 获取群聊漫游的消息
    /* 收藏 */
    public String Collection_ADD; // 添加收藏
    public String Collection_REMOVE;// 删除收藏
    public String Collection_LIST;// 表情列表
    public String Collection_LIST_OTHER;// 收藏列表
    public String USER_REPORT;// 举报用户 || 群组
    public String UPLOAD_COPY_FILE;// 服务端将文件拷贝一份，更换另外一个url地址返回
    public String ADDRESSBOOK_UPLOAD;// 上传本地联系人
    public String ADDRESSBOOK_GETALL;// 查询通讯录好友
    public String ADDENTION_BATCH_ADD;// 联系人内加好友 不需要验证
    // 支付密码，
    public String CHECK_PAY_PASSWORD; // 检查支付密码，
    public String UPDATE_PAY_PASSWORD;// 修改支付密码，
    // 集群 获取 meetUrl
    public String OPEN_MEET;
    // 以下是所有推送上传推送ID的接口，
    // 最后调用的是哪个，服务器端就是用哪个推送，
    // 小米推送接口
    public String configMi;
    // 华为推送接口
    public String configHw;
    // 极光推送接口
    public String configJg;
    // 登录分享sdk验证接口
    public String SDK_OPEN_AUTH_INTERFACE;
    // 是否开放注册，默认开放，服务器没返回这条配置也是默认开放，
    public boolean isOpenRegister;
    // 注册时是否需要验证码，
    public boolean isOpenSMSCode;
    // 关于页面的信息，
    public String companyName;
    public String copyright;
    // 是否隐藏好友搜索功能, true表示隐藏，
    public boolean isHideSearchFriend;
    // 是否使用新ui界面，主要影响发现页面，旧UI直接是生活圈，
    public boolean newUi = isShiku();

    public static boolean isShiku() {
        return Objects.equals(AppConfig.apiKey, "5e29f483c48848")
                && Objects.equals(BuildConfig.APPLICATION_ID, "com.sk.weichat");
    }

    public static String readConfigUrl(Context ctx) {
        String appUrl = PreferenceUtils.getString(ctx, "APP_SERVICE_CONFIG");
        if (TextUtils.isEmpty(appUrl)) {// 未手动输入过服务器配置，使用默认地址
            appUrl = AppConfig.CONFIG_URL;
        }
        return appUrl.replaceAll(" ", "");// 手动输入可能会有一些空格，替换掉
    }

    public static void saveConfigUrl(Context ctx, String str) {
        PreferenceUtils.putString(ctx, "APP_SERVICE_CONFIG", str + "/config");
    }

    /**
     * 会改变的配置
     **/
    public static AppConfig initConfig(ConfigBean configBean) {
        AppConfig config = new AppConfig();
        /* 1、Api 的服务器地址 */
        config.apiUrl = configBean.getApiUrl();

        /* 2、上传的服务器地址 */
        config.uploadUrl = configBean.getUploadUrl();

        /* 3、头像下载地址 */
        config.downloadAvatarUrl = configBean.getDownloadAvatarUrl();

        // 是否请求回执 0 不请求 1 请求
        int isOpenReceipt = configBean.getIsOpenReceipt();
        BaseApplication.IS_OPEN_RECEIPT = isOpenReceipt == 1 ? true : false;
        // 是否开放注册，
        String isOpenRegisterStr = configBean.getIsOpenRegister();
        boolean isOpenRegister = true;
        // 为0表示不开放注册，为1或者不存在表示开放注册，
        if ("0".equals(isOpenRegisterStr)) {
            isOpenRegister = false;
        }
        config.isOpenRegister = isOpenRegister;
        // 是否需要短信验证码，
        String isOpenSMSCodeStr = configBean.getIsOpenSMSCode();
        boolean isOpenSMSCode = false;
        // 为0表示不需要短信验证码，为1表示需要短信验证码，
        if ("1".equals(isOpenSMSCodeStr)) {
            isOpenSMSCode = true;
        }
        config.isOpenSMSCode = isOpenSMSCode;

        config.companyName = configBean.getCompanyName();
        config.copyright = configBean.getCopyright();

        config.isHideSearchFriend = configBean.getHideSearchByFriends() == 0;

        config.XMPPHost = configBean.getXMPPHost();

        config.XMPPDomain = configBean.getXMPPDomain();

        config.xmppPingTime = configBean.getXmppPingTime();

        config.XMPPTimeOut = configBean.getXMPPTimeout();

        config.JitsiServer = configBean.getJitsiServer();

        // apiUrl
        initApiUrls(config);
        initOthersUrls(config);
        return config;
    }

    private static void initApiUrls(AppConfig config) {
        String apiUrl = config.apiUrl;
        /* 登陆注册 */
        config.USER_LOGIN = apiUrl + "user/login";// 登陆
        config.USER_REGISTER = apiUrl + "user/register";// 注册
        config.USER_PASSWORD_RESET = apiUrl + "user/password/reset";// 用户重置密码
        config.USER_LOGIN_AUTO = apiUrl + "user/login/auto";// 检测Token是否过期
        config.USER_GETCODE_IMAGE = apiUrl + "getImgCode";// 获取图形验证码
        config.SEND_AUTH_CODE = apiUrl + "basic/randcode/sendSms";// 获取手机验证码

        config.VERIFY_TELEPHONE = apiUrl + "verify/telephone";// 验证手机号有没有被注册
        /* 用户 */
        config.USER_UPDATE = apiUrl + "user/update";// 用户资料修改
        config.USER_GET_URL = apiUrl + "user/get";  // 获取用户资料，更新本地数据的接口
        config.USER_PHOTO_LIST = apiUrl + "user/photo/list";// 获取相册列表
        config.USER_QUERY = apiUrl + "user/query";// 查询用户列表
        config.USER_NEAR = apiUrl + "nearby/user";// 查询搜索列表
        config.USER_SET_PRIVACY_SETTING = apiUrl + "/user/settings/update";// 设置用户隐私设置
        config.USER_GET_PRIVACY_SETTING = apiUrl + "/user/settings";// 查询用户隐私设置
        config.USER_GET_PUBLIC_MENU = apiUrl + "public/menu/list";// 获取公众号菜单，需要userType = 2 才能获取
        config.USER_DEL_CHATMESSAGE = apiUrl + "tigase/deleteMsg";// 删除某条聊天记录

        config.USER_ADD_COURSE = apiUrl + "user/course/add";     // 添加课程
        config.USER_QUERY_COURSE = apiUrl + "user/course/list";  // 查询课程
        config.USER_EDIT_COURSE = apiUrl + "user/course/update"; // 修改课程
        config.USER_DEL_COURSE = apiUrl + "user/course/delete";  // 删除课程
        config.USER_COURSE_DATAILS = apiUrl + "user/course/get"; // 获取课程

        /* 抖音 */
        config.GET_TRILL_LIST = apiUrl + "b/circle/msg/pureVideo";// 获取抖音视频列表
        config.GET_MUSIC_LIST = apiUrl + "music/list";// 获取抖音音乐列表

        /* 直播 */
        config.GET_LIVE_ROOM_LIST = apiUrl + "liveRoom/list";// 获取直播间列表
        config.CREATE_LIVE_ROOM = apiUrl + "liveRoom/create";// 创建直播间
        config.JOIN_LIVE_ROOM = apiUrl + "liveRoom/enterInto";// 加入直播间
        config.EXIT_LIVE_ROOM = apiUrl + "liveRoom/quit";// 退出直播间
        config.DELETE_LIVE_ROOM = apiUrl + "liveRoom/delete";// 删除直播间
        config.LIVE_ROOM_DETAIL = apiUrl + "liveRoom/get";// 直播间详情
        config.LIVE_ROOM_MEMBER_LIST = apiUrl + "liveRoom/memberList";// 获取直播间成员列表
        config.LIVE_ROOM_DANMU = apiUrl + "liveRoom/barrage";// 发送弹幕
        config.GET_LIVE_GIFT_LIST = apiUrl + "liveRoom/giftlist";// 得到礼物列表
        config.LIVE_ROOM_GIFT = apiUrl + "liveRoom/give";// 发送礼物

        config.LIVE_ROOM_PRAISE = apiUrl + "liveRoom/praise";// 发送点赞

        config.LIVE_ROOM_GET_IDENTITY = apiUrl + "liveRoom/get/member";// 获取身份信息
        config.LIVE_ROOM_UPDATE = apiUrl + "liveRoom/update";// 修改直播间
        config.LIVE_ROOM_SET_MANAGER = apiUrl + "liveRoom/setmanage";// 设置管理员
        config.LIVE_ROOM_SHUT_UP = apiUrl + "liveRoom/shutup";// 禁言/取消禁言
        config.LIVE_ROOM_KICK = apiUrl + "liveRoom/kick";// 踢人
        config.LIVE_ROOM_STATE = apiUrl + "liveRoom/start";// 正在直播 || 未开启直播
        config.LIVE_GET_LIVEROOM = apiUrl + "/liveRoom/getLiveRoom";// 获取自己的直播间

        config.EMPTY_SERVER_MESSAGE = apiUrl + "tigase/emptyMyMsg";// 清空与某人的聊天记录

        /* 商务圈 */
        config.MSG_ADD_URL = apiUrl + "b/circle/msg/add";// 发布一条公共消息的接口
        config.MSG_LIST = apiUrl + "b/circle/msg/list";// 获取公共消息的接口
        config.MSG_USER_LIST = apiUrl + "b/circle/msg/user";// 获取某个用户的最新公共消息
        config.MSG_GETS = apiUrl + "b/circle/msg/gets";// 根据IDS批量获取公共消息的接口(我的商务圈使用)
        config.MSG_GET = apiUrl + "b/circle/msg/get";// 根据ID获取公共消息
        config.CIRCLE_MSG_DELETE = apiUrl + "b/circle/msg/delete";// 删除一条商务圈消息
        config.MSG_PRAISE_ADD = apiUrl + "b/circle/msg/praise/add";// 赞
        config.MSG_PRAISE_DELETE = apiUrl + "b/circle/msg/praise/delete";// 取消赞
        config.MSG_COLLECT_DELETE = apiUrl + "/b/circle/msg/deleteCollect";// 删除收藏
        config.CIRCLE_MSG_LATEST = apiUrl + "b/circle/msg/latest";// 最新人才榜
        config.CIRCLE_MSG_HOT = apiUrl + "b/circle/msg/hot";// 最热人才榜
        config.MSG_COMMENT_ADD = apiUrl + "b/circle/msg/comment/add";// 增加一条评论
        config.MSG_COMMENT_DELETE = apiUrl + "b/circle/msg/comment/delete";// 删除一条评论
        config.MSG_COMMENT_LIST = apiUrl + "b/circle/msg/comment/list";// 删除一条评论
        config.UPLOAD_MUC_FILE_ADD = apiUrl + "room/add/share";// 上传群文件
        config.UPLOAD_MUC_FILE_FIND_ALL = apiUrl + "room/share/find";// 查询所有群文件
        config.UPLOAD_MUC_FILE_FIND = apiUrl + "room/share/find";// 查询单个群文件
        config.UPLOAD_MUC_FILE_DEL = apiUrl + "room/share/delete";// 删除单个群文件

        /* 登出 */
        config.USER_LOGOUT = apiUrl + "user/logout";
        config.USER_OUTTIME = apiUrl + "user/outtime";

        /* 红包相关的URL*/
        config.REDPACKET_SEND = apiUrl + "redPacket/sendRedPacket"; // 发送红包
        config.REDPACKET_OPEN = apiUrl + "redPacket/openRedPacket"; // 打开红包
        config.RECIVE_REDPACKET_LIST_GET = apiUrl + "redPacket/getRedReceiveList"; // 获得接收的红包
        config.SEND_REDPACKET_LIST_GET = apiUrl + "redPacket/getSendRedPacketList";// 获得发送的红包
        config.RENDPACKET_GET = apiUrl + "redPacket/getRedPacket"; // 获得红包详情
        config.CONSUMERECORD_GET = apiUrl + "user/consumeRecord/list"; // 获取消费记录列表
        config.RECHARGE_ADD = apiUrl + "/user/Recharge";// 余额充值
        config.RECHARGE_GET = apiUrl + "/user/getUserMoeny";// 余额查询

        config.VX_RECHARGE = apiUrl + "user/recharge/getSign";// 微信充值
        config.VX_UPLOAD_CODE = apiUrl + "user/bind/wxcode";  // 上传用户Code
        config.VX_TRANSFER_PAY = apiUrl + "transfer/wx/pay";  // 微信取现

        /* 组织架构相关 */
        config.AUTOMATIC_SEARCH_COMPANY = apiUrl + "org/company/getByUserId";// 自动查找公司
        config.CREATE_COMPANY = apiUrl + "org/company/create";// 创建公司
        config.SET_COMPANY_MANAGER = apiUrl + "org/company/setManager";// 指定管理者
        config.MODIFY_COMPANY_NAME = apiUrl + "org/company/modify";// 修改公司名称
        config.CHANGE_COMPANY_NOTIFICATION = apiUrl + "org/company/changeNotice";// 更改公司公告
        config.SEARCH_COMPANY = apiUrl + "org/company/search";// 查找公司
        config.DELETE_COMPANY = apiUrl + "org/company/delete";// 删除公司
        config.CREATE_DEPARTMENT = apiUrl + "org/department/create";// 创建部门
        config.MODIFY_DEPARTMENT_NAME = apiUrl + "org/department/modify";// 修改部门名称
        config.DELETE_DEPARTMENT = apiUrl + "org/department/delete";// 删除部门
        config.ADD_EMPLOYEE = apiUrl + "org/employee/add";// 添加员工
        config.DELETE_EMPLOYEE = apiUrl + "org/employee/delete"; // 删除员工
        config.MODIFY_EMPLOYEE_DEPARTMENT = apiUrl + "org/employee/modifyDpart";// 更改员工部门
        config.COMPANY_LIST = apiUrl + "org/company/list";// 公司列表
        config.DEPARTMENT_LIST = apiUrl + "org/department/list"; // 部门列表
        config.EMPLOYEE_LIST = apiUrl + "org/employee/list";// 员工列表
        config.GET_COMPANY_DETAIL = apiUrl + "org/company/get";// 获取公司详情
        config.GET_DEPARTMENT_DETAIL = apiUrl + "org/employee/get";// 获取员工详情
        config.GET_EMPLOYEE_DETAIL = apiUrl + "org/dpartment/get"; // 获取部门详情
        config.GET_EMPLOYEE_NUMBER_OF_COMPANY = apiUrl + "org/company/empNum"; // 获取公司员工人数
        config.EXIT_COMPANY = apiUrl + "org/company/quit";// 获取部门员工人数
        config.CHANGE_EMPLOYEE_IDENTITY = apiUrl + "org/employee/modifyPosition";// 修改职位

        /* 标签相关 */
        config.FRIENDGROUP_LIST = apiUrl + "friendGroup/list";
        config.FRIENDGROUP_ADD = apiUrl + "friendGroup/add";
        config.FRIENDGROUP_DELETE = apiUrl + "friendGroup/delete";
        config.FRIENDGROUP_UPDATE = apiUrl + "friendGroup/update";
        config.FRIENDGROUP_UPDATEGROUPUSERLIST = apiUrl + "friendGroup/updateGroupUserList";
        config.FRIENDGROUP_UPDATEFRIEND = apiUrl + "friendGroup/updateFriend";

        /* 漫游 */
        config.GET_LAST_CHAT_LIST = apiUrl + "tigase/getLastChatList";
        config.GET_CHAT_MSG = apiUrl + "tigase/shiku_msgs";
        config.GET_CHAT_MSG_MUC = apiUrl + "tigase/shiku_muc_msgs";

        /* 收藏相关 */
        config.Collection_ADD = apiUrl + "user/emoji/add";             // 添加收藏
        config.Collection_REMOVE = apiUrl + "user/emoji/delete";       // 删除收藏
        config.Collection_LIST = apiUrl + "user/emoji/list";           // 表情列表
        config.Collection_LIST_OTHER = apiUrl + "user/collection/list";// 收藏列表

        config.USER_REPORT = apiUrl + "user/report";// 举报用户 || 群组
        config.UPLOAD_COPY_FILE = apiUrl + "upload/copyFile";// 服务端将文件拷贝一份，更换另外一个url地址返回
        config.ADDRESSBOOK_UPLOAD = apiUrl + "addressBook/upload";// 上传本地联系人
        config.ADDRESSBOOK_GETALL = apiUrl + "addressBook/getAll";// 查询通讯录好友
        config.ADDENTION_BATCH_ADD = apiUrl + "friends/attention/batchAdd";// 联系人内加好友 不需要验证
        config.CHECK_PAY_PASSWORD = apiUrl + "user/checkPayPassword";   // 检查支付密码，
        config.UPDATE_PAY_PASSWORD = apiUrl + "user/update/payPassword";// 修改支付密码，
        // 集群 获取meetUrl
        config.OPEN_MEET = apiUrl + "user/openMeet";
        // 小米推送
        config.configMi = apiUrl + "user/xmpush/setRegId";
        // 华为推送
        config.configHw = apiUrl + "user/hwpush/setToken";
        // 极光推送
        config.configJg = apiUrl + "user/jPush/setRegId";
        config.SDK_OPEN_AUTH_INTERFACE = apiUrl + "open/authInterface";
    }

    private static void initOthersUrls(AppConfig config) {
        String uploadUrl = config.uploadUrl;
        String dowmLoadUrl = config.downloadAvatarUrl;
        config.UPLOAD_URL = uploadUrl + "upload/UploadServlet";// 上传图片接口
        config.AVATAR_UPLOAD_URL = uploadUrl + "upload/UploadAvatarServlet";// 上传头像接口
        // downloadAvatarUrl
        config.AVATAR_ORIGINAL_PREFIX = dowmLoadUrl + "avatar/o";// 头像原图前缀地址
        config.AVATAR_THUMB_PREFIX = dowmLoadUrl + "avatar/t";// 头像缩略图前缀地址
    }
}