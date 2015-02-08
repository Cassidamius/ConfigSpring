package com.spring.config.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.spring.config.annotation.DataSourceType;
import com.spring.config.initializer.DynamicDataSourceHolder;

@Component
@Aspect
@Order(0)
public class DataSourceAspect {
	
	@Before(value = "execution(* *..service.impl.*Impl.*(..))")
	public void before(JoinPoint point) {
		Object target = point.getTarget();
		String canonicalName = target.getClass().getCanonicalName();
		String methodName = point.getSignature().getName();
		System.out.println("target: " + canonicalName + ", methodName: " + methodName);
		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
				.getMethod().getParameterTypes();

//		Class<?>[] interfaces = target.getClass().getInterfaces();
//		if (interfaces == null || interfaces.length == 0) {
//			return;
//		}
//		Class<?> face = null;
//		for (Class<?> clazz : interfaces) {
//			if (target.getClass().getSimpleName().equals(clazz.getSimpleName() + "Impl")) {
//				face = clazz;
//				break;
//			}
//		}
//		if (face == null) {
//			return;
//		}
		try {
			Method m = target.getClass().getMethod(methodName, parameterTypes);
			if (m != null && m.isAnnotationPresent(DataSourceType.class)) {
				DataSourceType data = m.getAnnotation(DataSourceType.class);
				DynamicDataSourceHolder.putDataSource(data.value());
				System.out.println(data.value());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Before(value = "execution(* *..service.impl.*Impl.*(..))")
//	public void before(JoinPoint point) {
//		Object target = point.getTarget();
//		String canonicalName = target.getClass().getCanonicalName();
//		String methodName = point.getSignature().getName();
//		System.out.println("target: " + canonicalName + ", methodName: " + methodName);
//		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
//				.getMethod().getParameterTypes();
//
//		Class<?>[] interfaces = target.getClass().getInterfaces();
//		if (interfaces == null || interfaces.length == 0) {
//			return;
//		}
//		Class<?> face = null;
//		for (Class<?> clazz : interfaces) {
//			if (target.getClass().getSimpleName().equals(clazz.getSimpleName() + "Impl")) {
//				face = clazz;
//				break;
//			}
//		}
//		if (face == null) {
//			return;
//		}
//		try {
//			Method m = face.getMethod(methodName, parameterTypes);
//			if (m != null && m.isAnnotationPresent(DataSourceType.class)) {
//				DataSourceType data = m.getAnnotation(DataSourceType.class);
//				DynamicDataSourceHolder.putDataSource(data.value());
//				System.out.println(data.value());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
