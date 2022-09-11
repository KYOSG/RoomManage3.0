package com.sdut.rbs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("picUrl")
public class PicEntity {
    private int id;

    private String name;

    private String url;
}
