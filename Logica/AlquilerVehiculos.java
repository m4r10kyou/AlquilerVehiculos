package Logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excepciones.DAOExcepcion;
import persistencia.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import persistencia.dto.*;


public class AlquilerVehiculos {

    /**
     * @param args the command line arguments
     */
    private Map<Integer, Sucursal> sucursales;
    private Map<String, Categoria> categorias;
    private Map<String, Empleado> empleados;
    private List<Reserva> reservas;
    private List<Cliente> clientes;
    private Map<String, Coche> coches;
    private ICategoriaDAO catDAO;
    private IClienteDAO cliDAO;
    private IReservaDAO resDAO;
    private ISucursalDAO sucDAO;
    private IEmpleadoDAO empDAO;
    private IEntregaDAO entDAO;
    private ICocheDAO cocDAO;

    private static AlquilerVehiculos INSTANCE = new AlquilerVehiculos();

    /** Constructor */
    private AlquilerVehiculos()  {
    	try{
    		catDAO = new CategoriaDAOImp();
    		cliDAO = new ClienteDAOImp();
    		resDAO = new ReservaDAOImp();
    		sucDAO = new SucursalDAOImp();
    		cocDAO = new CocheDAOImp();
    		entDAO = new EntregaDAOImp();
    		empDAO = new EmpleadoDAOImp();
    	}catch (DAOExcepcion e){System.err.println(e);}
    	sucursales = new HashMap<Integer, Sucursal>();
    	categorias = new HashMap<String, Categoria>();
    	reservas = new ArrayList<Reserva>();
    	clientes = new ArrayList<Cliente>();
    	empleados = new HashMap<String, Empleado>();
    	coches = new HashMap<String, Coche>();
    	cargaSistema();
	}

/*
    public static AlquilerVehiculos getInstance() {
    	return INSTANCE;

	}
*/
    
//Refactory del getInstance
    private static void createInstance() {
        if (INSTANCE == null) {
            // S�lo se accede a la zona sincronizada
            // cuando la instancia no est� creada
            synchronized(AlquilerVehiculos.class) {
                // En la zona sincronizada ser�a necesario volver
                // a comprobar que no se ha creado la instancia
                if (INSTANCE == null) { 
                    INSTANCE = new AlquilerVehiculos();
                }
            }
        }
    }
    public static AlquilerVehiculos getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
 /*Fin Refactory*/   
    
