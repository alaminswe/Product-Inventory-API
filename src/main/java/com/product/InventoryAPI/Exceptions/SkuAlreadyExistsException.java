package com.product.InventoryAPI.Exceptions;

public class SkuAlreadyExistsException extends RuntimeException {

    public SkuAlreadyExistsException(String message) {
        super(message);
    }
}