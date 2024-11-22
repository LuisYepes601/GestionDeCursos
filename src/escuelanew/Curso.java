package escuelanew;

import java.awt.HeadlessException;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

public class Curso {

    private List<Estudiante> listas;
    private List<Profesor> Profesores;
    private String name;
    private int codigo;

    public Curso(String name) {
        this.name = name;
        this.listas = new ArrayList<>();
        this.Profesores = new ArrayList<>();
        this.codigo = (int) (Math.random() * 100) + 1;

    }

    public Curso() {
    }

    public List<Estudiante> getLista() {
        return listas;
    }

    public void setLista(List<Estudiante> lista) {
        this.listas = lista;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Profesor> getProfesores() {
        return Profesores;
    }

    public void setProfesores(List<Profesor> Profesores) {
        this.Profesores = Profesores;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String validarDatosEntrada(String campo, String regex, String mensajeError) {
        String dato;
        do {
            dato = JOptionPane.showInputDialog(campo);

            if (dato == null) {
                break;
            }

            if (!dato.matches(regex)) {
                JOptionPane.showMessageDialog(null, mensajeError);

            }

        } while (!dato.matches(regex));

        return dato;
    }

    public int validarDatos(String campo, String mensajeError) {

        boolean valido = true;

        int diaParse = 0;

        while (valido) {

            try {
                String dia = JOptionPane.showInputDialog(campo);
                if (dia == null) {
                    break;
                }
                diaParse = Integer.parseInt(dia);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, mensajeError);
            }

        }

        return diaParse;

    }

    public void addCurso(ArrayList<Curso> cursos) {

        String name = validarDatosEntrada("Nombre", "[a-zA-Z _-]{4,9}", "Error el nombre del curso no cumple con el minimo de caracteres.").toLowerCase();
        if (name == null) {
            return;
        }

        Curso cursoActual = new Curso(name);
        cursos.add(cursoActual);
        JOptionPane.showMessageDialog(null, "Curso " + name.toUpperCase() + " agregado con éxito");
        //System.out.println("Curso " + name + " agregado con éxito.");
        cursoActual.addProfesor();
        // System.out.println("Quieres agregar estudiantes a este curso? (si/no)");
        String resp = JOptionPane.showInputDialog("¿Quieres agregar estudiantes a este curso? (si/no)");
        if (resp == null) {
            return;
        }
        while (resp.toLowerCase().equalsIgnoreCase("si")) {
            cursoActual.addEstudiantes();
            // System.out.println("Quieres agregar estudiantes a este curso? (si/no)");
            resp = JOptionPane.showInputDialog("¿Quieres agregar estudiantes a este curso? (si/no)");
            if (resp == null) {
                return;
            }

        }
        //System.out.println("\n----------------------------------------------\n");
        JOptionPane.showMessageDialog(null, "\n----------------------------------------------\n" + " Datos de los estudiante ingresados" + cursoActual.mostrarEstudiantes() + "\n");
        //System.out.println("Datos de los estudiante ingresados");

    }

    public void addEstudiantes() {
        String dni;
        String nombre;
        int cont = 1;
        int diaParse;
        //System.out.println("INGRESA LOS DATOS DE EL ESTUDIANTE ");

        nombre = validarDatosEntrada("Nombre", "^^[A-Za-zÀ-ÿ\\s\\W]{1,15}$", "Nombre no válido necesitas igresar minimo un caracter. No se Admiten números ni simbolos");

        if (nombre == null) {
            return;
        }

        String apellido = validarDatosEntrada("Apellido", "^[A-Za-zÀ-ÿ\\s\\W]{1,15}$", "Apellido no válido necesitas igresar minimo un caracter. No se Admiten números ni simbolos");

        if (apellido == null) {
            return;
        }

        JOptionPane.showMessageDialog(null, "FECHA DE NACIMIENTO");

        diaParse = validarDatos("Dia", "Introduzca un valor númerico.");

        int mes = validarDatos("Mes", "Introduzca un valor númerico.");
        int anio = validarDatos("Año", "Introduzca un valor númerico.");
        listas.add(new Estudiante(nombre, apellido, diaParse, mes, anio));

    }

    public void addProfesor() {
        boolean dniValido = false;
        int cont = 1;
        double dninew = 0;
        String nombre = null;

        JOptionPane.showMessageDialog(null, "INGRESA LOS DATOS DE EL PROFESOR");
        //System.out.println("DNI: ");

        nombre = JOptionPane.showInputDialog("Nombre");
        if (nombre == null) {
            return;
        }

        while (!nombre.matches("[a-zA-Z _-]{4,9}")) {
            nombre = JOptionPane.showInputDialog("Nombre invalido. Minimo 5 caracteres");
            if (nombre == null) {
                return;
            }
        }

        Profesor profersorActual = new Profesor(nombre);
        Profesores.add(profersorActual);
    }

    public String mostrarProfesor() {
        StringBuilder sb = new StringBuilder();
        for (Profesor elem : Profesores) {
            sb.append("Nombre del profesor : ").append(elem.getName().toUpperCase()).append("\n").append("Código: " + elem.getId());
        }
        return sb.toString();
    }

    public String mostrarEstudiantes() {
        int cont = 0;
        StringBuilder mensaje = new StringBuilder(); // Usar StringBuilder para concatenar
        for (Estudiante elem : listas) {
            mensaje.append("\n-----------------------------------------------"
                    + "-------------------------------------------------------------------------------"
                    + "-----------------------------------").append("\nDatos del estudiante ").append(cont + 1).append(" | | ")
                    .append("DNI: ").append(elem.getDNI()).append(" | | ")
                    .append("Nombre: ").append(elem.getName()).append(" | | ")
                    .append("Apellido: ").append(elem.getApellido()).append(" | | ")
                    .append("Fecha de nacimiento: || ")
                    .append("Día: ").append(elem.getDia()).append(" | | ")
                    .append("Mes: ").append(elem.getMes()).append(" | | ")
                    .append("Año: ").append(elem.getAnio()).append(""); // Añadir un espacio entre estudiantes
            cont++;
        }

        if (cont == 0) {
            mensaje.append("No hay estudiantes registrados en este curso.");
        }

        return mensaje.toString(); // Convertir StringBuilder a String

    }

    public void eliminarCurso(ArrayList<Curso> lista) {
        /*boolean encontrado = false;
        String name = null;
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay cursos disponibles para eliminar. Le invitamos a agregar nuevos cursos.");

        } else {
            ListIterator<Curso> iterator = lista.listIterator();

            boolean cond = true;

            while (cond) {
                name = JOptionPane.showInputDialog("Ingresa el nombre del curso").toLowerCase();
                if (name == null) {
                    return;
                }

                if (name.matches("^[a-zA-Z ]+$")) {
                    cond = false;
                } else {
                    JOptionPane.showMessageDialog(null, "No se aceptan datos númericos ni espacios vacios.");
                }
            }

            while (iterator.hasNext()) {
                Curso siguiente = iterator.next();
                if (name.equals(siguiente.getName())) {
                    encontrado = true;
                    iterator.remove();
                    JOptionPane.showMessageDialog(null, "Curso eliminado con éxito");
                }

            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Curso no encontrado");
            }
        }*/

        String cod = null;
        boolean encontrado = false;
        int codigo = 0;
        if (!lista.isEmpty()) {
            try {
                ListIterator<Curso> iterator = lista.listIterator();

                boolean cond = true;

                while (cond) {
                    cod = JOptionPane.showInputDialog("Ingresa el código del curso");
                    if (cod == null) {
                        return;
                    }

                    if (cod.matches("^-?\\d+$")) {
                        cond = false;
                        codigo = Integer.parseInt(cod);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se aceptan caracteres ni espacios vacios.");
                    }

                    while (iterator.hasNext()) {
                        Curso siguiente = iterator.next();
                        if (siguiente.getCodigo() == codigo) {
                            encontrado = true;
                            iterator.remove();
                            JOptionPane.showMessageDialog(null, "Curso eliminado con éxito");
                        }

                    }

                }
                if (!encontrado) {
                    JOptionPane.showMessageDialog(null, "Curso no encontrado");
                }
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "No se permiten caracteres");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No hay cursos disponibles para eliminar. Le invitamos a agregar nuevos cursos.");
        }

    }

    public void mostrarCurso(ArrayList<Curso> lista) {
        if (!lista.isEmpty()) {
            int i = 0;
            StringBuilder sb = new StringBuilder();
            sb.append("Resumen de los cursos\n");

            for (Curso elem : lista) {

                sb.append("Nombre del curso ").append(elem.getName().toUpperCase()).append("\n").append("Código del curso: " + elem.getCodigo()).append("\n");
                i++;
            }
            JOptionPane.showMessageDialog(null, sb.toString());
            boolean valido = false;
            StringBuilder sb2 = new StringBuilder();

            sb2.append("Menu")
                    .append("\n1.Mas información de los cursos")
                    .append("\n0.Salir");
            do {
                try {
                    String opc = JOptionPane.showInputDialog(null, sb2.toString());
                    if (opc == null) {
                        return;
                    }
                    int opcion = Integer.parseInt(opc);
                    if (opcion == 1) {
                        valido = true;
                        StringBuilder sb3 = new StringBuilder();
                        for (Curso elem : lista) {

                            sb3.append("Nombre del curso: " + elem.getName().toUpperCase()).append("\n").append(elem.mostrarProfesor()).append("\n").append(elem.mostrarEstudiantes()).append("\n");

                        }
                        JOptionPane.showMessageDialog(null, sb3.toString());
                    } else if (opcion == 0) {
                        JOptionPane.showMessageDialog(null, "Gracias por visitarnos.");
                        valido = true;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor númerico. No dejar campos vacios.");
                }
            } while (!valido);

            //JOptionPane.showMessageDialog(null, sb);
        } else {

            JOptionPane.showMessageDialog(null, "No hay cursos a mostrar. Lo invitamos a agregar nuevos cursos");
        }

    }

    public static void reproducirSonido(String archivoSonido) {
        try {
            File archivo = new File(archivoSonido);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();  // Reproduce el sonido
        } catch (Exception e) {
            System.err.println("Error al reproducir el sonido: " + e.getMessage());
        }
    }

    public void buscarCurso(ArrayList<Curso> lista) {
        /* String nameCurso;
        if (!lista.isEmpty()) {
            
            do {
                nameCurso = JOptionPane.showInputDialog("Ingresa el nombre del curso a buscar");
                if (nameCurso == null) {
                    return;
                }

                if (!nameCurso.matches("^[a-zA-Z ]+$")) {
                    JOptionPane.showMessageDialog(null, "No se aceptan datos númericos ni espacios vacios.");
                }
                if (nameCurso == null) {
                    return;
                }
            } while (!nameCurso.matches("^[a-zA-Z ]+$"));

            boolean encontrado = false;
            for (Curso elem : lista) {
                if (elem.getName().equals(nameCurso)) {
                    encontrado = true;
                    JOptionPane.showMessageDialog(null, "El curso " + elem.getName() + " se encuentra");
                }
            }

            if (!encontrado) {

                JOptionPane.showMessageDialog(null, "El curso " + nameCurso + " no se encuentra");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay cursos disponibles. Lo invitamos a agregar nuevos cursos");

        }*/

        int codBuscar = 0;
        boolean encontrado = false;
        if (!lista.isEmpty()) {

            try {
                codBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el código del curso"));

                for (Curso curso : lista) {
                    if (curso.getCodigo() == codBuscar) {
                        encontrado = true;
                        JOptionPane.showMessageDialog(null, "El curso " + curso.getName().toUpperCase() + " se encuentra");
                    }
                }
                if (!encontrado) {
                    JOptionPane.showMessageDialog(null, "El curso " + codBuscar + " no se encuentra");
                }
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "No se aceptan datos númericos ni espacio vacios. Intentelo nuevamente");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No hay cursos disponibles. Lo invitamos a agregar nuevos cursos");
        }
    }

    public String validacionId(String msjEntrada, String msjError) {
        String cod = null;
        boolean continuar = true;
        while (continuar) {
            cod = JOptionPane.showInputDialog(msjEntrada);
            if (cod == null) {
                continuar = false;
            } else if (cod.matches("^\\d+$")) {
                continuar = false;
            } else {
                JOptionPane.showMessageDialog(null, msjError);
            }
        }

        return cod;

    }

    public void eliminarAlumnos(ArrayList<Curso> lista) {
        boolean continuar = true;
        String codigoCurso = null;
        String codigoEstudiante = null;

        if (!lista.isEmpty()) {
            // Validar el ID del curso
            codigoCurso = validacionId("Ingresa el código del curso", "No se aceptan caracteres ni espacios vacíos. Inténtalo nuevamente");
            if (codigoCurso == null) {
                return;
            }

            int codigoCursoInt = Integer.parseInt(codigoCurso);
            boolean cursoEncontrado = false;
            boolean estudianteEliminado = false;

            for (Curso curso : lista) {
                if (curso.getCodigo() == codigoCursoInt) {
                    cursoEncontrado = true;
                    if (!curso.getLista().isEmpty()) {
                        // Validar el ID del estudiante
                        codigoEstudiante = validacionId("Ingresa el código del estudiante", "No se aceptan caracteres ni espacios vacíos. Inténtalo nuevamente");
                        if (codigoEstudiante == null) {
                            return;
                        }

                        int codigoEstudianteInt = Integer.parseInt(codigoEstudiante);
                        List<Estudiante> estudiantes = curso.getLista();
                        Iterator<Estudiante> iterator = estudiantes.iterator();

                        while (iterator.hasNext()) {
                            Estudiante estudiante = iterator.next();
                            if (estudiante.getDNI() == codigoEstudianteInt) {
                                iterator.remove();
                                JOptionPane.showMessageDialog(null, "Estudiante " + estudiante.getName().toUpperCase() + " ha sido eliminado con éxito.");
                                estudianteEliminado = true;
                                break;
                            }
                        }

                        if (!estudianteEliminado) {
                            JOptionPane.showMessageDialog(null, "El estudiante no se encuentra en este curso.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay estudiantes en este curso.");
                    }
                    break;
                }
            }

            if (!cursoEncontrado) {
                JOptionPane.showMessageDialog(null, "El curso no se encuentra. Ingrese uno válido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden eliminar alumnos. Lo invitamos a agregar nuevos cursos.");
        }

    }

    public void buscarAlumno(ArrayList<Curso> lista) {
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se pueden buscar estudiantes. Lo invitamos a agregar nuevos cursos");
            return;
        }

        String cod = validacionId("Ingrese el código del curso", "Solo se aceptan datos numéricos. Inténtelo nuevamente");
        if (cod == null) {
            return;
        }

        int codParse;
        try {
            codParse = Integer.parseInt(cod);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El código del curso debe ser un número válido.");
            return;
        }

        Curso cursoEncontrado = null;
        for (Curso curso : lista) {
            if (curso.getCodigo() == codParse) {
                cursoEncontrado = curso;
                break;
            }
        }

        if (cursoEncontrado == null) {
            JOptionPane.showMessageDialog(null, "El curso no se encuentra. Ingrese un código válido.");
            return;
        }

        if (cursoEncontrado.getLista().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay estudiantes en este curso.");
            return;
        }

        String codEstudiante = validacionId("Ingrese el código del estudiante", "Solo se aceptan datos numéricos. Inténtelo nuevamente");
        if (codEstudiante == null) {
            return;
        }

        int codEsParse;
        try {
            codEsParse = Integer.parseInt(codEstudiante);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El código del estudiante debe ser un número válido.");
            return;
        }

        boolean estudianteEncontrado = false;
        for (Estudiante estudiante : cursoEncontrado.getLista()) {
            if (estudiante.getDNI() == codEsParse) {
                estudianteEncontrado = true;
                break;
            }
        }

        if (estudianteEncontrado) {
            JOptionPane.showMessageDialog(null, "El estudiante se encuentra en el curso.");
        } else {
            JOptionPane.showMessageDialog(null, "El estudiante no se encuentra en el curso.");
        }
    }

    public void addEstudiantesCurso(ArrayList<Curso> lista) {
        if (!lista.isEmpty()) {
            String codCurso = validacionId("Ingrese el código del curso", "Solo se aceptan datos numéricos. Inténtelo nuevamente");
            if (codCurso == null) {
                return;
            }

            int codParse = Integer.parseInt(codCurso);
            boolean cursoEncontrado = false;
            for (Curso elem : lista) {
                if (elem.getCodigo() == codParse) {
                    elem.addEstudiantes();
                    cursoEncontrado = true;
                    break;
                }
            }
            if (!cursoEncontrado) {
                JOptionPane.showMessageDialog(null, "El curso no se encuentra. Ingrese uno valido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar estudiantes. Lo invitamos a agregar nuevos cursos.");
        }

    }

    public void buscarCursosProfesor(ArrayList<Curso> listaCursos) {

        if (!listaCursos.isEmpty()) {
            String nameProfesor = JOptionPane.showInputDialog("Ingrese el nombre del profesor").toLowerCase();

            boolean profesorEncontrado = false;

            for (Curso curso : listaCursos) {
                for (Profesor profesor : curso.getProfesores()) {
                    if (profesor.getName().equalsIgnoreCase(nameProfesor)) {
                        profesorEncontrado = true;
                        JOptionPane.showMessageDialog(null, "El profesor " + profesor.getName() + " enseña el curso: " + curso.getName());

                    }
                }
            }

            if (!profesorEncontrado) {
                JOptionPane.showMessageDialog(null, "No se encontraron cursos para el profesor " + nameProfesor);

            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden buscar profesores. Lo invitamos a agregar nuevos cursos");
        }
    }

}
