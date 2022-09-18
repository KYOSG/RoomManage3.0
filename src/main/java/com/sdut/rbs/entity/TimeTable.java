package com.sdut.rbs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable{

    public int startWeek;
    public int endWeek;
    public String name;
    public String[] time;
    public String reason;

    public TimeTable(String rawData){
        String[] temp = rawData.split("/");
        this.time = new String[2];
        this.reason = temp[0];

        String tt = new String(temp[1].substring(1,5));
        switch (tt) {
            case "1-2节":
                this.time[0] = "第一节";
                this.time[1] = "第二节";
                break;
            case "3-4节":
                this.time[0] = "第三节";
                this.time[1] = "第四节";
                break;
            case "5-6节":
                this.time[0] = "第五节";
                this.time[1] = "第六节";
                break;
            case "7-8节":
                this.time[0] = "第七节";
                this.time[1] = "第八节";
                break;
            case "9-10":
                this.time[0] = "第九节";
                this.time[1] = "第十节";
                break;
        }

        String startWeekTemp = temp[1].substring(temp[1].length()-4,temp[1].length()-3);
        String endWeekTemp = temp[1].substring(temp[1].length()-2,temp[1].length()-1);
        if (startWeekTemp.equals("-")){
            startWeekTemp = temp[1].substring(temp[1].length()-5,temp[1].length()-4);
            endWeekTemp = temp[1].substring(temp[1].length()-3,temp[1].length()-1);
        }
        this.startWeek = Integer.parseInt(startWeekTemp);
        this.endWeek = Integer.parseInt(endWeekTemp);

        this.name = temp[2];
    }
}
