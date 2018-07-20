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

    public int inserir(AlunoBean aluno) {
        Connection conexao = ConexaoFactory.obterConexão();
        if (conexao != null) {
            String sql = "INSERT INTO alunos"
                    + "\n(id, nome, codigo_matricula, nota_1, nota_2, nota_3, media, frequencia)"
                    + "\nVALUES(?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement ps = conexao.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, aluno.getId());
                ps.setString(2, aluno.getNome());
                ps.setString(3, aluno.getCodigoMatricula());
                ps.setFloat(4, aluno.getNota1());
                ps.setFloat(5, aluno.getNota2());
                ps.setFloat(6, aluno.getNota3());
                ps.setFloat(7, aluno.getMedia());
                ps.setByte(8, aluno.getFrequencia());
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
        Connection conexao = ConexaoFactory.obterConexão();
        String sql = "UPDATE alunos SET nome = ?, codigo_matricula = ?, nota_1 = ?, nota_2 = ?, nota_3 = ?, media = ?, frequencia = ? WHERE id = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCodigoMatricula());
            ps.setFloat(3, aluno.getNota1());
            ps.setFloat(4, aluno.getNota2());
            ps.setFloat(5, aluno.getNota3());
            ps.setFloat(6, aluno.getMedia());
            ps.setByte(7, aluno.getFrequencia());
            ps.setInt(8, aluno.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoFactory.fecharConexão();
        }
        return false;
    }

    public boolean apagar(int id) {
        Connection conexao = ConexaoFactory.obterConexão();
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
        Connection conexao = ConexaoFactory.obterConexão();
        String sql = "SELECT id, nome, codigo_matricula, nota_1, nota_2, nota_3, media, frequencia"
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
                    aluno.setMedia(rs.getFloat("media"));
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

    public boolean conterAluno(String nomeAluno) {
        List<AlunoBean> alunos = obterAlunos();
        boolean condição = false;
        for (int i = 0; i < alunos.size(); i++) {
            if (condição == false) {
                if (alunos.get(i).getNome().equals(nomeAluno)) {
                    condição = true;
                } else {
                    condição = false;
                }
            }
        }
        return false;
    }

    public List<AlunoBean> obterAlunos() {
        Connection conexao = ConexaoFactory.obterConexão();
        List<AlunoBean> alunos = new ArrayList<>();

        if (conexao != null) {
            String sql = "SELECT id, nome, codigo_matricula, nota_1, nota_2, "
                    + "nota_3, media, frequencia FROM alunos";
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
                    aluno.setMedia(rs.getFloat("media"));
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
