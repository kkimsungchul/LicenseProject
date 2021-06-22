import util.EncryptUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception{


        String userHome = System.getProperty("user.home");
        String userName = System.getProperty("user.name");
        String osArch = System.getProperty("os.arch");
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");

        String sysInfo = userHome + "|"+ userName + "|"+ osArch
                + "|"+ osName + "|"+ osVersion;

        EncryptUtil encryptUtil = new EncryptUtil();
        String encryptSysInfo = encryptUtil.encrypt(sysInfo);
        String decryptSysInfo = encryptUtil.decrypt(encryptSysInfo);
        File file = new File("sysInfo.txt");
        FileWriter writer = null;
        try {
            // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
            writer = new FileWriter(file, false);
            writer.write(encryptSysInfo);
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
