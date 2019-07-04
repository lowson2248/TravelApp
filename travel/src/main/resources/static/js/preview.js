$(function(){
  $('#myfile').change(function(e) {
    var file = e.target.files[0];
    var reader = new FileReader();
    if(file.type.indexOf("image") < 0){
      alert("画像ファイルを指定してください。");
      return false;
    }
    //アップロードした画像をプレビュー表示する
    reader.onload = (function(file){
      return function(e){
        $("#pre-img").attr("src", e.target.result);
        $("#pre-img").attr("title", file.name);
      };
    })(file);
    reader.readAsDataURL(file);
  });
  //プレビュー画像のクリア
  $('.reset').click(function() {
    $('#pre-img').attr("src", 'images/noimage.png');
  });
});
