$("#searchBox").hide();
$("#dataBigBox").hide();
function searchs_show() {
    $("#searchBox").toggle(200);
}

function data_show() {
    $("#dataBigBox").toggle(200);
}
$("#paymentOrderTh").hide();
$(".paymentOrderTd").hide();
$("#userIDTh").hide();
$(".userIDTd").hide();
$("#orderTimeTh").hide();
$(".orderTimeTd").hide();
$("#agencyTh").hide();
$(".agencyTd").hide();
$("#salerTh").hide();
$(".salerTd").hide();
$("#placeTh").hide();
$(".placeTd").hide();
$("#placemanTh").hide();
$(".placemanTd").hide();
$("#packageNameTh").hide();
$(".packageNameTd").hide();

layui.use('form', function () {
    var form = layui.form;
    form.on('checkbox(c_orderNumber)', function (data) {
        // console.log(data.elem); //得到checkbox原始DOM对象
        // console.log(data.elem.checked); //是否被选中，true或者false
        // console.log(data.value); //复选框value值，也可以通过data.elem.value得到
        // console.log(data.othis); //得到美化后的DOM对象
        if (data.elem.checked) {
            $("#orderNumberTh").show();
            $(".orderNumberTd").show();
        } else {
            $("#orderNumberTh").hide();
            $(".orderNumberTd").hide();
        }
    });

    form.on('checkbox(c_payment)', function (data) {
        if (data.elem.checked) {
            $("#paymentTh").show();
            $(".paymentTd").show();
        } else {
            $("#paymentTh").hide();
            $(".paymentTd").hide();
        }
    });

    form.on('checkbox(c_paymentOrder)', function (data) {
        if (data.elem.checked) {
            $("#paymentOrderTh").show();
            $(".paymentOrderTd").show();
        } else {
            $("#paymentOrderTh").hide();
            $(".paymentOrderTd").hide();
        }
    });
    form.on('checkbox(c_userId)', function (data) {
        if (data.elem.checked) {
            $("#userIDTh").show();
            $(".userIDTd").show();
        } else {
            $("#userIDTh").hide();
            $(".userIDTd").hide();
        }
    });

    form.on('checkbox(c_orderTime)', function (data) {
        if (data.elem.checked) {
            $("#orderTimeTh").show();
            $(".orderTimeTd").show();
        } else {
            $("#orderTimeTh").hide();
            $(".orderTimeTd").hide();
        }
    });

    form.on('checkbox(c_agency)', function (data) {
        if (data.elem.checked) {
            $("#agencyTh").show();
            $(".agencyTd").show();
        } else {
            $("#agencyTh").hide();
            $(".agencyTd").hide();
        }
    });
    form.on('checkbox(c_saler)', function (data) {
        if (data.elem.checked) {
            $("#salerTh").show();
            $(".salerTd").show();
        } else {
            $("#salerTh").hide();
            $(".salerTd").hide();
        }
    });

    form.on('checkbox(c_place)', function (data) {
        if (data.elem.checked) {
            $("#placeTh").show();
            $(".placeTd").show();
        } else {
            $("#placeTh").hide();
            $(".placeTd").hide();
        }
    });

    
    form.on('checkbox(c_placeman)', function (data) {
        if (data.elem.checked) {
            $("#placemanTh").show();
            $(".placemanTd").show();
        } else {
            $("#placemanTh").hide();
            $(".placemanTd").hide();
        }
    });

    form.on('checkbox(c_powerNumber)', function (data) {
        if (data.elem.checked) {
            $("#powerNumberTh").show();
            $(".powerNumberTd").show();
        } else {
            $("#powerNumberTh").hide();
            $(".powerNumberTd").hide();
        }
    });

    form.on('checkbox(c_packageName)', function (data) {
        if (data.elem.checked) {
            $("#packageNameTh").show();
            $(".packageNameTd").show();
        } else {
            $("#packageNameTh").hide();
            $(".packageNameTd").hide();
        }
    });

    
    form.on('checkbox(c_startTime)', function (data) {
        if (data.elem.checked) {
            $("#startTimeTh").show();
            $(".startTimeTd").show();
        } else {
            $("#startTimeTh").hide();
            $(".startTimeTd").hide();
        }
    });
    form.on('checkbox(c_stopTime)', function (data) {
        if (data.elem.checked) {
            $("#stopTimeTh").show();
            $(".stopTimeTd").show();
        } else {
            $("#stopTimeTh").hide();
            $(".stopTimeTd").hide();
        }
    });

    form.on('checkbox(c_orderMoney)', function (data) {
        if (data.elem.checked) {
            $("#orderMoneyTh").show();
            $(".orderMoneyTd").show();
        } else {
            $("#orderMoneyTh").hide();
            $(".orderMoneyTd").hide();
        }
    });
    form.on('checkbox(c_orderState)', function (data) {
        if (data.elem.checked) {
            $("#orderStateTh").show();
            $(".orderStateTd").show();
        } else {
            $("#orderStateTh").hide();
            $(".orderStateTd").hide();
        }
    });
    form.on('checkbox(c_refund)', function (data) {
        if (data.elem.checked) {
            $("#refundTh").show();
            $(".refundTd").show();
        } else {
            $("#refundTh").hide();
            $(".refundTd").hide();
        }
    });

});