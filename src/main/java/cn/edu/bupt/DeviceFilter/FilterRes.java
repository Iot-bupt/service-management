package cn.edu.bupt.DeviceFilter;

import cn.edu.bupt.common.model.Ability;
import cn.edu.bupt.common.model.Device;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/29.
 */
@Data
public class FilterRes implements Comparable<FilterRes>{
    private Device device;
    private Ability ability;
    private double similarity;

    public FilterRes(Device Device, Ability ability, double similarity){
        this.device = device;
        this.ability = ability;
        this.similarity = similarity;
    }

    @Override
    public int compareTo(FilterRes that) {
        if(this.similarity<that.similarity){
            return -1;
        }else{
            return 1;
        }
    }
}
