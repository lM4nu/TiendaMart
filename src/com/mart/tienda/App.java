package com.mart.tienda;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.mart.tienda.auxi.Aplicado;
import com.mart.tienda.auxi.CamposBebida;
import com.mart.tienda.auxi.CamposComida;
import com.mart.tienda.auxi.Envase;
import com.mart.tienda.auxi.Menu;
import com.mart.tienda.modelos.Bebida;
import com.mart.tienda.modelos.Envasado;
import com.mart.tienda.modelos.PLimpieza;
import com.mart.tienda.modelos.Tienda;

public class App {
    public static Scanner scanner = new Scanner(System.in);

    static Tienda mart = new Tienda("Mart", 92, 10000f);

    public static void main(String[] args) throws Exception {

        Bebida agua = new Bebida(
                "Agua Villavicencio",
                "Bottella de agua con pico deportivo 500ml",
                4,
                15.50f,
                18.50f,
                true, 0.4f, new CamposBebida(), new CamposComida());

        Bebida whisky = new Bebida(
                "Scotch",
                "Bottella de Whisky Escoces 1L",
                10,
                40.00f,
                48.00f,
                true, 0.02f, new CamposBebida(0.50f), new CamposComida(true, 1000));

        Envasado tomate = new Envasado(
                "Pure de Tomate",
                "Bottella de pure 1L",
                10,
                40.5f,
                45.0f,
                true,
                0.2f,
                Envase.VIDRIO,
                new CamposComida());

        PLimpieza detergente = new PLimpieza(
                "Mr Musculo", "Limpiador de vajillas", 10, 4.0f, 4.6f, true, 0.5f,
                Aplicado.COCINA);

        mart.agregarProducto(agua);
        mart.agregarProducto(whisky);
        mart.agregarProducto(detergente);
        mart.agregarProducto(tomate);
        Menu.limpiar();

        Boolean menu = true;
        int eleccion;
        do {
            try {
                System.out.println("1. Mostrar listado de productos");
                System.out.println("2. Buscar");
                System.out.println("3. Mostrar estado de tienda");
                System.out.println("4. Agregar Producto");
                System.out.println("5. Vender Producto");
                System.out.println("0. Salir");
                eleccion = scanner.nextInt();
                scanner.nextLine();

                Menu.limpiar();
                switch (eleccion) {
                    case 1:
                        mart.mostrarProductos();
                        break;
                    case 2:
                        busquedaMenu();
                        break;
                    case 3:
                        mart.mostrarSaldoStock();
                        break;
                    case 4:
                        agregarProductoMenu();
                        break;
                    case 5:
                        venderProductoMenu();
                        break;
                    case 0:
                        menu = false;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }

            } catch (InputMismatchException e) {
                Menu.limpiar();
                System.out.println("Opcion no valida");
                scanner.nextLine();
            }
        } while (menu);
        scanner.close();
    }

