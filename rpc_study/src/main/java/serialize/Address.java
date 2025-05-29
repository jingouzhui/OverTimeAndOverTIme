package serialize;

import java.io.Serializable;

/**
 * @description:
 * @author: jingouzhui
 * @date: 2025/5/29 11:23
 */
public class Address implements Serializable {
    private String province;  // 省级
    private String city;      // 市级
    private String district;  // 区/县级
    private String detail;    // 详细地址

    public Address(String province, String city,
                   String district, String detail) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.detail = detail;
    }
    public Address() {}

    // Getter/Setter方法
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}