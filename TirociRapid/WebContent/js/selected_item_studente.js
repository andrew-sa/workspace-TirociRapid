function selectedItemStudente()
{
	var path = location.pathname;
	if(path.includes("tirocini_azienda"))
	{
		$("#item_lista_aziende_studente").attr("class","attiva");
		return;
	}
	if (path.includes("invia_richiesta"))
	{
		$("#item_richieste_studente").addClass("attiva");
		return;
	}
	if (path.includes("dati_azienda"))
	{
		$("#item_richieste_studente").addClass("attiva");
		return;
	}
	switch(path)
	{
		case "/TirociRapid/studente_aziende.jsp": 
		case "/TirociRapid/lista_aziende": 
			$("#item_lista_aziende_studente").attr("class","attiva");
			break;
			
		case "/TirociRapid/studente_profilo.jsp" :
		case "/TirociRapid/profilo_studente":
		case "/TirociRapid/modifica_curriculum":
		case "/TirociRapid/studente_curriculum.jsp":
			
			$("#item_profilo_studente").addClass("attiva");
			break;
			
		case "/TirociRapid/studente_richieste.jsp": 
		case "/TirociRapid/richieste": 
			
			$("#item_richieste_studente").addClass("attiva");
			break;
			
		case "/TirociRapid/studente_professori.jsp" :
		case "/TirociRapid/lista_professori":
			
			$("#item_lista_professori_studente").addClass("attiva");
			break;
			
			
		default:
			break;
	
	}
}