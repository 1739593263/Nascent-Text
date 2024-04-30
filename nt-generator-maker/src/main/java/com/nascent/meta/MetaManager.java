package com.nascent.meta;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

public class MetaManager {
    // volatile 修饰主内存中变量，并增加可见性宫多个线程使用
    private static volatile Meta meta;

    private MetaManager() {}
    // 单例双检索 -- 防止Meta被实例化多个，让主内存中存一个Meta供多个thread用
    public static Meta getMeta() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    public static Meta initMeta() {
        String metaInfo = ResourceUtil.readUtf8Str("meta.json");
        Meta metaBean = JSONUtil.toBean(metaInfo, Meta.class);
        // Validation
        MetaValidator.doValidAndFill(metaBean);
        return metaBean;
    }
}
