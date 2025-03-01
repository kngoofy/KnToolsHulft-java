package org.westclan.kntools.hulft.builder;

import java.io.IOException;
import java.util.ArrayList;

/**
 * * クラス：Hulftのジョブ起動情報定義 組み立て ArrayList<String>
 */
public class BuildHulftDefJob extends ABuildHulftDef {

    /**
     * Hulftのジョブ起動情報定義 組み立て ArrayList<String>
     * 
     * @param stringDefine
     * @return
     * @throws IOException
     */
    public ArrayList<String> buildHulftDefJob(String stringDefine) throws IOException {

        // 親クラスの abstract ABuildHulftDef の buildHulftDef を実行
        ArrayList<String> arrayList = buildHulftDef(stringDefine);

        // String stringData = stringDefine;

        // // ワーク用変数
        // String line = "";

        // // 一行ずつ ArrayList に積み上げ
        // ArrayList<String> arrayList = new ArrayList<String>();

        // // メイン処理 ループ
        // try (var reader = new BufferedReader(new StringReader(stringData))) {

        // while ((line = reader.readLine()) != null) {
        // line = line.trim();
        // arrayList.add(line);
        // }

        // }

        return arrayList;
    }
}
