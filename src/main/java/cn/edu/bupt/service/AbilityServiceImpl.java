package cn.edu.bupt.service;

import cn.edu.bupt.common.model.Ability;
import cn.edu.bupt.mapper.AbilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */


@Service
public class AbilityServiceImpl implements AbilityService{
    @Autowired
    AbilityMapper abilityMapper;

    @Override
    @Transactional
    public void addAbilityToAbilityGroup(Ability ability) {
        abilityMapper.insert(ability);
    }

    @Override
    @Transactional
    public void updateAbility(Ability ability) {
        abilityMapper.updateAbilityDes(ability.getAbilityId(), ability.getAbilityDes());
    }

    @Override
    public void deleteAbilityFromAbilityGroup(int abilityId) {
        abilityMapper.deleteByAbilityId(abilityId);
    }

    @Override
    public List<Ability> findAbilitiesByModelId(int modelId) {
        return abilityMapper.findAllAbilityByModelId(modelId);
    }
}
