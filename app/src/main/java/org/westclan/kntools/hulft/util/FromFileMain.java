package org.westclan.kntools.hulft.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.westclan.kntools.hulft.Excel.CreateExcelBook;

public class FromFileMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // 引数のチェック
        if (args.length < 7) {
            usage();
            System.exit(1);
        }

        String templateExcelBook = args[0];
        String createExcelBook = args[1];
        String sndFile = args[2];
        String rcvFile = args[3];
        String tgrpFile = args[4];
        String hstFile = args[5];
        String jobFile = args[6];

        System.out.println("");
        System.out.println(templateExcelBook + " : templateExcelBook");
        System.out.println(createExcelBook + " : CreateExcelBook");
        System.out.println(sndFile + " : sndFile");
        System.out.println(rcvFile + " : rcvFile");
        System.out.println(tgrpFile + " : tgrpFile");
        System.out.println(hstFile + " : hstFile");
        System.out.println(jobFile + " : jobFile");

        //
        ReadFromFile file = new ReadFromFile();
        String snd = file.readFromFileToString(sndFile);
        String rcv = file.readFromFileToString(rcvFile);
        String tgrp = file.readFromFileToString(tgrpFile);
        String hst = file.readFromFileToString(hstFile);
        String job = file.readFromFileToString(jobFile);

        // ExcelBook作成する
        var updateExcelBook = new CreateExcelBook();
        updateExcelBook.createExcelBook(
                templateExcelBook, createExcelBook,
                hst,
                job,
                rcv,
                snd,
                tgrp);

        System.exit(0);

    }

    public static void usage() {
        String message = """
                Usage: 引数はこの7つです。
                  テンプレートExcelBook名
                  生成するExcelBook名
                  Snd定義ファイル
                  Rcv定義ファイル
                  TGrp定義ファイル
                  Hst定義ファイル
                  Job定義ファイル
                ex. thisjava templateHulft.xlsx serverHulft.xlsx snd.def rcv.def hst.def tgrp.def job.def
                  """;

        System.out.println(message);
    }
}
