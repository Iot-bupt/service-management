package cn.edu.bupt.controller;

import cn.edu.bupt.util.ElasticUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/5/24.
 */

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class DiscoveryController {
    @RequestMapping(value = "/control/device",method = RequestMethod.POST)
    public String  controlDevice(String keywords){
        JsonObject obj = new JsonParser().parse(keywords).getAsJsonObject();
        JsonElement ele = obj.get("keywords");
        if (ele.isJsonArray()){
            StringBuffer sb = new StringBuffer();
            for(JsonElement e:ele.getAsJsonArray()){
                sb.append(e.toString()+" ");
            }
            sb.deleteCharAt(sb.length()-1);
            String res = ElasticUtil.queryAbilityByNameAndDes(sb.toString());
            //TODO 解析res拿到所有返回的服务，根据服务查到对应的设备，为每一个设备生成一个DeviceFilterMetadata，然后进入filter链处理
            return null;
        }else{
            return null;
        }
    }
}
