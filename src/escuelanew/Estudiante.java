package escuelanew;

public class Estudiante {

    private int DNI;
    private String name;
    private String apellido;
    private int dia;
    private int mes;
    private int anio;

    public Estudiante(String name, String apellido, int dia, int mes, int anio) {
        DNI = (int) (Math.random() * 1000) + 1;
        this.name = name;
        this.apellido = apellido;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int dNI) {
        DNI = dNI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Estudiante DNI=" + DNI + ", name=" + name + ", apellido=" + apellido + ", dia=" + dia + ", mes=" + mes
                + ", anio=" + anio;
    }
}
