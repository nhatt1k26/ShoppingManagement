package com.nhat.ShoppingManagement.service;

import com.nhat.ShoppingManagement.model.entity.CustomerCart;
import com.nhat.ShoppingManagement.model.entity.Invoice;
import com.nhat.ShoppingManagement.model.entity.InvoiceDetail;
import com.nhat.ShoppingManagement.model.response.InvoiceResponse;
import com.nhat.ShoppingManagement.repository.InvoiceDetailRepository;
import com.nhat.ShoppingManagement.repository.InvoiceRepository;
import com.nhat.ShoppingManagement.security.models.User;
import com.nhat.ShoppingManagement.service.interfaceService.InvoiceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    InvoiceRepository invoiceRepository;
    InvoiceDetailRepository invoiceDetailRepository;
    ModelMapper modelMapper;
    @Override
    public boolean isEmpty() {
        return invoiceRepository.findAll().isEmpty();
    }

    @Override
    public List<InvoiceResponse> findAll() {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        System.out.println(invoiceList);
        if (!invoiceList.isEmpty())
        {
            return invoiceList.stream().map( s ->
                    modelMapper.map(s,InvoiceResponse.class)
            ).collect(Collectors.toList());
        }
        else return new ArrayList<>();
    }

    @Override
    public InvoiceResponse findById(Long id) {
        return modelMapper.map(invoiceRepository.findById(id),InvoiceResponse.class);
    }

    @Override
    public void createInvoice(User user, List<CustomerCart> customerCartList){
        Invoice invoiceToCreate = new Invoice();
        invoiceToCreate.setUser(user);
        List<InvoiceDetail> invoiceDetailList = customerCartList.stream().map(s ->
                {
                    InvoiceDetail invoiceDetail= new InvoiceDetail(s.getProduct(), s.getItemQuantity(),s.getProduct().getPrice());
                    invoiceDetailRepository.save(invoiceDetail);
                    return invoiceDetail;
                }).collect(Collectors.toList());
        invoiceToCreate.setInvoiceDetail(invoiceDetailList);
    };

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
