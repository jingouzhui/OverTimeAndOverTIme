package org.jingouzhui.strategy.service;

/**
 * @description: 支付服务
 * @author: jingouzhui
 * @date: 2025/5/14 下午5:46
 */
public interface PaymentService {

    public String pay(String oderId);

    static PayType findPayTypeByPayService(PaymentService paymentService) {
        SupportPayType annotation = paymentService.getClass().getAnnotation(SupportPayType.class);
        return annotation.value();
    }

}
