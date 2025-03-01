package org.westclan.kntools.hulft.Excel;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 配信管理情報 定義シート作成
 */
public class UpdateSheetDefSnd extends AUpdateExcelBookDef {

    /**
     * 配信管理情報 定義シート作成
     * 
     * @param workbook
     * @param listDefSnd
     * @return
     */
    public boolean updateExclSheetDefSnd(Workbook workbook, ArrayList<String> listDefSnd) {

        // [DefSnd]シートを対象に

        // 親クラスの abstract AUpdateExcelBookDef の updateExclSheetDef を実行
        updateExclSheetDef(workbook, listDefSnd, "DefSnd");

        return true;
    }

}
