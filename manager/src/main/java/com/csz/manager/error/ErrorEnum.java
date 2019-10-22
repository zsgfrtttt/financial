package com.csz.manager.error;

public enum ErrorEnum {

    ID_NULL("001","编号不可以为csz",false),
    REWARD_RATE_NOT_VALID("002","收益率大于0%并且小于30%",false),
    STEP_AMOUNT_ZREO("003","投资步长不可为0",false),
    UNKNOWN("999","未知错误",false);

    private String code;
    private String msg;
    private boolean canRetry;

    private ErrorEnum(String code, String msg, boolean canRetry) {
        this.code = code;
        this.msg = msg;
        this.canRetry = canRetry;
    }

    public static ErrorEnum getValueByCode(String code){
        for (ErrorEnum value : ErrorEnum.values()) {
            if (value.code.equals(code)){
                return value;
            }
        }
        return UNKNOWN;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isCanRetry() {
        return canRetry;
    }
}
