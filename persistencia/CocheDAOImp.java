package persistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;

import persistencia.dto.CocheDTO;
import excepciones.DAOExcepcion;

public class CocheDAOImp implements ICocheDAO{

	protected ConnectionManager connManager;

	public CocheDAOImp()throws DAOExcepcion{
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){
			throw new DAOExcepcion(e);
			}
	}

	public List <CocheDTO> obtenerCochesSucursal(int id) throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from COCHE WHERE SUCURSAL = "+id+"");
			connManager.close();
			List<CocheDTO> listaCocheDTO= new ArrayList<CocheDTO>();

			while(rs.next()){
				CocheDTO cocDTO = new CocheDTO(
							rs.getString("MATRICULA"),
							rs.getDouble("KMSACTUALES"),
							rs.getInt("SUCURSAL"),
							rs.getString("CATEGORIA"),
							rs.getString("NOMBRE")
							);
				listaCocheDTO.add(cocDTO);
			}

			return listaCocheDTO;
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}
	}


}

