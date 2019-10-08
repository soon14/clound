/**
 * Created by cdxsai on 2018-05-23.
 * 此文件是为了方便所有示例能正常运行在不同环境下，让示例代码解耦
 * 在不同部署环境下，修改此文件配置即可
 */

app = {
    params: {}
};

// 是否使用mock服务
app.params['config.mockable'] = true;
// mock形式，client或server
app.params['config.mockmode'] = 'client';
// mock服务地址, 后端mock才需要配置
app.params['config.mockservice'] = '';
// 默认运行网络环境，lan局域网，ga公安网, internet互联网
app.params['config.network'] = 'lan';

// 地图图源配置，为了让示例代码解耦，通过配置方便在不同网络下使用不同图源来源
var mapsource = {};
// FC调试瓦片服务器的IP地址

var fcTileServerAdress = 'http://68.26.21.71:80';
var pgis_sl_url = fcTileServerAdress + '/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_PGISSLDT&version=v1&l={z}&x={y}&y={x}';
var pgis_yx_url = fcTileServerAdress + '/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_PGISYXDT&version=v1&l={z}&x={y}&y={x}';
var pgis_sy_url = fcTileServerAdress + '/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_PGISSYDT&version=v1&l={z}&x={y}&y={x}';
// 地图全局缺省配置
// 图源默认接入模式，FC（FinestClient）调试模式
FDefault.DEFAULT_TILE_STANDARD = 'fc';

// 如果示例是别的主应用加载运行，我们就从外部主应用中获取网络环境，独立运行就使用上面人工配置的网络模式
if((top.location != location) && window.parent && window.parent.app){
    app.params['config.network'] = window.parent.app.params['config.network'];
    fcTileServerAdress = 'http://' + window.parent.app.params['config.tileserverIP'] + '/fctiles';
}

