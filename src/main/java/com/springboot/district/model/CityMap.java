package com.springboot.district.model;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Cher on 2019-09-05
 * <p>
 * 全国（省，直辖市，自治区）映射集合
 */
public class CityMap {

    public static String SOUTH_AREA = "南区";

    public static String NORTH_AREA = "北区";

    public static Map<String, List<String>> model = new LinkedHashMap<>();

    public static List<String> north_provinces = Arrays.asList("北京", "甘肃", "河北", "河南", "黑龙江", "吉林", "辽宁",
            "内蒙", "山东", "山西", "陕西", "新疆", "西藏", "四川", "青海", "宁夏", "天津", "重庆");

    public static List<String> south_provinces = Arrays.asList("安徽", "福建", "广东", "广西", "贵州", "湖北", "湖南", "江苏",
            "江西", "上海", "云南", "浙江", "海南");

    static {
        model.put("北京", Arrays.asList("北京"));
        model.put("上海", Arrays.asList("上海"));
        model.put("天津", Arrays.asList("天津"));
        model.put("重庆", Arrays.asList("重庆"));
        model.put("黑龙江", Arrays.asList("哈尔滨", "齐齐哈尔", "牡丹江", "大庆", "伊春", "双鸭山", "鹤岗", "鸡西", "佳木斯", "七台河", "黑河", "绥化", "大兴安岭"));
        model.put("吉林", Arrays.asList("长春", "延边", "吉林", "白山", "白城", "四平", "松原", "辽源", "大安", "通化"));
        model.put("辽宁", Arrays.asList("沈阳", "大连", "葫芦岛", "旅顺", "本溪", "抚顺", "铁岭", "辽阳", "营口", "阜新", "朝阳", "锦州", "丹东", "鞍山"));
        model.put("内蒙", Arrays.asList("呼和浩特", "呼伦贝尔", "锡林浩特", "包头", "赤峰", "海拉尔", "乌海", "鄂尔多斯", "通辽"));
        model.put("河北", Arrays.asList("石家庄", "唐山", "张家口", "廊坊", "邢台", "邯郸", "沧州", "衡水", "承德", "保定", "秦皇岛"));
        model.put("河南", Arrays.asList("郑州", "开封", "洛阳", "平顶山", "焦作", "鹤壁", "新乡", "安阳", "濮阳", "许昌", "漯河", "三门峡", "南阳", "商丘", "信阳", "周口", "驻马店"));
        model.put("山东", Arrays.asList("济南", "青岛", "淄博", "威海", "曲阜", "临沂", "烟台", "枣庄", "聊城", "济宁", "菏泽", "泰安", "日照", "东营", "德州", "滨州", "莱芜", "潍坊"));
        model.put("山西", Arrays.asList("太原", "阳泉", "晋城", "晋中", "临汾", "运城", "长治", "朔州", "忻州", "大同", "吕梁"));
        model.put("江苏", Arrays.asList("南京", "苏州", "昆山", "南通", "太仓", "吴县", "徐州", "宜兴", "镇江", "淮安", "常熟", "盐城", "泰州", "无锡", "连云港", "扬州", "常州", "宿迁"));
        model.put("安徽", Arrays.asList("合肥", "巢湖", "蚌埠", "安庆", "六安", "滁州", "马鞍山", "阜阳", "宣城", "铜陵", "淮北", "芜湖", "毫州", "宿州", "淮南", "池州"));
        model.put("陕西", Arrays.asList("西安", "韩城", "安康", "汉中", "宝鸡", "咸阳", "榆林", "渭南", "商洛", "铜川", "延安"));
        model.put("宁夏", Arrays.asList("银川", "固原", "中卫", "石嘴山", "吴忠"));
        model.put("甘肃", Arrays.asList("兰州", "白银", "庆阳", "酒泉", "天水", "武威", "张掖", "甘南", "临夏", "平凉", "定西", "金昌"));
        model.put("青海", Arrays.asList("西宁", "海北", "海西", "黄南", "果洛", "玉树", "海东", "海南"));
        model.put("湖北", Arrays.asList("武汉", "宜昌", "黄冈", "恩施", "荆州", "神农架", "十堰", "咸宁", "襄樊", "孝感", "随州", "黄石", "荆门", "鄂州"));
        model.put("湖南", Arrays.asList("长沙", "邵阳", "常德", "郴州", "吉首", "株洲", "娄底", "湘潭", "益阳", "永州", "岳阳", "衡阳", "怀化", "韶山", "张家界"));
        model.put("浙江", Arrays.asList("杭州", "湖州", "金华", "宁波", "丽水", "绍兴", "雁荡山", "衢州", "嘉兴", "台州", "舟山", "温州"));
        model.put("江西", Arrays.asList("南昌", "萍乡", "九江", "上饶", "抚州", "吉安", "鹰潭", "宜春", "新余", "景德镇", "赣州"));
        model.put("福建", Arrays.asList("福州", "厦门", "龙岩", "南平", "宁德", "莆田", "泉州", "三明", "漳州"));
        model.put("贵州", Arrays.asList("贵阳", "安顺", "赤水", "遵义", "铜仁", "六盘水", "毕节", "凯里", "都匀"));
        model.put("四川", Arrays.asList("成都", "泸州", "内江", "凉山", "阿坝", "巴中", "广元", "乐山", "绵阳", "德阳", "攀枝花", "雅安", "宜宾", "自贡", "甘孜州", "达州", "资阳", "广安", "遂宁", "眉山", "南充"));
        model.put("广东", Arrays.asList("广州", "深圳", "潮州", "韶关", "湛江", "惠州", "清远", "东莞", "江门", "茂名", "肇庆", "汕尾", "河源", "揭阳", "梅州", "中山", "德庆", "阳江", "云浮", "珠海", "汕头", "佛山"));
        model.put("广西", Arrays.asList("南宁", "桂林", "阳朔", "柳州", "梧州", "玉林", "桂平", "贺州", "钦州", "贵港", "防城港", "百色", "北海", "河池", "来宾", "崇左"));
        model.put("云南", Arrays.asList("昆明", "保山", "楚雄", "德宏", "红河", "临沧", "怒江", "曲靖", "思茅", "文山", "玉溪", "昭通", "丽江", "大理"));
        model.put("海南", Arrays.asList("海口", "三亚", "儋州", "琼山", "通什", "文昌"));
        model.put("新疆", Arrays.asList("乌鲁木齐", "阿勒泰", "阿克苏", "昌吉", "哈密", "和田", "喀什", "克拉玛依", "石河子", "塔城", "库尔勒", "吐鲁番", "伊宁"));
        model.put("西藏", Arrays.asList("拉萨"));
    }

    public static List<String> getKeys(String province) {
        List<String> provinces = new ArrayList<>(model.keySet());
        if (Strings.isNullOrEmpty(province)) {
            return provinces;
        }
        return provinces.stream()
                .filter(p -> p.contains(province))
                .collect(Collectors.toList());
    }

    public static List<String> getKeyValue(String province) {
        for (String str : model.keySet()) {
            if (str.equals(province)) {
                return model.get(str);
            }
        }
        return null;
    }
}
