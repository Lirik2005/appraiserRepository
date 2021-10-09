addConclusionBtnEvent();

eventForConclusionPage();


function addConclusionBtnEvent() {
    document.getElementById("addConclusionBtn").addEventListener('click', function (event) {
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
        eventForConclusionPage();
    })
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function editConclusion(href) {
    fetch(href)
        .then(response => response.text()).then(fragment => {
        document.querySelector('#editConclusionModal').innerHTML = fragment
    })
        .then(() => {
            let modal = new bootstrap.Modal(document.querySelector("#editConclusionModal"), {});
            modal.show();
            document.getElementById("edit_conclusion").addEventListener('submit', event => submitEditConclusionForm(event))
        });
}

async function submitEditConclusionForm(event) {
    event.preventDefault();
    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });
    let response = fetch(request).then(response => response.text()).then(fragment => {
        let modal = bootstrap.Modal.getInstance(document.getElementById('editConclusionModal'));
        modal.hide();
        document.querySelector(".businessConclusion_list").innerHTML = fragment;
        eventForConclusionPage();
    });
}

function eventForConclusionPage() {
    document.querySelectorAll('.table .editBusinessBtn').forEach(editBusinessBtn => editBusinessBtn.addEventListener('click', function (event) {
        event.preventDefault()
        let href = this.getAttribute('href');
        editConclusion(href)
    }));
    document.querySelectorAll('.table .deleteBusinessBtn').forEach(deleteBusinessBtn => deleteBusinessBtn.addEventListener('click', function (event) {
        event.preventDefault()
        let href = this.getAttribute('href');
        document.querySelector('#deleteModal .modal-footer a').setAttribute('href', href)
        let modal = new bootstrap.Modal(document.querySelector("#deleteModal"), {});
        modal.show();
        document.getElementById('deleteBusinessConclusion').addEventListener('click', function (event) {
            event.preventDefault();
            fetch(href).then(response => response.text()).then(fragment => {
                document.querySelector(".businessConclusion_list").innerHTML = fragment;
                modal.hide();
                eventForConclusionPage();
            })
        })
    }));

}


/*async function saveAppraiser(request) {
    let response = await fetch(request);
    let appraiserTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('addModal'));
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
