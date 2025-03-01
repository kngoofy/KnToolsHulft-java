package org.westclan.kntools.hulft.role;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;
import org.westclan.kntools.hulft.Excel.UpdateSheetDefHst;
import org.westclan.kntools.hulft.Excel.UpdateSheetHst;
import org.westclan.kntools.hulft.builder.BuildHulftDefHst;
import org.westclan.kntools.hulft.builder.BuildHulftHst;
import org.westclan.kntools.hulft.model.RDefHulftHst;

/**
 * 詳細ホスト情報定義シートと詳細ホスト情報定義シート(定義テキスト)を作成する役割クラス
 */
public class RoleHst {

    /**
     * 詳細ホスト情報定義シートと詳細ホスト情報定義シート(定義テキスト)を作成する
     * 
     * @param workbook      ワークブック
     * @param stringDataHst 詳細ホスト情報定義データ String
     * @return
     * @throws IOException
     */
    public boolean roleHst(Workbook workbook, String stringDataHst) throws IOException {

        // (1-1) 詳細ホスト情報定義 データ ArrayList<RDefHulftHst> 組み立て
        var buildHst = new BuildHulftHst();
        ArrayList<RDefHulftHst> listHst = buildHst.buildHstArray(stringDataHst);

        // (1-2) 詳細ホスト情報定義シート[Hst]にデータをプロットする
        var updateSheetHst = new UpdateSheetHst();
        updateSheetHst.updateExcelSheetHst(workbook, listHst);

        // (2-1) 詳細ホスト情報定義 データ ArrayList<String> 組み立て
        var buildDefHst = new BuildHulftDefHst();
        ArrayList<String> listDefHst = buildDefHst.buildHulftDefHst(stringDataHst);

        // (2-2) 詳細ホスト情報Text定義シート[DefHst]にデータをプロットする
        var updateSheetDefHst = new UpdateSheetDefHst();
        updateSheetDefHst.updateExclSheetDefHst(workbook, listDefHst);

        return true;
    }
}
