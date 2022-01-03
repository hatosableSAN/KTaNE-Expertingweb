$(document).on("click", ".add", function() {
    $('DVI-D:<input type="text" name="name" size="3">Parallel:<input type="text" name="name" size="3">PS/2:<input type="text" name="name" size="3">RJ-45:<input type="text" name="name" size="3">Serial:<input type="text" name="name" size="3">Stereo RCA:<input type="text" name="name" size="3"><input type="button" value="＋" class="add pluralBtn"><input type="button" value="－" class="del pluralBtn">').insertAfter($(this).parent());
});
$(document).on("click", ".del", function() {
    var target = $(this).parent();
    if (target.parent().children().length > 1) {
        target.remove();
    }
});

