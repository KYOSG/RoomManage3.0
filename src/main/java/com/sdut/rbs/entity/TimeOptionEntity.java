package com.sdut.rbs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 时间选项
 * 
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
@Data
@TableName("timeOption")
public class TimeOptionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 选项名称
	 */
	@TableId

	private Integer id;

	private String name;

}
