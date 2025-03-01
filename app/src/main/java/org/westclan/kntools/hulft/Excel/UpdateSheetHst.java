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
import org.westclan.kntools.hulft.model.RDefHulftHst;

/**
 * クラス：テンプレートExcelBookの詳細ホスト情報[Hst]シートにCellデータとしてプロットする
 */
public class UpdateSheetHst {

    /**
     * テンプレートExcelBookの詳細ホスト情報[Hst]シートにCellデータとしてプロット
     * 
     * @param workbook データを配置する ExcelBook
     * @param listHst  RDefHulftHstレコードのArrayList
     * @return true or false
     * @throws IOException
     */
    public boolean updateExcelSheetHst(Workbook workbook, ArrayList<RDefHulftHst> listHst) throws IOException {

        // Record RDefHulftHst 型のArrayListの要素数を確保
        int sizeList = listHst.size();

        // Record RDefHulftTHstの要素数を確保
        int countRecord = RDefHulftHst.class.getRecordComponents().length;

        // [Hst]シートを対象に
        Sheet sheet = workbook.getSheet("Hst");

        // セルスタイルの組み立て
        CellStyle[] cellStyles = createCellStypes(workbook);

        // ExcelBook のSheetにデータを配置していく
        Row row;
        Cell cell;
        // CellStyle cellStyle = workbook.createCellStyle();
        // Font font = workbook.createFont();
        int offsetRow = 3;
        int offsetCell = 1;
        RDefHulftHst record;
        var hstBook = new UpdateSheetHst();
        // Excelの行単位のループ処理
        for (int rowIndex = 0; rowIndex < sizeList; rowIndex++) {
            row = sheet.createRow(rowIndex + offsetRow);

            // ArrayList から ひとつのレコードを取り出す
            record = listHst.get(rowIndex);

            // Excelの列単位のループ処理 セルをターゲット
            for (int colIndex = 0; colIndex < countRecord; colIndex++) {
                // Cellにデータを配置
                cell = row.createCell(colIndex + offsetCell);
                hstBook.insertValueToCell(rowIndex, colIndex, cellStyles, cell, record);
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

        //
        CellStyle[] cellStyles = { defCellStyle, idCellStyle };

        return cellStyles;
    }

    /**
     * セルスタイル配列の Index用 enum
     */
    enum enumCellStyleType {
        def(0),
        id(1),
        host(2);

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
     * @param record    RDefHulftHst型のrecord
     */
    boolean insertValueToCell(int rowIndex, int colIndex, CellStyle[] cellStyles,
            Cell cell,
            RDefHulftHst record) {

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

            // case 2: // HOST
            // cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
            // cell.setCellValue(record.HOST());
            // break;

            case 2: // HOSTTYPE
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.HOSTTYPE());
                break;

            case 3: // KCODETYPE
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.KCODETYPE().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.KCODETYPE()));
                } else {
                    cell.setCellValue(record.KCODETYPE());
                }
                break;

            case 4: // RCVPORT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.RCVPORT().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.RCVPORT()));
                } else {
                    cell.setCellValue(record.RCVPORT());
                }
                break;

            case 5: // REQPORT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.REQPORT().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.REQPORT()));
                } else {
                    cell.setCellValue(record.REQPORT());
                }
                break;

            case 6: // COMMENT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.COMMENT());
                break;

            case 7: // JISYEAR
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.JISYEAR().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.JISYEAR()));
                } else {
                    cell.setCellValue(record.JISYEAR());
                }
                break;

            case 8: // CONNECTTYPE
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.CONNECTTYPE());
                break;

            case 9: // MYPROXYNAME
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.MYPROXYNAME());
                break;

            case 10: // MYPROXYPORT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.MYPROXYPORT().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.MYPROXYPORT()));
                } else {
                    cell.setCellValue(record.MYPROXYPORT());
                }
                break;

            case 11: // HOSTSPSNUM
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                if (record.HOSTSPSNUM().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.HOSTSPSNUM()));
                } else {
                    cell.setCellValue(record.HOSTSPSNUM());
                }
                break;

            case 12: // SENDPERMIT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.SENDPERMIT());
                break;

            case 13: // HULJOBPERMIT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.HULJOBPERMIT());
                break;

            case 14: // HULSNDRCPERMIT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.HULSNDRCPERMIT());
                break;

            case 15: // HULRJOBPERMIT
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.HULRJOBPERMIT());
                break;

            case 16: // ALLOWINSTTRANS
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.ALLOWINSTTRANS());
                break;

            case 17: // ALLOWINSTTRANS
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.USRNOTIFY());
                break;

            case 18: // HUL7MODE
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(record.HUL7MODE());
                break;

            default:
                break;
        }

        return true;
    }
}