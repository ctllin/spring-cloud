function download() {
    var url = 'http://192.168.6.27/export';
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);    // 也可以使用POST方式，根据接口
    xhr.responseType = "blob";  // 返回类型blob
    //定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
    xhr.onload = function () {
        // 请求完成
        if (this.status === 200) {
            var firstContentLength = xhr.getResponseHeader('firstContentLength');
            console.log("firstContentLength:" + firstContentLength);
            var filaname = xhr.getResponseHeader('filename');
            console.log("filaname:" + filaname);
            var secondFileLength = xhr.getResponseHeader('secondFileLength');
            console.log("secondFileLength:" + secondFileLength);

            var blob = this.response;
            var newblob = blob.slice(firstContentLength);
            var reader = new FileReader();
            reader.readAsDataURL(newblob);  // 转换为base64，可以直接放入a表情href
            reader.onload = function (e) {
                // 转换完成，创建一个a标签用于下载
                var a = document.createElement('a');
                a.download = filaname;
                a.href = window.URL.createObjectURL(blob);
                a.target = "_blank";
                $("body").append(a);  // 修复firefox中无法触发click
                a.click();
                $(a).remove();
            }
        } else if (xhr.status == 1000) {
            console.log(this.response + " " + this.statusText);
        } else if (xhr.status == 1001) {
            console.log(this.response + " " + this.statusText);
        } else {
            console.log(this.response + " " + this.statusText);
        }

    };
    // 发送ajax请求
    xhr.send();
}
