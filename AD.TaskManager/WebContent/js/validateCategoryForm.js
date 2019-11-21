function validate() {
	var field = document.getElementById('name');
	var msg = '';
	if (field.value === '')
		msg += 'The field <i>name</i> cannot be empty<hr>';

	if (msg !== '') {
		msg = msg.slice(0, msg.length - '<hr>'.length);
		showAlert(msg);
	} else
		document.getElementsByTagName('form')[0].submit();
}