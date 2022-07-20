package com.training.ecommerce.service;

import com.training.ecommerce.model.Item;
import com.training.ecommerce.model.request.SellRequest;
import com.training.ecommerce.model.response.SellResponse;
import com.training.ecommerce.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    IItemRepository itemRepository;

    public SellResponse sell(SellRequest sellRequest)  {
        SellResponse sellResponse = new SellResponse();

        Item item = new Item();
        item.setName(sellRequest.getName());
        item.setColor(sellRequest.getColor());
        item.setMrp(sellRequest.getMrp());
        item.setSold(false);

        itemRepository.save(item);
        sellResponse.setSuccess(true);
        sellResponse.setMsg("Item up for sale successfully!");
        return sellResponse; //Add item in sellResponse and pass that too.
    }
}
