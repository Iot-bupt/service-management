package cn.edu.bupt.DeviceFilter;

import cn.edu.bupt.common.model.Ability;
import cn.edu.bupt.common.model.Device;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/24.
 */
@Data
public class DeviceFilterMetadata {
    private Device device;
    private String location;
    private long currentTime;
    private Ability ability;
    private double keywordSimilarity;

    public DeviceFilterMetadata(Device d,String location,long time,Ability ability,
                                double keywordSimilarity){
        this.device = d;
        this.location = location;
        this.currentTime = time;
        this.ability = ability;
        this.keywordSimilarity = keywordSimilarity;
    }
}
