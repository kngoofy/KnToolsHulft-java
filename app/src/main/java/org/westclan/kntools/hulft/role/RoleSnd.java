package org.westclan.kntools.hulft.role;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;
import org.westclan.kntools.hulft.Excel.UpdateSheetDefSnd;
import org.westclan.kntools.hulft.Excel.UpdateSheetSnd;
import org.westclan.kntools.hulft.builder.BuildHulftDefSnd;
import org.westclan.kntools.hulft.builder.BuildHulftSnd;
import org.westclan.kntools.hulft.model.RDefHulftSnd;

/**
 * 配信管理情報定義シートと配信管理情報定義シート(定義テキスト)を作成する役割クラス
 */
public class RoleSnd {

    /**
     * 配信管理情報定義シートと配信管理情報定義シート(定義テキスト)を作成する
     * 
     * @param workbook      ワークブック
     * @param stringDataSnd 配信管理情報定義データ String
     * @return
     * @throws IOException
     */
    public boolean roleSnd(Workbook workbook, String stringDataSnd) throws IOException {

        // (1-1) 配信管理情報 データ ArrayList<RDefHulftHst> 組み立て
        var buildSnd = new BuildHulftSnd();
        ArrayList<RDefHulftSnd> listSnd = buildSnd.buildSndArray(stringDataSnd);

        // (1-2) 配信管理情報定義シート[Snd]にデータをプロットする
        var updateSheetSnd = new UpdateSheetSnd();
        updateSheetSnd.updateExcelSheetSnd(workbook, listSnd);

        // (2-1) 配信管理情報 データ ArrayList<String> 組み立て
        var buildDefSnd = new BuildHulftDefSnd();
        ArrayList<String> listDefSnd = buildDefSnd.buildHulftDefSnd(stringDataSnd);

        // (2-2) 配信管理情報定義Textシート[DefSnd]にデータをプロットする
        var updateSheetDefSnd = new UpdateSheetDefSnd();
        updateSheetDefSnd.updateExclSheetDefSnd(workbook, listDefSnd);

        return true;
    }
}
