package org.westclan.kntools.hulft.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public abstract class ABuildHulftDef {

    public ArrayList<String> buildHulftDef(String stringDefine) throws IOException {

        String stringData = stringDefine;

        // ワーク用変数
        String line = "";

        // 一行ずつ ArrayList に積み上げ
        ArrayList<String> arrayList = new ArrayList<String>();

        // メイン処理 ループ
        try (var reader = new BufferedReader(new StringReader(stringData))) {

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                arrayList.add(line);
            }

        }

        return arrayList;
    }

}
