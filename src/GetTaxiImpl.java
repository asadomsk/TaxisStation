import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GetTaxiImpl  implements GetTaxiI{

    Station st=Station.getStationInst();
    private Map<Long, Integer> orders;

    @Override
    public void orderTaxi(Customer customer) {
        Integer taxi = st.getTaxisAvailable().poll();
        if (taxi != null) {
            handleAvailableTaxis(customer,taxi);
        } else {
            handleNonAvailableTaxis(customer);
        }
    }

    private void handleAvailableTaxis(Customer customer, Integer taxi){
        if (orders == null)
            orders = new HashMap<>();
        orders.put(customer.getId(), taxi);
        System.out.println("Reservation accepted:  Customer -  " + customer.toString() + ", Taxi num - "
                + (taxi + 1));
    }

    private void handleNonAvailableTaxis(Customer customer){
        if (customer.isVIP()) {
            handleVIPCustomers(customer);

        } else {
            handleNonVIPCustomers(customer);
        }
        System.out.println("Customer - " + customer.toString() + " has been queued");
    }

    private void handleVIPCustomers(Customer customer){
        if (st.getVip() == null)
            st.setVip(new LinkedList<>());
        st.getVip().add(customer);

    }

    private void handleNonVIPCustomers(Customer customer){

        if (st.getCustomers()== null)
            st.setCustomers(new LinkedList<>());
        st.getCustomers().add(customer);
    }

    @Override
    public void cancelTaxi(Long customerId) {
        Integer taxi = orders.get(customerId - 1);
        if (taxi != -1) {
            System.out.println("Customer - " + customerId + " canceled the order");
            orders.put(customerId - 1, -1);
            taxiAvailable(taxi);

        } else
            System.out.println("Sorry, you have no orders to cancel");
    }

    @Override
    public void taxiAvailable(Integer taxi) {
        System.out.println("The taxi number " + (taxi + 1) + " is available at the station");
        Customer cv = st.getVip().poll();
        if (cv != null) {
            createOrder(cv, taxi);
        } else {
            Customer cr = st.getCustomers().poll();
            if (cr != null) {
                createOrder(cr, taxi);
            } else {
                st.getTaxisAvailable().add(taxi);
            }

        }

    }

    private void createOrder(Customer c, Integer taxi){
        orders.put(c.getId(), taxi);
        System.out.println(
                "Reservation accepted:  Customer -  " + c.toString() + ", Taxi num - " + (taxi + 1));
    }

}
