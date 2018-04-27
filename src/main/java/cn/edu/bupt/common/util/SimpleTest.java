package cn.edu.bupt.common.util;

import cn.edu.bupt.common.model.DeviceType;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
public class SimpleTest {
    public static void main(String[] args){
        String str = "{\"test\":1 }";
        new JsonParser().parse(str).getAsJsonObject().get("test").getAsString();
       // BufferedReader
    }
}
