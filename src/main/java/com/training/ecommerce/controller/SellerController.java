package com.training.ecommerce.controller;

import com.training.ecommerce.model.request.SellRequest;
import com.training.ecommerce.model.response.SellResponse;
import com.training.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping(value = "/sell", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SellResponse> sell(@RequestBody SellRequest sellRequest)  {
        SellResponse sellResponse = sellerService.sell(sellRequest);
        return sellResponse.isSuccess()
                ? new ResponseEntity<>(sellResponse, HttpStatus.OK)
                : new ResponseEntity<>(sellResponse, HttpStatus.BAD_REQUEST);
    }
}
