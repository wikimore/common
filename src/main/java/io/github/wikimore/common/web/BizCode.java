package io.github.wikimore.common.web;

/**
 * @author Ted Wang
 */
public interface BizCode {

  /**
   * 成功
   */
  public static final int SUCCESS = 0;

  // ==================== //
  // common error code [1-1000)
  // ==================== //

  /**
   * 内部错误
   */
  public static final int SYSTEM_ERROR = 2;

  /**
   * 未登陆错误
   */
  public static final int NEED_LOGIN_ERROR = 50;

  /**
   * 敏感词错误
   */
  public static final int WORD_RISK_ERROR = 214;

  /**
   * 操作过于频繁
   */
  public static final int FREQUENCY_ERROR = 666;

  /**
   * 参数错误
   */
  public static final int PARAMS_ERROR = 3;


  /**
   * 客户端升级提示
   */
  public static final int CLIENT_UPGRADE = 300;

  // ==================== //
  // paint error code [30000-40000)
  // ==================== //

  public static final int PAINT_HAVE_RUNNING_TASK = 30001;

  // ==================== //
  // login error code [40000-50000)
  // ==================== //

  // 登录参数错误
  public static final int LOGIN_PARAM_ERROR = 40001;

  //手机号不正确
  public static final int PHONENO_ERROR = 40002;

  //验证码错误
  public static final int SMSCODE_ERROR = 40003;
  public static final int WECHAT_ACCESSTOKEN_ERROR = 40004;
  public static final int WECHAT_USERINFO_ERROR = 40005;
  public static final int PRODUCT_NOT_EXIST = 40006;
  //用户状态错误
  public static final int USER_STATUS_ERROR = 40007;
  //微信预下单接口调用出错
  public static final int ORDER_WXPAY_PREORDER_ERROR = 40008;
  public static final int ORDER_NOT_EXIST = 40008;
  // 接口参数错误
  public static final int PARAM_ERROR = 40009;
  //订单状态不正确
  public static final int ORDER_STATUS_ERROR = 40010;
  //用户不存在
  public static final int USER_NOT_EXIST = 40011;
  public static final int ORDER_CLOSE_ERROR = 40012;
  public static final int ALIPAY_SIGN_ERROR = 40013;
  public static final int ORDER_PAYAMOUNT_NOT_EQUAL = 40014;


}
