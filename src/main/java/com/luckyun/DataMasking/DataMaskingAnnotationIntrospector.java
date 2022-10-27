package com.luckyun.DataMasking;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.luckyun.annotation.DataMasking;

/**
 * <p>Title:DataMaskingAnnotationIntrospector</p>
 *
 * @description: 自定义AnnotationIntrospector，适配我们自定义注解返回相应的Serializer
 * @author: yangwenjun
 * @create: 2022-10-27 10:18
 */
public class DataMaskingAnnotationIntrospector extends NopAnnotationIntrospector {
	@Override
	public Object findSerializer(Annotated am) {
		DataMasking annotation = am.getAnnotation(DataMasking.class);
		if (annotation != null) {
			return new DataMaskingSerializer(annotation.maskFunc().operation());
		}
		return null;
	}
}