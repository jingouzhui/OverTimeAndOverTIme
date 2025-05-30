package org.jingouzhui.chain_demo;

import java.math.BigDecimal;

/**
 * @description: 用于处理金额在1000以下的审批
 * @author: jingouzhui
 * @date: 2025/5/30 16:40
 */
public class ManagerApproveHandler extends ApproveHandler{


    public ManagerApproveHandler(BigDecimal bigDecimal) {
        super(bigDecimal);
    }

    @Override
    public void handler(Amount amount,ApproveContext context) throws ClassNotFoundException {
        if (canHandler(amount)) {
            System.out.println("ManagerApproveHandler处理了");
        }else {
            System.out.println("ManagerApproveHandler跳过了");
            context.doNext(amount);
        }

    }
}
