/*
	function sendForm
	@param selector 
*/

function sendForm(items) {

    var URL = 'some path to server',
        form = items.form,
        name = items.name,
        password = items.password,
        submit = items.submit;

    name.addEventListener('input', function() {

        name.classList.remove('error');
        password.classList.remove('error');

    })

    submit.addEventListener('click', function(e) {

        e.preventDefaut;

        //Now validation is very simple, because database doesn't exist

        if (!name.value || !password.value) {

            name.classList.add('error');
            password.classList.add('error');

        } else {

            var XHR = new XMLHttpRequest();
            var data = JSON.stringify({
                name: name.value,
                password: password.value
            });

            XHR.open("POST", URL, true);
            XHR.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            XHR.send(data);

        }

    });

}

sendForm({
    form: document.forms[0],
    name: document.querySelector('.authentification__name'),
    password: document.querySelector('.authentification__password'),
    submit: document.querySelector('.authentification__submit')
});