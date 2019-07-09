package com.bawei.miaoguoqing0709.model.bean;

public class Bean {
private String commodityName;
private String masterPic;

    @Override
    public String toString() {
        return "Bean{" +
                "commodityName='" + commodityName + '\'' +
                ", masterPic='" + masterPic + '\'' +
                '}';
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getMasterPic() {
        return masterPic;
    }

    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }

    public Bean(String commodityName, String masterPic) {
        this.commodityName = commodityName;
        this.masterPic = masterPic;
    }
}
