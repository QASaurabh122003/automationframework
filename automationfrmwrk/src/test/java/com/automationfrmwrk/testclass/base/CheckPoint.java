package com.automationfrmwrk.testclass.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckPoint {

    private static final Logger log = LogManager.getLogger(CheckPoint.class.getName());

    public static HashMap<String,String> resultMap=new HashMap<String,String>();
    private static String PASS="PASS";
    private static  String FAIL="FAIL";

    /***
     * clears the hashMap
     */
    public static void clearHashMap (){
        log.info("Clearing the HashMap");
        resultMap.clear();
    }

    private static void setStatus(String mapKey,String status){
        resultMap.put(mapKey,status);
        log.info(mapKey+":->"+resultMap.get(mapKey));

    }
    public static void mark(String testName,boolean result,String resultMessage){
        testName=testName.toLowerCase();
        String mapKey=testName+"."+resultMessage;
        try{
            if (result){
                setStatus(mapKey,PASS);
            }else{
                setStatus(mapKey,FAIL);
            }
        }catch (Exception e){
            log.error("Expection occucred");
            setStatus(mapKey,FAIL);
            e.printStackTrace();
        }
    }

    public static void markFinal(String testName,boolean result,String resultMessage){
        testName=testName.toLowerCase();
        String mapKey=testName+":->"+resultMessage;
        try {
            if (result){
                setStatus(mapKey,PASS);

            }else{
                setStatus(mapKey,FAIL);
            }
        }catch (Exception e){
            log.info("Expection Occured");
            setStatus(mapKey,FAIL);
            e.printStackTrace();

        }
        ArrayList<String> resultList=new ArrayList<String>();

        for(String key:resultMap.keySet()){
            resultList.add(resultMap.get(key));
        }
        for(int i =0;i<resultList.size();i++){
            if(resultList.contains(FAIL)){
                log.info("Test method Fail");
                Assert.assertTrue(false);
            }else{
                log.info("Test method Pass");
                Assert.assertTrue(true);

            }

        }

    }



}
