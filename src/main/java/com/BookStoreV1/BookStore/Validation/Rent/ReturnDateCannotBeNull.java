package com.BookStoreV1.BookStore.Validation.Rent;

public class ReturnDateCannotBeNull extends RuntimeException{
    public ReturnDateCannotBeNull(String Message){
        super(Message);

    }
}
