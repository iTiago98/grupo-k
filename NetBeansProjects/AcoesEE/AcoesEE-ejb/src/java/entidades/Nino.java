package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQuery(
    name = "getNinos",
    query = "SELECT n FROM Nino n"
)

@Entity
public class Nino implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidos;
    @Column(nullable = false)
    private Character sexo;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private String observaciones;
    private Integer prioridad;
    @OneToMany(mappedBy = "nino")
    private Set<Envio> envio;
    @ManyToOne
    private Colonia colonia;
    @ManyToOne
    private Proyecto proyecto;
    @OneToMany
    private Set<FichaAcademica> fichasAcademicas;
    @ManyToOne
    private Socio socio; // apadrinador
    @OneToMany
    private Set<Donacion> donaciones;

    
    public Nino() {}
    
    public Nino(String nombre, String apellidos, char sexo, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getFechaNacimientoFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        return sdf.format(this.fechaNacimiento);
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

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Set<Envio> getEnvio() {
        return envio;
    }

    public void setEnvio(Set<Envio> envio) {
        this.envio = envio;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Set<FichaAcademica> getFichasAcademicas() {
        return fichasAcademicas;
    }

    public void setFichasAcademicas(Set<FichaAcademica> fichasAcademicas) {
        this.fichasAcademicas = fichasAcademicas;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Set<Donacion> getDonaciones() {
        return donaciones;
    }

    public void setDonaciones(Set<Donacion> donaciones) {
        this.donaciones = donaciones;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Nino)) return false;
        return this.id.equals(((Nino)obj).id);
    }}

