package cn.edu.bupt.mapper;

import cn.edu.bupt.common.model.DeviceType;
import cn.edu.bupt.common.model.Model;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@Mapper
public interface ModelMapper {
    @Insert("insert into model (manufacturer_id,device_type_id,model_name) values (#{manufacturerId},#{deviceTypeId},#{modelName}) ")
    @Options(useGeneratedKeys = true, keyProperty = "modelId")
    void insert(Model model);

    @Select("select  device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,model_name as modelName  from model where manufacturer_id = #{manufacturerId} and device_type_id=#{deviceTypeId}")
    List<Model> findAll(@Param("manufacturerId")int manufacturerId,@Param("deviceTypeId")int deviceTypeId);

    @Select("select  device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,model_name as modelName from model where manufacturer_id = #{id} and   device_type_id=#{deviceTypeId} and model_name like CONCAT(CONCAT('%', #{keyWord}), '%')")
    List<Model> findAllByKeyWord(@Param("id")int id, @Param("deviceTypeId")int deviceTypeId,@Param("keyWord")String keyWord);
}