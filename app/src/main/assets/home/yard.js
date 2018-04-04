$.app = {
    data: null,
    coalData: null,
    mcName: null,
    zoom: null,
    yelCarWidth:null,
    ywlCarHeight:null,
    yelTrainWidth:null,
    ywlTrainHeight:null,
    intervals: [],
    isMultiple: 0,
    realAmount:0,
    isTurn : false,
    yeljData : [{NAME: "信标1", YARD: "汽车沟", LOCATION_X: 5, LOCATION_Y: 2, HEIGHT: 20, FLAG: 0, WIDTH: 200},
        {NAME: "叶轮机-4", YARD: "火车沟", LOCATION_X: 98, LOCATION_Y: 11, HEIGHT: 20, FLAG: 1, WIDTH: 500},
        {NAME: "33额", YARD: "火车沟", LOCATION_X: 34, LOCATION_Y: 16, HEIGHT: 20, FLAG: 0, WIDTH: 500},
        {NAME: "信标2", YARD: "火车沟", LOCATION_X: 484, LOCATION_Y: 6, HEIGHT: 20, FLAG: 0, WIDTH: 500},
        {NAME: "信标3", YARD: "火车沟", LOCATION_X: 28, LOCATION_Y: 1, HEIGHT: 20, FLAG: 1, WIDTH: 500},
        {NAME: "叶轮机-2", YARD: "汽车沟", LOCATION_X: 40, LOCATION_Y: 13, HEIGHT: 20, FLAG: 1, WIDTH: 200},
        {NAME: "信标3", YARD: "汽车沟", LOCATION_X: 124, LOCATION_Y: 5, HEIGHT: 20, FLAG: 0, WIDTH: 200},
        {NAME: "信标4", YARD: "汽车沟", LOCATION_X: 195, LOCATION_Y: 14, HEIGHT: 20, FLAG: 0, WIDTH: 200}],
    init: function (data, zoom, name, id) {
        this.data = data;
        this.coalData = data.coal;
        this.zoom = zoom;
        this.mcName = name;
        this.yardId = id;
        for (var i = 0, len = data.length; i < len; i++) {
            if (data[i].NAME == name) {
                if (!zoom) {
                    $("p.mcName").text("煤场: " + name);
                    $("p.mcAmount").text("存煤: " + data[i].AMOUNT + " 吨");
                } else {
                    $('#mcName', parent.document).text("煤场名称: " + name);
                    $('#mcAmount', parent.document).text("煤场名称: " + data[i].AMOUNT + " 吨");
                    $('#meicSelt', parent.document).empty();
                }
                break;
            }
        }
        $.app.setBackground();
        $.app.drawYard();
        $.app.drawCar();
        $.app.drawTrain();
        // setTimeout(function () {
           $.app.timeOut(10 * 1000)
        // }, 1 * 1000);
    },
    setBackground: function () {
        $page = $.app.getPageSize();
        console.log($page.width + ',' + $page.height);
        canvas = document.getElementById("canvasDiv");
        ctx = canvas.getContext("2d");
        $height = $page.height - $page.height * 0.35;
        canvas.width = $page.width;
        canvas.height = $height;
        $angle = Math.atan((canvas.width * 0.3934681181959565 - canvas.width * 0.0388802488335925  ) / canvas.height) / Math.PI * 180;
        meidAngle = Math.tan($angle * 0.017453292519943);

        jspAngle = Math.tan((90 - $angle) * 0.017453292519943);
        translateX = 0 , translateY = 0;
        canvas.style.top = $page.height * 0.15;
        $carLeft = ($page.width - translateX) * 0.0388802488335925 + translateX;
        $trainLeft = ($page.width - translateX) * 0.3934681181959565 + translateX;
        $("#head").css({"display": "block", "margin": "5px 0px 0px " + $trainLeft + "px"});
        $("#foot").css({"display": "block", "margin": "0px 0px 2px " + $carLeft + "px"});
        var imageObj = new Image();
        imageObj.src = "images/home/floor/all/dit.png";
        imageObj.onload = function () {
            var f = this;
            ctx.drawImage(f, translateX, translateY, $page.width - translateX, $height - translateY);
        };
        var _carImg = '<img class="md_qc"  src=images/home/md_qc.png  style = "position:absolute; left: 40%; top: 43%;  width:20px; height:15px;" title="晋B70951">';
        $(document.body).append(_carImg);
        var f = this;
        canvas.addEventListener('click', function (e) {
            if (e.clientY - ($("#head").height()) >= canvas.height * 0.2719665271966527 && e.clientY - ($("#head").height()) <= canvas.height * 0.4184100418410042
                && e.clientX >= canvas.width * 0.2099533437013997 && e.clientX <= canvas.width * 0.2721617418351477) {
                $("#realAmount")[0].textContent = " " + (Math.random() * 50).toFixed(2) + "  ";
                $("#beltInfo")[0].timer = f.interval($("#beltInfo")[0]);
                var _w = canvas.width * 0.2721617418351477 -  canvas.width * 0.2099533437013997 ;
                var _h = canvas.height * 0.4184100418410042 - canvas.height * 0.2719665271966527;
                $("#beltInfo").css({display:'block',left:  canvas.width *0.2099533437013997 -  _w/2 + 'px', top:  canvas.height * 0.2719665271966527 + 'px', height: _h +'px' , width: _w*1.71334431630972+'px'});
            }
            else if (e.clientY - ($("#head").height()) >= canvas.height * 0.7322175732217573 && e.clientY - ($("#head").height()) <= canvas.height * 0.8451882845188285
                && e.clientX >= canvas.width * 0.0342146189735614 && e.clientX <= canvas.width * 0.0902021772939347) {
                window.wxc.xcConfirm('暂未工作');
            }
            else if (e.clientY - ($("#head").height()) >= canvas.height * 0.0209205020920502 && e.clientY - ($("#head").height()) <= canvas.height * 0.0502092050209205
                && e.clientX >= canvas.width * 0.0342146189735614 && e.clientX <= canvas.width * 0.3732503888024883) {
                // window.parent.callVideo();
                window.wxc.xcConfirm('请链接视频头');
            }
            else if (e.clientY - ($("#head").height()) >= canvas.height * 0.0167364016736402 && e.clientY - ($("#head").height()) <= canvas.height * 0.0418410041841004
                && e.clientX >= canvas.width * 0.9735614307931571 && e.clientX <= canvas.width) {
                window.wxc.xcConfirm('请链接视频头');
            }
            else if (e.clientY - ($("#head").height()) >= canvas.height * 0.8702928870292887 && e.clientY - ($("#head").height()) <= canvas.height * 0.899581589958159
                && e.clientX >= canvas.width * 0.0248833592534992 && e.clientX <= canvas.width * 0.0544323483670295) {
                window.wxc.xcConfirm('请链接视频头');
            }
            else if (e.clientY - ($("#head").height()) >= canvas.height * 0.8870292887029289 && e.clientY - ($("#head").height()) <= canvas.height * 0.9121338912133891
                && e.clientX >= canvas.width * 0.6500777604976672 && e.clientX <= canvas.width * 0.671850699844479) {
               window.wxc.xcConfirm('请链接视频头');
            } else {
                $("#beltInfo").css({display:'none'});
                clearInterval($("#beltInfo")[0].timer);
            }
        }, false);
    },

    interval: function (obj) {
        var f = this;
        clearInterval(obj.timer);
        return setInterval(function () {
            f.realAmount =   $('#realAmount').text();
            $("#realAmount")[0].textContent = " " + f.accAdd(f.realAmount*1 ,((Math.random() * 10).toFixed(0))*1)+ "  ";
        }, 1000)
    },

    accAdd : function (arg1, arg2) {
          var r1, r2, m;
          try {
                  r1 = arg1.toString().split(".")[1].length;
              }
          catch (e) {
                  r1 = 0;
              }
          try {
                  r2 = arg2.toString().split(".")[1].length;
              }
          catch (e) {
                  r2 = 0;
              }
          m = Math.pow(10, Math.max(r1, r2));
          return (arg1 * m + arg2 * m) / m;
      },

    drawYard: function () {
        var f = this;
        if (!f.zoom) {
            f.yardId = $('#meicSelt').val();
        }
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            url: "yard/getYardInfo",
            data: {
                "width": canvas.width - ((canvas.width - translateX) * 0.3934681181959565) - translateX - ((canvas.width - translateX) * 0.0139968895800933),
                "height": canvas.height,
                "yard": f.yardId
            },
            success: function (data) {
                if((f.isMultiple == 14  || f.isTurn )&& f.isMultiple >1){
                    f.isTurn = true
                    f.isMultiple--;
                }else if (f.isMultiple == 1){
                    f.isTurn = false;
                    f.isMultiple++;
                 } else{
                    f.isMultiple++;
                }
                mc = data.mc;
                douljs = data.doulj;
                mdList = data.mdList;
                yeljs = data.ylList;
                f.data.total = data.coalData;
                total = data.coalData[f.mcName];
                $("p.mcAmount").text("存煤: " + total + " 吨");
                var tmp = 0;
                for (var i in mdList) {
                    tmp += 1;
                    var tXa = mdList[0].xD + (mc.yD - mdList[0].yD) * meidAngle;
                    var tYa = mdList[0].yD;
                    var tXd = mdList[0].xU + (mc.yD - mdList[0].yU) * meidAngle;
                    var tYd = mdList[0].yU;
                    var _md = mdList[i];
                    var h = _md.amount / (( ( tXd - tXa ) * (tYa - tYd) ) * (total / (mc.yD * mc.xU * 5)));
                    $(document.body).append("<a class='meicLink' id='alink" + tmp + "' amount=" + _md.amount + " qe=" + _md.qe + " sgq = " + _md.sgq + "  href='https://www.baidu.com' target='_blank' >" + _md.name + "</a>");
                    $.app.draw3D(mdList[i].xD, mdList[i].yD, mdList[i].xR, mdList[i].yR, mdList[i].xU, mdList[i].yU, mdList[i].xL, mdList[i].yL, h, i * 1 + 1);
                }
                if (douljs) {
                    for (var i = 0, len = douljs.length; i < len; i++) {
                        $.app.drawDouljis(douljs[i]);
                        $.app.drawDouljyb(douljs[i]);
                    }
                }
                if (yeljs){
                    // for (var i = 0, len = yeljs.length; i < len; i++) {
                    //     $.app.drawYelj(yeljs[i]);
                    // }
                    for (var i = 0, len = f.yeljData.length; i < len; i++) {
                        $.app.drawYelj(f.yeljData[i]);
                    }
                }
            }
        })
    },

    drawYelj : function(yelj){
        var f = this;
        var _icon = 'images/home/yelj/ylj_h.png';
        if(yelj.FLAG == 1){
            _icon = 'images/home/yelj/ylj_lv.png';
        }
        var _title =  yelj.NAME;
        if(yelj.YARD == '汽车沟'){
            var _width =  (f.yelCarWidth * 14 / yelj.WIDTH ) * yelj.LOCATION_X;
            if(yelj.FLAG == 1 ){
                if(_width+ f.isMultiple*10 <= yelj.WIDTH){
                    _width  = _width+ f.isMultiple*10;
                }
            }
            var _heigth = (f.ywlCarHeight * jspAngle / yelj.HEIGHT ) * yelj.LOCATION_Y;
            if(_heigth > f.ywlCarHeight/2 ){
                $("#foot").append("<img class='ylImg' title="+_title+" src="+_icon+" style=position:absolute;left:" + ($carLeft +  _width) + "px;  width=" + f.yelCarWidth  + "px  height="+ f.ywlCarHeight/2+ "px/>");
              }else{
                $("#foot").append("<img class='ylImg' title="+_title+" src="+_icon+" style=position:absolute;padding-top:"+f.ywlCarHeight/2 +"px;left:"+($carLeft +  _width)+"px;  width=" + f.yelCarWidth  + "px  height="+ f.ywlCarHeight/2+ "px/>");
             };
        }
         if(yelj.YARD == '火车沟'){
            var _width =  (f.yelTrainWidth * 14 / yelj.WIDTH ) * yelj.LOCATION_X;
             if(yelj.FLAG == 1 ){
                 if(_width+ f.isMultiple*10 <= yelj.WIDTH){
                     _width  = _width+ f.isMultiple*10;
                 }
             }
            var _heigth = (f.ywlTrainHeight * jspAngle / yelj.HEIGHT ) * yelj.LOCATION_Y;
            if(_heigth > f.ywlTrainHeight/2 ){
                $("#head").append("<img class='ylImg' title="+_title+" src="+_icon+" style=position:absolute;left:" + ($trainLeft +  _width) + "px;  width=" + f.yelTrainWidth  + "px  height="+ f.ywlTrainHeight/2+ "px/>");
            }else{
                $("#head").append("<img class='ylImg' title="+_title+" src="+_icon+" style=position:absolute;padding-top:"+f.ywlTrainHeight/2 +"px;left:"+($trainLeft +  _width)+"px;  width=" + f.yelTrainWidth  + "px  height="+ f.ywlTrainHeight/2+ "px/>");
            };
          }
    },

    drawCar: function () {
        var $yelWidth = this.yelCarWidth = (canvas.width - (((canvas.width - translateX ) * 0.0388802488335925 + translateX ) + (canvas.height - translateY) / jspAngle)) / 15;
        var $yelHeight = this.ywlCarHeight = $yelWidth / jspAngle;

        var content = "<img src=images/home/yelj/all/l_h_t.gif  width=" + $yelWidth + "px height=" + $yelHeight + "px >";
        content += "<img src=images/home/yelj/all/r_h_t.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_h_t.gif    style = margin-left:-" + $yelWidth + "px    width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_h_t.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_f_t.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_f_t.gif    width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_f_t.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_f_t.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_l_t.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_l_t.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_l_t.gif  style = margin-left:-" + $yelWidth + "px   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_l_t.gif  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_lv_t.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_lv_t.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_lv_t.gif  style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_lv_t.gif  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_z_t.gif style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_z_t.gif  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_z_t.gif  style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_z_t.gif  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_zh_t.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_zh_t.gif  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_zh_t.gif  style = margin-left:-" + $yelWidth + "px   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_zh_t.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/tx.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/tx-r.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/tx.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/tx-r.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";


        if (!this.zoom) {
            content += " <img id=zoomIfram src=images/home/timg.png width=" + $yelWidth + "px height=" + $yelHeight + "px  style=float:right  onclick= \$.app.zoomClick() >";
        }

        $("#foot").append(content);

    },

    drawTrain: function () {
        var $yelWidth = this.yelTrainWidth  = (canvas.width - (((canvas.width - translateX ) * 0.0388802488335925 + translateX ) + (canvas.height - translateY) / jspAngle)) / 15;
        var $yelHeight = this.ywlTrainHeight  = $yelWidth / jspAngle;

        var content = "<img src=images/home/yelj/all/l_f_t.gif  width=" + $yelWidth + "px height=" + $yelHeight + "px >";
        content += "<img src=images/home/yelj/all/r_f_t.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_f_t.gif    style = margin-left:-" + $yelWidth + "px    width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_f_t.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_f_t.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_f_t.gif    width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_l_t.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_l_t.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_h_t.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_h_t.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_h_t.gif  style = margin-left:-" + $yelWidth + "px   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_h_t.gif  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_z_t.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_z_t.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_z_t.gif  style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_z_t.gif  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_lv_t.gif style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_lv_t.gif  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_lv_t.gif  style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_lv_t.gif  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_zh_t.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_zh_t.gif  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/l_zh_t.gif  style = margin-left:-" + $yelWidth + "px   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/all/r_zh_t.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/tx.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/tx-r.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/tx.gif   style = margin-left:-" + $yelWidth + "px  width=" + $yelWidth + "px height=" + $yelHeight + "px>";
        content += "<img src=images/home/yelj/tx-r.gif   width=" + $yelWidth + "px height=" + $yelHeight + "px>";


        $("#head").append(content);


    },


    draw3D: function (Xa, Ya, Xb, Yb, Xc, Yc, Xd, Yd, h, aId) {
        //计算顶面四个点的坐标(Xa1,Ya1,...)
        var dXa = Xa + h * 1 / Math.tan(70 * 0.017453292519943);
        var dYa = Ya - h * 1 / Math.tan(70 * 0.017453292519943);

        var dXb = Xb - h * 1 / Math.tan(70 * 0.017453292519943);
        var dYb = Yb - h * 1 / Math.tan(70 * 0.017453292519943);

        var dXc = Xc - h * 1 / Math.tan(70 * 0.017453292519943);
        var dYc = Yc + h * 1 / Math.tan(70 * 0.017453292519943);

        var dXd = Xd + h * 1 / Math.tan(70 * 0.017453292519943);
        var dYd = Yd + h * 1 / Math.tan(70 * 0.017453292519943);

        //底角四点坐标
        var tXa = Xa + (mc.yD - Ya) * meidAngle + $carLeft;
        var tYa = Ya;

        var tXb = Xb + (mc.yD - Yb) * meidAngle + $carLeft;
        var tYb = Yb;

        var tXc = Xc + (mc.yD - Yc) * meidAngle + $carLeft;
        var tYc = Yc;

        var tXd = Xd + (mc.yD - Yd) * meidAngle + $carLeft;
        var tYd = Yd;

        //顶角四点坐标
        var tdXa = dXa + (mc.yD - dYa) * meidAngle + $carLeft;
        var tdYa = dYa - h;

        var tdXb = dXb + (mc.yD - dYb) * meidAngle + $carLeft;
        var tdYb = dYb - h;

        var tdXc = dXc + (mc.yD - dYc) * meidAngle + $carLeft;
        var tdYc = dYc - h;

        var tdXd = dXd + (mc.yD - dYd) * meidAngle + $carLeft;
        var tdYd = dYd - h;

        //连接各个点
        ctx.save();
        ctx.translate(translateX, translateY);
        ctx.lineWidth = 1;


        //连接右侧面
        ctx.strokeStyle = "#444";
        ctx.fillStyle = "#222";
        ctx.beginPath();
        ctx.moveTo(tXb, tYb);
        ctx.lineTo(tdXb, tdYb);
        ctx.lineTo(tdXc, tdYc);
        ctx.lineTo(tXc, tYc);
        ctx.lineTo(tXb, tYb);
        ctx.stroke();
        ctx.fill();
        ctx.closePath();


        //连接内上侧面
        ctx.strokeStyle = "#444";
        ctx.fillStyle = "#111";
        ctx.beginPath();
        ctx.moveTo(tXc, tYc);
        ctx.lineTo(tdXc, tdYc);
        ctx.lineTo(tdXd, tdYd);
        ctx.lineTo(tXd, tYd);
        ctx.lineTo(tXc, tYc);
        ctx.fill();
        ctx.closePath();

        //连接下侧面
        ctx.strokeStyle = "#444";
        ctx.fillStyle = "#111";
        ctx.beginPath();
        ctx.moveTo(tXa, tYa);
        ctx.lineTo(tdXa, tdYa);
        ctx.lineTo(tdXb, tdYb);
        ctx.lineTo(tXb, tYb);
        ctx.lineTo(tXa, tYa);
        ctx.fill();
        ctx.closePath();


        //连接顶角各点
        ctx.strokeStyle = "#333";
        ctx.fillStyle = "#333";
        ctx.beginPath();
        ctx.moveTo(tdXa, tdYa);
        ctx.lineTo(tdXb, tdYb);
        ctx.lineTo(tdXc, tdYc);
        ctx.lineTo(tdXd, tdYd);
        ctx.lineTo(tdXa, tdYa);
        ctx.stroke();
        ctx.fill();
        ctx.closePath();
        ctx.restore();

         //设置煤场名链接
        var leftPx = tdXb - ((tdXb - tdXa) / 2);
        var topPx = tYa - translateY - 5;
        $("#alink" + aId).css({"left": leftPx + "px", "top": topPx + "px"});
        $.app.addTextMenu("alink" + aId);

    },

    drawDouljis: function (doulj) {
        var bark = new Image();
        if (doulj.course) {
            bark.src = "images/home/doulj/dlj_ic.png";
        } else {
            bark.src = "images/home/doulj/dlj_h.png";
        }
        bark.onload = function () {
            $.app.drawJsPics(bark, doulj);
        }
    },

    drawJsPics: function (bark, doulj) {
        var canvas = document.getElementById('canvasDiv');
        var context = canvas.getContext('2d');
        var dX = doulj.lng + (mc.yD - doulj.lat) * meidAngle + $carLeft;  // angle x
        var dY = doulj.lat;                                  // angle y
        dX = dX + translateX;
        dY = dY + translateY;
        context.save();
        context.translate(dX, dY);
        context.translate(-dX, -dY);
        context.drawImage(bark, dX - canvas.width * 0.0241581259150805, dY - canvas.height * 0.0286458333333333, canvas.width * 0.048, canvas.height * 0.057);
        context.restore();
    },


    drawDouljyb: function (doulj) {
        var bark = new Image();
        if (doulj.course) {
            bark.src = "images/home/doulj/yb.png";
        } else {
            bark.src = "images/home/doulj/yb_h.png";
        }
        bark.onload = function () {

            $.app.drawYbPics(bark, doulj);
        }
    },

    drawYbPics: function (bark, doulj) {
        var canvas = document.getElementById('canvasDiv');
        var context = canvas.getContext('2d')
        var dX = doulj.lng + (mc.yD - doulj.lat) * meidAngle + $carLeft;
        var dY = doulj.lat;
        dX = dX + translateX;
        dY = dY + translateY;

        var dx = dX;
        var dy = dY;
        var angle = (doulj.courseAngle - 90) * Math.PI / 180;

        /*
         var angle = (doulj.courseAngle - 90) * Math.PI / 180; // angle
         var radius = dY - ($page.height - $page.height*0.2) * 0.0572916666666667 / 2 - (dY - ($page.height - $page.height*0.2) * 0.0143229166666667 / 2) ; // the difference in y positions or the radius
         var dx = dX - $page.width * 0.0483162518301611 / 2 + radius * Math.sin(angle); // the draw x
         var dy = dY - ($page.height - $page.height*0.2) * 0.0572916666666667 / 2 - radius * Math.cos(angle); // the draw y
         */

        context.save();
        context.translate(dx, dy);
        if (doulj.course) {
            context.rotate(angle);
        }
        context.translate(-dx, -dy);
        // context.drawImage(bark, dX - $page.width * 0.0483162518301611 / 2, dy - ($page.height - $page.height * 0.2) * 0.0143229166666667 / 2, ($page.width) * 0.062, ($page.height - $page.height * 0.2) * 0.014);
        context.drawImage(bark, dX - $page.width * 0.0241581259150806, dy - canvas.height * 0.0095486111111112, canvas.height * 0.5, canvas.height * 0.014);
        context.restore();
    },

    //添加右键菜单
    addTextMenu: function (id) {
        var forRight = document.getElementById("mdRight");
        forRight.style.display = "none";
        var title = forRight.getElementsByTagName("li");
        for (var i = 0; i < title.length; i++) {
            title[i].onmouseover = function () {
                this.classname = "active";
            };
            title[i].onmouseout = function () {
                this.classname = "";
            };
        }
        var alink1 = document.getElementById(id);
        alink1.oncontextmenu = function (event) {
            var event = event || window.event;
            forRight.children[0].textContent = "存煤量: " + event.currentTarget.getAttribute('amount') + " t";
            forRight.children[1].textContent = "热值: " + event.currentTarget.getAttribute('qe') + " kcal/kg";
            forRight.children[2].textContent = "硫分: " + event.currentTarget.getAttribute('sgq') + " %";
            forRight.style.display = "block";
            forRight.style.left = event.clientX + "px";
            forRight.style.top = event.clientY + 5 + "px";
            return false;
        };
        alink1.onclick = function () {
            forRight.style.display = "none";
            return false;
        };
        document.onclick = function () {
            forRight.style.display = "none";
        };
    },


    meicActive: function (n, zoom) {
        $("a").remove(".meicLink");
        this.mcName = n;
        total = this.data.total[n];
        this.yardId = $('#meicSelt').val() == null ? $('#meicSelt', parent.document).val() : $('#meicSelt').val();
        if (!zoom) {
            window.parent.seltActive();
            return;
            $("p.mcName").text("煤场: " + n);
            $("p.mcAmount").text("存煤: " + total + " 吨");
        } else {
            $('#mcName', parent.document).text("煤场名称: " + n);
            $('#mcAmount', parent.document).text("煤场名称: " + total + " 吨");
        }
        cxt = canvas.getContext("2d");
        cxt.clearRect(0, 0, canvas.width, canvas.height);
        page = $.app.getPageSize();
        $height = $page.height - $page.height * 0.35;
        canvas.width = $page.width;
        canvas.height = $height;
        $angle = Math.atan((canvas.width * 0.3934681181959565 - canvas.width * 0.0388802488335925  ) / canvas.height) / Math.PI * 180;
        meidAngle = Math.tan($angle * 0.017453292519943);
        jspAngle = Math.tan((90 - $angle) * 0.017453292519943);
        translateX = 0 , translateY = 0;
        canvas.style.top = $page.height * 0.15;
        var imageObj = new Image();
        imageObj.src = "images/home/floor/all/dit.png";
        imageObj.onload = function () {
            ctx.drawImage(this, translateX, translateY, $page.width - translateX, $height - translateY);
        };
        $.app.drawYard();
    },

    intervalDraw: function () {
        $("a").remove(".meicLink");
        $("img").remove(".ylImg");
        cxt = canvas.getContext("2d");
        cxt.clearRect(0, 0, canvas.width, canvas.height);
        $page = $.app.getPageSize();
        $height = $page.height - $page.height * 0.35;
        canvas.width = $page.width;
        canvas.height = $height;
        $angle = Math.atan((canvas.width * 0.3934681181959565 - canvas.width * 0.0388802488335925  ) / canvas.height) / Math.PI * 180;
        meidAngle = Math.tan($angle * 0.017453292519943);
        jspAngle = Math.tan((90 - $angle) * 0.017453292519943);
        translateX = 0 , translateY = 0;
        canvas.style.top = $page.height * 0.15;
        var imageObj = new Image();
        imageObj.src = "images/home/floor/all/dit.png";
        imageObj.onload = function () {
            ctx.drawImage(this, translateX, translateY, $page.width - translateX, $height - translateY);
        };
        $.app.drawYard();
    },

    timeOut: function (t) {
        var _id = setInterval(function () {
            $.app.intervalDraw()
        }, t);
        if (!this.zoom) {
            this.intervals.push(_id);
        }
    },

    getPageSize: function () {
        var re = {};
        if (document.documentElement && document.documentElement.clientHeight) {
            var doc = document.documentElement;
            re.width = (doc.clientWidth > doc.scrollWidth) ? doc.clientWidth - 1 : doc.scrollWidth;
            re.height = (doc.clientHeight > doc.scrollHeight) ? doc.clientHeight : doc.scrollHeight;
        }
        else {
            var doc = document.body;
            re.width = (window.innerWidth > doc.scrollWidth) ? window.innerWidth : doc.scrollWidth;
            re.height = (window.innerHeight > doc.scrollHeight) ? window.innerHeight : doc.scrollHeight;
        }
        return re;
    },

    zoomClick: function () {
         window.parent.zoomClick();
    }
}




