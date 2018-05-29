package cn.edu.bupt.DeviceFilter;

import java.util.List;

/**
 * Created by Administrator on 2018/5/29.
 */
public class MultiplicationStrategy implements SimilarityCaculateStrategy{
    @Override
    public double caculateSimilarity(List<Filter> filters, DeviceFilterMetadata deviceFilterMetadata) {
       double res = deviceFilterMetadata.getKeywordSimilarity();
        for(Filter f:filters){
            res *= f.filter(deviceFilterMetadata);
        }
        return res;
    }
}
