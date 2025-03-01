package org.westclan.kntools.hulft.model;

import java.util.ArrayList;
// import org.westclan.kntools.hulft.model.EHulftManageType;

/**
 * record:Hulft のジョブ起動情報 レコード
 */
public record RDefHulftJob(

        String ID, // 識別させる情報ID プログラム内部管理用 {Job}
        EHulftManageType type, // 識別させる情報タイプ

        // HULFTのキーワードなので、大文字で管理することにした
        String JOB, // ジョブID
        ArrayList<String> DEF, // ジョブ名 １３個まで指定可能
        String COMMENT // コメント

) {

    // record の正規コンストラクタ:Canonical constructor
    public RDefHulftJob {
        type = EHulftManageType.JOB; // 上に String JOBが存在して重複 static importできず
    }

    // 標準的なコンストラクタ ()あり
    // public HulftJobDef() {
    // this();
    // }

    public void printLinkInfo() {
        String link = """
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE/HULFTOpeCmd/MngmntInfoBatchReg.htm?tocpath=5.%20HULFT%E3%81%AE%E6%93%8D%E4%BD%9C%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89%7C5.6%20%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E7%AE%A1%E7%90%86%7C_____1#%E9%85%8D%E4%BF%A1%E7%AE%A1%E7%90%86%E6%83%85%E5%A0%B1%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%83%BC%E3%81%AE%E5%BD%A2%E5%BC%8F
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE_LINUX/HULFTSysMngmntInfo/JobInfo.htm
                """;
        System.out.println(link);
    }
}
