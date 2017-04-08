package com.goldenchef.company.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/3/2.
 */
public class PickerViewUtils {

    public static List<String> getExperience(){
        List<String> list = new ArrayList<>();

        list.add("不限");
        list.add("无");
        list.add("1年以内");
        list.add("1-3年");
        list.add("3-5年");
        list.add("5年以上");

        return list;
    }

    public static List<String> getAge(){
        List<String> list = new ArrayList<>();

        list.add("不限");
        list.add("18-30");
        list.add("30-50");

        return list;
    }

    public static List<String> getCompanyNum(){
        List<String> list = new ArrayList<>();

        list.add("10人以下");
        list.add("10-20人");
        list.add("20-50人");
        list.add("50人以上");

        return list;
    }

    public static List<String> getProviceList() {
        List<String> list = new ArrayList<>();
        list.add("北京");
        list.add("上海");
        list.add("广东");
        list.add("浙江");
        list.add("天津");
        list.add("重庆");
        list.add("河北");
        list.add("山西");
        list.add("辽宁");
        list.add("吉林");
        list.add("黑龙江");
        list.add("江苏");
        list.add("安徽");
        list.add("福建");
        list.add("江西");
        list.add("山东");
        list.add("河南");
        list.add("湖北");
        list.add("湖南");
        list.add("海南");
        list.add("四川");
        list.add("贵州");
        list.add("云南");
        list.add("陕西");
        list.add("甘肃");
        list.add("青海");
        list.add("台湾");
        list.add("内蒙古");
        list.add("广西");
        list.add("西藏");
        list.add("宁夏");
        list.add("新疆");
        list.add("香港");
        list.add("澳门");
        return list;
    }

