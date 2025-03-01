package org.westclan.kntools.hulft.model;

/**
 * record:Hulft のジョブ起動情報 レコード フラットにしたもの ArrayListを平たく伸ばし
 */
public record RDefHulftJobFlat(

        String ID, // 識別させる情報ID プログラム内部管理用 {Job}
        EHulftManageType type, // 識別させる情報タイプ

        // HULFTのキーワードなので、大文字で管理することにした
        String JOB, // ジョブID
        String DEF, // ジョブ名 １３個まで指定可能
        String COMMENT, // コメント
        boolean hasBoderTop,
        boolean hasBoderBottom

) {

}
