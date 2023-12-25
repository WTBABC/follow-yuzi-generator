package com.yupi.maker.generator;

import com.yupi.maker.meta.Meta;
import com.yupi.maker.meta.MetaManager;

public class MainGenerator {
    public static void main(String[] args) {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);
    }
}
