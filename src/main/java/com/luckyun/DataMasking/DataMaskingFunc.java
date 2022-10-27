package com.luckyun.DataMasking;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: desensitization
 * @description:
 * @author: yangwenjun
 * @create: 2022-10-27 10:03
 */
public enum DataMaskingFunc {
	/**
	 *  脱敏转换器
	 */
	NO_MASK((str, maskChar) -> {
		return str;
	}),
	ALL_MASK((str, maskChar) -> {
		if (StringUtils.isNoneEmpty(str)) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < str.length(); i++) {
				sb.append(StringUtils.isNoneEmpty(maskChar) ? maskChar : DataMaskingOperation.MASK_CHAR);
			}
			return sb.toString();
		}
		return str;
	});

	private final DataMaskingOperation operation;

	private DataMaskingFunc(DataMaskingOperation operation) {
		this.operation = operation;
	}

	public DataMaskingOperation operation() {
		return this.operation;
	}

}


