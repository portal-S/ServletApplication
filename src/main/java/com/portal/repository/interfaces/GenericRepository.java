package com.portal.repository.interfaces;

import java.util.List;

public interface GenericRepository <T, ID>{
    public List<T> readAll();

    public T read(ID id);

    public T create(T t);

    public T update(T t);

    public boolean delete(ID id);
}
