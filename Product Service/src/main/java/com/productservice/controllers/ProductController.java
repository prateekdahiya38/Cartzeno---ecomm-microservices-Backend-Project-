package com.productservice.controllers;

import com.productservice.dtos.ProductRequestSelfDto;
import com.productservice.dtos.ProductResponseSelfDto;
import com.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseSelfDto> getSingleProduct(@PathVariable("productId") UUID productId) {
        return ResponseEntity.ok(productService.getSingleProduct(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseSelfDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getProductList());
    }

    @PostMapping
    public ResponseEntity<ProductResponseSelfDto> addProduct(@RequestBody ProductRequestSelfDto productRequestSelfDto){
        return new ResponseEntity<>(productService.addProduct(productRequestSelfDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponseSelfDto> updateProduct(@PathVariable ("productId") UUID id,@RequestBody ProductRequestSelfDto productRequestSelfDto){
        return ResponseEntity.ok(productService.updateProduct(id,productRequestSelfDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId")UUID productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("The product with ID " +  productId + " has been deleted Successfully");
    }
}
