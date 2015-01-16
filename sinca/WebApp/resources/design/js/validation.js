function validateFields(fields) {
	for ( var i = 0; i < fields.length; i++) {
		var field = fields[i];
		var id = field.id;
		var msg = field.msg;

		if ($("#" + id).val() == "") {
			$("#conteudoMsg").text(msg);
			$("#idMsg").val(id);
			popupMsg.show();
			return false;
		}
	}
	return true;
}
function focusMsg(){
	$("#"+$("#idMsg").val()).focus();
}