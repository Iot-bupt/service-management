package cn.edu.bupt.common.model;

import java.util.UUID;

/**
 * Created by Administrator on 2018/4/13.
 */
public class Device  {

    private UUID id;

    private Integer tenantId;

    private Integer customerId;

    private String name;

    private String searchText;

    private String parentDeviceId;

    private String deviceType; //设备

    private String manufacture;//厂商

    private String model;//设备型号

    private String status;//运行状态

    private String location;

//    public Device(Device device) {
//        this.id = id;
//        this.tenantId = tenantId;
//        this.customerId = customerId;
//        this.name = name;
//        this.searchText = searchText;
//        this.parentDeviceId = parentDeviceId;
//        this.deviceType = deviceType;
//        this.manufacture = manufacture;
//        this.model = model;
//        this.status = status;
//        this.location = location;
//    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentDeviceId() {
        return parentDeviceId;
    }

    public void setParentDeviceId(String parentDeviceId) {
        this.parentDeviceId = parentDeviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchTextSource() {
        return getName();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"tenantId\":")
                .append(tenantId);
        sb.append(",\"customerId\":")
                .append(customerId);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"searchText\":\"")
                .append(searchText).append('\"');
        sb.append(",\"parentDeviceId\":\"")
                .append(parentDeviceId).append('\"');
        sb.append(",\"deviceType\":\"")
                .append(deviceType).append('\"');
        sb.append(",\"manufacture\":\"")
                .append(manufacture).append('\"');
        sb.append(",\"model\":\"")
                .append(model).append('\"');
        sb.append(",\"status\":\"")
                .append(status).append('\"');
        sb.append(",\"location\":\"")
                .append(location).append('\"');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (id != null ? !id.equals(device.id) : device.id != null) return false;
        if (tenantId != null ? !tenantId.equals(device.tenantId) : device.tenantId != null) return false;
        if (customerId != null ? !customerId.equals(device.customerId) : device.customerId != null) return false;
        if (name != null ? !name.equals(device.name) : device.name != null) return false;
        if (searchText != null ? !searchText.equals(device.searchText) : device.searchText != null) return false;
        if (parentDeviceId != null ? !parentDeviceId.equals(device.parentDeviceId) : device.parentDeviceId != null)
            return false;
        if (deviceType != null ? !deviceType.equals(device.deviceType) : device.deviceType != null) return false;
        if (manufacture != null ? !manufacture.equals(device.manufacture) : device.manufacture != null) return false;
        if (model != null ? !model.equals(device.model) : device.model != null) return false;
        if (status != null ? !status.equals(device.status) : device.status != null) return false;
        return location != null ? location.equals(device.location) : device.location == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tenantId != null ? tenantId.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (searchText != null ? searchText.hashCode() : 0);
        result = 31 * result + (parentDeviceId != null ? parentDeviceId.hashCode() : 0);
        result = 31 * result + (deviceType != null ? deviceType.hashCode() : 0);
        result = 31 * result + (manufacture != null ? manufacture.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
