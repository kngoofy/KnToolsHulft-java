package org.westclan.kntools.hulft.Excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.westclan.kntools.hulft.role.RoleHst;
import org.westclan.kntools.hulft.role.RoleJob;
import org.westclan.kntools.hulft.role.RoleRcv;
import org.westclan.kntools.hulft.role.RoleSnd;
import org.westclan.kntools.hulft.role.RoleTGrp;

/**
 * クラス：Hulft定義の ExcelBookを生成するクラス
 */
public class CreateExcelBook {

    /**
     * Hulft定義のExcelBookを生成する
     * 
     * @param templateExcelFile 元となるテンプレート ExcelBook
     * @param targetExcelFile   テンプレートにデータを配置して、生成する ExcelBook
     * @param unitStringHst     詳細ホスト情報 定義Stringデータ
     * @param unitStringJob     ジョブ起動情報 定義Stringデータ
     * @param unitStringRcv     集信管理情報 定義Stringデータ
     * @param unitStringSnd     配信管理情報 定義Stringデータ
     * @param unitStringTGrp    転送グループ情報 定義Stringデータ
     * @return
     */
    public boolean createExcelBook(
            String templateExcelFile, String targetExcelFile,
            String unitStringHst,
            String unitStringJob,
            String unitStringRcv,
            String unitStringSnd,
            String unitStringTGrp) {

        System.out.println("");
        System.out.println("テンプレートExcelBook(templateExcelFile)   :" + templateExcelFile);
        System.out.println("生成するExcelBook(targetExcelFile)         :" + targetExcelFile);
        System.out.println("");

        // 処理ブロック テンプレートのExcelBookを読み込んで、workbook変数へ
        try (FileInputStream fis = new FileInputStream(templateExcelFile);
                Workbook workbook = new XSSFWorkbook(fis)) {

            // (0) Index シートにデータプをロットする
            UpdateSheetIndex updateSheetIndex = new UpdateSheetIndex();
            updateSheetIndex.updateSheetIndex(workbook);

            // (1-1) 詳細ホスト情報定義シート[Hst]にデータをプロットする
            // (1-2) 詳細ホスト情報Text定義シート[DefHst]にデータをプロットする
            RoleHst hst = new RoleHst();
            hst.roleHst(workbook, unitStringHst);

            // (2-1) ジョブ起動情報定義シート[Job]にデータをプロットする
            // (2-2) ジョブ起動情報定義Textシート[DefJob]にデータをプロットする
            RoleJob job = new RoleJob();
            job.roleJob(workbook, unitStringJob);

            // (3-1) 集信管理情報定義シート[Rcv]にデータをプロットする
            // (3-2) 集信管理情報定義Textシート[DefRcv]にデータをプロットする
            RoleRcv rcv = new RoleRcv();
            rcv.roleRcv(workbook, unitStringRcv);

            // (4-1) 配信管理情報定義シート[Snd]にデータをプロットする
            // (4-2) 配信管理情報定義Textシート[DefSnd]にデータをプロットする
            RoleSnd snd = new RoleSnd();
            snd.roleSnd(workbook, unitStringSnd);

            // (5-1) 転送グループ情報定義シート[TGrp]にデータをプロットする
            // (5-2) 転送グループ情報定義Textシート[DefTGrp]にデータをプロットする
            RoleTGrp tgrp = new RoleTGrp();
            tgrp.roleTGrp(workbook, unitStringTGrp);

            // ターゲットExcelBookを書き出す
            try (FileOutputStream fos = new FileOutputStream(targetExcelFile)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
