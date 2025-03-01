package org.westclan.kntools.hulft.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.westclan.kntools.hulft.model.RDefHulftHst;
import static org.westclan.kntools.hulft.model.EHulftManageType.HST;

/**
 * クラス：Hulftの詳細ホスト情報定義 組み立て
 */
public class BuildHulftHst {

    /**
     * メソッド：Hulftの詳細ホスト情報定義(String)を読み込んで、arrayListに組み立てる
     * 要素は record DefHulftHst
     * 
     * @param stringDefine 詳細ホスト情報定義(String)
     * @return 詳細ホスト情報定義 レコードのリスト
     * @throws IOException
     */
    public ArrayList<RDefHulftHst> buildHstArray(String stringDefine) throws IOException {

        String stringData = stringDefine;

        // ワーク用変数
        String line = "";
        String key = "";
        String value = "";
        String arrayKey[] = {};

        // 詳細ホスト情報定義のキーワードを HashMap に格納
        HashMap<String, String> hashMap = new HashMap<>();
        // 詳細ホスト情報定義をレコード化した物を ArrayList に積み上げ
        ArrayList<RDefHulftHst> arrayList = new ArrayList<RDefHulftHst>();

        // メイン処理 ループ
        try (var reader = new BufferedReader(new StringReader(stringData))) {

            while ((line = reader.readLine()) != null) {

                line = line.trim();
                if (line == "")
                    continue;
                if (line.matches("^#$"))
                    continue;

                // 詳細ホスト情報の先頭行の認識 ex. "# ID=ホスト名" 正規表現で検出
                if (line.matches("^# ID=.+$")) {
                    // System.out.println("詳細ホスト定義の先頭行を検出 ID行は [" + line + " ]です。");
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

                // 詳細ホスト情報の終了行ENDの認識 ex."END" 正規表現で検出
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
     * メソッド：record DefHulftHst を組み立てる
     * 
     * @param hashMap 解析した後に組み立てたHashMap
     * @return record DefHulftHst
     */
    public RDefHulftHst buildRecord(HashMap<String, String> hashMap) {
        var rec = new RDefHulftHst(
                tranIfNullUndefineString(hashMap.get("ID")),
                HST,
                tranIfNullUndefineString(hashMap.get("HOST")),
                tranIfNullUndefineString(hashMap.get("HOSTTYPE")),
                tranIfNullUndefineString(hashMap.get("KCODETYPE")),
                tranIfNullUndefineString(hashMap.get("JISYEAR")),
                tranIfNullUndefineString(hashMap.get("CONNECTTYPE")),
                tranIfNullUndefineString(hashMap.get("RCVPORT")),
                tranIfNullUndefineString(hashMap.get("REQPORT")),
                tranIfNullUndefineString(hashMap.get("HOSTSPSNUM")),
                tranIfNullUndefineString(hashMap.get("COMMENT")),
                tranIfNullUndefineString(hashMap.get("MYPROXYNAME")),
                tranIfNullUndefineString(hashMap.get("MYPROXYPORT")),
                tranIfNullUndefineString(hashMap.get("SENDPERMIT")),
                tranIfNullUndefineString(hashMap.get("HULJOBPERMIT")),
                tranIfNullUndefineString(hashMap.get("HULSNDRCPERMIT")),
                tranIfNullUndefineString(hashMap.get("HULRJOBPERMIT")),
                tranIfNullUndefineString(hashMap.get("ALLOWINSTTRANS")),
                tranIfNullUndefineString(hashMap.get("USRNOTIFY")),
                tranIfNullUndefineString(hashMap.get("HUL7MODE")));

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
