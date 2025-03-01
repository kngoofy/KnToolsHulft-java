package org.westclan.kntools.hulft.role;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;
import org.westclan.kntools.hulft.Excel.UpdateSheetDefTGrp;
import org.westclan.kntools.hulft.Excel.UpdateSheetTGrp;
import org.westclan.kntools.hulft.builder.BuildHulftDefTGrp;
import org.westclan.kntools.hulft.builder.BuildHulftTGrp;
import org.westclan.kntools.hulft.model.RDefHulftTGrpFlat;

/**
 * 転送グループ情報定義シートと転送グループ情報定義シート(定義テキスト)を作成する役割クラス
 */
public class RoleTGrp {

    /**
     * 転送グループ情報定義シートと転送グループ情報定義シート(定義テキスト)を作成する
     * 
     * @param workbook       ワークブック
     * @param stringDataTGrp 転送グループ情報定義データ String
     * @return
     * @throws IOException
     */
    public boolean roleTGrp(Workbook workbook, String stringDataTGrp) throws IOException {

        // (1-1) 転送グループ情報定義 データ ArrayList<RDefHulftHst> 組み立て
        var buildTGrp = new BuildHulftTGrp();
        // ArrayList<RDefHulftTGrp> listTGrp = buildTGrp.buildTGrpArray(stringDataTGrp);
        ArrayList<RDefHulftTGrpFlat> listTGrp = buildTGrp.buildTGrpArray(stringDataTGrp);

        // (1-2) 転送グループ情報定義シート[TGrp]にデータをプロットする
        var updateSheetTGrp = new UpdateSheetTGrp();
        updateSheetTGrp.updateExcelSheetTGrp(workbook, listTGrp);

        // (2-1) 転送グループ情報定義 データ ArrayList<String> 組み立て
        var buildDefTGrp = new BuildHulftDefTGrp();
        ArrayList<String> listDefTGrp = buildDefTGrp.buildHulftDefTGrp(stringDataTGrp);

        // (2-2) 転送グループ情報定義Textシート[DefTGrp]にデータをプロットする
        var updateSheetDefTGrp = new UpdateSheetDefTGrp();
        updateSheetDefTGrp.updateExclSheetDefTGrp(workbook, listDefTGrp);

        return true;
    }

}
