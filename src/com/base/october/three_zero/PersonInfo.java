package com.base.october.three_zero;

import java.io.Serializable;

/**
 * @author Ming
 * @date 2020/11/1 - 16:22
 * @describe
 */
public class PersonInfo implements Serializable {
    private Integer id;

    public PersonInfo(Integer id) {
        this.id = id;
    }
}
