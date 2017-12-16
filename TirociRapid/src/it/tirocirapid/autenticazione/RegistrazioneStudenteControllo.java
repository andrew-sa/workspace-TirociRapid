package it.tirocirapid.autenticazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa della registrazione dello studente
 */
@WebServlet("/registrazione_studente")
public class RegistrazioneStudenteControllo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneStudenteControllo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @param password rappresenta l'password dello studente
	 * @param username rappresenta l'username dello studente 
	 * @return true se str � vouta 
	 * @return false se str � piena
	 */
	private boolean isStudente(String username, String password)
	{
		return false;
	}

	/**
	 * 
	 * @param str rappresenta il contenuto del campo 
	 * @return true se str � vouta 
	 * @return false se str � piena
	 */
	private boolean isEmptyField(String str)
	{
		return false;
	}
}
