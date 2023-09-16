package com.quan.demo.framework.aop.mapper.wrapper;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

//https://blog.csdn.net/u014717572/article/details/84451041
//SpringBoot+Mybatis,返回Map的时候,将Map内的Key转换为驼峰的命名表达式
public class CustomWrapper extends MapWrapper {

	public CustomWrapper(MetaObject metaObject, Map<String, Object> map) {
		super(metaObject, map);
	}

	@Override
	public String findProperty(String name, boolean useCamelCaseMapping) {
		return name;
	}

}
