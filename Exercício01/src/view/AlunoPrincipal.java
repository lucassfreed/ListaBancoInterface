package view;

import bean.AlunoBean;
import dao.AlunoDAO;
import interfaces.JFrameBaseInterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class AlunoPrincipal implements JFrameBaseInterface {

    private JButton jButtonCadastrar, jButtonAlterar, jButtonExcluir;
    private DefaultTableModel dtm;
    public JFrame jFramePrincipal;
    public JTable jTable;
    private JScrollPane jScrollPaneTable;

    public AlunoPrincipal() {
        configurandoLookAndFeel();
        gerarTela();
        instanciarComponentes();
        adicionarComponentes();
        gerarLocalizacoes();
        gerarDimensoes();
        configScrollPaneTable();
        acaoBotaoCadastrar();
        acaoBotaoAlterar();
        acaoBotaoExcluir();
        popularTabela();
        jFramePrincipal.setVisible(true);
    }

    private void popularTabela() {
        AlunoDAO alunoDAO = new AlunoDAO();
        List<AlunoBean> alunos = alunoDAO.obterAlunos();

        for (AlunoBean aluno : alunos) {
            String status = "";
            float media = aluno.getMedia();
            if (media < 5) {
                status = "Reprovado(a) por média";
            }
            if (media < 7) {
                status = "Em exame";
            }
            if (media >= 7) {
                status = "Aprovado(a)";
            }
            if (aluno.getFrequencia() < 65) {
                status = "Reprovado(a) por frequência";
            }
            dtm.addRow(new Object[]{
                aluno.getId(),
                aluno.getNome(),
                status,
                aluno.getMedia(),
                aluno.getNota1(),
                aluno.getNota2(),
                aluno.getNota3(),
                aluno.getFrequencia() + "%"
            });
        }
    }

    @Override
    public void gerarTela() {
        jFramePrincipal = new JFrame("Menu principal");
        jFramePrincipal.setSize(1546, 600);
        jFramePrincipal.setLayout(null);
        jFramePrincipal.setLocationRelativeTo(null);
        jFramePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFramePrincipal.getContentPane().setBackground(Color.decode("#1e1e1e"));
    }

    @Override
    public void instanciarComponentes() {
        jButtonCadastrar = new JButton("Cadastrar aluno");
        jButtonCadastrar.setFont(new Font("Kiddos", 0, 20));

        jButtonAlterar = new JButton("<html>Alterar<br />informações</html>");
        jButtonAlterar.setFont(new Font("Kiddos", 0, 20));

        jButtonExcluir = new JButton("Excluir aluno");
        jButtonExcluir.setFont(new Font("Kiddos", 0, 20));

        jScrollPaneTable = new JScrollPane();
        jTable = new JTable();
        Object[] colunas = new Object[]{"ID", "Nome", "Status", "Média", "Nota 1", "Nota 2", "Nota 3", "Frequência"};
        dtm = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.setModel(dtm);
        jScrollPaneTable = new JScrollPane(jTable);
    }

    @Override
    public void adicionarComponentes() {
        jFramePrincipal.add(jButtonCadastrar);
        jFramePrincipal.add(jButtonAlterar);
        jFramePrincipal.add(jButtonExcluir);

        jFramePrincipal.add(jScrollPaneTable);
    }

    @Override
    public void gerarLocalizacoes() {
        jButtonCadastrar.setLocation(10, 30);
        jButtonAlterar.setLocation(10, 130);
        jButtonExcluir.setLocation(10, 230);

        jScrollPaneTable.setLocation(170, 15);
    }

    @Override
    public void gerarDimensoes() {
        jButtonCadastrar.setSize(150, 70);
        jButtonAlterar.setSize(150, 70);
        jButtonExcluir.setSize(150, 70);

        jScrollPaneTable.setSize(1350, 530);
    }

    private void configScrollPaneTable() {
        jScrollPaneTable.setViewportView(jTable);
        jScrollPaneTable.setVerticalScrollBarPolicy(jScrollPaneTable.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    private void acaoBotaoCadastrar() {
        jButtonCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AlunoCadastro();
            }
        });
    }

    private void acaoBotaoAlterar() {
        jButtonAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = jTable.getSelectedRow();
                AlunoAlterar.nome = dtm.getValueAt(linhaSelecionada, 1).toString();
                AlunoAlterar.n1 = Float.parseFloat(dtm.getValueAt(linhaSelecionada, 4).toString());
                AlunoAlterar.n2 = Float.parseFloat(dtm.getValueAt(linhaSelecionada, 5).toString());
                AlunoAlterar.n3 = Float.parseFloat(dtm.getValueAt(linhaSelecionada, 6).toString());
                AlunoAlterar.frequencia = Byte.parseByte(dtm.getValueAt(linhaSelecionada, 7).toString().replace("%", ""));
                new AlunoAlterar();
            }
        });
    }

    private void acaoBotaoExcluir() {
        jButtonExcluir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int opcao = JOptionPane.showOptionDialog(null,
                        "Você tem certeza que deseja realmente excluir este aluno?\nEsta ação não pode ser desfeita!", "Verificação",
                        0,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[]{
                            "Sim", "Não"
                        },
                        "Sim");
                if (opcao == 0) {
                    int linhaSelecionada = jTable.getSelectedRow();
                    new AlunoDAO().apagar(Integer.parseInt(dtm.getValueAt(linhaSelecionada, 0).toString()));
                    dtm.removeRow(linhaSelecionada);
                    JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
                    return;
                }
            }
        });
    }

    public static void configurandoLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {

        } catch (ClassNotFoundException e) {

        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        }
    }

}
