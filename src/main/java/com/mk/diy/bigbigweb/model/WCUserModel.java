package com.mk.diy.bigbigweb.model;

import com.mk.diy.bigbigweb.enums.UserEnum;

/**
 * 微信会员model
 *
 * @author wanghao
 * @create 2017-10-15 19:42
 */
public class WCUserModel extends Base{
    private static final long serialVersionUID = -2594867219831997489L;

    private String wechatNo;
    private String userName;
    private String userNo;
    private String createTime;
    private String status = UserEnum.UNBIND.getCode();
    private String flag;

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
