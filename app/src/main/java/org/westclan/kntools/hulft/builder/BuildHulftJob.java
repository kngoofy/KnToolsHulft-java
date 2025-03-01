package org.westclan.kntools.hulft.builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.westclan.kntools.hulft.model.RDefHulftJob;
import org.westclan.kntools.hulft.model.RDefHulftJobFlat;

import static org.westclan.kntools.hulft.model.EHulftManageType.JOB;;

/**
 * クラス：Hulftのジョブ起動情報定義 組み立て
 */
public class BuildHulftJob {

    /**
     * メソッド：Hulftのジョブ起動情報定義(String)を読み込んで、arrayListに組み立てる
     * 要素は record DefHulftJob
     * 
     * @param stringDefine ジョブ起動情報定義(String)
     * @return ジョブ起動情報定義 (フラット) レコードのリスト
     * @throws IOException
     */
    public ArrayList<RDefHulftJobFlat> buildJobArray(String stringDefine) throws IOException {

        String stringData = stringDefine;

        // ワーク用変数
        String line = "";
        String key = "";
        String value = "";
        String arrayKey[] = {};

        // ジョブ起動情報定義のキーワードを HashMap に格納
        HashMap<String, String> hashMap = new HashMap<>();
        // ジョブ起動情報定義をレコード化したものを AllyList に積み上げ
        ArrayList<RDefHulftJob> arrayList = new ArrayList<RDefHulftJob>();

        ArrayList<RDefHulftJobFlat> arrayFlatList = new ArrayList<RDefHulftJobFlat>();

        // ジョブ定義の判定スイッチ
        boolean job_def_switch = false;
        // ジョブ定義 ArrayList
        ArrayList<String> jobArrayList = new ArrayList<String>();

        // メイン処理 ループ
        try (var reader = new BufferedReader(new StringReader(stringData))) {

            while ((line = reader.readLine()) != null) {

                line = line.trim();
                if (line == "")
                    continue;
                if (line.matches("^#$"))
                    continue;

                // ジョブ起動情報の先頭行の認識 ex. "# ID=ホスト名" 正規表現で検出
                if (line.matches("^# ID=.+$")) {
                    // System.out.println("ジョブ起動情報の先頭行を検出 ID行は [" + line + " ]です。");
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

                // ジョブ起動情報の終了行ENDの認識 ex."END" 正規表現で検出
                if (line.matches("^END$")) {
                    // System.out.println("END行を認識しました。");
                    // recodeをにセットしてarrayListに追加する
                    arrayList.add(buildRecord(hashMap, jobArrayList));
                }

                // データ行の処理 "key=value" ex."JOB=JOBNAME" "key=" ex."COMMENT=[省略されている]"
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

                // ジョブ定義の終了を検出
                if (line.matches("^DEFEND$")) {
                    job_def_switch = false;
                    // System.out.println(jobArrayList);
                }

                if (job_def_switch == true) {
                    // System.out.println("job command line " + line);
                    jobArrayList.add(line);
                }

                // ジョブ定義の開始を検出 jobArrayList を初期化 (重要)
                if (line.matches("^JOB DEF$")) {
                    job_def_switch = true;
                    jobArrayList = new ArrayList<String>();
                    jobArrayList.clear(); // 念のため
                }
            }
        }

        //
        for (var array : arrayList) {

            // System.out.println(array.JOB());
            // System.out.println(array.DEF());
            boolean fullSwitch = true;
            // for (var arrayJob : arrray.DEF()) {
            for (int i = 0; i < array.DEF().size(); i++) {

                // int topline = 0;
                // Integer bottomline = array.DEF().size() - 1;
                boolean isBorderTop = false;
                boolean isBorderBottom = false;

                // System.out.println(array.DEF().get(i));
                if (i == 0) {
                    if (i == array.DEF().size() - 1) {
                        // arrayFlatList.add(buildRecordFlat(array, array.DEF().get(i), true, true));
                        isBorderTop = true;
                        isBorderBottom = true;
                    } else {
                        // arrayFlatList.add(buildRecordFlat(array, array.DEF().get(i), true, false));
                        isBorderTop = true;
                        isBorderBottom = false;
                    }
                } else {
                    if (i == array.DEF().size() - 1) {
                        // arrayFlatList.add(buildRecordFlat(array, array.DEF().get(i), false, true));
                        isBorderTop = false;
                        isBorderBottom = true;
                    } else {
                        // arrayFlatList.add(buildRecordFlat(array, array.DEF().get(i), false, false));
                        isBorderTop = false;
                        isBorderBottom = false;
                    }
                }
                arrayFlatList.add(buildRecordFlat(array, array.DEF().get(i), isBorderTop, isBorderBottom, fullSwitch));

                fullSwitch = false;
            }
        }

        // return arrayList;
        return arrayFlatList;

    }

    public RDefHulftJobFlat buildRecordFlat(RDefHulftJob srcRec, String def, boolean hasBoderTop,
            boolean hasBoderBottom, boolean fullSwitch) {

        if (fullSwitch) {
            var rec = new RDefHulftJobFlat(
                    srcRec.ID(),
                    srcRec.type(),
                    srcRec.JOB(),
                    def,
                    srcRec.COMMENT(),
                    hasBoderTop,
                    hasBoderBottom);
            return rec;
        } else {
            var rec = new RDefHulftJobFlat(
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
     * メソッド：record DefHulftJob を組み立てる
     * 
     * @param hashMap      解析した後に組み立てたHashMap
     * @param jobArrayList ジョブID StringのArrayList
     * @return record DefHulftJob
     */
    public RDefHulftJob buildRecord(HashMap<String, String> hashMap, ArrayList<String> jobArrayList) {
        var rec = new RDefHulftJob(
                tranIfNullUndefineString(hashMap.get("ID")),
                JOB,
                tranIfNullUndefineString(hashMap.get("JOB")),
                jobArrayList,
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
