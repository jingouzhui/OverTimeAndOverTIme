package org.jingouzhui.strategy.service;

import java.util.Arrays;

/**
 * @description: 支付类型
 * @author: jingouzhui
 * @date: 2025/5/14 下午5:52
 */
public enum PayType {

    WE_CHAT("wechat"),
    ZHI_FU_BAO("zhifubao"),
    CREDIT("credit");

    private String code;

    PayType(String code) {
        this.code = code;
    }


    public static PayType typeOf(String code) {
        return Arrays.stream(values())
                .filter(t -> t.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("无效支付类型" + code));
    }
}
