package com.hitanshudhawan.pojopreferences;

import com.hitanshudhawan.annotations.Pojo;
import com.hitanshudhawan.annotations.Pref;

@Pojo
public class Data {
    @Pref
    private final boolean a = true;
    @Pref
    private final float b = 98.6F;
    @Pref
    private int c;
    @Pref
    private final long d = 123456789L;
    @Pref
    private final String e = "Hitanshu";
}