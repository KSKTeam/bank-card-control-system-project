function sendForm(items) {
    var URL = 'http://localhost:8080/cardcontrolproject/webapi/users',
        form = items.form,
        name = items.name,
        password = items.password,
        submit = items.submit,
        formElems = [name, password],
        errorElem = items.errorElem;
    form.onsubmit = function(){
        return false;
    };
    formElems.forEach(function(item){
        item.addEventListener('input', function() {
            item.classList.remove('m-error');
            errorElem.style.display = 'none';
        });
    });
    submit.addEventListener('click', function(e) {
        e.preventDefaut;
        if (!validate(name.value, password.value)) {
            name.classList.add('m-error');
            password.classList.add('m-error');
        } else {
            var XHR = new XMLHttpRequest();
//            var data = JSON.stringify({
//                login: name.value,
//                password: password.value
//            });
            
            var new_URL = URL + '?login=' + name.value + '&password=' + password.value;
            console.log(new_URL);
            XHR.open("GET", new_URL, true);
            XHR.send();
            XHR.onreadystatechange = function(){
            	if(XHR.readyState != 4) return;
            	
            	if(XHR.status === 204){
            		name.classList.add('m-error');
                    password.classList.add('m-error');
                    errorElem.style.display = 'block';
            	}
            	if(XHR.status === 200){
            		var data = JSON.parse(XHR.responseText),
            			id = data.id;
            		console.log(id);
            		localStorage.setItem('trans-id', id );
            		window.location.href = 'transactions.html';
            	}
            }
        }
    });
}

function validate(name, pass){
    if( !name || !pass) return false;
    //if(pass.length < 8) return false;
    return true;
};


sendForm({
    form: document.forms[0],
    name: document.querySelector('.authentification__name'),
    password: document.querySelector('.authentification__password'),
    submit: document.querySelector('.authentification__submit'),
    errorElem: document.querySelector('.error')
});

