package com.mart.tienda.auxi;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    static Scanner scanner = new Scanner(System.in);

    public static void limpiar() {
        for (int i = 0; i < 50; i++) {
            System.out.printf("%n");
        }
    }

    public static LocalDate ingresarFecha() {
        int dia;
        int mes;
        int anio;
        do {
            dia = (int) ingresarNumero("Dia");
        } while (dia < 0 || dia > 31);

        do {
            mes = (int) ingresarNumero("Mes");
        } while (mes < 0 || mes > 12);
        do {
            anio = (int) ingresarNumero("Anio");
        } while (anio < 2000);

        return LocalDate.of(anio, mes, dia);

    }

    public static float ingresarNumero(String mensaje) {
        boolean seguir = true;
        float numero = 0;
        do {
            try {
                System.out.println("Ingrese " + mensaje);
                numero = Float.parseFloat(scanner.nextLine());
                seguir = false;

            } catch (InputMismatchException | NumberFormatException error) {
                // scanner.nextLine();
            }
        } while (seguir);
        return numero;
    }

    public static String ingresarString(String mensaje) {
        boolean seguir = true;
        StringBuffer resultado = new StringBuffer("");
        do {
            try {
                System.out.println("Ingrese " + mensaje);
                String in = scanner.nextLine();
                resultado.append(in);

                seguir = false;

            } catch (InputMismatchException error) {
                scanner.nextLine();
            }

        } while (seguir);

        return resultado.toString();
    }

    public static boolean ingresarBoolean(String mensaje) {
        boolean seguir = true;
        int eleccion;
        do {
            try {
                System.out.println("Ingrese " + mensaje);
                System.out.println("1. True");
                System.out.println("2. False");
                eleccion = scanner.nextInt();
                scanner.nextLine();

                switch (eleccion) {
                    case 1:
                        return true;
                    case 2:
                        return false;
                    default:
                        ;
                }

            } catch (InputMismatchException error) {
                scanner.nextLine();
            }

        } while (seguir);
        return false;
    }

    public static CamposBebida ingresarBebida() {
        System.out.println("Ingresar datos de la Bebida");
        boolean inputAlcoholica = false;
        boolean inputGrado = false;

        boolean alcoholica = false;
        float gradoAlc = 0.0f;

        boolean seguir = true;
        int eleccion;
        do {
            try {

                if (!inputAlcoholica && !inputGrado) {
                    System.out.println("1.Ingresar si es Alcoholica");
                }
                if (!inputGrado) {
                    System.out.println("2.Ingresar Grado de alcohol");
                }
                if (!inputAlcoholica || !inputGrado) {
                    System.out.println("3.Dejar el resto de datos por defecto y continuar");
                    eleccion = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    eleccion = 3;
                }
                switch (eleccion) {
                    case 1:
                        alcoholica = ingresarBoolean("Alcoholica");
                        inputAlcoholica = true;
                        break;
                    case 2:
                        gradoAlc = ingresarNumero("Grado de Alcohol");
                        inputGrado = true;
                        seguir = false;
                        break;
                    case 3:
                        seguir = false;
                        break;
                    default:
                        ;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida");
                scanner.nextLine();
            }

        } while (seguir);

        if (inputGrado && inputAlcoholica) {
            return new CamposBebida(alcoholica, gradoAlc);
        } else if (inputGrado) {
            return new CamposBebida(true, gradoAlc);
        } else {
            return new CamposBebida();
        }
    }

    public static CamposComida ingresarComida() {

        boolean inputImportado = false;
        boolean inputCalorias = false;
        boolean inputFecha = false;

        boolean importado = false;
        float calorias = 0;

        System.out.println("Ingresar datos del comestible");
        boolean seguir = true;
        int eleccion;
        do {
            try {

                if (!inputImportado) {
                    System.out.println("1.Ingresar Importado");
                }
                if (!inputCalorias) {
                    System.out.println("2.Ingresar Calorias");
                }
                if (!inputFecha) {
                    System.out.println("3.Ingresar Fecha de Vencimiento");
                }
                if (!inputImportado || !inputCalorias || !inputFecha) {
                    System.out.println("4.Dejar el resto de datos por defecto y continuar");
                    eleccion = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    eleccion = 4;
                }

                switch (eleccion) {
                    case 1:
                        importado = ingresarBoolean("Importado");
                        inputImportado = true;
                        break;
                    case 2:
                        calorias = ingresarNumero("Calorias");
                        inputCalorias = true;
                        break;
                    case 3:
                        System.out.println("Se le pedira la fecha luego");
                        inputFecha = true;
                        break;
                    case 4:
                        seguir = false;
                        break;
                    default:
                        ;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida");
                scanner.nextLine();
            }

        } while (seguir);

        if (inputFecha) {
            return new CamposComida(importado, calorias, ingresarFecha());
        } else {
            return new CamposComida(importado, calorias);
        }

    }

    public static Aplicado ingresarAplicado() {
        boolean seguir = true;
        int eleccion;
        do {
            try {
                System.out.println("Seleccione Aplicado ");
                System.out.println("1. Cocina");
                System.out.println("2. Pisos");
                System.out.println("3. Ropa");
                System.out.println("4. Multi Uso");
                eleccion = scanner.nextInt();
                scanner.nextLine();

                switch (eleccion) {
                    case 1:
                        return Aplicado.COCINA;
                    case 2:
                        return Aplicado.PISOS;
                    case 3:
                        return Aplicado.ROPA;
                    case 4:
                        return Aplicado.MULTIUSO;
                    default:
                        ;
                }

            } catch (InputMismatchException error) {
                scanner.nextLine();
            }

        } while (seguir);
        return Aplicado.MULTIUSO;
    }

    public static Envase ingresarEnvase() {
        boolean seguir = true;
        int eleccion;
        do {
            try {
                System.out.println("Seleccione Envase");
                System.out.println("1. Plastico");
                System.out.println("2. Vidrio");
                System.out.println("3. Lata");
                eleccion = scanner.nextInt();
                scanner.nextLine();

                switch (eleccion) {
                    case 1:
                        return Envase.PLASTICO;
                    case 2:
                        return Envase.VIDRIO;
                    case 3:
                        return Envase.LATA;
                    default:
                        ;
                }

            } catch (InputMismatchException error) {
                scanner.nextLine();
            }

        } while (seguir);
        return Envase.PLASTICO;
    }
}
