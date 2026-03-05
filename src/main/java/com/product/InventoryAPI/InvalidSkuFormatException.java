package com.product.InventoryAPI;

public class InvalidSkuFormatException extends RuntimeException {

    public InvalidSkuFormatException(String message) {
        super(message);
    }
}