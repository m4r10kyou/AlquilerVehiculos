//IReservaDAO
package persistencia;

import java.util.List;

import persistencia.dto.EmpleadoDTO;
import excepciones.*;

public interface IEmpleadoDAO {
 public EmpleadoDTO buscarEmpleado(String dni) throws DAOExcepcion;

 public List <EmpleadoDTO> obtenerEmpleadosPorSucursal(int idSucursal) throws DAOExcepcion;

 public void crearEmpleado(EmpleadoDTO empleado) throws DAOExcepcion;
}
