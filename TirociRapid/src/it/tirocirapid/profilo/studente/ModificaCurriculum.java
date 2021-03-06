package it.tirocirapid.profilo.studente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import it.tirocirapid.classes.manager.AbstractCurriculumManager;
import it.tirocirapid.classes.model.Curriculum;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa della gestione della modfica dei dati di un curriculum
 */
@WebServlet("/modifica_curriculum")
public class ModificaCurriculum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaCurriculum() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException
	{
		parametri = new ArrayList<>();
//		parametri.add("username");
		parametri.add("fax");
		parametri.add("capacitaCompetenzeRelazionali");
		parametri.add("capacitaCompetenzeTecniche");
		parametri.add("capacitaCompetenzeArtistiche");
		parametri.add("capacitaCompetenzePersonali");
		parametri.add("capacitaCompetenzeOrganizzative");
		parametri.add("altreCapacitaCompetenze");
		parametri.add("esperienzaLavorativa");
		parametri.add("madrelingua");
		parametri.add("altreLingue");
		parametri.add("patenti");
		parametri.add("ulterioriInformazioni");
		
		patenti = new ArrayList<>();
		patenti.add("AM");
		patenti.add("A1");
		patenti.add("A2");
		patenti.add("A");
		patenti.add("B1");
		patenti.add("B");
		patenti.add("C1");
		patenti.add("C");
		patenti.add("D1");
		patenti.add("D");
		patenti.add("BE");
		patenti.add("C1E");
		patenti.add("CE");
		patenti.add("D1E");
		patenti.add("DE");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String pathHome = request.getServletContext().getContextPath() + "/index.jsp";
		response.getWriter().println("<h1>Il servizio richiesto non &egrave; disponibile</h1>");
		response.getWriter().println("<a href=\""+ pathHome + "\"><button>Torna alla Home</button></a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		final String replacement = "";
		UserLoggato user = (UserLoggato) request.getSession().getAttribute("user");
		String username = user.getId();
		try
		{
			AbstractManagerFactory factory = new DAOFactory();
			AbstractCurriculumManager managerCurriculum = factory.createCurriculumManager();
			Curriculum curriculum = managerCurriculum.read(username);
			for (String param: parametri)
			{
				if (!param.equals(parametri.get(0)) && replaceIfMissing(request.getParameter(param), replacement).equals(replacement))
				{
					System.out.println(param);
					request.setAttribute("errore", "Il campo " + param + " &egrave; obbligatorio");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //ModificaCurriculum
					dispatcher.forward(request, response);
					return;
				}
				
				/* Validazione parametro fissato dal ciclo */
				if (param.equals(parametri.get(0))) //fax
				{
					if (validaFax(request.getParameter(param)))
					{
						curriculum.setFax(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " deve contenere 11 cifre");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(1))) //capacitaCompetenzeRelazionali
				{
					if (validaCapacitaCompetenzeX(request.getParameter(param)))
					{
						curriculum.setCapacitaCompetenzeRelazionali(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(2))) //capacitaCompetenzeTecniche
				{
					if (validaCapacitaCompetenzeX(request.getParameter(param)))
					{
						curriculum.setCapacitaCompetenzeTecniche(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(3))) //capacitaCompetenzeArtistiche
				{
					if (validaCapacitaCompetenzeX(request.getParameter(param)))
					{
						curriculum.setCapacitaCompetenzeArtistiche(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(4))) //capacitaCompetenzePersonali
				{
					if (validaCapacitaCompetenzeX(request.getParameter(param)))
					{
						curriculum.setCapacitaCompetenzePersonali(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(5))) //capacitaCompetenzeOrganizzative
				{
					if (validaCapacitaCompetenzeX(request.getParameter(param)))
					{
						curriculum.setCapacitaCompetenzeOrganizzative(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(6))) //altreCapacitaCompetenze
				{
					if (validaCapacitaCompetenzeX(request.getParameter(param)))
					{
						curriculum.setAltreCapacitaCompetenze(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(7))) //esperienzaLavorativa
				{
					if (validaEsperienzaLavorativa(request.getParameter(param)))
					{
						curriculum.setEsperienzaLavorativa(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(8))) //madrelingua
				{
					if (validaMadrelingua(request.getParameter(param)))
					{
						curriculum.setMadrelingua(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 20 caratteri");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(9))) //altreLingue
				{
					if (validaAltreLingue(request.getParameter(param)))
					{
						curriculum.setAltreLingue(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Le lingue del campo " + param + " devono essere superate da una virgola e il contenuto non deve superare i 100 caratteri");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(10))) //patenti
				{
					if (validaPatenti(request.getParameter(param).toUpperCase()))
					{
						curriculum.setPatenti(removeLastToken(request.getParameter(param).toUpperCase()));
					
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non &egrave; nel formato corretto (Le sigle delle patenti devono essere separate dalla virgola)");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(11))) //ulterioriInformazioni
				{
					if (validaUlterioriInformazioni(request.getParameter(param)))
					{
						curriculum.setUlterioriInformazioni(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
						dispatcher.forward(request, response);
						return;
					}
				}
				else //Non si verifica mai
				{
					request.setAttribute("errore", "Si &egrave; verificato un errore, si prega di riprovare");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_curriculum.jsp"); //CreaCurriculum
					dispatcher.forward(request, response);
					return;
				}
			} //fine ciclo for
			try
			{
				managerCurriculum.update(curriculum, username);;
				request.setAttribute("successo", "Il curriculum &egrave; stato modificato con successo");
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/profilo_studente"); //ProfiloStudente
				dispatcher.forward(request, response);
			}
			catch (MySQLIntegrityConstraintViolationException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "L'username dello studente immesso non &egrave; presente nel database");
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/"); //ModificaCurriculum
				dispatcher.forward(request, response);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/"); //ModificaCurriculum
				dispatcher.forward(request, response);
			}
			catch (InsertFailedException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", e.getMessage());
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/"); //ModificaCurriculum
				dispatcher.forward(request, response);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/"); //ModificaCurriculum
			dispatcher.forward(request, response);
		}
		catch (TuplaNotFoundException e)
		{
			e.printStackTrace();
			request.setAttribute("errore", e.getMessage());
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/"); //ModificaCurriculum
			dispatcher.forward(request, response);
		}
	}
	
	/**
	 * 
	 * @param fax Il fax da validare
	 * @return true se il fax rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaFax(String fax)
	{
		if (fax.trim().equals("") || fax == null)
		{
			return true;
		}
		else
		{
			return Pattern.matches("[0-9]{11}", fax);
		}
	}
	
	/**
	 * 
	 * @param capacitaCompetenzeX Le cpaicitaCompetenze da validare
	 * @return true se capacitaCompetenzeX rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaCapacitaCompetenzeX(String capacitaCompetenzeX)
	{
		return (capacitaCompetenzeX.length() <= 200);
	}
	
	/**
	 * 
	 * @param esperienzaLavorativa L'esperienzaLavorativa da validare
	 * @return true se esperienzaLavorativa rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaEsperienzaLavorativa(String esperienzaLavorativa)
	{
		return (esperienzaLavorativa.length() <= 200);
	}
	
	private boolean validaPatenti(String strPatenti)
	{
		String[] p = strPatenti.trim().split(TOKEN);
		for (int i = 0; i < p.length; i++)
		{
			if (!patenti.contains(p[i]))
			{
				return false;
			}
		}
		return true;
	}
	
	private boolean validaMadrelingua(String madrelingua)
	{
		return (Pattern.matches("[A-Za-z]{2,20}", madrelingua) && (madrelingua.length() <= 20));
	}
	
	private boolean validaAltreLingue(String altreLingue)
	{
		if (!altreLingue.trim().endsWith(TOKEN))
		{
			altreLingue = altreLingue.concat(TOKEN);
		}
		return Pattern.matches("(([A-Za-z\\s]{2,20},)+)", altreLingue) && (altreLingue.length() <= 200);
	}
	
	/**
	 * 
	 * @param ulterioriInformazioni Le ulterioriInformazioni da validare
	 * @return true se le ulterioriInformazioni rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaUlterioriInformazioni(String ulterioriInformazioni)
	{
		return (ulterioriInformazioni.length() <= 200);
	}
	
	private String removeLastToken(String str)
	{
		str = str.trim();
		if (str.endsWith(TOKEN))
		{
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	/**
	 * 
	 * @param orig la stringa da controllare
	 * @param replacement la stringa da restituire nel caso orig fosse vuota o nulla  
	 * @return orig se orig non � ne nulla e ne vuota
	 * @return replacement se orig � nulla o vuota
	 */
	private String replaceIfMissing(String orig, String replacement)
	{
		if (orig == null || orig.trim().equals(""))
			return replacement;
		else
			return orig;
	}
	
	private static ArrayList<String> parametri;
	private static ArrayList<String> patenti;
	private static final String TOKEN = ",";
	
}
