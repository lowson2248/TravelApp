$(function(){
var now = new Date();
var y = now.getFullYear();
var m = now.getMonth() + 1;
var d = now.getDate();
var w = now.getDay();
var wd = ['日', '月', '火', '水', '木', '金', '土'];
$('.today').text(y + '年' + m + '月' + d + '日' + '(' + wd[w] + ')');
});