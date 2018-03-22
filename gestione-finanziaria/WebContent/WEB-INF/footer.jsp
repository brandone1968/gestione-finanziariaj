 <%@ page pageEncoding="UTF-8" %>
        <div id="footer" class="panel-footer">
            Copyright © 2018 - MBSoft Sas. Tutti i diritti riservati. P.IVA 11054670010
            <a href="https://github.com/brandone1968/gestione-finanziariaj"> - Gestione Finanziaria</a> è sviluppato in Java.
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
                
                // Se cambia una qualunque casella economica del dettaglio - classe conteggi
                $(".conteggi").on('change keyup paste', function () {
                	
                	var numeroLoop = $('.conteggi').length / 3;
                	
                	var imponibile =0;
                	var iva = 22;
                	var totaleFattura;
                	for (var i = 1; i <= numeroLoop; i++) {
                    	var qta = $("#qta_"+i).val();
                    	var unitaMisuraQta = $("#unitaMisuraQta_"+i).val();
                    	var importo = $("#importo_"+i).val();
                    	var risultato;
                    	if (unitaMisuraQta==0){
                    		risultato = qta * importo;
                    	}else{
                    		risultato = qta * importo / 8;
                    	}
//                     	$("#totaleDettaglio").text(qta);
                    	$("#totaleDettaglio_"+i).html("<b>" + risultato + "</b>");
                    	imponibile = imponibile + risultato;
                    	totaleFattura = imponibile + (imponibile * iva / 100);
                	}
                	$("#imponibile").html("<b>" + imponibile + "</b>");
                	$("#iva").html("<b>" + iva + "</b>");
                	$("#totaleFattura").html("<b>" + totaleFattura + "</b>");

                });
                
                // Aggiunge dettaglio
                $("#bottoneAggiungiDettaglio").click(function(){
                	alert("aggiungi dettaglio");
                	var numeroDettagli = $('.conteggi').length / 3;
                	$('#tableDettagli').append("<tr><td><input type='text' id='descrizioneDettaglio_'" + numeroDettagli + " name='descrizioneDettaglio_3' value='' size='60' maxlength='60' /> <br></td><td>more data</td></tr>");
                });
                
                
            });
        </script>