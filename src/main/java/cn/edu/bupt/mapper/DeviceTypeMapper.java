package cn.edu.bupt.mapper;

import cn.edu.bupt.common.model.DeviceType;
import cn.edu.bupt.common.model.Manufacturer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@Mapper
public interface DeviceTypeMapper {

    @Insert("insert into device_type (manufacturer_id,device_type_name) values (#{manufacturerId},#{deviceTypeName}) ")
    @Options(useGeneratedKeys = true, keyProperty = "deviceTypeId")
    void insert(DeviceType deviceType);

    @Select("select  device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,device_type_name as deviceTypeName  from device_type where manufacturer_id = #{id}")
    List<DeviceType> findAll(int id);

    @Select("select  device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,device_type_name as deviceTypeName  from device_type where manufacturer_id = #{id} and device_type_name like CONCAT(CONCAT('%', #{keyWord}), '%')")
    List<DeviceType> findAllByKeyWord(@Param("id")int id, @Param("keyWord")String keyWord);
}