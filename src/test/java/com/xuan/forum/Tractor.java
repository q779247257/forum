package com.xuan.forum;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/8
 * @描述：拖拉机
 */

@Data
@Slf4j
public class Tractor {
    /** 燃料箱 */
    private int fuelTank;
    /** 路程公里数 */
    private int distance;

    public Tractor(){
        fullFuelTank();
    }

    /**
     * 充满燃料箱
     */
    public void fullFuelTank(){
        this.fuelTank = 50;
    }

    /** 行驶1km燃料减去1L */
    public void reduceFuelTank(){
        this.fuelTank -= 1;
        this.distance += 1;
        log.info("车路程："+this.distance);
    }


    /** 行驶 X km 燃料减去 X L */
    public void reduceFuelTank(Integer km){
        this.fuelTank -= km;
        if (km < 0){
            this.distance -= km;
        }else {
            this.distance += km;

        }
        log.info("车路程："+this.distance);
    }

    /**
     * 获取拖拉机所属下标
     * @param road
     * @return
     */
    public Integer tractorIndex(List road){
        return road.indexOf(this);
    }
}
