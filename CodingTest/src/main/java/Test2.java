/*
Write a program to read the sales record file and
Read the Sales record file (use the SalesRecords.csv file provided)
Calculate sum of profits per each item sold and print on console.
Calculate in which year there was more orders received and print it on console.
Calculate average days between order date and ship date.
 */

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test2 {

    private static OrderService orderService = new OrderServiceImpl();

    public static void main(String[] args) {
        List<Order> allOrders = orderService.getAllOrders();
        Map<String, Double> ordersByItemTypeProfit = orderService.aggregateOrdersByItemTypeProfit(allOrders);
        System.out.println(ordersByItemTypeProfit);
        Map<Integer, Long> unitsSoldForYear = orderService.aggregateUnitsSoldForYear(allOrders);
        Stream<Map.Entry<Integer, Long>> sorted = unitsSoldForYear.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
        System.out.println("sorted: " + sorted.collect(Collectors.toList()));
    }


}


