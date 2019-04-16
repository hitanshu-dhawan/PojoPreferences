package com.hitanshudhawan.compiler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrefUtilsTest {

    private VariableElement mockedVariableElement;
    private TypeMirror mockedTypeMirror;

    @Before
    public void setUp() throws Exception {
        mockedVariableElement = mock(VariableElement.class);
        mockedTypeMirror = mock(TypeMirror.class);
    }

    @After
    public void tearDown() throws Exception {
        //
    }

    @Test
    public void testPrimitiveBoolean() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(boolean.class.getCanonicalName());

        assertTrue(PrefUtils.isVariableSupported(mockedVariableElement));
        assertEquals(PrefUtils.getSharedPreferencesMethodName(mockedVariableElement), "Boolean");
        assertEquals(PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement), "false");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPrimitiveByte() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(byte.class.getCanonicalName());

        assertFalse(PrefUtils.isVariableSupported(mockedVariableElement));
        PrefUtils.getSharedPreferencesMethodName(mockedVariableElement);
        PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPrimitiveChar() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(char.class.getCanonicalName());

        assertFalse(PrefUtils.isVariableSupported(mockedVariableElement));
        PrefUtils.getSharedPreferencesMethodName(mockedVariableElement);
        PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPrimitiveShort() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(short.class.getCanonicalName());

        assertFalse(PrefUtils.isVariableSupported(mockedVariableElement));
        PrefUtils.getSharedPreferencesMethodName(mockedVariableElement);
        PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement);
    }

    @Test
    public void testPrimitiveInt() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(int.class.getCanonicalName());

        assertTrue(PrefUtils.isVariableSupported(mockedVariableElement));
        assertEquals(PrefUtils.getSharedPreferencesMethodName(mockedVariableElement), "Int");
        assertEquals(PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement), "0");
    }

    @Test
    public void testPrimitiveLong() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(long.class.getCanonicalName());

        assertTrue(PrefUtils.isVariableSupported(mockedVariableElement));
        assertEquals(PrefUtils.getSharedPreferencesMethodName(mockedVariableElement), "Long");
        assertEquals(PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement), "0L");
    }

    @Test
    public void testPrimitiveFloat() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(float.class.getCanonicalName());

        assertTrue(PrefUtils.isVariableSupported(mockedVariableElement));
        assertEquals(PrefUtils.getSharedPreferencesMethodName(mockedVariableElement), "Float");
        assertEquals(PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement), "0.0F");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPrimitiveDouble() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(double.class.getCanonicalName());

        assertFalse(PrefUtils.isVariableSupported(mockedVariableElement));
        PrefUtils.getSharedPreferencesMethodName(mockedVariableElement);
        PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testBooleanObject() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(Boolean.class.getCanonicalName());

        assertFalse(PrefUtils.isVariableSupported(mockedVariableElement));
        PrefUtils.getSharedPreferencesMethodName(mockedVariableElement);
        PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testByteObject() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(Byte.class.getCanonicalName());

        assertFalse(PrefUtils.isVariableSupported(mockedVariableElement));
        PrefUtils.getSharedPreferencesMethodName(mockedVariableElement);
        PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCharacterObject() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(Character.class.getCanonicalName());

        assertFalse(PrefUtils.isVariableSupported(mockedVariableElement));
        PrefUtils.getSharedPreferencesMethodName(mockedVariableElement);
        PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testShortObject() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(Short.class.getCanonicalName());

        assertFalse(PrefUtils.isVariableSupported(mockedVariableElement));
        PrefUtils.getSharedPreferencesMethodName(mockedVariableElement);
        PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIntegerObject() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(Integer.class.getCanonicalName());

        assertFalse(PrefUtils.isVariableSupported(mockedVariableElement));
        PrefUtils.getSharedPreferencesMethodName(mockedVariableElement);
        PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLongObject() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(Long.class.getCanonicalName());

        assertFalse(PrefUtils.isVariableSupported(mockedVariableElement));
        PrefUtils.getSharedPreferencesMethodName(mockedVariableElement);
        PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testFloatObject() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(Float.class.getCanonicalName());

        assertFalse(PrefUtils.isVariableSupported(mockedVariableElement));
        PrefUtils.getSharedPreferencesMethodName(mockedVariableElement);
        PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDoubleObject() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(Double.class.getCanonicalName());

        assertFalse(PrefUtils.isVariableSupported(mockedVariableElement));
        PrefUtils.getSharedPreferencesMethodName(mockedVariableElement);
        PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement);
    }

    @Test
    public void testStringObject() {
        when(mockedVariableElement.asType()).thenReturn(mockedTypeMirror);
        when(mockedTypeMirror.toString()).thenReturn(String.class.getCanonicalName());

        assertTrue(PrefUtils.isVariableSupported(mockedVariableElement));
        assertEquals(PrefUtils.getSharedPreferencesMethodName(mockedVariableElement), "String");
        assertEquals(PrefUtils.getSharedPreferencesDefaultValue(mockedVariableElement), "\"\"");
    }

}