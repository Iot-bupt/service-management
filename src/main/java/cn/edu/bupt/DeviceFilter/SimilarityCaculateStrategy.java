package cn.edu.bupt.DeviceFilter;

import java.util.List;

/**
 * Created by Administrator on 2018/5/29.
 */
public interface SimilarityCaculateStrategy {
    public double caculateSimilarity(List<Filter> filters,DeviceFilterMetadata deviceFilterMetadata);
}
