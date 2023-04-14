package com.kosuri.stores.model.request;

import com.kosuri.stores.model.enums.StockUpdateRequestType;

import java.util.Date;

public class StockUpdateRequest {
    private String manufacturer;

    private StockUpdateRequestType stockUpdateRequestType;

    private String mfName;

    private String itemCode;

    private String itemName;

    private String supplierName;

    private String rack;

    private String batch;

    private Date expiryDate;

    private Double balQuantity;

    private Double balPackQuantity;

    private Double balLooseQuantity;

    private String total;

    private Double mrpPack;

    private String itemCategory;

    private String storeId;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMfName() {
        return mfName;
    }

    public void setMfName(String mfName) {
        this.mfName = mfName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Double getBalQuantity() {
        return balQuantity;
    }

    public void setBalQuantity(Double balQuantity) {
        this.balQuantity = balQuantity;
    }

    public Double getBalPackQuantity() {
        return balPackQuantity;
    }

    public void setBalPackQuantity(Double balPackQuantity) {
        this.balPackQuantity = balPackQuantity;
    }

    public Double getBalLooseQuantity() {
        return balLooseQuantity;
    }

    public void setBalLooseQuantity(Double balLooseQuantity) {
        this.balLooseQuantity = balLooseQuantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Double getMrpPack() {
        return mrpPack;
    }

    public void setMrpPack(Double mrpPack) {
        this.mrpPack = mrpPack;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public StockUpdateRequestType getStockUpdateRequestType() {
        return stockUpdateRequestType;
    }

    public void setStockUpdateRequestType(StockUpdateRequestType stockUpdateRequestType) {
        this.stockUpdateRequestType = stockUpdateRequestType;
    }
}



