//ICocheDAO
package persistencia;

import java.util.List;

import persistencia.dto.CocheDTO;
import excepciones.*;

public interface ICocheDAO {
 public List <CocheDTO> obtenerCochesSucursal(int id) throws DAOExcepcion;
}
