package com.mart.tienda.modelos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Tienda {
    private String nombre;
    private int maxStock;
    private float saldo;
    private HashMap<String, ArrayList<Producto>> productos;

    public Tienda() {
        this.nombre = "";
        this.maxStock = 10;
        this.saldo = 0;
        this.productos = new HashMap<>();
    }

    public Tienda(String nombre, int maxStock, float saldo) {
        this.nombre = nombre;
        this.maxStock = maxStock;
        this.saldo = saldo;
        this.productos = new HashMap<>();
    }

    public void agregarProducto(Producto p) {
        float stock = p.getStock();
        float costoTotal = p.getCostoUnitario() * stock;

        if (this.saldo < costoTotal) {
            System.out.println("El producto " + p.getNombre()
                    + " no podrá ser agregado a la tienda por saldo insuficiente en la caja");
        } else if (this.getStock() + stock > maxStock) {
            System.out.println("El producto " + p.getNombre()
                    + " no podrá ser agregado a la tienda por que supera el stock maximo");
        } else {
            this.saldo -= costoTotal;
            if (p instanceof Bebida) {
                this.agregarBebida((Bebida) p);
            } else if (p instanceof Envasado) {
                this.agregarEnvasado((Envasado) p);
            } else if (p instanceof PLimpieza) {
                this.agregarPLimpieza((PLimpieza) p);
            } else {
                System.out.println("producto invalido");
            }
        }
    }

    public void mostrarProductos() {
        if (!this.getBebidas().isEmpty()) {
            System.out.println("Bebidas(" + this.getBebidas().size() + ")");
            for (Bebida b : this.getBebidas()) {
                System.out.printf("%s %s Disponible: %s Precio: %s Stock: %s%n", b.getId(), b.getNombre(),
                        b.isDisponible(),
                        b.getPrecioUnitario(), b.getStock());
            }
        }
        if (!this.getEnvasados().isEmpty()) {
            System.out.println("Envasados(" + this.getEnvasados().size() + ")");
            for (Envasado e : this.getEnvasados()) {
                System.out.printf("%s %s Disponible: %s Precio: %s Stock: %s%n", e.getId(), e.getNombre(),
                        e.isDisponible(),
                        e.getPrecioUnitario(), e.getStock());
            }
        }
        if (!this.getPLimpieza().isEmpty()) {
            System.out.println("Productos de Limpieza(" + this.getPLimpieza().size() + ")");
            for (PLimpieza p : this.getPLimpieza()) {
                System.out.printf("%s %s Disponible: %s Precio: %s Stock: %s%n", p.getId(), p.getNombre(),
                        p.isDisponible(),
                        p.getPrecioUnitario(), p.getStock());
            }
        }
    }

    public void mostrarProductosDisponibles() {
        if (!this.getBebidas().isEmpty()) {
            System.out.println("Bebidas(" + this.getBebidas().size() + ")");
            for (Bebida b : this.getBebidas()) {
                if (b.isDisponible()) {
                    System.out.printf("%s %s Precio: %s Stock: %s%n", b.getId(), b.getNombre(), b.getPrecioUnitario(),
                            b.getStock());
                }
            }
        }
        if (!this.getEnvasados().isEmpty()) {
            System.out.println("Envasados(" + this.getEnvasados().size() + ")");
            for (Envasado e : this.getEnvasados()) {
                System.out.printf("%s %s Disponible: %s Precio: %s Stock: %s%n", e.getId(), e.getNombre(),
                        e.isDisponible(),
                        e.getPrecioUnitario(), e.getStock());
            }
        }
        if (!this.getPLimpieza().isEmpty()) {
            System.out.println("Productos de Limpieza(" + this.getPLimpieza().size() + ")");
            for (PLimpieza p : this.getPLimpieza()) {
                System.out.printf("%s %s Disponible: %s Precio: %s Stock: %s%n", p.getId(), p.getNombre(),
                        p.isDisponible(),
                        p.getPrecioUnitario(), p.getStock());
            }
        }
    }

    public void venderProductos(String[] venta) {
        float costo = 0;
        for (int i = 0; i < venta.length; i += 2) {
            Producto p = buscarPorId(venta[i]);
            int cantidad = Integer.parseInt(venta[i + 1]);
            int stock;
            String id = venta[i];
            float precio;
            if (p instanceof Bebida) {
                Bebida producto = (Bebida) p;
                precio = producto.getPrecioUnitario();
                stock = producto.getStock();
            } else if (p instanceof Envasado) {
                Envasado producto = (Envasado) p;
                precio = producto.getPrecioUnitario();
                stock = producto.getStock();
            } else {
                PLimpieza producto = (PLimpieza) p;
                precio = producto.getPrecioUnitario();
                stock = producto.getStock();
            }

            if (cantidad > stock) {
                System.out.println("Hay productos con stock disponible menor al solicitado.");
                cantidad = stock;
            }

            System.out.printf("%s %s %s x %s%n", id, p.getNombre(), cantidad, precio);
            costo += cantidad * precio;
            this.setSaldo(this.getSaldo() + costo);
        }
        System.out.println("TOTAL VENTA: " + costo);
    }

    public Producto buscarPorId(String id) {
        String iniciales = id.substring(0, 2);
        Producto producto;
        // envasado
        if (iniciales.equals("AB")) {
            producto = this.getEnvasados().stream().filter(e -> e.getId().equals(id)).findFirst().get();

            // bebida
        } else if (iniciales.equals("AC")) {
            producto = this.getBebidas().stream().filter(e -> e.getId().equals(id)).findFirst().get();

        } else {// if(iniciales.equals("AZ")){
            producto = this.getPLimpieza().stream().filter(e -> e.getId().equals(id)).findFirst().get();
        }

        return producto;

    }

    // bebidas
    public void agregarBebida(Bebida bebida) {
        if (!this.productos.containsKey("Bebidas")) {
            this.productos.put("Bebidas", new ArrayList<Producto>());
        }
        this.productos.get("Bebidas").add(bebida);
        System.out.println("Producto agregado");
        bebida.stats();
    }

    public ArrayList<Bebida> getBebidas() {
        ArrayList<Bebida> list = new ArrayList<Bebida>();
        if (this.productos.containsKey("Bebidas")) {
            for (Producto i : this.productos.get("Bebidas")) {
                // casting de Producto a Bebida
                list.add((Bebida) i);
            }
        }

        return list;
    }

    // Envasados
    public void agregarEnvasado(Envasado envasado) {
        if (!this.productos.containsKey("Envasados")) {
            this.productos.put("Envasados", new ArrayList<Producto>());
        }
        this.productos.get("Envasados").add(envasado);
        System.out.println("Producto agregado");
        envasado.stats();
    }

    public ArrayList<Envasado> getEnvasados() {
        ArrayList<Envasado> list = new ArrayList<Envasado>();
        if (this.productos.containsKey("Envasados")) {
            for (Producto i : this.productos.get("Envasados")) {
                // casting de Producto a Envasado
                list.add((Envasado) i);
            }
        }

        return list;
    }

    // Producos de Limpieza
    public void agregarPLimpieza(PLimpieza plimpieza) {
        if (!this.productos.containsKey("Plimpieza")) {
            this.productos.put("Plimpieza", new ArrayList<Producto>());
        }
        this.productos.get("Plimpieza").add(plimpieza);
        System.out.println("Producto agregado");
        plimpieza.stats();
    }

    public ArrayList<PLimpieza> getPLimpieza() {
        ArrayList<PLimpieza> list = new ArrayList<PLimpieza>();
        if (this.productos.containsKey("Plimpieza")) {
            for (Producto i : this.productos.get("Plimpieza")) {
                // casting de Producto a PLimpieza
                list.add((PLimpieza) i);
            }
        }

        return list;
    }

    // uso de Streams para sumar el total de cada ArrayList
    public int getStock() {
        int sumaBebidas = this.getBebidas().stream().mapToInt(b -> b.getStock()).sum();
        int sumaEnvasados = this.getEnvasados().stream().mapToInt(e -> e.getStock()).sum();
        int sumaPLimpieza = this.getPLimpieza().stream().mapToInt(p -> p.getStock()).sum();
        return sumaBebidas + sumaEnvasados + sumaPLimpieza;

    }

    // getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public HashMap<String, ArrayList<Producto>> getProductos() {
        return productos;
    }

    public void setProductos(HashMap<String, ArrayList<Producto>> productos) {
        this.productos = productos;
    }

    public void mostrarSaldoStock() {
        System.out.println("Saldo Disponible: " + this.getSaldo());
        System.out.println("Capacidad de Stock: " + this.getMaxStock());
        System.out.println("Stock ocupado: " + this.getStock());
        System.out.println("Stock disponible: " + (this.getMaxStock() - this.getStock()));

    }

    // funciones de busqueda
    public void obtenerComestiblesConMenorDescuento(float descuento) {
        ArrayList<Producto> list = new ArrayList<Producto>();

        for (Bebida b : this.getBebidas().stream().filter(b -> !b.getCamposComida().isImportado()).toList()) {
            list.add((Producto) b);
        }
        for (Envasado b : this.getEnvasados().stream().filter(b -> !b.getCamposComida().isImportado()).toList()) {
            list.add((Producto) b);
        }

        for (Producto p : list.stream().sorted(Comparator.comparingDouble(Producto::getDescuento))
                .collect(Collectors.toList())) {
            System.out.println(p.getNombre() + " " + p.getDescuento());
        }

    }

    public void listarProductosConUtilidadesInferiores(float ganancia) {
        ArrayList<Producto> list = new ArrayList<Producto>();
        for (Bebida b : this.getBebidas()) {
            list.add((Producto) b);
        }
        for (Envasado b : this.getEnvasados()) {
            list.add((Producto) b);
        }
        for (PLimpieza b : this.getPLimpieza()) {
            list.add((Producto) b);
        }

        List<Producto> filtro = list.stream()
                .filter(p -> ((p.getPrecioUnitario() - p.getCostoUnitario()) - 1) < ganancia).toList();

        for (Producto p : filtro) {
            System.out.println(p.getNombre() + " Stock: " + p.getStock());
        }

    }

}
