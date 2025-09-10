package com.it.Fangwen;

import java.util.ArrayList;
import java.util.List;

// 访问者接口
interface Visitor {
    void visit(Book book);
    void visit(Fruit fruit);
}

// 元素接口
interface Element {
    void accept(Visitor visitor);
}

// 具体元素：书
class Book implements Element {
    private double price;

    public Book(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// 具体元素：水果
class Fruit implements Element {
    private double weight;

    public Fruit(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// 具体访问者：计算价格
class PriceCalculator implements Visitor {
    private double totalPrice = 0;

    @Override
    public void visit(Book book) {
        totalPrice += book.getPrice() * 0.9; // 书打九折
    }

    @Override
    public void visit(Fruit fruit) {
        totalPrice += fruit.getWeight() * 5; // 水果每斤5元
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

// 对象结构
class ShoppingCart {
    private List<Element> items = new ArrayList<>();

    public void addItem(Element item) {
        items.add(item);
    }

    public double calculateTotal(Visitor visitor) {
        for (Element item : items) {
            item.accept(visitor);
        }
        return ((PriceCalculator) visitor).getTotalPrice();
    }
}

// 使用示例
public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Book(100));
        cart.addItem(new Fruit(2.5));

        PriceCalculator calculator = new PriceCalculator();
        double total = cart.calculateTotal(calculator);
        System.out.println("总价：" + total + "元");
    }
}