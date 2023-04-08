package com.kosuri.stores.model.response;

import com.kosuri.stores.model.purchaseReport.PurchaseReportRecord;
import com.kosuri.stores.model.purchaseReport.SaleReportRecord;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GenerateSaleReportResponse extends ResponseEntity<GenerateSaleReportResponse> {
    private List<SaleReportRecord> saleReport;
    private String msg;
    public GenerateSaleReportResponse(HttpStatusCode status) {
        super(status);
    }

    public List<SaleReportRecord> getPurchaseReport() {
        return saleReport;
    }

    public void setPurchaseReport(List<SaleReportRecord> purchaseReport) {
        this.saleReport = purchaseReport;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
