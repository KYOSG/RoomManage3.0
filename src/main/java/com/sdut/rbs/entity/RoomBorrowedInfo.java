package com.sdut.rbs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomBorrowedInfo {
    private String id;
    private String roomId;
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
