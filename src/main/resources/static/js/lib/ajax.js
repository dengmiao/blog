const ajax = (url, data, type='GET') => {
    return new Promise((resolve, reject) => {
        let promise
        if('GET' === type || 'get' === type) {
            promise = $.ajax({
                url: url,
                type: 'GET',
            })
        }
        else {
            promise = $.ajax({
                url: url,
                type: type,
                contentType: "application/json;charset=UTF-8",
                dataType: 'json',
                data: JSON.stringify(data),
            })
        }
        promise.then(response => resolve(response)).catch(error => {
            window.layer.alert(`请求失败 [${error && error.responseJSON && error.responseJSON.message 
                ? error.responseJSON.message : null}]`)
        })
    })
}