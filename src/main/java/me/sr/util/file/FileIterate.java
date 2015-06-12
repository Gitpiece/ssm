package me.sr.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 遍历文件目录示例代码
 * Created by wanghuanyu on 2015/6/12.
 */
public class FileIterate {

    public void test() {
        List<File> files = new ArrayList<File>(1);
        List<File> tempFiles = new ArrayList<File>(1);
        files.add(new File("D:\\tmp"));

        while (files.size() > 0) {

            for (File file : files) {

                //如果是文件，如果修改，删除。
                if (file.isFile()) {
                    System.out.println("f "+file.getAbsoluteFile());
                } else if (file.isDirectory()) {
                    File[] files1 = file.listFiles();
                    for (int i = 0; i < files1.length; i++) {
                        File file1 = files1[i];
                        if (file1.isFile()) {
                            System.out.println("f "+file1.getAbsoluteFile());
                        } else if (file1.isDirectory()) {
                            System.out.println("d "+file1.getAbsoluteFile());
                            tempFiles.add(file1);
                        }
                    }
                }

            }
            files.clear();
            files.addAll(tempFiles);
            tempFiles.clear();
        }
    }
}
