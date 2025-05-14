package org.jingouzhui.strategy.controller;


import lombok.RequiredArgsConstructor;
import org.jingouzhui.strategy.service.PayType;
import org.jingouzhui.strategy.service.PaymentService;
import org.jingouzhui.strategy.service.PaymentStrategyContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payment")
public class PaymentController {

 private final PaymentStrategyContext paymentStrategyContext;

 public PaymentController(PaymentStrategyContext paymentStrategyContext) {
     this.paymentStrategyContext = paymentStrategyContext;
 }


    //  抽象逻辑
    //  要实现具体的逻辑策略
    //  选择一个不同的策略
    //  调用策略具体的逻辑

    @PostMapping("/pay")
    public String payOrder(@RequestParam String orderId, @RequestParam String paymentType) {
        PayType payType = PayType.typeOf(paymentType);
        PaymentService paymentService = paymentStrategyContext.getStrategy(payType);
       return paymentService.pay(orderId);
    }


}
