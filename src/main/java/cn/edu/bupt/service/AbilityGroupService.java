package cn.edu.bupt.service;

import cn.edu.bupt.common.model.AbilityGroup;

/**
 * Created by Administrator on 2018/4/12.
 */
public interface AbilityGroupService {
    public void addAbilityGroupService(AbilityGroup serviceGroup);
    public void getManufacturersByKeyWords(String keyWords);
    public void getDeviceTypesByKeyWords(String keyWords);
    public void getModelsByKeyWords(String keyWords);
}
