package com.kosuri.stores.handler;
import com.kosuri.stores.dao.PurchaseEntity;
import com.kosuri.stores.dao.SaleRepository;
import com.kosuri.stores.dao.SaleEntity;
import com.kosuri.stores.model.enums.StockUpdateRequestType;
import com.kosuri.stores.model.request.StockUpdateRequest;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;


@Service
public class SaleHandler {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private StockHandler stockHandler;
    public void createSaleEntityFromRequest(MultipartFile reapExcelDataFile, String storeId) throws Exception{

        List<SaleEntity> saleArrayList = new ArrayList<SaleEntity>();
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for(int i=4;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            SaleEntity tempSale = new SaleEntity();
            XSSFRow row = worksheet.getRow(i);
            tempSale.setDoc_Number(row.getCell(0).getStringCellValue());
            tempSale.setReadableDocNo(row.getCell(1).getStringCellValue());
            tempSale.setDate(row.getCell(2).getDateCellValue());
            tempSale.setTime(String.valueOf(row.getCell(3).getStringCellValue()));
            tempSale.setCustCode(row.getCell(4).getStringCellValue());
            tempSale.setCustName(row.getCell(5).getStringCellValue());


            tempSale.setPatientName(row.getCell(6).getStringCellValue());
            tempSale.setCreatedUser(row.getCell(7).getStringCellValue());
            tempSale.setItemCode(row.getCell(8).getStringCellValue());
            tempSale.setItemName(row.getCell(9).getStringCellValue());
            tempSale.setBatchNo(row.getCell(10).getStringCellValue());
            tempSale.setExpiryDate(row.getCell(11).getDateCellValue());
            tempSale.setMfacCode(row.getCell(12).getStringCellValue());
            tempSale.setMfacName(row.getCell(13).getStringCellValue());
            tempSale.setCatCode(row.getCell(14).getStringCellValue());
            tempSale.setCatName(row.getCell(15).getStringCellValue());
            tempSale.setBrandName(row.getCell(16).getStringCellValue());
            tempSale.setPacking(row.getCell(17).getStringCellValue());
            tempSale.setQtyBox((int)row.getCell(18).getNumericCellValue());
            tempSale.setQty((int)row.getCell(19).getNumericCellValue());
            tempSale.setSchQty((int)row.getCell(20).getNumericCellValue());
            tempSale.setSchDisc(row.getCell(21).getNumericCellValue());
            tempSale.setSaleRate(row.getCell(22).getNumericCellValue());
            tempSale.setmRP(row.getCell(23).getNumericCellValue());
            tempSale.setSaleValue(row.getCell(24).getNumericCellValue());
            tempSale.setDiscPerct(row.getCell(25).getNumericCellValue());
            tempSale.setDiscValue(row.getCell(26).getNumericCellValue());
            tempSale.setTaxableAmt(row.getCell(27).getNumericCellValue());
            tempSale.setcGSTPer((int)row.getCell(28).getNumericCellValue());
            tempSale.setcGSTAmt(row.getCell(29).getNumericCellValue());
            tempSale.setsGSTPer((int)row.getCell(30).getNumericCellValue());
            tempSale.setsGSTAmt(row.getCell(31).getNumericCellValue());
            tempSale.setiGSTPer((int)row.getCell(32).getNumericCellValue());
            tempSale.setiGSTAmt(row.getCell(33).getNumericCellValue());
            tempSale.setCessPer((int)row.getCell(34).getNumericCellValue());
            tempSale.setCessAmt(row.getCell(35).getNumericCellValue());
            tempSale.setAddCessPer((int)row.getCell(36).getNumericCellValue());
            tempSale.setAddCessAmt(row.getCell(37).getNumericCellValue());
            tempSale.setTotal(row.getCell(38).getNumericCellValue());
            tempSale.setRoundOff(row.getCell(39).getNumericCellValue());
            tempSale.setSuppBillNo(row.getCell(40).getStringCellValue());

            tempSale.setSuppCode(row.getCell(42).getStringCellValue());
            tempSale.setSuppName(row.getCell(43).getStringCellValue());
            tempSale.setProfessional(row.getCell(44).getStringCellValue());


            tempSale.setMobile(row.getCell(45).getStringCellValue());
            tempSale.setLcCode(row.getCell(46).getStringCellValue());
            tempSale.setPurRate(row.getCell(47).getNumericCellValue());
            tempSale.setPurRateWithGsT(row.getCell(48).getNumericCellValue());
            tempSale.setStoreId(storeId);

            saleArrayList.add(tempSale);
        }

        try {
            saleRepository.saveAll(saleArrayList);

            for(SaleEntity saleEntity: saleArrayList) {
                updateStock(saleEntity);
            }

        }catch(Exception e){
            System.out.println(e.getCause());
        }
    }

    private void updateStock(SaleEntity saleEntity) {
        StockUpdateRequest stockUpdateRequest = new StockUpdateRequest();
        stockUpdateRequest.setExpiryDate(saleEntity.getExpiryDate());
//        stockUpdateRequest.setBalLooseQuantity(saleEntity.getLooseQty());
        stockUpdateRequest.setBatch(saleEntity.getBatchNo());
        stockUpdateRequest.setStockUpdateRequestType(StockUpdateRequestType.SALE);
//        stockUpdateRequest.setBalPackQuantity(saleEntity.getPackQty());
//        stockUpdateRequest.setBalQuantity();
//        stockUpdateRequest.setTotal();
        stockUpdateRequest.setItemCode(saleEntity.getItemCode());
        stockUpdateRequest.setItemName(saleEntity.getItemName());
        stockUpdateRequest.setMfName(saleEntity.getMfacName());
        stockUpdateRequest.setManufacturer(saleEntity.getMfacCode());
        stockUpdateRequest.setStoreId(saleEntity.getStoreId());
        stockUpdateRequest.setMrpPack(saleEntity.getmRP());
        stockUpdateRequest.setSupplierName(saleEntity.getSuppName());

        stockHandler.updateStock(stockUpdateRequest);
    }
}
