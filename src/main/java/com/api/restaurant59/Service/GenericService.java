package com.api.restaurant59.Service;

import java.util.List;

public interface GenericService<T, ID> {

    T create(T EntityDto);

    List<T> readAll();

    T getById(ID id);

    T update(ID id, T EntityDto);

    void deleteById(ID id);

}
