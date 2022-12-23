const requestUrlGettingAll = 'http://localhost:8080/api/repair-request/get-all';
const requestUrlAcceptRequest = 'http://localhost:8080/api/repair-request/accept-request/';

//отправить запрос общего вида
function sendRequest(method, url) {
    const headers = {
        'Content-Type': 'application/json'
    }
    return fetch(url, {
        method: method,
        headers: headers
    }).then(response => {
        if(method === "GET") {
            return response.json();
        }
        else return response;
    })
}


let repairReqList = document.getElementById('repair-req-list')
// let repairReqBtn = document.getElementById('repair-req-btn')

//удалить потомков блока
function removeChildren(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

//обновить спискок заявок путем динамического добавления блоков
function generateRepairRequestsContainers(repairRequests) {
    removeChildren(repairReqList)
    repairRequests.forEach(repairReq => {
        let div = document.createElement('div');
        let isAcceptedClass = "";
        if (repairReq.accepted) {
            isAcceptedClass = "is-accepted";
        }
        let html = `<div class="row repair-request-row repair-request-row-data">
                <div class="col-sm">${repairReq.authorName}</div>
                <div class="col-sm">${repairReq.phone}</div>
                <div class="col-sm">${repairReq.serviceName}</div>
                <div class="col-sm">${repairReq.serviceTypeName}</div>
                <div class="col-sm">${repairReq.dateTimeWork}</div>
                <div class="col-sm">${repairReq.dateRequest}</div>
                <div class="col-sm "><div class="repair-request-accepted-bg ${isAcceptedClass}"></div></div>
                <button class="col-sm btn btn-primary accept-request-btn" type="submit" data-id="${repairReq.id}">Принять</button>
            </div>`
        div.insertAdjacentHTML('beforeend', html);
        repairReqList.appendChild(div);
    })
    //
            //массив кнопок "принять"
            let acceptRequestButtons = document.querySelectorAll(".accept-request-btn");
            //создание обработчиков для кнопок
            acceptRequestButtons.forEach(btn => {
                btn.addEventListener("click", (e) => {
                    sendRequest('POST', `${requestUrlAcceptRequest}${e.target.dataset.id}`)
                        .then(() => {
                            sendRequest('GET', requestUrlGettingAll)
                                .then(data => {
                                    generateRepairRequestsContainers(data)
                                })
                        })
                        .catch(err => console.log(err))
                    //----------
                    sendRequest('GET', requestUrlGettingAll)
                        .then(data => {
                            generateRepairRequestsContainers(data)
                        })
                    //---------
                })
            })
}

// //добавить обработчик события
// repairReqBtn.addEventListener('click', (e) => {
//     sendRequest('GET', requestUrlGettingAll)
//         .then(data => {
//             generateRepairRequestsContainers(data)
//         })
//         .catch(err => console.log(err))
// })

//обработчик загрузки списка при загрузке страницы
document.addEventListener('DOMContentLoaded', (e) => {
    sendRequest('GET', requestUrlGettingAll)
        .then(data => {
            generateRepairRequestsContainers(data)
        })
        .catch(err => console.log(err))
})


