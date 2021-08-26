document.addEventListener("DOMContentLoaded", function (event) {
    addAppraiserBtnEvent();
    eventForAppraiserPage();
});

function addAppraiserBtnEvent() {
    document.getElementById("addBtn").addEventListener('click', function (event) {
        event.preventDefault();
        let href = this.getAttribute('href');
        fetch(href)
            .then(response => response.text()).then(fragment => {
            document.querySelector('#addModal').innerHTML = fragment

        })
            .then(() => {
                let modal = new bootstrap.Modal(document.querySelector("#addModal"), {});
                modal.show();
                document.getElementById("add_appraiser").addEventListener('submit', event => submitNewAppraiserForm(event))
            })
    });
}

async function submitNewAppraiserForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });
    const param = new URLSearchParams({
        "login": formData.get('login')
    });
    fetch("appraisers/checkLogin?" + param).then(response => {
            if (response.ok) {
                saveAppraiser(request)
            } else {
                return Promise.reject(response);
            }
        }
    ).catch(error => error.text()).then(errorFragment => {
        document.querySelector("#addModal .custom-alert").innerHTML = errorFragment;
    });
}

async function saveAppraiser(request) {
    let response = await fetch(request);
    let appraiserTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('addModal'));
    modal.hide();
    document.querySelector(".appraiser_list").innerHTML = appraiserTable;
    eventForAppraiserPage();
}







function editUser(href) {
    fetch(href)
        .then(response => response.text()).then(fragment => {
        document.querySelector('#editModal').innerHTML = fragment

    })
        .then(() => {
            let modal = new bootstrap.Modal(document.querySelector("#edi"), {});
            modal.show();
            document.getElementById("edit_user").addEventListener('submit', event => submitEditUserForm(event))
        });
}

function eventForAppraiserPage() {
    document.querySelectorAll('.table tbody tr').forEach(trClick => trClick.addEventListener('dblclick', function (event) {
        event.preventDefault()
        let href = trClick.querySelector('a').getAttribute('href')
        editUser(href)
    }));

    document.querySelectorAll('.table .editBtn').forEach(editBtn => editBtn.addEventListener('click', function (event) {
        event.preventDefault()
        let href = this.getAttribute('href');
        editUser(href)
    }));

    document.querySelectorAll('.table .deleteBtn').forEach(deleteBtn => deleteBtn.addEventListener('click', function (event) {
        event.preventDefault()
        let href = this.getAttribute('href');
        document.querySelector('#deleteModal .modal-footer a').setAttribute('href', href)
        let modal = new bootstrap.Modal(document.querySelector("#deleteModal"), {});
        modal.show();
        document.getElementById('delUser').addEventListener('click', function (event) {
            event.preventDefault();
            fetch(href).then(response => response.text()).then(fragment => {
                document.querySelector(".user_list").innerHTML = fragment;
                modal.hide();
                eventForAppraiserPage();
            })
        })

    }));
}




async function submitEditUserForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });
    let response = await fetch(request);
    let userTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('editModal'));
    modal.hide();
    document.querySelector(".user_list").innerHTML = userTable;

    eventForAppraiserPage();
}

const path = "http://localhost:8080/users"

async function filterUser() {
    let searchWord = document.getElementById('searchWord').value
    const param = new URLSearchParams({
        "s": searchWord
    })
    fetch(path + "/filter?" + param).then(response => response.text()).then(fragment => {
        document.querySelector(".user_list").innerHTML = fragment
        eventForAppraiserPage();
    })


}
