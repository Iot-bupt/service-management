package cn.edu.bupt.DeviceFilter;

import cn.edu.bupt.common.model.Event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 * 往前回溯 forwardDays 天，如果这些天中的指定时间点有大于 threshold 的天数满足，则返回true
 * 某一天是否满足的条件是指定时间点附近对指定的设备相同的服务调用的次数除以该时间段总的服务调用次数
 * 此处可以考虑置信度，tf-idf等等概念
 */
public class TimeFilter implements Filter{
    private int forwardDays;
    private int threshold;

    public TimeFilter(int forwardDays,int threshold){
        this.forwardDays = forwardDays;
        this.threshold = threshold;
    }
    @Override
    public double filter(DeviceFilterMetadata metadata) {
        //TODO 考虑往前推30天，在三十天中的当前时间点附近如果发生指定服务调用的天数为count1，在三十天中的当前时间点附近如果发生指定服务调用的次数和为count2
        // TODO 评价指标考虑设置为 （count1/30）*（30/(count2+1)）
        long currentTime = System.currentTimeMillis();
        final long oneDays = 1000*60*60*24;
        List<Event> ls = new ArrayList<>();
        //TODO 初始化ls
        int countOfdays = 0;
        List<Double> scoreOfday = new LinkedList<>();
        int index = 0;

        for(int i=30;i>0;i++){
                long startTime = currentTime-i*oneDays;
                Event ev = ls.get(index);
                int happen = 0;
                int totalHappen = 0;
                while(index<ls.size()&&ev.getTime()<startTime){
                    ev = ls.get(index);
                    if(ev.getTime()>startTime-60*60*1000){
                        happen = 1;
                        totalHappen ++;
                    }
                    index ++;
                }
            countOfdays += happen;
            happen = 1;
            scoreOfday.add(sigmoid(totalHappen));
        }

        double score1 = (double)countOfdays/30;

        double sum1 = 0;

        for(double d:scoreOfday){
            sum1 += d;
        }
        sum1 = sum1 / 30;

        int count2 = 0;
        for(;index<ls.size();index++){
            Event e = ls.get(index);
            if(e.getTime()>currentTime-60*60*1000){
                count2++;
            }
        }
        //TODO score2 主要考虑当前设备在过去的指定时间段附近的指定服务调用次数和当前时间段的指定服务调用次数的关系
        //TODO 比如说 device1，在过去的30天中12点到1点的servicce1的调用次数为n1,今天的12点到1点的服务调用次数为n2
        //TODO  则score2 = n2-n1（实际上对n2 和 n1 分别做了sigmoid处理以应对异常数据）
        double score2 = sum1>sigmoid(count2)?sum1-sigmoid(count2):0.0000001;

        return score1*score2;
    }
    private  double sigmoid(double x){
       return 1/(1+ Math.pow(Math.E,-x));
    }
}
