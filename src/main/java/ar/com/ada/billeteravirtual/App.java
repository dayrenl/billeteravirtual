package ar.com.ada.billeteravirtual;

import java.util.*;

import ar.com.ada.billeteravirtual.security.Crypto;


public class App {

    public static Scanner Teclado = new Scanner(System.in);

    public static PersonaManager ABMPersona = new PersonaManager();
    public static UsuarioManager ABMUsuario = new UsuarioManager();
    public static BilleteraManager ABMBilletera = new BilleteraManager();
    public static CuentaManager ABMCuenta = new CuentaManager();
    public static void main(String[] args) throws Exception {
        
        try {
        
        ABMPersona.setup();
        ABMUsuario.setup();
        ABMBilletera.setup();
        ABMCuenta.setup();
        printOpciones();

        int opcion = Teclado.nextInt();
        Teclado.nextLine();

        while (opcion > 0) {

            switch (opcion) {
            
            case 1:
            
                alta();
                break;

            case 2:
                baja();
                break;

            case 3:
                modifica();
                break;

            case 4:
                listar();
                break;

            case 5:
                listarPorNombre();
                break;

            default:
                System.out.println("La opcion no es correcta.");
                break;
            }

            printOpciones();

            opcion = Teclado.nextInt();
            Teclado.nextLine();
        }

        // Hago un safe exit del manager
        ABMPersona.exit();
        ABMUsuario.exit();

    } catch (Exception e) {
        System.out.println("Ha habido un error. Vuelva a intentar.");
        throw e;
    } finally {
        System.out.println("Saliendo del sistema.");

    }
    }

    public static void alta() throws Exception {
        Persona p = new Persona();
        System.out.println("Ingrese el nombre:");
        p.setNombre(Teclado.nextLine());
        System.out.println("Ingrese el DNI:");
        p.setDni(Teclado.nextLine());
        try {
        System.out.println("Ingrese la edad:");
        p.setEdad(Teclado.nextInt());
        Teclado.nextLine(); }
        catch (Exception exedad) {
            System.out.println("El usuario debe ser mayor a 18");
            throw exedad;
        }
        System.out.println("Ingrese el Email:");
        p.setEmail(Teclado.nextLine());

        System.out.println("Persona generada con exito.  " + p);
        System.out.println("Ahora proporcione la informacion de usuario.");


            Usuario u = new Usuario();

            System.out.println("Escoja su username");
            u.setUsername(Teclado.nextLine()); 
            System.out.println("Escoja su password");
            String passwordEncriptada;
            String passwordEnTextoClaroDesencriptado;
            String passwordEnTextoClaro;
            passwordEnTextoClaro = Teclado.nextLine();
            passwordEncriptada = Crypto.encrypt(passwordEnTextoClaro, u.getUsername());
            passwordEnTextoClaroDesencriptado = Crypto.decrypt(passwordEncriptada, u.getUsername());
            System.out.println("Tu password encriptada es :" +  passwordEncriptada);

            System.out.println("Tu password desencriptada es :" +  passwordEnTextoClaroDesencriptado);
            
            if (passwordEnTextoClaro.equals(passwordEnTextoClaroDesencriptado))
            {
                System.out.println("Ambas passwords coinciden");
            }
            else {
                System.out.println("Las passwords no coinciden, nunca debio entrar aqui");
            }

            u.setPassword(passwordEncriptada);
            u.setEmail(p.getEmail());

            p.setUsuario(u);
            ABMPersona.create(p);
        

            Billetera b = new Billetera();
            b.setPersona(p);

            Cuenta c = new Cuenta();
            c.setMoneda("ARS");
            b.agregarCuenta(c);

            ABMBilletera.create(b);

          Movimiento m = new Movimiento();
            System.out.println("Ingrese su importe");  
            double importe = Teclado.nextDouble(); 
            b.movimientoInicial(m, importe, c, u);
            
            c.agregarMovimiento(m);
        
            ABMBilletera.update(b);


            System.out.println("Se ha creado una billetera");

            System.out.println("Sus datos personales son " + p.toString() + ", de usuario " + u.toString() + " y de billetera " + b.toString());

            System.out.println("Si desea transferir dinero, presione 6");
            int continuar = Teclado.nextInt();

            if (continuar == 6) {
                System.out.println("Escriba el id de usuario al que desea transferir");
                int numausuario = Teclado.nextInt();
                System.out.println("Escriba el importe que desea transferir");
                double dineroATransferir = Teclado.nextDouble(); 
                transferirDinero(numausuario, dineroATransferir, u);
            }
        }
    

    public static void baja() {
        System.out.println("Ingrese el nombre:");
        String n = Teclado.nextLine();
        System.out.println("Ingrese el ID de Persona:");
        int id = Teclado.nextInt();
        Teclado.nextLine();
        Persona personaEncontrada = ABMPersona.read(id);

        if (personaEncontrada == null) {
            System.out.println("Persona no encontrada.");

        } else {
            ABMPersona.delete(personaEncontrada);
            System.out.println("El registro de " + personaEncontrada.getDni() + " ha sido eliminado.");
        }
    }

    public static void bajaPorDNI() {
        System.out.println("Ingrese el nombre:");
        String n = Teclado.nextLine();
        System.out.println("Ingrese el DNI de Persona:");
        String dni = Teclado.nextLine();
        Persona personaEncontrada = ABMPersona.readByDNI(dni);

        if (personaEncontrada == null) {
            System.out.println("Persona no encontrada.");

        } else {
            ABMPersona.delete(personaEncontrada);
            System.out.println("El registro de " + personaEncontrada.getDni() + " ha sido eliminado.");
        }
    }

