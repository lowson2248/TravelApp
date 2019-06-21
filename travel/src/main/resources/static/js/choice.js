$(function(){
  $('#qadd').click(function(){
    $('#choice').append('<p id="choice"><input type="text" class="choice" name="question" /></p><br>');
  });
  $('#qdel').click(function(){
  $('#choice').empty();
  });
});
