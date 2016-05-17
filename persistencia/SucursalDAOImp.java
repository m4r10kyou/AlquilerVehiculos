package persistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;

import persistencia.dto.SucursalDTO;
import excepciones.DAOExcepcion;

public class SucursalDAOImp implements ISucursalDAO{

	protected ConnectionManager connManager;

	public SucursalDAOImp()throws DAOExcepcion{
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){
			throw new DAOExcepcion(e);
			}
	}

	public List <SucursalDTO> obtenerSucursales() throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from SUCURSAL ");
			connManager.close();
			List<SucursalDTO> listaSucursalesDTO= new ArrayList<SucursalDTO>();

			while(rs.next()){
				SucursalDTO sucDTO = new SucursalDTO(
							rs.getInt("ID"),
							rs.getString("DIRECCION")
							);
				listaSucursalesDTO.add(sucDTO);
			}
			return listaSucursalesDTO;
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}
	}


}

