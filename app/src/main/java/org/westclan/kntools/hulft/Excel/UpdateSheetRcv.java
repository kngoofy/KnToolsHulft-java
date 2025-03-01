package org.westclan.kntools.hulft.Excel;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.westclan.kntools.hulft.model.RDefHulftRcv;

/**
 * クラス：テンプレートExcelBookの集信管理情報[Rcv]シートにCellデータとしてプロットする
 */
public class UpdateSheetRcv {

    /**
     * テンプレートExcelBookの集信管理情報[Rcv]シートにCellデータとしてプロット
     * 
     * @param workbook データを配置する ExcelBook
     * @param listRcv  RDefHulftRcvレコードのArrayList
     * @return true or false
     * @throws IOException
     */
    public boolean updateExcelSheetRcv(Workbook workbook, ArrayList<RDefHulftRcv> listRcv) throws IOException {

        // Record RDefHulftRcv型のArrayListの要素数を確保
        int sizeList = listRcv.size();

        // Record RDefHulftTRcvの要素数を確保
        int countRecord = RDefHulftRcv.class.getRecordComponents().length;

        // [Rcv]シートを対象に
        Sheet sheet = workbook.getSheet("Rcv");

        // セルスタイルの組み立て
        CellStyle[] cellStyles = createCellStypes(workbook);

        // ExcelBook のSheetにデータを配置していく
        Row row;
        Cell cell;
        int offsetRow = 3;
        int offsetCell = 1;
        RDefHulftRcv record;

        var rcvBook = new UpdateSheetRcv();
        // Excelの行単位のループ処理
        for (int rowIndex = 0; rowIndex < sizeList; rowIndex++) {
            row = sheet.createRow(rowIndex + offsetRow);

            // ArrayList から ひとつのレコードを取り出す
            record = listRcv.get(rowIndex);

            // Excelの列単位のループ処理 セルをターゲット
            for (int colIndex = 0; colIndex < countRecord; colIndex++) {
                // Cellにデータを配置
                cell = row.createCell(colIndex + offsetCell);
                rcvBook.insertValueToCell(rowIndex, colIndex, cellStyles, cell, record);
            }
        }

        // セルの列幅を自動サイズに調整
        for (int colIndex = 0; colIndex < countRecord; colIndex++) {
            sheet.autoSizeColumn(colIndex + offsetCell);
        }

        return true;
    }

    /**
     * 適用するセルスタイルの作成
     * 
     * @param workbook
     * @return
     */
    private CellStyle[] createCellStypes(Workbook workbook) {

        // デフォルト セルスタイル
        CellStyle defCellStyle = workbook.createCellStyle();
        Font defFont = workbook.createFont();
        defFont.setFontName("Meiryo UI");
        defFont.setFontHeightInPoints((short) 10);
        defCellStyle.setFont(defFont);

        defCellStyle.setBorderTop(BorderStyle.THIN);
        defCellStyle.setBorderBottom(BorderStyle.THIN);
        defCellStyle.setBorderLeft(BorderStyle.THIN);
        defCellStyle.setBorderRight(BorderStyle.THIN);
        defCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        defCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        defCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        defCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        // id セルスタイル
        CellStyle idCellStyle = workbook.createCellStyle();
        idCellStyle.cloneStyleFrom(defCellStyle);

        Font idFont = workbook.createFont();
        idFont.setFontName("Meiryo UI");
        idFont.setBold(true);
        idFont.setFontHeightInPoints((short) 10);
        idCellStyle.setFont(idFont);

        idCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        idCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // file セルスタイル
        CellStyle fileCellStyle = workbook.createCellStyle();
        fileCellStyle.cloneStyleFrom(idCellStyle);

        fileCellStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
        fileCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //
        CellStyle[] cellStyles = { defCellStyle, idCellStyle, fileCellStyle };

        return cellStyles;
    }

    /**
     * セルスタイル配列の Index用 enum
     */
    enum enumCellStyleType {
        def(0),
        id(1),
        file(2);

        private int no;

        enumCellStyleType(int no) {
            this.no = no;
        }

        public int getNo() {
            return this.no;
        }
    }

    /**
     * セルにデータをプロットする
     * 
     * @param rowIndex  ExcelBookの行を示すインデックス
     * @param colIndex  ExcelBookの列を示すインデックス
     * @param workbook  対象ExcelBook名
     * @param cellStyle Cellのスタイル
     * @param font      Font指定
     * @param cell      プロットする対象のCell
     * @param record    RDefHulftRcv型のrecord
     */
    boolean insertValueToCell(int rowIndex, int colIndex, CellStyle[] cellStyles,
            Cell cell,
            RDefHulftRcv record) {

        switch (colIndex) {
            case 0:
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(rowIndex + 1);
                break;

            case 1: // ID
                cell.setCellStyle(cellStyles[enumCellStyleType.id.getNo()]);
                if (record.ID().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.ID()));
                } else {
                    cell.setCellValue(record.ID());
                }
                break;

            // case 2: // RCVFILE
            // cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
            // cell.setCellValue(record.RCVFILE());
            // break;

            case 2: // FILENAME
                cell.setCellStyle(cellStyles[enumCellStyleType.file.getNo()]);
                // cell.setCellStyle(fileCellStyle);
                cell.setCellValue(record.FILENAME());
                break;

            case 3: // OWNER
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.OWNER());
                break;

            case 4: // GROUP
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.GROUP());
                break;

            case 5: // PERM
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.PERM().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.PERM()));
                } else {
                    cell.setCellValue(record.PERM());
                }
                break;

            case 6: // CODESET
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.CODESET().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.CODESET()));
                } else {
                    cell.setCellValue(record.CODESET());
                }
                break;

            case 7: // TRANSMODE
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.TRANSMODE());
                break;

            case 8: // ABNORMAL
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.ABNORMAL());
                break;

            case 9: // RCVTYPE
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.RCVTYPE());
                break;

            case 10: // GENCTL
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.GENCTL());
                break;

            case 11: // GENMNGNO
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.GENMNGNO().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.GENMNGNO()));
                } else {
                    cell.setCellValue(record.GENMNGNO());
                }
                break;

            case 12: // JOBID
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.JOBID());
                break;

            case 13: // EJOBID
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.EJOBID());
                break;

            case 14: // GRPID
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.GRPID());
                break;

            case 15: // PASSWORD
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.PASSWORD());
                break;

            case 16: // JOBWAIT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.JOBWAIT());
                break;

            case 17: // DATAVERIFY
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.DATAVERIFY().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.DATAVERIFY()));
                } else {
                    cell.setCellValue(record.DATAVERIFY());
                }
                break;

            case 18: // COMMENT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.COMMENT());
                break;

            default:
                break;
        }

        return true;
    }
}
