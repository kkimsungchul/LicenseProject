package util;

import java.io.*;

public class LicenseUtil {



    public String getSysInfo() throws Exception{
        String sysInfo = "";
        String line;
        try{
            //파일 객체 생성
            File file = new File("license.txt");
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


}
