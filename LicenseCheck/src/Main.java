import util.DateUtil;
import util.LicenseUtil;

public class Main {

    public static void main(String[] args)throws Exception {
        LicenseUtil licenseUtil = new LicenseUtil();
        String sysInfo[] = licenseUtil.decryptSysInfo(licenseUtil.getSysInfo());
        String userHome = System.getProperty("user.home");
        String userName = System.getProperty("user.name");
        String osArch = System.getProperty("os.arch");
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String company = "securus";
        DateUtil dateUtil = new DateUtil();
//        for(int i=0;i<sysInfo.length;i++){
//            System.out.println(sysInfo[i]);
//        }
        if(!sysInfo[0].equals(userHome)){
            System.out.println("0 불일치");
        }
        if(!sysInfo[1].equals(userName)){
            System.out.println("1 불일치");
        }
        if(!sysInfo[2].equals(osArch)){
            System.out.println("2 불일치");
        }
        if(!sysInfo[3].equals(osName)){
            System.out.println("3 불일치");
        }
        if(!sysInfo[4].equals(osVersion)){
            System.out.println("4 불일치");
        }
        if(!sysInfo[5].equals(company)){
            System.out.println("5 불일치");
        }
        if(Integer.parseInt(sysInfo[6]) < Integer.parseInt(dateUtil.getDate())){
            System.out.println("기간만료");
        }
    }
}
