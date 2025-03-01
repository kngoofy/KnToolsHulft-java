package org.westclan.kntools.hulft.role;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;
import org.westclan.kntools.hulft.Excel.UpdateSheetDefRcv;
import org.westclan.kntools.hulft.Excel.UpdateSheetRcv;
import org.westclan.kntools.hulft.builder.BuildHulftDefRcv;
import org.westclan.kntools.hulft.builder.BuildHulftRcv;
import org.westclan.kntools.hulft.model.RDefHulftRcv;

/**
 * 集信管理情報定義シートと集信管理情報定義シート(定義テキスト)を作成する役割クラス
 */
public class RoleRcv {

    /**
     * 集信管理情報定義シートと集信管理情報定義シート(定義テキスト)を作成する
     * 
     * @param workbook      ワークブック
     * @param stringDataRcv 集信管理情報定義データ String
     * @return
     * @throws IOException
     */
    public boolean roleRcv(Workbook workbook, String stringDataRcv) throws IOException {

        // (1-1) 集信管理情報 データ ArrayList<RDefHulftHst> 組み立て
        var buildRcv = new BuildHulftRcv();
        ArrayList<RDefHulftRcv> listRcv = buildRcv.buildRcvArray(stringDataRcv);

        // (1-2) 集信管理情報定義シート[Rcv]にデータをプロットする
        var updateSheetRcv = new UpdateSheetRcv();
        updateSheetRcv.updateExcelSheetRcv(workbook, listRcv);

        // (2-1) 集信管理情報 データ ArrayList<String> 組み立て
        var buildDefRcv = new BuildHulftDefRcv();
        ArrayList<String> listDefRcv = buildDefRcv.buildHulftDefRcv(stringDataRcv);

        // (2-2) 集信管理情報定義Textシート[DefRcv]にデータをプロットする
        var updateSheetDefRcv = new UpdateSheetDefRcv();
        updateSheetDefRcv.updateExclSheetDefRcv(workbook, listDefRcv);

        return true;
    }
}
