package com.sdut.rbs;

import com.sdut.rbs.dao.BorrowedInfoDao;
import com.sdut.rbs.dao.SpecialDao;
import com.sdut.rbs.dao.TimeOptionDao;
import com.sdut.rbs.entity.BorrowInfoEntity;
import com.sdut.rbs.entity.OldTable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RbsServerSfApplicationTests {

    @Resource
    private TimeOptionDao timeOptionDao;
    @Resource
    private SpecialDao specialDao;
    @Resource
    private BorrowedInfoDao borrowedInfoDao;
    @Test
    public void timeOrderTest(){
        for (int i=0;i<7;i++){
            System.out.println(timeOptionDao.getAllTime());
        }
    }

    @Test
    public void tranTable(){
        List<OldTable> oldList = specialDao.getOld();
        System.out.println(oldList.size());
        List<BorrowInfoEntity> newList = new ArrayList<>();
        for(OldTable item : oldList){
            switch (item.getTimeName()){
                case "第一、二节":
                    newList.add(createItem(item,"第一节"));
                    newList.add(createItem(item,"第二节"));
                    break;
                case "第三、四节":
                    newList.add(createItem(item,"第三节"));
                    newList.add(createItem(item,"第四节"));
                    break;
                case "第五、六节":
                    newList.add(createItem(item,"第五节"));
                    newList.add(createItem(item,"第六节"));
                    break;
                case "第七、八节":
                    newList.add(createItem(item,"第七节"));
                    newList.add(createItem(item,"第八节"));
                    break;
                case "第九、十节":
                    newList.add(createItem(item,"第九节"));
                    newList.add(createItem(item,"第十节"));
                    break;
            }
        }

        for (BorrowInfoEntity item : newList){
            try {
                borrowedInfoDao.insert(item);
            }catch (Exception e){
                e.getStackTrace();
            }

        }

    }

    private BorrowInfoEntity createItem(OldTable item, String timeName){
        BorrowInfoEntity borrowInfoEntity = new BorrowInfoEntity();

        borrowInfoEntity.setApplyDate(item.getApplyTime());
        borrowInfoEntity.setDate(item.getDate());
        borrowInfoEntity.setReason(item.getReason());
        borrowInfoEntity.setName(item.getUsername());
        borrowInfoEntity.setTime(timeName);
        borrowInfoEntity.setIsAdmit("1");
        borrowInfoEntity.setRoomName(item.getRoomName());



        return borrowInfoEntity;
    }
}
