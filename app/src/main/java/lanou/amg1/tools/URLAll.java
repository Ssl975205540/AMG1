package lanou.amg1.tools;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/23.
 */
public class URLAll {


    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int Five = 5;
    public static final String MORE_URL = "http://news.app.autohome.com.cn/shouye_v7.0.0/mobile/metadata.ashx?a=2&pm=2&v=7.0.7&types=newstype%7C636002832282225908%2Cvideotype%7C636002832282225908";
    // 欢迎页
    public static final String WELCOME_URL = "http://url.cn/40IP3VW";
    public static final String NEW_URL="http://app.api.autohome.com.cn/autov4.2.5/news/newslist-a2-pm1-v4.2.5-c0-nt0-p1-s30-l0.html";//最新
//    http://adnewnc.app.autohome.com.cn/advert_v7.0.0/ad/focusadandnewslistad.ashx?pageid=bbfd27d3-94d4-49ab-84c1-c8b52dd654da&lon=121.550935&cityid=0&mac=28%3Afa%3Aa0%3Aa5%3A65%3Ad7&networkid=0&appid=2&idfa=0&devicebrand=vivo&deviceid=869723021470361&devicemodel=vivoX6D&platform=2&pageindex=1&gps_city=210200&version=7.1.5&isretry=0&newstype=0&lat=38.889732
//    http://mobile.app.autohome.com.cn/mobile_v5.9.5/mobile/metadata.ashx?types=newstype%7C635990328072382159%2Cseriesnews%7C635975674429487538%2Cpiccategory%7C635975674429487538%2Clevel%7C635975674429487538%2Ccountry%7C635975674429487538%2Cprice%7C635975674429487538%2Cvideotype%7C635975674429487538%2Cjingxuan%7C636076361379610366%2Cfindorder%7C635975674429487538%2Cdealerscope%7C635975674429487538%2Cspectestdetail%7C635975674429487538%2Cseriesclubfilter%7C635975674429487538%2Cbuyorder%7C635975674429487538%2Cspeccompare%7C635975674429487538&a=2&pm=2&v=7.1.5

    //优创
    //    http://news.app.autohome.com.cn/newspf_v7.1.0/newspf/NPNewsList.ashx?a=2&pm=2&v=7.1.5&au=&type=3&lastid=&lastuid=27774674&size=30
    public static final String SHAREDPREFERENCES_WELCOMEPAGE= "welcomepage_welComePage";
    public static final String SHAREDPREFERENCES_WELCOMEPAGE_BOOLEAN= "first";
    public static final String SHAREDPREFERENCES_MAIN= "main_mainActivity";
    public final static String URL_VIEDIO = "http://app.api.autohome.com.cn/autov4.8.8/news/videolist-pm1-vt0-s30-lastid0.json";
    public static final String SHAREDPREFERENCES_ADAVERACTIVITY= "adaverActivity";

    public static final String WELCOMEPAGE_FINSH = "WelComePage.finsh";



    //发现页的URL
    public static final String DISCOVERY_PAGE = "http://223.99.255.20/mobile.app.autohome.com.cn/discover_v7.0.0/mobile/getcardlist.ashx?a=2&pm=1&v=7.0.0&uid=&deviceid=021676cd548e5cf2b6149c916a767228fac74da0&pid=0&cid=0&state=1&pageindex=1&pagesize=20&lat=0.000000&lng=0.000000&hid=";

    //第二页
    public static final String FORUM_PAGE= "http://223.99.255.20/clubnc.app.autohome.com.cn/club_v7.0.5/club/jingxuantopic.ashx?platud=2&categoryid=0&pageindex=1&pagesize=30&devicetype=android.1501_M02&deviceid=860954030358581&userid=0&operation=1&netstate=0&gps=38.889726%2C121.550943";
    public static final String FORUM_PAGE_Q = "http://forum.app.autohome.com.cn/forum_v7.0.0/forum/club/topiccontent-a2-pm2-v7.1.0-t";
    public static final String FORUM_PAGE_H = "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw360.json ";

    //第二页八个按钮
    public static final String FINCAR_ONE_PAGE_FIRST= "http://cars.app.autohome.com.cn/cars_v7.0.0/cars/getmarks-a2-pm2.json";

    //媳妇当车模
    public static final String WIFE_MODEL_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c104-p1-s30.json";
    //美人”记”
    public static final String NOTORIOUS_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c110-p1-s30.json";
    //论坛名人堂
    public static final String HOF_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c172-p1-s30.json";
    //论坛讲师
    public static final String LECTURER_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c230-p1-s30.json";
    //汽车之家十年
    public static final String CAR_HOME_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c233-p1-s30.json";

