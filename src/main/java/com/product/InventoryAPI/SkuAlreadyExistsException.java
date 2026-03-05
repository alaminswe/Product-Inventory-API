package com.product.InventoryAPI;

public class SkuAlreadyExistsException extends RuntimeException {

    public SkuAlreadyExistsException(String message) {
        super(message);
    }
}