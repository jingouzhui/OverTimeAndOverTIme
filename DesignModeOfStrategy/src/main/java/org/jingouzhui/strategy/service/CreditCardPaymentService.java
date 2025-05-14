package org.jingouzhui.strategy.service;

import org.springframework.stereotype.Component;

/**
 * @description: 信用卡支付
 * @author: jingouzhui
 * @date: 2025/5/14 下午5:49
 */
@Component
@SupportPayType(value = PayType.CREDIT)
public class CreditCardPaymentService implements  PaymentService{
    @Override
    public String pay(String orderId) {
        return " CreditCardPaymentService" + orderId;
    }
}
