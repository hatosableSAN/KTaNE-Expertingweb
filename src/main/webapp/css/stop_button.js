//formのinput submitボタンがクリックされた時二重送信を防ぐ
(function () {
    console.log("ボタン二重送信防止テスト");
    $(document).on("click", 'input[type="submit"]', function (event) {
        console.log("ボタン二重送信防止");
        $(event.target).val('処理中...');
        $('form').find('input[type="submit"], button[type="submit"]').prop('disabled', 'true');
        $('button').prop('disabled', 'true');
        $(event.target).parents("form").submit();
    });
})();