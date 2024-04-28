package com.nascent.meta;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

public class MetaManager {
    private static volatile Meta meta;

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
        return metaBean;
    }
}
