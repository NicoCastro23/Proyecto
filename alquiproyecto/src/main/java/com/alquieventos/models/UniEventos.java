package com.alquieventos.models;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import javax.mail.MessagingException;

import com.alquieventos.services.EmailService;
import com.alquieventos.services.LogginService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class UniEventos implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Cliente> clientes = new ArrayList<>();
    private List<Evento> eventos = new ArrayList<>();
    private List<Factura> facturas = new ArrayList<>();
    private Map<String, Double> cupones = new HashMap<>();
    private User usuarioActual;
    private Administrador administrador;
    private transient EmailService emailService;

    public UniEventos() {
        administrador = Administrador.getInstance();
        emailService = new EmailService("uqunieventos@gmail.com", "ynsf wlno spjm vxad");
        cargarDatos();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Map<String, Double> getCupones() {
        return cupones;
    }

    public void setCupones(Map<String, Double> cupones) {
        this.cupones = cupones;
    }

    public User getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(User usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendVerificationCode(String email, String codigo) {
        try {
            emailService.sendEmail(email, "Código de Verificación", "Su código de verificación es: " + codigo);
            System.out.println("Correo de verificación enviado a " + email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendDiscountCode(String email, String codigo, int porcentaje) {
        try {
            emailService.sendEmail(email, "Código de Descuento",
                    "Su código de descuento del " + porcentaje + "% es: " + codigo);
            System.out.println("Correo de descuento enviado a " + email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void registerClient(String identificacion, String nombre, String telefono, String email, String contrasena) {
        if (buscarClientePorIdentificacion(identificacion) != null) {
            System.out.println("El cliente con identificación " + identificacion + " ya está registrado.");
            return;
        }

        Cliente cliente = new Cliente(identificacion, nombre, telefono, email, contrasena);
        clientes.add(cliente);
        System.out.println("Cliente registrado: " + nombre);

        sendVerificationCode(email, cliente.getVerificationCode());
        // Enviar correo de verificación y código de descuento del 15%
        String codigoDescuento = GeneradorCodigo.generarCodigo();
        cliente.agregarCodigoDescuento(codigoDescuento, 0.15);
        sendDiscountCode(email, codigoDescuento, 15);
        guardarDatos();
    }

    public void agregarEvento(Evento evento) {
        if (buscarEventoPorNombreCiudadFecha(evento.getNombre(), evento.getCiudad(), evento.getFecha()) != null) {
            System.out.println("El evento " + evento.getNombre() + " en " + evento.getCiudad() + " en la fecha "
                    + evento.getFecha() + " ya está registrado.");
            return;
        }
        eventos.add(evento);
        System.out.println("Evento agregado: " + evento.getNombre() + " en " + evento.getCiudad());
        guardarDatos();
    }

    public void listarEventosPorCiudad(String ciudad) {
        System.out.println("Eventos en " + ciudad + ":");
        for (Evento evento : eventos) {
            if (evento.getCiudad().equals(ciudad)) {
                System.out.println(evento.getNombre() + " - " + evento.getFecha());
            }
        }
    }

    public boolean createAccount(String id, String nombre, String telefono, String email,
            String contrasena) {
        if (buscarClientePorIdentificacion(id) != null) {
            System.out.println("El cliente con identificación " + id + " ya está registrado.");
            return false;
        }
        registerClient(id, nombre, telefono, email, contrasena);
        return true;
    }

    public boolean login(String identificacion, String contrasena) {
        if (identificacion.equals(administrador.getEmail()) && contrasena.equals(administrador.getPassword())) {
            usuarioActual = administrador;
            System.out.println("Login exitoso. Bienvenido, Administrador!");
            return true;
        }

        Cliente cliente = buscarClientePorIdentificacion(identificacion);
        if (cliente != null && cliente.getPassword().equals(contrasena)) {
            if (!cliente.isVerified()) {
                System.out.println("Debe verificar su cuenta ingresando el código de verificación.");
                return false;
            }
            usuarioActual = cliente;
            System.out.println("Login exitoso. Bienvenido, " + cliente.getName() + "!");
            return true;
        }

        System.out.println("Identificación o contraseña incorrecta.");
        return false;
    }

    public void logout() {
        if (usuarioActual != null) {
            System.out.println("Hasta luego, " + usuarioActual.getName() + "!");
            usuarioActual = null;
        } else {
            System.out.println("No hay usuario logueado.");
        }
    }

    public boolean verificarCodigo(String identificacion, String codigo) {
        Cliente cliente = buscarClientePorIdentificacion(identificacion);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return false;
        }

        String storedCode = cliente.getVerificationCode();
        System.out.println("Código almacenado: " + storedCode); // Debugging
        System.out.println("Código ingresado: " + codigo); // Debugging

        if (storedCode != null && storedCode.equals(codigo)) {
            cliente.setVerified(true);
            System.out.println("Cuenta verificada exitosamente.");
            guardarDatos();
            return true;
        } else {
            System.out.println("Código de verificación incorrecto.Intenta de nuevo");
            return false;
        }
    }

    public Cliente buscarClientePorIdentificacion(String identificacion) {
        if (identificacion == null) {
            System.out.println("La identificación proporcionada es nula.");
            return null;
        }

        System.out.println("Buscando cliente con identificación: " + identificacion);

        for (Cliente cliente : clientes) {
            System.out.println("Comparando con cliente: " + cliente.getIdentificacion());
            if (identificacion.equals(cliente.getIdentificacion())) {
                System.out.println("Cliente encontrado: " + cliente.getName());
                return cliente;
            }
        }

        System.out.println("Cliente con identificación " + identificacion + " no encontrado.");
        return null;
    }

    public Evento buscarEventoPorNombreCiudadFecha(String nombre, String ciudad, LocalDate fecha) {
        for (Evento evento : eventos) {
            if (evento.getNombre().equals(nombre) && evento.getCiudad().equals(ciudad)
                    && evento.getFecha().equals(fecha)) {
                return evento;
            }
        }
        return null;
    }

    public void iniciarAplicacion() {
        Scanner scanner = new Scanner(System.in);
        LogginService logginService = new LogginService(this);

        while (true) {
            System.out.println("1. Crear cuenta");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Verificar cuenta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcion == 1) {
                System.out.print("Identificación: ");
                String identificacion = scanner.nextLine();
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Teléfono: ");
                String telefono = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Contraseña: ");
                String contrasena = scanner.nextLine();
                if (createAccount(identificacion, nombre, telefono, email, contrasena)) {
                    System.out.println(
                            "Cuenta creada exitosamente. Verifique su correo electrónico para activar su cuenta y recibir su cupón de descuento.");
                } else {
                    System.out.println("No se pudo crear la cuenta. Identificación ya registrada.");
                }
            } else if (opcion == 2) {
                System.out.print("Identificación: ");
                String identificacion = scanner.nextLine();
                System.out.print("Contraseña: ");
                String contrasena = scanner.nextLine();
                if (logginService.login(identificacion, contrasena)) {
                    System.out.println("Acceso concedido. Puede usar el programa.");
                    menuCompraEntradas(scanner, this);
                } else {
                    System.out.println("Identificación o contraseña incorrecta, o cuenta no verificada.");
                }
            } else if (opcion == 3) {
                logout();
            } else if (opcion == 4) {
                System.out.print("Identificación: ");
                String identificacion = scanner.nextLine();
                System.out.print("Código de verificación: ");
                String codigo = scanner.nextLine();
                if (verificarCodigo(identificacion, codigo)) {
                    System.out.println("Cuenta verificada exitosamente. Ahora puede iniciar sesión.");
                } else {
                    System.out.println("Código de verificación incorrecto.");
                }
            } else if (opcion == 5) {
                System.out.println("Saliendo del programa...");
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }

        scanner.close();
        guardarDatos(); // Guardar datos al salir
    }

    public void guardarDatos() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("unieventos.dat"))) {
            out.writeObject(this);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarDatos() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("unieventos.dat"))) {
            UniEventos uniEventos = (UniEventos) in.readObject();
            this.clientes = uniEventos.getClientes();
            this.eventos = uniEventos.getEventos();
            this.facturas = uniEventos.getFacturas();
            this.cupones = uniEventos.getCupones();
            this.usuarioActual = null; // Asegurar que no se mantiene la sesión de usuario previa
            System.out.println("Datos cargados correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de datos. Se creará uno nuevo al guardar.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void menuCompraEntradas(Scanner scanner, UniEventos uniEventos) {
        Cliente clienteActual = (Cliente) uniEventos.usuarioActual;

        System.out.println("Buscar eventos:");
        System.out.println("1. Buscar por nombre");
        System.out.println("2. Buscar por tipo de evento");
        System.out.println("3. Buscar por ciudad");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        List<Evento> eventosEncontrados = new ArrayList<>();

        if (opcion == 1) {
            System.out.print("Ingrese el nombre del evento: ");
            String nombre = scanner.nextLine();
            eventosEncontrados = buscarEventoPorNombre(uniEventos, nombre);
        } else if (opcion == 2) {
            System.out
                    .print("Ingrese el tipo de evento (CONCIERTO, TEATRO, DEPORTE, FESTIVAL, CULTURAL, EMPRESARIAL): ");
            TipoEvento tipo = TipoEvento.valueOf(scanner.nextLine().toUpperCase());
            eventosEncontrados = buscarEventoPorTipo(uniEventos, tipo);
        } else if (opcion == 3) {
            System.out.print("Ingrese la ciudad del evento: ");
            String ciudad = scanner.nextLine();
            eventosEncontrados = buscarEventoPorCiudad(uniEventos, ciudad);
        } else if (opcion == 4) {
            return;
        } else {
            System.out.println("Opción no válida.");
            return;
        }

        if (eventosEncontrados.isEmpty()) {
            System.out.println("No se encontraron eventos.");
            return;
        }

        System.out.println("Seleccione el evento para comprar entradas:");
        for (int i = 0; i < eventosEncontrados.size(); i++) {
            System.out.println((i + 1) + ". " + eventosEncontrados.get(i).getNombre());
        }
        int indiceEvento = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir el salto de línea

        if (indiceEvento < 0 || indiceEvento >= eventosEncontrados.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        Evento eventoSeleccionado = eventosEncontrados.get(indiceEvento);
        System.out.println("Localidades disponibles:");
        for (int i = 0; i < eventoSeleccionado.getLocalidades().size(); i++) {
            Localidad localidad = eventoSeleccionado.getLocalidades().get(i);
            System.out.println((i + 1) + ". " + localidad.getNombre() + " - Capacidad: "
                    + localidad.getCapacidadInicial() + " - Precio: " + localidad.getPrecio());
        }
        int indiceLocalidad = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir el salto de línea

        if (indiceLocalidad < 0 || indiceLocalidad >= eventoSeleccionado.getLocalidades().size()) {
            System.out.println("Selección no válida.");
            return;
        }

        Localidad localidadSeleccionada = eventoSeleccionado.getLocalidades().get(indiceLocalidad);
        System.out.print("Ingrese la cantidad de entradas a comprar: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        if (cantidad <= 0 || cantidad > localidadSeleccionada.getCapacidadInicial()) {
            System.out.println("Cantidad no válida o insuficiente disponibilidad.");
            return;
        }

        double subtotal = localidadSeleccionada.getPrecio() * cantidad;
        double total = subtotal;
        System.out.println("Total antes de descuentos: " + total);

        while (true) {
            System.out.print("¿Desea aplicar un código de descuento? (si/no): ");
            if (scanner.nextLine().equalsIgnoreCase("si")) {
                System.out.print("Ingrese el código de descuento: ");
                String codigoDescuento = scanner.nextLine();
                if (clienteActual.validarCodigoDescuento(codigoDescuento)) {
                    total = subtotal * (1 - clienteActual.obtenerPorcentajeDescuento(codigoDescuento));
                    clienteActual.eliminarCodigoDescuento(codigoDescuento);
                    System.out.println("Código de descuento aplicado. Total actualizado: " + total);
                } else {
                    System.out.println("Código de descuento no válido.");
                }
            } else {
                break;
            }
        }

        System.out.println("Total a pagar: " + total);
        System.out.print("¿Desea realizar la compra? (si/no): ");
        if (scanner.nextLine().equalsIgnoreCase("si")) {
            localidadSeleccionada.setCapacidadInicial(localidadSeleccionada.getCapacidadInicial() - cantidad);
            System.out.println("Compra realizada para el evento: " + eventoSeleccionado.getNombre()
                    + " en la localidad: " + localidadSeleccionada.getNombre());

            Factura factura = new Factura(subtotal, total, clienteActual, eventoSeleccionado, localidadSeleccionada,
                    true, UUID.randomUUID().toString());
            facturas.add(factura);

            try {
                generarCodigoQR(factura);
                enviarEmailCompra(clienteActual.getEmail(), factura);
            } catch (WriterException | IOException | MessagingException e) {
                e.printStackTrace();
            }

            guardarDatos(); // Guardar datos de la compra

            // Enviar nuevo código de descuento del 10% después de la primera compra
            if (!clienteActual.isCompra()) {
                String nuevoCodigoDescuento = GeneradorCodigo.generarCodigo();
                clienteActual.agregarCodigoDescuento(nuevoCodigoDescuento, 0.10);
                sendDiscountCode(clienteActual.getEmail(), nuevoCodigoDescuento, 10);
                clienteActual.setCompra(true);
            }
        } else {
            System.out.println("Compra cancelada.");
        }
    }

    private void generarCodigoQR(Factura factura) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(factura.getCodigo(), BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();

        try (FileOutputStream fos = new FileOutputStream("factura_" + factura.getCodigo() + ".png")) {
            fos.write(pngData);
        }
    }

    private void enviarEmailCompra(String email, Factura factura) throws MessagingException {
        String contenido = "Detalles de la compra:\n" +
                "Evento: " + factura.getEvento().getNombre() + "\n" +
                "Localidad: " + factura.getLocalidad().getNombre() + "\n" +
                "Subtotal: " + factura.getSubtotal() + "\n" +
                "Total: " + factura.getTotal() + "\n" +
                "Fecha: " + factura.getFecha() + "\n" +
                "Código de la factura: " + factura.getCodigo();

        emailService.sendEmail(email, "Detalles de su compra", contenido);
        System.out.println("Correo de compra enviado a " + email);
    }

    private static List<Evento> buscarEventoPorNombre(UniEventos uniEventos, String nombre) {
        List<Evento> eventosEncontrados = new ArrayList<>();
        for (Evento evento : uniEventos.getEventos()) {
            if (evento.getNombre().equalsIgnoreCase(nombre)) {
                imprimirDetallesEvento(evento);
                eventosEncontrados.add(evento);
            }
        }
        if (eventosEncontrados.isEmpty()) {
            System.out.println("No se encontró ningún evento con el nombre: " + nombre);
        }
        return eventosEncontrados;
    }

    private static List<Evento> buscarEventoPorTipo(UniEventos uniEventos, TipoEvento tipo) {
        List<Evento> eventosEncontrados = new ArrayList<>();
        for (Evento evento : uniEventos.getEventos()) {
            if (evento.getTipo() == tipo) {
                imprimirDetallesEvento(evento);
                eventosEncontrados.add(evento);
            }
        }
        if (eventosEncontrados.isEmpty()) {
            System.out.println("No se encontró ningún evento del tipo: " + tipo);
        }
        return eventosEncontrados;
    }

    private static List<Evento> buscarEventoPorCiudad(UniEventos uniEventos, String ciudad) {
        List<Evento> eventosEncontrados = new ArrayList<>();
        for (Evento evento : uniEventos.getEventos()) {
            if (evento.getCiudad().equalsIgnoreCase(ciudad)) {
                imprimirDetallesEvento(evento);
                eventosEncontrados.add(evento);
            }
        }
        if (eventosEncontrados.isEmpty()) {
            System.out.println("No se encontró ningún evento en la ciudad: " + ciudad);
        }
        return eventosEncontrados;
    }

    private static void imprimirDetallesEvento(Evento evento) {
        System.out.println("Nombre: " + evento.getNombre());
        System.out.println("Ciudad: " + evento.getCiudad());
        System.out.println("Descripción: " + evento.getDescripcion());
        System.out.println("Tipo: " + evento.getTipo());
        System.out.println("Fecha: " + evento.getFecha());
        System.out.println("Localidades: ");
        for (Localidad localidad : evento.getLocalidades()) {
            System.out.println("  Nombre: " + localidad.getNombre());
            System.out.println("  Capacidad: " + localidad.getCapacidadInicial());
            System.out.println("  Precio: " + localidad.getPrecio());
        }
        System.out.println();
    }

    // Método para crear cupones
    public void crearCupon(String codigo) {
        cupones.put(codigo, 0.10);
        guardarDatos();
    }

    public boolean validarCupon(String codigo) {
        return cupones.containsKey(codigo);
    }

    public double obtenerPorcentajeDescuentoCupon(String codigo) {
        return cupones.getOrDefault(codigo, 0.0);
    }

    public void eliminarCupon(String codigo) {
        cupones.remove(codigo);
        guardarDatos();
    }

    // Métodos para obtener las compras de un cliente
    public List<Factura> obtenerComprasCliente(String identificacionCliente) {
        List<Factura> comprasCliente = new ArrayList<>();
        for (Factura factura : facturas) {
            if (factura.getCliente().getIdentificacion().equals(identificacionCliente)) {
                comprasCliente.add(factura);
            }
        }
        return comprasCliente;
    }

    // Métodos para obtener estadísticas de eventos
    public Map<String, Map<String, Object>> obtenerEstadisticasEventos() {
        Map<String, Map<String, Object>> estadisticas = new HashMap<>();
        for (Evento evento : eventos) {
            Map<String, Object> datosEvento = new HashMap<>();
            double totalGanado = 0;
            Map<String, Double> porcentajeVendidoPorLocalidad = new HashMap<>();
            for (Localidad localidad : evento.getLocalidades()) {
                int capacidadInicial = localidad.getCapacidadInicial();
                int capacidadRestante = localidad.getCapacidadInicial();
                double porcentajeVendido = ((double) (capacidadInicial - capacidadRestante) / capacidadInicial) * 100;
                porcentajeVendidoPorLocalidad.put(localidad.getNombre(), porcentajeVendido);
                totalGanado += (capacidadInicial - capacidadRestante) * localidad.getPrecio();
            }
            datosEvento.put("porcentajeVendidoPorLocalidad", porcentajeVendidoPorLocalidad);
            datosEvento.put("totalGanado", totalGanado);
            estadisticas.put(evento.getNombre(), datosEvento);
        }
        return estadisticas;
    }

}
