public class Bike {


    Bike(){
        System.out.println("Constructor is invoked");

    }


    static String brandName ;
    static String cc;
    static int average;
    static int price;
    static int torque;


    public static void main(String args[]){

        Bike splendor = new Bike();
        Bike duke = new Bike();

        splendor.brandName = "Bajaj";
        splendor.cc = "125";
        splendor.average = 50;

        Bike.setTorque(70);

        System.out.println("Brand name is : "+brandName);
        System.out.println("CC is : "+cc);
        System.out.println("average is : "+average);
        System.out.println("price is : "+price);
        System.out.println("torque is : "+torque);

    }

    public static void brandName(String name){
        brandName = name;
    }

    public static void setTorque(int torqueValue){
        torque = torqueValue;
    }

    public String getCC(){
        return cc;
    }

    public void run(){
        System.out.println("Bike is running ");

    }
    
}
