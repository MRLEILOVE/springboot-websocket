// JavaScript Document
var info = new Vue({
	el:"#app",
	data:{
        body:{},
		sites:[
		    {"name":'用户管理',"imagesrc":'/statics/images/adminicon.png',
			 labels:[
                 {ahref:"/admin-list",labelname:"全部用户"},
                 {ahref:"/operate-list",labelname:"运营人员"},
				 {ahref:"/maintainer-list",labelname:"维护人员"},
             ]
			
			},
		   {"name":'设备管理',"imagesrc":'/statics/images/equpicon.png',
			 labels:[
				 {ahref:"/device/list",labelname:"全部设备"},
				 {ahref:"/device/debug",labelname:"设备调试"},
                 {ahref:"/moduleState",labelname:"模块状态管理"}, // 302上报日志
                 // {ahref:"/admin-add",labelname:"设备解绑"},
				  // {ahref:"/admin-add",labelname:"订单管理"},
				  // {ahref:"/admin-add",labelname:"每日统计"},
				  // {ahref:"/admin-add",labelname:"故障记录"},
			 ]
			
			},
            {"name":'运营中心',"imagesrc":'/statics/images/operateicon.png',
                labels:[
                    // {ahref:"/siteType",labelname:"场所类型管理"},
                    {ahref:"/agentList",labelname:"代理商管理"},
                    {ahref:"/saleAe",labelname:"销售代表管理"},
                    {ahref:"/packageManagement",labelname:"套餐管理"},
                    {ahref:"/siteManagement",labelname:"场所管理"},

                ]

            },
            {"name":'财务管理',"imagesrc":'/statics/images/moneyicon.png',
                labels:[
                    {ahref:"/accountAgency",labelname:"代理结算"},
                    {ahref:"/accountOperate",labelname:"销售代表结算"},
                    {ahref:"/accountSiteAdminister",labelname:"场所管理员结算"},
                ]

            },
            {"name":'统计管理',"imagesrc":'/statics/images/staticicon.png',
                labels:[
                    {ahref:"/orderList",labelname:"订单管理"},
                    // {ahref:"/placeStatistics",labelname:"场所统计"},
                    {ahref:"/placeDivide",labelname:"场所分成"},
                ]

            },
            {"name":'日志管理',"imagesrc":'/statics/images/configicon.png',
                labels:[
                    {ahref:"/loginLogger",labelname:"系统日志"},
                    // {ahref:"/logRecord",labelname:"订单日志"},
                    // {ahref:"/moduleLog",labelname:"模块日志"}, // 303上报日志
                ]

            },

		]
		

	},
    mounted:function () {
        this.$http.post('/findUser',{},{emulateJSON:true}).then(function(res){
            this.body = res.body
            // console.log(res);
        },function(res){
            console.log(res.status);
        });
    }
	
	
	
})