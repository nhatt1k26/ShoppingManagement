package com.nhat.ShoppingManagement.controller;

import com.nhat.ShoppingManagement.model.dto.ProductDTO;
import com.nhat.ShoppingManagement.model.response.ProductDataResponse;
import com.nhat.ShoppingManagement.service.interfaceService.ProductDataService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/productData")
public class ProductDataController {
    private final Logger logger = LoggerFactory.getLogger(CustomerCartController.class);
    private final ProductDataService productDataService;
    private final ModelMapper modelMapper;


    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";


    @GetMapping("/uploadimage")
    public String displayUploadForm() {
        return "imageupload/index";
    }

    @PostMapping("/upload") public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
        return "imageupload/index";
    }

    @GetMapping("/all")
//    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAllProductData(){
        logger.info("Find all product is call!");
        return ResponseEntity.ok(productDataService.findAll());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDataResponse> findById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(productDataService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductDataResponse>> findByProductId(
            @PathVariable Long productId
    ){
        return new ResponseEntity<>(productDataService.findByProductId(productId), HttpStatus.OK);
    }



    @PostMapping("/product/{productId}/uploadProductData/")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadProductData(
            @PathVariable Long productId,
            @RequestBody MultipartFile multipartFile
    )
    {
        productDataService.uploadProductData(productId,multipartFile);
        return new ResponseEntity<>("Add new product successful", HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{productDataId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteById(
            @PathVariable Long productDataId
    )
    {
        productDataService.deleteProductData(productDataId);
        return new ResponseEntity<>("Deleted product",HttpStatus.OK);
    }
}