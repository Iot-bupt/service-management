package cn.edu.bupt.util;

import okhttp3.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2018/5/23.
 */
public class HttpUtil {
    private static OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public static String sendGet(String url,String reqBody) throws Exception{
        String res = "";
//        Request.Builder reqBuild = new Request.Builder();
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
//        urlBuilder.addQueryParameter(key, value);
//        reqBuild.url(urlBuilder.build());
//
//        reqBuild.b
//
////        RequestBody body = RequestBody.create(JSON, reqBody);
////        Request.Builder buider = new Request.Builder()
////                .url(url)
////                .get();
//        Request request =  buider.build();
//        Response response = client.newCall(request).execute();
//        if (response.isSuccessful()){
//            return response.body().string();
//        }else{
//            return null;
//        }
        try{
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
         //   connection.connect();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            PrintWriter  out = new PrintWriter(connection.getOutputStream());
            out.print(reqBody);
            out.flush();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                res += line;
            }
            out.close();
            in.close();
            return res;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String sendPut(String url,String reqBody) throws Exception{
        RequestBody body = RequestBody.create(JSON, reqBody);
        Request.Builder buider = new Request.Builder()
                .url(url).put(body);
        Request request =  buider.build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }else{
            return null;
        }
    }

    public static String sendPost(String url,String reqBody) throws Exception{
        RequestBody body = RequestBody.create(JSON, reqBody);
        Request.Builder buider = new Request.Builder()
                .url(url)
                .method("post",body);
        Request request =  buider.build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }else{
            return null;
        }
    }

    public static String sendDelete(String url,String reqBody) throws Exception{
        RequestBody body = RequestBody.create(JSON, reqBody);
        Request.Builder buider = new Request.Builder()
                .url(url).delete();
        Request request =  buider.build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }else{
            return null;
        }
    }
}
