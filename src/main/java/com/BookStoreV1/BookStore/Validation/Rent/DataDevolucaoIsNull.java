package com.BookStoreV1.BookStore.Validation.Rent;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;

public class DataDevolucaoIsNull extends EntityExistsException {
    public DataDevolucaoIsNull(LocalDate Message) {
        super(String.format("Data devolução nãó pode ser definida agora.", Message));
    }
}
