
public class MainClass {

    public static void main(String[] args) {
        Station st = Station.getStationInst();
        GetTaxiI getTaxi=new GetTaxiImpl();

        st.setTaxisAvailable(10);

        for (int i = 0; i < 9; i++) {

            getTaxi.orderTaxi(new Customer(i, false));

        }

        getTaxi.orderTaxi(new Customer(9, true));
        getTaxi.orderTaxi(new Customer(10, false));
        getTaxi.orderTaxi(new Customer(11, false));
        getTaxi.orderTaxi(new Customer(12, true));
        getTaxi.taxiAvailable(5);
        getTaxi.orderTaxi(new Customer(13, true));
        getTaxi.taxiAvailable(3);
        getTaxi.taxiAvailable(7);

    }
}
