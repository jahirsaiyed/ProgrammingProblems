import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public Map<Integer, Long> aggregateUnitsSoldForYear(List<Order> records) {
        return records.stream()
                .collect(Collectors.groupingBy(o -> o.getOrderDate().getYear(), Collectors.summingLong(Order::getUnitsSold)));
    }

    @Override
    public Map<String, Double> aggregateOrdersByItemTypeProfit(List<Order> records) {
        return records.stream()
                .collect(Collectors.groupingBy(Order::getItemType, Collectors.summingDouble(Order::getProfit)));
    }

}
