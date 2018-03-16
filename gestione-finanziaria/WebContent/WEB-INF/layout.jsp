<!-- Codice da inserire nel body -->

        
            <nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-target">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                        
	                        <c:choose>
	       						<c:when test="${active_page=='mbsoft'}">
	              					<li class="active altezza18">
	       						</c:when>
	       						<c:otherwise>
	              						<li class="altezza18">
	       						</c:otherwise>
	  						</c:choose>
							<a href="<c:url value="/mbsoft"/>">MBSoft</a></li>
	                        <c:choose>
	       						<c:when test="${active_page=='home'}">
	              					<li class="active">
	       						</c:when>
	       						<c:otherwise>
	              					<li>
	       						</c:otherwise>
	  						</c:choose>
								<a href="<c:url value="/home"/>">
	                        <i class="fa fa-home fa-lg" aria-hidden="true"></i>&nbsp</a></li>
 
 	                        <c:choose>
	       						<c:when test="${active_page=='fatture'}">
	              					<li class="active">
	       						</c:when>
	       						<c:otherwise>
	              					<li>
	       						</c:otherwise>
	  						</c:choose>
							<a href="<c:url value="/fatture"/>">Fatture</a></li>
     	                    <c:choose>
	       						<c:when test="${active_page=='imposte'}">
	              					<li class="active">
	       						</c:when>
	       						<c:otherwise>
	              					<li>
	       						</c:otherwise>
	  						</c:choose>
                             <a href="<c:url value="/imposte"/>">Imposte</a></li>
     	                    <c:choose>
	       						<c:when test="${active_page=='statistiche'}">
	              					<li class="active">
	       						</c:when>
	       						<c:otherwise>
	              					<li>
	       						</c:otherwise>
	  						</c:choose>
	                        <a href="<c:url value="/statistiche"/>">Statistiche</a></li>  
                       
                            <li class="dropdown">
                                 <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Anagrafiche <span class="caret"></span></a> 
                                <ul class="dropdown-menu">
                                    <li><a href="#">Clienti</a></li>
                                    <li><a href="#">IVA</a></li>
                                    <li><a href="#">Scadenze</a></li>
                                    <li><a href="#">Tributi</a></li>
                                    <li><a href="#">Scadenze</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div><!--/.nav-collapse -->

                </div><!-- /.container -->
            </nav>
		
