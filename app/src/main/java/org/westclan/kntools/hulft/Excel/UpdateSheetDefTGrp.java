package org.westclan.kntools.hulft.Excel;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 転送グループ情報[ 定義シート作成
 */
public class UpdateSheetDefTGrp extends AUpdateExcelBookDef {

    /**
     * 転送グループ情報[ 定義シート作成
     * 
     * @param workbook
     * @param listDefTGrp
     * @return
     */
    public boolean updateExclSheetDefTGrp(Workbook workbook, ArrayList<String> listDefTGrp) {

        // [DefTGrp]シートを対象に

        // 親クラスの abstract AUpdateExcelBookDef の updateExclSheetDef を実行
        updateExclSheetDef(workbook, listDefTGrp, "DefTGrp");

        return true;
    }

}
