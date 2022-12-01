package old;

public class Submarine {
    private Sonar sonar;

    public Submarine() {
        sonar = new Sonar();
    }

    public String executeOrders(String orderType, String inputPath) {
        switch (orderType) {
            case "depth":
                return Sonar.depthCalculations(inputPath);
        }

        return "orders not understood";
    }


    public static void main(String[] args) {
        System.out.println(new Submarine().executeOrders(args[0], args[1]));
    }
}
