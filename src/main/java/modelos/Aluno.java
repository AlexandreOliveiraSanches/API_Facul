package modelos;
import conexao.Conexao;
import java.sql.*;
import java.sql.SQLException;

public class Aluno {
    private String nome;
    private int cursoId;
    private String ra;

    public Aluno(){

    }
    
    public Aluno(String nome, int cursoId, String ra) {
        this.nome = nome;
        this.cursoId = cursoId;
        this.ra = ra;
    }
    
    public boolean salvar(){
        Connection conn = Conexao.conectar();
        String sql = "INSERT INTO aluno (ra, nome, curso_id) VALUES (?, ?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, ra);
            stmt.setString(2, nome);
            stmt.setInt(3, cursoId);
            stmt.executeUpdate();
            return true;
        } catch(SQLException e){
            System.out.println("Erro ao salvar aluno: " + e.getMessage());
            return false;
        }
    }

    public static Aluno buscarAluno(String ra){
        Connection conn = Conexao.conectar();
        String sql = "SELECT * FROM aluno WHERE ra = ?";
        Aluno aluno = null;
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, ra);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                aluno = new Aluno(rs.getString("nome"), rs.getInt("curso_id"), rs.getString("ra"));
            }
        } catch(SQLException e){
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return aluno;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public int getCursoId(){
        return cursoId;
    }
    public void setCurso(int cursoId){
        this.cursoId = cursoId;
    }

    public String getRa(){
        return ra;
    }
    public void setRa(String ra){
        this.ra = ra;
    }

    @Override
    public String toString() {
        String nomeCurso = Curso.buscarCursoPorId(cursoId);
        return "\n====== Dados de " + nome + " ======\nRA: " + ra + "\nCurso: " + nomeCurso;
    }
}