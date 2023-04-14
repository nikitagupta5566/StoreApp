package com.kosuri.stores.handler;

import com.kosuri.stores.dao.PurchaseEntity;
import com.kosuri.stores.dao.PurchaseRepository;
import com.kosuri.stores.model.enums.StockUpdateRequestType;
import com.kosuri.stores.model.request.StockUpdateRequest;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class PurchaseHandler {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private StockHandler stockHandler;

    public void createPurchaseEntityFromRequest(MultipartFile reapExcelDataFile, String storeId) throws Exception {

        List<PurchaseEntity> purchaseArrayList = new ArrayList<PurchaseEntity>();
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);


        for (int i = 4; i < worksheet.getPhysicalNumberOfRows(); i++) {
            PurchaseEntity tempPurchase = new PurchaseEntity();

            XSSFRow row = worksheet.getRow(i);

            tempPurchase.setDoc_Number(String.valueOf(row.getCell(0).getNumericCellValue()));
            tempPurchase.setReadableDocNo(row.getCell(1).getStringCellValue());
            tempPurchase.setDate(row.getCell(2).getDateCellValue());
            tempPurchase.setBillNo(row.getCell(3).getStringCellValue());
            tempPurchase.setBillDt(row.getCell(4).getDateCellValue());
            tempPurchase.setItemCode(row.getCell(5).getStringCellValue());
            tempPurchase.setItemName(row.getCell(6).getStringCellValue());
            tempPurchase.setBatchNo(row.getCell(7).getStringCellValue());
            tempPurchase.setExpiryDate(row.getCell(8).getDateCellValue());
            tempPurchase.setCatCode(row.getCell(9).getStringCellValue());
            tempPurchase.setCatName(row.getCell(10).getStringCellValue());
            tempPurchase.setMfacCode(row.getCell(11).getStringCellValue());
            tempPurchase.setMfacName(row.getCell(12).getStringCellValue());
            tempPurchase.setBrandName(row.getCell(13).getStringCellValue());
            tempPurchase.setPacking(row.getCell(14).getStringCellValue());
            tempPurchase.setDcYear(row.getCell(15).getStringCellValue());
            tempPurchase.setDcPrefix(row.getCell(16).getStringCellValue());
            tempPurchase.setDcSrno(row.getCell(17).getStringCellValue());
            tempPurchase.setQty((int) row.getCell(18).getNumericCellValue());
            tempPurchase.setPackQty(row.getCell(19).getNumericCellValue());
            tempPurchase.setLooseQty(row.getCell(20).getNumericCellValue());
            tempPurchase.setSchPackQty(row.getCell(21).getNumericCellValue());
            tempPurchase.setSchLooseQty(row.getCell(22).getNumericCellValue());
            tempPurchase.setSchDisc(row.getCell(23).getNumericCellValue());
            tempPurchase.setSaleRate(row.getCell(24).getNumericCellValue());
            tempPurchase.setPurRate(row.getCell(25).getNumericCellValue());
            tempPurchase.setmRP(row.getCell(26).getNumericCellValue());
            tempPurchase.setPurValue(row.getCell(27).getNumericCellValue());
            tempPurchase.setDiscPer(row.getCell(28).getNumericCellValue());
            tempPurchase.setMargin(row.getCell(29).getNumericCellValue());
            tempPurchase.setSuppCode(row.getCell(30).getStringCellValue());
            tempPurchase.setSuppName(row.getCell(31).getStringCellValue());
            tempPurchase.setDiscValue(row.getCell(32).getNumericCellValue());
            tempPurchase.setTaxableAmt(row.getCell(33).getNumericCellValue());
            tempPurchase.setGstCode(row.getCell(34).getStringCellValue());
            tempPurchase.setcGSTPer((int)row.getCell(35).getNumericCellValue());
            tempPurchase.setcGSTAmt(row.getCell(36).getNumericCellValue());
            tempPurchase.setsGSTPer((int)row.getCell(37).getNumericCellValue());
            tempPurchase.setsGSTAmt(row.getCell(38).getNumericCellValue());
            tempPurchase.setiGSTPer((int)row.getCell(39).getNumericCellValue());
            tempPurchase.setCessPer((int)row.getCell(40).getNumericCellValue());
            tempPurchase.setCessAmt(row.getCell(41).getNumericCellValue());
            tempPurchase.setTotal(row.getCell(42).getNumericCellValue());
            tempPurchase.setPost(String.valueOf(row.getCell(43).getNumericCellValue()));
            tempPurchase.setStoreId(storeId);

            purchaseArrayList.add(tempPurchase);
        }

        purchaseRepository.saveAll(purchaseArrayList);

        for(PurchaseEntity purchaseEntity: purchaseArrayList) {
            updateStock(purchaseEntity);
        }
    }

    private void updateStock(PurchaseEntity purchaseEntity) {
        StockUpdateRequest stockUpdateRequest = new StockUpdateRequest();
        stockUpdateRequest.setExpiryDate(purchaseEntity.getExpiryDate());
        stockUpdateRequest.setBalLooseQuantity(purchaseEntity.getLooseQty());
        stockUpdateRequest.setBatch(purchaseEntity.getBatchNo());
        stockUpdateRequest.setStockUpdateRequestType(StockUpdateRequestType.PURCHASE);
        stockUpdateRequest.setBalPackQuantity(purchaseEntity.getPackQty());
        stockUpdateRequest.setBalQuantity(purchaseEntity.getPackQty()); // Check this
//        stockUpdateRequest.setTotal();
        stockUpdateRequest.setItemCategory(purchaseEntity.getItemCat());
        stockUpdateRequest.setItemCode(purchaseEntity.getItemCode());
        stockUpdateRequest.setItemName(purchaseEntity.getItemName());
        stockUpdateRequest.setMfName(purchaseEntity.getMfacName());
        stockUpdateRequest.setManufacturer(purchaseEntity.getMfacCode());
        stockUpdateRequest.setStoreId(purchaseEntity.getStoreId());
        stockUpdateRequest.setMrpPack(purchaseEntity.getmRP());
        stockUpdateRequest.setSupplierName(purchaseEntity.getSuppName());

        stockHandler.updateStock(stockUpdateRequest);
    }
}
