package org.westclan.kntools.hulft.util;

import java.util.ResourceBundle;

/**
 * ユーティリティクラス
 */
public class Util {

    /**
     * テスト用
     */
    public static void testMethod() {
        String result = "";
        result = getProperties("file.template-excelbook");
        System.out.println(result);
        result = getProperties("file.create-excelbook");
        System.out.println(result);
    }

    /**
     * プロパティファイルからプロパティを返す
     * 
     * @param key プロパティのキー
     * @return プロパティ値
     */
    public static String getProperties(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        return bundle.getString(key);
    }

}
