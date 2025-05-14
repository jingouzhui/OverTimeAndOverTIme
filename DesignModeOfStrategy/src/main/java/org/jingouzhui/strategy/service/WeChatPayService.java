package org.jingouzhui.strategy.service;

import org.springframework.stereotype.Component;

/**
 * @description:微信支付
 * @author: jingouzhui
 * @date: 2025/5/14 下午5:47
 */
@Component
@SupportPayType(value = PayType.WE_CHAT)
public class WeChatPayService implements  PaymentService{
    @Override
    public String pay(String orderId) {
        return "WeChat Pay Service " + orderId ;
    }
}
