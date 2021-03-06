package org.itstep.task01;

import org.itstep.task02.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.*;
import java.util.Random;
import java.util.stream.Stream;

class TestTask03 {

    private final String className = "org.itstep.task03.Country";

    @Test
    @Order(1)
    @DisplayName("Проверка наличия класса Country")
    void classExists() throws ClassNotFoundException {
        Class.forName(className);
    }

    @Order(2)
    @ParameterizedTest(name = "{0}")
    @DisplayName("Проверка закрытых полей")
    @ValueSource(strings = {"name", "continent", "code", "capital", "cities"})
    void privateFieldExists(String fieldName) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> cls = Class.forName(className);
        Field fullName = cls.getDeclaredField(fieldName);
        Assertions.assertTrue(Modifier.isPrivate(fullName.getModifiers()));
    }

    @Order(2)
    @ParameterizedTest(name = "{0}")
    @DisplayName("Проверка геттеров")
    @ValueSource(strings = {"getName", "getContinent", "getCode", "getInhabitants", "getCapital", "getCities"})
    void publicGetterExists(String getterName) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> cls = Class.forName(className);
        Method declaredMethod = cls.getDeclaredMethod(getterName);
        Assertions.assertTrue(Modifier.isPublic(declaredMethod.getModifiers()));
    }

    @Order(3)
    @ParameterizedTest(name = "{1}")
    @DisplayName("Проверка сеттеров")
    @CsvSource({"java.lang.String,setName", "java.lang.String,setContinent", "java.lang.String,setCode",
            "org.itstep.task02.City,setCapital", "org.itstep.task02.City,addCity"})
    void publicSetterExists(Class<?> clazz, String setterName) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> cls = Class.forName(className);
        Method declaredMethod = cls.getDeclaredMethod(setterName, clazz);
        Assertions.assertTrue(Modifier.isPublic(declaredMethod.getModifiers()));
    }

    @Order(4)
    @ParameterizedTest(name = "{1}")
    @DisplayName("Проверка сеттеров/геттеров")
    @CsvSource({"java.lang.String,name", "java.lang.String,continent", "java.lang.String,code",
            "org.itstep.task02.City,capital"})
    void setterGetterWork(Class<?> clazz, String fieldName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(className);
        Object obj = cls.getDeclaredConstructor().newInstance();

        String setterName = String.format("set%c%s", Character.toUpperCase(fieldName.charAt(0)), fieldName.substring(1));
        String getterName = String.format("get%c%s", Character.toUpperCase(fieldName.charAt(0)), fieldName.substring(1));

        Method setter = cls.getDeclaredMethod(setterName, clazz);
        Method getter = cls.getDeclaredMethod(getterName);
        Object expected = getExpected(clazz);
        setter.invoke(obj, expected);
        Object actual = getter.invoke(obj);
        Assertions.assertEquals(expected, actual);
    }

    @Order(5)
    @Test
    @DisplayName("Проверка метода добавления города")
    void addCity() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(className);
        Object obj = cls.getDeclaredConstructor().newInstance();

        Class<?> argClass = Class.forName("org.itstep.task02.City");
        Method setter = cls.getDeclaredMethod("addCity", argClass);

        setter.invoke(obj, getExpected(argClass));
        setter.invoke(obj, getExpected(argClass));
        setter.invoke(obj, getExpected(argClass));
    }

    @Order(6)
    @Test
    @DisplayName("Проверка метода подсчета количества жителей")
    void inhabitantsCount() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(className);
        Object obj = cls.getDeclaredConstructor().newInstance();

        Method setter = cls.getDeclaredMethod("addCity", City.class);
        Assertions.fail("Not implemented yet");
    }

    @Order(7)
    @Test
    @DisplayName("Проверка Конструкторов")
    void constructors() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<?> cls = Class.forName(className);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Assertions.assertEquals(2, declaredConstructors.length);

        Class<String> stringClass = String.class;
        Class<?> cityClass = Class.forName("org.itstep.task02.City");
        Constructor<?> ctor = cls.getDeclaredConstructor(stringClass, stringClass, stringClass, cityClass);

        Object name = getExpected(stringClass);
        Object continent = getExpected(stringClass);
        Object code = getExpected(stringClass);
        Object capital = getExpected(cityClass);

        Object obj = ctor.newInstance(name, continent, code, capital);

        Method getName = cls.getDeclaredMethod("getName");
        Assertions.assertEquals(name, getName.invoke(obj));

        Method getContinent = cls.getDeclaredMethod("getContinent");
        Assertions.assertEquals(continent, getContinent.invoke(obj));

        Method getCode = cls.getDeclaredMethod("getCode");
        Assertions.assertEquals(code, getCode.invoke(obj));

        Method getCapital = cls.getDeclaredMethod("getCapital");
        Assertions.assertSame(capital, getCapital.invoke(obj));
    }


    static Random rnd = new Random();

    static Object getExpected(Class<?> clazz) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cityClass = Class.forName("org.itstep.task02.City");
        if (int.class.equals(clazz)) {
            return rnd.nextInt(800_000);
        } else if (String.class.equals(clazz)) {
            return randomString();
        } else if (cityClass.equals(clazz)) {
            Constructor<?> ctor = clazz.getDeclaredConstructor();
            return ctor.newInstance();
        }
        return null;
    }

    static String randomString() {
        return Stream.generate(() -> rnd.nextInt('z' - 'a' + 1) + 'a')
                .limit(rnd.nextInt(20) + 20)
                .map(i -> (char) i.intValue())
                .map(s -> new String(new char[]{s}))
                .reduce(String::concat).orElse("");
    }
}
