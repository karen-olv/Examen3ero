package final_poo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CineCrud {
    private Connection conexion;

    public CineCrud() {
        conexion = ConexionMySQL.conectar();
    }

    public boolean insertarPelicula(String titulo, String genero, int año, String clasificacion) {
        String sql = "insert into peliculas(titulo, genero, año, clasificacion) values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, genero);
            ps.setInt(3, año);
            ps.setString(4, clasificacion);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar película: " + e.getMessage());
            return false;
        }
    }

    public ResultSet buscarTodasPeliculas() {
        String sql = "select * from peliculas";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al buscar películas: " + e.getMessage());
            return null;
        }
    }
   
    public ResultSet buscarPorTitulo(String titulo) {
        String sql = "select * from peliculas where titulo = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, titulo);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al buscar por título: " + e.getMessage());
            return null;
        }
    }

    public boolean actualizarPelicula(int id, String titulo, String genero, int año, String clasificacion) {
        String sql = "update peliculas set titulo = ?, genero = ?, año = ?, clasificacion = ? where id = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, genero);
            ps.setInt(3, año);
            ps.setString(4, clasificacion);
            ps.setInt(5, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar película: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPelicula(int id) {
        String sql = "delete from peliculas where id = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar película: " + e.getMessage());
            return false;
        }
    }
}

    
    

