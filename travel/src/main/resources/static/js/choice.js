$(function () {
  var counter = $('.inputList').length;
  if ($('.inputList').length < 3) {
      $('.micon').fadeOut();
    };
  $(document).on('click', '#qadd', () => {
counter++;

//要素の追加
$('<input>', {
  type: 'text',
  class: 'choice',
  name: "choice"
}).appendTo($("<li>", {
  class: 'inputList'
}).appendTo('#choiceUl'));

//要素が3つ以上なら削除ボタンを表示
if ($('.inputList').length >= 3) {
  $('.micon').fadeIn();
};
  });
  //要素の削除
  $(document).on('click', '#qdel', () => {
    //最後の要素を削除
    $('#choiceUl li').last().remove();
    //選択肢が3つ未満の場合、削除ボタンを消す
    if ($('.inputList').length < 3) {
      $('.micon').fadeOut();
    };
  });
});