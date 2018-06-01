package cn.edu.bupt.DeviceFilter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 */
@Component
public class FilterFacade implements Filter{
    @Value("filter.locationfilter.threhold")
    private double locationThrehold;

    @Value("filter.timefilter.forwardDays")
    private int forwardDays;

    @Value("filter.timefilter.threhold")
    private int timeThrehold;

    List<Filter> filters;
    SimilarityCaculateStrategy startegy;
    @PostConstruct
    private void init(){
        filters = new ArrayList<>();
        filters.add(new LocationFilter(locationThrehold));
        filters.add(new TimeFilter(forwardDays,timeThrehold));
        startegy = new MultiplicationStrategy();
    }
    @Override
    public double filter(DeviceFilterMetadata metadata) {
        return startegy.caculateSimilarity(filters,metadata);
    }
}