package com.sdut.rbs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 借用原因
 * 
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
@Data
@TableName("reason")
public class ReasonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 原因名称
	 */
	@TableId
	private Integer id;

	private String name;

}
