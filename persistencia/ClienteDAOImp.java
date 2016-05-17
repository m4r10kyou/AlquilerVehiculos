
package persistencia;

import java.sql.*;
import java.time.LocalDateTime;

import org.hsqldb.types.DateTimeType;

import persistencia.dto.ClienteDTO;
import excepciones.DAOExcepcion;

public class ClienteDAOImp implements IClienteDAO{

	protected ConnectionManager connManager;

	public ClienteDAOImp()throws DAOExcepcion{
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){
			throw new DAOExcepcion(e);
			}
	}

	public ClienteDTO buscarCliente(String dni)throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from CLIENTE where DNI= '"+dni+"'");
			connManager.close();

			if (rs.next())
				return new ClienteDTO(
							rs.getString("DNI"),
							rs.getString("NOMBREAPELLIDOS"),
							rs.getString("DIRECCION"),
							rs.getString("POBLACION"),
							rs.getString("CODPOSTAL"),
							rs.getDate("FECHACARNETCONDUCIR").toLocalDate(),
							rs.getString("DIGITOSTC"),
							rs.getInt("MESTC"),
							rs.getInt("AÑOTC"),
							rs.getInt("CVCTC"),
							rs.getString("TIPOTC"));
			else
				return null;
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}
	}

	 public void crearCliente(ClienteDTO cliente) throws DAOExcepcion{
		 try{

				connManager.connect();
				Timestamp fecha=Timestamp.valueOf(cliente.getFechaCanetConducir().atStartOfDay());

				connManager.updateDB("INSERT INTO CLIENTE VALUES ('"
				+cliente.getDni()+"','"+
				cliente.getNombreyApellidos()+"','"+
				cliente.getDireccion()+"','"+
				cliente.getPoblacion()+"','"+
				cliente.getCodPostal()+"','"+
				fecha+"','"+
				cliente.getDigitosTC()+"','"+
				cliente.getMesTC()+"','"+
				cliente.getAnyoTC()+"','"+
				cliente.getCvcTC()+"','"+
				cliente.getTipoTC()
				+"')");

				connManager.close();
				}
				catch (SQLException e){	throw new DAOExcepcion(e);}
	 }

	public ClienteDTO buscarClientePorReserva(int id) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from CLIENTE C where exists"
					+ "(select * from RESERVA R where R.CLIENTEREALIZA = C.DNI and R.ID = " + id + ")");
			connManager.close();

			if (rs.next())
				return new ClienteDTO(
							rs.getString("DNI"),
							rs.getString("NOMBREAPELLIDOS"),
							rs.getString("DIRECCION"),
							rs.getString("POBLACION"),
							rs.getString("CODPOSTAL"),
							rs.getDate("FECHACARNETCONDUCIR").toLocalDate(),
							rs.getString("DIGITOSTC"),
							rs.getInt("MESTC"),
							rs.getInt("AÑOTC"),
							rs.getInt("CVCTC"),
							rs.getString("TIPOTC"));
			else
				return null;
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}
	}


}

