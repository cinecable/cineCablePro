package global;

import javax.faces.bean.SessionScoped;

@SessionScoped
public class Parametro {
	public static final String FILE_SEPARATOR = "/";//File.separator;
	public static final long DAY_IN_MILLISECONDS = (24*60*60*1000);
	public static final String PROPERTIES_FILE_NAME = "parametros.properties";
	
	//Seccion de rangos de los cargos
	public static final short CARGO_NIVEL_SERVICIO_MIN = 1;
	public static final short CARGO_NIVEL_SERVICIO_MAX = 50;
	public static final short CARGO_NIVEL_DESCUENTO_MIN = 51;
	public static final short CARGO_NIVEL_DESCUENTO_MAX = 100;
	public static final short CARGO_NIVEL_IMPUESTO_MIN = 101;
	public static final short CARGO_NIVEL_IMPUESTO_MAX = 150;
	
	//Seccion de niveles de los cargos
	public static final short CARGO_NIVEL_IVA_SERVICIOS = 101;
	public static final short CARGO_NIVEL_ICE_SERVICIOS = 102;
	
	//Seccion de tipos de menu
	public static final int TIPOMENU_PRINCIPAL = 1;
	public static final int TIPOMENU_CLIENTE = 2;
	
	//Seccion de formas de pago
	public static final int TIPO_FORMA_PAGO_CHEQUE = 1;
	public static final int TIPO_FORMA_PAGO_TARJETA_CREDITO = 2;
	public static final int TIPO_FORMA_PAGO_TARJETA_DEBITO = 3;
	public static final int TIPO_FORMA_PAGO_TRANSFERENCIA = 4;
	public static final int TIPO_FORMA_PAGO_DEPOSITO = 5;
	public static final int TIPO_FORMA_PAGO_EFECTIVO = 6;
	public static final int TIPO_FORMA_PAGO_CREDITO = 7;
	public static final int TIPO_FORMA_PAGO_EXCEDENTE = 8;
	
	//Seccion de tipos de menu
	public static final int TIPO_MENU_PRINCIPAL = 1;
	public static final int TIPO_MENU_CLIENTE = 2;
	
	//Seccion de estado civil
	public static final int ESTADO_CIVIL_SOLTERO = 1;
	public static final int ESTADO_CIVIL_CASADO = 2;
	public static final int ESTADO_CIVIL_DIVORCIADO = 3;
	public static final int ESTADO_CIVIL_UNION_LIBRE = 4;
	public static final int ESTADO_CIVIL_OTRO = 5;
	
	//Seccion de tipos de identificacion
	public static final int TIPO_IDENTIFICACION_CEDULA = 1;
	public static final int TIPO_IDENTIFICACION_RUC = 2;
	public static final int TIPO_IDENTIFICACION_PASAPORTE = 3;
	public static final int TIPO_IDENTIFICACION_OTRO = 4;
	
	//Seccion de tipos de persona
	public static final int TIPO_PERSONA_NATURAL = 1;
	public static final int TIPO_PERSONA_JURIDICO = 2;
	
	//Session de areas
	public static final int AREA_VENTAS = 1;
	public static final int AREA_TECNICA = 2;
	public static final int AREA_COBRANZAS = 3;
	public static final int AREA_COMPRAS = 4;
	public static final int AREA_INSTALACIONES = 5;
	public static final int AREA_ADMINISTRATIVO = 6;
	
	//Seccion tipo sector
	public static final int TIPO_SECTOR_ENTRECALLES = 1;
	public static final int TIPO_SECTOR_URBANIZACION = 2;
	
	//Seccion tipo debito
	public static final int TIPO_DEBITO_BANCARIO = 1;
	public static final int TIPO_DEBITO_TARJETA = 2;
	public static final int TIPO_DEBITO_OFICINA = 3;
	
	//Estados Factura
	public static final int FACTURA_ESTADO_PENDIENTE = 3;
	public static final int FACTURA_ESTADO_PAGADA = 4;
	public static final int FACTURA_ESTADO_MORA = 5;
	public static final int FACTURA_ESTADO_ANULADO = 7;
	
	//Estados Credito
	public static final int CREDITO_ESTADO_CONSUMIDO = 4;
	
	//Estados Excedente
	public static final int EXCEDENTE_ESTADO_PENDIENTE = 3;
	public static final int EXCEDENTE_ESTADO_CONSUMIDO = 4;
	
	//Estados Pagos
	public static final int PAGOS_ESTADO_ACTIVO = 1;
	
	//Estados TPagos
	public static final int TPAGOS_ESTADO_PENDIENTE = 3;
	public static final int TPAGOS_ESTADO_CONSUMIDO = 4;
	
	//Estados Cargo
	public static final int CARGOS_ESTADO_PENDIENTE = 3;
	public static final int CARGOS_ESTADO_PAGADO = 4;
	
	//Estados Cuenta Cliente
	public static final int CUENTA_CLIENTE_IMPAGO = 3;
	public static final int CUENTA_CLIENTE_PENDIENTE = 2;
	
	//Seccion tipo motivo
	public static final int TIPO_MOTIVO_CREDITO = 2;
	public static final int TIPO_MOTIVO_MULTAS = 3;
	public static final int TIPO_MOTIVO_CREDITO_INTERNO = 4;
	public static final int TIPO_MOTIVO_FACTURA = 5;

}
