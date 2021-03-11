package com.example.rest.utils;

public interface Mapper<T, U> {
    U to(T t);
}
