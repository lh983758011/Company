package com.goldenchef.company.entities;

import java.io.Serializable;

/**
 * @author luo_hao
 * @pram 修改日期
 * @date 创建日期：2017/3/3
 */
public class PositionEntity implements Serializable {

    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
