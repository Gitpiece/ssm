package com.icfcc;

import java.io.File;

/**
 * Created by admin on 2015/8/16.
 */
public class MybatisGenerator {
    public static final String resourcePath = System.getProperty("user.dir")
            + File.separator + "src\\test\\resources" + File.separator;
    public static void main(String[] args) {
        System.out.println(new File(resourcePath, "generatorConfig.xml").getAbsolutePath());
        int i = 0;
        args = new String[4];
        args[i++] = "-configfile";
        args[i++] = new File(resourcePath, "generatorConfig.xml").getAbsolutePath();
        args[i++] = "-overwrite";
        args[i++] = "-verbose";
//        args[i++] = "-tables";
//        args[i++] = "Course";
        org.mybatis.generator.api.ShellRunner.main(args);

        //
//        List<String> warnings = new ArrayList<String>();
//        File configurationFile = new File(resourcePath,"generatorConfig.xml");

    }
}
