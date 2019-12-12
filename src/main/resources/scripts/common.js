$(".chzn-select").chosen();
if($("modal") != null)
    $('#modal').modal('show');

$('#navbarDropdown').click(function (e) {
    //if($("#catalog").css('display') == "none") {
    if($("#catalog").css('visibility') == "hidden") {
        //$("#catalog").show();
        $("#catalog").css("visibility", "visible");
        //$("#but-dropdown").addClass("active");
    }
    else {
        //$("#catalog").hide();
        $("#catalog").css("visibility", "hidden");
        //$("#catalog").css("display", "none");
       // $("#but-dropdown").removeClass("active");
    }
});