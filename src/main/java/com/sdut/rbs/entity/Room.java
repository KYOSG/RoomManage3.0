package com.sdut.rbs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private Integer id;
    private String roomName;
    private int isMedia;
    private int capacity;
    private String imgUrl;
    private int isSpecial;
    private int isPower;
    private String description;
    private Integer state;
}
