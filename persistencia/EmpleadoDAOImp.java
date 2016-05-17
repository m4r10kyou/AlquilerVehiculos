package persistencia;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import persistencia.dto.EmpleadoDTO;
import excepciones.DAOExcepcion;


public class EmpleadoDAOImp implements IEmpleadoDAO {

	protected ConnectionManager connManager;

	public EmpleadoDAOImp() throws DAOExcepcion {
		super();
		try {
			connManager = new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e) {
			throw new DAOExcepcion(e);
		}
	}

	public EmpleadoDTO buscarEmpleado(String dni) throws DAOExcepcion {
		try {
			connManager.connect();
			ResultSet rs = connManager.queryDB("select * from EMPLEADO where DNI = '" + dni + "'");
			connManager.close();

			if (rs.next())
				return new EmpleadoDTO(
						rs.getString("DNI"),
						rs.getString("NOMBRE"),
						rs.getBoolean("ADMINISTRADOR"),
						rs.getInt("SUCURSAL"));
			else
				return null;
		}
		catch (SQLException e) { throw new DAOExcepcion(e);}
	}

	public List<EmpleadoDTO> obtenerEmpleadosPorSucursal(int idSucursal)
			throws DAOExcepcion {
		try {
			connManager.connect();
			ResultSet rs = connManager.queryDB("select * from EMPLEADO where SUCURSAL = " + idSucursal);
			connManager.close();

			List<EmpleadoDTO> listaEmpleadosDTO = new ArrayList<EmpleadoDTO>();

			try {
				while (rs.next()) {
					EmpleadoDTO empDTO = new EmpleadoDTO(
							rs.getString("DNI"),
							rs.getString("NOMBRE"),
							rs.getBoolean("ADMINISTRADOR"),
							rs.getInt("SUCURSAL"));

					listaEmpleadosDTO.add(empDTO);
				}
				return listaEmpleadosDTO;
			}
			catch (Exception e) { throw new DAOExcepcion(e); }
		}
		catch (SQLException e) { throw new DAOExcepcion(e); }
		catch (DAOExcepcion e) { throw e; }
	}

	public void crearEmpleado(EmpleadoDTO empleado) throws DAOExcepcion {
		try {
			connManager.connect();
			connManager.updateDB("INSERT INTO EMPLEADO VALUES ('"
					+ empleado.getDni() + "', '"
					+ empleado.getNombre() + "', "
					+ (empleado.isAdministrador() ? 1 : 0) + ", "
					+ empleado.getSucursal() + ")");
			connManager.close();
		}
		catch (SQLException e) { throw new DAOExcepcion(e); }
	}
}
