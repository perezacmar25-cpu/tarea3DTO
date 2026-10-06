package com.salesianos.dam.primerjemplo.config;

import com.salesianos.dam.primerjemplo.model.Category;
import com.salesianos.dam.primerjemplo.model.Product;
import com.salesianos.dam.primerjemplo.repo.CategoryRepository;
import com.salesianos.dam.primerjemplo.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor

public class DataSeed implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        // 1. Crear e insertar categorías de ejemplo
        Category catElectronics = Category.builder()
                .name("Electrónica")
                .build();

        Category catHome = Category.builder()
                .name("Hogar")
                .build();

        Category catBooks = Category.builder()
                .name("Libros")
                .build();

        categoryRepository.save(catElectronics);
        categoryRepository.save(catHome);
        categoryRepository.save(catBooks);

        // 2. Crear e insertar productos de ejemplo asociados a las categorías
        Product p1 = Product.builder()
                .name("Smartphone Pro Max")
                .price(999.99)
                .details("Pantalla OLED 6.7 pulgadas, 256GB storage")
                .category(catElectronics)
                .createdAt(LocalDateTime.now())
                .build();

        Product p2 = Product.builder()
                .name("Auriculares Inalámbricos")
                .price(149.50)
                .details("Cancelación de ruido activa y batería de 30 horas")
                .category(catElectronics)
                .createdAt(LocalDateTime.now())
                .build();

        Product p3 = Product.builder()
                .name("Cafetera Express")
                .price(89.90)
                .details("15 bares de presión y espumador de leche")
                .category(catHome)
                .createdAt(LocalDateTime.now())
                .build();

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
    }
}
