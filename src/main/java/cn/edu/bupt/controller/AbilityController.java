package cn.edu.bupt.controller;

import cn.edu.bupt.common.model.Ability;
import cn.edu.bupt.service.AbilityService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
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
public class AbilityController extends BaseController{
    @Autowired
    AbilityService abilityService;
    JsonParser parser = new JsonParser();
    @RequestMapping(value = "/ability",method = RequestMethod.POST)
   public Ability saveAbility(@RequestBody String data,HttpServletResponse response){
        JsonObject obj = parser.parse(data).getAsJsonObject();
        int abilityId = obj.has("abilityId")? obj.getAsJsonPrimitive("abilityId").getAsInt():-1;
        int modelId = obj.has("modelId")? obj.getAsJsonPrimitive("modelId").getAsInt():-1;
        String abilityDes = obj.has("abilityDes")?obj.get("abilityDes").getAsString():null;
        if(modelId==-1||abilityDes==null) return null;
        Ability ability = new Ability();
        ability.setModelId(modelId);
        ability.setAbilityDes(abilityDes);

        if(abilityId==-1){
            abilityService.addAbilityToAbilityGroup(ability);
        }else{
            ability.setAbilityId(abilityId);
            abilityService.updateAbility(ability);
        }
        return ability;
    }

    @RequestMapping(value = "/ability/{abilityId}",method = RequestMethod.DELETE)
    public void deleteAbility(@PathVariable int abilityId,HttpServletResponse response){
        abilityService.deleteAbilityFromAbilityGroup(abilityId);
    }

    @RequestMapping(value = "/ability/{modelId}",method = RequestMethod.GET)
    public List<Ability> findAbilitiesByModelId(@PathVariable int modelId,HttpServletResponse response){
       return abilityService.findAbilitiesByModelId(modelId);
    }

}
