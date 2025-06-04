package org.jingouzhui.observer.practice;

import java.math.BigDecimal;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/4 23:09
 */
public class Main {

    public static void main(String[] args) {
        Bus bus = new Bus();
        Stock stock = new Stock(bus,new BigDecimal(100));
        Investor a = new Investor("zxq");
        Investor b = new Investor("zxq2");
        bus.subscribe(a);
        bus.subscribe(b);

        stock.setPrice(new BigDecimal(106));

    }
}
