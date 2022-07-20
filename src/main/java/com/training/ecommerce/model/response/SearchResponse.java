package com.training.ecommerce.model.response;

import com.training.ecommerce.model.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResponse extends Response{
    List<Item> searchResults;
}
