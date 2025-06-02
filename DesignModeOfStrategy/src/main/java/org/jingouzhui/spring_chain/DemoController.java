package org.jingouzhui.spring_chain;


import org.jingouzhui.spring_chain.validate.ValidateReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: demo
 * @author: jingouzhui
 * @date: 2025/5/31 21:28
 */
@RestController
@RequestMapping("/test/chain")
public class DemoController {



    private final AbstractChainContext<ValidateReq> abstractChainContext;

    public DemoController(AbstractChainContext<ValidateReq> abstractChainContext) {
        this.abstractChainContext = abstractChainContext;
    }


    @PostMapping("/validate")
    public void testChain(){
        ValidateReq req = new ValidateReq();
        req.setLength(5);
        req.setMax(11);
        req.setMin(7);
        abstractChainContext.handler("validate",req);
    }

}