    public static final String AUSLESE_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c121-p1-s30.json";//精挑细选
    public static final String DISCOURSE_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c106-p1-s30.json";//现身说法
    public static final String HIGH_POINT_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c118-p1-s30.json";//高端阵地
    public static final String ELECTRIC_VEHICLE_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c210-p1-s30.json";//电动车
    public static final String BUY_CAR_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c199-p1-s30.json";//汇买车
    public static final String DRIVING_CRITIQUE_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c198-p1-s30.json";//行车点评
    public static final String DRIVING_MEMBER_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c168-p1-s30.json";//超级试驾员
    public static final String OVERSEAS_BUYERSL_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c113-p1-s30.json";//海外购车
    public static final String CLASSIC_CAR_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c109-p1-s30.json";//经典老车
    public static final String SISTER_CAR_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c191-p1-s30.json";//妹子选车
    public static final String PREFERENTIAL_CAR_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c196-p1-s30.json";//优惠购车
    public static final String ORIGINAL_LARGE_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c107-p1-s30.json";//原创大片
    public static final String TOP_ELEGANT_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c105-p1-s30.json";//顶配风采
    public static final String MODIFIED_RATIONAL_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c122-p1-s30.json";//改装有理
    public static final String WAY_MOTORING_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c194-p1-s30.json";//养车之道
    public static final String FIRST_CAMP_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c119-p1-s30.json";//首发阵营
    public static final String NEW_LIVE_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c112-p1-s30.json";//新车直播
    public static final String HISTORICAL_TOPIC_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c120-p1-s30.json";//历史选题
    public static final String GREAT_VIDEO_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c227-p1-s30.json";//精彩视频
    public static final String FRIEND_HEAVEN_EARTH_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c184-p1-s30.json";//摩友天地
    public static final String HONEYMOON_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c108-p1-s30.json";//蜜月之旅
    public static final String SWEET_WEDDING_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c124-p1-s30.json";//甜蜜婚礼
    public static final String PHOTOGRAPHY_CLASS_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c123-p1-s30.json";//摄影课堂
    public static final String CAR_PARTY_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c185-p1-s30.json";//车友聚会
    public static final String BIKE_TRIBE_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c186-p1-s30.json";//单车部落
    public static final String GOSSIP_CLUB_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c214-p1-s30.json";//杂谈俱乐部
    public static final String NORCO_TRAVELS_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c218-p1-s30.json";//华北游记
    public static final String SOUTHWEST_TRAVELS_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c223-p1-s30.json";//西南游记
    public static final String NORTHEAST_TRAVELS_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c221-p1-s30.json";//东北游记
    public static final String NORTHWEST_TRAVELS_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c222-p1-s30.json";//西北游记
    public static final String SKY_TRAVELS_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c220-p1-s30.json";//华中游记
    public static final String SOUTH_TRAVELS_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c224-p1-s30.json";//华南游记
    public static final String HYZ_TRAVELS_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c219-p1-s30.json";//华东游记
    public static final String MACAO_TRAVELS_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c225-p1-s30.json";//港澳台游记
    public static final String OVERSEAS_TRAVELS_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c226-p1-s30.json";//海外游记
    public static final String SEA_PEARL_URL = "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c212-p1-s30.json";//沧海遗珠

    public static final String TOP_POSTS_URL = "http://183.232.160.141/club.app.autohome.com.cn/club_v7.0.5/club/shotfoumlist-pm2-p1-s50.json";//热帖 public static final String TOP_POSTS_URL = "http://club.app.autohome.com.cn/club_v5.6.0/club/shotfoumlist-pm1-p1-s50.json";//热帖


    //第三页
    public static final  String NEWCAR_BRAND_URL ="http://223.99.255.20/cars.app.autohome.com.cn/cars_v5.7.0/cars/brands-pm2-ts635966571635589297.json";
    //第三页GirdView
    public static  final  String HOTBRAND_URL = "http://183.232.160.141/cars.app.autohome.com.cn/dealer_v6.0.0/dealer/hotbrands-pm2.json";

    public  static final  String MAINCAR_URL ="http://183.232.160.141/adnewnc.app.autohome.com.cn/advert_v7.0.0/ad/infoad.ashx?pageid=cee6fa23-52bc-4547-9ae9-ab0bc02d36d8&cityid=0&mac=74%3Aac%3A5f%3A6d%3A3c%3A93&networkid=0&appid=2&idfa=0&devicebrand=360&deviceid=860954030358581&lng=121.550946&devicemodel=1501_M02&platform=2&adtype=1&gps_city=210200&version=7.0.7&isretry=0&provinceid=210000&lat=38.889732";

    public  static final  String CITY ="http://comm.app.autohome.com.cn/news/province-pm2-ts635109755524561900.json";

