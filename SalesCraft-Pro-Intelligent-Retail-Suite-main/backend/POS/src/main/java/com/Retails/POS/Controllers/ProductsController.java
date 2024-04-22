package com.Retails.POS.Controllers;

import com.Retails.POS.Models.Products;
import com.Retails.POS.Services.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/")
public class ProductsController {

    @Autowired
    private ProductsServices productsServices;

    // retrieve all products
    @GetMapping(value = "/product/")
    public ResponseEntity<List<Products>> getProducts(){
        List<Products> productList = productsServices.getAllProducts();
        return ResponseEntity.ok(productList);
    }


    //retrieve the product by productname --> http://localhost:8080/api/product/search?productName=Toner
    @GetMapping(value = "/product/search")
    public ResponseEntity<List<Products>> getProductByName(@RequestParam(name = "productName") String productName){
        List<Products> productList = productsServices.findByProductName(productName);
        return ResponseEntity.ok(productList);

    }
    //retrieve all product by id
    @GetMapping(value = "/product/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable String id){
        Products product = productsServices.getProductById(id);
        return ResponseEntity.ok(product);
    }

    //add new product
    @PostMapping(value = "/product/")
    public ResponseEntity<String> saveProduct(@RequestBody Products products){
        productsServices.saveProduct(products);
        return ResponseEntity.ok(productsServices.toString());
    }

    //update product by id
    @PutMapping(value = "/product/{id}")
    public ResponseEntity<Products> updateProduct(@RequestBody Products products, @PathVariable String id){
        products.setId(id);
        productsServices.saveProduct(products);
        return ResponseEntity.ok(products);
    }

    //delete product by id
    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id){
        productsServices.deleteProduct(id);
        return ResponseEntity.ok("Ok");
    }


}