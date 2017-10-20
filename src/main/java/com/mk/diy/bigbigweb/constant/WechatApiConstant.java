package com.mk.diy.bigbigweb.constant;

/**
 * 微信api接口地址
 *
 * @author wanghao
 * @create 2017-10-18 14:30
 */
public class WechatApiConstant {

    /**
     * 获取access_token
     */
    public static final String GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=%1$s&appid=%2$s&secret=%3$s";

    /**
     * 自定义菜单 ：创建自定义菜单
     */
    public static final String CREATE_MENU_POST ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

    /**
     * 自定义菜单 ：查询自定义菜单
     */
    public static final String QUERY_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";

    /**
     * 自定义菜单 ：删除自定义菜单
     */
    public static final String DEL_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";

    /**
     * 自定义菜单 ：创建个性化菜单
     */
    public static final String CREATE_CONDITIONAL_MENU_POST = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=%s";

    /**
     * 自定义菜单 ：删除个性化菜单
     */
    public static final String DEL_CONDITIONAL_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=%s";

    /**
     * 自定义菜单 ：测试个性化菜单匹配结果
     */
    public static final String TRY_CONDITIONAL_MENU_POST = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=%s";

    /**
     * 自定义菜单 ：获取自定义菜单配置接口
     *
     * 1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自定义菜单配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。
     * 2、本接口与自定义菜单查询接口的不同之处在于，本接口无论公众号的接口是如何设置的，都能查询到接口，而自定义菜单查询接口则仅能查询到使用API设置的菜单配置。
     * 3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。
     * 4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。
     * 5、本接口中返回的图片/语音/视频为临时素材（临时素材每次获取都不同，3天内有效，通过素材管理-获取临时素材接口来获取这些素材），本接口返回的图文消息为永久素材素材（通过素材管理-获取永久素材接口来获取这些素材）。
     */
    public static final String QUERY_MENU_CONFIG_GET = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=%s";

    /**
     * 发送信息-客服信息：添加客服账号
     */
    public static final String CUSTOM_ADD_POST = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=%s";

    /**
     * 发送信息-客服信息：修改客服账号
     */
    public static final String CUSTOM_UPDATE_POST = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=%s";

    /**
     * 发送信息-客服信息：删除客服账号
     */
    public static final String CUSTOM_DEL_GET = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=%s";

    /**
     * 发送信息-客服信息：设置客服头像
     */
    public static final String CUSTOM_UPLOADHEADIMG_POST = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=%s";

    /**
     * 发送信息-客服信息：获取所有客服账号
     */
    public static final String CUSTOM_ALL_GET = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=%s";

    /**
     * 客服接口：发信息
     */
    public static final String CUSTOM_SEND_POST = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";

    /**
     * 客服接口：发送输入状态
     * 此接口需要客服消息接口权限。
     * 1. 如果不满足发送客服消息的触发条件，则无法下发输入状态。
     * 2. 下发输入状态，需要客服之前30秒内跟用户有过消息交互。
     * 3. 在输入状态中（持续15s），不可重复下发输入态。
     * 4. 在输入状态中，如果向用户下发消息，会同时取消输入状态。
     */
    public static final String CUSTOM_TYPING_POST = "https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=%s";

    /**
     * 素材管理 ： 新增临时素材
     */
    public static final String MEDIA_ADD_POST = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%1$s&type=%2$s";
    
    /**
     * 素材管理 ： 获取临时素材
     */
    public static final String MEDIA_GET_GET = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%1$s&media_id=%2$s";

    /**
     * 素材管理 ： 新增永久素材
     *
     * 1、最近更新：永久图片素材新增后，将带有URL返回给开发者，开发者可以在腾讯系域名内使用（腾讯系域名外使用，图片将被屏蔽）。
     * 2、公众号的素材库保存总数量有上限：图文消息素材、图片素材上限为5000，其他类型为1000。
     * 3、素材的格式大小等要求与公众平台官网一致：
            图片（image）: 2M，支持bmp/png/jpeg/jpg/gif格式
            语音（voice）：2M，播放长度不超过60s，mp3/wma/wav/amr格式
            视频（video）：10MB，支持MP4格式
            缩略图（thumb）：64KB，支持JPG格式
     * 4、图文消息的具体内容中，微信后台将过滤外部的图片链接，图片url需通过"上传图文消息内的图片获取URL"接口上传图片获取。
     * 5、"上传图文消息内的图片获取URL"接口所上传的图片，不占用公众号的素材库中图片数量的5000个的限制，图片仅支持jpg/png格式，大小必须在1MB以下。
     * 6、图文消息支持正文中插入自己帐号和其他公众号已群发文章链接的能力。
     */
    public static final String MATERIAL_ADD_POST = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";

    /**
     * 素材管理 ： 获取永久素材
     */
    public static final String MATERIAL_GET_POST = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=%s";

    /**
     * 素材管理 ： 删除永久素材
     */
    public static final String MATERIAL_DELETE_POST = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=%s";

    /**
     * 素材管理 ： 修改永久图文素材
     */
    public static final String MATERIAL_UPDATE_POST = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=%s";

    /**
     * 素材管理 ： 获取素材总数
     */
    public static final String MATERIAL_COUNT_GET = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=%s";

    /**
     * 素材管理 ： 获取素材列表
     * 请注意：
     * 1、获取永久素材的列表，也包含公众号在公众平台官网素材管理模块中新建的图文消息、语音、视频等素材
     * 2、临时素材无法通过本接口获取
     * 3、调用该接口需https协议
     */
    public static final String MATERIAL_LIST_POST = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";

}