// 根据不同网络初始化相应值，互联网网络
if(app.params['config.network']=='internet'){
    FDefault.DEFAULT_TILE_STANDARD = 'pb';
    // 地图url缺省配置就是互联网官网的，不需要再设置，而且其值细节我们一般也不公开
    // 因为官网url大部分是加密的，原则上厂商是给自己的SDK调用的，我们SDK能调用是我们有解密能力，对接参数不公开
	FDefault.DEFAULT_MAPURL_TIANDI ={
            sl: "http://t5.tianditu.gov.cn/DataServer?T=vec_c&x={x}&y={y}&l={z}&tk=56b81006f361f6406d0e940d2f89a39c",
            bz: "http://t5.tianditu.gov.cn/DataServer?T=cva_c&x={x}&y={y}&l={z}&tk=56b81006f361f6406d0e940d2f89a39c",
            slbz:
			[
				"http://t5.tianditu.gov.cn/DataServer?T=vec_c&x={x}&y={y}&l={z}&tk=56b81006f361f6406d0e940d2f89a39c",
				"http://t5.tianditu.gov.cn/DataServer?T=cva_c&x={x}&y={y}&l={z}&tk=56b81006f361f6406d0e940d2f89a39c"
			],
            yx: "http://t5.tianditu.gov.cn/DataServer?T=img_c&x={x}&y={y}&l={z}&tk=56b81006f361f6406d0e940d2f89a39c",
            sy:
			[
				"http://t5.tianditu.gov.cn/DataServer?T=img_c&x={x}&y={y}&l={z}&tk=56b81006f361f6406d0e940d2f89a39c",
				"http://t5.tianditu.gov.cn/DataServer?T=cia_w&x={x}&y={y}&l={z}&tk=56b81006f361f6406d0e940d2f89a39c"
			],
            dx: ["http://t5.tianditu.gov.cn/DataServer?T=ter_w&x={x}&y={y}&l={z}&tk=56b81006f361f6406d0e940d2f89a39c"]
        }
}
// 局域网网络
else if(app.params['config.network']=='lan'){
    FDefault.DEFAULT_TILE_STANDARD = 'pb';
    FDefault.DEFAULT_MAPURL_SOUGOU = {
        sl: fcTileServerAdress + '/sougou/SG-SL-GZ-0918-FC/{z}/{x}_{y}.png',
        yx: fcTileServerAdress + '/sougou/SG-YX-GZ-0918-FC/{z}/{x}_{y}.png',
        bz: fcTileServerAdress + '/sougou/SG-YXBZ-GZ-0918-FC/{z}/{x}_{y}.png',
        sy: [
            fcTileServerAdress + '/sougou/SG-YX-GZ-0918-FC/{z}/{x}_{y}.png',
            fcTileServerAdress + '/sougou/SG-YXBZ-GZ-0918-FC/{z}/{x}_{y}.png'
        ],
        sslk: fcTileServerAdress + '/sougou/SG-SSLK-GZ-0916-FC/{z}/{x}_{y}.png'
    };
    FDefault.DEFAULT_MAPURL_BAIDU = {
        sl: fcTileServerAdress +'/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_INTASLDT&version=v1&l={z}&x={y}&y={x}',
        yx: fcTileServerAdress +'/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_INTAYXDT&version=v1&l={z}&x={y}&y={x}',
        sy:  fcTileServerAdress + '/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_INTASYDT&version=v1&l={z}&x={y}&y={x}',
        wyl: fcTileServerAdress + '/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_INTAWYLDT&version=v1&l={z}&x={y}&y={x}',
        sslk: fcTileServerAdress +'/synframe/map/getMapImage.do?mapId=4595&keyinfo=Q6QRXQp7TVINK7MzmZTYvNoaNGnr5CzHKCuP0brbrEEeSnXP8FTwFldjPr2lGQLU0xjoDnYRNCnCo3Etc20GGwaa&l={z}&x={y}&y={x}',
        qbj: fcTileServerAdress + '/baidu/BD-SLQBJ-GZ-1018-FC/{z}/{x}_{y}.png',
        gdh: fcTileServerAdress + '/baidu/BD-SLGDH-GZ-1018-FC/{z}/{x}_{y}.png',
        qxl: fcTileServerAdress + '/baidu/BD-SLQXL-GZ-1018-FC/{z}/{x}_{y}.png',
        hy: fcTileServerAdress + '/baidu/BD-SLHY-GZ-1018-FC/{z}/{x}_{y}.png',
        hsjj: fcTileServerAdress + '/baidu/BD-SLHSJJ-GZ-1018-FC/{z}/{x}_{y}.png',
        jj: fcTileServerAdress + '/baidu/BD-SLJJ-GZ-1018-FC/{z}/{x}_{y}.png',
        zrl: fcTileServerAdress + '/baidu/BD-SLZRL-GZ-1018-FC/{z}/{x}_{y}.png',
        lmf: fcTileServerAdress + '/baidu/BD-SLLMF-GZ-1018-FC/{z}/{x}_{y}.png',
        qcl: fcTileServerAdress + '/baidu/BD-SLQCL-GZ-1018-FC/{z}/{x}_{y}.png',
        qxll: fcTileServerAdress + '/baidu/BD-SLQXLL-GZ-1018-FC/{z}/{x}_{y}.png',

    };
    FDefault.DEFAULT_MAPURL_GAODE = {
        sl: fcTileServerAdress + '/gaode/GD-SL-GZ-1018-FC/{z}/{x}_{y}.png',
        yx: fcTileServerAdress + '/gaode/GD-YX-GZ-1018-FC/{z}/{x}_{y}.png',
        lw: fcTileServerAdress + '/gaode/GD-YXLW-GZ-1018-FC/{z}/{x}_{y}.png',
        sy: [
            fcTileServerAdress + '/gaode/GD-YX-GZ-1018-FC/{z}/{x}_{y}.png',
            fcTileServerAdress + '/gaode/GD-YXLW-GZ-1018-FC/{z}/{x}_{y}.png'
        ]
    };
    FDefault.DEFAULT_MAPURL_GOOGLE = {
        sl: fcTileServerAdress + '/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_CMAPSLDT&version=v1&l={z}&x={y}&y={x}',
        yx: fcTileServerAdress + '/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_CMAPYXDT&version=v1&l={z}&x={y}&y={x}',
        sy: fcTileServerAdress + '/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_CMAPSYDT&version=v1&l={z}&x={y}&y={x}',
    };
    FDefault.DEFAULT_MAPURL_TENCENT = {
        sl: fcTileServerAdress + '/tencent/TX-SL-GZ-1018-FC/{z}/{x}_{y}.png',
        yx: '',
        dx: '',
        bz: '',
        sy: [
            '',
            ''
        ],
        dxbz: [
            '',
            ''
        ]
    };
    FDefault.DEFAULT_MAPURL_TIANDI = {
        sl: fcTileServerAdress + '/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_TDTSLDT&version=v1&l={z}&x={y}&y={x}',
        yx: fcTileServerAdress + '/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_TDTYXDT&version=v1&l={z}&x={y}&y={x}',
        sy: fcTileServerAdress + '/images/GetImage.do?method=showImageRedisBytable&table=jingyi_admin:MA_INTDTSY&version=v1&l={z}&x={y}&y={x}'
    };
    FDefault.DEFAULT_MAPURL_OSM = {
        sl: fcTileServerAdress + '/osm/OSM-SL-GZ-1018-FC/{z}/{x}_{y}.png',
    },
    FDefault.DEFAULT_MAPURL_BING = {
        sl: fcTileServerAdress + '/bing/BY-SL-GZ-1018-FC/{z}/{x}_{y}.png',
    },
    FDefault.DEFAULT_MAPURL_ARCGIS = {
        sl: fcTileServerAdress + '/arcgis/AG-SL-GZ-1018-FC/{z}/{x}_{y}.png',
    },
    FDefault.DEFAULT_MAPURL_PGIS = {
        sl:fcTileServerAdress + '/synframe/map/getMapImage.do?mapId=4591&keyinfo=Q6QRXQp7TVINK7MzmZTYvNoaNGnr5CzHKCuP0brbrEEeSnXP8FTwFldjPr2lGQLU0xjoDnYRNCnCo3Etc20GGwaa&l={z}&x={y}&y={x}',
        yx:fcTileServerAdress + '/synframe/map/getMapImage.do?mapId=4592&keyinfo=Q6QRXQp7TVINK7MzmZTYvNoaNGnr5CzHKCuP0brbrEEeSnXP8FTwFldjPr2lGQLU0xjoDnYRNCnCo3Etc20GGwaa&l={z}&x={y}&y={x}',
        sy:fcTileServerAdress + '/synframe/map/getMapImage.do?mapId=4593&keyinfo=Q6QRXQp7TVINK7MzmZTYvNoaNGnr5CzHKCuP0brbrEEeSnXP8FTwFldjPr2lGQLU0xjoDnYRNCnCo3Etc20GGwaa&l={z}&x={y}&y={x}'
    },
    FDefault.DEFAULT_MAPURL_EDS = {
        sl: fcTileServerAdress + '/eds/{city}/{z}/{x}_{y}.png',
    }
}
// 公安网网络
else if(app.params['config.network']=='ga'){

}

/**
 * 远程POST请求
 * 封装一个post方法
 * 因jquery的post设置dataType与使用done模式结合起来一起用时比较别扭
 */
function post(url, params, dataType){
    var request = $.ajax({
        type: 'POST',
        url: url,
        data: params,
        dataType: dataType?dataType:'json'
    });
    return request;
}

var tpCache = {};
function getRemoteTemplate(path) {

    var defer = $.Deferred();

    if(tpCache.hasOwnProperty(path)){
        defer.resolve(tpCache[path]);
    }else{
        $.ajax({
            type: 'GET',
            url: path,
            dataType: 'text',
            async: false
        }).done(function(str){
            tpCache[path] = str;
            defer.resolve(tpCache[path]);
        });
    }

    return defer.promise();
}

// 自定义组件隔离作用域，示例不引入任何框架，为了示例代码即完整演示功能又主线逻辑简介，我们简单实现组件开发模式
var widgetScope = {};

