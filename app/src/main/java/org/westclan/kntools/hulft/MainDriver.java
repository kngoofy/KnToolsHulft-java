package org.westclan.kntools.hulft;

import java.io.IOException;
// import java.io.FileInputStream;
// import java.util.HashMap;
// import java.util.Properties;
import org.westclan.kntools.hulft.Excel.*;
import org.westclan.kntools.hulft.data.*;
import org.westclan.kntools.hulft.util.Util;

public class MainDriver {

    public static void main(String[] args) throws IOException {

        // プロパティファイルから keyを指定して テンプレートファイルとターゲットファイルを取得する
        String templateExcelBook = Util.getProperties("file.template-excelbook");
        String createdExcelBook = Util.getProperties("file.create-excelbook");
        //
        System.out.println("");
        System.out.println("templateExcelBook => " + templateExcelBook);
        System.out.println("createdExcelBook  => " + createdExcelBook);
        System.out.println("");

        // ExcelBook作成する
        var updateExcelBook = new CreateExcelBook();
        updateExcelBook.createExcelBook(
                templateExcelBook, createdExcelBook,
                TestDataHst.Data,
                TestDataJob.Data,
                TestDataRcv.Data,
                TestDataSnd.Data,
                TestDataTGrp.Data);

        System.exit(0);

        // // プロパティファイルを読み込んで
        // HashMap<String, String> prop =
        // loadProperties("app\\src\\main\\resources\\config.properties");
        // // テンプレートExcelBookと組み立てるExcelBook名をセット
        // String templateExcelBook = prop.get("templateExcelBook");
        // String createdExcelBook = prop.get("createdExcelBook");

        // System.out.println("templateExcelBook := " + templateExcelBook);
        // System.out.println("createdExcelBook := " + createdExcelBook);

        // テストデータ生成準備
        // BuildHulft buildHulft = new BuildHulft();
        // HulftData hulftData = buildHulft.buildHulft(
        // TestDataHst.Data,
        // TestDataJob.Data,
        // TestDataRcv.Data,
        // TestDataSnd.Data,
        // TestDataTGrp.Data);

        // // ExcelBook作成する
        // var updateExcelBook = new CreateExcelBook();
        // updateExcelBook.createExcelBook(
        // templateExcelBook, createdExcelBook, hulftData);

        // templateExcelBook, createdExcelBook,
        // listHst,
        // listJob,
        // listRcv,
        // listSnd,
        // listTGrp);
    }

    // /**
    // * プロパティファイルからプロパティを読み込み HashMapに組み立てる
    // *
    // * @param propName プロパティファイル名
    // * @return HashMap<String, String>
    // */
    // public static HashMap<String, String> loadProperties(String propName) {
    // String templateExcelBook = "no-templateExcel.xlsx";
    // String createdExcelBook = "no-createExcel.xlsx";
    // var names = new HashMap<String, String>();

    // Properties properties = new Properties();
    // try (FileInputStream input = new FileInputStream(propName)) {
    // properties.load(input);
    // templateExcelBook = properties.getProperty("file.template-excelbook");
    // createdExcelBook = properties.getProperty("file.create-excelbook");
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // // HashMap に put
    // names.put("templateExcelBook", templateExcelBook);
    // names.put("createdExcelBook", createdExcelBook);

    // return names;
    // }

}
