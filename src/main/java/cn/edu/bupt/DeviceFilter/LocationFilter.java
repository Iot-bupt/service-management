package cn.edu.bupt.DeviceFilter;

/**
 * Created by Administrator on 2018/5/24.
 */
public class LocationFilter implements Filter{
    private double threshold;

    public LocationFilter(double threshold){
        this.threshold = threshold;
    }

    @Override
    public boolean filter(DeviceFilterMetadata metadata) {
        String location1 = metadata.getDevice().getLocation();
        String location2 = metadata.getLocation();
        double similarity = caculateSimilarity(location1,location2);
        if(similarity>=threshold){
            return true;
        }else{
            return false;
        }
    }

    private double caculateSimilarity(String location1, String location2) {
        return 0;
    }
}
