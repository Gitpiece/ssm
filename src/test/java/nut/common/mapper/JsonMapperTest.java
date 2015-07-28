package nut.common.mapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015/7/22.
 */
public class JsonMapperTest {

    @Test
    public void test(){
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", 1);
        map.put("pId", -1);
        map.put("name", "根节点");
        list.add(map);
        map = Maps.newHashMap();
        map.put("id", 2);
        map.put("pId", 1);
        map.put("name", "你好");
        map.put("open", true);
        list.add(map);
        String json = JsonMapper.getInstance().toJson(list);
        System.out.println(json);
    }
}
