function validate() {
	var field = document.getElementById('title');
	var msg = '';
	if (field.value === '')
		msg += 'The field <i>title</i> cannot be empty<hr>';

	field = document.getElementById('description');
	if (field.value === '')
		msg += 'The field <i>description</i> cannot be empty<hr>';

	field = document.getElementById('category');
	if (field.selectedIndex === 0)
		msg += 'You have to select one <i>category</i><hr>';

	field = document.getElementById('init_date');
	if (field.value === '') {
		msg += 'The field <i>start date</i> cannot be empty<hr>';
	} else {
		var init_date, deadline, today;
		init_date = new Date(Date.parse(field.value));
		today = new Date(new Date().toISOString().slice(0, 10));
		if (init_date.getTime() < today.getTime())
			msg += 'The <i>start date</i> cannot be earlier than today<hr>';
	}
	field = document.getElementById('end_date');
	if (field.value === '') {
		msg += 'The field <i>end date</i> cannot be empty<hr>';
	} else {
		deadline = new Date(Date.parse(field.value));
		if (deadline.getTime() <= init_date.getTime())
			msg += 'The <i>deadline</i> cannot be earlier than then <i>start date</i><hr>';
	}
	if (msg !== '') {
		msg = msg.slice(0, msg.length - '<hr>'.length);
		showAlert(msg);
	} else
		document.getElementsByTagName('form')[0].submit();
}