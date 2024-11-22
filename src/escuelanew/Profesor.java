package escuelanew;

import java.util.ArrayList;

public class Profesor {

    private int id;
    private String name;
    private ArrayList<Curso> cursos;

    public Profesor(String name) {
        this.id = (int) ((Math.random()*1000) + 1);
        this.name = name;
        this.cursos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    

}
