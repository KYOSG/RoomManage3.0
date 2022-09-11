package com.sdut.rbs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 教室信息表
 * 
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
@Data
@TableName("room")
public class RoomEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 教室名称
	 */
	@TableId
	private Integer id;

	private String name;
	/**
	 * 容量
	 */
	private String capacity;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 教室状态（1正常2维护）
	 */
	private String status;
	//头图链接
	private String imgUrl;

}
