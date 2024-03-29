package com.csz.entity.enums;

public enum ProductStatus {
    AUDINTING("审核中"),
    IN_SELL("销售中"),
    LOCKED("暂停销售"),
    FINISHED("已结束");

    private String desc;

    ProductStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
