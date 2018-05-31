package cn.edu.bupt.DeviceFilter;

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
        return 1.0;
    }
}
