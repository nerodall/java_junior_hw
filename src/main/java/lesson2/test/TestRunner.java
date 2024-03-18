package lesson2.test;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class TestRunner {
    public static void run(Class<?> testClass) {
        final Object testObj = initTestObj(testClass);
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.accessFlags().contains(AccessFlag.PRIVATE)) {
                continue;
            }
            if (method.getAnnotation(BeforeAll.class) != null) {
                try {
                    method.invoke(testObj);

                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        for (Method method : testClass.getDeclaredMethods()) {
            if (method.accessFlags().contains(AccessFlag.PRIVATE)) {
                continue;
            }

            if (method.getAnnotation(Test.class) != null) {

                for (Method method1 : testClass.getDeclaredMethods()){
                    if (method1.accessFlags().contains(AccessFlag.PRIVATE)) {
                        continue;
                    }
                    if (method1.getAnnotation(BeforeEach.class) != null) {
                        try {
                            method1.invoke(testObj);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                try {
                    method.invoke(testObj);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

                for (Method method2 : testClass.getDeclaredMethods()){
                    if (method2.accessFlags().contains(AccessFlag.PRIVATE)) {
                        continue;
                    }
                    if (method2.getAnnotation(AfterEach.class) != null) {
                        try {
                            method2.invoke(testObj);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }


        }
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.accessFlags().contains(AccessFlag.PRIVATE)) {
                continue;
            }
            if (method.getAnnotation(AfterAll.class) != null) {
                try {
                    method.invoke(testObj);

                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    private static Object initTestObj(Class<?> testClass) {
        try {
            Constructor<?> noArdgConstructor = testClass.getConstructor();
            return noArdgConstructor.newInstance();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Нет конструктора по умолчанию");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Не удается создать объект тест класса");
        }
    }


}

