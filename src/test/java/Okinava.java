public abstract class Okinava {

    private static String brandName ;
    private static String cc;

    public static void setBrandName(String brandName) {
        Okinava.brandName = brandName;
    }


    public static String getCc() {
        return cc;
    }

    public static void setCc(String cc) {
        Okinava.cc = cc;
    }

    static int average;

    public abstract int getAverage();

    public String getBrandName(){
        return brandName;
    }





}
