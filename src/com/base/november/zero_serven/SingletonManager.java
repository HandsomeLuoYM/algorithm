package com.base.november.zero_serven;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming
 * @date 2020/11/8 - 22:13
 * @describe 容器模式
 */
public class SingletonManager {
    private static Map<String, Object> objMap = new HashMap<String, Object>();

    private SingletonManager() {
    }

    public static void registerService(String key, Object instance) {
        if (!objMap.containsKey(key)) {
            objMap.put(key, instance);
        }
    }

    public static Object getService(String key) {
        return objMap.get(key);
    }
}
