package com.test.melnyk.service.serviceImpl;

import com.test.melnyk.dao.entitydao.OrderDao;
import com.test.melnyk.model.Product;
import com.test.melnyk.repository.Cart;
import com.test.melnyk.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    private OrderService orderService;

    @Mock
    OrderDao orderDao;

    @Mock
    Cart cart;

    @Before
    public void setUp() {
        orderService = new OrderServiceImpl(orderDao,cart);
    }

    @Test
    public void shouldCreateOrder(){
        when(orderDao.create(cart.getCart())).thenReturn(true);
        orderService.createOrder();
        verify(orderDao).create(cart.getCart());
    }

    @Test
    public void shouldReturnTreeMapOfAllOrders(){
        TreeMap<LocalDateTime, Map<Product, Integer>> testOrderMap = new TreeMap<>();
        when(orderDao.getOrders()).thenReturn(testOrderMap);
        orderService.showAllOrders();
        verify(orderDao).getOrders();
    }

    @Test
    public void buyAllProductsFromCartTest(){
        String test = "";
        when(cart.getTotalPrice()).thenReturn(test);
        orderService.buyAllProductsFromCart();
        verify(cart).getTotalPrice();
    }

    @Test
    public void showAllOrdersInSpecifiedPeriodOfTimeTest(){
        String test = "";
        LocalDateTime localDateTimeFrom = LocalDateTime.of(2019,10,28,17,33,33);
        LocalDateTime localDateTimeTo = LocalDateTime.of(2019,10,29,17,33,33);
        when(orderDao.getOrdersByPeriodOfTime(localDateTimeFrom,localDateTimeTo)).thenReturn(test);
        orderService.showAllOrdersInSpecifiedPeriodOfTime(localDateTimeFrom,localDateTimeTo);
        verify(orderDao).getOrdersByPeriodOfTime(localDateTimeFrom,localDateTimeTo);
    }

    @Test
    public void getOrdersByNearestDateTest(){
        TreeMap<LocalDateTime, Map<Product, Integer>> testOrderMap = new TreeMap<>();
        LocalDateTime time = LocalDateTime.of(2019,10,28,00,00,00);
        when(orderDao.getOrders()).thenReturn(testOrderMap);
        orderService.getOrdersByNearestDate(time);
        verify(orderDao,times(3)).getOrders();
    }

}