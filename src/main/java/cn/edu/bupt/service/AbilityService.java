package cn.edu.bupt.service;

import cn.edu.bupt.common.model.Ability;

/**
 * Created by Administrator on 2018/4/12.
 */
public interface AbilityService {
    public void addAbilityToAbilityGroup(Ability ability);
    public void updateAbility(Ability ability);
    public void deleteAbilityFromAbilityGroup(int abilityId);
    public void findAbilitiesByModelId(int modelId);
}
