package com.sam



import grails.test.mixin.*
import org.junit.*
import org.ccil.cowan.tagsoup.Parser

@TestFor(Template)
@Mock([TemplateFramework,TemplateElement,Segment])
class TemplateTests {

    String originContent = '''
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <title>超值积分大换购</title>
        </head>
        <body style="background:#e9f4ff;">
            <table width="622" border="0" align="center" cellpadding="0" cellspacing="0" style="font-family:Tahoma, Geneva, sans-serif; line-height:normal; font-size:12px;">
                <tr>
                    <td align="center" style="line-height:20px;font-size:12px;"><br/>
                        <a href="http://www.jinjiang.com/event/edm/12edm/m5t.html">如果邮件无法显示，请点击这里&gt;&gt;</a><br/>
                        为确保此邮件能正常接收，建议您将promotion@ejinjiang.com加入联系人列表
                    </td>
                </tr>
                <tr>
                    <td><table width="620" border="0" cellspacing="0" cellpadding="0" style="background:#fff; border:1px solid #ccc; margin-top:5px;">
                        <tr>
                            <td height="64"><table width="620" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="162" height="64" align="center">
                                        <a href="http://www.jinjiang.com/?cmpid=edm_12m5_home" target="_blank">
                                            <img border="0" src="http://www.jinjiang.com/event/edm/12edm/img5/logo.jpg" width="136" height="55" />
                                        </a>
                                    </td>
                                    <td width="458" align="right">
                                        <div style="padding-right:15px; color:#666464;font-size:12px; ">
                                            <span style="color:#0069b8;">
                                                <a href="http://www.jinjiang.com/membercenter/member/showOrdinaryLoginPage?cmpid=edm_12m5_login" target="_blank" style="color:#0069b8; text-decoration:none;font-size:12px;">登录</a> | <a href="http://www.jinjiang.com/membercenter/quickRegister?cmpid=edm_12m5_register" target="_blank" style="color:#0069b8; text-decoration:none;font-size:12px;">免费注册</a>
                                            </span> <br />
                                        服务热线：1010-1666     在线预订：<a href="http://www.jinjiang.com/?cmpid=edm_12m5_home" target="_blank">www.jinjiang.com </a>
                                        </div>
                                    </td>
                                </tr>
                            </table></td>
                        </tr>
                        <tr>
                            <td bgcolor="#1b5398" height="35">&nbsp;&nbsp;
                                <span style="line-height:35px; color:#fff; font-size:12px;">
                                    <a href="http://www.jinjiang.com/hotel?cmpid=edm_12m5_hotel" target="_blank" style="color:#fff; text-decoration:none;font-size:12px;">酒店</a> &nbsp;|&nbsp;
                                    <a href="http://www.jinjiang.com/travel?cmpid=edm_12m5_travel" target="_blank" style="color:#fff; text-decoration:none;font-size:12px;">旅游</a> &nbsp;|&nbsp;
                                    <a href="http://www.jinjiang.com/auto?cmpid=edm_12m5_auto" target="_blank" style="color:#fff; text-decoration:none;font-size:12px;">租车</a> &nbsp;|&nbsp;
                                    <a href="http://www.jinjiang.com/flights?cmpid=edm_12m5_flights" target="_blank" style="color:#fff; text-decoration:none;font-size:12px;">机票</a> &nbsp;|&nbsp;
                                    <a href="http://www.4008666000.com/index.html?cmpid=edm_12m5_epass" target="_blank" style="color:#fff; text-decoration:none;font-size:12px;">e卡通</a> &nbsp;|&nbsp;
                                    <a href="http://www.jinjiang.com/membercenter/member?cmpid=edm_12m5_member" target="_blank" style="color:#fff; text-decoration:none;font-size:12px;">锦江礼享<sup>+</sup></a>
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td height="182">
                                <table width="620" border="0" cellspacing="0" cellpadding="0" style="height:182px; overflow:hidden;">
                                    <tr>
                                        <td width="146" height="40" align="center" bgcolor="#e6e7e7">
                                            <img src="http://www.jinjiang.com/event/edm/12edm/img5/p1.gif" width="130" height="25" />
                                        </td>
                                        <td width="474" rowspan="3">
                                            <a href="http://www.jinjiang.com/event/item-promo/points.html?cmpid=edm_12m5_points" target="_blank">
                                                <img src="http://www.jinjiang.com/event/edm/12edm/img5/p3.jpg" alt="超值积分大换购" title="超值积分大换购" width="474" height="182" border="0" />
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="48" bgcolor="#e6e7e7">
                                            <span style="display:block; color:#666; line-height:20px; font-size:12px;">
                                                &nbsp;[Name]，你好!<br />
                                                &nbsp;会员卡号：[JJBE Card Number]<br /> &nbsp;当前积分：<b>[Point 1 Value]</b><br /> &nbsp;礼计划：[Tier]<br /> &nbsp;享计划：[JJBE Benefit Card Type]
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center" bgcolor="#e6e7e7">
                                            <a href="http://www.jinjiang.com/membercenter/myclub?cmpid=edm_12m5_myclub" target="_blank">
                                                <img alt="超值积分大换购" title="超值积分大换购" src="http://www.jinjiang.com/event/edm/12edm/img5/p2.gif" width="130" height="22" border="0" />
                                            </a>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <table width="599" border="0" align="center" cellpadding="0" cellspacing="0" style="height:201px; overflow:hidden;">
                                    <tr>
                                        <td>
                                            <a href="http://www.jinjiang.com/hotel?cmpid=edm_12m5_morehotel" target="_blank">
                                                <img src="http://www.jinjiang.com/event/edm/12edm/img5/p4.gif" width="599" height="31" border="0" />
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="170" align="left">
                                            <a href="http://www.jinjiang.com/hotel/show?&hotelId=534&checkinDate=2012-06-23&checkoutDate=2012-06-24&cmpid=edm_12m5_guoji" target="_blank">
                                                <img src="http://www.jinjiang.com/event/edm/12edm/img5/q1.jpg" width="198" height="173" border="0" alt="上海国际饭店 提前三天预订享受折扣优惠" title="上海国际饭店 提前三天预订享受折扣优惠"/>
                                            </a>
                                            <a href="http://www.jinjiang.com/hotel/show?&hotelId=606&checkinDate=2012-06-23&checkoutDate=2012-06-24&cmpid=edm_12m5_banshan" target="_blank">
                                                <img src="http://www.jinjiang.com/event/edm/12edm/img5/q2.jpg" width="202" height="173" border="0" alt="三亚半山锦江海景度假酒店 预订即享优惠" title="三亚半山锦江海景度假酒店 预订即享优惠"/>
                                            </a>
                                            <a href="http://www.jinjiang.com/hotel/show?&hotelId=224&checkinDate=2012-06-06&checkoutDate=2012-06-07&cmpid=edm_12m5_xiada" target="_blank">
                                                <img src="http://www.jinjiang.com/event/edm/12edm/img5/q3.jpg" border="0" width="199" height="174" title="锦江之星厦门大学店 毗邻厦门大学，可步行至白城沙滩" alt="锦江之星厦门大学店 毗邻厦门大学，可步行至白城沙滩" />
                                            </a>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td align="center" height="130">
                                <a href="http://www.jinjiang.com/award/campaign?cmpid=edm_12m5_zhuce" target="_blank">
                                    <img src="http://www.jinjiang.com/event/edm/12edm/img5/q4.jpg" width="599" height="129" border="0" title="豪礼摇不停，苹果摘不完！会员登录即可获赠4枚摇奖币" alt="豪礼摇不停，苹果摘不完！会员登录即可获赠4枚摇奖币" />
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table width="599" border="0" align="center" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td colspan="2">
                                            <a href="http://www.jinjiang.com/travel?cmpid=edm_12m5_moretravel" target="_blank">
                                                <img src="http://www.jinjiang.com/event/edm/12edm/img5/p11.gif" width="599" height="22" border="0"  />
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <a segment="推荐路线图片1" href="http://www.jinjiang.com/travel/group/853671.html?cmpid=edm_12m5_hangzhou" target="_blank">
                                                <img src="http://www.jinjiang.com/event/edm/12edm/img5/q5.jpg" width="599" height="117" border="0" title="杭州、乌镇二日游 上有天堂、下有苏杭”，品淡妆浓抹总相宜的韵味，领略西湖秀丽的湖光山色和众多的名胜古迹。乘坐西湖环湖游船，途中可观赏到西湖十景，第二天游览乌镇，体味原汁原味的水乡生活。" alt="杭州、乌镇二日游 上有天堂、下有苏杭”，品淡妆浓抹总相宜的韵味，领略西湖秀丽的湖光山色和众多的名胜古迹。乘坐西湖环湖游船，途中可观赏到西湖十景，第二天游览乌镇，体味原汁原味的水乡生活。" />
                                            </a>
                                            <a href="http://www.jinjiang.com/travel/group/821714.html?cmpid=edm_12m5_xianggang" target="_blank">
                                                <img src="http://www.jinjiang.com/event/edm/12edm/img5/q6.jpg" width="599" height="137" border="0" title="香港五星四日自由行 享受优惠直飞往返机票，入住地段优越的五星酒店，齐享香港两大主题乐园。从九龙尖沙咀到港岛中环、铜锣湾，尽情享受香港免税购物热潮。品味香港烩聚世界各地特色的美食。" alt="香港五星四日自由行 享受优惠直飞往返机票，入住地段优越的五星酒店，齐享香港两大主题乐园。从九龙尖沙咀到港岛中环、铜锣湾，尽情享受香港免税购物热潮。品味香港烩聚世界各地特色的美食。" />
                                            </a>
                                            <a href="http://www.jinjiang.com/travel/group/857506.html?cmpid=edm_12m5_taiwan" target="_blank">
                                                <img src="http://www.jinjiang.com/event/edm/12edm/img5/q7.jpg" width="599" height="126" border="0" title="超值台湾环岛八日游 参观世界四大博物馆之一的【故宫博物馆】，游览镶嵌在台湾本岛中央的湛蓝明珠【日月潭风景区】，由【花东海岸公路】前往花莲，沿途八仙洞、三仙台、北回归线标等奇景，令人流连忘返。" alt="超值台湾环岛八日游 参观世界四大博物馆之一的【故宫博物馆】，游览镶嵌在台湾本岛中央的湛蓝明珠【日月潭风景区】，由【花东海岸公路】前往花莲，沿途八仙洞、三仙台、北回归线标等奇景，令人流连忘返。" />
                                            </a>
                                            <a href="http://www.jinjiang.com/travel/group/852533.html?cmpid=edm_12m5_guandao" target="_blank">
                                                <img src="http://www.jinjiang.com/event/edm/12edm/img5/q8.jpg" width="600" height="131" border="0" title="美国关岛六天四夜半自由行 关岛一向以休闲娱乐设施齐全闻名，每年吸引超过一百三十万名游客，其中包括潜水、游泳、滑浪风帆、高尔夫球、浮潜、拖曳伞、购物、探索古查莫洛人文化、观赏海豚和热带鱼等。" alt="美国关岛六天四夜半自由行 关岛一向以休闲娱乐设施齐全闻名，每年吸引超过一百三十万名游客，其中包括潜水、游泳、滑浪风帆、高尔夫球、浮潜、拖曳伞、购物、探索古查莫洛人文化、观赏海豚和热带鱼等。"  />
                                            </a>
                                            <div style="color:#666;">*此邮件中价格为促销价，最终价格以网站为准！</div>
                                            <a href="http://www.jinjiang.com/event/item-promo/travelmovie.html?cmpid=edm_12m5_film" target="_blank">
                                                <img src="http://www.jinjiang.com/event/edm/12edm/img5/q9.jpg" width="599" height="104" border="0" title="邀您带上爱去旅行" alt="邀您带上爱去旅行"/>
                                            </a>
                                            <img src="http://www.jinjiang.com/event/edm/12edm/img5/q10.jpg" width="599" height="56" border="0" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <table width="599" border="0" align="center" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="http://www.jinjiang.com/hotel/jjhotel?cmpid=edm_12m5_xingjijiudian" target="_blank">
                                    <img src="http://www.jinjiang.com/event/edm/12edm/img5/a1.gif" width="72" height="69" border="0" />
                                </a>
                                <a href="http://www.jinjiang.com/hotel/jjinn?cmpid=edm_12m5_jinjiangzhixing" target="_blank">
                                    <img src="http://www.jinjiang.com/event/edm/12edm/img5/a2.gif" width="82" height="69" border="0" />
                                </a>
                                <a href="http://www.jinjiang.com/hotel/bestayhotel?cmpid=edm_12m5_baishikuaijie" target="_blank">
                                    <img src="http://www.jinjiang.com/event/edm/12edm/img5/a3.gif" width="87" height="69" border="0" />
                                </a>
                                <a href="http://www.jinjiang.com/travel/shct?cmpid=edm_12m5_guolv" target="_blank">
                                    <img src="http://www.jinjiang.com/event/edm/12edm/img5/a4.gif" width="80" height="69" border="0" />
                                </a>
                                <a href="http://www.jinjiang.com/travel/shjjt?cmpid=edm_12m5_jinlv" target="_blank">
                                    <img src="http://www.jinjiang.com/event/edm/12edm/img5/a5.gif" width="81" height="69" border="0" />
                                </a>
                                <a href="http://www.jinjiang.com/travel/sht?cmpid=edm_12m5_shanglv" target="_blank">
                                    <img src="http://www.jinjiang.com/event/edm/12edm/img5/a6.gif" width="64" height="69" border="0" />
                                </a>
                                <a href="http://www.jinjiang.com/travel?cmpid=edm_12m5_lvyou" target="_blank">
                                    <img src="http://www.jinjiang.com/event/edm/12edm/img5/a7.gif" width="80" height="69" border="0" />
                                </a>
                                <a href="http://www.jinjiang.com/auto?cmpid=edm_12m5_zuche" target="_blank">
                                    <img src="http://www.jinjiang.com/event/edm/12edm/img5/a8.gif" width="74" height="69" border="0" />
                                </a>
                            </td>
                        </tr>
                    </table>
                    </td>
                </tr>
            </table>
        </body>
        </html>
    '''

    void testCompile() {
        def templateFramework = new TemplateFramework(htmlContent:originContent,templateName:"testTemplateName",templateCode:"10001")
        templateFramework.save()
        templateFramework.compile()
        templateFramework.save()
        mockDomain(Segment,templateFramework.segments.asList())
        domain.templateFramework = templateFramework
        domain.templateTitle = 'testTemplateTitle'
        domain.save(flush: true)
        Assert.assertEquals(domain.templateElements.size(),1)
        Assert.assertNotNull(domain.middleTemplateContent)
        Assert.assertEquals(domain)
    }
}
