package org.jingouzhui.chain_demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 用于构建审批链
 * @author: jingouzhui
 * @date: 2025/5/30 16:39
 */
public class Approvechain {

    List<ApproveHandler> handlers = new ArrayList<>();

    //private ApproveContext context = new ApproveContext();

    public void addHandler(ApproveHandler handler) {
        handlers.add(handler);
    }

    public void handler(Amount amount) throws ClassNotFoundException {
        ApproveContext context = new ApproveContext(0);
        while (true) {
            int index = context.getIndex();
            if (index == handlers.size()) {
                break;
            }
            ApproveHandler handler = handlers.get(index);
            handler.handler(amount,context);
            if (context.getIndex() == index) {
                break;
            }

        }
        System.out.println("处理结束");
    }

}
