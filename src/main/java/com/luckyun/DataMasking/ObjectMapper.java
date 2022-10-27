package com.luckyun.DataMasking;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * <p>Title:ObjectMapper</p>
 *
 * @description: 覆盖ObjectMapper(springboot用)
 * @author: yangwenjun
 * @create: 2022-10-27 10:12
 */
@Configuration(proxyBeanMethods = false)
class DataMaskConfiguration {

	@Configuration(
			proxyBeanMethods = false
	)
	@ConditionalOnClass({Jackson2ObjectMapperBuilder.class})
	static class JacksonObjectMapperConfiguration {
		JacksonObjectMapperConfiguration() {
		}
		@Bean
		@Primary
		ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
			ObjectMapper objectMapper = builder.createXmlMapper(false).build();
			AnnotationIntrospector ai = objectMapper.getSerializationConfig().getAnnotationIntrospector();
			AnnotationIntrospector newAi = AnnotationIntrospectorPair.pair(ai, new DataMaskingAnnotationIntrospector());
			objectMapper.setAnnotationIntrospector(newAi);
			return objectMapper;
		}
	}

}
