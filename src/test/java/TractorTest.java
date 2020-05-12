
import com.xuan.forum.Tractor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/8
 *
 * 题目：
 *      有一片沙漠长度500KM,拖拉机每次加满油50L可以跑50KM,在沙漠的起点有无限的油,
 *      拖拉机可以从起点把油运到中途, 在中途建立加油站,那么拖拉机刚好跑出沙漠需要加多少次油,
 *      来回多少次(改变一次向为一次),总共需要跑多远的里程?
 */
@Slf4j
public class TractorTest {
    /** 路程 500km */
     static ArrayList road;
     /** 拖拉机 */
     static Tractor tractor = new Tractor();
     /** 加油站 */
     static List<Integer> gasStation = new ArrayList<Integer>(500);
     /** 掉头次数 */
     static int cout = 0;

    static {
        road = new ArrayList(500);
        //起点设置为充满油的拖拉机
        road.add(0, tractor);
        for (int index =1 ; index <= 499 ; index++){
            road.add(index, null);
        }
        //设置0为加油站
        gasStation.add(0,(Integer) 1);
        for (int index =1 ; index <= 499 ; index++){
            gasStation.add(index, null);
        }
    }

    public static void main(String[] args) {
        while (true){
            //获取拖拉机当前位置
            Integer index = tractor.tractorIndex(road);
            if (index == 499){
                System.out.println("退出循环");
                break;
            }
            //向前行驶
            travel();
            //判断当前节点是否是加油站
            if (gasStation.get(index) != null && gasStation.get(index) instanceof Integer){
                //加满油
                fullFuelTank();
                log.info(tractor+"在"+index+"加满油！");
            }else //不是加油站 油箱箱剩余一半 则返回上一个加油站
                if (tractor.getFuelTank() == 25){
                    //当前节点设置为加油站
                    gasStation.set(index,1);
                    log.info(tractor+"设置"+index+"为加油站点，回头次数+1！");
                    //后行驶
                    travel(-25);
                    //掉头次数+1
                    cout+=1;
            }

        }
        log.info("拖拉机的掉头次数"+cout);
        log.info("拖拉机公里数"+tractor.getDistance());
    }

    /** 行驶1km */
    public static void travel(){
        //行驶
        road.set(tractor.tractorIndex(road)+1,tractor);
        road.set(tractor.tractorIndex(road),null);
        //油箱减
        tractor.reduceFuelTank();
    }

    /** 行驶Xkm */
    public static void travel(Integer number){
        //行驶
        road.set(tractor.tractorIndex(road)+number,tractor);
        road.set(tractor.tractorIndex(road),null);
        //油箱减油水
        tractor.reduceFuelTank(number);
        //如果是向后行驶 次数+1
        if (number < 0 )cout++;
    }

    /** 加满油 */
    public static void fullFuelTank(){
        //拖拉机加满油
        tractor.fullFuelTank();
    }


}
