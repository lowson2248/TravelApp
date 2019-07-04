$(function () {

    var counter = 2;
  
    $(document).on('click', '#qadd', () => {
      counter++; 
      
      $('<input>', {
        class: 'email',
        id: 'member'+($('.inputList').length+1)
      }).appendTo($("<li>", {
        class: 'inputList',
        id: counter
      }).appendTo('#memberUl'));
      
      /*$('<select>', {
        name: 'authority',
        id: 'authority'+($('.inputList').length+1)
      }).appendTo($("#"+counter))*/
    
      $('<select>', {
    class: 'membRole',
    id: 'role'+counter,
    name: 'role'
    }).appendTo('#'+ counter);
    $('#role'+counter).append('<option value="master">管理者</option>');
    $('#role'+counter).append('<option value="sub">サブ</option>');
    $('#role'+counter).append('<option value="memb">ユーザー</option>');

    console.log($('.inputList').length);
      if ($('.inputList').length >= 3) {
        $('.micon').fadeIn();
      };
    });
  });

    //要素の削除
    $(document).on('click', '#qdel', () => {
      //最後の要素を削除
      $('#member'+$('.inputList').length).parent().remove();
      //選択肢が3つ未満の場合、削除ボタンを消す
      if ($('.inputList').length < 3) {
        $('.micon').fadeOut();
      };
    });
  
  
  