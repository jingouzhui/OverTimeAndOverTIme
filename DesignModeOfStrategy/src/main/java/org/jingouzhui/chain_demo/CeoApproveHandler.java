package org.jingouzhui.chain_demo;

import java.math.BigDecimal;

/**
 * @description: 用于处理5000以上的金额审批
 * @author: jingouzhui
 * @date: 2025/5/30 16:42
 */
public class CeoApproveHandler extends ApproveHandler {

    public CeoApproveHandler(BigDecimal threshold) {
        super(threshold);
    }

    @Override
    public void handler(Amount amount, ApproveContext approveContext) throws ClassNotFoundException {
        if (canHandler(amount)) {
            System.out.println("CeoApproveHandler处理了");

        } else {
            System.out.println("CeoApproveHandler跳过了");
            approveContext.doNext(amount);
        }

    }
}
