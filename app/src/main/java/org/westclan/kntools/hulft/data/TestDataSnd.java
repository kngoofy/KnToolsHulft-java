package org.westclan.kntools.hulft.data;

/**
 * テスト・確認用データ 配信管理情報定義
 */
public class TestDataSnd {

    public static final String Data = """
            #
            # ID=HEADER
            #

            SNDFILE=SNDFILE
            FILENAME=FILENAME
            DBID=DBID
            TRANSTYPE=TRANSTYPE
            TRANSPRTY=TRANSPRTY
            INTERVAL=INTERVAL
            BLOCKLEN=BLOCKLEN
            BLOCKCNT=BLOCKCNT
            COMP=COMP
            JOBID=JOBID
            COMMENT=COMMENT
            GRPID=GRPID
            FMTID=FMTID
            EJOBID=EJOBID
            KJCHNGE=KJCHNGE
            CLEAR=CLEAR
            PASSWORD=PASSWORD
            CODESET=CODESET
            COMPSIZE=COMPSIZE
            SHIFTTRANSACT=SHIFTTRANSACT
            PREJOBID=PREJOBID
            DEFLATELEVEL=DEFLATELEVEL
            END

            #
            # ID=SND_TEST01
            #

            SNDFILE=SND_TEST01
            FILENAME=/tmp/test.txt
            DBID=
            TRANSTYPE=B
            TRANSPRTY=50
            INTERVAL=0
            BLOCKLEN=0
            BLOCKCNT=0
            COMP=N
            JOBID=JOB01
            COMMENT=JOHO=XXXXXX
            GRPID=VM-TEST02
            FMTID=
            EJOBID=JOB01
            KJCHNGE=N
            CLEAR=K
            PASSWORD=TESTPASS
            CODESET=0
            COMPSIZE=0
            SHIFTTRANSACT=Y
            PREJOBID=
            DEFLATELEVEL=
            END

            #
            # ID=SND_TEST02
            #
            SNDFILE=SND_TEST02
            FILENAME=/tmp/test.txt
            DBID=
            TRANSTYPE=B
            TRANSPRTY=50
            INTERVAL=0
            BLOCKLEN=0
            BLOCKCNT=0
            COMP=N
            JOBID=JOB01
            COMMENT=
            GRPID=VM-TEST02
            FMTID=
            EJOBID=JOB01
            KJCHNGE=N
            CLEAR=K
            PASSWORD=TESTPASS
            CODESET=0
            COMPSIZE=0
            SHIFTTRANSACT=Y
            PREJOBID=
            DEFLATELEVEL=
            END
            """;

    public void printLinkInfo() {
        String link = """
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE/HULFTOpeCmd/MngmntInfoBatchReg.htm?tocpath=5.%20HULFT%E3%81%AE%E6%93%8D%E4%BD%9C%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89%7C5.6%20%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E7%AE%A1%E7%90%86%7C_____1#%E9%85%8D%E4%BF%A1%E7%AE%A1%E7%90%86%E6%83%85%E5%A0%B1%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%83%BC%E3%81%AE%E5%BD%A2%E5%BC%8F
                """;
        System.out.println(link);
    }

}
