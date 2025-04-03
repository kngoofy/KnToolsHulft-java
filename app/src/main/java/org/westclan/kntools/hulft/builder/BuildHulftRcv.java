package org.westclan.kntools.hulft.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.westclan.kntools.hulft.model.RDefHulftRcv;
import static org.westclan.kntools.hulft.model.EHulftManageType.RCV;

/**
 * クラス：Hulftの集信管理情報 組み立て
 */
public class BuildHulftRcv {

    /**
     * メソッド：Hulftの集信管理情報定義(String)を読み込んで、arrayListに組み立てる
     * 要素は record DefHulftRcv
     * 
     * @param stringDefine 集信管理情報定義(String)
     * @return 集信管理情報定義 レコードのリスト
     * @throws IOException
     */
    public ArrayList<RDefHulftRcv> buildRcvArray(String stringDefine) throws IOException {

        String stringData = stringDefine;

        // ワーク用変数
        String line = "";
        String key = "";
        String value = "";
        String arrayKey[] = {};
        String arrayKey2[] = {};

        // 集信管理情報定義のキーワードを HashMap に格納
        HashMap<String, String> hashMap = new HashMap<>();
        // 集信管理情報定義をレコード化した物を ArrayList に積み上げ
        ArrayList<RDefHulftRcv> arrayList = new ArrayList<RDefHulftRcv>();

        // メイン処理 ループ
        try (var reader = new BufferedReader(new StringReader(stringData))) {

            while ((line = reader.readLine()) != null) {

                line = line.trim();
                if (line == "")
                    continue;
                if (line.matches("^#$"))
                    continue;

                // 情報の先頭行の認識 ex. "# ID=集信ID" 正規表現で検出
                if (line.matches("^# ID=.+$")) {
                    // System.out.println("集信管理定義の先頭行を検出 ID行は [" + line + " ]です。");
                    // 集信管理定義の先頭行を認識したので、hashMap をクリアする (重要)
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

                // 集信管理情報の終了行ENDの認識 ex."END" 正規表現で検出
                if (line.matches("^END$")) {
                    // System.out.println("END行を認識しました。");
                    // recodeをにセットしてarrayListに追加する
                    arrayList.add(buildRecord(hashMap));
                }

                // データ行の処理 "key=value" ex."HOST=SERVERNAME" "key=" ex."COMMENT=[省略されている]"
                // if (line.matches("^.+=.+$")) {
                if (line.matches("^.+=*$")) {
                    arrayKey = line.split("=");
                    key = arrayKey[0];
                    if (arrayKey.length == 2) {
                        value = arrayKey[1];
                    } else if (arrayKey.length == 1) {
                        value = "-"; // valueが省略されている場合 ハイフンで埋め
                    } else {
                        arrayKey2 = line.split("=", 2);
                        value = arrayKey2[1];
                    }
                    // System.out.println(key + " = " + value);

                    // ハッシュに追加
                    hashMap.put(key, value);
                }
            }

        }

        return arrayList;
    }

    /**
     * メソッド：record DefHulftRcv を組み立てる
     * 
     * @param hashMap 解析した後に組み立てたHashMap
     * @return record DefHulftRcv
     */
    public RDefHulftRcv buildRecord(HashMap<String, String> hashMap) {
        // System.out.println(hashMap);
        var rec = new RDefHulftRcv(
                tranIfNullUndefineString(hashMap.get("ID")),
                RCV,
                tranIfNullUndefineString(hashMap.get("RCVFILE")),
                tranIfNullUndefineString(hashMap.get("FILENAME")),
                tranIfNullUndefineString(hashMap.get("OWNER")),
                tranIfNullUndefineString(hashMap.get("GROUP")),
                tranIfNullUndefineString(hashMap.get("PERM")),
                tranIfNullUndefineString(hashMap.get("CODESET")),
                tranIfNullUndefineString(hashMap.get("TRANSMODE")),
                tranIfNullUndefineString(hashMap.get("ABNORMAL")),
                tranIfNullUndefineString(hashMap.get("RCVTYPE")),
                tranIfNullUndefineString(hashMap.get("GENCTL")),
                tranIfNullUndefineString(hashMap.get("GENMNGNO")),
                tranIfNullUndefineString(hashMap.get("JOBID")),
                tranIfNullUndefineString(hashMap.get("EJOBID")),
                tranIfNullUndefineString(hashMap.get("GRPID")),
                tranIfNullUndefineString(hashMap.get("PASSWORD")),
                tranIfNullUndefineString(hashMap.get("JOBWAIT")),
                tranIfNullUndefineString(hashMap.get("DATAVERIFY")),
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
