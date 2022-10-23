package com.sdut.rbs.entity;

import lombok.Data;

/**
 * @Projectname: RBS-Server-SF
 * @Filename: OldTable
 * @Author: SpringForest
 * @Data:2022/10/19 19:32
 * @Description:
 */
@Data
public class OldTable {
    private int id;
    private int roomId;
    private String roomName;
    private String username;
    private String userDepart;
    private int timeId;
    private String timeName;
    private String date;
    private String reason;
    private String applyTime;
    private int isNeedMedia;
    private int isAdmit;
}
