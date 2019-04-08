package com.hitanshudhawan.pojopreferences;

import com.hitanshudhawan.annotations.Pojo;
import com.hitanshudhawan.annotations.Pref;

@Pojo
public class Data {
    @Pref
    private boolean a;
    @Pref
    private Boolean b;
    @Pref
    private float c;
    @Pref
    private Float d;
    @Pref
    private int e;
    @Pref
    private Integer f;
    @Pref
    private long g;
    @Pref
    private Long h;
    @Pref
    private String i;
    //
    private Short j;
}
