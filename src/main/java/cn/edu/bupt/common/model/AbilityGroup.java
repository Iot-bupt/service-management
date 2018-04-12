package cn.edu.bupt.common.model;

import lombok.Data;
import lombok.Getter;

/**
 * Created by Administrator on 2018/4/12.
 */

public class AbilityGroup {
    @Getter
    private Manufacturer manufacturer;
    @Getter
    private DeviceType deviceType;
    @Getter
    private Model model;
    public AbilityGroup(String manufacturerName, String deviceTypeName, String modelName){
        this.manufacturer = new Manufacturer();
        manufacturer.setManufacturerName(manufacturerName);
        this.deviceType = new DeviceType();
        deviceType.setDeviceTypeName(deviceTypeName);
        this.model = new Model();
        model.setModelName(modelName);
    }
}
