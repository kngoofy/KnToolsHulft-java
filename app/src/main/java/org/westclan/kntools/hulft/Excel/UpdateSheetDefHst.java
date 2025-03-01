package org.westclan.kntools.hulft.Excel;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 詳細ホスト情報 定義シート作成
 */
public class UpdateSheetDefHst extends AUpdateExcelBookDef {
    /**
     * 詳細ホスト情報 定義シート作成
     * 
     * @param workbook
     * @param listDefHst
     * @return
     */
    public boolean updateExclSheetDefHst(Workbook workbook, ArrayList<String> listDefHst) {

        // [DefHst]シートを対象に

        // 親クラスの abstract AUpdateExcelBookDef の updateExclSheetDef を実行
        updateExclSheetDef(workbook, listDefHst, "DefHst");

        return true;
    }

}
