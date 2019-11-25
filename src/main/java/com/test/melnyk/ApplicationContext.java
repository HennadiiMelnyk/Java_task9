package com.test.melnyk;

import com.test.melnyk.controller.Command;
import com.test.melnyk.controller.command.itemcommand.AddProductToCartCommand;
import com.test.melnyk.controller.command.itemcommand.AddProductToListCommand;
import com.test.melnyk.controller.command.itemcommand.ExitCommand;
import com.test.melnyk.controller.command.itemcommand.ShowListProductCommand;
import com.test.melnyk.controller.command.ordercommand.*;
import com.test.melnyk.controller.command.ordercommand.BuyAllProductsFromCartCommand;
import com.test.melnyk.controller.command.ordercommand.CreateOrderCommand;
import com.test.melnyk.controller.command.ordercommand.GetLastFiveItemInCartCommand;
import com.test.melnyk.controller.command.ordercommand.GetOrderByNearestDateCommand;
import com.test.melnyk.controller.command.ordercommand.ShowAllOrdersCommand;
import com.test.melnyk.controller.command.ordercommand.ShowAllOrdersInSpecifiedPeriodOfTimeCommand;
import com.test.melnyk.controller.command.ordercommand.ShowCartCommand;
import com.test.melnyk.dao.entitydao.ItemDao;
import com.test.melnyk.dao.entitydao.OrderDao;
import com.test.melnyk.dao.entitydao.impl.ItemDaoImpl;
import com.test.melnyk.dao.entitydao.impl.OrderDaoImpl;
import com.test.melnyk.model.Product;
import com.test.melnyk.randominputhelper.RandomInputHelper;
import com.test.melnyk.randominputhelper.impl.RandomInputHelperImpl;
import com.test.melnyk.repository.Cart;
import com.test.melnyk.repository.LastFiveOrderInCart;
import com.test.melnyk.service.ItemService;
import com.test.melnyk.service.OrderService;
import com.test.melnyk.service.serviceImpl.ItemServiceImpl;
import com.test.melnyk.service.serviceImpl.OrderServiceImpl;
import com.test.melnyk.strategy.ProductFactory;
import com.test.melnyk.strategy.Strategy;
import com.test.melnyk.strategy.consolestrategy.ConsoleStrategy;
import com.test.melnyk.strategy.consolestrategy.annotationConsoleStrategy.AnnotationConsoleElectricalEngineeringFactory;
import com.test.melnyk.strategy.consolestrategy.annotationConsoleStrategy.AnnotationConsoleProductFactory;
import com.test.melnyk.strategy.consolestrategy.annotationConsoleStrategy.AnnotationConsoleTeapotFactory;
import com.test.melnyk.strategy.consolestrategy.strategyConsoleFactory.ElectricalFactoryForConsoleStrategy;
import com.test.melnyk.strategy.consolestrategy.strategyConsoleFactory.ProductFactoryForConsoleStrategy;
import com.test.melnyk.strategy.consolestrategy.strategyConsoleFactory.TeapotFactoryForConsoleStrategy;
import com.test.melnyk.strategy.randomstrategy.RandomStrategy;
import com.test.melnyk.strategy.randomstrategy.annotationRandomStrategy.AnnotationRandomElectricalEngineeringFactory;
import com.test.melnyk.strategy.randomstrategy.annotationRandomStrategy.AnnotationRandomProductFactory;
import com.test.melnyk.strategy.randomstrategy.annotationRandomStrategy.AnnotationRandomTeapotFactory;
import com.test.melnyk.strategy.randomstrategy.strategyRandomFactory.ProductFactoryForRandomStrategy;
import com.test.melnyk.strategy.randomstrategy.strategyRandomFactory.RandomStrategyForElectricalEngineeringItem;
import com.test.melnyk.strategy.randomstrategy.strategyRandomFactory.RandomStrategyForTeapotItem;
import com.test.melnyk.util.Loader;
import com.test.melnyk.util.PrinterWrapper;
import com.test.melnyk.consts.Constants;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;

public class ApplicationContext {

    private Map<String, Command> commands;
    private ItemDao itemDao;
    private OrderDao orderDao;
    private ItemService itemService;
    private OrderService orderService;
    private List<Product> productList;
    private Cart cart;
    private LastFiveOrderInCart lastFiveOrderInCart;
    private TreeMap<LocalDateTime, Map<Product, Integer>> orderMap;
    private PrinterWrapper printerWrapper;
    private Strategy strategy;
    private Scanner scanner;
    private Loader loader;
    private RandomInputHelper randomInputHelper;

    public ApplicationContext() {
        productList = getLoader().loadDataToList();
    }

    public Map<String, Command> getCommandContainer() {
        if (commands == null) {
            commands = new LinkedHashMap<>();
            commands.put("1", new ShowListProductCommand(getItemService()));
            commands.put("2", new CreateOrderCommand(getOrderService()));
            commands.put("3", new ShowCartCommand(getItemService()));
            commands.put("4", new GetLastFiveItemInCartCommand(getItemService()));
            commands.put("5", new AddProductToCartCommand(getItemService(), getPrinterWrapper(), getScanner()));
            commands.put("6", new ShowAllOrdersCommand(getOrderService()));
            commands.put("7", new BuyAllProductsFromCartCommand(getOrderService()));
            commands.put("8", new ShowAllOrdersInSpecifiedPeriodOfTimeCommand(getOrderService(), getPrinterWrapper(), getScanner()));
            commands.put("9", new GetOrderByNearestDateCommand(getOrderService(), getPrinterWrapper()));
            commands.put("10", new AddProductToListCommand(getItemService(), getPrinterWrapper(), getStrategy()));
            commands.put("stop", new ExitCommand(getItemService()));
        }
        return commands;
    }

