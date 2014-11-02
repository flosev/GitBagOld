package net.golovach.inject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

class FieldReflector {

    /**
     * 1) Non static
     * 2) all scopes
     * 3) from all parents
     * 4) clazz fields - include
     * 5) upperBound fields - exclude
     */
    public static List<Field> collectUpTo(Class<?> clazz, Class<?> upperBound) {
        ArrayList<Field> result = new ArrayList<>();
        Class<?> current = clazz;
        while (current != upperBound) {
            result.addAll(asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        return result;
    }

    /**
     * 1) Non static
     * 2) all scopes
     * 3) from all parents
     * 4) clazz fields - include
     * 5) upperBound fields - exclude
     */
//    public static List<Field> collectUpTo(Class<?> clazz, Class<?> upperBound) {
//        if (clazz == upperBound) {
//            throw new IllegalArgumentException();
//        }
//        return Arrays.asList(clazz.getDeclaredFields());
//        return Arrays.asList(clazz.getFields());
//    }

    /**
     * Filter only marked by @Inject
     */
    public static List<Field> filterInject(List<Field> allFields) {
        ArrayList<Field> result = new ArrayList<>();
        for (Field field : allFields) {
            Inject annotation = field.getAnnotation(Inject.class);
            if (annotation != null) {
                result.add(field);
            }
        }
        return result;
    }
}
