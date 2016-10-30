package com.icfcc.db;

import org.mybatis.generator.internal.ObjectFactory;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Created by admin on 2015/8/16.
 */
public class MybatisGenerator {
    public static void main(String[] args) throws URISyntaxException {
        File file = new File("generatorConfig.xml");
        String resourcePath = "ssm-db\\src\\test\\resources\\"+file.getName();
        System.out.println(new File(resourcePath).getAbsolutePath());
        int i = 0;
        args = new String[4];
        args[i++] = "-configfile";
        args[i++] = new File(resourcePath).getAbsolutePath();
        args[i++] = "-overwrite";
        args[i++] = "-verbose";
//        args[i++] = "-tables";
//        args[i++] = "Course";

        ObjectFactory.addResourceClassLoader(MybatisGenerator.class.getClassLoader());
        org.mybatis.generator.api.ShellRunner.main(args);

        //
//        List<String> warnings = new ArrayList<String>();
//        File configurationFile = new File(resourcePath,"generatorConfig.xml");

    }
}
