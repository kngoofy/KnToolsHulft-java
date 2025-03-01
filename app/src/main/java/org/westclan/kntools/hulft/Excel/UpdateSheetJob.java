package org.westclan.kntools.hulft.Excel;

import java.io.IOException;
import java.util.ArrayList;
// import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.westclan.kntools.hulft.model.RDefHulftJob;
import org.westclan.kntools.hulft.model.RDefHulftJobFlat;

/**
 * クラス：テンプレートExcelBookのジョブ起動情報[Job]シートにCellデータとしてプロットする
 */
public class UpdateSheetJob {

    /**
     * テンプレートExcelBookのョブ起動情報[Job]シートにCellデータとしてプロット
     * 
     * @param workbook データを配置する ExcelBook
     * @param listJob  RDefHulftTJobレコードのArrayList
     * @return true or false
     * @throws IOException
     */
    public boolean updateExcelSheetJob(Workbook workbook, ArrayList<RDefHulftJobFlat> listJob) throws IOException {

        // Record RDefHulftJob型のArrayListの要素数を確保
        int sizeList = listJob.size();

        // Record RDefHulftTJobの要素数を確保
        int countRecord = RDefHulftJob.class.getRecordComponents().length;

        // [Job]シートを対象に
        Sheet sheet = workbook.getSheet("Job");

        // セルスタイルの組み立て
        CellStyle[] cellStyles = createCellStypes(workbook);

        // ExcelBook のSheetにデータを配置していく
        Row row;
        Cell cell;
        // CellStyle cellStyle = workbook.createCellStyle();
        // Font font = workbook.createFont();
        int offsetRow = 3;
        int offsetCell = 1;
        // RDefHulftJob record;
        RDefHulftJobFlat record;
        // int arraySize = 0;
        // var jobBook = new UpdateExcelBookJob();
        // Excelの行単位のループ処理
        for (int rowIndex = 0; rowIndex < sizeList; rowIndex++) {
            row = sheet.createRow(rowIndex + offsetRow);

            // ArrayList から ひとつのレコードを取り出す
            record = listJob.get(rowIndex);

            // int arraySize = record.DEF().size();

            // Excelの列単位のループ処理 セルをターゲット
            for (int colIndex = 0; colIndex < countRecord; colIndex++) {
                // Cellにデータを配置
                cell = row.createCell(colIndex + offsetCell);
                insertValueToCell(rowIndex, colIndex, cellStyles, cell, record);
            }
        }

        // セルの列幅を自動サイズに調整
        for (int colIndex = 0; colIndex < countRecord + offsetCell; colIndex++) {
            sheet.autoSizeColumn(colIndex);
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

        // id セルスタイル null
        CellStyle idNullCellStyle = workbook.createCellStyle();
        Font idNullFont = workbook.createFont();
        idNullFont.setFontName("Meiryo UI");
        idNullFont.setBold(true);
        idNullFont.setFontHeightInPoints((short) 10);
        idNullCellStyle.setFont(idNullFont);

        idNullCellStyle.setBorderLeft(BorderStyle.THIN);
        idNullCellStyle.setBorderRight(BorderStyle.THIN);
        idNullCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        idNullCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        idNullCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        idNullCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // id セルスタイル TopBorder
        CellStyle idTopCellStyle = workbook.createCellStyle();
        idTopCellStyle.cloneStyleFrom(idNullCellStyle);

        idTopCellStyle.setBorderTop(BorderStyle.THIN);
        idTopCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        // id セルスタイル Bottom
        CellStyle idBottomCellStyle = workbook.createCellStyle();
        idBottomCellStyle.cloneStyleFrom(idNullCellStyle);

        idBottomCellStyle.setBorderBottom(BorderStyle.THIN);
        idBottomCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        // id セルスタイル Both
        CellStyle idBothCellStyle = workbook.createCellStyle();
        idBothCellStyle.cloneStyleFrom(idNullCellStyle);

        idBothCellStyle.setBorderTop(BorderStyle.THIN);
        idBothCellStyle.setBorderBottom(BorderStyle.THIN);
        idBothCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        idBothCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        // jobDef デフォルト セルスタイル
        CellStyle jobDefCellStyle = workbook.createCellStyle();
        Font jobDefTopfFont = workbook.createFont();
        jobDefTopfFont.setFontName("Meiryo UI");
        jobDefTopfFont.setFontHeightInPoints((short) 10);
        jobDefCellStyle.setFont(jobDefTopfFont);

        jobDefCellStyle.setBorderTop(BorderStyle.THIN);
        jobDefCellStyle.setBorderBottom(BorderStyle.THIN);
        jobDefCellStyle.setBorderLeft(BorderStyle.THIN);
        jobDefCellStyle.setBorderRight(BorderStyle.THIN);
        jobDefCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        jobDefCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        jobDefCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        jobDefCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        jobDefCellStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
        jobDefCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // comment セルスタイル commentNull
        CellStyle commentNullCellStyle = workbook.createCellStyle();
        Font commentNullFont = workbook.createFont();
        commentNullFont.setFontName("Meiryo UI");
        // commentNullFont.setBold(true);
        commentNullFont.setFontHeightInPoints((short) 10);
        commentNullCellStyle.setFont(commentNullFont);

        commentNullCellStyle.setBorderLeft(BorderStyle.THIN);
        commentNullCellStyle.setBorderRight(BorderStyle.THIN);
        commentNullCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        commentNullCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        // comment セルスタイル commentTop
        CellStyle commentTopCellStyle = workbook.createCellStyle();
        commentTopCellStyle.cloneStyleFrom(commentNullCellStyle);

        commentTopCellStyle.setBorderTop(BorderStyle.THIN);
        commentTopCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        // comment セルスタイル commentBottom
        CellStyle commentBottomCellStyle = workbook.createCellStyle();
        commentBottomCellStyle.cloneStyleFrom(commentNullCellStyle);

        commentBottomCellStyle.setBorderBottom(BorderStyle.THIN);
        commentBottomCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        // comment セルスタイル commentBoth
        CellStyle commentBothCellStyle = workbook.createCellStyle();
        commentBothCellStyle.cloneStyleFrom(commentNullCellStyle);

        commentBothCellStyle.setBorderTop(BorderStyle.THIN);
        commentBothCellStyle.setBorderBottom(BorderStyle.THIN);
        commentBothCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        commentBothCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        //
        CellStyle[] cellStyles = { defCellStyle,
                idTopCellStyle, idNullCellStyle, idBothCellStyle, idBottomCellStyle,
                jobDefCellStyle,
                commentTopCellStyle, commentNullCellStyle, commentBothCellStyle, commentBottomCellStyle

        };

        return cellStyles;
    }

    /**
     * セルスタイル配列の Index用 enum
     */
    enum enumCellStyleType {
        def(0),

        idTop(1),
        idNull(2),
        idBoth(3),
        idBottom(4),

        jobDef(5),

        commentTop(6),
        commentNull(7),
        commentBoth(8),
        commentBottom(9),

        ;

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
     * @param record    RDefHulftJob型のrecord
     */
    // boolean insertValueToCell(int rowIndex, int colIndex, Workbook workbook,
    // CellStyle cellStyle, Font font,
    // Cell cell,
    // RDefHulftJob record) {
    boolean insertValueToCell(int rowIndex, int colIndex, CellStyle[] cellStyles,
            Cell cell,
            RDefHulftJobFlat record) {

        switch (colIndex) {

            case 0:
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(rowIndex + 1);
                break;

            case 1: // ID

                if (record.ID().matches("\\d+")) { // isDigit?
                    cell.setCellValue(Integer.parseInt(record.ID()));
                } else {
                    cell.setCellValue(record.ID());
                }

                if (record.hasBoderTop() && record.hasBoderBottom()) {
                    cell.setCellStyle(cellStyles[enumCellStyleType.idBoth.getNo()]);
                }
                if (!record.hasBoderTop() && !record.hasBoderBottom()) {
                    cell.setCellStyle(cellStyles[enumCellStyleType.idNull.getNo()]);
                }
                if (record.hasBoderTop() && !record.hasBoderBottom()) {
                    cell.setCellStyle(cellStyles[enumCellStyleType.idTop.getNo()]);
                }
                if (!record.hasBoderTop() && record.hasBoderBottom()) {
                    cell.setCellStyle(cellStyles[enumCellStyleType.idBottom.getNo()]);
                }

                break;

            // case 2: // JOB

            // // cell.setCellValue(record.JOB());

            // break;

            case 2: // DEF (ArrayList)

                // font.setFontHeightInPoints((short) 15);
                // cellStyle.setFont(font);

                // cell.setCellStyle(defCellStyle);
                cell.setCellStyle(cellStyles[enumCellStyleType.jobDef.getNo()]);
                cell.setCellValue(record.DEF());
                break;

            case 3: // COMMENT

                if (record.hasBoderTop() && record.hasBoderBottom()) {
                    cell.setCellStyle(cellStyles[enumCellStyleType.commentBoth.getNo()]);
                }
                if (!record.hasBoderTop() && !record.hasBoderBottom()) {
                    cell.setCellStyle(cellStyles[enumCellStyleType.commentNull.getNo()]);
                }
                if (record.hasBoderTop() && !record.hasBoderBottom()) {
                    cell.setCellStyle(cellStyles[enumCellStyleType.commentTop.getNo()]);
                }
                if (!record.hasBoderTop() && record.hasBoderBottom()) {
                    cell.setCellStyle(cellStyles[enumCellStyleType.commentBottom.getNo()]);
                }

                cell.setCellValue(record.COMMENT());
                break;

            default:
                break;
        }

        return true;
    }
}
