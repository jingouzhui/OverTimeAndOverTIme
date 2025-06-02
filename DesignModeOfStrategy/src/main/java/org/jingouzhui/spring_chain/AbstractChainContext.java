package org.jingouzhui.spring_chain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 负责组装责任链，执行责任链
 * @author: jingouzhui
 * @date: 2025/5/31 21:29
 */
@Component
public final class AbstractChainContext<T> implements CommandLineRunner {

    private Map<String, List<AbstractChainHandler>> abstractChainHandlerContainer = new HashMap<>();

    /**
     *
     * @param mark  代表哪一种类型的责任链
     * @param requestParam 请求参数
     */
    public void handler(String mark, T requestParam){
        List<AbstractChainHandler> handlers = abstractChainHandlerContainer.get(mark);
        if (CollectionUtils.isEmpty(handlers)){
            throw new RuntimeException(mark+"对应的责任链为空");
        }
        handlers.forEach(handler -> {
            try {
                handler.handle(requestParam);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, AbstractChainHandler> chainFilterMap = ApplicationContextHolder.getBeansOfType(AbstractChainHandler.class);
        chainFilterMap.forEach((beanName,bean) -> {
            List<AbstractChainHandler> handlers = abstractChainHandlerContainer.get(bean.mark());
            if (CollectionUtils.isEmpty(handlers)) {
                handlers = new ArrayList<>();
            }
            handlers.add(bean);

            List<AbstractChainHandler> sortedHandlers = handlers.stream()
                    .sorted(Comparator.comparing(Ordered::getOrder))
                    .collect(Collectors.toList());
            abstractChainHandlerContainer.put(bean.mark(),sortedHandlers);
        });

    }


}
