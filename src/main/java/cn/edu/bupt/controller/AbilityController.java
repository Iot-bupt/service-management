package cn.edu.bupt.controller;

import cn.edu.bupt.common.model.Ability;
import cn.edu.bupt.common.util.AbilityValidator;
import cn.edu.bupt.service.AbilityService;
import cn.edu.bupt.util.ElasticUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AbilityController extends BaseController{
    @Autowired
    AbilityService abilityService;
    JsonParser parser = new JsonParser();
    @RequestMapping(value = "/ability",method = RequestMethod.POST)
   public Ability saveAbility(@RequestBody String data,HttpServletResponse response){
        log.info("AbilityController.saveAbility receive a request [{}]" ,data );
        JsonObject obj = parser.parse(data).getAsJsonObject();
        int abilityId = obj.has("abilityId")? obj.getAsJsonPrimitive("abilityId").getAsInt():-1;
        int modelId = obj.has("modelId")? obj.getAsJsonPrimitive("modelId").getAsInt():-1;
        String abilityDes = obj.has("abilityDes")?obj.get("abilityDes").toString():null;
        if(modelId==-1||abilityDes==null) return null;
        if(!AbilityValidator.isValidateAbility(abilityDes))return null;
        Ability ability = new Ability();
        ability.setModelId(modelId);
        ability.setAbilityDes(abilityDes);

        if(abilityId==-1){
            abilityService.addAbilityToAbilityGroup(ability);
        }else{
            ability.setAbilityId(abilityId);
            abilityService.updateAbility(ability);
        }
        JsonObject ob = new JsonObject();
        ob.addProperty("abilityId",ability.getAbilityId());
        JsonObject servcieDes = new JsonParser().parse(ability.getAbilityDes()).getAsJsonObject();
        ob.add("serviceName",servcieDes.get("serviceName"));
        ob.add("serviceDescription",servcieDes.get("serviceDescription"));
        ElasticUtil.insertDoc(ability.getAbilityId(),ob.toString());
        return ability;
    }

    @RequestMapping(value = "/ability/{abilityId}",method = RequestMethod.DELETE)
    public void deleteAbility(@PathVariable int abilityId,HttpServletResponse response){
        log.info("AbilityController.deleteAbility receive a request [{}]" ,abilityId );
        abilityService.deleteAbilityFromAbilityGroup(abilityId);
        ElasticUtil.deleteDoc(abilityId);
    }

    @RequestMapping(value = "/ability/{modelId}",method = RequestMethod.GET)
    public List<Ability> findAbilitiesByModelId(@PathVariable int modelId,HttpServletResponse response){
        log.info("AbilityController.findAbilitiesByModelId receive a request [{}]" ,modelId );
       return abilityService.findAbilitiesByModelId(modelId);
    }

    @RequestMapping(value = "/ability/{manufacturerName}/{deviceTypeName}/{modelName:.+}",method = RequestMethod.GET)
    public List<Ability> findAbilitiesByThreeTouple(@PathVariable String manufacturerName,@PathVariable String deviceTypeName,
                                                    @PathVariable String modelName, HttpServletResponse response){
        log.info("AbilityController.findAbilitiesByModelId receive a request [{},{},{}]" ,manufacturerName,deviceTypeName ,modelName);
        return abilityService.findAbilitiesByThreeCouple(manufacturerName,deviceTypeName,modelName);
    }

}
