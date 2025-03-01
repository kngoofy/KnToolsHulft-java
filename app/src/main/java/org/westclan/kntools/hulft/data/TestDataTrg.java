package org.westclan.kntools.hulft.data;

/**
 * テスト・確認用データ ファイルトリガ情報定義
 */
public class TestDataTrg {

    public static final String Data = """
            TRGID=T0000001
            DIRECTORYNAME=/home/users/usr1/
            TARGETFILENAME=file1
            JOBID=J0000001
            CREATE=Y
            DELETE=Y
            MODIFY=Y
            TYPE=S
            FILECHECK=Y
            FILECHECKWAIT=N
            INTERVAL=3600
            EXECUTION=Y
            COMMENT=人事情報ファイル
            END
            """;

    public void printLinkInfo() {
        String link = """
                https://www.hulft.com/help/ja-jp/HULFT-V10/LX-OPE/Content/HULFT_OPE/HULFTOpeCmd/MngmntInfoBatchReg.htm?tocpath=5.%20HULFT%E3%81%AE%E6%93%8D%E4%BD%9C%E3%82%B3%E3%83%9E%E3%83%B3%E3%83%89%7C5.6%20%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E7%AE%A1%E7%90%86%7C_____1#%E3%83%95%E3%82%A1%E3%82%A4%E3%83%AB%E3%83%88%E3%83%AA%E3%82%AC%E6%83%85%E5%A0%B1%E3%83%91%E3%83%A9%E3%83%A1%E3%83%BC%E3%82%BF%E3%83%BC%E3%81%AE%E5%BD%A2%E5%BC%8F
                """;
        System.out.println(link);
    }
}
