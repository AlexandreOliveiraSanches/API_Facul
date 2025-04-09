package modelos;
import conexao.Conexao;
import java.sql.*;
import java.sql.SQLException;

public class Curso {
    private int id;
    private String nome;
    
    public Curso(){
        
    }
    
    public Curso(String nome){
        this.nome = nome;
    }
    
    public boolean salvar(){
        Connection conn = Conexao.conectar();
        String sql = "INSERT INTO curso (nome) VALUES (?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, nome);
            stmt.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Erro ao salvar curso: " + e.getMessage());
            return false;
        }
    }
    
    public static void listarCursos(){
        Connection conn = Conexao.conectar();
        String sql = "SELECT * FROM curso";
        
        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.println("\n== CURSOS DISPONÍVEIS ==");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Nome: " + rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar cursos: " + e.getMessage());
        }
    }
    
    public static String buscarCursoPorId(int idCurso) {
        Connection conn = Conexao.conectar();
        String nomeCurso = null;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT nome FROM curso WHERE id = ?")) {
            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nomeCurso = rs.getString("nome");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar curso: " + e.getMessage());
        }

        return nomeCurso;
    }
    
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }

    public String getNome() { 
        return nome; 
    }
    public void setNome(String nome) { 
        this.nome = nome; 
    }
}
