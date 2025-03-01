package org.westclan.kntools.hulft.model;

/**
 * record:Hulft の転送グループ情報 レコード フラットにしたもの ArrayListを平たく伸ばし
 */
public record RDefHulftTGrpFlat(

        String ID, // 識別させる情報ID プログラム内部管理用 {TGrp}
        EHulftManageType type, // 識別させる情報タイプ

        // HULFTのキーワードなので、大文字で管理することにした
        String GRP, // 転送グループID
        String SERVER, // ホスト名 1000 個まで指定可能
        String COMMENT, // コメント
        boolean hasBoderTop,
        boolean hasBoderBottom) {

}