    /** Getters y setters */
	public Map<Integer, Sucursal> getSucursales() {
		return sucursales;
	}
	public void setSucursales(Map<Integer, Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
	public Map<String, Coche> getCoches() {
		return coches;
	}
	public void setCoches(Map<String, Coche> coches) {
		this.coches = coches;
	}
	public Map<String, Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(Map<String, Categoria> categorias) {
		this.categorias = categorias;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public Map<String, Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(Map<String, Empleado> empleados) {
		this.empleados = empleados;
	}

	/** Modificadores de listas */
	public void addReserva(Reserva r) {
		reservas.add(r);
	}
	public boolean removeReserva(Reserva r) {
		return reservas.remove(r);
	}
	public Reserva removeReserva(int index) {
		return reservas.remove(index);
	}
	public Reserva getReserva(int index) {
		return reservas.get(index);
	}

	public void putSucursal(Integer key, Sucursal s) {
		sucursales.put(key, s);
	}
	public Sucursal removeSucursal(Integer key) {
		return sucursales.remove(key);
	}
	public Sucursal getSucursal(Integer key) {
		return sucursales.get(key);
	}
	
	public void putCoche(String key, Coche c) {
		coches.put(key, c);
	}
	public Coche removeCoche(String key) {
		return coches.remove(key);
	}
	public Coche getCoche(String key) {
		return coches.get(key);
	}

	public void putCategoria(String key, Categoria c) {
		categorias.put(key, c);
	}
	public Categoria removeCategoria(String key) {
		return categorias.remove(key);
	}
	public Categoria getCategoria(String key) {
		return categorias.get(key);
	}

	public void addCliente(Cliente c) {
		clientes.add(c);
	}
	public boolean removeCliente(Cliente c) {
		return clientes.remove(c);
	}
	public Cliente removeCliente(int index) {
		return clientes.remove(index);
	}
	public Cliente getCliente(int index) {
		return clientes.get(index);
	}
	
	public void addEmpleado(String key, Empleado e) {
		empleados.put(key, e);
	}
	public Empleado removeEmpleado(String key) {
		return empleados.remove(key);
	}
	public Empleado getEmpleado(String key) {
		return empleados.get(key);
	}

	/** Métodos */

	private void cargaSistema(){
		cargaCategorias();
		cargaSucursal();
	}

	/* Cargar de la base de datos */
	
	private List<CategoriaDTO> obtenerCategorias() {
		try
		{
			return	catDAO.obtenerCategorias();
		} catch(DAOExcepcion e) {
			return	null;
		}
	}

	private void cargaCategorias() {
		List<CategoriaDTO> listacatDTO = obtenerCategorias();
		// Crear y a�adir todas las categorias a la colecci�n
		for (CategoriaDTO catDTO : listacatDTO) {
			putCategoria(catDTO.getNombre(), new Categoria(catDTO.getNombre(),
			catDTO.getPrecioModIlimitada(), catDTO.getPrecioModKms(),
			catDTO.getPrecioKMModKms(), catDTO.getPrecioSeguroTRiesgo(),
			catDTO.getPrecioSeguroTerceros()));
		}
		// Actualizar los enlaces que representan la relaci�n "superior"
		for (CategoriaDTO catDTO : listacatDTO) {
			if (catDTO.getNombreCategoriaSuperior() != null)
				getCategoria(catDTO.getNombre()).
				setCategoriaSuperior(getCategoria(catDTO.getNombreCategoriaSuperior()));
		}
	}

	private List<SucursalDTO> obtenerSucursales() {
		List<SucursalDTO> lis= new ArrayList<>();
		try
		{
				lis=sucDAO.obtenerSucursales();
		} catch(DAOExcepcion e) {
			System.out.print(e);
		}
		return lis;
	}
	private void cargaSucursales() {
		List<SucursalDTO> listasucDTO = obtenerSucursales();
		for (SucursalDTO sucDTO : listasucDTO) {
			Sucursal s = new Sucursal(sucDTO.getId(), sucDTO.getDireccion());
			cargarReservasSucursal(s);
		}
		for (Reserva res : reservas) {
			sucursales.get(res.getLugar_devolucion()).addLugarDevolucion(res);
		}
	}
	
	private void cargarReservasSucursal(Sucursal s) {
		try {
			for(ReservaDTO resDTO : resDAO.obtenerReservasPorSucursalOrigen(s.getId())) {
				Reserva res = new Reserva(resDTO.getId(),resDTO.getFechaRecogida(), resDTO.getFechaDevolucion(),
						"" + resDTO.getModalidadAlquiler(), categorias.get(resDTO.getNombreCategoria()),
						resDTO.getIdSucursalDevolucion(), s.getId(), cargarClienteReserva(resDTO.getId()));
				reservas.add(res);
				s.addLugarRecogida(res);
			}
			putSucursal(new Integer(s.getId()), s);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Cliente cargarCliente(String dni) {
		ClienteDTO cliDTO;
		
		try {
			cliDTO = cliDAO.buscarCliente(dni);

			Cliente cli = new Cliente(cliDTO.getDni(), cliDTO.getNombreyApellidos(), "", cliDTO.getDireccion(), cliDTO.getPoblacion(),
					cliDTO.getTipoTC(), Integer.parseInt(cliDTO.getCodPostal().trim()), cliDTO.getDigitosTC().trim(), cliDTO.getMesTC(), cliDTO.getAnyoTC(), cliDTO.getCvcTC(),
					cliDTO.getFechaCanetConducir());
			clientes.add(cli);
			return cli;
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected Cliente cargarClienteReserva(int id) {
		ClienteDTO cliDTO;
		
		try {
			cliDTO = cliDAO.buscarClientePorReserva(id);

			Cliente cli = new Cliente(cliDTO.getDni(), cliDTO.getNombreyApellidos(), "", cliDTO.getDireccion(), cliDTO.getPoblacion(),
					cliDTO.getTipoTC(), Integer.parseInt(cliDTO.getCodPostal().trim()), cliDTO.getDigitosTC().trim(), cliDTO.getMesTC(), cliDTO.getAnyoTC(), cliDTO.getCvcTC(),
					cliDTO.getFechaCanetConducir());
			clientes.add(cli);
			return cli;
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private List<EmpleadoDTO> obtenerEmpleadosSucursal(int idsucursal) {
		List<EmpleadoDTO> lis;
		try
		{
				lis=empDAO.obtenerEmpleadosPorSucursal(idsucursal);
		} catch(DAOExcepcion e) {
			System.err.print(e);
			lis = new ArrayList<EmpleadoDTO>();
		}
		return lis;
	}
	
	private void cargarEmpleados() {
		for(Integer suc : sucursales.keySet()) {
			for(EmpleadoDTO empDTO : obtenerEmpleadosSucursal(suc.intValue())) {
				Empleado emp = new Empleado(empDTO.getDni(), empDTO.getNombre(), empDTO.isAdministrador(),
											sucursales.get(empDTO.getSucursal()));
				sucursales.get(suc).addEmpleado(emp);
				empleados.put(emp.getDni(), emp);
			}
		}
	}

	protected void cargarEntregas() {
		try {
			for(EntregaDTO entDTO : entDAO.obtenerEntregas()) {
				Entrega ent = new Entrega(entDTO.getFecha(), entDTO.getTipoSeguro(), entDTO.getKms(), entDTO.getCombustible(),
											coches.get(entDTO.getCocheAsignado()), empleados.get(entDTO.getEmpleadoRealiza()));
				for(Reserva res : reservas) {
					if(res.getId() == entDTO.getId()) {
						res.setEntrega(ent);
						break;
					}
				}
			}
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cargarEntregasPorSucursal(int idSucursal) {
		try {
			for(EntregaDTO entDTO : entDAO.obtenerEntregasPorSucursal(idSucursal)) {
				Entrega ent = new Entrega(entDTO.getFecha(), entDTO.getTipoSeguro(), entDTO.getKms(), entDTO.getCombustible(),
											coches.get(entDTO.getCocheAsignado()), empleados.get(entDTO.getEmpleadoRealiza()));
				for(Reserva res : reservas) {
					if(res.getId() == entDTO.getId()) {
						res.setEntrega(ent);
						break;
					}
				}
			}
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<CocheDTO> obtenerCochesSucursal(int idsucursal) {
		List<CocheDTO> lis;
		try
		{
				lis=cocDAO.obtenerCochesSucursal(idsucursal);
		} catch(DAOExcepcion e) {
			System.err.print(e);
			lis = new ArrayList<CocheDTO>();
		}
		return lis;
	}
	
	private void cargarCoches() {
		for(Integer suc : sucursales.keySet()) {
			for(CocheDTO cocDTO : obtenerCochesSucursal(suc.intValue())) {
				Coche coc = new Coche(cocDTO.getMatricula(), cocDTO.getKsmActuales(), suc.intValue(),
						cocDTO.getCategoria(), categorias.get(cocDTO.getNombre()));
				if(suc == cocDTO.getSucursal())
					sucursales.get(suc).addCoche(coc);
				coches.put(coc.getMatricula(), coc);
			}
		}
	}
	
	/* Crear en la base de datos */
	
	public void crearCliente(String dni, String nombre, String apellidos,  String direccion,  String poblacion, String tipoTC,
    		int cod_postal, String digitosTC, int mesTC, int anyoTC, int cvcTC, LocalDate fechaCarnetConducir) {

		try {
			if (cliDAO.buscarCliente(dni) == null) {
				ClienteDTO cliDTO = new ClienteDTO(dni, nombre + " " + apellidos, direccion, poblacion, "" + cod_postal,
						fechaCarnetConducir, digitosTC, mesTC, anyoTC, cvcTC, tipoTC);
				cliDAO.crearCliente(cliDTO);

				Cliente cli = new Cliente(dni, nombre, apellidos, direccion, poblacion, tipoTC,
						cod_postal, digitosTC, mesTC, anyoTC, cvcTC, fechaCarnetConducir);
				clientes.add(cli);
			}
			else {
				System.out.println( "El cliente ya existe");
			}
		} catch (DAOExcepcion e) {
			System.out.println("Error al acceder a la base de datos");
		}
	}

	public void crearReserva(Reserva res) {

		//int id = res.hashCode() + fechaRecogida.hashCode(); // PROVISIONAL
		try {
			if(resDAO.buscarReserva(res.getId()) == null) {
				int modal;
				if(res.getModalidadAlquiler().equals("Kilometraje ilimitado")){
					 modal = 0;
				 } else modal=1;
				ReservaDTO resDTO = new ReservaDTO(res.getId(), res.getFechaRegogida(), res.getFechaDevolucion(), modal,
						res.getDniCliente(), res.getCatergoria().getId(), res.getLugar_recogida(), res.getLugar_devolucion());
				resDAO.crearReserva(resDTO);

				reservas.add(res);
				sucursales.get(res.getLugar_recogida()).addLugarRecogida(res);
				sucursales.get(res.getLugar_devolucion()).addLugarDevolucion(res);
			}
			else {
				System.err.println("La reserva ya existe");
				// La reserva ya existe
			}
		} catch (DAOExcepcion e) {
			System.err.println("Error al guardar reserva");
			System.out.println(e);
			// Error al accader a la base de datos
		}
	}
	
	public void crearEntrega(int id, Entrega ent) {
		try {
			if(entDAO.buscarEntrega(id) == null) {
				EntregaDTO entDTO = new EntregaDTO(id, ent.getFecha(), ent.getTipoSeguro(), ent.getKms(), ent.getCombustible(),
						ent.getCoche().getMatricula(), ent.getEmpleado().getDni());

				entDAO.crearEntrega(entDTO);
				
				for(Reserva res : reservas)
					if(res.getId() == id)	res.setEntrega(ent);
			}
			else {
				System.err.println("La entrega ya existe");
				// La entrega ya existe
			}
		} catch (DAOExcepcion e) {
			System.err.println("Error al guardar entrega");
			System.out.println(e);
			// Error al accader a la base de datos
		}
	}

	/* Listar */
	
	public List<Reserva> listarReservasSucursal(int cod) {
		return sucursales.get(cod).getLugaresRecogida();
	}
	public List<Reserva> listarReservasSucursalD(int cod) {
		return sucursales.get(cod).getLugaresDevolucion();
	}
	public List<Coche> listaCochesDisponibles(int id){
		return sucursales.get(id).getCoches();
	}
	public void cargaSucursal(){
		cargaSucursales();
		cargarEmpleados();
		cargarCoches();
	}
}
