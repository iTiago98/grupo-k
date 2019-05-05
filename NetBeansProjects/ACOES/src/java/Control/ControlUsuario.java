package Control;

import JPA.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "ControlUsuario")
@SessionScoped
public class ControlUsuario implements Serializable {
    @Inject
    private ControlAutorizacion ctrl;
    
    private String usuarioNombre;
    private String contrasenia;
    private Usuario usuario;
    private ArrayList<Usuario> usuarios;

    
    public ControlUsuario() {
        usuario = new Usuario();
        
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("admin", "admin", Usuario.Rol.ADMINISTRADOR));
        usuarios.add(new Usuario("user", "user", Usuario.Rol.NORMAL));
    }
    
    public String addUsuario() {
        usuarios.add(this.usuario);
        this.usuario = new Usuario();
        return null;
    }
    
    public String removeUsuario(Usuario u) {
        usuarios.remove(u);
        
        return null; // la misma página
    }
    
    public String goModifyUsuario(Usuario u) {
        this.usuario = u;
        return "usuariosModificar.xhtml";
    }
    
    public String modifyUsuario() {
        for(Usuario user: usuarios) {
            if(user.equals(this.usuario)) user = this.usuario;
        }
        
        this.usuario = new Usuario();
        
        return "usuarios.xhtml"; // volvemos a la página de usuarios para visualizar los cambios
    } 

    
    public String autenticar() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        for(Usuario u: usuarios) {
            if(u.getUsuario().equals(this.usuarioNombre) && u.getContrasenia().equals(this.contrasenia)) {
                ctrl.setUsuario(u);
                return "home.xhtml";
            }
        }
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrectos", "Usuario o contraseña incorrectos"));
        return "home.xhtml";
    }

    
   
    /**************************************************/
    // GETTERS Y SETTERS
    /**************************************************/
       
    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
