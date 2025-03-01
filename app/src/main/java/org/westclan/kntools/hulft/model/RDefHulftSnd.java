package org.westclan.kntools.hulft.model;

import static org.westclan.kntools.hulft.model.EHulftManageType.SND;

/**
 * record:Hulft の配信管理情報 レコード
 */
public record RDefHulftSnd(

        String ID, // 識別させる情報ID プログラム内部管理用 {Snd}
        EHulftManageType type, // 識別させる情報タイプ

        // HULFTのキーワードなので、大文字で管理することにした
        String SNDFILE, // ファイルID
        String FILENAME, // 配信ファイル名
        String INTERVAL, // 転送間隔
        String BLOCKLEN, // 転送ブロック長
        String BLOCKCNT, // 転送ブロック数
        String COMP, // 圧縮方式 {NO|1|2|3|4}
        String COMPSIZE, // 圧縮単位
        String COMPMODE, // 圧縮モード {S|D|C}]
        String TRANSPRTY, // 転送優先度
        String TRANSTYPE, // 転送タイプ {FORMAT|BINARY|TEXT|MFORMAT}
        String CODESET, // EBCDICセット {0|A|B|C|D|E|F|G|H|I|V|W|X}]
        String KJCHNGE, // コード変換 {S|R|N}
        String SHIFTTRANSACT, // シフトコードの扱い {Y|N}]
        String CLEAR, // 配信ファイルの扱い {K|C|D|L}]
        String PREJOBID, // 配信前ジョブID
        String JOBID, // 正常時ジョブID
        String EJOBID, // 異常時ジョブID
        String DBID, // 連携DBID
        String GRPID, // 転送グループID
        String FMTID, // フォーマットID|マルチフォーマットID
        String PASSWORD, // 暗号キー
        String COMMENT // コメント

) {
    // record の正規コンストラクタ:Canonical constructor
    public RDefHulftSnd {
        type = SND;
    }

    // 標準的なコンストラクタ ()あり
    // public HulftSndDef() {
    // this();
    // }

    public void printLinkInfo() {
        String link = """
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE/HULFTOpeCmd/MngmntInfoBatchReg.htm?tocpath=5.%20HULFT%E3%81%AE%E6%93%8D%E4%BD%9C%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89%7C5.6%20%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E7%AE%A1%E7%90%86%7C_____1#%E9%85%8D%E4%BF%A1%E7%AE%A1%E7%90%86%E6%83%85%E5%A0%B1%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%83%BC%E3%81%AE%E5%BD%A2%E5%BC%8F
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE_LINUX/HULFTSysMngmntInfo/SndMngmntInfo.htm
                """;
        System.out.println(link);
    }
}
