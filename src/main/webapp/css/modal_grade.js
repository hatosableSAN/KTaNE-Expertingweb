
$(function () {
    //alert("test");
    $(document).on('click', '.seat',function () {
        var seatid= $(this).attr('id');
        var attendance= $(this).attr('attendance');
        var index = $('.seatall').index(this);//前から順番に0.1.2...と数字がつけられる
        window.sessionStorage.clear();
        window.sessionStorage.setItem('Selected', index);
        window.sessionStorage.setItem('SeatNum', seatid);
        window.sessionStorage.setItem('attendance', attendance);
        if(attendance==1){
        $('#attendance').text("出席");
        }else{
         $('#attendance').text("欠席");  
        }

        var id=window.sessionStorage.getItem("Selected");
        $('#seatnum').val(id);//hiddenパラメータを座席のidに置き換え

        //alert(index);
        //alert("クリックされました");
        $(this.form).find("textarea, :text, select, radio, checkbox").val("").end().find(":checked").prop("checked", false);
        // $('textarea').val(index);
        
        $(this).blur();	//ボタンからフォーカスを外す
        if ($("#modal-overlay")[0]) return false;		//新しくモーダルウィンドウを起動しない (防止策1)
        //if($("#modal-overlay")[0]) $("#modal-overlay").remove() ;		//現在のモーダルウィンドウを削除して新しく起動する (防止策2)

        //オーバーレイを出現させる
        $("body").append('<div id="modal-overlay"></div>');
        $("#modal-overlay").fadeIn("slow");
        

        //コンテンツをセンタリングする
        centeringModalSyncer();

        //コンテンツをフェードインする
        $("#modal-content-grade").fadeIn("slow");

        //[#modal-overlay]、または[#modal-close]をクリックしたら…
        $("#modal-overlay,#modal-close").unbind().click(function () {

            //[#modal-content-grade]と[#modal-overlay]をフェードアウトした後に…
            $("#modal-content-grade,#modal-overlay").fadeOut("slow", function () {

                //[#modal-overlay]を削除する
                $('#modal-overlay').remove();

            });

        });
    });
    //リサイズされたら、センタリングをする関数[centeringModalSyncer()]を実行する

    function centeringModalSyncer() {

        //画面(ウィンドウ)の幅、高さを取得
        var w = $(window).width();
        var h = $(window).height();

        // コンテンツ(#modal-content-grade)の幅、高さを取得
        // jQueryのバージョンによっては、引数[{margin:true}]を指定した時、不具合を起こします。
        //		var cw = $( "#modal-content-grade" ).outerWidth( {margin:true} );
        //		var ch = $( "#modal-content-grade" ).outerHeight( {margin:true} );
        var cw = $("#modal-content-grade").outerWidth();
        var ch = $("#modal-content-grade").outerHeight();

        //センタリングを実行する
        $("#modal-content-grade").css({ "left": ((w - cw) / 2) + "px", "top": ((h - ch) / 2) + "px" });

    }
    $("#modal-open").click(
        function () {
            //[id:modal-open]をクリックしたら起こる処理
            alert("クリックされました");
        }
    );
});