package com.tecnoshop.stock.controller;

import com.tecnoshop.stock.dto.DecreaseStockRequest;
import com.tecnoshop.stock.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/decrease")
    public ResponseEntity<?> decrease(@RequestBody DecreaseStockRequest request) {
        try {
            stockService.decreaseStock(request);
            return ResponseEntity.ok("Stock actualizado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno al actualizar stock");
        }
    }

    // NUEVO endpoint para revertir (sumar) stock
    @PostMapping("/restore")
    public ResponseEntity<?> restore(@RequestBody DecreaseStockRequest request) {
        try {
            stockService.restoreStock(request);
            return ResponseEntity.ok("Stock restaurado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno al restaurar stock");
        }
    }

    @PostMapping("/check")
    public ResponseEntity<?> check(@RequestBody DecreaseStockRequest request) {
        try {
            stockService.checkStock(request);
            return ResponseEntity.ok("Stock suficiente ");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno al verificar stock");
        }
    }

}
