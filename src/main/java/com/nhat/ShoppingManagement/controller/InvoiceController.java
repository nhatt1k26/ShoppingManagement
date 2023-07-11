package com.nhat.ShoppingManagement.controller;

import com.nhat.ShoppingManagement.model.response.InvoiceResponse;
import com.nhat.ShoppingManagement.service.interfaceService.InvoiceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoice")
public class InvoiceController {
    private final Logger logger = LoggerFactory.getLogger(CustomerCartController.class);
    private final InvoiceService invoiceService;
    private final ModelMapper modelMapper;


    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<InvoiceResponse>> findAll(){
        return ResponseEntity.ok(invoiceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> findInvoice(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(invoiceService.findById(id), HttpStatus.OK);
    }

//    @PostMapping("/addInvoice")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> addInvoice(
//            @RequestBody InvoiceDTO newInvoice
//    )
//    {
//        invoiceService.addInvoice(newInvoice);
//        return new ResponseEntity<>("Add new invoice successful", HttpStatus.CREATED);
//    }




//    @PutMapping("/updateInvoice/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> updateInvoice(
//            @PathVariable Long id,
//            @RequestBody InvoiceDTO newInvoice
//    )
//    {
//        invoiceService.updateInvoice(id,newInvoice);
//        return new ResponseEntity<>("Update new invoice successful", HttpStatus.CREATED);
//    }


    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteById(
            @PathVariable Long id
    )
    {
        invoiceService.deleteInvoice(id);
        return new ResponseEntity<>("Deleted invoice",HttpStatus.OK);
    }
}
