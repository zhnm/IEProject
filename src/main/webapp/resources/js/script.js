(function($) {
    $(document).ready(function () { 
        
        $('.newsbox').hover(function(){
              if ( $('.text',this).is( ":hidden" ) ) {
                $('.text',this).slideDown(500);
              } else {
                $('.text',this).slideUp(500);
                }
        });

        
     var i=7;
     var j=1;
     $("#add_row").click(function(){
        $('#tab_logic').append('<tr id="addr'+(i)+'"></tr>');
      $('#addr'+i).html("<td><select class='bootstrap-select'><option value='submit' selected='selected'>ثبت</option><option value='wait'>انتظار</option><option value='delete'>حذف</option></select></td><td></td><td class='digit'><input type='text' class='' name='' id='' placeholder=''/></td><td class='digit'><input type='text' class='' name='' id=''  placeholder=''/><input type='text' class='' name='' id='' placeholder=''/><input type='text' class='' name='' id=''  placeholder=''/></td><td></td><td></td><td></td><td></td><td></td>");

      
      i++; 
  });
     $("#delete_row").click(function(){
         if(i>1){
         $("#addr"+(i-1)).html('');
         i--;
         }
     }); 
        
        $('.fa-plus').click(function(){
           $('#time').append('<div class="col-md-10 col-md-offset-2" id="t'+(j)+'"></div>'); 
           $('#t'+j).html("<select class='bootstrap-select'><option value='1' selected='selected'>شنبه</option><option value='2'> یکشنبه</option><option value='2'> دوشنبه</option><option value='2'> سه شنبه</option><option value='2'> چهارشنبه</option><option value='2'> پنج شنبه</option></select><input type='time' class='form-control' name='username' id='username' />");
            ++j;
            
        });
        
        $('.fa-minus').click(function(){
            console.log(j);
            if(j>1){
                console.log('hiiiii');
             $("#t"+(j-1)).html('');
             j--;
         } 
        });
        
    });     
})(jQuery);