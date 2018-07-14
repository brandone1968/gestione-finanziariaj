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
        <script type="text/javascript" src="inc/lib/jquery/jquery.validate.min.js"></script>
        <script type="text/javascript" src="inc/lib/jquery/jquery.formatCurrency-1.4.0.min.js"></script>
        <script type="text/javascript" src="inc/lib/jquery/jquery.formatCurrency.it-IT.js"></script> 
        <script type="text/javascript" src="inc/lib/jquery/jquery.number.min.js"></script>
        
        
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
            	
                $("#formFatturaAdd").validate({  
              	  rules: {
              		  numFattura: "required",
              		  descrizioneFattura: "required",
              		  dataFattura: "required",
                    },
              	  messages: {
              		  'numFattura': " Inserire il numero della fattura",
              		  'descrizioneFattura': " Inserire una descrizione della fattura",
              		  'dataFattura': " Inserire la data emissione della fattura",
              	  },

              	  submitHandler: function(form) { 
              	    form.submit();
              	    //alert('I dati sono stati inseriti correttamente');
              	  },

              	  invalidHandler: function() { 
              	    $("#popupAlert").modal('show');
              	  },      
              	});
            	
                $('#tableData').paging({limit: 12});
                
                $('#date1').datepicker({
                    clearBtn: false,
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
            	// per motivi di performance è meglio delegare il controllo ad un padre più vicino, in questo caso la tabella tableDettagli
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
                			"<td><span id='totaleDettaglio_" + numeroDettagli + "' name='totaleDettaglio_" + numeroDettagli + "' value='' class='totali'</span><br></td>");
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
                
               
                // funzione che fa accettare solo i numeri
                $('table#tableDettagli').on ("keydown", ".solo_numeri", function (e) {	

                    // allow function keys and decimal separators
                    if (
                        // backspace, delete, tab, escape, enter, comma and .
                        $.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 188, 190]) !== -1 ||
                        // Ctrl/cmd+A, Ctrl/cmd+C, Ctrl/cmd+X
                        ($.inArray(e.keyCode, [65, 67, 88]) !== -1 && (e.ctrlKey === true || e.metaKey === true)) ||
                        // home, end, left, right
                        (e.keyCode >= 35 && e.keyCode <= 39)) {
                 
                    	
                        /*
                        // optional: replace commas with dots in real-time (for en-US locals)
                        if (e.keyCode === 188) {
                            e.preventDefault();
                            $(this).val($(this).val() + ".");
                        }*/
                 
                        // optional: replace decimal points (num pad) and dots with commas in real-time (for EU locals)
                        if (e.keyCode === 110 || e.keyCode === 190) {
                            e.preventDefault();
                            $(this).val($(this).val() + ",");
                        }
                        

                        return;
                    }
                    // block any non-number
                    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                        e.preventDefault();
                    }
                    
                    
                	
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
						// Sostituisco il punto con la virgola
                     	importo = importo.replace(/,/,".");

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
                	
                	
                	$("#imponibile").text(imponibile);
                	$("#iva").html("<b>" + iva + "</b>");
                	$("#totaleFattura").text(totaleFattura);
                	
                 	$('.totali').toNumber().formatCurrency({ 
                 		region: 'it-IT' });
                }
                
            });
        </script>