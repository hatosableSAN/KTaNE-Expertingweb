//formのinput submitボタンがクリックされた時二重送信を防ぐ
(function () {
    console.log("ボタン二重送信防止test!!");
    $(document).on("click", 'input[type="submit"]', function () {
        console.log("ボタン二重送信防止");
        $('form').find('input[type="submit"], button[type="submit"]').prop('disabled', 'true');
        $('form').find(':submit').val('送信中...');
        $('form').submit();
    });
    $(document).on("click", '#aho', function () {
        console.log("ボタン二重送信防止");
    });
})();