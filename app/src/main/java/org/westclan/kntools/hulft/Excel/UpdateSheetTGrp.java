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
import org.westclan.kntools.hulft.model.RDefHulftTGrp;
import org.westclan.kntools.hulft.model.RDefHulftTGrpFlat;

/**
 * クラス：テンプレートExcelBookの転送グループ情報[TGrp]シートにCellデータとしてプロットする
 */
public class UpdateSheetTGrp {

    /**
     * テンプレートExcelBookの転送グループ情報[TGrp]シートにCellデータとしてプロット
     * 
     * @param workbook データを配置する ExcelBook
     * @param listTGrp RDefHulftTGrpレコードのArrayList
     * @return true or false
     * @throws IOException
     */
    // boolean updateExcelSheetTGrp(Workbook workbook, ArrayList<RDefHulftTGrp>
    // listTGrp) throws IOException {
    public boolean updateExcelSheetTGrp(Workbook workbook, ArrayList<RDefHulftTGrpFlat> listTGrp) throws IOException {

        // Record RDefHulftTGrp型のArrayListの要素数を確保
        int sizeList = listTGrp.size();

        // Record RDefHulftTGrpの要素数を確保
        int countRecord = RDefHulftTGrp.class.getRecordComponents().length;

        // [TGrp]シートを対象に
        Sheet sheet = workbook.getSheet("TGrp");

        // セルスタイルの組み立て
        CellStyle[] cellStyles = createCellStypes(workbook);

        // ExcelBook のSheetにデータを配置していく
        Row row;
        Cell cell;
        int offsetRow = 3;
        int offsetCell = 1;
        // RDefHulftTGrp record;
        RDefHulftTGrpFlat record;

        var tgrpBook = new UpdateSheetTGrp();
        // Excelの行単位のループ処理
        for (int rowIndex = 0; rowIndex < sizeList; rowIndex++) {
            row = sheet.createRow(rowIndex + offsetRow);

            // ArrayList から ひとつのレコードを取り出す
            record = listTGrp.get(rowIndex);
            // System.out.println("Tgprecord -> " + record);

            // Excelの列単位のループ処理 セルをターゲット
            for (int colIndex = 0; colIndex < countRecord; colIndex++) {
                // Cellにデータを配置
                cell = row.createCell(colIndex + offsetCell);
                tgrpBook.insertValueToCell(rowIndex, colIndex, cellStyles, cell, record);
            }
        }

        // セルの列幅を自動サイズに調整
        for (int colIndex = 0; colIndex < countRecord + offsetCell; colIndex++) {
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

        // id Null セルスタイル
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

        // id Top セルスタイル topBorder
        CellStyle idTopCellStyle = workbook.createCellStyle();
        idTopCellStyle.cloneStyleFrom(idNullCellStyle);

        idTopCellStyle.setBorderTop(BorderStyle.THIN);
        idTopCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        // id Bottom セルスタイル
        CellStyle idBottomCellStyle = workbook.createCellStyle();
        idBottomCellStyle.cloneStyleFrom(idNullCellStyle);

        idBottomCellStyle.setBorderBottom(BorderStyle.THIN);
        idBottomCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        // id Bothセルスタイル
        CellStyle idBothCellStyle = workbook.createCellStyle();
        idBothCellStyle.cloneStyleFrom(idNullCellStyle);

        idBothCellStyle.setBorderTop(BorderStyle.THIN);
        idBothCellStyle.setBorderBottom(BorderStyle.THIN);
        idBothCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        idBothCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        // ServerDef セルスタイル
        CellStyle serverDefCellStyle = workbook.createCellStyle();
        Font serverDefTopfFont = workbook.createFont();
        serverDefTopfFont.setFontName("Meiryo UI");
        serverDefTopfFont.setFontHeightInPoints((short) 10);
        serverDefCellStyle.setFont(serverDefTopfFont);

        serverDefCellStyle.setBorderTop(BorderStyle.THIN);
        serverDefCellStyle.setBorderBottom(BorderStyle.THIN);
        serverDefCellStyle.setBorderLeft(BorderStyle.THIN);
        serverDefCellStyle.setBorderRight(BorderStyle.THIN);
        serverDefCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        serverDefCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        serverDefCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        serverDefCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        serverDefCellStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
        serverDefCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // デフォルト セルスタイル
        CellStyle defBottomCellStyle = workbook.createCellStyle();
        Font defBottomFont = workbook.createFont();
        defBottomFont.setFontName("Meiryo UI");
        defBottomFont.setFontHeightInPoints((short) 10);
        defBottomCellStyle.setFont(defBottomFont);

        defBottomCellStyle.setBorderTop(BorderStyle.THIN);
        defBottomCellStyle.setBorderBottom(BorderStyle.THIN);
        defBottomCellStyle.setBorderLeft(BorderStyle.THIN);
        defBottomCellStyle.setBorderRight(BorderStyle.THIN);
        defBottomCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        defBottomCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        defBottomCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        defBottomCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        // line セルスタイル
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

        // comment セルスタイル topBorder
        CellStyle commentTopCellStyle = workbook.createCellStyle();
        commentTopCellStyle.cloneStyleFrom(commentNullCellStyle);

        commentTopCellStyle.setBorderTop(BorderStyle.THIN);
        commentTopCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        // commentBottom セルスタイル
        CellStyle commentBottomCellStyle = workbook.createCellStyle();
        commentBottomCellStyle.cloneStyleFrom(commentNullCellStyle);

        commentBottomCellStyle.setBorderBottom(BorderStyle.THIN);
        commentBottomCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        // commentBoth セルスタイル
        CellStyle commentBothCellStyle = workbook.createCellStyle();
        commentBothCellStyle.cloneStyleFrom(commentNullCellStyle);

        commentBothCellStyle.setBorderTop(BorderStyle.THIN);
        commentBothCellStyle.setBorderBottom(BorderStyle.THIN);
        commentBothCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        commentBothCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        //
        CellStyle[] cellStyles = { defCellStyle,
                idTopCellStyle, idNullCellStyle, idBothCellStyle, idBottomCellStyle,
                serverDefCellStyle,
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

        serverDef(5),

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
     * @param record    RDefHulftTGrp型のrecord
     */
    boolean insertValueToCell(int rowIndex, int colIndex, CellStyle[] cellStyles,
            Cell cell,
            RDefHulftTGrpFlat record) {

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

            // case 2: // GRP
            // cell.setCellStyle(tgrpCellStyle);
            // if (record.GRP().matches("\\d+")) { // isDigit?
            // cell.setCellValue(Integer.parseInt(record.GRP()));
            // } else {
            // cell.setCellValue(record.GRP());
            // }
            // break;

            case 2: // SERVER (ArrayList)
                cell.setCellStyle(cellStyles[enumCellStyleType.serverDef.getNo()]);
                cell.setCellValue(record.SERVER());
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
