package control;

import entidades.Usuario;

import negocio.NegocioUsuario;
import negocio.DatosDuplicadosException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
    
    @EJB
    private NegocioUsuario negUsuario;
      
    private String usuarioNombre;
    private String contrasenia;
    private Usuario usuario;
    //private ArrayList<Usuario> usuarios;

    
    public ControlUsuario() {
        usuario = new Usuario();
        //usuarios.add(new Usuario("admin", "admin", Usuario.Rol.ADMINISTRADOR));
        //usuarios.add(new Usuario("user", "user", Usuario.Rol.NORMAL));
    }
    
    public String addUsuario() {
        //usuarios.add(this.usuario);
        //try {
            negUsuario.addUsuario(this.usuario);
        /*} catch(DatosDuplicadosException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
        }*/
        this.usuario = new Usuario();
        return null;
    }
    
    public String removeUsuario(Usuario u) {
        //usuarios.remove(u);
        negUsuario.removeUsuario(u);
        return null;
    }
    
    public String goModifyUsuario(Usuario u) {
        this.usuario = u;
        return "usuariosModificar.xhtml";
    }
    
    public String modifyUsuario() {
        //for(Usuario u: usuarios) {
        for(Usuario user: negUsuario.getUsuarios()) {
            if(user.equals(this.usuario)) negUsuario.modifyUsuario(this.usuario);
        }
        
        this.usuario = new Usuario();
        
        return "usuarios.xhtml"; // volvemos a la página de usuarios para visualizar los cambios
    } 

    
    public String autenticar() {        
        FacesContext ctx = FacesContext.getCurrentInstance();

        // No es la forma mas elegante de solucionar esto, pero es que no se donde
        // es el mejor lugar para poner esto sin que me salten excepciones con el
        // entity manager.
       
        // Las siguientes lineas añaden un usuario "admin" con passwd "admin" a la
        // base de datos.
        this.usuario.setUsuario("admin");
        this.usuario.setContrasenia("admin");
        this.usuario.setRol(Usuario.Rol.ADMINISTRADOR);
        this.addUsuario();
        
        
        //for(Usuario u: usuarios) {
        for(Usuario u: negUsuario.getUsuarios()) {
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
    
    public List<Usuario> getUsuarios() {
        return negUsuario.getUsuarios();
    }
    
    /*
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    */
}
