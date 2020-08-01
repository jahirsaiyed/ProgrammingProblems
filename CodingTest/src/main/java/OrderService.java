import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getAllOrders();

    Map<Integer, Long> aggregateUnitsSoldForYear(List<Order> records);

    Map<String, Double> aggregateOrdersByItemTypeProfit(List<Order> records);
}
