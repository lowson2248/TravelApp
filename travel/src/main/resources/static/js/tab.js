$(function() {
  $(".btn a").click(function() {
    $(this).parent().addClass("active").siblings(".active").removeClass("active");
    var tab = $(this).attr("href");
    $(tab).addClass("active").siblings(".active").removeClass("active");
    return false;
  });
});
