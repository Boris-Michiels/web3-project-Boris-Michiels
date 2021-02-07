if (document.forms["logInForm"]) document.forms["logInForm"].onsubmit = function(event) {validateLogInForm(event)};
if (document.forms["registerForm"]) document.forms["registerForm"].onsubmit = function(event) {validateRegisterForm(event)};
if (document.forms["changePasswordForm"]) document.forms["changePasswordForm"].onsubmit = function(event) {validateChangePasswordForm(event)};
if (document.forms["contactForm"]) document.forms["contactForm"].onsubmit = function(event) {validateContactForm(event)};
if (document.forms["testResultForm"]) document.forms["testResultForm"].onsubmit = function(event) {validateTestResultForm(event)};

function validateLogInForm(event) {
    let form, errors;
    errors = [];
    form = event.target;

    if (form["useridLogIn"].value.trim() === "") {
        errors.push("No userid given");
        form["useridLogIn"].value = "";
        form["useridLogIn"].className = "has-error";
    } else form["useridLogIn"].className = "has-success";
    if (form["passwordLogIn"].value.trim() === "") {
        errors.push("No password given");
        form["passwordLogIn"].value = "";
        form["passwordLogIn"].className = "has-error";
    } else form["passwordLogIn"].className = "has-success";

    if (errors.length > 0) {
        event.preventDefault();
        createErrorDivList(document.getElementsByTagName("h3")[0], "afterend", errors);
    }
}

function validateRegisterForm(event) {
    let form, errors;
    errors = [];
    form = event.target;

    if (form["userid"].value.trim() === "") {
        errors.push("No userid given");
        form["userid"].value = "";
        form["userid"].className = "form-group has-error";
    } else form["userid"].className = "form-group has-success";
    if (form["firstName"].value.trim() === "") {
        errors.push("No first name given");
        form["firstName"].value = "";
        form["firstName"].className = "form-group has-error";
    } else form["firstName"].className = "form-group has-success";
    if (form["lastName"].value.trim() === "") {
        errors.push("No last name given");
        form["lastName"].value = "";
        form["lastName"].className = "form-group has-error";
    } else form["lastName"].className = "form-group has-success";
    if (form["email"].value.trim() === "") {
        errors.push("No email given");
        form["email"].value = "";
        form["email"].className = "form-group has-error";
    } else {
        let EMAIL_PATTERN = new RegExp("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (!form["email"].value.match(EMAIL_PATTERN)) {
            errors.push("Email is not valid");
            form["email"].value = "";
            form["email"].className = "form-group has-error";
        } else form["email"].className = "form-group has-success";
    }
    if (form["password"].value.trim() === "") {
        errors.push("No password given");
        form["password"].value = "";
        form["password"].className = "has-error";
    } else form["password"].className = "form-group has-success";

    if (errors.length > 0) {
        event.preventDefault();
        createErrorDivList(document.getElementsByTagName("h3")[1], "afterend", errors);
    }
}

function validateChangePasswordForm(event) {
    let form, error;
    form = event.target;

    if (form["newPassword"].value.trim() === "") {
        error = "No password given";
        form["newPassword"].value = "";
        form["newPassword"].className = "has-error";
        event.preventDefault();
        createErrorDivP(document.getElementsByTagName("main")[0], "afterbegin", error);
    } else {
        form["newPassword"].className = "has-success";
    }
}

function validateContactForm(event) {
    let form, errors;
    errors = [];
    form = event.target;

    if (form["firstName"].value.trim() === "") {
        errors.push("No first name given");
        form["firstName"].value = "";
        form["firstName"].className = "form-group has-error";
    } else form["firstName"].className = "form-group has-success";
    if (form["lastName"].value.trim() === "") {
        errors.push("No last name given");
        form["lastName"].value = "";
        form["lastName"].className = "form-group has-error";
    } else form["lastName"].className = "form-group has-success";
    if (form["email"].value.trim() === "") {
        errors.push("No email given");
        form["email"].value = "";
        form["email"].className = "form-group has-error";
    } else {
        let EMAIL_PATTERN = new RegExp("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (!form["email"].value.match(EMAIL_PATTERN)) {
            errors.push("Email is not valid");
            form["email"].value = "";
            form["email"].className = "form-group has-error";
        } else form["email"].className = "form-group has-success";
    }
    if (form["phoneNumber"].value.trim() === "") {
        errors.push("No phone number given");
        form["phoneNumber"].value = "";
        form["phoneNumber"].className = "has-error";
    } else form["phoneNumber"].className = "form-group has-success";
    if (form["dateTime"].value.trim() === "") {
        errors.push("No date and/or time given");
        form["dateTime"].value = "";
        form["dateTime"].className = "has-error";
    } else form["dateTime"].className = "form-group has-success";

    if (errors.length > 0) {
        event.preventDefault();
        createErrorDivList(document.getElementsByTagName("h3")[1], "afterend", errors);
    }
}

function validateTestResultForm(event) {
    let form, error;
    form = event.target;

    if (form["date"].value.trim() === "") {
        error = "No date given";
        form["date"].value = "";
        form["date"].className = "has-error";
        event.preventDefault();
        createErrorDivP(document.getElementsByTagName("h3")[0], "afterend", error);
    } else {
        form["date"].className = "has-success";
    }
}

function createErrorDivP(element, place, error) {
    removeMessageDivs();

    element.insertAdjacentHTML(place, "<div class=\"alert-danger\"><p>" + error + "</p></div>");
}

function createErrorDivList(element, place, errors) {
    removeMessageDivs();

    let errorDivs = document.getElementsByClassName("alert-danger"), ul;

    element.insertAdjacentHTML(place, "<div class=\"alert-danger\"><ul></ul></div>");
    ul = errorDivs[0].getElementsByTagName("ul")[0];
    for (let i = 0; i < errors.length; i++) {
        ul.insertAdjacentHTML("beforeend", "<li>" + errors[i] + "</li>");
    }
}

function removeMessageDivs() {
    let errorDivs = document.getElementsByClassName("alert-danger"), successDivs = document.getElementsByClassName("alert-success");

    while(errorDivs[0]) {
        errorDivs[0].parentNode.removeChild(errorDivs[0]);
    }

    while(successDivs[0]) {
        successDivs[0].parentNode.removeChild(successDivs[0]);
    }
}