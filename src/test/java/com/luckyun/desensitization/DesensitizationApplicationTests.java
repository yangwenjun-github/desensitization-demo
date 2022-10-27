package com.luckyun.desensitization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.luckyun.DataMasking.DataMaskingAnnotationIntrospector;
import com.luckyun.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesensitizationApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		ObjectMapper objectMapper = new JsonMapper(); //builder.createXmlMapper(false).build();
		AnnotationIntrospector ai = objectMapper.getSerializationConfig().getAnnotationIntrospector();
		AnnotationIntrospector newAi = AnnotationIntrospectorPair.pair(ai, new DataMaskingAnnotationIntrospector());
		objectMapper.setAnnotationIntrospector(newAi);
		User user = new User();
		user.setId(1L);
		user.setName("张三");
		user.setAge(18);
		user.setEmail("511486460@qq.com");
		System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
	}

}
