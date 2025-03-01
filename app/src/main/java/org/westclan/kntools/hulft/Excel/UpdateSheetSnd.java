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
import org.westclan.kntools.hulft.model.RDefHulftSnd;

/**
 * クラス：テンプレートExcelBookの配信管理情報[Snd]シートにCellデータとしてプロットする
 */
public class UpdateSheetSnd {

    /**
     * テンプレートExcelBookの配信管理情報[Snd]シートにCellデータとしてプロット
     * 
     * @param workbook データを配置する ExcelBook
     * @param listSnd  RDefHulftTSndレコードのArrayList
     * @return true or false
     * @throws IOException
     */
    public boolean updateExcelSheetSnd(Workbook workbook, ArrayList<RDefHulftSnd> listSnd) throws IOException {

        // Record RDefHulftSnd型のArrayListの要素数を確保
        int sizeList = listSnd.size();

        // Record RDefHulftTGrpの要素数を確保
        int countRecord = RDefHulftSnd.class.getRecordComponents().length;

        // [Snd]シートを対象に
        Sheet sheet = workbook.getSheet("Snd");

        // セルスタイルの組み立て
        CellStyle[] cellStyles = createCellStypes(workbook);

        // ExcelBook のSheetにデータを配置していく
        Row row;
        Cell cell;
        int offsetRow = 3;
        int offsetCell = 1;
        RDefHulftSnd record;

        var sndBook = new UpdateSheetSnd();
        // Excelの行単位のループ処理
        for (int rowIndex = 0; rowIndex < sizeList; rowIndex++) {
            row = sheet.createRow(rowIndex + offsetRow);

            // ArrayList から ひとつのレコードを取り出す
            record = listSnd.get(rowIndex);

            // Excelの列単位のループ処理 セルをターゲット
            for (int colIndex = 0; colIndex < countRecord; colIndex++) {
                // Cellにデータを配置
                cell = row.createCell(colIndex + offsetCell);
                sndBook.insertValueToCell(rowIndex, colIndex, cellStyles, cell, record);
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

        Font fileFont = workbook.createFont();
        fileFont.setFontName("Meiryo UI");
        fileFont.setBold(true);
        fileFont.setFontHeightInPoints((short) 10);
        fileCellStyle.setFont(fileFont);

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
     * @param record    RDefHulftSnd型のrecord
     */
    boolean insertValueToCell(int rowIndex, int colIndex, CellStyle[] cellStyles,
            Cell cell,
            RDefHulftSnd record) {

        switch (colIndex) {
            case 0:
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(rowIndex + 1);
                break;

            case 1: // SNDFILE
                cell.setCellStyle(cellStyles[enumCellStyleType.id.getNo()]);
                if (record.ID().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.ID()));
                } else {
                    cell.setCellValue(record.ID());
                }
                break;

            // case 2: // SNDFILE
            // cell.setCellValue(record.SNDFILE());
            // break;

            case 2: // FILENAME
                cell.setCellStyle(cellStyles[enumCellStyleType.file.getNo()]);
                cell.setCellValue(record.FILENAME());
                break;

            case 3: // INTERVAL
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.INTERVAL().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.INTERVAL()));
                } else {
                    cell.setCellValue(record.INTERVAL());
                }
                break;

            case 4: // BLOCKLEN
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.BLOCKLEN().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.BLOCKLEN()));
                } else {
                    cell.setCellValue(record.BLOCKLEN());
                }
                break;

            case 5: // BLOCKCNT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.BLOCKCNT().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.BLOCKCNT()));
                } else {
                    cell.setCellValue(record.BLOCKCNT());
                }
                break;

            case 6: // COMP
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.COMP().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.COMP()));
                } else {
                    cell.setCellValue(record.COMP());
                }
                break;

            case 7: // COMPSIZE
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.COMPSIZE().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.COMPSIZE()));
                } else {
                    cell.setCellValue(record.COMPSIZE());
                }
                break;

            case 8: // COMPMODE
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.COMPMODE());
                break;

            case 9: // TRANSPRTY
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.TRANSPRTY().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.TRANSPRTY()));
                } else {
                    cell.setCellValue(record.TRANSPRTY());
                }
                break;

            case 10: // TRANSTYPE
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.TRANSTYPE());
                break;

            case 11: // CODESET
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.CODESET().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.CODESET()));
                } else {
                    cell.setCellValue(record.CODESET());
                }
                break;

            case 12: // KJCHNGE
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.KJCHNGE());
                break;

            case 13: // SHIFTTRANSACT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.SHIFTTRANSACT());
                break;

            case 14: // CLEAR
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.CLEAR());
                break;

            case 15: // PREJOBID
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.PREJOBID().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.PREJOBID()));
                } else {
                    cell.setCellValue(record.PREJOBID());
                }
                break;

            case 16: // JOBID
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.JOBID().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.JOBID()));
                } else {
                    cell.setCellValue(record.JOBID());
                }
                break;

            case 17: // EJOBID
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.EJOBID().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.EJOBID()));
                } else {
                    cell.setCellValue(record.EJOBID());
                }
                break;

            case 18: // DBID
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.DBID());
                break;

            case 19: // GRPID
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.GRPID().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.GRPID()));
                } else {
                    cell.setCellValue(record.GRPID());
                }
                break;

            case 20: // FMTID
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.FMTID());
                break;

            case 21: // PASSWORD
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.PASSWORD());
                break;

            case 22: // COMMENT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.COMMENT());
                break;

            default:
                break;
        }

        return true;
    }

}
