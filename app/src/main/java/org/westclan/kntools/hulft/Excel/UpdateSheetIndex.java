package org.westclan.kntools.hulft.Excel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Index シートにデータをプロットするクラス
 */
public class UpdateSheetIndex {

    /**
     * Index シートに (作成)日付をプロットする
     * 
     * @param workbook ExcelWorkBook
     * @return
     */
    public boolean updateSheetIndex(Workbook workbook) {

        // [Index]シートを対象に
        Sheet sheet = workbook.getSheet("Index");

        //
        Row row;
        Cell cell;

        // 作成日付をセルに
        row = sheet.getRow(2);
        cell = row.getCell(9);
        cell.setCellValue(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        return true;

    }
}
