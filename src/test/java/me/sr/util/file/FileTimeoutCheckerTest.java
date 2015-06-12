package me.sr.util.file;

import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by wanghuanyu on 2015/6/12.
 */
public class FileTimeoutCheckerTest {

    @Test
    public void testBackgroundProcess() throws Exception {
        String monitorroot = "D:\\testBackgroundProcess\\";
        long offtime = 1 * 60 * 1000L;
        String cronexpression = "*/5 * * * * MON-FRI";

        File file1 = new File(monitorroot);
        if(!file1.exists()){
            file1.mkdirs();
        }

		ConcurrentTaskScheduler ConcurrentTaskScheduler = new ConcurrentTaskScheduler();
        final FileTimeoutChecker fileTimeoutChecker = new FileTimeoutChecker(monitorroot,offtime );
        ConcurrentTaskScheduler.schedule(new Runnable() {
            public void run() {
                fileTimeoutChecker.backgroundProcess();
            }
        }, new CronTrigger(cronexpression));

        for(int i = 0 ;i<1000;i++){

            File file = new File(monitorroot,"abc"+i+".txt");
            try {
                file.createNewFile();
                System.out.println("create file :" + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        FileUtils.deleteDirectory(file1);
        System.exit(0);
    }
}