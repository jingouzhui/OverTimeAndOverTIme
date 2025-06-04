package org.jingouzhui.observer.practice;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/6/4 22:44
 */
public class StockPriceEvent extends BaseEvent{

    private String info;

    public StockPriceEvent(String info) {
        this.info = info;

    }

    @Override
    public Object source() {
        return info;
    }


}
