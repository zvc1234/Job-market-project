package com.proiect.lamunca.service;

import java.util.List;

public interface IService<T,ID> {

    boolean add(T element);

    List<T> getAll();

    boolean getById(ID id);

}
