package com.example.user;

public enum Messages {
    NOT_REGISTERED {
        public String toString() {
            return "User not registered";
        }
    },
    INVALID {
        public String toString() {
            return "Invalid username or password";
        }
    },

    USERNAME_EXIST {
        public String toString() {
            return "User already exist";
        }
    }

}
