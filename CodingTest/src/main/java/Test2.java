import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Write a program to read the sales record file and
Read the Sales record file (use the SalesRecords.csv file provided)
Calculate sum of profits per each item sold and print on console.
Calculate in which year there was more orders received and print it on console.
Calculate average days between order date and ship date.
 */

public class Test2 {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public static void main(String[] args) {
        List<Order> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader("SalesRecords.csv"));) {
            String[] values = null;
            csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                records.add(buildOrder(values));
            }
            Map<String, Double> profitByItemType = records.stream()
                    .collect(Collectors.groupingBy(Order::getItemType, Collectors.summingDouble(Order::getProfit)));
            System.out.println(profitByItemType);

            Map<Integer, Long> unitsSoldByYear = records.stream()
                    .collect(Collectors.groupingBy(o -> o.getOrderDate().getYear(), Collectors.summingLong(Order::getUnitsSold)));

            Stream<Map.Entry<Integer, Long>> sorted = unitsSoldByYear.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

            System.out.println("sorted: " +sorted.collect(Collectors.toList()));
            System.out.println(unitsSoldByYear);

        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private static Order buildOrder(String[] values) throws ParseException {
        Order order = new Order();
        order.setRegion(values[0]);
        order.setCountry(values[1]);
        order.setOrderDate(dateFormat.parse(values[5]));
        order.setOrderId(Long.parseLong(values[6]));
        order.setItemType(values[2]);
        order.setShipDate(dateFormat.parse(values[7]));
        order.setProfit(Double.parseDouble(values[13]));
        order.setUnitsSold(Long.parseLong(values[8]));
        return order;
    }
}

class Order {
//    Region	Country	Item Type	Sales Channel	Order Priority	Order Date	Order ID	Ship Date	Units Sold	Unit Price	Unit Cost	Total Revenue	Total Cost	Total Profit
    private long orderId;
    private String region;
    private String country;
    private String itemType;
    private Date orderDate;
    private Date shipDate;
    private Double profit;
    private long unitsSold;

    public Order() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public long getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(long unitsSold) {
        this.unitsSold = unitsSold;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", itemType='").append(itemType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
