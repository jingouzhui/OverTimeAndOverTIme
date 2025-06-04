package org.jingouzhui.observer.practice;

import java.math.BigDecimal;

/**
 * @description: 具体事件 股票
 * @author: jingouzhui
 * @date: 2025/6/4 22:22
 */
public class Stock{

    private Bus bus;
    private BigDecimal price;

    public Stock(Bus bus, BigDecimal price) {
        this.bus = bus;
        this.price = price;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        BigDecimal oldPrice = this.price;
        this.price = price;
        StockPriceEvent stockPriceEvent = new StockPriceEvent(price.toString());
        if(changed(oldPrice,price)){
            bus.publish(stockPriceEvent);
        }
    }

    private boolean changed(BigDecimal oldPrice, BigDecimal newPrice) {
        BigDecimal subtract = oldPrice.subtract(newPrice).abs();
        BigDecimal thrshold = oldPrice.multiply(new BigDecimal(0.05));
        return  subtract.compareTo(thrshold) > 0;
    }

}
