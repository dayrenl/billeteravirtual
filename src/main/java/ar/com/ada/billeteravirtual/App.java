package ar.com.ada.billeteravirtual;

import java.util.*;

import ar.com.ada.billeteravirtual.security.Crypto;


public class App {

    public static Scanner Teclado = new Scanner(System.in);

    public static PersonaManager ABMPersona = new PersonaManager();
    public static UsuarioManager ABMUsuario = new UsuarioManager();

    public static void main(String[] args) throws Exception {
        
        try {
        
        ABMPersona.setup();
        ABMUsuario.setup();
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

    public static void alta() {
        Persona p = new Persona();
        System.out.println("Ingrese el nombre:");
        p.setNombre(Teclado.nextLine());
        System.out.println("Ingrese el DNI:");
        p.setDni(Teclado.nextLine());
        System.out.println("Ingrese la edad:");
        p.setEdad(Teclado.nextInt());
        Teclado.nextLine();
        System.out.println("Ingrese el Email:");
        p.setEmail(Teclado.nextLine());

        ABMPersona.create(p);
        
        System.out.println("Persona generada con exito.  " + p);
        System.out.println("Si desea crear un usuario, presione 6");


        System.out.println("Si no desea crear un usuario, presione cualquier numero");
        int crearUsuario = Teclado.nextInt();
        Teclado.nextLine();
        if (crearUsuario == 6) {
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
            ABMUsuario.create(u);
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

    public static void modifica() {
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
            System.out.println("Ingrese la nueva edad:");
            personaEncontrada.setEdad(Teclado.nextInt());
            Teclado.nextLine();

            System.out.println("Ingrese el nuevo Email:");
            personaEncontrada.setEmail(Teclado.nextLine());

            ABMPersona.update(personaEncontrada);
            System.out.println("El registro de " + personaEncontrada.getDni() + " ha sido modificado.");

        } else {
            System.out.println("Persona no encontrada.");
        }

    }

    public static void modificaByDNI() {
        // System.out.println("Ingrese el nombre de la persona a modificar:");
        // String n = Teclado.nextLine();
        System.out.println("Ingrese el DNI de la persona a modificar:");
        String dni = Teclado.nextLine();
        Persona personaEncontrada = ABMPersona.readByDNI(dni);

        if (personaEncontrada != null) {

            System.out.println(personaEncontrada.toString() + "seleccionado para modificacion.");
            System.out.println("Ingrese el nuevo nombre:");
            personaEncontrada.setNombre(Teclado.nextLine());
            System.out.println("Ingrese el nuevo DNI:");
            personaEncontrada.setDni(Teclado.nextLine());
            // Teclado.nextLine();
            System.out.println("Ingrese la nueva edad:");
            personaEncontrada.setEdad(Teclado.nextInt());
            Teclado.nextLine();

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
}