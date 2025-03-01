package org.westclan.kntools.hulft.model;

import static org.westclan.kntools.hulft.model.EHulftManageType.HST;

/**
 * record:Hulft の詳細ホスト情報 レコード
 */
public record RDefHulftHst(

        String ID, // 識別させる情報ID プログラム内部管理用 {Hst}
        EHulftManageType type, // 識別させる情報タイプ

        // HULFTのキーワードなので、大文字で管理することにした
        String HOST, // =ホスト名
        String HOSTTYPE, // ホスト種 ={HOST|LINUX|WIN|IBMI}
        String KCODETYPE, // 転送コードセット ={SHIFT-JIS|JEF|EUC-JP|IBM kanji|UTF-8|GB18030|IBM
        // Simplified-Chinese}
        String JISYEAR, // 日本語規格 ={78|83} または {0|1}
        String CONNECTTYPE, // 接続形態 =LAN
        String RCVPORT, // 集信ポートNo.
        String REQPORT, // 要求受付ポートNo.
        String HOSTSPSNUM, // ホスト別配信多重度
        String COMMENT, // コメント
        String MYPROXYNAME, // PROXYサーバ名
        String MYPROXYPORT, // PROXYポートNo.
        String SENDPERMIT, // 送信要求または再送要求受付許可 ={Y|N}
        String HULJOBPERMIT, // 集信後ジョブ結果参照要求受付許可 ={Y|N}
        String HULSNDRCPERMIT, // ジョブ実行結果通知受付許可 ={Y|N}
        String HULRJOBPERMIT, // リモートジョブ実行受付許可 ={Y|N}
        String ALLOWINSTTRANS, // 簡易転送受付許可 ={Y|N}
        String USRNOTIFY, // ユーザの通知 ={Y|N}
        String HUL7MODE // HULFT7 通信モード ={Y|N}

) {

    // record の正規コンストラクタ:Canonical constructor
    public RDefHulftHst {
        type = HST;
    }

    // 標準的なコンストラクタ ()あり
    // public HulftHstDef() {
    // this();
    // }

    public void printLinkInfo() {
        String link = """
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE/HULFTOpeCmd/MngmntInfoBatchReg.htm?tocpath=5.%20HULFT%E3%81%AE%E6%93%8D%E4%BD%9C%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89%7C5.6%20%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E7%AE%A1%E7%90%86%7C_____1#%E9%85%8D%E4%BF%A1%E7%AE%A1%E7%90%86%E6%83%85%E5%A0%B1%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%83%BC%E3%81%AE%E5%BD%A2%E5%BC%8F
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE_LINUX/HULFTSysMngmntInfo/HostInfo.htm
                """;
        System.out.println(link);
    }
}