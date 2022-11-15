package com.quan.demo.reflect;

import java.lang.reflect.*;
import java.util.*;

/**
 * TypeDemo
 *
 * @author Force-oneself
 * @date 2022-10-20
 */
public class TypeDemo<T extends List<String> & Collection<String>> {

    List<String> parameterizedType;

    Map<String, Object> parameterizedTypeMap;

    T typeVariable;

    T[] genericArrayType;

    List <? extends String> upperWildcardType;
    List <? super String> lowerWildcardType;
    List <?> wildcardType;

    String s;

    public static void main(String[] args) throws Exception {
        printWithField("parameterizedType");
        printWithField("parameterizedTypeMap");
        printWithField("typeVariable");
        printWithField("genericArrayType");
        printWithField("upperWildcardType");
        printWithField("lowerWildcardType");
        printWithField("wildcardType");
        printWithField("s");
    }

    private static void printWithField(String fieldName) throws NoSuchFieldException {
        Field field = TypeDemo.class.getDeclaredField(fieldName);
        Type type = field.getGenericType();
        printWithType(type);
    }

    private static void printWithType(Type type) {
        System.out.println("Current Type is " + type);
        // ParameterizedType是参数化类型，即带有泛型的类型，比如List<String>、Set<Long>、Map<String, Long>、Class<Float>等类型
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            System.out.println("=== ParameterizedType ===");
            // 原始类型
            Type rawType = parameterizedType.getRawType();
            System.out.println("原始类型 Raw Type: " + rawType.getTypeName());
            // 泛型类型列表
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            System.out.println("范型类型 Actual Type Arguments: " + Arrays.toString(typeArguments));
            // WildcardType是通配符类型，通配符是 ?，也就是当该类型是ParameterizedType时，当里面的泛型是通配符？时，该泛型就是WildcardType。
            // 该接口有两个方法，Type[] getUpperBounds()、Type[] getLowerBounds()。
            for (Type typeArgument : typeArguments) {
                if (typeArgument instanceof WildcardType) {
                    System.out.println("upper = " + Arrays.toString(((WildcardType) typeArgument).getUpperBounds()));
                    System.out.println("lower = " + Arrays.toString(((WildcardType) typeArgument).getLowerBounds()));
                }

            }
            // 作为内部类
            Type ownerType = parameterizedType.getOwnerType();
            System.out.println("原始类型外部类类型 Owner Type: " + (Objects.isNull(ownerType) ? "null" : ownerType.getTypeName()));
        }

        // TypeVariable是泛型类型，比如常见的T t和List<E>中的 E
        if (type instanceof TypeVariable) {
            System.out.println("=== TypeVariable ===");
            TypeVariable<?> typeVariable = (TypeVariable<?>) type;
            Type[] bounds = typeVariable.getBounds();
            // 没有extends 即为Object
            System.out.println("范型声明上边界 Bounds: " + Arrays.toString(bounds));
            String name = typeVariable.getName();
            System.out.println("范型名 Name: " + name);
            GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
            System.out.println("所属类类型 Generic Declaration: " + genericDeclaration);
        }
        // GenericArrayType是泛型数组类型。这个理解起来其实比较简单，两个前提组成，一是泛型，二是数组，即带泛型的数组类型。
        // 比如T[] t、List<T>[] list等，有泛型且有数组的类型
        if (type instanceof GenericArrayType) {
            System.out.println("=== GenericArrayType ===");
            GenericArrayType genericArrayType = (GenericArrayType) type;
            Type genericComponentType = genericArrayType.getGenericComponentType();
            System.out.println("数组泛型类型 Generic Component Type: " + genericComponentType.getTypeName());
        }
        // WildcardType是通配符类型，通配符是 ?，也就是当该类型是ParameterizedType时，当里面的泛型是通配符？时，该泛型就是WildcardType
        if (type instanceof WildcardType) {
            System.out.println("范型上边界 upper: " + Arrays.toString(((WildcardType) type).getUpperBounds()));
            System.out.println("范型下边界 lower: " + Arrays.toString(((WildcardType) type).getLowerBounds()));
        }
        // 正常不带范型的成员变量都是Class类型
        System.out.println();
    }
}
