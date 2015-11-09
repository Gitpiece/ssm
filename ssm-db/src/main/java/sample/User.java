package sample;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "user")
@XmlRootElement
public class User extends SuperUser implements Serializable{

    @Id
    @JsonIgnore
    @Column(name = "ID")
    private Integer ID;
    @Column(name = "NAME")
    private String NAME;

    @Column(name = "AGE")
    private Integer AGE;

    @XmlAttribute
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    @XmlAttribute
    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME == null ? null : NAME.trim();
    }

    @XmlAttribute
    public Integer getAGE() {
        return AGE;
    }

    public void setAGE(Integer AGE) {
        this.AGE = AGE;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", NAME='" + NAME + '\'' +
                ", AGE=" + AGE +
                '}';
    }
}
