package com.hitanshudhawan.compiler;

import java.util.Set;

class Variable {
    static String BOOLEAN = boolean.class.getCanonicalName();
    static String FLOAT = float.class.getCanonicalName();
    static String INT = int.class.getCanonicalName();
    static String LONG = long.class.getCanonicalName();
    static String STRING = String.class.getCanonicalName();
    static String STRING_SET = Set.class.getCanonicalName() + "<" + String.class.getCanonicalName() + ">";
}