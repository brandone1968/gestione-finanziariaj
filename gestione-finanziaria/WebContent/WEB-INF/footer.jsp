 <%@ page pageEncoding="UTF-8" %>
        <div id="footer" class="panel-footer">
            Copyright © 2018 - MBSoft Sas. Tutti i diritti riservati. P.IVA 11054670010
            <a href="https://github.com/brandone1968/gestione-finanziaria"> - Gestione Finanziaria</a> è sviluppato in Java.
        </div>

        <script type="text/javascript" src="inc/lib/jquery/jquery-3.3.1.min.js"></script> 
		<script type="text/javascript" src="inc/lib/jquery/jquery-ui.min.js"></script>
        <!-- JavaScript Boostrap plugin -->
        <script type="text/javascript" src="inc/lib/bootstrap/js/bootstrap.min.js"></script> 
        <!-- JavaScript Mio plugin -->
 		<script type="text/javascript" src="inc/lib/jquery/datepicker.js"></script> 
        <script type="text/javascript" src="inc/lib/jquery/paging.js"></script>
        <script src="inc/lib/bootstrap/js/bootstrap-datepicker.min.js"></script>
        <script src="inc/lib/bootstrap/js/bootstrap-datepicker.it.min.js"></script>
        
        <script type="text/javascript">
            $(document).ready(function (ev1) {
                $('#tableData').paging({limit: 12});
                
                $('#date1').datepicker({
                    clearBtn: true,
                    language: "it",
                    daysOfWeekHighlighted: "0",
                    calendarWeeks: true,
                    autoclose: true,
                    todayHighlight: true
                });
                
                $( "#date2" ).datepicker({
                    clearBtn: true,
                    language: "it",
                    daysOfWeekHighlighted: "0",
                    calendarWeeks: true,
                    autoclose: true,
                    todayHighlight: true
                });

                $('#ditta1').change(function(ev2){
                	  var ditta1 = $('#ditta1').val();
               	  
                	  $.post('AjaxDatiDitta',{idDitta:ditta1},function(responceText) {
                			$('#descrizioneDitta1').html(responceText);  
                	  });                	                  	  
                });
                
                $('#ditta2').change(function(ev2){
              	  var ditta1 = $('#ditta2').val();
             	  
              	  $.post('AjaxDatiDitta',{idDitta:ditta1},function(responceText) {
              			$('#descrizioneDitta2').html(responceText);  
              	  });                	                  	  
              });
            });
        </script>