    public static void agregarProductoMenu() {
        int eleccion;
        boolean menu = true;
        do {
            try {
                System.out.println("Seleccionar Tipo de Producto");
                System.out.println("1. Bebida");
                System.out.println("2. Envasado");
                System.out.println("3. Producto de Limpieza");
                System.out.println("0. Volver");
                eleccion = scanner.nextInt();
                scanner.nextLine();
                int tipo = 0;

                Menu.limpiar();
                switch (eleccion) {
                    case 1:
                        tipo = 1;
                        break;
                    case 2:
                        tipo = 2;
                        break;
                    case 3:
                        tipo = 3;
                        break;
                    case 0:
                        menu = false;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }

                if (menu) {
                    String nombre = Menu.ingresarString("Nombre");
                    String desc = Menu.ingresarString("Descripcion");
                    int stock = (int) Menu.ingresarNumero("Stock");
                    float cUnitario = Menu.ingresarNumero("Costo Unitario");
                    float pUnitario = cUnitario;
                    switch (tipo) {
                        case 1, 2:
                            // El porcentaje de ganancia de los productos comestibles no podrá superar el
                            // 20%
                            System.out.println(
                                    "El porcentaje de ganancia de los productos comestibles no podrá superar el 20%");

                            do {
                                pUnitario = Menu.ingresarNumero("Precio Unitario");
                            } while (pUnitario > cUnitario * 1.2 || pUnitario <= cUnitario);
                            break;
                        case 3:
                            // el de los productos de limpieza no podrá ser menor del 10% ni mayor al 25%,
                            System.out.println(
                                    "El porcentaje de ganancia de los productos de limpieza no podrá ser menor de 10% ni mayor de 25%");
                            do {
                                pUnitario = Menu.ingresarNumero("Precio Unitario");
                            } while (pUnitario < cUnitario * 1.1 || pUnitario > cUnitario * 1.25);
                            break;
                    }
                    boolean disponible = Menu.ingresarBoolean("Disponible");
                    float descuento = 0;
                    // float descuento = Menu.ingresarNumero("Descuento (en decimal ej: 0.5 es
                    // 50%)");
                    switch (tipo) {
                        case 1:
                            // El porcentaje de descuento de las bebidas no podrá superar el 15%
                            do {
                                descuento = Menu.ingresarNumero("Descuento (en decimal ej: 0.5 es 50%)");
                            } while (descuento > 0.15f);
                            break;
                        case 2:
                            // el de los envasados el 20%
                            do {
                                descuento = Menu.ingresarNumero("Descuento (en decimal ej: 0.5 es 50%)");
                            } while (descuento > 0.2f);
                            break;
                        case 3:
                            // el de los productos de limpieza el 25%
                            do {
                                descuento = Menu.ingresarNumero("Descuento (en decimal ej: 0.5 es 50%)");
                            } while (descuento > 0.2f);
                            break;
                    }
                    CamposComida cc;
                    switch (tipo) {
                        // Bebida
                        case 1:
                            cc = Menu.ingresarComida();
                            CamposBebida cb = Menu.ingresarBebida();
                            Bebida bebida = new Bebida(nombre, desc, stock, cUnitario, pUnitario, disponible, descuento,
                                    cb, cc);
                            mart.agregarProducto(bebida);
                            break;
                        // Envasado
                        case 2:
                            cc = Menu.ingresarComida();
                            Envase envase = Menu.ingresarEnvase();
                            Envasado envasado = new Envasado(nombre, desc, stock, cUnitario, pUnitario, disponible,
                                    descuento, envase, cc);
                            mart.agregarProducto(envasado);
                            break;
                        // Producto de Limpieza
                        case 3:
                            Aplicado tipoAplicado = Menu.ingresarAplicado();
                            PLimpieza plimpieza = new PLimpieza(nombre, desc, stock, cUnitario, pUnitario, disponible,
                                    descuento, tipoAplicado);
                            mart.agregarProducto(plimpieza);
                            break;
                        default:
                            ;
                    }

                }

            } catch (InputMismatchException e) {
                Menu.limpiar();
                System.out.println("Opcion no valida");
                scanner.nextLine();
            }

        } while (menu);
    }

