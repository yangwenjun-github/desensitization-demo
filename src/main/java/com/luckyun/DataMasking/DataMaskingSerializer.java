package com.luckyun.DataMasking;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * <p>Title:DataMaskingSerializer</p>
 *
 * @description:
 * @author: yangwenjun
 * @create: 2022-10-27 10:04
 */
public class DataMaskingSerializer extends StdScalarSerializer<Object> {
	private final DataMaskingOperation operation;

	public DataMaskingSerializer(DataMaskingOperation operation) {
		super(String.class,false);
		this.operation = operation;
	}
	public DataMaskingSerializer() {
		super(String.class,false);
		this.operation = null;
	}


	@Override
	public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		if (Objects.isNull(operation)){
			String content=DataMaskingFunc.ALL_MASK.operation().mask(value.toString(),null);
			jsonGenerator.writeString(content);
		}else {
			String content=operation.mask(value.toString(),null);
			jsonGenerator.writeString(content);
		}
	}
	public boolean isEmpty(SerializerProvider  provider,Object value) {
		String str=(String) value;
		return str.isEmpty();
	}
	public final void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
		this.serialize(value, gen, provider);
	}

	public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
		return this.createSchemaNode("string", true);
	}

	public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
		this.visitStringFormat(visitor, typeHint);
	}

}

