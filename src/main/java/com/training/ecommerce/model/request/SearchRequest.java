package com.training.ecommerce.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest extends Request{
    String query;
}
