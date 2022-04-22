public class BajajBike extends Bike implements Car{

    final String BRAND_NAME;
    static String cc;
    String bikeName;
    int price;



    BajajBike (){
        BRAND_NAME = "Bajaj";
    }

    BajajBike (String bikeName, String cc){
        BRAND_NAME = "Bajaj";
        this.cc = cc;
        this.bikeName = bikeName;
    }

    public void setPrice(){
        price = 50000;
    }

    private void setPrice(int price){
        this.price = price;
    }

    protected void setPrice(String price){
        this.price = Integer.valueOf(price);
    }

    public void run(){
        System.out.println("Bajaj Bike is running ");

    }
    public static void main(String args[]){
        BajajBike bajajBike = new BajajBike();
        bajajBike.run();
    }
}
