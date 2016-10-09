package sample;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

/**
 * Created by whydk on 2016/7/5.
 */
public class JSONTest {

    @Test
    public void test(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url","url");

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        jsonObject = new JSONObject();
        jsonObject.put("url","url");
        jsonArray.add(jsonObject);

        JSONObject rs = new JSONObject();
        rs.put("rs",jsonArray);
        System.out.println(rs.toJSONString());

        net.sf.json.JSONObject pjson = net.sf.json.JSONObject.fromObject(rs.toJSONString());
        System.out.println(pjson.getString("rs"));
        pjson = net.sf.json.JSONObject.fromObject(pjson.getString("rs"));
        System.out.println(pjson);
    }
}
