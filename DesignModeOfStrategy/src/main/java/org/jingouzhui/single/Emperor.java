package org.jingouzhui.single;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description: 只能产生n个实例的对象
 * @author: jingouzhui
 * @date: 2025/6/2 21:52
 */

/**
 * 这种需要产生固定数量对象的模式就叫做有上限的多例模式，它是单例模式的一种扩展，采用有上限的多例模式，我们可以在设计时决定在内存中有多少个实例，
 * 方便系统进行扩展，修正单例可能存在的性能问题，提供系统的响应速度。例如读取文件，我们可以在系统启动时完成初始化工作，
 * 在内存中启动固定数量的reader实例，然后在需要读取文件时就可以快速响应。
 */
public class Emperor {
    private static  Integer maxNumOfEmperor = 2;
    private static List<String> nameList = new ArrayList<>();
    private static List<Emperor> emperorList = new ArrayList<>();

    /**
     * 当前皇帝序列号
     */
    private static Integer countNumOfEmperor = 0;

    static {
        for (int i = 0; i < maxNumOfEmperor; i++) {
            emperorList.add(new Emperor("第"+(i+1)+"个皇帝"));
        }
    }

    private Emperor(){}

    private Emperor(String name) {
        nameList.add(name);
    }

    public static Emperor getEmperor(){
        Random random = new Random();
        countNumOfEmperor =  random.nextInt(maxNumOfEmperor);
        return  emperorList.get(countNumOfEmperor);

    }

    public void say() {
        System.out.println(nameList.get(countNumOfEmperor));
    }

    public static void main(String[] args) {
        //定义大臣数量
        int ministerCount = 5;
        for (int i = 0; i < ministerCount; i++) {
            Emperor emperor = Emperor.getEmperor();
            System.out.println("第"+(i+1)+"个大臣参观的是：");
            emperor.say();
        }
    }
}
