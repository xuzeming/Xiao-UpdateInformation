$(function() {
	var getUserInfo = "/card/finduserinfobylogid";
	window.onload = function() {
		var vm = new Vue({
			el : '#userInfo',
			created : function() {
				this.getCardByCardId()
			},
			data : {
				userInfo : {},
			},
			methods : {
				getCardByCardId : function() {
					//发送get请求
					this.$http.get(getUserInfo).then(function(res) {
						this.userInfo = res.data.userInfo;
					}, function() {
						console.log('请求失败处理');
					});
				}
			},
		})
	};

	gettimeClick = function(obj) {
		settime(obj);
		var phoneNum = '';
		var phoneNumStr = $('#phoneNum').val();
		$.ajax({
			url : "/card/checkinfo",
			type : 'POST',
			data : {
				phoneNum : phoneNumStr
			},
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					alert('操作成功');
				} else {
					alert('操作失败');
				}
			}
		});
	};

	updateSubmit = function() {
		var phoneNumStr = $('#phoneNum').val();
		var checkNum = $('#checkNum').val();
		var oldPwd = $('#oldPwd').val();
		var newPwd = $('#newPwd').val();
		var reNewPwd = $('#reNewPwd').val();
		var formData = new FormData();
		formData.append('phoneNum', phoneNumStr);
		formData.append('checkNum', checkNum);
		formData.append('oldPwd', oldPwd);
		formData.append('newPwd', newPwd);

		if (newPwd != "" && newPwd == reNewPwd) {
			$.ajax({
				url : "/card/updateuserpwd",
				type : 'POST',
				data : formData,
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
					if (data.success) {
						alert('修改密码成功');
//window.location.href='/card/mycard';
					} else {
						alert(data.errMsg);
					}
				}
			});
		} else {
			alert('再次输入的密码不一致')
		}
	}
})