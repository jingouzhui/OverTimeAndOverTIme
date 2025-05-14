package org.jingouzhui.strategy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 策略映射逻辑应下沉到服务层
 */
@Service
public class PaymentStrategyContext {
    private final Map<PayType, PaymentService> strategyMap;

    @Autowired
    public PaymentStrategyContext(List<PaymentService> strategies) {
        this.strategyMap = strategies.stream()
                .filter(s -> s.getClass().isAnnotationPresent(SupportPayType.class))
                .collect(Collectors.toMap(
                       PaymentService::findPayTypeByPayService,
                        Function.identity()
                ));

        // 校验完整性
        Arrays.stream(PayType.values()).forEach(type -> {
            if (!strategyMap.containsKey(type)) {
                throw new IllegalStateException("未配置支付类型: " + type);
            }
        });
    }

    public PaymentService getStrategy(PayType payType) {
        return Optional.ofNullable(strategyMap.get(payType))
                .orElseThrow(() -> new IllegalArgumentException("不支持的支付类型"));
    }
}
