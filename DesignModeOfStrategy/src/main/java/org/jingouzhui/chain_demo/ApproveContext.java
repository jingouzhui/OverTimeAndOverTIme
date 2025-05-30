package org.jingouzhui.chain_demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/30 17:20
 */
@Data
@Getter
@Setter
public class ApproveContext {

   // private Amount amount;

    private Integer index;

    public ApproveContext( Integer index) {
        this.index = index;
    }

    public ApproveContext() {

    }

   /* public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }*/

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void doNext(Amount amount) {
        index++;
       // this.amount = amount;
    }
}
