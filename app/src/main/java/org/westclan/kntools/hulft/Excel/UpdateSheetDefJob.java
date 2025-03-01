package org.westclan.kntools.hulft.Excel;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * ジョブ起動情報 定義シート作成
 */
public class UpdateSheetDefJob extends AUpdateExcelBookDef {

    /**
     * ジョブ起動情報 定義シート作成
     * 
     * @param workbook
     * @param listDefJob
     * @return
     */
    public boolean updateExclSheetDefJob(Workbook workbook, ArrayList<String> listDefJob) {

        // [DefJob]シートを対象に

        // 親クラスの abstract AUpdateExcelBookDef の updateExclSheetDef を実行
        updateExclSheetDef(workbook, listDefJob, "DefJob");

        return true;
    }

}
