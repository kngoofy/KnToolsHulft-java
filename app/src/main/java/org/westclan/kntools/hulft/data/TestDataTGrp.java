package org.westclan.kntools.hulft.data;

/**
 * テスト・確認用データ 転送グループ情報定義
 */
public class TestDataTGrp {

    public static final String Data = """
            #
            # ID=HEADER
            #

            GRP=GRP
            SERVER DEF
             SERVER
             SERVER2
            DEFEND
            COMMENT=COMMENT
            END

            #
            # ID=SRV-GRP01
            #

            GRP=SRV-GRP01
            SERVER DEF
             GRP1-VMSVR-TEST01
             GRP1-VMSVR-TEST02
            DEFEND
            COMMENT=転送グループその１
            END

            #
            # ID=SRV-GRP02
            #

            GRP=SRV-GRP02
            SERVER DEF
             GRP2-VMSVR-TEST03
             GRP2-VMSVR-TEST04
            DEFEND
            COMMENT=転送グループその２
            END

            #
            # ID=SRV-GRP03
            #

            GRP=SRV-GRP03
            SERVER DEF

            DEFEND
            COMMENT=
            END
            """;

    public void printLinkInfo() {
        String link = """
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE/HULFTOpeCmd/MngmntInfoBatchReg.htm?tocpath=5.%20HULFT%E3%81%AE%E6%93%8D%E4%BD%9C%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89%7C5.6%20%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E7%AE%A1%E7%90%86%7C_____1#%E8%BB%A2%E9%80%81%E3%82%B0%E3%83%AB%E3%83%BC%E3%83%97%E6%83%85%E5%A0%B1%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%83%BC%E3%81%AE%E5%BD%A2%E5%BC%8F
                """;
        System.out.println(link);
    }
}
