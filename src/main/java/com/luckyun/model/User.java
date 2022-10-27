package com.luckyun.model;

import com.luckyun.DataMasking.DataMaskingFunc;
import com.luckyun.annotation.DataMasking;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title:User</p>
 *
 * @description: 实体类
 * @author: yangwenjun
 * @create: 2022-10-27 10:14
 */
@Data
public class User implements Serializable {
	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 姓名
	 */
	@DataMasking(maskFunc = DataMaskingFunc.ALL_MASK)
	private String name;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 邮箱
	 */
	@DataMasking(maskFunc = DataMaskingFunc.ALL_MASK)
	private String email;

}
