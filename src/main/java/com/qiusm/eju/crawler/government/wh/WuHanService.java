package com.qiusm.eju.crawler.government.wh;

import com.qiusm.eju.crawler.government.GovernmentBaseService;
import com.qiusm.eju.crawler.government.base.utils.CommonUtils;
import com.qiusm.eju.crawler.government.wh.dao.*;
import com.qiusm.eju.crawler.government.wh.entity.*;
import com.qiusm.eju.crawler.utils.FileUtils;
import com.qiusm.eju.crawler.utils.ImageReaderUtils;
import com.qiusm.eju.crawler.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static com.qiusm.eju.crawler.constant.CharacterSet.GBK;
import static com.qiusm.eju.crawler.constant.SymbolicConstant.COMMA;


/**
 * 武汉政府源数据采集
 *
 * @author qiushengming
 * @date 2021年6月23日
 */
@Slf4j
@Service
@EnableConfigurationProperties(WuHanProperties.class)
public class WuHanService extends GovernmentBaseService {

    private static final String CITY_NAME = "武汉";
    /**
     * 初始种子
     */
    private static final String INITIAL_SEED_URL = "http://119.97.201.22:8083/search/spfxmcx/spfcx_index.aspx";

    /**
     * 楼盘项目列表.翻页key
     */
    private static final String EVENT_ARGUMENT = "__EVENTARGUMENT";
    private static final String EVENT_TARGET = "__EVENTTARGET";
    private static final String VIEW_STATE_GENERATOR = "__VIEWSTATEGENERATOR";
    private static final String VIEW_STATE = "__VIEWSTATE";
    private static final String VIEW_STATE_VALUE = "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";

    private static final String WU_HAN_DOMAIN_PREFIX = "http://119.97.201.22:8080/";

    static {
        ERROR_MSG.addAll(Arrays.asList("<h1>您的网络存在异常！</h1>,<h1>网络存在异常！</h1>".split(COMMA)));
    }

    @Resource
    private FdWuhanHouseMapper houseMapper;

    @Resource
    private FdWuhanGtzMapper gtzMapper;

    @Resource
    private FdWuhanGhxkzMapper ghxkzMapper;

    @Resource
    private FdWuhanKfsMapper kfsMapper;

    @Resource
    private FdWuhanBuildingMapper buildingMapper;

    @Resource
    private FdWuhanUnitMapper unitMapper;

    @Resource
    private WuHanDao dao;

    /**
     * 首页列表翻页参数
     */
    private final Map<String, String> homePageFlipParams;

    private final ExecutorService executor;
    private final ExecutorService unitExecutor;
    private final WuHanProperties properties;

    /**
     * 当前采集的页码
     */
    private int nowPage = 1;

    private boolean isStop = false;

    public WuHanService(WuHanProperties properties) {
        this.properties = properties;

        executor = CommonUtils.newFixedThreadPool("u_pool", this.properties.getMaxThreadCoreCount(), 20L);
        unitExecutor = CommonUtils.newFixedThreadPool("ud_pool", this.properties.getMaxThreadCoreCount(), 20L);

        // 首页翻页
        homePageFlipParams = new HashMap<>(8);
        homePageFlipParams.put(EVENT_TARGET, "AspNetPager1");
        homePageFlipParams.put(VIEW_STATE_GENERATOR, "24525601");
        homePageFlipParams.put(VIEW_STATE, VIEW_STATE_VALUE);
    }

    public void stop() {
        isStop = true;
    }

    /**
     * 初始启动方法
     */
    public void start() {
        String htmlStr = httpGetBody(INITIAL_SEED_URL, null, "xmList1");
        log.info("开始第1页的采集");
        xmListParser(INITIAL_SEED_URL, htmlStr);
        Document doc = Jsoup.parse(htmlStr);
        // 获取总页数
        Element pageEle = doc.getElementById("AspNetPager1");
        String pageStr = pageEle.select("font").get(0).text();
        int totalPage = Integer.parseInt(pageStr);
        for (int i = 102; i < totalPage + 1; i++) {
            nowPage = i;
            Map<String, String> params = new HashMap<>(8);
            params.putAll(homePageFlipParams);
            params.put(EVENT_ARGUMENT, String.valueOf(i));
            xmList(params);
            if (isStop) {
                break;
            }
        }
        isStop = false;
    }

