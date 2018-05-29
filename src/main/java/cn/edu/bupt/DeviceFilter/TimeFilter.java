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
        return 1.0;
    }
}
