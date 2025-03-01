package org.westclan.kntools.hulft.model;

import java.util.ArrayList;

/**
 * Hulft データ格納クラス 各パラメータの String と ArrayList まとめて格納
 * 使用を取りやめ オブジェクトが大きくなりすぎる危険性を感じた
 * 本番データ量が大きいサーバの可能性感じたので
 */
public class HulftData {

    // (1) 詳細ホスト情報定義 データ ArrayList
    ArrayList<String> listDefHst;
    ArrayList<RDefHulftHst> listHst;

    // (2)ジョブ起動情報定義 データ ArrayList
    ArrayList<String> listDefJob;
    // ArrayList<RDefHulftJob> listJob;
    ArrayList<RDefHulftJobFlat> listJob;

    // (3) 集信管理情報定義 データ ArrayList
    ArrayList<String> listDefRcv;
    ArrayList<RDefHulftRcv> listRcv;

    // (4) 配信管理情報定義 データ ArrayList
    ArrayList<String> listDefSnd;
    ArrayList<RDefHulftSnd> listSnd;

    // (5) 転送グループ情報定義 データ ArrayList
    ArrayList<String> listDefTGrp;
    // ArrayList<RDefHulftTGrp> listTGrp;
    ArrayList<RDefHulftTGrpFlat> listTGrp;

    /**
     * コンストラクタ
     * 
     * @param listDefHst  詳細ホスト情報定義 レコードデータのリスト
     * @param listHst     詳細ホスト情報定義 データのリスト
     * @param listDefJob  ジョブ起動情報定義 レコードデータのリスト
     * @param listJob     ジョブ起動情報定義 データのリスト
     * @param listDefRcv  集信管理情報定義 レコードデータのリスト
     * @param listRcv     集信管理情報定義 データのリスト
     * @param listDefSnd  配信管理情報定義 レコードデータのリスト
     * @param listSnd     配信管理情報定義 データのリスト
     * @param listDefTGrp 転送グループ情報定義 レコードデータのリスト
     * @param listTGrp    転送グループ情報定義 データのリスト
     */
    public HulftData(
            ArrayList<String> listDefHst, ArrayList<RDefHulftHst> listHst,
            ArrayList<String> listDefJob, ArrayList<RDefHulftJobFlat> listJob,
            ArrayList<String> listDefRcv, ArrayList<RDefHulftRcv> listRcv,
            ArrayList<String> listDefSnd, ArrayList<RDefHulftSnd> listSnd,
            ArrayList<String> listDefTGrp, ArrayList<RDefHulftTGrpFlat> listTGrp) {
        this.listDefHst = listDefHst;
        this.listHst = listHst;
        this.listDefJob = listDefJob;
        this.listJob = listJob;
        this.listDefRcv = listDefRcv;
        this.listRcv = listRcv;
        this.listDefSnd = listDefSnd;
        this.listSnd = listSnd;
        this.listDefTGrp = listDefTGrp;
        this.listTGrp = listTGrp;
    }

    /**
     * 詳細ホスト情報定義 レコードデータのリストを返す
     * 
     * @return RDefHulftHst
     */
    public ArrayList<RDefHulftHst> getListHst() {
        return this.listHst;
    }

    /**
     * ジョブ起動情報定義 （フラット）レコードデータのリストを返す
     * 
     * @return
     */
    // public ArrayList<RDefHulftJob> getListJob() {
    public ArrayList<RDefHulftJobFlat> getListJob() {
        return this.listJob;
    }

    /**
     * 集信管理情報定義 レコードデータのリストを返す
     * 
     * @return
     */
    public ArrayList<RDefHulftRcv> getListRcv() {
        return this.listRcv;
    }

    /**
     * 配信管理情報定義 レコードデータのリストを返す
     * 
     * @return
     */
    public ArrayList<RDefHulftSnd> getListSnd() {
        return this.listSnd;
    }

    /**
     * 転送グループ情報定義 （フラット） レコードデータのリストを返す
     * 
     * @return
     */
    public ArrayList<RDefHulftTGrpFlat> getListTGrp() {
        return this.listTGrp;
    }

    /**
     * 詳細ホスト情報定義 データのリストを返す
     * 
     * @return
     */
    public ArrayList<String> getListDefHst() {
        return this.listDefHst;
    }

    /**
     * ジョブ起動情報定義 データのリストを返す
     * 
     * @return
     */
    public ArrayList<String> getListDefJob() {
        return this.listDefJob;
    }

    /**
     * 集信管理情報定義 データのリストを返す
     * 
     * @return
     */

    public ArrayList<String> getListDefRcv() {
        return this.listDefRcv;
    }

    /**
     * 配信管理情報定義 データのリストを返す
     * 
     * @return
     */

    public ArrayList<String> getListDefSnd() {
        return this.listDefSnd;
    }

    /**
     * 詳転送グループ情報定義 データのリストを返す
     * 
     * @return
     */
    public ArrayList<String> getListDefTGrp() {
        return this.listDefTGrp;
    }

}
