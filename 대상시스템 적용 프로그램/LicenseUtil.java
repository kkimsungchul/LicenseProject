package com.securus.ciim.util;

import java.io.*;

public class LicenseUtil {



    public String getSysInfo() throws Exception{
        String sysInfo = "";
        String line;
        try{
            //파일 객체 생성
            File file = new File("C:\\apache-tomcat-8.0.30\\securus_license\\license.txt");
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);

            line = bufReader.readLine();

            sysInfo = line;
            //.readLine()은 끝에 개행문자를 읽지 않는다.
            bufReader.close();
        }catch (
        FileNotFoundException e) {
            e.printStackTrace();
        }catch(
        IOException e){
            System.out.println(e);
        }
        return sysInfo;
    }

    public String[] decryptSysInfo(String sysInfo) throws Exception{
        String decryptSysInfo[];
        EncryptUtil encryptUtil = new EncryptUtil();
        decryptSysInfo = encryptUtil.decrypt(sysInfo).split("\\|");
        return decryptSysInfo;
    }


    public boolean licenseCheck() throws Exception{

        String sysInfo[] = decryptSysInfo(getSysInfo());
        String userHome = System.getProperty("user.home");
        String userName = System.getProperty("user.name");
        String osArch = System.getProperty("os.arch");
        String osName = System.getProperty("os.name");
		String company = "securus";
        String osVersion = System.getProperty("os.version");

        for(int i=0;i<sysInfo.length;i++){
            System.out.println(sysInfo[i]);
        }
        DateUtil dateUtil = new DateUtil();

        if(!sysInfo[0].equals(userHome)){
            System.out.println("0 불일치");
            return false;
        }
        if(!sysInfo[1].equals(userName)){
            System.out.println("1 불일치");
            return false;
        }
        if(!sysInfo[2].equals(osArch)){
            System.out.println("2 불일치");
            return false;
        }
        if(!sysInfo[3].equals(osName)){
            System.out.println("3 불일치");
            return false;
        }
        if(!sysInfo[4].equals(osVersion)){
            System.out.println("4 불일치");
            return false;
        }
		if(!sysInfo[5].equals(company)){
            System.out.println("5 불일치");
        }
        if(Integer.parseInt(sysInfo[6]) < Integer.parseInt(dateUtil.getDate())){
            System.out.println("기간만료");
            return false;
        }
        return true;
    }
}
