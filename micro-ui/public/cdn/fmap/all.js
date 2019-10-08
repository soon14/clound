

function random(min, max){
    var range= max - min;
    var rand = Math.random();
    return (min + rand * range);
};

function radomPoints(count, range, nametpl) {
    var mockNum = count;
    var mockData = new Array();
    for(var i=0; i<mockNum; i++){
        var item = {
            id: angular.uuid(),
            name: nametpl+i,
            x: random(range.minx, range.maxx),
            y: random(range.miny, range.maxy),
            text: nametpl+i,
            alias: '我叫' + nametpl+i,
        };
        mockData.push(item);
    }
    return mockData;
}

/**
 * 在mock接口函数中同步获取JSON数据文件
 * 这个方法主要是用于后期后台开发好后,我们可以从浏览器监控接口数据生成JSON文件完善mock数据，便于后续调试和演示
 * @param file
 * @returns {Object}
 */
function synGetMockJsonFile(file) {
    file = parseAddress(file);
    var request = new XMLHttpRequest();
    request.open('GET', file, false);
    request.send(null);
    return $.parseJSON(request.response);
}

function synGetMockXMLFile(file) {
    file = parseAddress(file);
    var request = new XMLHttpRequest();
    request.open('GET', file, false);
    request.send(null);
    return request.response;
}

/**
 * 解析相对地址，主要是iframe.document.open()打开一个空文档，将与根应用同济
 * 示例的初始运行baseURI和编辑后运行baseURI将不一致，一个在二级一个在根级
 * path必须为相对路径
 * 有"demos/"则前面加 “../”
 */
