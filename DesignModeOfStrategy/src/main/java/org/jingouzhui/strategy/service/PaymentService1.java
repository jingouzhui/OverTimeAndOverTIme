package org.jingouzhui.strategy.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService1 {

    public String processPayment(String orderId, String paymentType) {

        // 未使用策略模式的代码：通过 if-else 处理不同支付类型
        if ("ALIPAY".equalsIgnoreCase(paymentType)) {
            return processAlipay(orderId);
        } else if ("WECHAT".equalsIgnoreCase(paymentType)) {
            return processWechat(orderId);
        } else if ("CREDIT_CARD".equalsIgnoreCase(paymentType)) {
            return processCreditCard(orderId);
        } else {
            throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }
    }

    private String processAlipay(String orderId) {
        return "Processing Alipay payment for order: " + orderId;
    }

    private String processWechat(String orderId) {
        return "Processing WeChat payment for order: " + orderId;
    }

    private String processCreditCard(String orderId) {
        return "Processing Credit Card payment for order: " + orderId;
    }
}
