function sendForm(items) {
    var URL = 'some path to server',
        form = items.form,
        name = items.name,
        password = items.password,
        submit = items.submit,
        formElems = [name, password];
    form.onsubmit = function(){
        return false;
    };
    formElems.forEach(function(item){
        item.addEventListener('input', function() {
            item.classList.remove('m-error');
        });
    });
    submit.addEventListener('click', function(e) {
        e.preventDefaut;
        if (!validate(name.value, password.value)) {
            name.classList.add('m-error');
            password.classList.add('m-error');
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

function validate(name, pass){
    if( !name || !pass) return false;
    if(pass.length < 8) return false;
    return true;
};


sendForm({
    form: document.forms[0],
    name: document.querySelector('.authentification__name'),
    password: document.querySelector('.authentification__password'),
    submit: document.querySelector('.authentification__submit')
});

