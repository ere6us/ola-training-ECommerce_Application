package com.training.ecommerce.model;

import java.util.List;

public class Buyer extends User{
    private List<Item> cart;
    private List<Item> purchasedItems;
}
