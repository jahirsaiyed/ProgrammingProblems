import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    public List<Order> getAllOrders() {
        List<Order> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader("SalesRecords.csv"));) {
            String[] values = null;
            csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                records.add(buildOrder(values));
            }
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return records;
    }

    private Order buildOrder(String[] values) throws ParseException {
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
