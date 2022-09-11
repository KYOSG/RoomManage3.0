package com.sdut.rbs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 借用记录
 * 
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
@Data
@TableName("borrowInfo")
public class BorrowInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 借用人
	 */
	private String name;
	/**
	 * 借用时间
	 */
	private String time;
	/**
	 * 借用日期
	 */
	private String date;
	/**
	 * 借用原因
	 */
	private String reason;
	/**
	 * 申请时间
	 */
	private String applyDate;
	/**
	 * 是否通过
	 */
	private String isAdmit;
	//教室名称
	private String roomName;
}
