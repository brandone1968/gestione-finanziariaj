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
            	
            	var numeroLoop = $('.conteggi').length / 3;
            	$('#numDettagli').val(numeroLoop);
            	if (numeroLoop>1) {
            	    $('#bottoneEliminaDettaglio').show();
            	} else {
            		$('#bottoneEliminaDettaglio').hide();
            	}
				
            	if (numeroLoop>4) {
            		$('#bottoneAggiungiDettaglio').hide();
            	} else {
            		$('#bottoneAggiungiDettaglio').show();
            	}
            	
            	aggiornaConteggi();
            	
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
                // Utilizzare eventi delegati invece di quelli diretti, altrimenti l'evento non sarà abilitato sulle righe aggiunte
                // --------------------------------------------------------------------------------------------------------------------------
                // $(".conteggi").on('change keyup paste', function () {   <-- evento diretto
            	// $(document).on ("change keyup paste", ".conteggi", function () { <-- evento delegato 
            	// Nel secondo caso la rilevazione dell'evento viene delegata a document che controllerà tutti i figli
            	// per motivi di performance è meglio delegare il cotrollo ad un padre più vicino, in questo caso la tabella tableDettagli
            	// --------------------------------------------------------------------------------------------------------------------------
            	$('table#tableDettagli').on ("change keyup paste", ".conteggi", function () {
            		aggiornaConteggi();
                });
                
                // Aggiunge dettaglio
                $("#bottoneAggiungiDettaglio").click(function(){
                	var numeroDettagli = $('.conteggi').length / 3;
                	numeroDettagli++;
                	$('#numDettagli').val(numeroDettagli);
                	if (numeroDettagli>1) {
                	    $('#bottoneEliminaDettaglio').show();
                	} else {
                		$('#bottoneEliminaDettaglio').hide();
                	}
					
                	if (numeroDettagli>4) {
                		$('#bottoneAggiungiDettaglio').hide();
                	} else {
                		$('#bottoneAggiungiDettaglio').show();
                	}
                	
//                 	$('#tableDettagli').append("<tr><td><input type='text' id='descrizioneDettaglio_'" + numeroDettagli + " name='descrizioneDettaglio_3' value='' size='60' maxlength='60' /> <br></td><td>more data</td></tr>");
                	$('.dettaglioFattura').last().after("<tr class='dettaglioFattura'>" + 
                			"<td><input type='text' id='descrizioneDettaglio_" + numeroDettagli + "' name='descrizioneDettaglio_" + numeroDettagli + "' value='' size='60' maxlength='60' /> <br></td>" +
                			"<td><input type='text' id='qta_" + numeroDettagli + "' name='qta_" + numeroDettagli + "' value='' size='3' maxlength='3' class='conteggi' /> <br></td>" +
                			"<td><select id='unitaMisuraQta_" + numeroDettagli + "' name='unitaMisuraQta_" + numeroDettagli + "' class='conteggi'><option value='0'>Giorni</option><option value='1'>Ore</option></td>" +
                			"<td><input type='text' id='importo_" + numeroDettagli + "' name='importo_" + numeroDettagli + "' value='' size='6' maxlength='6' class='conteggi' /> <br></td>" +
                			"<td><span id='totaleDettaglio_" + numeroDettagli + "' name='totaleDettaglio_" + numeroDettagli + "' value='' </span><br></td>");
                });
                
                // Elimina dettaglio
                $("#bottoneEliminaDettaglio").click(function(){
                	$('.dettaglioFattura').last().remove();
                	
                	var numeroLoop = $('.conteggi').length / 3;
                	$('#numDettagli').val(numeroLoop);
                	if (numeroLoop<2) {                		
                	    $('#bottoneEliminaDettaglio').hide();
                	} else {
                		$('#bottoneEliminaDettaglio').show();
                	}
                	if (numeroLoop<5) {
                		$('#bottoneAggiungiDettaglio').show();
                	} else {
                		$('#bottoneAggiungiDettaglio').hide();
                	}
                	
                	aggiornaConteggi();
                	
                });
                
                function aggiornaConteggi() {
                
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
                	
                }
                
            });
        </script>