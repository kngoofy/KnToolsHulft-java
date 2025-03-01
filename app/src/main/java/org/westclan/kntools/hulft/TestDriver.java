package org.westclan.kntools.hulft;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.westclan.kntools.hulft.util.ReadFromFile;
import org.westclan.kntools.hulft.util.Util;

/**
 * テスト・確認用・試し用 クラス
 */
public class TestDriver {

        public static void main(String[] args) throws FileNotFoundException, IOException {

                var aa = new ReadFromFile();
                // aa.readFromFileToStringHst("app\\src\\main\\resources\\Hst.def");
                String hst = aa.readFromFileToString("app\\src\\main\\resources\\Hst.def");
                System.out.println(hst);

                String snd = aa.readFromFileToString("app\\src\\main\\resources\\Snd.def");
                System.out.println(snd);

                System.exit(0);

                String template = Util.getProperties("file.template-excelbook");
                System.out.println("template := " + template);

                String target = Util.getProperties("file.create-excelbook");
                System.out.println("target := " + target);

                System.exit(0);

        }

}
