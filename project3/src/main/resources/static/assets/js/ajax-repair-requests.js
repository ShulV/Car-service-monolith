const requestURL = 'http://localhost:8080/api/repair-request/get-all';

//отправить запрос общего вида
function sendRequest(method, url) {
    const headers = {
        'Content-Type': 'application/json'
    }
    return fetch(url, {
        method: method,
        headers: headers
    }).then(response => {return response.json()})
}


let repairReqList = document.getElementById('repair-req-list')
let repairReqBtn = document.getElementById('repair-req-btn')

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
        console.log(repairReq)
        let div = document.createElement('div');
        // let html = `<div>${repairReq.authorName}</div>`
        let html = `<div>${repairReq.authorName}</div>`
        div.insertAdjacentHTML('beforeend', html);
        repairReqList.appendChild(div);
    })
}

//добавить обработчик события для input'a поиска
repairReqBtn.addEventListener('click', (e) => {
    // const fullRequestUrl = requestURL;
    sendRequest('GET', requestURL)
        .then(data => {
            generateRepairRequestsContainers(data)

        })
        .catch(err => console.log(err))
})