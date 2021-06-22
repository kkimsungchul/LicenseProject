import util.DateUtil;
import util.LicenseUtil;



public class Main {

    public static void main(String[] args) throws Exception{
        LicenseUtil licenseUtil = new LicenseUtil();
        DateUtil dateUtil = new DateUtil();
        //String expiryDate = dateUtil.getTime("yyyyMMdd",1,1);
        String expiryDate = "20220610";
        licenseUtil.getLicenseKey(expiryDate);







    }
}
