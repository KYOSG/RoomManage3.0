package com.sdut.rbs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable{

    public int startWeek;
    public int endWeek;
    public String username;
    public String userDepart;
    public int timeId;
    public String timeName;
    public String reason;

    public TimeTable(String rawData){
        String[] temp = rawData.split("/");

        this.reason = temp[0];
        this.timeName = temp[1].substring(1,5);

        String tt = new String(this.timeName);
        switch (tt) {
            case "1-2节" -> {
                this.timeName = "第一、二节";
                this.timeId = 1;
            }
            case "3-4节" -> {
                timeName = "第三、四节";
                this.timeId = 2;
            }
            case "5-6节" -> {
                timeName = "第五、六节";
                this.timeId = 3;
            }
            case "7-8节" -> {
                timeName = "第七、八节";
                this.timeId = 4;
            }
            case "9-10" -> {
                timeName = "第九、十节";
                this.timeId = 5;
            }
        }

        String startWeekTemp = temp[1].substring(temp[1].length()-4,temp[1].length()-3);
        String endWeekTemp = temp[1].substring(temp[1].length()-2,temp[1].length()-1);
        if (startWeekTemp.equals("-")){
            startWeekTemp = temp[1].substring(temp[1].length()-5,temp[1].length()-4);
            endWeekTemp = temp[1].substring(temp[1].length()-3,temp[1].length()-1);
        }
        this.startWeek = Integer.parseInt(startWeekTemp);
        this.endWeek = Integer.parseInt(endWeekTemp);

        this.username = temp[2];
        this.userDepart = temp[3];


    }
}
