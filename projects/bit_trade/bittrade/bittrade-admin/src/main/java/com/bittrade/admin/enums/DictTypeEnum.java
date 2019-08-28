package com.bittrade.admin.enums;

/**
 * @author leigq
 * 字典类型枚举
 */
public enum DictTypeEnum {

    SYS_USER_SEX("sys_user_sex", "用户性别"),

    SYS_SHOW_HIDE("sys_show_hide", "菜单状态"),

    SYS_NORMAL_DISABLE("sys_normal_disable", "系统开关"),

    SYS_JOB_STATUS("sys_job_status", "任务状态"),

    SYS_YES_NO("sys_yes_no", "系统是否"),

    SYS_NOTICE_TYPE("sys_notice_type", "通知类型"),

    SYS_NOTICE_STATUS("sys_notice_status", "通知状态"),

    SYS_OPER_TYPE("sys_oper_type", "操作类型"),

    SYS_COMMON_STATUS("sys_common_status", "系统状态"),

    SYS_CONTRACT_DIRECTION("sys_contract_direction", "方向（涨/跌）"),

    AUDIT_AUTHENTICATION_STATUS("audit_authentication_status", "审核状态"),

    AUDIT_RECHARGE_TYPE("audit_recharge_type", "充值类型"),

    AUDIT_RECHARGE_STATUS("audit_recharge_status", "充值状态"),

    AUDIT_WITHDRAW_TYPE("audit_withdraw_type", "提现类型"),

    AUDIT_WITHDRAW_STATUS("audit_withdraw_status", "提现状态"),

    USER_AUTHENTICATION("user_authentication", "身份认证"),

    USER_AGNT("user_agnt", "代理"),

    CONTRACT_PROFITANDLOSS("contract_profitAndLoss", "盈亏"),

    CONTRACT_STATUS("contract_status", "平仓类型"),

    ARTICLE_STATUS("article_status", "文章状态"),

    COMMON_STATUS("common_status", "通用状态"),

    USER_AGNT2("user_agnt2", "代理级别"),

    CONTRACT_TOKEN_ORDER("contract_token_order", "订单类型"),

    RECHARGE_CURRENCY("recharge_currency", "充值币种"),

    APP_TYPE("app_type", "app类型"),

    APP_STATUS("app_status", "app是否强制升级"),

    USER_INTERNAL_ACCOUNT("user_internal_account", "内部账号"),

    CONTRACT_CLIENT_TYPE("contract_client_type", "订单平台区分"),

    ORDERUSER_ORDERSTAUTS("orderUser_orderStauts", "订单状态"),

    ORDERUSER_PRODUCTTYPE("orderUser_productType", "交易对类型"),

    ORDERUSER_TYPE("orderUser_type", "购买出售订单类型"),

    ORDERUSER_ORDERSTAUTSTYPE("orderUser_orderStautsType", "取消订单状态"),

    RDERENTRUST_ISATTESTATION("rderEntrust_isAttestation", "委托单是否认证"),

    PERSONALCARD_AUTH_STAUTS("personalCard_auth_Stauts", "认证状态"),

    PERSONALPARTNER_TYPE("personalPartner_type", "合伙人类型"),

    PREMIUM_STAUTS("premium_Stauts", "是否溢价"),

    BETA_ACTIVATE("beta_activate", "是否激活"),

    BETA_ORDER_STAUTS("beta_order_stauts", "订单状态"),

    BETA_RESERVSTAUTS("beta_reservStauts", "预约状态"),

    BETA_PLATFORM_AND_USER("beta_platform_and_user", "订单创建类型"),

    BETA_PROCESSEDA_ORDER_STAUTS("beta_processeda_order_stauts", "处理订单状态"),

    BETA_SELL_STATUS("beta_sell_Status", "出售状态"),

    PERSONALCARD_CURRENTIDENTITY("personalCard_currentIdentity", "当前身份类型"),

    BETA_PRODUCTTYPE("beta_productType", "钱包类型"),

    RECORD_SMS_LOG_TYPE("record_sms_log_type", "短信类型"),

    COUNTRY_CODE_USE("country_code_use", "区域码是否启用"),

    COIN_TYPE("coin_type", "是否可以充币/提币"),

    UPDATE("update", "test_key"),

    BETA_COVERAGE_TYPE("beta_coverage_Type", "覆盖的类型（修改的类型）"),

    IMPORT_STAUTS("import_stauts", "导入状态"),

    BETA_BETAFEMINA_STATUS("beta_betaFemina_status", "母狗状态"),

    HOUSE_PRODUCT_STATUS("house_product_status", "商品类型"),

    BETA_HOUSEPRODUCT_STATUS("beta_HouseProduct_status", "商品出售状态"),

    BETA_HOUSEPRODUCT_BESUITABLE("beta_HouseProduct_beSuitable", "商品适用"),

    BETA_PUP_STATE("beta_pup_state", "小狗状态"),

    BETA_PUP_GENDER("beta_pup_gender", "小狗性别"),

    HOUSE_PRODUCT_SHOWPOSITION("house_product_showPosition", "商品展示位置"),

    HOUSE_PRODUCT_EXPENDTYPE("house_product_expendType", "消耗类别"),

    DOG_ROBBING("dog_robbing", "抢狗概率"),

    ;

    DictTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String key;
    private String value;

    @Override
    public String toString() {
        return key;
    }
}
