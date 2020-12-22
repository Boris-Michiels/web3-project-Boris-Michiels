function validateLogInForm() {
    let form, userid, password, errors;
    errors = [];
    form = document.forms["logInForm"];
    userid = form["useridLogIn"].value;
    password = form["passwordLogIn"].value;

    if (userid.trim() === "") {
        errors.push("No userid given");
        form["useridLogIn"].value = "";
        form["useridLogIn"].className = "has-error";
    } else form["useridLogIn"].className = "has-success";
    if (password.trim() === "") {
        errors.push("No password given");
        form["passwordLogIn"].value = "";
        form["passwordLogIn"].className = "has-error";
    } else form["passwordLogIn"].className = "has-success";

    if (errors.length > 0) {
        createErrorDivList(document.getElementsByTagName("h3")[0], "afterend", errors);
        return false;
    } else return true;
}

function validateRegisterForm() {
    let form, userid, firstName, lastName, email, password, errors;
    errors = [];
    form = document.forms["registerForm"];
    userid = form["userid"].value;
    firstName = form["firstName"].value;
    lastName = form["lastName"].value;
    email = form["email"].value;
    password = form["password"].value;

    if (userid.trim() === "") {
        errors.push("No userid given");
        form["userid"].value = "";
        form["userid"].className = "form-group has-error";
    } else form["userid"].className = "form-group has-success";
    if (firstName.trim() === "") {
        errors.push("No first name given");
        form["firstName"].value = "";
        form["firstName"].className = "form-group has-error";
    } else form["firstName"].className = "form-group has-success";
    if (lastName.trim() === "") {
        errors.push("No last name given");
        form["lastName"].value = "";
        form["lastName"].className = "form-group has-error";
    } else form["lastName"].className = "form-group has-success";
    if (email.trim() === "") {
        errors.push("No email given");
        form["email"].value = "";
        form["email"].className = "form-group has-error";
    } else {
        let EMAIL_PATTERN = new RegExp("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (!email.match(EMAIL_PATTERN)) {
            errors.push("Email is not valid");
            form["email"].value = "";
            form["email"].className = "form-group has-error";
        } else form["email"].className = "form-group has-success";
    }
    if (password.trim() === "") {
        errors.push("No password given");
        form["password"].value = "";
        form["password"].className = "has-error";
    } else form["password"].className = "form-group has-success";

    if (errors.length > 0) {
        createErrorDivList(document.getElementsByTagName("h3")[1], "afterend", errors);
        return false;
    } else return true;
}

function validateChangePasswordForm() {
    let form, password, error;
    form = document.forms["changePasswordForm"];
    password = form["newPassword"].value;

    if (password.trim() === "") {
        error = "No password given";
        form["newPassword"].value = "";
        form["newPassword"].className = "has-error";
        createErrorDivP(document.getElementsByTagName("main")[0], "afterbegin", error);
        return false;
    } else {
        form["newPassword"].className = "has-success";
        return true;
    }
}

function validateContactForm() {
    let form, firstName, lastName, email, phone, dateTime, errors;
    errors = [];
    form = document.forms["contactForm"];
    firstName = form["firstName"].value;
    lastName = form["lastName"].value;
    email = form["email"].value;
    phone = form["phoneNumber"].value;
    dateTime = form["dateTime"].value;

    if (firstName.trim() === "") {
        errors.push("No first name given");
        form["firstName"].value = "";
        form["firstName"].className = "form-group has-error";
    } else form["firstName"].className = "form-group has-success";
    if (lastName.trim() === "") {
        errors.push("No last name given");
        form["lastName"].value = "";
        form["lastName"].className = "form-group has-error";
    } else form["lastName"].className = "form-group has-success";
    if (email.trim() === "") {
        errors.push("No email given");
        form["email"].value = "";
        form["email"].className = "form-group has-error";
    } else {
        let EMAIL_PATTERN = new RegExp("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (!email.match(EMAIL_PATTERN)) {
            errors.push("Email is not valid");
            form["email"].value = "";
            form["email"].className = "form-group has-error";
        } else form["email"].className = "form-group has-success";
    }
    if (phone.trim() === "") {
        errors.push("No phone number given");
        form["phoneNumber"].value = "";
        form["phoneNumber"].className = "has-error";
    } else form["phoneNumber"].className = "form-group has-success";
    if (dateTime.trim() === "") {
        errors.push("No date and/or time given");
        form["dateTime"].value = "";
        form["dateTime"].className = "has-error";
    } else form["dateTime"].className = "form-group has-success";

    if (errors.length > 0) {
        createErrorDivList(document.getElementsByTagName("h3")[1], "afterend", errors);
        return false;
    } else return true;
}

function validateTestResultForm() {
    let form, date, error;
    form = document.forms["testResultForm"];
    date = form["date"].value;

    if (date.trim() === "") {
        error = "No password given";
        form["date"].value = "";
        form["date"].className = "has-error";
        createErrorDivP(document.getElementsByTagName("h3")[0], "afterend", error);
        return false;
    } else {
        form["date"].className = "has-success";
        return true;
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