package cn.edu.bupt.DeviceFilter;

import cn.edu.bupt.util.BdNLPUtil;

/**
 * Created by Administrator on 2018/5/24.
 */
public class LocationFilter implements Filter{
    private double threshold;

    public LocationFilter(double threshold){
        this.threshold = threshold;
    }

    @Override
    public double filter(DeviceFilterMetadata metadata) {
        String location1 = metadata.getDevice().getLocation();
        String location2 = metadata.getLocation();
        double similarity = caculateSimilarity(location1,location2);
        return similarity;
    }

    private double caculateSimilarity(String location1, String location2) {
        try{
            return BdNLPUtil.getSimilarity(location1,location2,"BOW");
        }catch(Exception e){
            e.printStackTrace();
            return 0.1;
        }
    }
}
