package org.westclan.kntools.hulft.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {

    public String readFromFileToString(String fileName) throws FileNotFoundException, IOException {

        if (!(new File(fileName)).exists()) {
            System.out.print("ファイルが存在しません " + fileName);
            System.exit(1);

        }

        // ワーク用
        String line = "";
        // ArrayList<String> arrayList = new ArrayList<>();

        StringBuffer strBuf = new StringBuffer();

        // ファイルをオープンしてバッファリード
        try (var reader = new BufferedReader(new FileReader(fileName))) {

            // 一行づつ
            while ((line = reader.readLine()) != null) {
                // arrayList.add(line);
                strBuf.append(line + "\n");
            }

        }

        // 積み上げた ArrayList を返す
        // return arrayList;
        return strBuf.toString();
    }
}
