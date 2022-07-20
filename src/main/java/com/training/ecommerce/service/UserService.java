package com.training.ecommerce.service;

import com.training.ecommerce.model.Item;
import com.training.ecommerce.model.User;
import com.training.ecommerce.model.request.*;
import com.training.ecommerce.model.response.*;
import com.training.ecommerce.repository.IItemRepository;
import com.training.ecommerce.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IItemRepository itemRepository;

    @Value("${pepper}")
    private String pepper;


    public SignupResponse register(SignupRequest signupRequest) {
        SignupResponse signupResponse = new SignupResponse();

        //Check if user exists.
        User existingUser = userRepository.findByEmail(signupRequest.getEmail());
        if(existingUser!=null)  {
            signupResponse.setSuccess(false);
            signupResponse.setMsg("User already exists.");
            return signupResponse;
        }

        //Create user from SignUpRequest
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());

        //Generate salt.
        String salt = BCrypt.gensalt();
        System.out.println("salt = "+salt);
        user.setSalt(salt);

        //Hash the password.
        String hashedPassword = BCrypt.hashpw(user.getPassword() + pepper, salt);
        user.setPassword(hashedPassword);

        //Save in the db.
        userRepository.save(user);

        //Return response.
        signupResponse.setSuccess(true);
        signupResponse.setMsg("Signup successful.");
        return signupResponse;
    }

    public LoginResponse authenticate(LoginRequest loginRequest)  {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        LoginResponse loginResponse = new LoginResponse();

        //Check if user exists or not
        if(user==null)  {
            loginResponse.setSuccess(false);
            loginResponse.setMsg("User not found. Please signup.");
            return loginResponse;
        }

        //Generate Hashed Password
        String salt = user.getSalt();
        System.out.println("salt = "+salt);
        String hashedPassword = user.getPassword();
        String generatedHashedPassword = BCrypt.hashpw(loginRequest.getPassword() + pepper, salt);
        System.out.println("generatedHashedPassword = "+generatedHashedPassword);

        //Compare Password
        if(!Objects.equals(hashedPassword, generatedHashedPassword))   {
            loginResponse.setSuccess(false);
            loginResponse.setMsg("Invalid password. Please try again.");
            return loginResponse;
        }
        loginResponse.setSuccess(true);
        loginResponse.setMsg("Login successful.");
        return loginResponse;
    }

    public SearchResponse search(SearchRequest searchRequest)   {
        SearchResponse searchResponse = new SearchResponse();
        List<Item> searchResult = itemRepository.findByNameContainingIgnoreCase(searchRequest.getQuery());
        if(searchResult.isEmpty())  {
            searchResponse.setSuccess(false);
            searchResponse.setMsg("Couldn't find any item with given query. Please try again.");
            searchResponse.setSearchResults(searchResult);
            return searchResponse;
        }

        searchResponse.setSuccess(true);
        searchResponse.setMsg("Success! Found items for your query.");
        searchResponse.setSearchResults(searchResult);
        return searchResponse;
    }

    public AddToCartResponse addToCart(AddToCartRequest addToCartRequest)   {
        return new AddToCartResponse();
    }

    public BuyResponse buy(BuyRequest buyRequest) {
        return new BuyResponse();
    }
}
