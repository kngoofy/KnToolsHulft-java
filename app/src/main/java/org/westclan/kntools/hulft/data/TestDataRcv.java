package org.westclan.kntools.hulft.data;

/**
 * テスト・確認用データ 集信管理情報定義
 */
public class TestDataRcv {

    public static final String Data = """
            #
            # ID=HEADER
            #

            RCVFILE=RCVFILE
            FILENAME=FILENAME
            OWNER=OWNER
            GROUP=GROUP
            PERM=PERM
            TRANSMODE=TRANSMODE
            ABNORMAL=ABNORMAL
            RCVTYPE=RCVTYPE
            JOBID=JOBID
            COMMENT=COMMENT
            GRPID=GRPID
            EJOBID=EJOBID
            GENCTL=GENCTL
            PASSWORD=PASSWORD
            CODESET=CODESET
            JOBWAIT=JOBWAIT
            GENMNGNO=GENMNGNO
            DATAVERIFY=DATAVERIFY
            END

            #
            # ID=RCV_TEST01
            #

            RCVFILE=RCV_TEST01
            FILENAME=/tmp/test.txt
            OWNER=test
            GROUP=test
            PERM=666
            TRANSMODE=R
            ABNORMAL=D
            RCVTYPE=S
            JOBID=
            COMMENT=JOHO=xxxxxx
            GRPID=
            EJOBID=
            GENCTL=N
            PASSWORD=TESTPASS
            CODESET=0
            JOBWAIT=T
            GENMNGNO=0000
            DATAVERIFY=0
            END

            #
            # ID=RCV_TEST02
            #

            RCVFILE=RCV_TEST02
            FILENAME=/tmp/test.txt
            OWNER=test
            GROUP=test
            PERM=666
            TRANSMODE=R
            ABNORMAL=D
            RCVTYPE=S
            JOBID=
            COMMENT=
            GRPID=
            EJOBID=
            GENCTL=N
            PASSWORD=TESTPASS
            CODESET=0
            JOBWAIT=T
            GENMNGNO=0000
            DATAVERIFY=0
            END
            """;

    public void printLinkInfo() {
        String link = """
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE/HULFTOpeCmd/MngmntInfoBatchReg.htm?tocpath=5.%20HULFT%E3%81%AE%E6%93%8D%E4%BD%9C%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89%7C5.6%20%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E7%AE%A1%E7%90%86%7C_____1#%E9%9B%86%E4%BF%A1%E7%AE%A1%E7%90%86%E6%83%85%E5%A0%B1%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%83%BC%E3%81%AE%E5%BD%A2%E5%BC%8F
                """;
        System.out.println(link);
    }

}
