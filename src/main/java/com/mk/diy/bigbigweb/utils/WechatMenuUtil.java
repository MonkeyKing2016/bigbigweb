package com.mk.diy.bigbigweb.utils;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.enums.MenuType;
import com.mk.diy.bigbigweb.model.request.MenuButton;

/**
 * 微信Menu工具类
 *
 * @author wanghao
 * @create 2017-10-20 9:59
 */
public class WechatMenuUtil {

    public static MenuButton createMainButton(String name){
        MenuButton button = new MenuButton();
        button.setName(name);
        return button;
    }
    /**
     * 除 MenuType.MINIPROGRAM 外 其他类型菜单按钮 只需要传一个Param即可
     * MINIPROGRAM 需要按照顺序传递过来
     *  1.url
     *  2.appid
     *  3.pagepath
     * @param menuType
     * @param name
     * @param params
     * @return
     */
    public static MenuButton createButton(MenuType menuType, String name, String ... params){
        MenuButton button = new MenuButton();
        button.setType(menuType.getCode());
        button.setName(name);
        switch (menuType) {
            case CLICK:
                button.setKey(params[0]);
                break;
            case VIEW:
                button.setUrl(params[0]);
                break;
            case SCANCODE_PUSH:
                button.setKey(params[0]);
                break;
            case SCANCODE_WAITMSG:
                button.setKey(params[0]);
                break;
            case PIC_SYSPHOTO:
                button.setKey(params[0]);
                break;
            case PIC_PHOTO_OR_ALBUM:
                button.setKey(params[0]);
                break;
            case PIC_WEIXIN:
                button.setKey(params[0]);
                break;
            case LOCATION_SELECT:
                button.setKey(params[0]);
                break;
            case MEDIA_ID:
                button.setMedia_id(params[0]);
                break;
            case VIEW_LIMITED:
                button.setMedia_id(params[0]);
                break;
            case MINIPROGRAM:
                button.setUrl(params[0]);
                button.setAppid(params[1]);
                button.setPagepath(params[2]);
                break;
            default:
                break;
        }
        return button;
    }

    public static MenuButton createButton(String menuType, String name, String ... params){
        return createButton(MenuType.getMenuType(menuType), name, params);
    }

}