    // 车商城 (webview)
    public static final String DISCOVER_CAR_MALL = "http://m.mall.autohome.com.cn/?isapp=1";
    // 分期购车 (webview)
    public static final String DISCOVER_HIRE_CAR = "http://m.mall.autohome.com.cn/finance/list/3-0-0-0-0-0-0-0-0-1.html?isapp=1";
    // 养车之家 (webview)
    public static final String DISCOVER_SUBSIDY_HOME = "http://yc.m.autohome.com.cn/banner/list?serviceCity=210200&isWz=false";
    // 找二手车 (webview)
    public static final String DISCOVER_FIND_CAR = "http://m.che168.com/beijing/list/?sourcename=main&pvareaid=101964";
    // 团购活动 (webview)
    public static final String DISCOVER_GROUP_BUY = "http://tuan.m.autohome.com.cn/app/h5/home";
    // 爱车估值 (webview)
    public static final String DISCOVER_CAR_VALUATION = "http://m.che168.com/pinggu/?buymark=oaha10027&sellmark=oaha10026&appversionbuy=2.08V&appversionsell=2.04V&hideheadfoot=1&hidead=1&pvareaid=101375";
    // 全部
    public static final String DISCOVER_ALL_URL = "http://mobile.app.autohome.com.cn/discover_v7.1.0/mobile/entrylist.ashx?pm=1";





    public static final ArrayList<Bean> ARRLIST_ALL(){

        ArrayList<Bean> arrayList = new ArrayList<>();

        arrayList.add(new Bean("媳妇当车模",URLAll.WIFE_MODEL_URL));
        arrayList.add(new Bean("美人”记”",URLAll.NOTORIOUS_URL));
        arrayList.add(new Bean("论坛红人馆",URLAll.HOF_URL));
        arrayList.add(new Bean("论坛讲师",URLAll.LECTURER_URL));
        arrayList.add(new Bean("精挑细选",URLAll.AUSLESE_URL));
        arrayList.add(new Bean("现身说法",URLAll.DISCOURSE_URL));
        arrayList.add(new Bean("高端阵地",URLAll.HIGH_POINT_URL));
        arrayList.add(new Bean("电动车",URLAll.ELECTRIC_VEHICLE_URL));
        arrayList.add(new Bean("汇买车",URLAll.BUY_CAR_URL));
        arrayList.add(new Bean("行车点评",URLAll.DRIVING_CRITIQUE_URL));
        arrayList.add(new Bean("超级试驾员",URLAll.DRIVING_MEMBER_URL));
        arrayList.add(new Bean("海外购物",URLAll.OVERSEAS_BUYERSL_URL));
        arrayList.add(new Bean("经典老车",URLAll.CLASSIC_CAR_URL));
        arrayList.add(new Bean("妹子选车",URLAll.SISTER_CAR_URL));
        arrayList.add(new Bean("优惠购车",URLAll.PREFERENTIAL_CAR_URL));
        arrayList.add(new Bean("原创大片",URLAll.ORIGINAL_LARGE_URL));
        arrayList.add(new Bean("顶配风采",URLAll.TOP_ELEGANT_URL));
        arrayList.add(new Bean("改装有理",URLAll.MODIFIED_RATIONAL_URL));
        arrayList.add(new Bean("养车有道",URLAll.WAY_MOTORING_URL));
        arrayList.add(new Bean("首发阵营",URLAll.FIRST_CAMP_URL));
        arrayList.add(new Bean("新车直播",URLAll.NEW_LIVE_URL));
        arrayList.add(new Bean("历史选题",URLAll.HISTORICAL_TOPIC_URL));
        arrayList.add(new Bean("精彩视频",URLAll.GREAT_VIDEO_URL));
        arrayList.add(new Bean("摩友天地",URLAll.FRIEND_HEAVEN_EARTH_URL));
        arrayList.add(new Bean("蜜月之旅",URLAll.HONEYMOON_URL));
        arrayList.add(new Bean("甜蜜婚礼",URLAll.SWEET_WEDDING_URL));
        arrayList.add(new Bean("摄影课堂",URLAll.PHOTOGRAPHY_CLASS_URL));
        arrayList.add(new Bean("车友聚会",URLAll.CAR_PARTY_URL));
        arrayList.add(new Bean("单车部落",URLAll.BIKE_TRIBE_URL));
        arrayList.add(new Bean("杂谈俱乐部",URLAll.GOSSIP_CLUB_URL));
        arrayList.add(new Bean("华北游记",URLAll.NORCO_TRAVELS_URL));
        arrayList.add(new Bean("西南游记",URLAll.SOUTHWEST_TRAVELS_URL));
        arrayList.add(new Bean("东北游记",URLAll.NORTHEAST_TRAVELS_URL));
        arrayList.add(new Bean("西北游记",URLAll.NORTHWEST_TRAVELS_URL));
        arrayList.add(new Bean("华中游记",URLAll.SKY_TRAVELS_URL));
        arrayList.add(new Bean("华南游记",URLAll.SOUTH_TRAVELS_URL));
        arrayList.add(new Bean("华东游记",URLAll.HYZ_TRAVELS_URL));
        arrayList.add(new Bean("港澳台游记",URLAll.MACAO_TRAVELS_URL));
        arrayList.add(new Bean("海外游记",URLAll.OVERSEAS_TRAVELS_URL));
        arrayList.add(new Bean("沧海遗珠",URLAll.SEA_PEARL_URL));
        return arrayList;


    }

   public static class Bean{

       public String title;

       public String url;

       public Bean( String title,String url) {
           this.url = url;
           this.title = title;
       }

       public String getTitle() {
           return title;
       }

       public String getUrl() {
           return url;
       }
   }


}
