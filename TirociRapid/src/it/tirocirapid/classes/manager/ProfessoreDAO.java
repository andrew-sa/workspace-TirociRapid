package it.tirocirapid.classes.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Professore dal DB.
 */
public class ProfessoreDAO extends AbstractProfessoreManager {

	/**
	 * Verifica se un professore con quel username e password � presente nel DB
	 * @param username rappresenta l'username del professore da cercare
	 * @param password rappresenta la password del professore da cercare
	 * @return true se esiste un professore avente l'username e la password passati come parametri nel DB
	 * @return false se esiste un professore avente l'username passato come parametro, ma la password non corrisponde
	 * @throws TuplaNotFoundException viene lanciata se non esiste un professore avente l'username passato come parametro sul DB
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public int search(String username, String password) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(SEARCH);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			if (password.equals(rs.getString(2)))
			{
				if ("ResponsabileApprovazioni".equals(rs.getString(3)))
				{
					con.commit();
					rs.close();
					ps.close();
					DriverManagerConnectionPool.getIstance().releaseConnection(con);
					return 2;
				}
				else
				{
					con.commit();
					rs.close();
					ps.close();
					DriverManagerConnectionPool.getIstance().releaseConnection(con);
					return 1;
				}
			}
			else
			{
				con.commit();
				rs.close();
				ps.close();
				DriverManagerConnectionPool.getIstance().releaseConnection(con);
				return 0;
			}
		}
		else
		{
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException();
		}
	}

	/**
	 * Cerca un professore nel DB tramite l'username
	 * @param username rappresenta l'username del professore da cercare
	 * @return Professore il professore cercato
	 * @throws TuplaNotFoundException viene lanciata se il professore non � presente all'interno del DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public Professore read(String username) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			Professore professore = new Professore();
			professore.setUsername(username);
			professore.setNome(rs.getString(1));
			professore.setCognome(rs.getString(2));
			professore.setEmailIstituzionale(rs.getString(3));
			professore.setTelefono(rs.getString(4));
			professore.setAmbito(rs.getString(5));
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			return professore;
		}
		else
		{
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException("Il professore selezionato non &egrave; presente nel database");
		}
	}

	/**
	 * Cerca tutti i professori presenti nel DB
	 * @return ArrayList<Professore> rappresentano tutti i professori 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public ArrayList<Professore> readAll() throws SQLException
	{
		ArrayList<Professore> professori = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(READ_ALL);
		while (rs.next())
		{
			Professore professore = new Professore();
			professore.setNome(rs.getString(1));
			professore.setCognome(rs.getString(2));
			professore.setEmailIstituzionale(rs.getString(3));
			professore.setTelefono(rs.getString(4));
			professore.setAmbito(rs.getString(5));
			professore.setEmail(rs.getString(6));
			professore.setUsername(rs.getString(7));
			professori.add(professore);
		}
		con.commit();
		rs.close();
		stm.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return professori;
	}

	/**
	 * Cerca nel DB l'email di un professore tramite l'username 
	 * @param username l'username del professore di cui vogliamo sapere l'email
	 * @return String rappresenta l'email del professore con quel username
	 * @throws TuplaNotFoundException viene lanciata se l'azienda con la partita IVA specificata non � presente all'interno del DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public String readEmail(String username) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_EMAIL);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			String email = rs.getString(1);
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			return email;
		}
		else
		{
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException("Il professore selezionato non &egrave; presente nel database");
		}
	}
	
	private static final String SEARCH = "SELECT Username, Pass, Tipo FROM professore WHERE Username = ?";
	private static final String READ = "SELECT Nome, Cognome, EmailIstituzionale, Telefono, Ambito FROM professore WHERE Username = ?";
	private static final String READ_ALL = "SELECT Nome, Cognome, EmailIstituzionale, Telefono, Ambito, Email, Username FROM professore";
	private static final String READ_EMAIL = "SELECT EmailIstituzionale FROM professore WHERE Username = ?";
}
