/**
 * Created by zhangbokang on 2017/5/11.
 */
var MyData = {
    findblogClass:"/findblogClass",
    findblogTag:"/findblogTag"
}
var Myfn={
    //过滤
    search:function (callBack,cacheName,key,label) {
        function isBigEnough(element, index, array) {
            var re = new RegExp(key);
            if (re.test(array[index].spell)){return true;}
            if (re.test(array[index].fullSpelling)){return true;}
            if (array[index][label] && re.test(array[index][label])){return true;}
            return false;
        }
        callBack(MyData[cacheName].filter(isBigEnough));
    },
    // 自动完成搜索
    autoComplete:function (domId) {
        //如果用户数据没有则从服务器获取一下（把初始化代码放这里，解决发送多次请求的问题）
        if(MyData[domId] == undefined){
            $.ajax({
                type : "GET",
                url : MyData['find'+domId],
                dataType : "json",
                success : function (data) {
                    MyData[domId] = Myfn.addSpell($.map(data, function (item) {return {"label": item['label']}}),"label");
                },
                error : function () {alert("请求出现问题！");return;}
            });
        }

        $("#"+domId).autocomplete({
            minLength:0,
            source: function (request, response) {
                var key = request.term.trim().toUpperCase();
                Myfn.search(function (data) {response(data);},domId,key,"label");
            },
            create: function () {
                $(this).data('ui-autocomplete')._renderItem = function (ul, item) {
                    return $("<li>").append($("<div>")).text(item.label).appendTo(ul);
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
    }
}

//页面加载时调用一次自动完成来初始化
Myfn.autoComplete("blogClass");
Myfn.autoComplete("blogTag");