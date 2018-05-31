package cn.edu.bupt.controller;

import cn.edu.bupt.DeviceFilter.DeviceFilterMetadata;
import cn.edu.bupt.DeviceFilter.FilterRes;
import cn.edu.bupt.DeviceFilter.Filter;
import cn.edu.bupt.common.model.Ability;
import cn.edu.bupt.common.model.AbilityGroup;
import cn.edu.bupt.common.model.Device;
import cn.edu.bupt.service.AbilityGroupService;
import cn.edu.bupt.service.AbilityService;
import cn.edu.bupt.util.BdNLPUtil;
import cn.edu.bupt.util.ElasticUtil;
import cn.edu.bupt.util.HttpUtil;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 */

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class DiscoveryController {

    @Autowired
    AbilityService abilityService;
    @Autowired
    AbilityGroupService abilityGroupService;
    @Autowired
    Filter filter;

    @Value("service.threhold")
    int serviceThrehold;

    @Value("deviceAccess.host")
    String deviceAccessHost;
    @Value("deviceAccess.port")
    int deviceAccessPost;

    Gson gson = new Gson();

    @RequestMapping(value = "/control/device",method = RequestMethod.POST)
    public String  controlDevice(@RequestBody String sentence) throws Exception{
        JsonObject obj = new JsonParser().parse(sentence).getAsJsonObject();
        String sentence1 = obj.get("sentence").getAsString();
        boolean keywordsMaching = obj.get("keywordsMaching").getAsBoolean();
        List<String> keywors = getKeyWords(sentence1);
        String location = getLocaltion(sentence1);
        if(keywordsMaching){
            StringBuffer sb = new StringBuffer();
            for(String e:keywors)
                sb.append(e.toString()+" ");
            sb.deleteCharAt(sb.length()-1);
            String res = ElasticUtil.queryAbilityByNameAndDes(sb.toString());
            List<DeviceFilterMetadata> ls = getDevicesByThreeTouple(res,location);
            //TODO 解析res拿到所有返回的服务，根据服务查到对应的设备，为每一个设备生成一个DeviceFilterMetadata，然后进入filter链处理
            final List<FilterRes> fIlterRess = new ArrayList<>();
            ls.forEach(dm->{
                fIlterRess.add(new FilterRes(dm.getDevice(),dm.getAbility(),filter.filter(dm)));
            });
            Collections.sort(fIlterRess);
            //TODO 根据fIlterRess中的设备及其对应的服务来调用相应的服务
            return null;
        }else{
//            abilityService.
            return null;
        }
    }

    private List<String> getKeyWords(String sentence1) throws Exception{
        return BdNLPUtil.getKeyWords(sentence1);
    }

    private String getLocaltion(String sentence1) throws Exception{
        List<String> ls = BdNLPUtil.getLocation(sentence1);
        StringBuffer sb = new StringBuffer();
        ls.forEach(str->{
            sb.append(str);
        });
        return sb.toString();
    }

    List<DeviceFilterMetadata> getDevicesByThreeTouple(String threeTouples,String location) throws Exception{
        JsonObject obj = new JsonParser().parse(threeTouples).getAsJsonObject();
        JsonArray array = obj.get("hits").getAsJsonObject().get("hits").getAsJsonArray();
        int size = array.size()<serviceThrehold?array.size():serviceThrehold;
        List<DeviceFilterMetadata> ans = new ArrayList<>();
        for (int i=0;i<size;i++){
            int ablityId = array.get(i).getAsJsonObject().get("_id").getAsInt();
            double keywordsSimilarity = array.get(i).getAsJsonObject().get("_score").getAsDouble();
            Ability ab = abilityService.getAbilityByAbilityId(ablityId);

            AbilityGroup ag =  abilityGroupService.getAbilityGroupByModelId(ab.getModelId());
            //TODO 根据三元组名称
            String url = "http://"+deviceAccessHost+":"+deviceAccessPost+"/api/v1/"+ag.getManufacturer().getManufacturerName()+
                    "/"+ag.getDeviceType().getDeviceTypeName()+"/"+ag.getModel().getModelName()+"/devices?limit=100";
            String res = HttpUtil.sendGet(url);
            JsonArray arrays = new JsonParser().parse(res).getAsJsonArray();
            for(JsonElement ele:arrays){
                Device d = gson.fromJson(ele,Device.class);
                ans.add(new DeviceFilterMetadata(d,location,System.currentTimeMillis(),ab,keywordsSimilarity));
            }
        }
        return ans;
    }
}
