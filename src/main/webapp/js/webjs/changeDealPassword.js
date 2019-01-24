$(function(){
	setPwdSame = function(s){
		var dealPwd = $('#dealPwd').val();
		var reDealPwd = $('#reDealPwd').val();
		if(dealPwd == reDealPwd && dealPwd != ""){
			if(s){
				$('#billPwd').val(dealPwd);
				$('#reBillPwd').val(dealPwd);
				document.getElementsByName('billPwdName')[0].readOnly = true;
    			document.getElementsByName('billPwdName')[1].readOnly = true;
			}else{
				$('#billPwd').val("");
				$('#reBillPwd').val("");
				document.getElementsByName('billPwdName')[0].readOnly = false;
    			document.getElementsByName('billPwdName')[1].readOnly = false;	    				
			}
		} else {
			confirm("交易密码为空,或交易密码与其确认密码不一致");
			$('#reDealPwd').val("");
			$('#billPwd').val("");
			$('#reBillPwd').val("");
			document.getElementsByName('billPwdName')[0].readOnly = false;
			document.getElementsByName('billPwdName')[1].readOnly = false;
			document.getElementById('setpwdsame1').checked = false;
		}
	}
	
	checkIsSame = function(){
		var state = document.getElementById('setpwdsame1').checked;
		if(state){
			var dealPwd = $('#dealPwd').val();
			confirm('已经设置交易密码,资金密码相同');
			$('#billPwd').val(dealPwd);
			$('#reBillPwd').val(dealPwd);
		}
	}
	
	submit = function(){
		var dealPwd = $('#dealPwd').val();
		var reDealPwd = $('#reDealPwd').val();
		var billPwd = $('#billPwd').val();
		var reBillPwd = $('#reBillPwd').val();
		var checkNum = $('#checkNum').val();
		var phoneNum = $('#phoneNum').val();
		
		if(dealPwd != reDealPwd || dealPwd == "" || billPwd != reBillPwd || billPwd == ""){
    		alert("存在密码不一致, 请重新输入你错误的确认密码!");
			if(dealPwd != reDealPwd || dealPwd == ""){
    			$('#reDealPwd').val('');
			} 
			if(billPwd != reBillPwd || billPwd == "") {
    			$('#reBillPwd').val('');
			}
			return ;
		}
		var formData = new FormData();
		formData.append('dealPwd', dealPwd);
		formData.append('billPwd', billPwd);
		formData.append('checkNum', checkNum);
		formData.append('phoneNum', phoneNum);
		$.ajax({
			url:"/card/updateaccountpwd",
			type:'POST',
			data:formData,
			contentType:false,
			processData:false,
			cache:false,
			success:function(data){
				if(data.success){
					alert('修改密码成功');
//					window.location.href='/card/mycard';
				} else {
					alert(data.errMsg);
				}
			}
		});
	}
	
	gettimeClick = function(obj) {
		settime(obj);
		var phoneNum= '';
		var phoneNumStr = $('#phoneNum').val();
		$.ajax({
			url:"/card/checkinfo",
			type:'POST',
			data:{
				phoneNum:phoneNumStr
			},
			dataType: 'json',
			success:function(data){
				if(data.success){
					alert('操作成功');
				} else {
					alert('操作失败');
				}
			}
		});
	}
	
	var queryPhoneNumOfUser = "/card/findphonenumofuser"
		window.onload = function(){
		    var vm = new Vue({
		        el:'#phoneNumber',
		        created:function(){
		        	this.getCardByCardId()
		        },
		        data:{
		        	userInfo:{}
		        },
		        methods:{
		        	getCardByCardId:function(){
		                //发送get请求
		                this.$http.get(queryPhoneNumOfUser).then(function(res){
		                   this.userInfo = res.data.userInfo;
		                },function(){
		                    console.log('请求失败处理');
		                });
		        	}
		        },
		    })
		};
	
	
	
	
})