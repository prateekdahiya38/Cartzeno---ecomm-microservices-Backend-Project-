package com.productservice.controllers;

import com.productservice.dtos.FakeStoreProductDto;
import com.productservice.dtos.ProductDto;
import com.productservice.exceptions.exceptions.FakeStoreException;
import com.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/fake-store")
public class FakeStoreController {
    private final ProductService productService;

    public FakeStoreController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct (@PathVariable("productId") int id) throws FakeStoreException {
        return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
    }

    @GetMapping ("/products")
    public List<ProductDto> getAllProducts() throws FakeStoreException {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) throws FakeStoreException {
        return new ResponseEntity<>(productService.addProduct(productDto),HttpStatus.OK);
    }
}
