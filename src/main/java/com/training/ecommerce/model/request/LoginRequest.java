package com.training.ecommerce.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest extends Request{
    private String email;
    private String password;
}