    public static void venderProductoMenu() {
        int eleccion;
        boolean menu = true;
        do {
            System.out.println("1. Ingresar id y cantidad");
            System.out.println("0. Volver");
            eleccion = (int) Menu.ingresarNumero("Opción");
            String[] venta;
            switch (eleccion) {
                case 1:
                    venta = elegirProductoMenu();
                    Menu.limpiar();
                    mart.venderProductos(venta);
                    break;
                case 0:
                    menu = false;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (menu);
    }

    public static String[] elegirProductoMenu() {
        Menu.limpiar();
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> elegidas = new ArrayList<>();
        ArrayList<Integer> cantidades = new ArrayList<>();
        boolean seguir = true;
        for (Bebida b : mart.getBebidas()) {
            if (b.isDisponible())
                ids.add(b.getId());
        }
        for (Envasado e : mart.getEnvasados()) {
            if (e.isDisponible())
                ids.add(e.getId());
        }
        for (PLimpieza l : mart.getPLimpieza()) {
            if (l.isDisponible())
                ids.add(l.getId());
        }

        do {

            List<Bebida> bebList = mart.getBebidas().stream().filter(b -> b.isDisponible()).toList();
            List<Envasado> envList = mart.getEnvasados().stream().filter(b -> b.isDisponible()).toList();
            List<PLimpieza> plimList = mart.getPLimpieza().stream().filter(b -> b.isDisponible()).toList();
            int bebDisponibles = bebList.size();
            int envDisponibles = envList.size();
            int plimDisponibles = plimList.size();
            int maxDisponible = bebDisponibles + envDisponibles + plimDisponibles;

            System.out.println("Bebidas");
            for (int i = 0; i < bebDisponibles; i++) {
                Bebida bebida = bebList.get(i);
                System.out.printf("%s. %s %s Precio: %s Stock: %s%n", (i + 1), ids.get(i), bebida.getNombre(),
                        bebida.getPrecioUnitario(), bebida.getStock());
            }
            System.out.println("Envasados");
            for (int i = bebDisponibles; i < bebDisponibles + envDisponibles; i++) {
                Envasado envasado = envList.get(i - bebDisponibles);
                System.out.printf("%s. %s %s Precio: %s Stock: %s%n", (i + 1), ids.get(i), envasado.getNombre(),
                        envasado.getPrecioUnitario(), envasado.getStock());
            }
            System.out.println("Productos de Limpieza");
            for (int i = bebDisponibles + envDisponibles; i < maxDisponible; i++) {
                PLimpieza plimpieza = plimList.get(i - bebDisponibles - envDisponibles);
                System.out.printf("%s. %s %s Precio: %s Stock: %s%n", (i + 1), ids.get(i), plimpieza.getNombre(),
                        plimpieza.getPrecioUnitario(), plimpieza.getStock());
            }
            System.out.println("0. Finalizar eleccion");

            int eleccion = (int) Menu.ingresarNumero("Producto") - 1;
            switch (eleccion) {
                case -1:
                    seguir = false;
                    break;
                default:
                    if (eleccion >= 0 && eleccion < maxDisponible && !elegidas.contains(ids.get(eleccion))) {
                        elegidas.add(ids.get(eleccion));
                        int cantidad = 0;
                        do {
                            cantidad = (int) Menu.ingresarNumero("Cantidad");
                        } while (cantidad > 10);
                        elegidas.add(Integer.toString(cantidad));
                        Menu.limpiar();
                    }
            }

        } while (seguir && elegidas.size() < 6);

        System.out.println("Productos seleccionados: " + elegidas.toString());
        String[] x = new String[elegidas.size()];
        x = elegidas.toArray(x);
        return x;
    }

    public static void busquedaMenu() {
        boolean seguir = true;
        int eleccion;
        do {

            System.out.println("Busquedas disponibles");
            System.out.println("1. Obtener comestibles con menor descuento");
            System.out.println("2. Productos con utilidades inferiores");
            System.out.println("0. Volver");

            eleccion = (int) Menu.ingresarNumero("Opcion");

            switch (eleccion) {
                case 1:
                    float descuento = Menu.ingresarNumero("Descuento");
                    Menu.limpiar();
                    mart.obtenerComestiblesConMenorDescuento(descuento);
                    break;
                case 2:
                    float ganancia = Menu.ingresarNumero("Ganancia");
                    Menu.limpiar();
                    mart.listarProductosConUtilidadesInferiores(ganancia);
                    break;
                case 0:
                    seguir = false;
                    break;
                default:
                    ;
            }

        } while (seguir);

    }

}
