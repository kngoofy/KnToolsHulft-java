package org.westclan.kntools.hulft.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.westclan.kntools.hulft.model.RDefHulftSnd;
import static org.westclan.kntools.hulft.model.EHulftManageType.SND;

/**
 * クラス：Hulftの配信管理情報 組み立て
 */
public class BuildHulftSnd {

    /**
     * メソッド：Hulftの配信管理情報定義(String)を読み込んで、arrayListに組み立てる
     * 要素は record DefHulftSnd
     * 
     * @param stringDefine 配信管理情報定義(String)
     * @return 配信管理情報定義 レコードのリスト
     * @throws IOException
     */
    public ArrayList<RDefHulftSnd> buildSndArray(String stringDefine) throws IOException {

        String stringData = stringDefine;

        // ワーク用変数
        String line = "";
        String key = "";
        String value = "";
        String arrayKey[] = {};
        HashMap<String, String> hashMap = new HashMap<>();
        ArrayList<RDefHulftSnd> arrayList = new ArrayList<RDefHulftSnd>();

        // メイン処理 ループ
        try (var reader = new BufferedReader(new StringReader(stringData))) {

            while ((line = reader.readLine()) != null) {

                line = line.trim();
                if (line == "")
                    continue;
                if (line.matches("^#$"))
                    continue;

                // 情報の先頭行の認識 ex. "# ID=配信ID" 正規表現で検出
                if (line.matches("^# ID=.+$")) {
                    // System.out.println("配信管理定義の先頭行を検出 ID行は [" + line + " ]です。");
                    // 配信管理定義の先頭行を認識したので、hashMap をクリアする (重要)
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

                // 配信管理情報の終了行ENDの認識 ex."END" 正規表現で検出
                if (line.matches("^END$")) {
                    // System.out.println("END行を認識しました。");
                    // recodeをにセットしてarrayListに追加する
                    arrayList.add(buildRecord(hashMap));
                }

                // System.out.println(line);

                // データ行の処理 "key=value" ex."HOST=SERVERNAME" "key=" ex."COMMENT=[省略されている]"
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
            }

        }

        return arrayList;
    }

    /**
     * メソッド：record DefHulftSnd を組み立てる
     * 
     * @param hashMap 解析した後に組み立てたHashMap
     * @return record DefHulftSnd
     */
    public RDefHulftSnd buildRecord(HashMap<String, String> hashMap) {
        var rec = new RDefHulftSnd(
                tranIfNullUndefineString(hashMap.get("ID")),
                SND,
                tranIfNullUndefineString(hashMap.get("SNDFILE")),
                tranIfNullUndefineString(hashMap.get("FILENAME")),
                tranIfNullUndefineString(hashMap.get("INTERVAL")),
                tranIfNullUndefineString(hashMap.get("BLOCKLEN")),
                tranIfNullUndefineString(hashMap.get("BLOCKCNT")),
                tranIfNullUndefineString(hashMap.get("COMP")),
                tranIfNullUndefineString(hashMap.get("COMPSIZE")),
                tranIfNullUndefineString(hashMap.get("COMPMODE")),
                tranIfNullUndefineString(hashMap.get("TRANSPRTY")),
                tranIfNullUndefineString(hashMap.get("TRANSTYPE")),
                tranIfNullUndefineString(hashMap.get("CODESET")),
                tranIfNullUndefineString(hashMap.get("KJCHNGE")),
                tranIfNullUndefineString(hashMap.get("SHIFTTRANSACT")),
                tranIfNullUndefineString(hashMap.get("CLEAR")),
                tranIfNullUndefineString(hashMap.get("PREJOBID")),
                tranIfNullUndefineString(hashMap.get("JOBID")),
                tranIfNullUndefineString(hashMap.get("EJOBID")),
                tranIfNullUndefineString(hashMap.get("DBID")),
                tranIfNullUndefineString(hashMap.get("GRPID")),
                tranIfNullUndefineString(hashMap.get("FMTID")),
                tranIfNullUndefineString(hashMap.get("PASSWORD")),
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
