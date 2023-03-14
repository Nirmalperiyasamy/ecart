package com.example.product.util;

public enum Messages {
    INSUFFICIENT_BALANCE {
        public String toString() {
            return "Insufficient balance in your account ";
        }
    }, SOLD {
        public String toString() {
            return "product sold out";
        }
    }
}
