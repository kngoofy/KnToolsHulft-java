package org.westclan.kntools.hulft.data;

/**
 * テスト・確認用データ マルチフォーマット情報定義
 */
public class TestDataMFmt {

    public static final String Data = """
            #
            MFMT=mformat1
            KEYSTART=1
            KEYLEN=4
            DFMTID=default
            COMMENT=人事情報ファイル
            MFORMAT DEF
             0001   format01
             0002   format02
             0003   format03
             0004   format04
             0005   format05
             0006   format06
             0007   format07
            DEFEND
            END
            """;

    public void printLinkInfo() {
        String link = """
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE/HULFTOpeCmd/MngmntInfoBatchReg.htm?tocpath=5.%20HULFT%E3%81%AE%E6%93%8D%E4%BD%9C%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89%7C5.6%20%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E7%AE%A1%E7%90%86%7C_____1#%E3%83%95%E3%82%A9%E3%83%BC%E3%83%9E%E3%83%83%E3%83%88%E6%83%85%E5%A0%B1%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%83%BC%E3%81%AE%E5%BD%A2%E5%BC%8F
                """;
        System.out.println(link);
    }

}
