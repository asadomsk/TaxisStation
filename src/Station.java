import lombok.*;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Station {

    @Setter(AccessLevel.NONE)
    private Queue<Integer> taxisAvailable;

    private Queue<Customer> customers;

    private Queue<Customer> vip;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static Station station;


    public static Station getStationInst(){
        if(station==null){
            synchronized (Station.class){
                if(station==null)
                    station= new Station();
            }
        }
        return station;

    }

    public void setTaxisAvailable(int numOfTaxis) {
        taxisAvailable = new LinkedList<>();
        for (int i=0;i< numOfTaxis; i++){
            taxisAvailable.add(i);
        }
    }
}
