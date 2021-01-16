public interface GetTaxiI {

     void orderTaxi(Customer customer);

     void cancelTaxi(Long customerId) ;

     void taxiAvailable(Integer taxi);
}