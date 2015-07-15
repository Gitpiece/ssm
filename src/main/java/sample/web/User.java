package sample.web;

import java.io.Serializable;
public class User implements Serializable {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "[id]="+this.id+";[name]="+this.name;
    }
}
