package org.jingouzhui.chain;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 校验链上下文   可以起到传递信息的作用   其只对责任链节点可见 对外部不可见
 * @author: jingouzhui
 * @date: 2025/5/29 19:24
 */
public class ValidatorContext {

    private List<String> errorMsgs = new ArrayList<>();
    private Integer index = 0;

    private Object value;

    public ValidatorContext(Object value) {
        this.value = value;
    }

   public Integer currentIndex() {
       return index;
   }

    public void doNext(Object value){
        index++;
        //可以在链中处理时修改参数的值在传递给下一个链条
        this.value = value;
    }
    public Object getValue() {
       return value;
    }

    public void appendErrorMsg(String msg) {
        errorMsgs.add(msg);
    }

    public void throwErrorMsgIFNecessary() {
        if (CollectionUtils.isEmpty(errorMsgs)) {
            return;
        }
        throw new ValidateException(errorMsgs.toString());
    }
}
