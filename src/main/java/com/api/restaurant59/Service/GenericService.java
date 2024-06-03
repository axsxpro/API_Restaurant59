package com.api.restaurant59.Service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface GenericService<T, ID> {

    T create(T EntityDto);

    List<T> readAll();

    //readAll with pagination
    Page<T> readAll(int page, int size);

    T getById(ID id);

    T update(ID id, T EntityDto);

    void deleteById(ID id);

}
