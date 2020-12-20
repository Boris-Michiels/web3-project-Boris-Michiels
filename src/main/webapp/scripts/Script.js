if (document.getElementById("contactsTable")) {
    document.addEventListener("DOMContentLoaded", sortContactTable);
}

function sortContactTable() {
    let table, rows, switching, i, x, y;
    table = document.getElementById("contactsTable");
    switching = true;

    while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++) {
            x = rows[i].getElementsByTagName("TD");
            y = rows[i + 1].getElementsByTagName("TD");
            if (dateTimeToComparableInt(x[0].innerHTML, x[1].innerHTML) < dateTimeToComparableInt(y[0].innerHTML, y[1].innerHTML)) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                break;
            }
        }
    }
}

function filterContactsTable() {
    let from, until, fromDate, fromTime, untilDate, untilTime, rows, i, x;
    from = document.getElementById("fromContact").value;
    until = document.getElementById("untilContact").value;
    rows = document.getElementById("contactsTable").rows;

    for (i = 1; i < rows.length; i++) {
        rows[i].style.display = "";
    }

    if (from) {
        fromDate = from.split("T")[0];
        fromTime = from.split("T")[1];
        for (i = 1; i < rows.length; i++) {
            x = rows[i].getElementsByTagName("TD");
            if (dateTimeToComparableInt(x[0].innerHTML, x[1].innerHTML) < dateTimeToComparableInt(fromDate, fromTime)) {
                rows[i].style.display = "none";
            }
        }
    }

    if (until) {
        untilDate = until.split("T")[0];
        untilTime = until.split("T")[1];
        for (i = 1; i < rows.length; i++) {
            x = rows[i].getElementsByTagName("TD");
            if (dateTimeToComparableInt(x[0].innerHTML, x[1].innerHTML) > dateTimeToComparableInt(untilDate, untilTime)) {
                rows[i].style.display = "none";
            }
        }
    }
}

function clearContactsFilter() {
    let rows, i;
    document.getElementById("fromContact").value = "";
    document.getElementById("untilContact").value = "";
    rows = document.getElementById("contactsTable").rows;

    for (i = 1; i < rows.length; i++) {
        rows[i].style.display = "";
    }
}

function dateTimeToComparableInt(dateString, timeString) {
    let dateArray, timeArray, dstr, tstr;

    if (dateString.includes("/")) {
        dateArray = dateString.split("/");
        dstr = dateArray[2] + dateArray[1] + dateArray[0];
    } else if (dateString.includes("-")) {
        dateArray = dateString.split("-");
        dstr = dateArray[0] + dateArray[1] + dateArray[2];
    }

    if (timeString.includes(":")) {
        timeArray = timeString.split(":");
        tstr = timeArray[0] + timeArray[1];
    }
    return parseInt(dstr + tstr);
}