package com.kosuri.stores.handler;

import com.kosuri.stores.dao.PurchaseEntity;
import com.kosuri.stores.dao.SaleEntity;
import com.kosuri.stores.model.purchaseReport.PurchaseReportRecord;
import com.kosuri.stores.model.purchaseReport.SaleReportRecord;
import com.kosuri.stores.model.request.GeneratePurchaseReportRequest;
import com.kosuri.stores.model.request.GenerateReportRequest;
import com.kosuri.stores.model.request.GenerateSaleReportRequest;
import com.kosuri.stores.model.response.GeneratePurchaseReportResponse;
import com.kosuri.stores.model.response.GenerateSaleReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReportHandler {
    @Autowired
    private RepositoryHandler repositoryHandler;

    public GeneratePurchaseReportResponse generatePurchaseReport(GeneratePurchaseReportRequest request) throws Exception {
        Optional<List<PurchaseEntity>> purchaseRecords = repositoryHandler.getPurchaseRecordsByStore(request.getStoreId());

        if (!purchaseRecords.isPresent() || purchaseRecords.get().isEmpty()) {
            GeneratePurchaseReportResponse response = new GeneratePurchaseReportResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMsg("No records found for storeId");
            return response;
        }
        List<PurchaseReportRecord> purchaseReport = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (PurchaseEntity purchaseEntity : purchaseRecords.get()) {
            if (validateRecord(request, purchaseEntity.getDate()) && validateVendorAndProduct(request, purchaseEntity.getSuppName(), purchaseEntity.getItemCat())) {
                PurchaseReportRecord record = new PurchaseReportRecord();
                record.setStoreId(purchaseEntity.getStoreId());
                record.setDate(purchaseEntity.getDate());
                record.setVendorName(purchaseEntity.getSuppName());
                record.setProductType(purchaseEntity.getItemCat());
                record.setBatchNo(purchaseEntity.getBatchNo());
                record.setExpiryDate(purchaseEntity.getExpiryDate());
                //record.setMfgDate(purchaseEntity.get);
                record.setMrp(purchaseEntity.getmRP());
                record.setDiscount(purchaseEntity.getDiscValue());
                record.setGst(purchaseEntity.getcGSTAmt());
                record.setPurchasePrice(purchaseEntity.getPurRate());
                record.setPurchaseAmount(purchaseEntity.getPurValue());

                purchaseReport.add(record);
            }
        }
        GeneratePurchaseReportResponse response = new GeneratePurchaseReportResponse(HttpStatus.OK);
        response.setPurchaseReport(purchaseReport);

        return response;
    }

    private boolean validateRecord(GenerateReportRequest request, Date entityDate) throws Exception {
        boolean isValid = true;

        if(request.getDateFrom() != null && entityDate.before(request.getDateFrom())){
            isValid = false;
        }

        if(request.getDateTo() != null && entityDate.after(request.getDateTo())){
            isValid = false;
        }

        return isValid;
    }

    private boolean validateVendorAndProduct(GenerateReportRequest request, String vendor, String productType){
        boolean isValid = true;

        if(request.getVendorName() != null && !vendor.equals(request.getVendorName())){
            isValid = false;
        }

        if (request.getProductType() != null && !productType.equals(request.getProductType())){
            isValid = false;
        }

        return isValid;
    }

    public GenerateSaleReportResponse generateSaleReport(GenerateSaleReportRequest request) throws Exception {
        Optional<List<SaleEntity>> saleRecords = repositoryHandler.getSaleRecordsByStore(request.getStoreId());

        if (!saleRecords.isPresent() || saleRecords.get().isEmpty()) {
            GenerateSaleReportResponse response = new GenerateSaleReportResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMsg("No records found for storeId");
            return response;
        }

        List<SaleReportRecord> purchaseReport = new ArrayList<>();

        for (SaleEntity saleEntity : saleRecords.get()) {
            if (validateRecord(request, saleEntity.getDate()) && validateVendorAndProduct(request, saleEntity.getSuppName(), saleEntity.getCatName())) {
                SaleReportRecord record = new SaleReportRecord();
                record.setStoreId(saleEntity.getStoreId());
                record.setDate(saleEntity.getDate());
                record.setVendorName(saleEntity.getSuppName());
                record.setProductType(saleEntity.getCatName());
                record.setBatchNo(saleEntity.getBatchNo());
                record.setExpiryDate(saleEntity.getExpiryDate());
                //record.setMfgDate(purchaseEntity.get);
                record.setMrp(saleEntity.getmRP());
                record.setDiscount(saleEntity.getDiscValue());
                record.setGst(saleEntity.getcGSTAmt());
                record.setPurchasePrice(saleEntity.getPurRate());
                record.setPurchaseAmount(saleEntity.getTotal());

                purchaseReport.add(record);
            }
        }

        GenerateSaleReportResponse response = new GenerateSaleReportResponse(HttpStatus.OK);
        response.setPurchaseReport(purchaseReport);

        return response;
    }
}
