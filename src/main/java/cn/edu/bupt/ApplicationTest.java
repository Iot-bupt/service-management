package cn.edu.bupt;

import cn.edu.bupt.common.model.Ability;
import cn.edu.bupt.mapper.DeviceTypeMapper;
import cn.edu.bupt.mapper.ManufacturerMapper;
import cn.edu.bupt.mapper.ModelMapper;
import cn.edu.bupt.mapper.AbilityMapper;
import cn.edu.bupt.util.ElasticUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

class T{
    public void test(){
        System.out.print("haha");
    }
    public void test1(T t){
        System.out.print("haha");
    }

    public static void test2(T t){
        System.out.print("haha");
    }
}

@RunWith(SpringRunner.class)
@EnableTransactionManagement
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private ManufacturerMapper mapper;

    @Autowired
    private DeviceTypeMapper mapper1;

    @Autowired
    private ModelMapper mapper2;

    @Autowired
    private AbilityMapper mapper3;

    @Test
    public void test(){
//        List ls = new ArrayList<>();
//        ls.add(ls);
//        ls.toString();
            List<T> ls = new ArrayList<>();
            ls.add(new T());
            ls.forEach(T::test2);
//        Comparator.comparing();
//        Arrays.asList()
//           List<Ability> as =  mapper3.findAllAbilityByModelId(1);
//
//        as.forEach(a->{
//            JsonObject data = new JsonObject();
//            data.addProperty("abilityId",a.getAbilityId());
//            JsonObject servcieDes = new JsonParser().parse(a.getAbilityDes()).getAsJsonObject();
//            data.add("serviceName",servcieDes.get("serviceName"));
//            data.add("serviceDescription",servcieDes.get("serviceDescription"));
//            ElasticUtil.insertDoc(a.getAbilityId(),data.toString());
//            ElasticUtil.deleteDoc(a.getAbilityId());
        }
//           as.forEach(a->{
//               System.out.println(a);
//           });
//        System.err.println(123);
//        Manufacturer m =  new Manufacturer();
//        System.out.println("before "+m.getManufacturerId());;
//        m.setManufacturerName("test3");
//        mapper.insert(m);
//        System.out.println("after "+m.getManufacturerId());;
//        DeviceType d = new DeviceType();
//        d.setManufacturerId(2);
//        d.setDeviceTypeName("test1");
//        mapper1.insert(d);
  //      mapper1.findAll(1);
        //System.out.println();
//        Model m = new Model();
//        m.setManufacturerId(1);
//        m.setDeviceTypeId(1);
//        m.setModelName("testModel");

//        Service s = new Service();
//                s.setModelId(1);
//        s.setServiceDes("anather des 1");
//        //mapper3.insert(s);
//        System.out.println(mapper3.findAllServiceByModelId(1));;
//        mapper3.deleteByServiceId(1);
//        mapper2.insert(m);
//        System.out.println(mapper2.findAllByKeyWord(1,1,"edl"));
     //  System.out.println(mapper1.findAllByKeyWord(1,"test1"));
    }

