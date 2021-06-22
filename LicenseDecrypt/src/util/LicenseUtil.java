package util;

import java.io.*;

public class LicenseUtil {



    public String getSysInfo() throws Exception{
        String sysInfo = "";
        String line;
        try{
            //파일 객체 생성
            File file = new File("sysInfo.txt");
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

    public String createLicenseKey(String expiryDate) throws Exception{
        String sysInfo = getSysInfo();
        String decryptSysInfo[] = decryptSysInfo(sysInfo) ;
        String tempArray[] = new String[decryptSysInfo.length+2];
        String company = "securus";
        for(int i=0;i<decryptSysInfo.length;i++){
            tempArray[i]=decryptSysInfo[i];
        }
        tempArray[decryptSysInfo.length] = company;
        tempArray[decryptSysInfo.length+1] = expiryDate;

        String tempString="";
        for(int i=0;i<tempArray.length;i++){
            if(i!=0){
                tempString+= "|"+tempArray[i];
            }else{
                tempString+=tempArray[i];
            }
        }

        EncryptUtil encryptUtil= new EncryptUtil();
        tempString = encryptUtil.encrypt(tempString);
        return tempString;
    }

    public void getLicenseKey(String expiryDate) throws Exception{

        File file = new File("license.txt");
        FileWriter writer = null;
        try {
            // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
            writer = new FileWriter(file, false);
            writer.write(createLicenseKey(expiryDate));
            writer.flush();
            System.out.println("DONE");
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null) writer.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

}
