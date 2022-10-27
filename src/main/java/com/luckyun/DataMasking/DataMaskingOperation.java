package com.luckyun.DataMasking;

/**
 * <p>Title:DataMaskingOperation</p>
 *
 * @description: 自定义Serializer，参考jackson的StringSerializer，下面的示例只针对String类型进行脱敏
 * @author: yangwenjun
 * @create: 2022-10-27 10:02
 */
public interface DataMaskingOperation {
	String MASK_CHAR = "*";
	String mask(String content, String maskChar);
}
