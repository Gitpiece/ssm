package me.pinenut.util;

import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * map 工具类
 * Created by why on 2016/6/20.
 */
public class MapUtil {


    public static <V> List<V> toValueList(Map<?,V> map) throws BeansException {
        List values = new ArrayList<V>(map.size());
        Set<? extends Map.Entry<?, V>> entrySet = map.entrySet();
        for (Map.Entry<?, V> entry:entrySet){
            values.add(entry.getValue());
        }
        return values;
    }

    public static <K> List<K> toKeyList(Map<K,?> map) throws BeansException {
        List keys = new ArrayList<K>(map.size());
        Set<? extends Map.Entry<K, ?>> entrySet = map.entrySet();
        for (Map.Entry<K, ?> entry:entrySet){
            keys.add(entry.getKey());
        }
        return keys;
    }
}
