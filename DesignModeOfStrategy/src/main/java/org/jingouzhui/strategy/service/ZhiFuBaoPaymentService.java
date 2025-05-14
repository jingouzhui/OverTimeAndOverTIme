package org.jingouzhui.strategy.service;

import org.springframework.stereotype.Component;

/**
 * @description: 支付宝
 * @author: jingouzhui
 * @date: 2025/5/14 下午5:48
 */
@Component
@SupportPayType(value = PayType.ZHI_FU_BAO)
public class ZhiFuBaoPaymentService implements PaymentService{
    @Override
    public String pay(String orderId) {
        return "ZhiFuBaoPaymentService" + orderId;
    }
}
