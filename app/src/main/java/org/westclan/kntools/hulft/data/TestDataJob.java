package org.westclan.kntools.hulft.data;

/**
 * テスト・確認用データ ジョブ起動情報定義
 */
public class TestDataJob {

    public static final String Data = """
            #
            # ID=HEADER
            #

            JOB=HEADER
            JOB DEF
             JOBHEADER01 command line A copy file to dest
            DEFEND
            COMMENT=HEADER comment
            END

            #
            # ID=JOB01
            #

            JOB=JOB01
            JOB DEF
             JOB01 command line 1 copy file to dest
             JOB01 command line 2 rename dest to other
             JOB01 command line 3 rename dest to other
             JOB01 command line 4 rename dest to other
             JOB01 command line 5 rename dest to other
            DEFEND
            COMMENT=JOB01 comment
            END

            #
            # ID=JOB02
            #

            JOB=JOB02
            JOB DEF
             JOB02 command line N1 mv "${FILENM}" "${FILENM}_${EDATE2}${ETIME}"
             JOB02 command line N2 opes...
             JOB02 command line N3 grep ASCII text.file
            DEFEND
            COMMENT=
            END

            #
            # ID=JOB03
            #

            JOB=JOB03
            JOB DEF
             JOB03 command line N1 mv "${FILENM}" "${FILENM}_${EDATE2}${ETIME}"
             JOB02 command line N3 grep ASCII text.file
            DEFEND
            COMMENT=
            END
            """;

    public void printLinkInfo() {
        String link = """
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE/HULFTOpeCmd/MngmntInfoBatchReg.htm?tocpath=5.%20HULFT%E3%81%AE%E6%93%8D%E4%BD%9C%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89%7C5.6%20%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E7%AE%A1%E7%90%86%7C_____1#%E3%82%B8%E3%83%A7%E3%83%96%E8%B5%B7%E5%8B%95%E6%83%85%E5%A0%B1%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%83%BC%E3%81%AE%E5%BD%A2%E5%BC%8F
                """;
        System.out.println(link);
    }

}