    public Map<String, Strategy> getStrategyMap() {
        Map<String, Strategy> strategyMap = new HashMap<>();
        strategyMap.put(Constants.MAP_KEY_ONE, new ConsoleStrategy(getPrinterWrapper(), getScanner(), getConsoleStrategyMap()));
        strategyMap.put(Constants.MAP_KEY_TWO, new ConsoleStrategy(getPrinterWrapper(), getScanner(), getConsoleAnnotationStrategyMap()));
        strategyMap.put(Constants.MAP_KEY_THREE, new RandomStrategy(getPrinterWrapper(), getScanner(), getRandomAnnotationStrategyMap()));
        strategyMap.put(Constants.MAP_KEY_FOUR, new RandomStrategy(getPrinterWrapper(), getScanner(), getRandomStrategyMap()));
        return strategyMap;
    }

    public Map<String, ProductFactory> getRandomStrategyMap() {
        Map<String, ProductFactory> randomStrategyMap = new HashMap<>();
        randomStrategyMap.put(Constants.MAP_KEY_ONE, new ProductFactoryForRandomStrategy());
        randomStrategyMap.put(Constants.MAP_KEY_TWO, new RandomStrategyForElectricalEngineeringItem());
        randomStrategyMap.put(Constants.MAP_KEY_THREE, new RandomStrategyForTeapotItem());
        return randomStrategyMap;
    }

    public Map<String, ProductFactory> getConsoleStrategyMap() {
        Map<String, ProductFactory> map = new HashMap<>();
        map.put(Constants.MAP_KEY_ONE, new ProductFactoryForConsoleStrategy(getScanner(), getPrinterWrapper()));
        map.put(Constants.MAP_KEY_TWO, new ElectricalFactoryForConsoleStrategy(getScanner(), getPrinterWrapper()));
        map.put(Constants.MAP_KEY_THREE, new TeapotFactoryForConsoleStrategy(getScanner(), getPrinterWrapper()));
        return map;
    }

    public Map<String, ProductFactory> getRandomAnnotationStrategyMap() {
        Map<String, ProductFactory> randomAnnotationFactoryMap = new HashMap<>();
        randomAnnotationFactoryMap.put(Constants.MAP_KEY_ONE, new AnnotationRandomProductFactory(getRandomInputHelper()));
        randomAnnotationFactoryMap.put(Constants.MAP_KEY_TWO, new AnnotationRandomElectricalEngineeringFactory(getRandomInputHelper()));
        randomAnnotationFactoryMap.put(Constants.MAP_KEY_THREE, new AnnotationRandomTeapotFactory(getRandomInputHelper()));
        return randomAnnotationFactoryMap;
    }

    public Map<String, ProductFactory> getConsoleAnnotationStrategyMap() {
        Map<String, ProductFactory> consoleAnnotationFactoryMap = new HashMap<>();
        consoleAnnotationFactoryMap.put(Constants.MAP_KEY_ONE, new AnnotationConsoleProductFactory(getScanner(), getPrinterWrapper()));
        consoleAnnotationFactoryMap.put(Constants.MAP_KEY_TWO, new AnnotationConsoleElectricalEngineeringFactory(getScanner(), getPrinterWrapper()));
        consoleAnnotationFactoryMap.put(Constants.MAP_KEY_THREE, new AnnotationConsoleTeapotFactory(getScanner(), getPrinterWrapper()));
        return consoleAnnotationFactoryMap;
    }

    public ItemDao getItemDao() {
        if (itemDao == null) {
            itemDao = new ItemDaoImpl(productList);
        }
        return itemDao;
    }

    public OrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new OrderDaoImpl(getOrderMap(), getCart());
        }
        return orderDao;
    }

    public ItemService getItemService() {
        if (itemService == null) {
            itemService = new ItemServiceImpl(getCart(), getItemDao(), getLastFiveOrderInCart());
        }
        return itemService;
    }

    public OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderServiceImpl(getOrderDao(), getCart());
        }
        return orderService;
    }

    public Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    public TreeMap<LocalDateTime, Map<Product, Integer>> getOrderMap() {
        if (orderMap == null) {
            orderMap = new TreeMap<>();
        }
        return orderMap;
    }

    public LastFiveOrderInCart getLastFiveOrderInCart() {
        if (lastFiveOrderInCart == null) {
            lastFiveOrderInCart = new LastFiveOrderInCart();
        }
        return lastFiveOrderInCart;
    }

    public PrinterWrapper getPrinterWrapper() {
        if (printerWrapper == null) {
            printerWrapper = new PrinterWrapper(System.out);
        }
        return printerWrapper;
    }

    public Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public Loader getLoader() {
        if (loader == null) {
            loader = new Loader(getItemDao());
        }
        return loader;
    }

    public RandomInputHelper getRandomInputHelper() {
        if (Objects.nonNull(randomInputHelper)) {
            randomInputHelper = new RandomInputHelperImpl();
        }
        return randomInputHelper;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}



