package org.westclan.kntools.hulft.data;

/**
 * テスト・確認用データ 詳細ホスト情報定義
 */
public class TestDataHst {

    public static final String Data = """
                #
                # ID=HEADER
                #

                HOST=HOST
                HOSTTYPE=HOSTTYPE
                KCODETYPE=KCODETYPE
                RCVPORT=RCVPORT
                REQPORT=REQPORT
                COMMENT=COMMENT
                JISYEAR=JISYEAR
                CONNECTTYPE=CONNECTTYPE
                MYPROXYNAME=MYPROXYNAME
                MYPROXYPORT=MYPROXYPORT
                HOSTSPSNUM=HOSTSPSNUM
                SENDPERMIT=SENDPERMIT
                HULJOBPERMIT=HULJOBPERMIT
                HULSNDRCPERMIT=HULSNDRCPERMIT
                HULRJOBPERMIT=HULRJOBPERMIT
                ALLOWINSTTRANS=ALLOWINSTTRANS
                USRNOTIFY=USRNOTIFY
                HUL7MODE=HUL7MODE
                END
                #
                # ID=SRV-TEST01
                #

                HOST=SRV-TEST01
                HOSTTYPE=U
                KCODETYPE=8
                RCVPORT=30000
                REQPORT=31000
                COMMENT=
                JISYEAR=1
                CONNECTTYPE=L
                MYPROXYNAME=
                MYPROXYPORT=
                HOSTSPSNUM=0
                SENDPERMIT=Y
                HULJOBPERMIT=Y
                HULSNDRCPERMIT=Y
                HULRJOBPERMIT=Y
                ALLOWINSTTRANS=N
                USRNOTIFY=N
                HUL7MODE=N
                END

                #
                # ID=SRV-TEST02
                #

                HOST=SRV-TEST02
                HOSTTYPE=U
                KCODETYPE=8
                RCVPORT=30000
                REQPORT=31000
                COMMENT=
                JISYEAR=1
                CONNECTTYPE=L
                MYPROXYNAME=
                MYPROXYPORT=8603
                HOSTSPSNUM=0
                SENDPERMIT=Y
                HULJOBPERMIT=Y
                HULSNDRCPERMIT=Y
                HULRJOBPERMIT=Y
                ALLOWINSTTRANS=N
                USRNOTIFY=N
                HUL7MODE=N
                END
            """;

    public void printLinkInfo() {
        String link = """
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE/HULFTOpeCmd/MngmntInfoBatchReg.htm?tocpath=5.%20HULFT%E3%81%AE%E6%93%8D%E4%BD%9C%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89%7C5.6%20%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E7%AE%A1%E7%90%86%7C_____1#%E8%A9%B3%E7%B4%B0%E3%83%9B%E3%82%B9%E3%83%88%E6%83%85%E5%A0%B1%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%83%BC%E3%81%AE%E5%BD%A2%E5%BC%8F
                """;
        System.out.println(link);
    }
}
