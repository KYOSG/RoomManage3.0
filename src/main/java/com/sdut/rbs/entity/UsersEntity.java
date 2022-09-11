package com.sdut.rbs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户信息表
 * 
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
@Data
@TableName("users")
public class UsersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 工号
	 */
	@TableId
	private String id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 身份
	 */
	private String role;
	/**
	 * 密码
	 */
	private String password;

}
