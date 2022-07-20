package com.training.ecommerce.controller;

import com.training.ecommerce.model.request.*;
import com.training.ecommerce.model.response.*;
import com.training.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/signUp", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SignupResponse> signUp(@RequestBody SignupRequest signupRequest) {
        SignupResponse signupResponse = userService.register(signupRequest);
        return signupResponse.isSuccess()
                ? new ResponseEntity<>(signupResponse, HttpStatus.OK)
                : new ResponseEntity<>(signupResponse, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/logIn", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginResponse> logIn(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.authenticate(loginRequest);
        return loginResponse.isSuccess()
                ? new ResponseEntity<>(loginResponse, HttpStatus.OK)
                : new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/search", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SearchResponse> search(@RequestBody SearchRequest searchRequest) {
        SearchResponse searchResponse = userService.search(searchRequest);
        return searchResponse.isSuccess()
                ? new ResponseEntity<>(searchResponse, HttpStatus.OK)
                : new ResponseEntity<>(searchResponse, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/adToCart", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AddToCartResponse> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        AddToCartResponse addToCartResponse = userService.addToCart(addToCartRequest);
        return addToCartResponse.isSuccess()
                ? new ResponseEntity<>(addToCartResponse, HttpStatus.OK)
                : new ResponseEntity<>(addToCartResponse, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/buy", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BuyResponse> buy(@RequestBody BuyRequest buyRequest) {
        BuyResponse buyResponse = userService.buy(buyRequest);
        return buyResponse.isSuccess()
                ? new ResponseEntity<>(buyResponse, HttpStatus.OK)
                : new ResponseEntity<>(buyResponse, HttpStatus.BAD_REQUEST);
    }
}
