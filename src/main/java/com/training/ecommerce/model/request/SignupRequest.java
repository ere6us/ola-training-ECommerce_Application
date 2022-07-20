package com.training.ecommerce.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest extends Request{
    private String name;
    private String email;
    private String password;
}
