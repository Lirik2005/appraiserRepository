addConclusionBtnEvent();
eventForAppraiserPage();


function addConclusionBtnEvent() {
    document.getElementById("addBtn").addEventListener('click', function (event) {
        event.preventDefault();
        let href = this.getAttribute('href');
        fetch(href)
            .then(response => response.text()).then(fragment => {
            document.querySelector('#addConclusionModal').innerHTML = fragment

        })
            .then(() => {
                let modal = new bootstrap.Modal(document.querySelector("#addConclusionModal"), {});
                modal.show();
                document.getElementById("add_conclusion").addEventListener('submit', event => submitNewConclusionForm(event))
            })
    });
}

async function submitNewConclusionForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });
    fetch(request).then(response => response.text()).then(fragment => {
        let modal = bootstrap.Modal.getInstance(document.getElementById("addConclusionModal"));
        modal.hide();
        document.querySelector(".businessConclusion_list").innerHTML = fragment;
        //    eventForConclusionPage();
    })
}

/*async function saveAppraiser(request) {
    let response = await fetch(request);
    let appraiserTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('addModal'));
    modal.hide();
    document.querySelector(".appraiser_list").innerHTML = appraiserTable;
    eventForAppraiserPage();
}

function editAppraiser(href) {
    fetch(href)
        .then(response => response.text()).then(fragment => {
        document.querySelector('#editModal').innerHTML = fragment
    })
        .then(() => {
            let modal = new bootstrap.Modal(document.querySelector("#editModal"), {});
            modal.show();
            document.getElementById("edit_appraiser").addEventListener('submit', event => submitEditAppraiserForm(event))
        });
}

function eventForAppraiserPage() {
    document.querySelectorAll('.table .editBtn').forEach(editBtn => editBtn.addEventListener('click', function (event) {
        event.preventDefault()
        let href = this.getAttribute('href');
        editAppraiser(href)
    }));

    document.querySelectorAll('.table .deleteBtn').forEach(deleteBtn => deleteBtn.addEventListener('click', function (event) {
        event.preventDefault()
        let href = this.getAttribute('href');
        document.querySelector('#deleteModal .modal-footer a').setAttribute('href', href)
        let modal = new bootstrap.Modal(document.querySelector("#deleteModal"), {});
        modal.show();
        document.getElementById('deleteAppraiser').addEventListener('click', function (event) {
            event.preventDefault();
            fetch(href).then(response => response.text()).then(fragment => {
                document.querySelector(".appraiser_list").innerHTML = fragment;
                modal.hide();
                eventForAppraiserPage();
            })
        })
    }));

}

async function submitEditAppraiserForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });
    let response = await fetch(request);
    let appraiserTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('editModal'));
    modal.hide();
    document.querySelector(".appraiser_list").innerHTML = appraiserTable;
    eventForAppraiserPage();
}

const path = "http://localhost:8080/appraisers"

async function searchAppraiser() {
    let searchWord = document.getElementById('searchText').value
    const param = new URLSearchParams({
        "searchText": searchWord
    });
    fetch(path + "/filter?" + param).then(response => response.text()).then(fragment => {
        document.querySelector(".appraiser_list").innerHTML = fragment
        eventForAppraiserPage();
    })

}*/