function parseAddress(path){
/*    if(window.isReRunByWriteDocument){
        path = path.replace(new RegExp(/\.\.\/\.\.\/\.\.\//g), "examples/");
        path = path.replace(new RegExp(/\.\.\/\.\.\//g), "examples/");
    }*/
    path = path.replace(new RegExp(/\.\.\/\.\.\/\.\.\//g), "examples/");
    path = path.replace(new RegExp(/\.\.\/\.\.\//g), "examples/");
    return path;
}

function parseRequestBody(body){
    var params = new Object();
    if(body && body!="")
    {
        body = decodeURIComponent(body);
        var strs = body.split("&");
        for(var i=0;i<strs.length;i++)
        {
            var sTemp = strs[i].split("=");
            var key = sTemp[0];
            key = key.replace(new RegExp(/\[\]/g), "");
            if(params[key]){
                // 第一次发现有多个时
                if(!(Object.prototype.toString.call(params[key]) === '[object Array]')){
                    var temp = params[key];
                    params[key] = new Array();
                    params[key].push(temp);
                }
                params[key].push(sTemp[1]);
            }else{
                params[key]=(sTemp[1]);
            }
        }
    }
    return params;
}

Mock.mock('example/getSysMenu.do', function(request){
    var result = synGetMockJsonFile('../../datas/sys_menus.json');
    return result;
});

Mock.mock('example/getSubArea.do', function(request){
    var params = parseRequestBody(request.body);
    var parentCode = params.id; // 当前请求区域列表的父区域代号
    var datas = [
        [
            {text: '广州', id: 't1-1',xjd: 113, wd: 24, extent: [], polygon:[]},
            {text: '深圳', id: 't1-2', xjd: 113, wd: 24, extent: [], polygon:[]},
            {text: '韶关', id: 't1-3', xjd: 113, wd: 24, extent: [], polygon:[]},
            {text: '珠海', id: 't1-4', xjd: 113, wd: 24, extent: [], polygon:[]},
            {text: '汕头', id: 't1-5', xjd: 113, wd: 24, extent: [], polygon:[]},
            {text: '佛山', id: 't1-6', xjd: 113, wd: 24, extent: [], polygon:[]},
            {text: '江门', id: 't1-7', xjd: 113, wd: 24, extent: [], polygon:[]},
            {text: '湛江', id: 't1-8', xjd: 113, wd: 24, extent: [], polygon:[]},
            {text: '茂名', id: 't1-9', xjd: 113, wd: 24, extent: [], polygon:[]},
            {text: '肇庆', id: 't1-10',jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '惠州', id: 't1-11',jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '梅州', id: 't1-12',jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '汕尾', id: 't1-13',jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '河源', id: 't1-14',jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '阳江', id: 't1-15',jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '清远', id: 't1-16',jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '东莞', id: 't1-17',jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '中山', id: 't1-18',jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '潮州', id: 't1-19',jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '揭阳', id: 't1-10',jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '云浮', id: 't1-11',jd: 113, wd: 24, extent: [], polygon:[]}
        ],
        [
            {text: '测2.1', id: 't2-1', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测2.2', id: 't2-2', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测2.3', id: 't2-3', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测2.4', id: 't2-4', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测2.5', id: 't2-5', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测2.6', id: 't2-6', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测2.7', id: 't2-7', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测2.8', id: 't2-8', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测2.9', id: 't2-9', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测2.10',id: 't2-10', jd: 113, wd: 24, extent: [], polygon:[]}
        ],
        [
            {text: '测3.1', id: 't3-1', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测3.2', id: 't3-2', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测3.3', id: 't3-3', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测3.4', id: 't3-4', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测3.5', id: 't3-5', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测3.6', id: 't3-6', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测3.7', id: 't3-7', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测3.8', id: 't3-8', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测3.9', id: 't3-9', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测3.10', id: 't3-10', jd: 113, wd: 24, extent: [], polygon:[]}
        ]
    ];
    var result = [];
    if('root'==parentCode){
        result = datas[0];
    }else if(parentCode.indexOf('t1-')!=-1){
        result = datas[1];
    }else if(parentCode.indexOf('t2-')!=-1){
        result = datas[2];
    }else{
        result = [];
    }
    return result;
});

Mock.mock('example/getSubDivision.do', function(request){
    var params = parseRequestBody(request.body);
    var parent = params.code;
    var result = new Array();
    var dict = synGetMockJsonFile('../../datas/areas/areas.json');
    for(var key in dict){
        if(dict[key]['parent']==parent){
            var bound = new Array();
            var sp = dict[key]['bound'].split(',');
            sp.forEach(function(str){
                var coord = str.split(' ');
                var x = parseFloat(coord[0]);
                var y = parseFloat(coord[1]);
                bound.push([x,y]);
            });
            dict[key]['bound'] = bound;
            result.push(dict[key]);
        }
    }
    return result;
});

Mock.mock('example/getAllNavAreas.do', function(request){
    var result =        [
            {text: '广州', id: 'gz', pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '深圳', id: 'sz', pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '韶关', id: 'sg', pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '珠海', id: 'zh', pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '汕头', id: 'st', pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '佛山', id: 'fs', pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '江门', id: 'jm', pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '湛江', id: 'zj', pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '茂名', id: 'mm', pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '肇庆', id: 'zq',pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '惠州', id: 'hz',pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '梅州', id: 'mz',pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '汕尾', id: 'sw',pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '河源', id: 'hy',pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '阳江', id: 'yj',pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '清远', id: 'qy',pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '东莞', id: 'dg',pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '中山', id: 'zs',pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '潮州', id: 'cz',pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '揭阳', id: 'jy',pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '云浮', id: 'yf',pid:'gds', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测1', id: 'c1', pid: 'gz', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测2', id: 'c2', pid: 'gz', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测3', id: 'c3', pid: 'gz', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测4', id: 'c4', pid: 'gz', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测5', id: 'c5', pid: 'c1', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测6', id: 'c6', pid: 'c1', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测7', id: 'c7', pid: 'c1', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测8', id: 'c8', pid: 'c1', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测5', id: 'c9', pid: 'c5', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测6', id: 'c10', pid: 'c5', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测7', id: 'c11', pid: 'c5', jd: 113, wd: 24, extent: [], polygon:[]},
            {text: '测8', id: 'c12', pid: 'c5', jd: 113, wd: 24, extent: [], polygon:[]}
        ];
    return result;
});

Mock.mock('example/getEdsAllCitys.do', function(request){
    var result =        [
        {text: '北京', id: 'beijing', pid:'china', jd: 116.3910, wd: 39.9068, extent: [], polygon:[]},
        {text: '上海', id: 'shanghai', pid:'china', jd: 121.49536826723718, wd: 31.24190862740689, extent: [], polygon:[]},
        {text: '天津', id: 'tianjin', pid:'china', jd: 117.1932, wd: 39.1387, extent: [], polygon:[]},
        {text: '广州', id: 'guangzhou', pid:'china', jd: 113.31783311541976, wd: 23.108823241840277, extent: [], polygon:[]},
        {text: '深圳', id: 'sz', pid:'china', jd: 114.0351, wd: 22.5619, extent: [], polygon:[]},// E都市城市名不够规范
        {text: '惠州', id: 'huizhou', pid:'china', jd: 114.4102, wd: 23.0895, extent: [], polygon:[]},
        {text: '潮州', id: 'chaozhou', pid:'china', jd: 116.6378, wd: 23.6694, extent: [], polygon:[]},
        {text: '福州', id: 'fuzhou', pid:'china', jd: 119.2994, wd: 26.0855, extent: [], polygon:[]},
        {text: '厦门', id: 'xiamen', pid:'china', jd: 118.1006, wd: 24.4877, extent: [], polygon:[]},
        {text: '柳州', id: 'liuzhou',pid:'china', jd: 109.4176, wd: 24.3231, extent: [], polygon:[]},
        {text: '桂林', id: 'guilin',pid:'china', jd: 110.2982, wd: 25.2691, extent: [], polygon:[]},
        {text: '承德', id: 'chengde',pid:'china', jd: 117.9230, wd: 40.9861, extent: [], polygon:[]},
        {text: '秦皇岛', id: 'qinhuangdao',pid:'china', jd: 119.5437, wd: 39.9165, extent: [], polygon:[]},
        {text: '武汉', id: 'wuhan',pid:'china', jd: 114.2800, wd: 30.5815, extent: [], polygon:[]},
        {text: '荆门', id: 'jingmen',pid:'china', jd: 112.1966, wd: 31.0420, extent: [], polygon:[]},
        {text: '荆州', id: 'jingzhou',pid:'china', jd: 112.2325, wd: 30.3276, extent: [], polygon:[]},
        {text: '南京', id: 'nanjing',pid:'china', jd: 118.7748, wd: 32.06037, extent: [], polygon:[]},
        {text: '南通', id: 'nantong',pid:'china', jd: 120.8518, wd: 32.0203, extent: [], polygon:[]},
        {text: '南昌', id: 'nanchang',pid:'china', jd: 115.9143, wd: 28.6768, extent: [], polygon:[]},
        {text: '长春', id: 'changchun',pid:'china', jd: 125.30227, wd: 43.89878, extent: [], polygon:[]},
        {text: '大连', id: 'dalian',pid:'china', jd: 121.61087, wd: 38.91794, extent: [], polygon:[]},
        {text: '抚顺', id: 'fushun', pid: 'china', jd: 123.8656965, wd: 41.87047, extent: [], polygon:[]},
        {text: '呼和浩特', id: 'huhehaote', pid: 'china', jd: 111.67102, wd: 40.820569, extent: [], polygon:[]},
        {text: '包头', id: 'baotou', pid: 'china', jd: 109.88737, wd: 40.6445969, extent: [], polygon:[]},
        {text: '济南', id: 'jinan', pid: 'china', jd: 116.9782, wd: 36.66686978, extent: [], polygon:[]},
        {text: '晋中', id: 'jinzhong', pid: 'china', jd: 112.75092, wd: 37.6799, extent: [], polygon:[]},
        {text: '成都', id: 'chengdu', pid: 'china', jd: 104.07697, wd: 30.6685, extent: [], polygon:[]},
        {text: '南充', id: 'nanchong', pid: 'china', jd: 106.0858, wd: 30.8014, extent: [], polygon:[]},
        {text: '乌鲁木齐', id: 'wulumuqi', pid: 'china', jd: 87.620555, wd: 43.8305, extent: [], polygon:[]},
        {text: '丽江', id: 'lijiang', pid: 'hina', jd: 100.22263, wd: 26.879, extent: [], polygon:[]},
        {text: '杭州', id: 'hz', pid: 'china', jd: 120.147375539, wd: 30.2968986649, extent: [], polygon:[]},
        {text: '台州', id: 'taizhou', pid: 'china', jd: 121.3711, wd: 28.6638, extent: [], polygon:[]},
        {text: '湖州', id: 'huzhou', pid: 'china', jd: 120.1019, wd: 30.8696, extent: [], polygon:[]},
        {text: '金华', id: 'jinhua', pid: 'china', jd: 119.6281, wd: 29.1022, extent: [], polygon:[]},
        {text: '衢州', id: 'quzhou', pid: 'china', jd: 118.8735, wd: 28.9601, extent: [], polygon:[]},
        {text: '绍兴', id: 'shaoxing', pid: 'china', jd: 120.5830, wd: 30.0041, extent: [], polygon:[]},
        {text: '柯桥', id: 'keqiao', pid: 'china', jd: 120.4897, wd: 30.0890, extent: [], polygon:[]},
        {text: '东阳', id: 'dongyang', pid: 'china', jd: 120.2348, wd: 29.2909, extent: [], polygon:[]},
        {text: '永康', id: 'yongkang', pid: 'china', jd: 120.04619, wd: 28.8944, extent: [], polygon:[]},
        {text: '德清', id: 'deqing', pid: 'china', jd: 119.97065, wd: 30.54725, extent: [], polygon:[]},
        {text: '安吉', id: 'aj', pid: 'china', jd: 119.6742, wd: 30.6414, extent: [], polygon:[]},
        {text: '缙云', id: 'jy', pid: 'china', jd: 120.0833, wd: 28.6608, extent: [], polygon:[]},
        {text: '富阳', id: 'fuyang', pid: 'china', jd: 119.9563, wd: 30.05436, extent: [], polygon:[]},
        {text: '建德', id: 'jd', pid: 'china', jd: 119.27489, wd: 29.4781, extent: [], polygon:[]},
        {text: '上虞', id: 'shangyu', pid: 'china', jd: 120.8604, wd: 30.03683, extent: [], polygon:[]},
        {text: '嘉兴', id: 'jiaxing', pid: 'china', jd: 120.7537, wd: 30.7712, extent: [], polygon:[]},
        {text: '临安', id: 'la', pid: 'china', jd: 119.7197, wd: 30.2386, extent: [], polygon:[]}
    ];
    return result;
});

Mock.mock('example/getEdsCitys.do', function(request){
    var result =        [
        {text: '北京', id: 'beijing', pid:'china', jd: 116.3910, wd: 39.9068, extent: [], polygon:[]},
        {text: '上海', id: 'shanghai', pid:'china', jd: 121.49536826723718, wd: 31.24190862740689, extent: [], polygon:[]},
        {text: '天津', id: 'tianjin', pid:'china', jd: 117.1932, wd: 39.1387, extent: [], polygon:[]},
        {text: '广州', id: 'guangzhou', pid:'china', jd: 113.31783311541976, wd: 23.108823241840277, extent: [], polygon:[]},
        {text: '深圳', id: 'shenzhen', pid:'china', jd: 114.0351, wd: 22.5619, extent: [], polygon:[]},// E都市城市名不够规范
        /*{text: '惠州', id: 'huizhou', pid:'china', jd: 114.4102, wd: 23.0895, extent: [], polygon:[]},*/
        {text: '武汉', id: 'wuhan',pid:'china', jd: 114.2800, wd: 30.5815, extent: [], polygon:[]},
        {text: '乌鲁木齐', id: 'wulumuqi', pid: 'china', jd: 87.620555, wd: 43.8305, extent: [], polygon:[]},
        {text: '杭州', id: 'hangzhou', pid: 'china', jd: 120.147375539, wd: 30.2968986649, extent: [], polygon:[]}
    ];
    return result;
});

Mock.mock('example/getBound.do', function(request){
    var params = parseRequestBody(request.body);
    var areaId = params.areaId;
    var result = [];
    result = (synGetMockJsonFile('../../datas/areas/areas.json'))[areaId];
    var bound = new Array();
    var sp = result.bound.split(',');
    sp.forEach(function(str){
        var coord = str.split(' ');
        var x = parseFloat(coord[0]);
        var y = parseFloat(coord[1]);
        bound.push([x,y]);
    });
    result.bound = bound;
    return result;
});

Mock.mock('example/getAllSougoEds.do', function(request){
    var result =        [
        {text: '北京', id: 'beijing', pid:'china', jd: 116.3910, wd: 39.9068, extent: [], polygon:[]},
        {text: '上海', id: 'sh', pid:'china', jd: 121.49536826723718, wd: 31.24190862740689, extent: [], polygon:[]},
        {text: '广州', id: 'guangzhou', pid:'china', jd: 113.31783311541976, wd: 23.108823241840277, extent: [], polygon:[]},
        {text: '深圳', id: 'sz', pid:'china', jd: 114.0351, wd: 22.5619, extent: [], polygon:[]},// E都市城市名不够规范
        {text: '惠州', id: 'hz', pid:'china', jd: 114.4102, wd: 23.0895, extent: [], polygon:[]},
        {text: '武汉', id: 'wuhan',pid:'china', jd: 114.2800, wd: 30.5815, extent: [], polygon:[]},
        {text: '天津', id: 'tianjin', pid:'china', jd: 117.1932, wd: 39.1387, extent: [], polygon:[]},
        {text: '乌鲁木齐', id: 'wulumuqi', pid: 'china', jd: 87.620555, wd: 43.8305, extent: [], polygon:[]}
        //{text: '杭州', id: 'hz', pid: 'china', jd: 120.147375539, wd: 30.2968986649, extent: [], polygon:[]}
    ];
    return result;
});

Mock.mock('example/getAllProjectCitys.do', function(request){
    var result =        [
        //{text: '广州', id: 'guangzhou', pid:'gds', center:[], extent: [], polygon:[]},
        {text: '北海', id: 'beihai', pid:'gds', center:[109.13274370615675,21.47285923526918], extent: [], polygon:[], mapurl: 'beihai/BD-SL-BH-1018-FC'},
        {text: '东莞', id: 'dongguan', pid:'gds', center:[113.74646615875854,23.045490983271566], extent: [], polygon:[], mapurl: 'dongguan/BD-SL-DG-1018-FC'},
        {text: '黄石', id: 'huangshi', pid:'gds', center:[115.04430906375515,30.211539442678806], extent: [], polygon:[], mapurl: 'huangshi/BD-SL-HS-1018-FC'},
        {text: '惠州', id: 'huizhou', pid:'gds', center:[114.41061643605205,23.088786722913397], extent: [], polygon:[], mapurl: 'huizhou/BD-SL-HZ-1018-FC'},
        {text: '汕尾', id: 'shanwei', pid:'gds', center:[115.35896218748614,22.775694302856532], extent: [], polygon:[], mapurl: 'shanwei/BD-SL-SW-1118-FC'},
        {text: '韶关', id: 'shaoguan', pid:'gds', center:[113.58414511645981,24.808755875028123], extent: [], polygon:[], mapurl: 'shaoguan/BD-SL-SG-1118-FC'},
        {text: '深圳', id: 'shenzhen', pid:'gds', center:[114.03497641085715,22.56137432058139], extent: [], polygon:[], mapurl: 'shenzhen/BD-SL-SZ-1018-FC'},
        {text: '顺德', id: 'shunde', pid:'gds', center:[113.28807115378893,22.808034589848326], extent: [], polygon:[], mapurl: 'shunde/BD-SL-SD-1018-FC'},
        {text: '乌鲁木齐', id: 'wulumuqi', pid:'gds', center:[87.59831408777386,43.80054503077597], extent: [], polygon:[], mapurl: 'wulumuqi/BD-SL-WLMQ-1018-FC'},
        {text: '梧州', id: 'wuzhou', pid:'gds', center:[111.29989211102362,23.488935619300523], extent: [], polygon:[], mapurl: 'wuzhou/BD-SL-WZ-1018-FC'},
        {text: '肇庆', id: 'zhaoqing', pid:'gds', center:[112.47239342431719,23.078844910204786], extent: [], polygon:[], mapurl: 'zhaoqing/BD-SL-ZQ-1018-FC'},
        {text: '郑州', id: 'zhengzhou', pid:'gds', center:[113.6600093574273,34.75062802070487], extent: [], polygon:[], mapurl: 'zhengzhou/BD-SL-ZZ-1018-FC'},
        {text: '中山', id: 'zhongshan', pid:'gds', center:[113.3716121091736,22.523562783438376], extent: [], polygon:[], mapurl: 'zhongshan/BD-SL-ZS-1118-FC'}
    ];
    return result;
})

Mock.mock('example/randomFeatures.do', function(request){
    var params = parseRequestBody(request.body);
    var mockNum = params.count;
    var extent = params.extent;
    var result = new Array();
    for(var i=0; i<mockNum; i++){
        var uuid = $.uuid();
        var item = {
            id: uuid,
            code: uuid.substr(0,13),
            name: 'item' + i,
            x: random(parseFloat(extent[0]), parseFloat(extent[2])),
            y: random(parseFloat(extent[1]), parseFloat(extent[3])),
            text: '航天精一（广东）信息科技有限公司',
            alias: '航天精一'
        };
        result.push(item);
    }
    return result;
});

Mock.mock('example/getFeatureDetial.do', function(request){
    var params = parseRequestBody(request.body);
    var coord = params.coord;
    coord[0] = parseFloat(coord[0]).toFixed(4);
    coord[1] = parseFloat(coord[1]).toFixed(4);
    var result = [
        {proText: '人员编号', proValue: 'R7465690002006110D1Y'},
        {proText: '姓名', proValue: '张三'},
        {proText: '性别', proValue: '男'},
        {proText: '民族', proValue: '汉族'},
        {proText: '身份证号', proValue: '441323199011200000'},
        {proText: '居住地址', proValue: '海珠区同福中路宝贝里22号'},
        {proText: '坐标', proValue: coord.toString(), type: 'link', click: 'nearSerach'},
    ];
    return result;
});

Mock.mock('example/randomPolices.do', function(request){
    var params = parseRequestBody(request.body);
    var mockNum = params.count;
    var extent = params.extent;
    var result = new Array();
    for(var i=0; i<mockNum; i++){
        var uuid = $.uuid();
        var item = {
            id: uuid,
            code: uuid.substr(0,13),
            name: '警员' + uuid.substr(0,13),
            x: random(parseFloat(extent[0]), parseFloat(extent[2])),
            y: random(parseFloat(extent[1]), parseFloat(extent[3])),
            isOnline: (Math.random() >= 0.5)
        };
        result.push(item);
    }
    return result;
});

Mock.mock('example/randomVideos.do', function(request){
    var params = parseRequestBody(request.body);
    var mockNum = params.count;
    var extent = params.extent;
    var result = new Array();
    for(var i=0; i<mockNum; i++){
        var uuid = $.uuid();
        var item = {
            id: uuid,
            code: uuid.substr(0,13),
            name: '视频' + uuid.substr(0,13),
            x: random(parseFloat(extent[0]), parseFloat(extent[2])),
            y: random(parseFloat(extent[1]), parseFloat(extent[3])),
            devicetype: (Math.random() >= 0.5?'ball':'gum')
        };
        result.push(item);
    }
    return result;
});

Mock.mock('example/getMyPoliceDetial.do', function(request){
    var params = parseRequestBody(request.body);
    params.x = parseFloat(params.x).toFixed(4);
    params.y = parseFloat(params.y).toFixed(4);
    var police = {
        bh: params.code,
        xm: params.name,
        xb: '男',
        mz: '汉族',
        sfzh: '4413231990112XXXXX',
        jzdz: '海珠区同福中路宝贝里22号',
        zb: params.x+ ',' + params.y
    };
    return police;
});

Mock.mock('example/getMyVipDetial.do', function(request){
    var params = parseRequestBody(request.body);
    params.x = parseFloat(params.x).toFixed(4);
    params.y = parseFloat(params.y).toFixed(4);
    var police = {
        percent: 90,
        oimg: '../../assets/img/data/vip1s.jpg',
        img: '../../assets/img/data/vip1t.jpg',
        name: '张三',
        gender: '男',
        age: 40,
        sfzh: '4413231990112XXXXX',
        sxtbh: '440301000005',
        yfys: '灰色'
    };
    return police;
});

Mock.mock('example/getMyPlots.do', function(request){
    var result = synGetMockJsonFile('../../datas/demo_myplots_gz.json');
    return result;
});

Mock.mock('example/getTrack.do', function(request){
    var result = synGetMockJsonFile('../../datas/sd_pdt_track.json');
    return result;
});

Mock.mock('example/getRealtimePolices.do', function(request){
    var result = [];
    var policeNum = 50;
    var random = function(min, max){
        var range= max - min;
        var rand = Math.random();
        return (min + rand * range);
    };
    // ˳������Χ
    var sdCord = {
        minY: 22.76597,
        maxY: 22.83989,
        minX: 113.224923,
        maxX: 113.438082
    };
    for(var i=0; i<policeNum; i++){
        var police = {
            id: 'police_' + i,
            x: random(sdCord.minX, sdCord.maxX),
            y:  random(sdCord.minY, sdCord.maxY)
        };
        result.push(police);
    }
    return result;
});

Mock.mock('example/getDitie.do', function(request){
    var params = parseRequestBody(request.body);
    var result = synGetMockJsonFile('../../datas/demo_lines_' + params.city + '.json');
    return result;
});

Mock.mock('example/getVipAreas.do', function(request){
    var params = parseRequestBody(request.body);
    var result = synGetMockJsonFile('../../datas/demo_polygons_' + params.city + '.json');
    return result;
});

Mock.mock('example/getMixFeatures.do', function(request){
    var params = parseRequestBody(request.body);
    var mockNum = 50;
    var extent = params.extent;
    var result = new Array();
    for(var i=0; i<mockNum; i++){
        var item = {
            id: $.uuid(),
            name: 'item' + i,
            x: random(parseFloat(extent[0]), parseFloat(extent[2])),
            y: random(parseFloat(extent[1]), parseFloat(extent[3])),
            text: '广东精一规划科技股份有限公司',
            alias: '精一规划'
        };
        result.push(item);
    }
    var polygons = synGetMockJsonFile('../../datas/demo_polygons_' + params.city + '.json');
    var lines = synGetMockJsonFile('../../datas/demo_lines_' + params.city + '.json');
    lines.forEach(function(item){
        item.geoType = "Polyline";
    });
    return result.concat(polygons).concat(lines);
});

Mock.mock('tool/getTilServiceList.do', function(request){
    //var params = parseRequestBody(request.body);
    var result = synGetMockJsonFile('../../datas/tool_tileservices.json');
    return result;
});

Mock.mock('tool/getTilServiceDetial.do', function(request){
    var params = parseRequestBody(request.body);
    var result;
    var list = synGetMockJsonFile('../../datas/tool_tileservices.json');
    for(var i=0; i<list.length;i++){
        if(list[i].code==params.serviceCode){
            result = list[i];
            break;
        }
    }
    return result;
});

Mock.mock('example/getTrack.do', function(request){
    var result = synGetMockJsonFile('../../datas/tracks/sd_pdt_track.json');
    return result;
});

function formatBound(strBound){
    var bound = new Array();
    var sp = strBound.split(',');
    sp.forEach(function(str){
        var coord = str.split(' ');
        var x = parseFloat(coord[0]);
        var y = parseFloat(coord[1]);
        bound.push([x,y]);
    });
    return bound;
}



