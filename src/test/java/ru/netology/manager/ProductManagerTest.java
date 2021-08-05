package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Product first = new Book(1, "Тестирование ПО. Базовый курс", 1000, "Святослав Куликов");
    private Product second = new Book(2, "Тестирование dot com", 950, "Роман Савин");
    private Product third = new Book(3, "Тестирование черного ящика", 1100, "Борис Бейзер");
    private Product fourth = new Smartphone(4, "Iphone 11 128 Black", 55000, "Apple");
    private Product fifth = new Smartphone(5, "Galaxy S21 5G 256 Silver", 96000, "Samsung");
    private Product sixth = new Smartphone(6, "Mi 10T Pro 256", 44000, "Xiaomi");
    private Product seventh = new Smartphone(7,"Iphone 11 64 White", 50000,"Apple");


    @BeforeEach
    public void addProducts() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
        manager.add(seventh);
    }

    @Test
    public void searchBookByAuthor() {
        Product[] actual = manager.searchBy("Святослав Куликов");
        Product[] expected = new Product[]{first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchBookByName() {
        Product[] actual = manager.searchBy("Тестирование черного ящика");
        Product[] expected = new Product[]{third};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchPhoneByName() {
        Product[] actual = manager.searchBy("Iphone 11 128 Black");
        Product[] expected = new Product[]{fourth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchPhoneByManufacturer() {
        Product[] actual = manager.searchBy("Xiaomi");
        Product[] expected = new Product[]{sixth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchBookByNotExistingName() {
        Product[] actual = manager.searchBy("Война и мир");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected,actual);
    }

    @Test
    public void searchBookByNotExistingAuthor() {
        Product[] actual = manager.searchBy("Лев Николаевич Толстой");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected,actual);
    }

    @Test
    public void searchPhoneByNotExistingName() {
        Product[] actual = manager.searchBy("Huawei P40 Pro");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected,actual);
    }

    @Test
    public void searchPhoneByNotExistingManufacturer() {
        Product[] actual = manager.searchBy("Nokia");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected,actual);
    }

    @Test
    public void searchMultiplePhones() {
        Product[] actual = manager.searchBy("Iph");
        Product[] expected = new Product[]{fourth,seventh};
        assertArrayEquals(expected,actual);
    }

    @Test
    public void searchMultipleBooks() {
        Product[] actual = manager.searchBy("Тестирование");
        Product[] expected = new Product[]{first,second,third};
        assertArrayEquals(expected,actual);
    }



}