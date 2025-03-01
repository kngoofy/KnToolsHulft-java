package org.westclan.kntools.hulft.Excel;

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

/**
 * Hulft 定義 Line 用 Abstract クラス
 */
public abstract class AUpdateExcelBookDef {

    /**
     * Sheet にデータをプロットして埋めていく
     * 
     * @param workbook
     * @param listDefJob
     * @param sheetName
     * @return
     */
    boolean updateExclSheetDef(Workbook workbook, ArrayList<String> listDefJob, String sheetName) {

        // sheetName シートを対象に
        Sheet sheet = workbook.getSheet(sheetName);

        // セルスタイルの組み立て
        CellStyle[] cellStyles = createCellStypes(workbook);

        // ExcelBook のSheetにデータを配置していく
        Row row;
        Cell cell;
        int offsetRow = 3;
        int offsetCell = 1;
        int maxColumns = 2;

        String line;

        for (int rowIndex = 0; rowIndex < listDefJob.size(); rowIndex++) {

            row = sheet.createRow(rowIndex + offsetRow);

            line = listDefJob.get(rowIndex);

            // Excelの列単位のループ処理 セルをターゲット
            for (int colIndex = 0; colIndex < 2; colIndex++) {
                // Cellにデータを配置
                cell = row.createCell(colIndex + offsetCell);
                insertValueToCell(rowIndex, colIndex, cellStyles, cell, line);
            }

        }

        // セルの列幅を自動サイズに調整
        for (int colIndex = 0; colIndex < maxColumns; colIndex++) {
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

        // line セルスタイル
        CellStyle lineCellStyle = workbook.createCellStyle();
        Font lineFont = workbook.createFont();
        lineFont.setFontName("Meiryo UI");
        // lineFont.setBold(true);
        lineFont.setFontHeightInPoints((short) 10);
        lineCellStyle.setFont(lineFont);

        lineCellStyle.setBorderTop(BorderStyle.THIN);
        lineCellStyle.setBorderBottom(BorderStyle.THIN);
        lineCellStyle.setBorderLeft(BorderStyle.THIN);
        lineCellStyle.setBorderRight(BorderStyle.THIN);
        lineCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lineCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lineCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lineCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lineCellStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
        lineCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // line # ID= セルスタイル
        CellStyle lineCellIDStyle = workbook.createCellStyle();
        lineCellIDStyle.cloneStyleFrom(lineCellStyle);

        Font lineIDFont = workbook.createFont();
        lineIDFont.setFontName("Meiryo UI");
        lineIDFont.setBold(true);
        lineIDFont.setFontHeightInPoints((short) 10);
        lineIDFont.setColor(IndexedColors.BLUE.getIndex());
        lineCellStyle.setFont(lineIDFont);

        //
        CellStyle[] cellStyles = { defCellStyle, lineCellStyle, lineCellIDStyle };

        return cellStyles;
    }

    /**
     * セルスタイル配列の Index用 enum
     */
    enum enumCellStyleType {
        def(0),
        line(1),
        lineID(2);

        private int no;

        enumCellStyleType(int no) {
            this.no = no;
        }

        public int getNo() {
            return this.no;
        }
    }

    /**
     * セルへデータをプロットして埋める
     * 
     * @param rowIndex
     * @param colIndex
     * @param workbook
     * @param cellStyle
     * @param font
     * @param cell
     * @param line
     * @return
     */
    private boolean insertValueToCell(int rowIndex, int colIndex, CellStyle[] cellStyles, Cell cell, String line) {

        switch (colIndex) {

            case 0:
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(rowIndex + 1);
                break;

            case 1: //

                if (line.matches("^# ID=.*$") || (line.matches("^#.*$"))) {
                    cell.setCellStyle(cellStyles[enumCellStyleType.line.getNo()]);
                } else {
                    cell.setCellStyle(cellStyles[enumCellStyleType.lineID.getNo()]);
                }

                // if (line.ID().matches("\\d+")) { // isDigit?
                // cell.setCellValue(Integer.parseInt(line.ID()));
                // } else {
                // cell.setCellValue(line.ID());
                // }
                cell.setCellValue(line);
                break;

            default:
                break;
        }

        return true;
    }

}
