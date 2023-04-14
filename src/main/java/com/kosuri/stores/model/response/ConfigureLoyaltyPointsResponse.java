package com.kosuri.stores.model.response;

public class ConfigureLoyaltyPointsResponse extends GenericResponse{
    private Double totalSalesVolume;
    private Double totalDiscount;

    public Double getTotalSalesVolume() {
        return totalSalesVolume;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalSalesVolume(Double totalSalesVolume) {
        this.totalSalesVolume = totalSalesVolume;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}
