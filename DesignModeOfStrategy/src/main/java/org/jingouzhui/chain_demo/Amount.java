package org.jingouzhui.chain_demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/30 16:54
 */
@Data
public class Amount {


    private BigDecimal amount;


    public Amount(BigDecimal amount) {
        this.amount = amount;
    }
    public Amount() {

    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
