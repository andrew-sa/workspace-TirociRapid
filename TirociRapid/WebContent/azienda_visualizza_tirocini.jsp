<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.tirocirapid.classes.model.Tirocinio"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Visualizza tirocini</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/messaggi.css">

<script type="text/javascript" src="js/formcheck.js"></script>
<script type="text/javascript" src="js/selected_item_azienda.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/messaggi.js"></script>

</head>
<body onload="selectedItemAzienda(); nascondiMessaggiTop();">

<%@ include file="slider.jsp" %>

	<%@ include file="nav_azienda.jsp" %>
	
		<div class="container">
		<%
		if (request.getAttribute("errore") != null)
		{
		%>
			<h1 class="erroreTop"><%= request.getAttribute("errore") %></h1>
		<%
		}
		else if (request.getAttribute("successo") != null)
		{
		%>
			<h1 class="successoTop"><%= request.getAttribute("successo") %></h1>
		<%
		}
		%>
	
		<%
			if (request.getAttribute("tirocini") != null) 
			{
				ArrayList<Tirocinio> tirocini = (ArrayList<Tirocinio>) request.getAttribute("tirocini");
				for (Tirocinio t : tirocini) 
				{
					  
                      String statoAttuale = t.getStato();
                      switch (statoAttuale)
                      {
          					case "TirProp":
          						statoAttuale = "Necessita di conferma di un responsabile delle approvazioni ";
          						break;
          					case "TirConf":
          						statoAttuale = "Confermato dal responsabile delle approvazioni e che pu&ograve; essere scelto dagli studenti"; 
          						break;
          					case "TirRif":
          						statoAttuale = "Rifiutato dal Responsabile Approvazioni, in attesa di rimozione dal Responsabile dell'azienda";
          						break;
          					case "TirNDisp":
          						statoAttuale = "Eliminato dal responsabile dell'azienda, che resta visibile per gli studenti che hanno gi� avviato questo tirocinio";
          						break;
          					default:
          						break;
                      }
                      
		%>
		<div class="col-sm-12">

			<div class="bs-calltoaction bs-calltoaction-default">
				<div class="row">
					<div class="col-md-9 cta-contents">
						<h1 class="cta-title"><%=t.getNome() %></h1>
						<div class="cta-desc">
							<p>
								<span class="parametri-azienda">Stato:</span> <%=statoAttuale %>
							</p>
							<p>
								<span class="parametri-azienda">Descrizione:</span> <%=t.getDescrizione() %>
							</p>
							<p>
								<span class="parametri-azienda">Offerta Formativa:</span> <%=t.getOffertaFormativa() %>
							</p>
						</div>
					</div>
					<div class="col-md-3 cta-button">
						<a href="elimina_tirocinio?nome=<%=t.getNome()%>" class="btn btn-lg btn-block btn-default">Elimina</a>
					</div>
				</div>
			</div>


		</div>
		
		<%
			} //FINE FOR

			} // FINE IF
			
			
		%>
		</div>
	<%@include file="footer.jsp" %>
</body>
</html>