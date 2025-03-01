package org.westclan.kntools.hulft.Excel;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 集信管理情報 定義シート作成
 */
public class UpdateSheetDefRcv extends AUpdateExcelBookDef {

    /**
     * 集信管理情報 定義シート作成
     * 
     * @param workbook
     * @param listDefRcv
     * @return
     */
    public boolean updateExclSheetDefRcv(Workbook workbook, ArrayList<String> listDefRcv) {

        // [DefRcv]シートを対象に

        // 親クラスの abstract AUpdateExcelBookDef の updateExclSheetDef を実行
        updateExclSheetDef(workbook, listDefRcv, "DefRcv");

        return true;
    }

}
