package Model;

public class UsuarioTO {
    private int idUsuario;
    private String Nombre_Usuario;
    private String Contrasena; 
    private String Tipo_Usuario;
    private String estado;
    private String email;
    private String nombreCompleto;

    public UsuarioTO(){
    }
    
    public UsuarioTO(int idUsuario, String Nombre_Usuario, String Contrasena, String Tipo_Usuario, String estado, String email, String nombreCompleto) {
        this.idUsuario = idUsuario;
        this.Nombre_Usuario = Nombre_Usuario;
        this.Contrasena = Contrasena;
        this.Tipo_Usuario = Tipo_Usuario;
        this.estado = estado;
        this.email = email;
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre_Usuario() {
        return Nombre_Usuario;
    }

    public void setNombre_Usuario(String Nombre_Usuario) {
        this.Nombre_Usuario = Nombre_Usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getTipo_Usuario() {
        return Tipo_Usuario;
    }

    public void setTipo_Usuario(String Tipo_Usuario) {
        this.Tipo_Usuario = Tipo_Usuario;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    
}
