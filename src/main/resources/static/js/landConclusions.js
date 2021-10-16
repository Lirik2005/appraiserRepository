addLandConclusionBtnEvent();

eventForLandConclusionPage();


function addLandConclusionBtnEvent() {
    document.getElementById("addLandConclusionBtn").addEventListener('click', function (event) {
        event.preventDefault();
        let href = this.getAttribute('href');
        fetch(href)
            .then(response => response.text()).then(fragment => {
            document.querySelector('#addLandConclusionModal').innerHTML = fragment

        })
            .then(() => {
                let modal = new bootstrap.Modal(document.querySelector("#addLandConclusionModal"), {});
                modal.show();
                document.getElementById("add_land_conclusion").addEventListener('submit', event => submitNewConclusionForm(event))
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
        let modal = bootstrap.Modal.getInstance(document.getElementById("addLandConclusionModal"));
        modal.hide();
        document.querySelector(".landConclusion_list").innerHTML = fragment;
        eventForLandConclusionPage();
    })
}
//////////////////////////////////////////////////////////////////////////////

function editConclusion(href) {
    fetch(href)
        .then(response => response.text()).then(fragment => {
        document.querySelector('#editLandConclusionModal').innerHTML = fragment
    })
        .then(() => {
            let modal = new bootstrap.Modal(document.querySelector("#editLandConclusionModal"), {});
            modal.show();
            document.getElementById("edit_land_conclusion").addEventListener('submit', event => submitEditConclusionForm(event))
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
        let modal = bootstrap.Modal.getInstance(document.getElementById('editLandConclusionModal'));
        modal.hide();
        document.querySelector(".landConclusion_list").innerHTML = fragment;
        eventForLandConclusionPage();
    });
}

function eventForLandConclusionPage() {
    document.querySelectorAll('.table .editLandBtn').forEach(editLandBtn => editLandBtn.addEventListener('click', function (event) {
        event.preventDefault()
        let href = this.getAttribute('href');
        editConclusion(href)
    }));
    document.querySelectorAll('.table .deleteLandBtn').forEach(deleteLandBtn => deleteLandBtn.addEventListener('click', function (event) {
        event.preventDefault()
        let href = this.getAttribute('href');
        document.querySelector('#deleteLandModalLabel .modal-footer a').setAttribute('href', href)
        let modal = new bootstrap.Modal(document.querySelector("#deleteLandModalLabel"), {});
        modal.show();
        document.getElementById('deleteLandConclusion').addEventListener('click', function (event) {
            event.preventDefault();
            fetch(href).then(response => response.text()).then(fragment => {
                document.querySelector(".landConclusion_list").innerHTML = fragment;
                modal.hide();
                eventForLandConclusionPage();
            })
        })
    }));
}

async function searchLandConclusion() {
    const path = "http://localhost:8080/conclusions"
    let searchWord = document.getElementById('searchLandText').value
    const param = new URLSearchParams({
        "searchLandText": searchWord
    });
    fetch(path + "/landConclusions/filter?" + param).then(response => response.text()).then(fragment => {
        document.querySelector(".landConclusion_list").innerHTML = fragment
        eventForLandConclusionPage();
    })
}