    public static void modifica() throws Exception {
        // System.out.println("Ingrese el nombre de la persona a modificar:");
        // String n = Teclado.nextLine();
        System.out.println("Ingrese el ID de la persona a modificar:");
        int id = Teclado.nextInt();
        Teclado.nextLine();
        Persona personaEncontrada = ABMPersona.read(id);

        if (personaEncontrada != null) {

            System.out.println(personaEncontrada.toString() + "seleccionado para modificacion.");
            System.out.println("Ingrese el nuevo nombre:");
            personaEncontrada.setNombre(Teclado.nextLine());
            System.out.println("Ingrese el nuevo DNI:");
            personaEncontrada.setDni(Teclado.nextLine());
            // Teclado.nextLine();
            try {
            System.out.println("Ingrese la nueva edad:");
            personaEncontrada.setEdad(Teclado.nextInt());
            Teclado.nextLine(); }
            catch (Exception exedad) {
                System.out.println("El usuario debe ser mayor de 18");
                throw exedad;
            }

            System.out.println("Ingrese el nuevo Email:");
            personaEncontrada.setEmail(Teclado.nextLine());

            ABMPersona.update(personaEncontrada);
            System.out.println("El registro de " + personaEncontrada.getDni() + " ha sido modificado.");

        } else {
            System.out.println("Persona no encontrada.");
        }

    }

    public static void modificaByDNI() throws Exception {
        // System.out.println("Ingrese el nombre de la persona a modificar:");
        // String n = Teclado.nextLine();
        System.out.println("Ingrese el DNI de la persona a modificar:");
        String dni = Teclado.nextLine();
        Persona personaEncontrada = ABMPersona.readByDNI(dni);

        if (personaEncontrada != null){

            System.out.println(personaEncontrada.toString() + "seleccionado para modificacion.");
            System.out.println("Ingrese el nuevo nombre:");
            personaEncontrada.setNombre(Teclado.nextLine());
            System.out.println("Ingrese el nuevo DNI:");
            personaEncontrada.setDni(Teclado.nextLine());
            // Teclado.nextLine();
            try {
            System.out.println("Ingrese la nueva edad:");
            personaEncontrada.setEdad(Teclado.nextInt());
            Teclado.nextLine(); }
            catch (Exception exedad) {
                System.out.println("El usuario debe ser mayor a 18");
                throw exedad;
            }
            System.out.println("Ingrese el nuevo Email:");
            personaEncontrada.setEmail(Teclado.nextLine());

            ABMPersona.update(personaEncontrada);
            System.out.println("El registro de " + personaEncontrada.getDni() + " ha sido modificado.");

        } else {
            System.out.println("Persona no encontrada.");
        }

    }

    public static void listar() {

        List<Persona> todas = ABMPersona.buscarTodas();
        for (Persona p : todas) {
            System.out.println("Id: " + p.getPesonaId() + " Nombre: " + p.getNombre());
        }
    }

    public static void listarPorNombre() {

        System.out.println("Ingrese el nombre:");
        String nombre = Teclado.nextLine();

        List<Persona> personas = ABMPersona.buscarPor(nombre);
        for (Persona p : personas) {
            System.out.println("Id: " + p.getPesonaId() + " Nombre: " + p.getNombre());
        }
    }

    public static void printOpciones() {
        System.out.println("=======================================");
        System.out.println("");
        System.out.println("Para agregar una persona presione 1.");
        System.out.println("Para eliminar una persona presione 2.");
        System.out.println("Para modificar una persona presione 3.");
        System.out.println("Para ver el listado presione 4.");
        System.out.println("Buscar una persona por nombre especifico(SQL Injection)) 5.");
        System.out.println("Para terminar presione 0.");
        System.out.println("");
        System.out.println("=======================================");
    }

    public static void transferirDinero(int usuarioId, double importe, Usuario u) {
    Billetera b2 = ABMBilletera.read(3);
    Movimiento m2 = new Movimiento();
    Billetera b3 = ABMBilletera.read(usuarioId);
    Movimiento m3 = new Movimiento();
    m2.setImporte(-60);
    m2.setFechaMovimiento(new Date());   
    m2.setConceptoOperacion("Pagos"); 
    m2.setEstado(1);
    m2.setTipoOperacion("Deposito");
    m2.setDetalle("Pago");
    m2.setDeUsuario(u.getUsuarioId());
    m2.setaUsuario(u.getUsuarioId());
    m2.setCuentaDestino(b2.getCuentas().get(0).getIdcuenta());
    m2.setCuentaOrigen(b3.getCuentas().get(0).getIdcuenta());

    m3.setImporte(+60);
    m3.setFechaMovimiento(new Date());
    m3.setConceptoOperacion("Deposito");
    m3.setEstado(2);
    m3.setTipoOperacion("Transferencia");
    m3.setDetalle("Sueldo");
    m3.setDeUsuario(u.getUsuarioId());
    m3.setaUsuario(u.getUsuarioId());
    m3.setCuentaDestino(b2.getCuentas().get(0).getIdcuenta());
    m3.setCuentaOrigen(b3.getCuentas().get(0).getIdcuenta());

    
    
    b2.agregarMovimiento(m2); 
    ABMBilletera.update(b2);
    b3.agregarMovimiento(m3);
    ABMBilletera.update(b3);

    }
}