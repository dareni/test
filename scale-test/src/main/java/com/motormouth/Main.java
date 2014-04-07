package com.motormouth;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final int testCount=100;
    Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.doTest();
    }

    private void doTest() throws IOException {
        Thread[] browserThreads = new Thread[testCount];
        for(int i=0; i<testCount; i++) {
            System.out.print(i + " ");
            Runnable wsc = new WebSiteClient();
            browserThreads[i] = new Thread(wsc);
            browserThreads[i].start();
        }
    }

    public class WebSiteClient implements Runnable {

        public void run() {
            try {
//                MdiFramePage mdiFramePage = new MdiFramePage("http://localhost:8080/encompass-web");
                MdiFramePage mdiFramePage = new MdiFramePage("http://localhost:8088");
                logger.info("firstpage");
                mdiFramePage.doLogin();
                logger.info("donelogin");
                mdiFramePage.checkLogin();
                
            } catch (IOException ex) {
                System.out.println(ex);
                logger.error("WebSiteClient error.", ex);
            }

        }
    }
    


}
