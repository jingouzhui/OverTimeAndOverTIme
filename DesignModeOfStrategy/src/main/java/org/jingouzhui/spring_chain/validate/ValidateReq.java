package org.jingouzhui.spring_chain.validate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.jingouzhui.chain.annotation.Length;
import org.jingouzhui.chain.annotation.Max;
import org.jingouzhui.chain.annotation.Min;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/31 22:02
 */
public class ValidateReq {

    @Length(length = 5)
    private Integer length;

    @Max(10)
    private Integer max;

    @Min(6)
    private Integer min;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }
}
