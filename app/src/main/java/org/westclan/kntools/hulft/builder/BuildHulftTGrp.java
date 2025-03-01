package org.westclan.kntools.hulft.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.westclan.kntools.hulft.model.RDefHulftTGrp;
import org.westclan.kntools.hulft.model.RDefHulftTGrpFlat;

import static org.westclan.kntools.hulft.model.EHulftManageType.TGRP;

/**
 * クラス：Hulftの転送グループ情報定義 組み立て
 */
public class BuildHulftTGrp {

    /**
     * 
     * @param stringDefine 転送グループ情報定義(String
     * @return 転送グループ情報定義 (フラット) レコードのリスト
     * @throws IOException
     */
    public ArrayList<RDefHulftTGrpFlat> buildTGrpArray(String stringDefine) throws IOException {

        String stringData = stringDefine;

        // ワーク用変数
        String line = "";
        String key = "";
        String value = "";
        String arrayKey[] = {};

        // 転送グループ情報定義のキーワードを HashMap に格納
        HashMap<String, String> hashMap = new HashMap<>();
        // 転送グループ情報定義をレコード化した物を ArrayList に積み上げ
        ArrayList<RDefHulftTGrp> arrayList = new ArrayList<RDefHulftTGrp>();

        ArrayList<RDefHulftTGrpFlat> arrayFlatList = new ArrayList<RDefHulftTGrpFlat>();

        // 転送グループ定義の判定スイッチ
        boolean tgrp_def_switch = false;
        // 転送グループ定義 ArrayList
        ArrayList<String> tgrpArrayList = new ArrayList<String>();

        // メイン処理 ループ
        try (var reader = new BufferedReader(new StringReader(stringData))) {

            while ((line = reader.readLine()) != null) {

                line = line.trim();
                if (line == "")
                    continue;
                if (line.matches("^#$"))
                    continue;

                // 転送グループ情報の先頭行の認識 ex. "# ID=ホスト名" 正規表現で検出
                if (line.matches("^# ID=.+$")) {
                    // System.out.println("転送グループ情報の先頭行を検出 ID行は [" + line + " ]です。");
                    // ホスト定義の先頭行を認識したので、hashMap をクリアする (重要)
                    hashMap.clear();

                    arrayKey = line.split("=");
                    key = arrayKey[0].replace("# ", "");
                    if (arrayKey.length == 2) {
                        value = arrayKey[1];
                    } else {
                        value = "-"; // valueが省略されている場合 ハイフンで埋め
                    }

                    // ハッシュに追加
                    hashMap.put(key, value);
                }

                // 転送グループ情報の終了行ENDの認識 ex."END" 正規表現で検出
                if (line.matches("^END$")) {
                    // System.out.println("END行を認識しました。");
                    // recodeをにセットしてarrayListに追加する
                    arrayList.add(buildRecord(hashMap, tgrpArrayList));
                }

                // データ行の処理 "key=value" ex."GRP=GROUPNAME" "key=" ex."COMMENT=[省略されている]"
                // if (line.matches("^.+=.+$")) {
                if (line.matches("^.+=*$")) {
                    arrayKey = line.split("=");
                    key = arrayKey[0];
                    if (arrayKey.length == 2) {
                        value = arrayKey[1];
                    } else {
                        value = "-"; // valueが省略されている場合 ハイフンで埋め
                    }

                    // ハッシュに追加
                    hashMap.put(key, value);
                }

                // 転送グループ定義の終了を検出
                if (line.matches("^DEFEND$")) {
                    tgrp_def_switch = false;
                }

                if (tgrp_def_switch == true) {
                    tgrpArrayList.add(line);
                }

                // 転送グループ定義の開始を検出 tgrpArrayList を初期化 (重要)
                if (line.matches("^SERVER DEF$")) {
                    tgrp_def_switch = true;
                    tgrpArrayList = new ArrayList<String>();
                    tgrpArrayList.clear(); // 念のため
                }
            }
        }

        //
        for (var array : arrayList) {

            boolean fullSwitch = true;
            // for (var arrayJob : arrray.DEF()) {
            for (int i = 0; i < array.SERVER().size(); i++) {

                // int topline = 0;
                // Integer bottomline = array.DEF().size() - 1;
                boolean isBorderTop = false;
                boolean isBorderBottom = false;

                // System.out.println(array.SERVER().get(i));
                if (i == 0) {
                    if (i == array.SERVER().size() - 1) {
                        // arrayFlatList.add(buildRecordFlat(array, array.DEF().get(i), true, true));
                        isBorderTop = true;
                        isBorderBottom = true;
                    } else {
                        // arrayFlatList.add(buildRecordFlat(array, array.DEF().get(i), true, false));
                        isBorderTop = true;
                        isBorderBottom = false;
                    }
                } else {
                    if (i == array.SERVER().size() - 1) {
                        // arrayFlatList.add(buildRecordFlat(array, array.DEF().get(i), false, true));
                        isBorderTop = false;
                        isBorderBottom = true;
                    } else {
                        // arrayFlatList.add(buildRecordFlat(array, array.DEF().get(i), false, false));
                        isBorderTop = false;
                        isBorderBottom = false;
                    }
                }
                arrayFlatList
                        .add(buildRecordFlat(array, array.SERVER().get(i), isBorderTop, isBorderBottom, fullSwitch));

                fullSwitch = false;
            }
        }

        // return arrayList;
        return arrayFlatList;
    }

    public RDefHulftTGrpFlat buildRecordFlat(RDefHulftTGrp srcRec, String def, boolean hasBoderTop,
            boolean hasBoderBottom, boolean fullSwitch) {

        if (fullSwitch) {
            var rec = new RDefHulftTGrpFlat(
                    srcRec.ID(),
                    srcRec.type(),
                    srcRec.GRP(),
                    def,
                    srcRec.COMMENT(),
                    hasBoderTop,
                    hasBoderBottom);
            return rec;
        } else {
            var rec = new RDefHulftTGrpFlat(
                    " ",
                    srcRec.type(),
                    " ",
                    def,
                    " ",
                    hasBoderTop,
                    hasBoderBottom);
            return rec;
        }

        // return rec;
    }

    /**
     * メソッド：record DefHulftTGrp を組み立てる
     * 
     * @param hashMap 解析した後に組み立てたHashMap
     * @return record DefHulftTGrp
     */
    public RDefHulftTGrp buildRecord(HashMap<String, String> hashMap, ArrayList<String> tgrpArrayList) {
        var rec = new RDefHulftTGrp(
                tranIfNullUndefineString(hashMap.get("ID")),
                TGRP,
                tranIfNullUndefineString(hashMap.get("GRP")),
                tgrpArrayList,
                tranIfNullUndefineString(hashMap.get("COMMENT")));

        return rec;
    }

    /**
     * 引数の文字列がNullだったら、"undefine"を返す。
     * Nullでなかったら、引数の文字列を返す。
     * 
     * @param value
     * @return String "undfine" or value
     */
    public String tranIfNullUndefineString(String value) {
        if (value == null) {
            return "undefine";
        } else {
            return value;
        }
    }
}
