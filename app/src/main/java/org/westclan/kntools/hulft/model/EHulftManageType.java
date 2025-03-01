package org.westclan.kntools.hulft.model;

/**
 * enum
 * 
 * 管理情報パラメータファイル生成コマンド
 * utligen -f filename -i {snd|rcv|job|hst|tgrp|fmt|mfmt|trg} -id ID
 * 
 * パラメータ説明
 * -i {snd|rcv|job|hst|tgrp|fmt|mfmt|sch|trg|mail}
 * パラメータファイルを生成する管理情報を選択（省略不可）
 * 
 * snd: 配信管理情報
 * rcv: 集信管理情報
 * job: ジョブ起動情報
 * hst: 詳細ホスト情報
 * tgrp: 転送グループ情報
 * fmt: フォーマット情報
 * mfmt: マルチフォーマット情報
 * trg: ファイルトリガ情報
 */
public enum EHulftManageType {
    SND, // 配信管理情報
    RCV, // 集信管理情報
    JOB, // ジョブ起動情報
    HST, // 詳細ホスト情報
    TGRP, // 転送グループ情報
    FMT, // フォーマット情報
    MFMT, // マルチフォーマット情報
    TRG; // ファイルトリガ情報

    public void printLinkInfo() {
        String link = """
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE/HULFTOpeCmd/MngmntInfoParaFGenCmd.htm?tocpath=5.%20HULFT%E3%81%AE%E6%93%8D%E4%BD%9C%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89%7C5.6%20%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E7%AE%A1%E7%90%86%7C_____4
                """;
        System.out.println(link);
    }

}
