package com.kosuri.stores.handler;

import com.kosuri.stores.dao.PurchaseEntity;
import com.kosuri.stores.model.purchaseReport.PurchaseReport;
import com.kosuri.stores.model.purchaseReport.PurchaseReportRecord;
import com.kosuri.stores.model.request.GeneratePurchaseReportRequest;
import com.kosuri.stores.model.response.GeneratePurchaseReportResponse;
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

        if (!purchaseRecords.isPresent() || purchaseRecords.isEmpty()) {
            GeneratePurchaseReportResponse response = new GeneratePurchaseReportResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMsg("No records found for storeId");
            return response;
        }
        List<PurchaseReportRecord> purchaseReport = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (PurchaseEntity purchaseEntity : purchaseRecords.get()) {
            if (validatePurchaseRecord(request, purchaseEntity)) {
                PurchaseReportRecord record = new PurchaseReportRecord();
                record.setStoreId(purchaseEntity.getStoreId());
                record.setDate(purchaseEntity.getDate());
                record.setVendorName(purchaseEntity.getSuppName());
                record.setProductType("TEST");
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

    private boolean validatePurchaseRecord(GeneratePurchaseReportRequest request, PurchaseEntity purchaseEntity) throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        if (!purchaseEntity.getDate().before(request.getDateFrom()) &&
                !purchaseEntity.getDate().after(request.getDateTo())) {
            return true;
        }
        return false;
    }
}
