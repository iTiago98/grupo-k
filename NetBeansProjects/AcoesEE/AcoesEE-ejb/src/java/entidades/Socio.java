package entidades;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Socio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String DNI;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidos;
    private String direccion;
    private String telefono;
    private String correo;
    private String observaciones;
    @OneToMany(mappedBy = "socio")
    private Set<Envio> envios;
    @OneToMany(mappedBy = "socio")
    private Set<Nino> ninos; // niños apadrinados
    @OneToMany
    private Set<Donacion> donaciones;

    /*** NUEVO ***/
    public Socio() {}
    
    public Socio(String DNI,String nombre, String apellidos){
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    
    public Socio(String DNI,String nombre, String apellidos, Set<Envio> envios, Set<Nino> ninos, Set<Donacion> donaciones){
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ninos = ninos;
        this.envios = envios;
        this.donaciones = donaciones;
    }
    
    @Override
    public String toString() {
        return this.nombre + " " + this.apellidos;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Set<Envio> getEnvios() {
        return envios;
    }

    public void setEnvios(Set<Envio> envios) {
        this.envios = envios;
    }

    public Set<Nino> getNinos() {
        return ninos;
    }

    public void setNinos(Set<Nino> ninos) {
        this.ninos = ninos;
    }

    public Set<Donacion> getDonaciones() {
        return donaciones;
    }

    public void setDonaciones(Set<Donacion> donaciones) {
        this.donaciones = donaciones;
    }

}
