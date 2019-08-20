package com.wallet.chain.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static <T> List<T> list(T... args) {
        List<T> list = new ArrayList<>();
        for (T arg : args) {
            list.add(arg);
        }
        return list;
    }
}
