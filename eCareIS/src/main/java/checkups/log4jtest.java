package checkups;

import org.apache.log4j.Logger;

/**
 * Created by Alexey on 26.06.2015.
 */
public class log4jtest {
    static Logger logger = Logger.getLogger(log4jtest.class);
    public static void main(String[] args) {
        try{
            throw new Exception();
        } catch (Exception e) {
            logger.error("Exception happend",e);
        }
        logger.info("just info");
        logger.warn("warn");
        A.doLog();


    }

    static class A{
        static Logger logger = Logger.getLogger(A.class);
        static void doLog(){
            logger.info("info from A class");
            logger.error("Error message from  class A");
            logger.debug("Debug message from  class A");
        }
    }
}
