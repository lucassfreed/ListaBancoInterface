package dao;

import bean.AlunoBean;
import conexao.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    
    public static Connection conexao = ConexaoFactory.obterConexão();

    public int inserir(AlunoBean aluno) {
        if (conexao != null) {
            String sql = "INSERT INTO alunos"
                    + "\n(id, nome, codigo_matricula, nota_1, nota_2, nota_3, frequencia)"
                    + "\nVALUES(?,?,?,?,?,?,?)";

            try {
                PreparedStatement ps = conexao.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, aluno.getId());
                ps.setString(2, aluno.getNome());
                ps.setString(3, aluno.getCodigoMatricula());
                ps.setFloat(4, aluno.getNota1());
                ps.setFloat(5, aluno.getNota2());
                ps.setFloat(6, aluno.getNota3());
                ps.setByte(7, aluno.getFrequencia());
                ps.execute();
                ResultSet resultSet = ps.getGeneratedKeys();
                
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConexaoFactory.fecharConexão();
            }
        }
        return 0;
    }
    
    public boolean alterar(AlunoBean aluno) {
        String sql = "UPDATE clientes SET nome = ?, codigo_matricula = ?, nota_1 = ?, nota_2 = ?, nota_3 = ?, frequencia = ? WHERE id = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCodigoMatricula());
            ps.setFloat(3, aluno.getNota1());
            ps.setFloat(4, aluno.getNota2());
            ps.setFloat(5, aluno.getNota3());
            ps.setByte(6, aluno.getFrequencia());
            ps.setInt(5, aluno.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoFactory.fecharConexão();
        }
        return false;
    }
    
    public boolean apagar(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?;";
        if (conexao != null) {
            try {
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, id);
                return ps.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConexaoFactory.fecharConexão();
            }
        }
        return false;
    }
    
    public AlunoBean obterAlunoPorID(int id) {
        String sql = "SELECT id, nome, codigo_matricula, nota_1, nota_2, nota_3, frequencia"
                + "\nFROM alunos WHERE id = ?;";
        if (conexao != null) {
            try {
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, id);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                if (rs.next()) {
                    AlunoBean aluno = new AlunoBean();
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setCodigoMatricula(rs.getString("codigo_matricula"));
                    aluno.setNota1(rs.getFloat("nota_1"));
                    aluno.setNota2(rs.getFloat("nota_2"));
                    aluno.setNota3(rs.getFloat("nota_3"));
                    aluno.setFrequencia(rs.getByte("frequencia"));
                    return aluno;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConexaoFactory.fecharConexão();
            }
        }
        return null;
    }
    
    public List<AlunoBean> obterAlunos() {
        List<AlunoBean> alunos = new ArrayList<>();
        
        if (conexao != null) {
            String sql = "SELECT id, nome, codigo_matricula, nota_1, nota_2, "
                    + "nota_3, frequencia FROM alunos";
            try {
                Statement st = conexao.createStatement();
                st.execute(sql);
                ResultSet rs = st.getResultSet();
                while (rs.next()) {
                    AlunoBean aluno = new AlunoBean();
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setCodigoMatricula(rs.getString("codigo_matricula"));
                    aluno.setNota1(rs.getFloat("nota_1"));
                    aluno.setNota2(rs.getFloat("nota_2"));
                    aluno.setNota3(rs.getFloat("nota_3"));
                    aluno.setFrequencia(rs.getByte("frequencia"));
                    alunos.add(aluno);
                }
                
                
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConexaoFactory.fecharConexão();
            }
        }
        
        return alunos;
    }

}
