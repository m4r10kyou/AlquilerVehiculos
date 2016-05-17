package persistencia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import persistencia.dto.EntregaDTO;
import excepciones.DAOExcepcion;


public class EntregaDAOImp implements IEntregaDAO {

	protected ConnectionManager connManager;

	public EntregaDAOImp() throws DAOExcepcion {
		super();
		try {
			connManager = new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e) {
			throw new DAOExcepcion(e);
		}
	}

	public EntregaDTO buscarEntrega(int identificador) throws DAOExcepcion {
		try {
			connManager.connect();
			ResultSet rs = connManager.queryDB("select * from ENTREGA where ID = " + identificador);
			connManager.close();

			if (rs.next())
				return new EntregaDTO(
						rs.getInt("ID"),
						LocalDateTime.of(rs.getDate("FECHA").toLocalDate(), rs.getTime("FECHA").toLocalTime()),
						rs.getString("tipoSeguro").trim(),
						rs.getDouble("KMS"),
						rs.getDouble("COMBUSTIBLE"),
						rs.getString("COCHEASIGNADO"),
						rs.getString("EMPLEADOREALIZA"));
			else
				return null;
		}
		catch (SQLException e) { throw new DAOExcepcion(e);}
	}
	
	private List<EntregaDTO> crearListaDTO(ResultSet rs) throws Exception{
		List<EntregaDTO> listaEntregasDTO = new ArrayList<EntregaDTO>();
		
		while (rs.next()) {
			EntregaDTO entDTO = new EntregaDTO(
					rs.getInt("ID"),
					LocalDateTime.of(rs.getDate("FECHA").toLocalDate(), rs.getTime("FECHA").toLocalTime()),
					rs.getString("tipoSeguro").trim(),
					rs.getDouble("KMS"),
					rs.getDouble("COMBUSTIBLE"),
					rs.getString("COCHEASIGNADO"),
					rs.getString("EMPLEADOREALIZA"));

			listaEntregasDTO.add(entDTO);
		}
		return listaEntregasDTO;
	}

	public List<EntregaDTO> obtenerEntregas()
			throws DAOExcepcion {
		try {
			connManager.connect();
			ResultSet rs = connManager.queryDB("select * from ENTREGA");
			connManager.close();
			try {
				return crearListaDTO(rs);
			}
			catch (Exception e) { throw new DAOExcepcion(e); }
		}
		catch (SQLException e) { throw new DAOExcepcion(e); }
		catch (DAOExcepcion e) { throw e; }
	}
	
	public List<EntregaDTO> obtenerEntregasPorSucursal(int idSucursal)
			throws DAOExcepcion {
		try {
			connManager.connect();
			ResultSet rs = connManager.queryDB("select * from ENTREGA E where exists "
					+ "(select * from RESERVA R where R.ID = E.ID and R.SUCURSALRECOGIDA = " + idSucursal + ")");
			connManager.close();
			try {
				return crearListaDTO(rs);
			}
			catch (Exception e) { throw new DAOExcepcion(e); }
		}
		catch (SQLException e) { throw new DAOExcepcion(e); }
		catch (DAOExcepcion e) { throw e; }
	}

	public void crearEntrega(EntregaDTO entrega) throws DAOExcepcion {
		try {
			connManager.connect();
			connManager.updateDB("INSERT INTO ENTREGA VALUES (" +
					entrega.getId() + ", '" +
					Timestamp.valueOf(entrega.getFecha()) + "', '" +
					entrega.getTipoSeguro() + "', " +
					entrega.getKms() + ", " +
					entrega.getCombustible() + ", '" +
					entrega.getCocheAsignado() + "', '" +
					entrega.getEmpleadoRealiza() + "')");
			connManager.close();
		}
		catch (SQLException e) { throw new DAOExcepcion(e); }
	}
}
