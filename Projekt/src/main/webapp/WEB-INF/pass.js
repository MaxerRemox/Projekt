document.addEventListener("DOMContentLoaded", function () {


    const pass1Input = document.getElementById('pass1');
    const pass2Input = document.getElementById('pass2');
    const submitButton = document.querySelector('button[type="submit"]');
    const errorMessage = document.getElementById('error-message');
    submitButton.addEventListener('click', validateForm);

    function validateForm(e) {
        const passValid = pass1Input.value === pass2Input.value;
        if (!passValid) {
            errorMessage.innerHTML += 'Hasła nie są takie same<br>'
        }
        e.preventDefault();
    }
})