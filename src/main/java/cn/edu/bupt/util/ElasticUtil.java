package cn.edu.bupt.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2018/5/23.
 */
public class ElasticUtil {
    private static final String ESUrl = "http://127.0.0.1:9200";
    private static final String index = "ability";
    public static void main(String[] args){
        System.out.println(queryAbilityByNameAndDes("control"));
    }
    public static String insertDoc(int id,String data){
        String url = ESUrl+"/"+index+"/doc/"+id+"?pretty";
        try{
            String res = HttpUtil.sendPut(url,data);
            return res;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static String updateDoc(int id,String data){
        String url = ESUrl+"/"+index+"/doc/"+id+"?pretty";
        try{
            String res = HttpUtil.sendPut(url,data);
            return res;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static String deleteDoc(int id){
        String url = ESUrl+"/"+index+"/doc/"+id+"?pretty";
        try{
            String res = HttpUtil.sendDelete(url,"");
            return res;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String queryAbilityByNameAndDes(String keywords){


        JsonObject condition1 =  new JsonObject();
        condition1.addProperty("serviceName",keywords);
        JsonObject condition2 =  new JsonObject();
        condition2.addProperty("serviceDescription",keywords);

        JsonObject match1 =  new JsonObject();
        match1.add("match",condition1);
        JsonObject match2 =  new JsonObject();
        match2.add("match",condition2);

        JsonObject should = new JsonObject();
        JsonArray array = new JsonArray();
        array.add(match1);
        array.add(match2);
        should.add("should",array);

        JsonObject bool = new JsonObject();
        bool.add("bool",should);

        JsonObject query = new JsonObject();
        query.add("query",bool);
        String url = ESUrl+"/"+index+"/_search";
        try{
            return HttpUtil.sendGetForEs(url,query.toString());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
