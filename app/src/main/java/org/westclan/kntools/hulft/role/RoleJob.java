package org.westclan.kntools.hulft.role;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;
import org.westclan.kntools.hulft.Excel.UpdateSheetDefJob;
import org.westclan.kntools.hulft.Excel.UpdateSheetJob;
import org.westclan.kntools.hulft.builder.BuildHulftDefJob;
import org.westclan.kntools.hulft.builder.BuildHulftJob;
import org.westclan.kntools.hulft.model.RDefHulftJobFlat;

/**
 * ジョブ起動情報定義シートとジョブ起動情報定義シート(定義テキスト)を作成する役割クラス
 */
public class RoleJob {

    /**
     * ジョブ起動情報定義シートとジョブ起動情報定義シート(定義テキスト)を作成する
     * 
     * @param workbook      ワークブック
     * @param stringDataJob ジョブ起動情報定義データ String
     * @return
     * @throws IOException
     */
    public boolean roleJob(Workbook workbook, String stringDataJob) throws IOException {

        // (2-1)ジョブ起動情報定義 データ ArrayList<RDefHulftHst> 組み立て
        var buildJob = new BuildHulftJob();
        ArrayList<RDefHulftJobFlat> listJob = buildJob.buildJobArray(stringDataJob);

        // (1-1) ジョブ起動情報定義シート[Job]にデータをプロットする
        var updateSheetJob = new UpdateSheetJob();
        updateSheetJob.updateExcelSheetJob(workbook, listJob);

        // (2-1)ジョブ起動情報定義 データ ArrayList<String> 組み立て
        var buildDefJob = new BuildHulftDefJob();
        ArrayList<String> listDefJob = buildDefJob.buildHulftDefJob(stringDataJob);

        // (2-1) ジョブ起動情報定義Textシート[DefJob]にデータをプロットする
        var updateSheetDefJob = new UpdateSheetDefJob();
        updateSheetDefJob.updateExclSheetDefJob(workbook, listDefJob);

        return true;
    }
}
