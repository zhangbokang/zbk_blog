/**
 * Created by zhangbokang on 2017/5/17.
 */
//引入py.js，用来处理汉字添加拼音的处理
// document.write('<script src="http://zhishi01-1253216462.costj.myqcloud.com/static/spell/py.js"></script>');
// document.write('<script src="//cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>');
// document.write('<script src="/static/jqueryui/jquery-ui.min.js"></script>');
var common = {
    COLUMNS:{
        classifyNode:
            [{
                field: 'id',
                title: '分类ID',
                width:"20%"
            }, {
                field: 'text',
                title: '分类名称',
                width:"40%"
            }, {
                field: 'updateTime',
                title: '最后更新时间',
                width:"20%",
                formatter:function(value,row,index){
                    function add0(m){return m<10?'0'+m:m }
                    var time = new Date(value);
                    var y = time.getFullYear();
                    var m = time.getMonth()+1;
                    var d = time.getDate();
                    var h = time.getHours();
                    var mm = time.getMinutes();
                    var s = time.getSeconds();
                    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
                }

            },{
                field:'opt',
                title:'操作',
                width:"20%",
                formatter:function(value,row,index){
                    return ["<button class='btn btn-default' onclick='deleteClassifyMake("
                    +row.id+",\""+row.text+"\")'>删除</button>"
                    ].join("");
                }
            }],
        tag:
            [{
                field: 'tagId',
                title: '标签ID',
                width:"20%"
            }, {
                field: 'name',
                title: '标签名称',
                width:"40%"
            }, {
                field: 'createTime',
                title: '最后更新时间',
                width:"20%",
                formatter:function(value,row,index){
                    function add0(m){return m<10?'0'+m:m }
                    var time = new Date(value);
                    var y = time.getFullYear();
                    var m = time.getMonth()+1;
                    var d = time.getDate();
                    var h = time.getHours();
                    var mm = time.getMinutes();
                    var s = time.getSeconds();
                    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
                }
            },{
                field:'opt',
                title:'操作',
                width:"20%",
                formatter:function(value,row,index){
                    return ["<button class='btn btn-default' onclick='deleteTagMake("
                    +row.tagId+",\""+row.name+"\")'>删除</button>"
                    ].join("");
                }
            }],
        comment:
            [{
                field: 'commentId',
                title: '评论ID',
                width:"15%"
            },{
                field: 'docTitle',
                title: '文章标题',
                width:"25%"
            },{
                field: 'content',
                title: '评论内容',
                width:"30%"
            },{
                field: 'createTime',
                title: '评论时间',
                width:"15%",
                formatter:function(value,row,index){
                    function add0(m){return m<10?'0'+m:m }
                    var time = new Date(value);
                    var y = time.getFullYear();
                    var m = time.getMonth()+1;
                    var d = time.getDate();
                    var h = time.getHours();
                    var mm = time.getMinutes();
                    var s = time.getSeconds();
                    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
                }
            },{
                field:'opt',
                title:'操作',
                width:"15%",
                formatter:function(value,row,index){}
            }],
        doc:
            [{
                field: 'docId',
                title: '文章ID',
                width:"15%"
            }, {
                field: 'title',
                title: '文章标题',
                width:"25%"
            },{
                field: 'classifyNodeName',
                title: '文章分类',
                width:"15%"
            },{
                field: 'tagName',
                title: '文章标题',
                width:"15%"
            }, {
                field: 'updateTime',
                title: '最后更新时间',
                width:"20%",
                formatter:function(value,row,index){
                    function add0(m){return m<10?'0'+m:m }
                    var time = new Date(value);
                    var y = time.getFullYear();
                    var m = time.getMonth()+1;
                    var d = time.getDate();
                    var h = time.getHours();
                    var mm = time.getMinutes();
                    var s = time.getSeconds();
                    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
                }
            },{
                field:'opt',
                title:'操作',
                width:"10%",
                formatter:function(value,row,index){
                    return ["<a class='btn btn-default' href='/doc/editDoc?docId=" +
                    row.docId+"'>修改</a>"//,
                        // "<button class='btn btn-default' onclick='deleteTagManage("
                        // +row.tagId+")'>删除</button>"

                    ].join("");
                }
            }],
    },
    URL:{
        doc:{
            findAllDoc:"/doc/findAllDocOutJsonByPage",
        },
        classifyNode:{
            findAllClassifyNode:"/classifyNode/findAllClassifyNode",
            findAllClassifyNodeByPage:"/classifyNode/findAllClassifyNodeByPage",
            addClassifyNode:"/classifyNode/addClassifyNode",
            updateClassifyNode:"/classifyNode/updateClassifyNode",
            deleteClassifyNode:"/classifyNode/deleteClassifyNode"
        },
        tag:{
            findAllTag:"/tag/findAllTag",
            findAllTagByPage:"/tag/findAllTagByPage",
            addTag:"/tag/addTag",
            updateTag:"/tag/updateTag",
            deleteTag:"/tag/deleteTag"
        }
    },
    Data:{
        cache:{
            classifyNode:[],
            tag:[]
        }
        // classifyCache:[],
        // tagCache:[]
    },

    Fn:{
        /**
         * 过滤数据，用于自动完成输入框，当用户输入后根据用户输入过滤初匹配数据，并提供回调
         * @param callBack 回调函数，应为一个function，会有一个data传参，该传参就是过滤后的数据
         * @param data 要过滤的数组对象
         * @param key 用户的输入
         * @param label 数组元素的自定义过滤字段名称，不写默认"name"
         */
        searchData:function (callBack,data,key,label) {
            function isBigEnough(element, index, array) {
                var re = new RegExp(key);
                if (re.test(element.spell) || re.test(element.fullSpelling)){
                    return true;
                }
                if (label == undefined){
                    label ="name";
                }
                if (element[label] && re.test(element[label])){
                    return true;
                }
                return false;
            }
            callBack(data.filter(isBigEnough));
        },
        /**
         * 根据输入去服务器请求数据
         * @param url 请求数据的地址
         * @param param 请求数据的附加参数
         * @param label 显示的字段
         * @returns {*} 返回请求得到的数据
         */
        queryData:function (url,param,label) {
            if (param == undefined){
                param ={}
            }
            if (label == undefined){
                label = "name";
            }
            // if(cache == undefined) {
            var cache;
                $.ajax({
                    type: "POST",
                    url: url,
                    data: param,
                    async:false, //同步请求，避免数据延迟产生错误
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 1) {
                            cache = $.map(result.data,function (item) {
                                var obj={};
                                for (var i in item){
                                    var v = item[i];
                                    if (i == label){i="label";}
                                    obj[i]=v;
                                }
                                return obj;
                            });
                            common.Fn.addSpell(cache,"label");
                            return;
                        }
                        alert(result.msg);
                    },
                    error: function () {
                        alert("“"+url +"”请求出现问题！");
                        return;
                    }
                });
                return cache;
            // }
        },
        /**
         * 根据domId给输入框添加自动完成功能
         * 注意：domId不带#号
         * @param domId
         */
        autoCompleteByDomId:function (domId,url) {
            var $dom = $("#"+domId);

            //如果数据没有则从服务器获取一下
            common.Data.cache[domId] = common.Fn.queryData(url);
            var cache = common.Data.cache[domId];

            $dom.autocomplete({
                minLength:0,//输入多少字后显示列表，0表示不输入也显示
                source: function (request, response) {
                    var key = request.term.trim().toUpperCase();
                    //如果没有输入任何数据，则返回全部数据
                    if (!$.trim(key)){
                        response(cache);
                        return;
                    }
                    common.Fn.searchData(function (data) {
                        response(data);
                    },cache,key);
                },
                create: function () {
                    $(this).data('ui-autocomplete')._renderItem = function (ul, item) {
                        return $("<li>")
                            .append($("<div>").text(item.label))
                            .appendTo(ul);
                    };
                },
                select: function (event, ui) {
                    var item = ui.item;
                    $(this).val(ui.item.label);
                    for (var i in item) {
                        $(this).attr(i, item[i]);
                    }
                    event.preventDefault();
                    $(this).blur();
                }
            }).focus(function (){
                // $(this).val("");//清除属性
                // Util.removeAttributes($(this));
                $(this).autocomplete("search");
            });
        },
        /**
         * 给数据添加拼音
         *
         * 传入两个参数，
         *  data：一个json数组
         *  label：中文所在元素的key
         *
         * 返回
         *  在原json数组的每个元素中添加两个字段
         *      spell：大写的首字母
         *      fullSpelling：大写的全拼
         *
         */
        addSpell:function(data,label) {
            $.each(data,function (n,value) {
                value['spell']=pinyin.getCamelChars(value[label]);
                value['fullSpelling']=pinyin.getFullChars(value[label]).toUpperCase();
            });
            return data;
        },
        /**
         * 根据传入的item元素，给ID是传入的domId的元素设置属性
         *  属性名为item的key，属性值为item的value
         * @param domId
         * @param item
         */
        setAttr:function (domId,item) {
            if (item === undefined || item.length == 0){
                return;
            }
            var $dom = $("#"+domId);
            for (var i in item){
                $dom.val(item["label"]);
                $dom.attr(i,item[i]);
            }
        }
    }
}

