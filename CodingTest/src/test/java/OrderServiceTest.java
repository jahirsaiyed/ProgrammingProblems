import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

public class OrderServiceTest {

    @Mock
    private OrderDAO orderDAO;
    @InjectMocks
    private OrderService orderService = new OrderServiceImpl();

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(OrderServiceTest.class);
    }

    @Test
    public void testGetAllOrdersNull() {
        orderDAO = Mockito.mock(OrderDAOImpl.class);

        Mockito.when(orderDAO.getAllOrders()).thenReturn(null);
        Assert.assertNull(orderService.getAllOrders());
    }

    @Test
    public void testGetAllOrdersEmpty() {
        Mockito.when(orderDAO.getAllOrders()).thenReturn(Collections.emptyList());
        Assert.assertEquals(0, orderService.getAllOrders().size());
    }

    @Test
    public void testGetAllOrdersSingleValueReturned() {
        Mockito.when(orderDAO.getAllOrders()).thenReturn(Collections.singletonList(new Order()));
        Assert.assertEquals(1, orderService.getAllOrders().size());
    }
}
