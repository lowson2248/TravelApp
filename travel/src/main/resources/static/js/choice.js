$(function () {

<<<<<<< HEAD
  var counter = 2;
=======
  var counter = $('.inputList').length;
  if (counter < 3) {
      $('.micon').fadeOut();
    };
>>>>>>> 8a9f97cbfb64b213761d57c21650a6fe9ae81996

  $(document).on('click', '#qadd', () => {

    counter++;

    //要素の追加
    $('<input>', {
      type: 'text',
      class: 'choice',
<<<<<<< HEAD
      id: 'choice'+($('.inputList').length+1)
    }).appendTo($("<li>", {
      class: 'inputList',
      id: counter
=======
      name: "choiceList"
    }).appendTo($("<li>", {
      class: 'inputList'
>>>>>>> 8a9f97cbfb64b213761d57c21650a6fe9ae81996
    }).appendTo('#choiceUl'));

    //要素が3つ以上なら削除ボタンを表示
    if ($('.inputList').length >= 3) {
      $('.micon').fadeIn();
    };
  });
    
  //要素の削除
  $(document).on('click', '#qdel', () => {
    //最後の要素を削除
    $('#choice'+$('.inputList').length).parent().remove();
    //選択肢が3つ未満の場合、削除ボタンを消す
    if ($('.inputList').length < 3) {
      $('.micon').fadeOut();
    };
  });

});

