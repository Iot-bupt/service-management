package cn.edu.bupt.util;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/5/30.
 */
public class BdNLPUtil {
    private static final String APP_ID = "你的 App ID";
    private static final String API_KEY = "18c5087d5a3842199108c6dc1c0a1cc7";
    private static final String SECRET_KEY = "efaf620f7a8c4c178ecb526eebd737f7";
    private static final  AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
    private static JSONArray temp = null;

    public static List<String> getKeyWords(String sentence) throws Exception{
        JSONObject res = client.lexer(sentence,new HashMap<>());
        JSONArray array = res.getJSONArray("items");
        if(array == null){
            throw new Exception("调用百度分词api报错");
        }
        temp = array;
        List<String> ans = new ArrayList<>();
        for(int i=0;i<temp.length();i++){
            JSONObject curr = temp.getJSONObject(i);
            String str = curr.getString("pos");
            if(str.startsWith("n")||str.startsWith("v")||str.equals("f")||str.equals("s")){
                ans.add(curr.getString("item"));
            }
        }
        return ans;
    }
    public static List<String> getLocation(String sentence) throws Exception{
        if (temp==null){
            throw new Exception("分词结果为null");
        }
        List<String> ans = new ArrayList<>();
        for(int i=0;i<temp.length();i++){
            JSONObject curr = temp.getJSONObject(i);
            String str = curr.getString("pos");
            if(str.startsWith("n")||str.equals("f")||str.equals("s")||str.equals("t")){
                ans.add(curr.getString("item"));
            }
        }
        return ans;
    }

    public static double getSimilarity(String text1,String text2,String model) throws Exception{
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("model", model);
        JSONObject res = client.simnet(text1,text2,options);
        return res.getDouble("score");
    }
}