    public static List<List<String>> getCityList() {
        List<List<String>> list = new ArrayList<>();
        List<String> beijing = new ArrayList<>();
        beijing.add("北京");

        List<String> shanghai = new ArrayList<>();
        shanghai.add("上海");

        List<String> guangdong = new ArrayList<>();
        guangdong.add("广州");
        guangdong.add("深圳");
        guangdong.add("珠海");
        guangdong.add("汕头");
        guangdong.add("惠州");
        guangdong.add("韶关");
        guangdong.add("佛山");
        guangdong.add("江门");
        guangdong.add("湛江");
        guangdong.add("茂名");
        guangdong.add("肇庆");
        guangdong.add("梅州");
        guangdong.add("汕尾");
        guangdong.add("河源");
        guangdong.add("阳江");
        guangdong.add("清远");
        guangdong.add("东莞");
        guangdong.add("中山");
        guangdong.add("潮州");
        guangdong.add("揭阳");
        guangdong.add("云浮");

        List<String> zhejiang = new ArrayList<>();
        zhejiang.add("杭州");
        zhejiang.add("湖州");
        zhejiang.add("嘉兴");
        zhejiang.add("宁波");
        zhejiang.add("绍兴");
        zhejiang.add("温州");
        zhejiang.add("台州");
        zhejiang.add("丽水");
        zhejiang.add("金华");
        zhejiang.add("衢州");
        zhejiang.add("舟山");
        zhejiang.add("义务");
        zhejiang.add("慈溪");
        zhejiang.add("海宁");
        zhejiang.add("萧山");

        List<String> tianjing = new ArrayList<>();
        tianjing.add("天津");

        List<String> chongqing = new ArrayList<>();
        chongqing.add("重庆");

        List<String> hebei = new ArrayList<>();
        hebei.add("石家庄");
        hebei.add("保定");
        hebei.add("张家口");
        hebei.add("承德");
        hebei.add("唐山");
        hebei.add("廊坊");
        hebei.add("沧州");
        hebei.add("衡水");
        hebei.add("邢台");
        hebei.add("邯郸");
        hebei.add("秦皇岛");
        hebei.add("迁安");

        List<String> shanxi = new ArrayList<>();
        shanxi.add("太原");
        shanxi.add("大同");
        shanxi.add("阳泉");
        shanxi.add("晋中");
        shanxi.add("长治");
        shanxi.add("晋城");
        shanxi.add("临汾");
        shanxi.add("运城");
        shanxi.add("朔州");
        shanxi.add("忻州");
        shanxi.add("吕梁");
        shanxi.add("汾阳");

        List<String> liaoning = new ArrayList<>();
        liaoning.add("沈阳");
        liaoning.add("大连");
        liaoning.add("鞍山");
        liaoning.add("抚顺");
        liaoning.add("本溪");
        liaoning.add("丹东");
        liaoning.add("锦州");
        liaoning.add("营口");
        liaoning.add("阜新");
        liaoning.add("辽阳");
        liaoning.add("铁岭");
        liaoning.add("朝阳");
        liaoning.add("盘锦");
        liaoning.add("葫芦岛");

        List<String> jilin = new ArrayList<>();
        jilin.add("长春");
        jilin.add("吉林");
        jilin.add("四平");
        jilin.add("通化");
        jilin.add("白城");
        jilin.add("辽源");
        jilin.add("松原");
        jilin.add("白山");
        jilin.add("延边");
        jilin.add("延吉");

        List<String> heilongjiang = new ArrayList<>();
        heilongjiang.add("哈尔滨");
        heilongjiang.add("齐齐哈尔");
        heilongjiang.add("牡丹江");
        heilongjiang.add("佳木斯");
        heilongjiang.add("绥化");
        heilongjiang.add("黑河");
        heilongjiang.add("伊春");
        heilongjiang.add("大庆");
        heilongjiang.add("七台河");
        heilongjiang.add("鸡西");
        heilongjiang.add("鹤岗");
        heilongjiang.add("双鸭山");
        heilongjiang.add("大兴安岭");

        List<String> jiangsu = new ArrayList<>();
        jiangsu.add("南京");
        jiangsu.add("无锡");
        jiangsu.add("镇江");
        jiangsu.add("苏州");
        jiangsu.add("南通");
        jiangsu.add("扬州");
        jiangsu.add("盐城");
        jiangsu.add("徐州");
        jiangsu.add("淮安");
        jiangsu.add("连云港");
        jiangsu.add("常州");
        jiangsu.add("泰州");
        jiangsu.add("宿迁");
        jiangsu.add("昆山");
        jiangsu.add("海门");
        jiangsu.add("常熟");
        jiangsu.add("泗洪");
        jiangsu.add("丹阳");
        jiangsu.add("吴江");
        jiangsu.add("太仓");
        jiangsu.add("宜兴");
        jiangsu.add("张家港");
        jiangsu.add("江阴");
        jiangsu.add("溧阳");
        jiangsu.add("靖江");

        List<String> anhui = new ArrayList<>();
        anhui.add("合肥");
        anhui.add("蚌埠");
        anhui.add("芜湖");
        anhui.add("淮南");
        anhui.add("马鞍山");
        anhui.add("安庆");
        anhui.add("宿州");
        anhui.add("阜阳");
        anhui.add("毫州");
        anhui.add("滁州");
        anhui.add("淮北");
        anhui.add("铜陵");
        anhui.add("宣城");
        anhui.add("六安");
        anhui.add("池州");
        anhui.add("黄山");
        anhui.add("巢湖");

        List<String> fujian = new ArrayList<>();
        fujian.add("福州");
        fujian.add("厦门");
        fujian.add("宁德");
        fujian.add("莆田");
        fujian.add("泉州");
        fujian.add("漳州");
        fujian.add("龙岩");
        fujian.add("三明");
        fujian.add("南平");

        List<String> jiangxi = new ArrayList<>();
        jiangxi.add("南昌");
        jiangxi.add("九江");
        jiangxi.add("上饶");
        jiangxi.add("抚州");
        jiangxi.add("宜春");
        jiangxi.add("吉安");
        jiangxi.add("赣州");
        jiangxi.add("景德镇");
        jiangxi.add("萍乡");
        jiangxi.add("新余");
        jiangxi.add("鹰潭");
        jiangxi.add("贵溪");

        List<String> shandong = new ArrayList<>();
        shandong.add("济南");
        shandong.add("青岛");
        shandong.add("德州");
        shandong.add("烟台");
        shandong.add("潍坊");
        shandong.add("济宁");
        shandong.add("泰安");
        shandong.add("临沂");
        shandong.add("菏泽");
        shandong.add("滨州");
        shandong.add("东营");
        shandong.add("威海");
        shandong.add("枣庄");
        shandong.add("日照");
        shandong.add("莱芜");
        shandong.add("聊城");
        shandong.add("龙口");
        shandong.add("淄博");

        List<String> henan = new ArrayList<>();
        henan.add("郑州");
        henan.add("安阳");
        henan.add("新乡");
        henan.add("许昌");
        henan.add("平顶山");
        henan.add("信阳");
        henan.add("南阳");
        henan.add("开封");
        henan.add("洛阳");
        henan.add("商丘");
        henan.add("焦作");
        henan.add("鹤壁");
        henan.add("濮阳");
        henan.add("周口");
        henan.add("漯河");
        henan.add("驻马店");
        henan.add("三门峡");
        henan.add("济源");

        List<String> hubei = new ArrayList<>();
        hubei.add("武汉");
        hubei.add("襄阳");
        hubei.add("鄂州");
        hubei.add("孝感");
        hubei.add("黄冈");
        hubei.add("黄石");
        hubei.add("咸宁");
        hubei.add("荆州");
        hubei.add("宜昌");
        hubei.add("十堰");
        hubei.add("随州");
        hubei.add("荆门");
        hubei.add("恩施");
        hubei.add("襄樊");
        hubei.add("仙桃");
        hubei.add("天门");
        hubei.add("潜江");

        List<String> hunan = new ArrayList<>();
        hunan.add("长沙");
        hunan.add("湘潭");
        hunan.add("株洲");
        hunan.add("衡阳");
        hunan.add("郴州");
        hunan.add("常德");
        hunan.add("益阳");
        hunan.add("娄底");
        hunan.add("邵阳");
        hunan.add("岳阳");
        hunan.add("张家界");
        hunan.add("怀化");
        hunan.add("永州");
        hunan.add("湘西");

        List<String> hainan = new ArrayList<>();
        hainan.add("海口");
        hainan.add("三亚");
        hainan.add("三沙");
        hainan.add("琼海");

        List<String> sichuan = new ArrayList<>();
        sichuan.add("成都");
        sichuan.add("攀枝花");
        sichuan.add("自贡");
        sichuan.add("绵阳");
        sichuan.add("南充");
        sichuan.add("达州");
        sichuan.add("遂宁");
        sichuan.add("广安");
        sichuan.add("巴中");
        sichuan.add("泸州");
        sichuan.add("宜宾");
        sichuan.add("内江");
        sichuan.add("资阳");
        sichuan.add("乐山");
        sichuan.add("眉山");
        sichuan.add("雅安");
        sichuan.add("德阳");
        sichuan.add("广元");
        sichuan.add("阿坝");
        sichuan.add("凉山");
        sichuan.add("甘孜");
        sichuan.add("西昌");

        List<String> guizhou = new ArrayList<>();
        guizhou.add("贵阳");
        guizhou.add("遵义");
        guizhou.add("安顺");
        guizhou.add("铜仁");
        guizhou.add("毕节");
        guizhou.add("六盘水");
        guizhou.add("黔东南");
        guizhou.add("黔南");
        guizhou.add("黔西南");

        List<String> yunnan = new ArrayList<>();
        yunnan.add("昆明");
        yunnan.add("曲靖");
        yunnan.add("保山");
        yunnan.add("玉溪");
        yunnan.add("普洱");
        yunnan.add("大理");
        yunnan.add("昭通");
        yunnan.add("临沧");
        yunnan.add("丽江");
        yunnan.add("西双版纳");
        yunnan.add("文山");
        yunnan.add("红河");
        yunnan.add("德宏");
        yunnan.add("怒江");
        yunnan.add("迪庆");
        yunnan.add("楚雄");
        yunnan.add("红河州");

        List<String> shanxi2 = new ArrayList<>();
        shanxi2.add("西安");
        shanxi2.add("咸阳");
        shanxi2.add("长安");
        shanxi2.add("延安");
        shanxi2.add("榆林");
        shanxi2.add("漯南");
        shanxi2.add("商洛");
        shanxi2.add("安康");
        shanxi2.add("汉中");
        shanxi2.add("宝鸡");
        shanxi2.add("铜川");

        List<String> gansu = new ArrayList<>();
        gansu.add("兰州");
        gansu.add("定西");
        gansu.add("平凉");
        gansu.add("庆阳");
        gansu.add("武威");
        gansu.add("金昌");
        gansu.add("张掖");
        gansu.add("酒泉");
        gansu.add("天水");
        gansu.add("白银");
        gansu.add("陇南");
        gansu.add("嘉峪关");
        gansu.add("临夏");
        gansu.add("甘南");

        List<String> qinghai = new ArrayList<>();
        qinghai.add("西宁");
        qinghai.add("海东");
        qinghai.add("海北");
        qinghai.add("黄南");
        qinghai.add("海南");
        qinghai.add("果洛");
        qinghai.add("玉树");
        qinghai.add("海西");

        List<String> taiwan = new ArrayList<>();
        taiwan.add("台北");
        taiwan.add("新北");
        taiwan.add("台中");
        taiwan.add("台南");
        taiwan.add("高雄");
        taiwan.add("基隆");
        taiwan.add("嘉义");
        taiwan.add("屏东");

        List<String> neimenggu = new ArrayList<>();
        neimenggu.add("呼和浩特");
        neimenggu.add("包头");
        neimenggu.add("乌海");
        neimenggu.add("通辽");
        neimenggu.add("赤峰");
        neimenggu.add("鄂尔多斯");
        neimenggu.add("呼伦贝尔");
        neimenggu.add("巴彦淖尔");
        neimenggu.add("乌兰察布");
        neimenggu.add("阿兰联盟");
        neimenggu.add("锡林郭勒");

        List<String> guangxi = new ArrayList<>();
        guangxi.add("南宁");
        guangxi.add("崇左");
        guangxi.add("柳州");
        guangxi.add("来宾");
        guangxi.add("桂林");
        guangxi.add("梧州");
        guangxi.add("贺州");
        guangxi.add("贵港");
        guangxi.add("玉林");
        guangxi.add("百色");
        guangxi.add("钦州");
        guangxi.add("河池");
        guangxi.add("北海");
        guangxi.add("防城港");
        guangxi.add("兴安");

        List<String> xizang = new ArrayList<>();
        xizang.add("拉萨");
        xizang.add("日喀则");

        List<String> ningxia = new ArrayList<>();
        ningxia.add("银川");
        ningxia.add("石嘴山");
        ningxia.add("吴忠");
        ningxia.add("固原");
        ningxia.add("中卫");

        List<String> xinjiang = new ArrayList<>();
        xinjiang.add("乌鲁木齐");
        xinjiang.add("克拉玛依");
        xinjiang.add("昌吉");
        xinjiang.add("巴音郭楞");
        xinjiang.add("博尔塔拉");
        xinjiang.add("伊犁");
        xinjiang.add("克孜勒苏柯尔克孜");
        xinjiang.add("哈密");
        xinjiang.add("喀什地区");
        xinjiang.add("石河子");
        xinjiang.add("阿克苏");
        xinjiang.add("和田");

        List<String> xianggang = new ArrayList<>();
        xianggang.add("香港");

        List<String> aomen = new ArrayList<>();
        aomen.add("澳门");

        list.add(beijing);
        list.add(shanghai);
        list.add(guangdong);
        list.add(zhejiang);
        list.add(tianjing);
        list.add(chongqing);
        list.add(hebei);
        list.add(shanxi);
        list.add(liaoning);
        list.add(jilin);
        list.add(heilongjiang);
        list.add(jiangsu);
        list.add(anhui);
        list.add(fujian);
        list.add(jiangxi);
        list.add(shandong);
        list.add(henan);
        list.add(hubei);
        list.add(hunan);
        list.add(hainan);
        list.add(sichuan);
        list.add(guizhou);
        list.add(yunnan);
        list.add(shanxi2);
        list.add(gansu);
        list.add(qinghai);
        list.add(taiwan);
        list.add(neimenggu);
        list.add(guangxi);
        list.add(xizang);
        list.add(ningxia);
        list.add(xinjiang);
        list.add(xianggang);
        list.add(aomen);
        return list;
    }

}
