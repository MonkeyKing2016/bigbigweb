package com.mk.diy.bigbigweb.model.request;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单model
 *
 * @author wanghao
 * @create 2017-10-19 15:31
 */
public class MenuModel implements Serializable{
    private static final long serialVersionUID = -2502379246469752232L;

    private List<MenuButton> button;

    public List<MenuButton> getButton() {
        return button;
    }

    public void setButton(List<MenuButton> button) {
        this.button = button;
    }
}
