$(function(){
	var list3 = "";
	window.onload = function(){
	    var vm = new Vue({
	        el:'#list',
	        created:function(){
	        	this.get()
	        },
	        data:{
	            bankCardList:{},
	        	userAccount:{}
	        },
	        methods:{
	            get:function(){
	                //发送get请求
	                this.$http.get("/card/bankcardlist").then(function(res){
	                   this.bankCardList = res.data.bankCardList;
	                   this.userAccount = res.data.userAccount;
	                },function(){
	                    console.log('请求失败处理');
	                });
	            },
	        }
	    });
	}
	
	
	goUnbundling = function(obj){
		var cardId = $(obj).parents("tr").find(".cardId").html();
//		alert(cardNum);
		var cardNum = $(obj).parents("tr").find(".cardNum").html();
		var unbundlingUrl = '/card/unbundling?cardId=' + cardId +'&cardNum=' + cardNum;
		window.location.href=unbundlingUrl;
	}
})

