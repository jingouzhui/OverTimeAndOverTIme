package org.jingouzhui.chain_demo;

import java.math.BigDecimal;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/30 16:46
 */
public class Main {

    public Approvechain buildApprovechain() {
        Approvechain approvechain = new Approvechain();
        approvechain.addHandler(new ManagerApproveHandler(new BigDecimal("1000")));
        approvechain.addHandler(new DirectorApproveHandler(new BigDecimal("5000")));
        approvechain.addHandler(new CeoApproveHandler(new BigDecimal(Integer.MAX_VALUE)));
        return approvechain;
    }



    public static void main(String[] args) throws ClassNotFoundException {
        Main main = new Main();
        Approvechain approvechain = main.buildApprovechain();
        BigDecimal bigDecimal = new BigDecimal(10000);
        Amount amount = new Amount(bigDecimal);
        approvechain.handler(amount);

    }
}
