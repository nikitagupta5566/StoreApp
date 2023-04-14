package com.kosuri.stores.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pharma_purchase_detail")
public class PurchaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name="Doc_Number")
    private String doc_Number;

    @Column(name="Ref_Doc_Number",nullable = true, length = 45)
    private String readableDocNo;
    @Column(name = "Date")
    private Date date;

    @Column(name="Bill_No",nullable = true, length = 45)
    private String billNo;

    @Column(name="Bill_Date",nullable = true, length = 45)
    private Date billDt;
    @Column(name="Item_Code",nullable = true, length = 45)
    private String itemCode;
    @Column(name="Item_Name",nullable = true, length = 45)
    private String itemName;
    @Column(name="Batch_Number",nullable = true, length = 45)
    private String batchNo;
    @Column(name="Expiry_Date",nullable = true, length = 45)
    private Date expiryDate;
    @Column(name="Cat_Code",nullable = true, length = 45)
    private String catCode;


    @Column(name="Cat_Name",nullable = true, length = 45)
    private String catName;
    @Column(name="Mfac_Code",nullable = true, length = 45)
    private String mfacCode;
    @Column(name="Mfac_Name",nullable = true, length = 45)
    private String mfacName;
    @Column(name="Brand_Name",nullable = true, length = 45)
    private String brandName;
    @Column(name="Packing",nullable = true, length = 45)
    private String packing;
    @Column(name="DC_Year",nullable = true, length = 45)
    private String dcYear;
    @Column(name="DC_Prefix",nullable = true, length = 45)
    private String dcPrefix;
    @Column(name="DC_Srno",nullable = true, length = 45)
    private String dcSrno;
    @Column(name="Qty_Box",nullable = true, length = 45)
    private Integer qty;
    @Column(name="Pack_Qty",nullable = true, length = 45)
    private Double packQty;
    @Column(name="Loose_Qty",nullable = true, length = 45)
    private Double looseQty;
    @Column(name="Sch_Pack_Qty",nullable = true, length = 45)
    private Double schPackQty;
    @Column(name="Sch_Loose_Qty",nullable = true, length = 45)
    private Double schLooseQty;
    @Column(name="Sch_Disc",nullable = true, length = 45)
    private Double schDisc;
    @Column(name="Sal_Rate",nullable = true, length = 45)
     private Double SaleRate;
    @Column(name="Pur_Rate",nullable = true, length = 45)
    private Double purRate;
    @Column(name="Mrp",nullable = true, length = 45)
    private Double mRP;
    @Column(name="Purchase_Value",nullable = true, length = 45)
    private Double purValue;
    @Column(name="Disc_Per",nullable = true, length = 45)
    private Double discPer;
    @Column(name="Margin",nullable = true, length = 45)
    private Double margin;
    @Column(name="Supp_Code",nullable = true, length = 45)
    private String suppCode;
    @Column(name="Supp_Name",nullable = true, length = 45)
    private String suppName;
    @Column(name="Disc",nullable = true, length = 45)
    private Double discValue;
    @Column(name="Taxable_Amt",nullable = true, length = 45)
    private Double taxableAmt;
    @Column(name="Gst_Code",nullable = true, length = 45)
    private String gstCode;
    @Column(name="Cgst",nullable = true, length = 45)
    private Integer cGSTPer;
    @Column(name="Sgst",nullable = true, length = 45)
    private Integer sGSTPer;
    @Column(name="Cgst_Amt",nullable = true, length = 45)
    private Double cGSTAmt;
    @Column(name="Sgst_Amt",nullable = true, length = 45)
    private Double sGSTAmt;
    @Column(name="Igst",nullable = true, length = 45)
    private Integer iGSTPer;
    @Column(name="Total",nullable = true, length = 45)
    private Double total;
    @Column(name="Post",nullable = true, length = 45)
    private String post;
    @Column(name="Item_Category",nullable = true, length = 45)
    private String itemCat;


    @Column(name="Cess_Per",nullable = true, length = 45)
    private Integer cessPer;
    @Column(name="Cess_Amt",nullable = true, length = 45)
    private Double cessAmt;
    @Column(name="Store_ID")
    private String storeId;

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public String getStoreId() {
        return storeId;
    }


    public String getItemCat() {
        return itemCat;
    }


    public void setItemCat(String itemCat) {
        this.itemCat = itemCat;
    }
    public void setSaleRate(Double saleRate) {
        this.SaleRate = saleRate;
    }

    public Double getSaleRate(String saleRate) {
        return this.SaleRate;
    }


    public void set(String itemCat) {
        this.itemCat = itemCat;
    }

    public String getDoc_Number() {
        return doc_Number;
    }


    public void setDoc_Number(String doc_Number) {
        this.doc_Number = doc_Number;
    }


    public String getReadableDocNo() {
        return readableDocNo;
    }


    public void setReadableDocNo(String readableDocNo) {
        this.readableDocNo = readableDocNo;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public String getBillNo() {
        return billNo;
    }


    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }


    public Date getBillDt() {
        return billDt;
    }


    public void setBillDt(Date billDt) {
        this.billDt = billDt;
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


    public String getBatchNo() {
        return batchNo;
    }


    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }


    public Date getExpiryDate() {
        return expiryDate;
    }


    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


    public String getCatCode() {
        return catCode;
    }


    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }


    public Integer getCessPer() {
        return cessPer;
    }


    public void setCessPer(Integer cessPer) {
        this.cessPer = cessPer;
    }


    public Double getCessAmt() {
        return cessAmt;
    }


    public void setCessAmt(Double cessAmt) {
        this.cessAmt = cessAmt;
    }


    public String getCatName() {
        return catName;
    }


    public void setCatName(String catName) {
        this.catName = catName;
    }


    public String getMfacCode() {
        return mfacCode;
    }


    public void setMfacCode(String mfacCode) {
        this.mfacCode = mfacCode;
    }


    public String getMfacName() {
        return mfacName;
    }


    public void setMfacName(String mfacName) {
        this.mfacName = mfacName;
    }

    public String getBrandName() {
        return brandName;
    }


    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


    public String getPacking() {
        return packing;
    }


    public void setPacking(String packing) {
        this.packing = packing;
    }


    public String getDcYear() {
        return dcYear;
    }


    public void setDcYear(String dcYear) {
        this.dcYear = dcYear;
    }


    public String getDcPrefix() {
        return dcPrefix;
    }


    public void setDcPrefix(String dcPrefix) {
        this.dcPrefix = dcPrefix;
    }


    public String getDcSrno() {
        return dcSrno;
    }


    public void setDcSrno(String dcSrno) {
        this.dcSrno = dcSrno;
    }


    public Integer getQty() {
        return qty;
    }


    public void setQty(Integer qty) {
        this.qty = qty;
    }


    public Double getPackQty() {
        return packQty;
    }


    public void setPackQty(Double packQty) {
        this.packQty = packQty;
    }


    public Double getLooseQty() {
        return looseQty;
    }


    public void setLooseQty(Double looseQty) {
        this.looseQty = looseQty;
    }


    public Double getSchPackQty() {
        return schPackQty;
    }


    public void setSchPackQty(Double schPackQty) {
        this.schPackQty = schPackQty;
    }


    public Double getSchLooseQty() {
        return schLooseQty;
    }


    public void setSchLooseQty(Double schLooseQty) {
        this.schLooseQty = schLooseQty;
    }


    public Double getSchDisc() {
        return schDisc;
    }


    public void setSchDisc(Double schDisc) {
        this.schDisc = schDisc;
    }


    public Double getPurRate() {
        return purRate;
    }


    public void setPurRate(Double purRate) {
        this.purRate = purRate;
    }


    public Double getmRP() {
        return mRP;
    }


    public void setmRP(Double mRP) {
        this.mRP = mRP;
    }


    public Double getPurValue() {
        return purValue;
    }


    public void setPurValue(Double purValue) {
        this.purValue = purValue;
    }


    public Double getDiscPer() {
        return discPer;
    }


    public void setDiscPer(Double discPer) {
        this.discPer = discPer;
    }


    public Double getMargin() {
        return margin;
    }


    public void setMargin(Double margin) {
        this.margin = margin;
    }

    public String getSuppCode() {
        return suppCode;
    }


    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }


    public String getSuppName() {
        return suppName;
    }


    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public Double getDiscValue() {
        return discValue;
    }


    public void setDiscValue(Double discValue) {
        this.discValue = discValue;
    }


    public Double getTaxableAmt() {
        return taxableAmt;
    }


    public void setTaxableAmt(Double taxableAmt) {
        this.taxableAmt = taxableAmt;
    }


    public String getGstCode() {
        return gstCode;
    }


    public void setGstCode(String gstCode) {
        this.gstCode = gstCode;
    }


    public Integer getcGSTPer() {
        return cGSTPer;
    }


    public void setcGSTPer(Integer cGSTPer) {
        this.cGSTPer = cGSTPer;
    }


    public Integer getsGSTPer() {
        return sGSTPer;
    }


    public void setsGSTPer(Integer sGSTPer) {
        this.sGSTPer = sGSTPer;
    }

    public Double getcGSTAmt() {
        return cGSTAmt;
    }

    public void setcGSTAmt(Double cGSTAmt) {
        this.cGSTAmt = cGSTAmt;
    }

    public Double getsGSTAmt() {
        return sGSTAmt;
    }

    public void setsGSTAmt(Double sGSTAmt) {
        this.sGSTAmt = sGSTAmt;
    }

    public Integer getiGSTPer() {
        return iGSTPer;
    }

    public void setiGSTPer(Integer iGSTPer) {
        this.iGSTPer = iGSTPer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Double getSaleRate() {
        return SaleRate;
    }
// getters and setters
}


