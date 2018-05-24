package cn.edu.bupt.DeviceFilter;

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
}
