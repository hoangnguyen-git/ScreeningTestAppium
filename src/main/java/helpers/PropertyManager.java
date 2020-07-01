package helpers;

import java.util.Properties;

public class PropertyManager {

    private static PropertyManager instance;
    private static String url;
    private static String company;
    private static String username;
    private static String password;
    private static String OTP;
    private static String fileName = null;
    private static int timeOutSecond;

    //Create a Singleton instance. We need only one instance of Property Manager.
    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
            instance.loadData();
        }
        return instance;
    }

    //Get all configuration data and assign to related fields.
    private void loadData() {
        try {
            if (!System.getProperty("properties").equals(null)) {
                fileName = System.getProperty("properties");
            }
            System.out.println("FIle name " + fileName);

        } catch (Exception e) {
            e.getMessage();
            fileName = "environment.properties";
        }
        Properties properties = ReadDataFile.getPro(fileName);
        url = properties.getProperty("url");
        System.out.println("\nenvironment " + url);
        company = properties.getProperty("company");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        OTP = properties.getProperty("OTP");
        timeOutSecond = Integer.parseInt(properties.getProperty("timeOutSecond"));
    }

    public String getURL() {
        return url;
    }

    public String getCompany() {
        return company;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getOTP() {
        return OTP;
    }

    public int getTimeOut() {
        return timeOutSecond;
    }
}
