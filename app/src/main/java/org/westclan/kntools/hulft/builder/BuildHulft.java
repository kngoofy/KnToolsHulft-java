package org.westclan.kntools.hulft.builder;

import java.io.IOException;
import java.util.ArrayList;

import org.westclan.kntools.hulft.model.*;

/**
 * Hulft 各種定義データ の ArrayList データ組み立て
 * まとめて Facadeぽい窓口的クラス
 * HulftData クラスに格納する
 * 
 * (このクラスを使用するのをやめた 本番データ多いと オブジェクトが多きするかもなので)
 */
public class BuildHulft {

    /**
     * Hulft 各パラメータを組み立て、HulftDataクラスに格納する
     * 
     * @param stringDataHst  Stinrg 詳細ホスト情報定義 データ
     * @param stringDataJob  Stinrg ジョブ起動情報定義 データ
     * @param stringDataRcv  Stinrg 集信管理情報 データ
     * @param stringDataSnd  Stinrg 配信管理情報 データ
     * @param stringDataTGrp Stinrg 転送グループ情報定義 データ
     * @return HulftData クラス 各パラメータ格納済み
     * @throws IOException
     */
    public HulftData buildHulft(
            String stringDataHst,
            String stringDataJob,
            String stringDataRcv,
            String stringDataSnd,
            String stringDataTGrp) throws IOException {

        // (1-1) 詳細ホスト情報定義 データ ArrayList<RDefHulftHst> 組み立て
        var buildHst = new BuildHulftHst();
        ArrayList<RDefHulftHst> listHst = buildHst.buildHstArray(stringDataHst);

        // (1-2) 詳細ホスト情報定義 データ ArrayList<String> 組み立て
        var buildDefHst = new BuildHulftDefHst();
        ArrayList<String> listDefHst = buildDefHst.buildHulftDefHst(stringDataHst);

        // (2-1)ジョブ起動情報定義 データ ArrayList<RDefHulftHst> 組み立て
        var buildJob = new BuildHulftJob();
        // ArrayList<RDefHulftJob> listJob = buildJob.buildJobArray(stringDataJob);
        ArrayList<RDefHulftJobFlat> listJob = buildJob.buildJobArray(stringDataJob);

        // (2-1)ジョブ起動情報定義 データ ArrayList<String> 組み立て
        var buildDefJob = new BuildHulftDefJob();
        ArrayList<String> listDefJob = buildDefJob.buildHulftDefJob(stringDataJob);

        // (3-1) 集信管理情報 データ ArrayList<RDefHulftHst> 組み立て
        var buildRcv = new BuildHulftRcv();
        ArrayList<RDefHulftRcv> listRcv = buildRcv.buildRcvArray(stringDataRcv);

        // (3-2) 集信管理情報 データ ArrayList<String> 組み立て
        var buildDefRcv = new BuildHulftDefRcv();
        ArrayList<String> listDefRcv = buildDefRcv.buildHulftDefRcv(stringDataRcv);

        // (4-1) 配信管理情報 データ ArrayList<RDefHulftHst> 組み立て
        var buildSnd = new BuildHulftSnd();
        ArrayList<RDefHulftSnd> listSnd = buildSnd.buildSndArray(stringDataSnd);

        // (4-2) 配信管理情報 データ ArrayList<String> 組み立て
        var buildDefSnd = new BuildHulftDefSnd();
        ArrayList<String> listDefSnd = buildDefSnd.buildHulftDefSnd(stringDataSnd);

        // (5-1) 転送グループ情報定義 データ ArrayList<RDefHulftHst> 組み立て
        var buildTGrp = new BuildHulftTGrp();
        // ArrayList<RDefHulftTGrp> listTGrp = buildTGrp.buildTGrpArray(stringDataTGrp);
        ArrayList<RDefHulftTGrpFlat> listTGrp = buildTGrp.buildTGrpArray(stringDataTGrp);

        // (5-2) 転送グループ情報定義 データ ArrayList<String> 組み立て
        var buildDefTGrp = new BuildHulftDefTGrp();
        ArrayList<String> listDefTGrp = buildDefTGrp.buildHulftDefTGrp(stringDataTGrp);

        // hulftData クラスに格納
        HulftData hulftData = new HulftData(
                listDefHst, listHst,
                listDefJob, listJob,
                listDefRcv, listRcv,
                listDefSnd, listSnd,
                listDefTGrp, listTGrp);

        // HulftData クラスを返す
        return hulftData;
    }

}
