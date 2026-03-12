package com.product.InventoryAPI.Exceptions;

public class InvalidSkuFormatException extends RuntimeException {

    public InvalidSkuFormatException(String message) {
        super(message);
    }
}