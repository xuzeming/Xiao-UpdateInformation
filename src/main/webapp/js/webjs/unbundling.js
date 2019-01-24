$(function() {
	//从URL获取product参数的值
	var cardId = getQueryString('cardId');

	function getQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = location.search.substr(1).match(reg);
		if (r != null)
			return unescape(decodeURI(r[2]));
		return null;
	}

	var queryCardByCardIdUrl = "/card/findcardbycardid?cardId=" + cardId;
	window.onload = function() {
		var vm = new Vue({
			el : '#card',
			created : function() {
				this.getCardByCardId()
			},
			data : {
				deleteMyCard : {},
			},
			methods : {
				getCardByCardId : function() {
					//发送get请求
					this.$http.get(queryCardByCardIdUrl).then(function(res) {
						this.deleteMyCard = res.data.card;
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

	deleteSubmit = function() {
		var phoneNumStr = $('#phoneNum').val();
		var checkNum = $('#checkNum').val();
		var formData = new FormData();
		formData.append('phoneNum', phoneNumStr);
		formData.append('checkNum', checkNum);
		formData.append('cardId', cardId);
		$.ajax({
			url : "/card/deletecardunionbycardid",
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert('解除绑定成功');
					window.location.href = '/card/mycard';
				} else {
					alert(data.errMsg);
				}
			}
		});
	};
});