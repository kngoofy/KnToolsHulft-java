package org.westclan.kntools.hulft.model;

import static org.westclan.kntools.hulft.model.EHulftManageType.RCV;

/**
 * record:Hulft の集信管理情報 レコード
 */
public record RDefHulftRcv(

        String ID, // 識別させる情報ID プログラム内部管理用 {Rcv}
        EHulftManageType type, // 識別させる情報タイプ

        // HULFTのキーワードなので、大文字で管理することにした
        String RCVFILE, // ファイルID
        String FILENAME, // 集信ファイル名
        String OWNER, // オーナー名]
        String GROUP, // グループ名]
        String PERM, // パーミッション
        String CODESET, // EBCDICセット {0|A|B|C|D|E|F|G|H|I|V|W|X}]
        String TRANSMODE, // 登録モード {NEW|REP|MOD}
        String ABNORMAL, // 異常時の処置 {DELETE|KEEP|RESTORE}
        String RCVTYPE, // 集信形態 {S|M}
        String GENCTL, // 世代管理{YES|NO}
        String GENMNGNO, // 世代管理数
        String JOBID, // 正常時ジョブID
        String EJOBID, // 異常時ジョブID
        String GRPID, // 転送グループID
        String PASSWORD, // 暗号キー
        String JOBWAIT, // {J|T}
        String DATAVERIFY, // {0|1}
        String COMMENT // コメント

) {
    // record の正規コンストラクタ:Canonical constructor
    public RDefHulftRcv {
        type = RCV;
    }

    // 標準的なコンストラクタ ()あり
    // public HulftRcvDef() {
    // this();
    // }

    public void printLinkInfo() {
        String link = """
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE/HULFTOpeCmd/MngmntInfoBatchReg.htm?tocpath=5.%20HULFT%E3%81%AE%E6%93%8D%E4%BD%9C%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89%7C5.6%20%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E7%AE%A1%E7%90%86%7C_____1#%E9%85%8D%E4%BF%A1%E7%AE%A1%E7%90%86%E6%83%85%E5%A0%B1%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%83%BC%E3%81%AE%E5%BD%A2%E5%BC%8F
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE_LINUX/HULFTSysMngmntInfo/RcvMngmntInfo.htm
                """;
        System.out.println(link);
    }

}
