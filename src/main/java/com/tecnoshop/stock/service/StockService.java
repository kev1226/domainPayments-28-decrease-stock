package com.tecnoshop.stock.service;

import com.tecnoshop.stock.dto.DecreaseStockRequest;
import com.tecnoshop.stock.dto.StockItem;
import com.tecnoshop.stock.entity.Product;
import com.tecnoshop.stock.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final ProductRepository productRepository;

    public StockService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void decreaseStock(DecreaseStockRequest request) {
        for (StockItem item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Producto no encontrado: " + item.getProductId()));

            if (product.getStock() < item.getQuantity()) {
                throw new IllegalArgumentException(
                        "Stock insuficiente para producto ID " + product.getId());
            }

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }
    }

    // NUEVO método para revertir stock en caso de error
    @Transactional
    public void restoreStock(DecreaseStockRequest request) {
        for (StockItem item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Producto no encontrado: " + item.getProductId()));

            product.setStock(product.getStock() + item.getQuantity());
            productRepository.save(product);
        }
    }

    @Transactional(readOnly = true)
    public void checkStock(DecreaseStockRequest request) {
        for (StockItem item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Producto no encontrado: " + item.getProductId()));

            if (product.getStock() < item.getQuantity()) {
                throw new IllegalArgumentException(
                        "Stock insuficiente para producto ID " + product.getId());
            }
        }
    }

}
