package cn.edu.bupt.controller;

import cn.edu.bupt.common.model.*;
import cn.edu.bupt.service.AbilityGroupService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AbilityGroupController extends BaseController{

    @Autowired
    AbilityGroupService abilityGroupService;

    JsonParser parser = new JsonParser();

    @RequestMapping(value = "/abilityGroup", method = RequestMethod.POST)
    public AbilityGroup  saveAbilityGroup(@RequestBody String group,HttpServletResponse response){
        log.info("AbilityGroup.saveAbilityGroup receive request [{}]",group);
        JsonObject obj = parser.parse(group).getAsJsonObject();
        String manufacturerName = obj.has("manufacturerName")?obj.get("manufacturerName").getAsString():null;
        String deviceType = obj.has("deviceType")?obj.get("deviceType").getAsString():null;
        String model = obj.has("model")?obj.get("model").getAsString():null;
        if(manufacturerName==null||deviceType==null||model==null){
            return null;
        }
        AbilityGroup abilityGroup = new AbilityGroup(manufacturerName,deviceType,model);
        abilityGroupService.addAbilityGroup(abilityGroup);
        return abilityGroup;
    }

    @RequestMapping(value = "/abilityGroup/manufacturers", method = RequestMethod.GET)
    public List<Manufacturer> getManufacturers(@RequestParam(required = false) String keyword,
                                               HttpServletResponse response){
        log.info("AbilityGroup.getManufacturers receive request [{}]",keyword);
        return abilityGroupService.getManufacturersByKeyWords(keyword);
    }

    @RequestMapping(value = "/abilityGroup/deviceTypes", method = RequestMethod.GET)
    public List<DeviceType> getDeviceTypes(@RequestParam int manufacturerId, @RequestParam(required = false) String keyword,
                                           HttpServletResponse response){
        log.info("AbilityGroup.getDeviceTypes receive request [{}] [{}]",manufacturerId,keyword);
        return abilityGroupService.getDeviceTypesByKeyWords(manufacturerId,keyword);
    }

    @RequestMapping(value = "/abilityGroup/models", method = RequestMethod.GET)
    public List<Model> getModels(@RequestParam int manufacturerId, @RequestParam int deviceTypeId, @RequestParam(required = false)  String keyword,
                                 HttpServletResponse response){
        log.info("AbilityGroup.getModels receive request [{}] [{}] [{}]",manufacturerId,deviceTypeId,keyword);
        return abilityGroupService.getModelsByKeyWords(manufacturerId,deviceTypeId,keyword);
    }
}
