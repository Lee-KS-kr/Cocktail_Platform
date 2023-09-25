package com.platform.cocktail.cocktail_platform.api.csv;

public interface Parser<T> {
    T parse(String str);
}
