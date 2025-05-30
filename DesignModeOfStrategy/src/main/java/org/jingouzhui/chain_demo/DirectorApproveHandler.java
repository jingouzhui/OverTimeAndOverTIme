package org.jingouzhui.chain_demo;

import java.math.BigDecimal;

/**
 * @description: 用于处理1000-5000的金额审批
 * @author: jingouzhui
 * @date: 2025/5/30 16:41
 */
public class DirectorApproveHandler extends   ApproveHandler{


    public DirectorApproveHandler(BigDecimal bigDecimal){
        super(bigDecimal);
    }
    @Override
    public void handler(Amount amount,ApproveContext approveContext) throws ClassNotFoundException {
        if (canHandler(amount)) {
            System.out.println("DirectorApproveHandler处理了");
        }else {
            System.out.println("DirectorApproveHandler跳过了");
            approveContext.doNext(amount);
        }


    }
}
