$(function() {

	$('#sel12').click(function() {
		var sel = $('#sel12').val()
		$('#sp').text(sel)
	});

	$('#gettime').click(function() {
		settime(this);
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
	});

	$('#submit').click(function() {
		var selectBank = jQuery("#sel12  option:selected").text();
		var cardOwnerName = $('#cardOwnerName').val();
		var cardNum = $('#cardNum').val();
		var phoneNum = $('#phoneNum').val();
		var checkNum = $('#checkNum').val();
		var formData = new FormData();
		formData.append('selectBank', selectBank);
		formData.append('cardOwnerName', cardOwnerName);
		formData.append('cardNum', cardNum);
		formData.append('phoneNum', phoneNum);
		formData.append('checkNum', checkNum);
		$.ajax({
			url : "/card/addcard",
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert('恭喜,绑定新卡成功');
					window.location.href = '/card/mycard';
				} else {
					alert(data.errMsg);
				}
			}
		});
	});

});