    /**
     * 楼栋缺失补偿
     */
    public void houseStart() {
        List<FdWuhanHouse> houseList = houseMapper.selectByExample(null);
        houseList.forEach(house -> {
            unitExecutor.submit(() -> {
                if (StringUtils.endsWith(house.getProjectId(), "back")) {
                    return;
                }
                FdWuhanBuildingExample buildingExample = new FdWuhanBuildingExample();
                buildingExample.createCriteria().andHouseIdEqualTo(house.getId());
                long buildingSize = buildingMapper.countByExample(buildingExample);
                if (buildingSize > 0) {
                    return;
                }
                String url = "http://119.97.201.22:8083/search/spfxmcx/spfcx_lpb.aspx?DengJH=" + StringUtils.encode(house.getProjectId(), GBK);
                log.info("id:{}, pname:{}, pId{}", house.getId(), house.getProjectName(), house.getProjectId());
                try {
                    // 不相等时，则重请求进行界面加载
                    buildingList(url, house);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }

    /**
     * 楼栋信息获取失败，进行补偿获取。<br>
     * 逻辑：从building表中获取start=0的数据。并且，调用`unitList`方法<br>
     */
    public void buildingStart() {
        Long maxId = 0L;
        List<FdWuhanBuilding> var1;
        while ((var1 = buildingMapper.selectByPendLoadHouseInfo(maxId)).size() > 0) {
            maxId = var1.get(var1.size() - 1).getId();
            //选择待加载房屋信息
            for (FdWuhanBuilding building : var1) {
                unitExecutor.submit(() -> {
                    try {
                        String newReqUrl = building.getUrl();
                        List<FdWuhanUnit> units = unitList(newReqUrl, building);

                        // 存储数据
                        buildingMapper.updateByPrimaryKey(building);
                        dao.batchSave(units);

                        log.info("id:{}, pname:{}, pId:{}, bId:{}", building.getHouseId(), building.getProjectName(),
                                building.getProjectId(), building.getId());
                    } catch (Exception e) {
                        log.info("id:{}, pname:{}, pId:{}, bId:{}, error:{}", building.getHouseId(), building.getProjectName(),
                                building.getProjectId(), building.getId(), e.getMessage());
                    }
                });
            }
        }
    }

    public void unitStart() {
        log.info("1.线程：{}，开始抓取房屋详情数据。", Thread.currentThread().getName());
        Long maxId = 0L;
        while (true) {
            try {
                // 查询需要处理的房屋列表按照楼栋为一个处理单位
                List<FdWuhanBuilding> buildingList = buildingMapper.selectByPendLoadHouseDetail(maxId);
                maxId = buildingList.get(buildingList.size() - 1).getId();
                if (buildingList.isEmpty()) {
                    break;
                }

                for (FdWuhanBuilding building : buildingList) {
                    String newReqUrl = building.getUrl();

                    Map<String, FdWuhanUnit> unitMapDb = dao.selectBuildingIdByHouseUnit(building);

                    if (unitMapDb == null) {
                        continue;
                    }

                    // 从页面中解析出来的房屋信息
                    List<FdWuhanUnit> unitsParser = unitList(newReqUrl, building);
                    log.info("task : bId:{}, projectId:{}, unit size:{}", building.getId(), building.getProjectId(), unitsParser.size());

                    for (FdWuhanUnit unitParser : unitsParser) {
                        // 判断当前房屋是否需要更新房屋信息
                        // 1. 当房屋的detailUrl is not null && houseAddress is null时，则需要进行更新
                        if (StringUtils.isEmpty(unitParser.getDetailsUrl())) {
                            continue;
                        }

                        String key = String.format("%s,%s,%s", unitParser.getUnitId(), unitParser.getNominalFloor(), unitParser.getRoomNo());
                        FdWuhanUnit unitDb = unitMapDb.get(key);
                        if (unitDb == null) {
                            continue;
                        }

                        unitDb.setDetailsUrl(unitParser.getDetailsUrl());
                        unitExecutor.submit(() -> {
                            unitDetail(unitDb.getDetailsUrl(), unitDb);

                            // 重试成功则将状态设置为ok.1,否则设置为failure。失败的数据则后续手动处理。
                            if (!StringUtils.isEmpty(unitDb.getHouseAddress())) {
                                unitDb.setStatus("ok.1");
                            } else {
                                unitDb.setStatus("failure");
                            }
                            log.info("bid:{}, uid:{}, {}, {}", unitDb.getBuildingId(), unitDb.getId(),
                                    unitDb.getStatus(), unitDb.getDetailsUrl());
                            unitMapper.updateByPrimaryKey(unitDb);
                        });
                    }
                }

                //优先级，运行中资源未满，排队中  更新状态排队中
                // Thread.sleep(TimeUnit.SECONDS.toMillis(10));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.info("2.线程：{}，结束抓取房屋详情数据。", Thread.currentThread().getName());
    }

    /**
     * 项目列表解析
     *
     * @param params params
     */
    private void xmList(Map<String, String> params) {
        String htmlStrXm = httpPostBodyGbk(INITIAL_SEED_URL, params, "xmList");
        xmListParser(INITIAL_SEED_URL, htmlStrXm);
    }

    private void xmListParser(String requestUrl, String htmlStrXm) {
        Document doc = Jsoup.parse(htmlStrXm);
        // 1. 获取table
        Element tables = doc.getElementById("tables");
        // 2. 获取所有行
        Elements trs = tables.getElementsByTag("tr");
        // 3. 遍历所有行
        int i = 1;
        for (Element tr : trs) {
            try {
                // 第一行是标题行
                if (StringUtils.equals(tr.attr("class"), "tab_title")) {
                    continue;
                }
                Elements tds = tr.getElementsByTag("td");
                // 1，2，3，4，5，6
                FdWuhanHouse house = new FdWuhanHouse();
                /*house.setId(houseMapper.selectMaxId() + 1);*/
                // 城市
                house.setCityName(CITY_NAME);
                // 项目名称
                house.setProjectName(tds.get(0).text());
                // 批准销售套数
                house.setApprovalPresaleHouseNum(tds.get(1).text());
                // 住宅已售套数
                house.setHouseSoldNum(tds.get(2).text());
                // 住宅未售套数
                house.setHouseUnsaleNum(tds.get(3).text());
                // 非住宅已售套数
                house.setNonHouseSoldNum(tds.get(4).text());
                // 非住宅未售套数
                house.setNonHouseUnsoldNum(tds.get(5).text());
                // 请求url
                house.setUrl(requestUrl);

                Elements aTags = tr.getElementsByTag("a");
                Element aTag = aTags.get(0);

                // 项目ID
                String href = aTag.attr("href");
                house.setProjectId(href.split("=")[1]);

                if (checkHouse(house)) {
                    log.info("开始第{}页第{}行的采集。项目：{},{} 已采集。",
                            nowPage, i++, house.getProjectName(), house.getProjectId());
                    continue;
                } else {
                    log.info("开始第{}页第{}行的采集。正在采集项目：{},{}。预计总套数：{}",
                            nowPage, i++, house.getProjectName(), house.getProjectId(), house.getApprovalPresaleHouseNum());
                }

                String newReqUrl = buildUrl(requestUrl, aTag.attr("href"));
                xmDetail(newReqUrl, house);

                // 已行为单位进行存储数据 （已一个完整的项目为单位进行存储）
                // flush();
            } catch (Exception ex) {
                log.error("{}", ex.getMessage());
                ex.printStackTrace();
            }

            if (isStop) {
                log.info("停止运行！");
                break;
            }
        }
    }

    /**
     * 校验当前项目是否已经采集过了，且具备完整性。 <br>
     * 判断依据：projectId&projectName是否存在，存在就认为是完整<br>
     * 1. 优化：需要增加判定数据的完整性，目前先不管了。后续优化<br>
     * 2. 大概率是楼栋跳转到房屋列表时失败，后续增加这个的补充抓取。
     *
     * @param house house
     * @return
     */
    private boolean checkHouse(FdWuhanHouse house) {
        FdWuhanHouseExample example = new FdWuhanHouseExample();
        example.createCriteria()
                .andProjectIdEqualTo(house.getProjectId())
                .andProjectNameEqualTo(house.getProjectName());
        return houseMapper.countByExample(example) > 0;
    }

    private void xmDetail(String requestUrl, FdWuhanHouse house) {
        String htmlStr = httpGetBody(requestUrl, null, "xmDetail");
        xmDetailParser(requestUrl, htmlStr, house);
    }

    /**
     * 项目明细
     *
     * @param requestUrl requestUrl
     * @param htmlStr    htmlStr
     * @param house      house
     */
    private void xmDetailParser(String requestUrl, String htmlStr, FdWuhanHouse house) {
        house.setUrl(requestUrl);

        Document doc = Jsoup.parse(htmlStr);

        // 这个table_mx可能会是空的，空的话就后续再补充抓了
        Element table = doc.getElementById("table_mx");
        // 项目名称
        String projectName7 = table.getElementById("txt_xmmc1").text();
        String projectName8 = table.getElementById("txt_xmmc2").text();
        if (!StringUtils.equals(projectName7, projectName8)) {
            projectName7 += ',' + projectName8;
        }
        // 项目坐落
        String projectAddress = table.getElementById("txt_xmzl").text();
        // 开发企业
        String ghxkzConstructionUnit = table.getElementById("txt_kfqy").text();
        // 联系电话
        String consultingTelephone = table.getElementById("txt_lxdh").text();
        // 合同备案办理部门
        String crhd = table.getElementById("txt_xmbajg").text();
        // 房屋栋数
        String builndingNum = table.getElementById("txt_fwds").text();
        // 用地面积 在该页面返回都是【详见栋列表】
        String baseArea = table.getElementById("txt_ydmj").text();
        // 房屋套数
        String houseNum = table.getElementById("txt_fwts").text();

        // 房地基础信息
        house.setProjectName(projectName7);
        house.setProjectAddress(projectAddress);
        house.setContractRecordHandlingDepartment(crhd);
        houseMapper.insert(house);

        // 房地国土证信息
        FdWuhanGtz gtz = new FdWuhanGtz();
        gtz.setCityName(CITY_NAME);
        gtz.setUrl(requestUrl);
        gtz.setHouseId(house.getId());
        gtz.setProjectId(house.getProjectId());
        // 国有土地使用证
        gtz.setGtzNo(table.getElementById("txt_gytdsyzh").text());


        // 规划许可证信息
        FdWuhanGhxkz ghxkz = new FdWuhanGhxkz();
        ghxkz.setCityName(CITY_NAME);
        ghxkz.setUrl(requestUrl);
        ghxkz.setHouseId(house.getId());
        ghxkz.setProjectId(house.getProjectId());
        // 开发企业
        ghxkz.setGhxkzConstructionUnit(ghxkzConstructionUnit);
        // 建设工程规划许可
        ghxkz.setGhxkzNo(table.getElementById("txt_jsgcxkz").text());
        // 栋数
        ghxkz.setBuilndingNum(builndingNum);
        // 基地面积
        ghxkz.setBaseArea(baseArea);
        // 居住户数
        ghxkz.setHouseholdsNum(houseNum);

        // 开发商详情
        FdWuhanKfs kfs = new FdWuhanKfs();
        kfs.setCityName(CITY_NAME);
        kfs.setUrl(requestUrl);
        kfs.setHouseId(house.getId());
        kfs.setProjectId(house.getProjectId());
        kfs.setKfsContactNumber(consultingTelephone);

        gtzMapper.insert(gtz);
        ghxkzMapper.insert(ghxkz);
        kfsMapper.insert(kfs);

        /*cacheObject(house);
        cacheObject(gtz);
        cacheObject(ghxkz);
        cacheObject(kfs);*/

        buildingList(requestUrl.replace("spfcx_mx", "spfcx_lpb"), house);

    }

    private void buildingList(String requestUrl, FdWuhanHouse house) {
        String htmlStr = httpGetBody(requestUrl, null, "buildingList");
        buildingListParser(requestUrl, htmlStr, house);
    }

    private void buildingListParser(String requestUrl, String htmlStr, FdWuhanHouse house) {
        if (htmlStr == null) {
            log.error("请求失败url:{}", requestUrl);
            return;
        }
        Document doc = Jsoup.parse(htmlStr);

        Elements tables = doc.select("div.box>table");
        Element table = tables.get(1);
        Elements tbodys = table.select("tbody");
        tbodys.forEach(tbody -> {
            Elements tds = tbody.select("td");

            // 楼栋名称
            String buildingName = tds.get(0).text();
            // 总层数
            String totalLayerNum = tds.get(1).text();
            // 总套数
            String buildingHouseNum = tds.get(2).text();
            // 建筑面积
            String builndingArea = tds.get(3).text();
            // 测绘机构
            String sma = tds.get(4).text();

            FdWuhanBuilding building = new FdWuhanBuilding();
            building.setHouseId(house.getId());
            building.setProjectId(house.getProjectId());
            building.setCityName(CITY_NAME);
            // building.setUrl(requestUrl);
            building.setProjectName(house.getProjectName());
            building.setBuildingName(buildingName);
            building.setBuildingTotalLayerNum(totalLayerNum);
            building.setBuildingArea(builndingArea);
            building.setSurveyingMappingAgencies(sma);
            building.setBuildingHouseNum(buildingHouseNum);

            Elements aTags = tbody.getElementsByTag("a");
            if (CollectionUtils.isNotEmpty(aTags)) {
                Element aTag = aTags.get(0);

                String newReqUrl = buildUrl(requestUrl, aTag.attr("href"));
                // unitList(newReqUrl, building);
                building.setUrl(newReqUrl);
            }

            buildingMapper.insert(building);
        });
    }

    private List<FdWuhanUnit> unitList(String requestUrl, FdWuhanBuilding building) {
        String htmlStr = httpGetBody(requestUrl, null, "unitList");
        return unitListParser(requestUrl, htmlStr, building);
    }

    protected List<FdWuhanUnit> unitListParser(String requestUrl, String htmlStr, FdWuhanBuilding building) {
        List<FdWuhanUnit> units = new ArrayList<>();
        Document doc = Jsoup.parse(htmlStr);
        // 存在控制指针的情况
        Element fwxx = doc.getElementById("fwxx");
        if (null == fwxx) {
            building.setStatus("try");
            buildingMapper.updateByPrimaryKey(building);
            String fileName = String.format("%s_%s_%s_%s.html",
                    building.getHouseId(), building.getProjectId(), building.getBuildingId(), building.getBuildingName());
            FileUtils.printFile(htmlStr, properties.getErrorHtmlPath(), fileName, false);
        } else {
            Elements trs = fwxx.select("div>table.tab_style>tbody>tr");

            // 楼栋号
            String buildingId = trs.get(1).select("td").get(0).text();
            building.setBuildingId(buildingId);

            // 房号信息 =================================================================================================
            trs.forEach(tr -> {
                Elements tds = tr.select("td");
                if (tds.isEmpty()) {
                    return;
                }

                // 限制出售的 style="background-color:#000000"
                // 未出售的 style="background-color:#CCFFFF"
                // 已出售的 style="background-color:#FF0000"
                for (Element td : tr.select("td[style*=background-color:]")) {
                    FdWuhanUnit unit = new FdWuhanUnit();
                    unit.setCityName(CITY_NAME);
                    unit.setUrl(requestUrl);
                    unit.setHouseId(building.getHouseId());
                    unit.setBuildingId(building.getId());
                    unit.setProjectId(building.getProjectId());
                    unit.setProjectName(building.getProjectName());
                    unit.setBuildingName(building.getBuildingName());
                    unit.setUnitId(tds.get(1).text());
                    unit.setNominalFloor(tds.get(2).text());
                    // 室号
                    unit.setRoomNo(td.text());

                    String salesStatus;
                    switch (td.attr("style")) {
                        case "background-color:#FF0000":
                            // 已出售的 红色
                            salesStatus = "已出售";
                            break;
                        case "background-color:#CCFFFF":
                            // 未出售的 蓝色
                            salesStatus = "未出售";
                            break;
                        case "background-color:#000000":
                            // 限制出售的 黑色
                            salesStatus = "限制出售";
                            break;
                        case "background-color:#FFFF00":
                            // 已在建工程抵押 黄色
                            salesStatus = "已在建工程抵押";
                            break;
                        case "background:#CC0099":
                            // 已查封
                            salesStatus = "已查封";
                            break;
                        default:
                            // 未知状态
                            salesStatus = "未知状态";
                    }
                    unit.setHouseSalesStatus(salesStatus);

                    // 当herf = 已[00000000-0000-0000-0000-000000000000]结尾时，说明没有备案价格，直接跳过
                    // 当没有备案价格时则直接存储数据，如果有备案价格把当前房信息传递给下一个解析器
                    String newReqUrl = td.getElementsByTag("a").get(0).attr("href");
                    if (!StringUtils.endsWith(newReqUrl, "00000000-0000-0000-0000-000000000000")) {
                        unit.setDetailsUrl(newReqUrl);

                    }

                    // unitMapper.insert(unit);
                    units.add(unit);

                    /*unitExecutor.submit(() -> {
                        if (StringUtils.isNotEmpty(unit.getDetailsUrl())) {
                            unitDetail(unit.getDetailsUrl(), unit);
                            unitMapper.updateByPrimaryKey(unit);
                        }
                    });*/

                }
            });
        }
        return units;
    }

    private void unitDetail(String requestUrl, FdWuhanUnit unit) {
        Map<String, String> head = new HashMap<>();
        head.put("Cookie", "ASP.NET_SessionId=ty3vaelzuziwhzsjgiexnl1y");
        head.put("Upgrade-Insecure-Requests", "1");
        String htmlStr = httpGetBodyAll(requestUrl, head, "unitDetail", "utf-8");
        unitDetailParser(requestUrl, htmlStr, unit);
    }

    private void unitDetailParser(String requestUrl, String htmlStr, FdWuhanUnit unit) {
        Document doc = Jsoup.parse(htmlStr);

        try {
            Elements tbodys = doc.select("tbody");
            if (tbodys.isEmpty()) {
                unit.setStatus("try");
                log.debug("unit 明细响应失败：{}， unit id {}", requestUrl, unit.getId());
            } else {
                Elements tds = tbodys.get(0).select("td");
                for (Element td : tds) {
                    String key = td.text().replaceAll("：", "");
                    switch (key) {
                        case "房屋座落":
                            unit.setHouseAddress(td.nextElementSibling().text());
                            break;
                        case "预售许可证号":
                        case "预售（现售）许可证号":
                            unit.setPresellNo(td.nextElementSibling().text());
                            break;
                        case "预测面积（平方米）":
                        case "预售（现售）建筑面积（平方米）":
                            unit.setMeasuredTotalArea(td.nextElementSibling().text());
                            break;
                        case "预售（现售）单价（元/平方米）":
                            unit.setPreBuildingAvgPrice(td.nextElementSibling().text());
                            break;
                        case "房屋总价款（元）":
                            unit.setHouseTotalPrice(td.nextElementSibling().text());
                            break;
                        case "实测面积（平方米）":
                            unit.setVersion(td.nextElementSibling().text());
                            break;
                        case "预售方案备案单价（元）":
                            String src = td.nextElementSibling().select("img").first().attr("src");
                            String url = WU_HAN_DOMAIN_PREFIX + src;
                            unit.setPreBuildingAvgPrice(url.split("price=")[1].replaceAll("/", "") + "_" + UUID.randomUUID());

                            String fileName = unit.getPreBuildingAvgPrice() + ".png";
                            String filePath = properties.getPicturePath() + unit.getProjectId() + "/";
                            byte[] data = ImageReaderUtils.imageToByte(url);
                            FileUtils.printFile(data, filePath, fileName, false);
                            break;
                        default:
                    }
                }
            }

            if (StringUtils.isEmpty(unit.getHouseAddress())
                    || StringUtils.isEmpty(unit.getPresellNo())
                    || StringUtils.isEmpty(unit.getMeasuredTotalArea())
                    || StringUtils.isEmpty(unit.getPreBuildingAvgPrice())) {
                /*
                 * StringUtils.isEmpty(unit.getHouseTotalPrice()
                 * 实际情况有些地方的总价会是空的。
                 * */
                log.warn("有空数据请注意。");
            }
        } catch (Exception ex) {
            log.error("unit 明细处理失败：{}", ex.getMessage());
        }
    }

    private String buildUrl(String url, String href) {
        return url.substring(0, url.lastIndexOf("/") + 1) + StringUtils.encode(href, "GBK");
    }
}
