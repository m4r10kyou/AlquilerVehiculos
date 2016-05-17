//IEntregaDAO
package persistencia;

import java.util.List;

import persistencia.dto.EntregaDTO;
import excepciones.*;

public interface IEntregaDAO {
 public EntregaDTO buscarEntrega(int id)throws DAOExcepcion;
 
 public void crearEntrega(EntregaDTO entrega) throws DAOExcepcion;

 public List <EntregaDTO> obtenerEntregas() throws DAOExcepcion;
 
 public List <EntregaDTO> obtenerEntregasPorSucursal(int idSucursal) throws DAOExcepcion;
}
