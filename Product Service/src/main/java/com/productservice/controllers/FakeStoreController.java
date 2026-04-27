package com.productservice.controllers;


import com.productservice.dtos.FakeStoreProductDto;
import com.productservice.dtos.FakeStoreProductRequestDto;
import com.productservice.exceptions.exceptions.FakeStoreException;
import com.productservice.services.FakeStoreProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/fake-store")
public class FakeStoreController {
    private final FakeStoreProductService productService;

    public FakeStoreController(FakeStoreProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<FakeStoreProductDto> getSingleProduct (@PathVariable("productId") int id) throws FakeStoreException {
        return ResponseEntity.ok(productService.getSingleProduct(id));
    }

    @GetMapping ("/products")
    public ResponseEntity<List<FakeStoreProductDto>> getAllProducts() throws FakeStoreException {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<FakeStoreProductDto> addProduct(@RequestBody FakeStoreProductRequestDto fakeStoreProductRequestDto) throws FakeStoreException {
        return new ResponseEntity<>(productService.addProduct(fakeStoreProductRequestDto),HttpStatus.CREATED);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<FakeStoreProductDto> updateProduct(@PathVariable("productId") int id, @RequestBody FakeStoreProductRequestDto fakeStoreProductRequestDto) throws FakeStoreException {
       return ResponseEntity.ok(productService.updateProduct(id, fakeStoreProductRequestDto));
    }

    @DeleteMapping ("/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") int id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("The Product with ID: " + id + " has been deleted Successfully");
    }
}
