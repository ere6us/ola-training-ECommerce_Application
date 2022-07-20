package com.training.ecommerce.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellRequest extends Request{
    private String name;
    private double mrp;
    private String color;
}
