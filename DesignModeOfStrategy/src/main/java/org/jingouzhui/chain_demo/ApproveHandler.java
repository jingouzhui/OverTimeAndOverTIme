package org.jingouzhui.chain_demo;

import java.math.BigDecimal;

/**
 * @description: 审批接口
 * @author: jingouzhui
 * @date: 2025/5/30 16:38
 */
public abstract class ApproveHandler {

    protected BigDecimal threshold;

    public ApproveHandler(BigDecimal threshold) {
        this.threshold = threshold;
    }

    /**
     * 用于审批报销
     */
    public  abstract void handler(Amount amount,ApproveContext context) throws ClassNotFoundException;

    public boolean canHandler(Amount amount) {
        return amount.getAmount().compareTo(threshold) <= 0;
    }

}
