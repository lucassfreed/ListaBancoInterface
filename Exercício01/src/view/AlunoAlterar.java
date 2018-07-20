package view;

import bean.AlunoBean;
import dao.AlunoDAO;
import interfaces.JFrameBaseInterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AlunoAlterar implements JFrameBaseInterface {

    public static String nome = "";
    public static float n1 = 0f;
    public static float n2 = 0f;
    public static float n3 = 0f;
    public static byte frequencia = 0;
    public int linhaSelecionada;
    private JLabel jLabelNome, jLabelNota1, jLabelNota2, jLabelNota3, jLabelFrequencia;
    private JTextField jTextFieldNome, jTextFieldNota1, jTextFieldNota2, jTextFieldNota3, jTextFieldFrequencia;
    private JButton jButtonSalvar;
    private JFrame jFrameAlterar;

    public AlunoAlterar() {
        configurandoLookAndFeel();
        gerarTela();
        instanciarComponentes();
        adicionarComponentes();
        gerarLocalizacoes();
        gerarDimensoes();
        acaoBotaoSalvar();
        preencherCampos();
        jFrameAlterar.setVisible(true);
    }

    @Override
    public void gerarTela() {
        jFrameAlterar = new JFrame("Menu de alteração de informações");
        jFrameAlterar.setSize(500, 520);
        jFrameAlterar.setLayout(null);
        jFrameAlterar.setLocationRelativeTo(null);
        jFrameAlterar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrameAlterar.getContentPane().setBackground(Color.decode("#1e1e1e"));
    }

    @Override
    public void adicionarComponentes() {
        jFrameAlterar.add(jLabelNome);
        jFrameAlterar.add(jTextFieldNome);

        jFrameAlterar.add(jLabelNota1);
        jFrameAlterar.add(jTextFieldNota1);

        jFrameAlterar.add(jLabelNota2);
        jFrameAlterar.add(jTextFieldNota2);

        jFrameAlterar.add(jLabelNota3);
        jFrameAlterar.add(jTextFieldNota3);

        jFrameAlterar.add(jLabelFrequencia);
        jFrameAlterar.add(jTextFieldFrequencia);

        jFrameAlterar.add(jButtonSalvar);
    }

    @Override
    public void instanciarComponentes() {
        jLabelNome = new JLabel("Nome");
        jLabelNome.setForeground(Color.decode("#ffffff"));
        jLabelNome.setFont(new Font("Kiddos", 0, 30));
        jTextFieldNome = new JTextField();

        jLabelNota1 = new JLabel("Primeira nota");
        jLabelNota1.setForeground(Color.decode("#ffffff"));
        jLabelNota1.setFont(new Font("Kiddos", 0, 30));
        jTextFieldNota1 = new JTextField();

        jLabelNota2 = new JLabel("Segunda nota");
        jLabelNota2.setForeground(Color.decode("#ffffff"));
        jLabelNota2.setFont(new Font("Kiddos", 0, 30));
        jTextFieldNota2 = new JTextField();

        jLabelNota3 = new JLabel("Terceira nota");
        jLabelNota3.setForeground(Color.decode("#ffffff"));
        jLabelNota3.setFont(new Font("Kiddos", 0, 30));
        jTextFieldNota3 = new JTextField();

        jLabelFrequencia = new JLabel("Frequencia");
        jLabelFrequencia.setForeground(Color.decode("#ffffff"));
        jLabelFrequencia.setFont(new Font("Kiddos", 0, 30));
        jTextFieldFrequencia = new JTextField();

        jButtonSalvar = new JButton("Salvar aluno");
        jButtonSalvar.setFont(new Font("Kiddos", 0, 25));

    }

    @Override
    public void gerarLocalizacoes() {
        jLabelNome.setLocation(270, 10);
        jTextFieldNome.setLocation(272, 35);

        jLabelNota1.setLocation(270, 120);
        jTextFieldNota1.setLocation(272, 148);

        jLabelNota2.setLocation(270, 190);
        jTextFieldNota2.setLocation(272, 218);

        jLabelNota3.setLocation(270, 260);
        jTextFieldNota3.setLocation(272, 288);

        jLabelFrequencia.setLocation(270, 370);
        jTextFieldFrequencia.setLocation(272, 403);

        jButtonSalvar.setLocation(60, 20);
    }

    @Override
    public void gerarDimensoes() {
        jLabelNome.setSize(80, 20);
        jTextFieldNome.setSize(150, 30);

        jLabelNota1.setSize(170, 30);
        jTextFieldNota1.setSize(150, 30);

        jLabelNota2.setSize(170, 30);
        jTextFieldNota2.setSize(150, 30);

        jLabelNota3.setSize(170, 30);
        jTextFieldNota3.setSize(150, 30);

        jLabelFrequencia.setSize(130, 45);
        jTextFieldFrequencia.setSize(150, 30);

        jButtonSalvar.setSize(150, 80);
    }

    private void acaoBotaoSalvar() {
        jButtonSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTextFieldNome.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O campo: 'Nome', precisa ser preenchido!");
                    jTextFieldNome.requestFocus();
                    return;
                }
                if (jTextFieldNota1.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O campo: 'Primeira nota', precisa ser preenchido!");
                    jTextFieldNota1.requestFocus();
                    return;
                }
                if (jTextFieldNota2.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O campo: 'Segunda nota', precisa ser preenchido!");
                    jTextFieldNota2.requestFocus();
                    return;
                }
                if (jTextFieldNota3.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O campo: 'Terceira nota', precisa ser preenchido!");
                    jTextFieldNota3.requestFocus();
                    return;
                }
                if (jTextFieldFrequencia.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O campo: 'Frequência', precisa ser preenchido!");
                    jTextFieldFrequencia.requestFocus();
                    return;
                }
                verificaçãoSegurança();

                String nome = jTextFieldNome.getText();
                float nota1 = Float.parseFloat(jTextFieldNota1.getText());
                float nota2 = Float.parseFloat(jTextFieldNota2.getText());
                float nota3 = Float.parseFloat(jTextFieldNota3.getText());
                Byte frequencia = Byte.parseByte(jTextFieldFrequencia.getText());
                float media = (nota1 + nota2 + nota3) / 3;

                JTable jt = new AlunoPrincipal().jTable;

                boolean condição = false;
                int linhaSelecionada = 0;
                for (int i = 0; i < jt.getRowCount(); i++) {
                    if (!condição) {
                        if (jt.getValueAt(i, 1).toString().equals(nome)) {
                            condição = true;
                            linhaSelecionada = i;
                        } else {
                            condição = false;
                        }
                    }
                }

                int id = Integer.parseInt(jt.getValueAt(linhaSelecionada, 0).toString());
                AlunoBean aluno = new AlunoBean();
                aluno.setId(id);
                aluno.setNome(nome);
                aluno.setCodigoMatricula(aluno.getCodigoMatricula());
                aluno.setNota1(nota1);
                aluno.setNota2(nota2);
                aluno.setNota3(nota3);
                aluno.setMedia(media);
                aluno.setFrequencia(frequencia);
                new AlunoDAO().alterar(aluno);
                JOptionPane.showMessageDialog(null, "O(a) aluno(a): '" + nome + "' foi alterado(a) com sucesso!"
                        + "\nA média deste(a) aluno(a) é: " + media);
                new AlunoPrincipal().jTable.updateUI();
                jFrameAlterar.dispose();
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

    private void verificaçãoSegurança() {
        try {
            float nota1 = Float.parseFloat(jTextFieldNota1.getText().trim());
            if (nota1 < 0 || nota1 > 10) {
                JOptionPane.showMessageDialog(null, "A primeira nota é inválida!");
                jTextFieldNota1.setText("");
                jTextFieldNota1.requestFocus();
                return;
            }
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "A primeira nota é inválida!");
            jTextFieldNota1.setText("");
            jTextFieldNota1.requestFocus();
            return;
        }

        try {
            float nota2 = Float.parseFloat(jTextFieldNota2.getText().trim());
            if (nota2 < 0 || nota2 > 10) {
                JOptionPane.showMessageDialog(null, "A segunda nota é inválida!");
                jTextFieldNota2.setText("");
                jTextFieldNota2.requestFocus();
                return;
            }
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "A segunda nota é inválida!");
            jTextFieldNota2.setText("");
            jTextFieldNota2.requestFocus();
            return;
        }

        try {
            float nota3 = Float.parseFloat(jTextFieldNota3.getText().trim());
            if (nota3 < 0 || nota3 > 10) {
                JOptionPane.showMessageDialog(null, "A terceira nota é inválida!");
                jTextFieldNota3.setText("");
                jTextFieldNota3.requestFocus();
                return;
            }
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "A terceira nota é inválida!");
            jTextFieldNota3.setText("");
            jTextFieldNota3.requestFocus();
            return;
        }

        try {
            byte porcentagem = Byte.parseByte(jTextFieldFrequencia.getText().trim().replace("%", ""));
            if (porcentagem < 0 || porcentagem > 100) {
                JOptionPane.showMessageDialog(null, "A frequência é inválida!");
                jTextFieldFrequencia.setText("");
                jTextFieldFrequencia.requestFocus();
                return;
            }
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "A frequência é inválida!");
            jTextFieldFrequencia.setText("");
            jTextFieldFrequencia.requestFocus();
            return;
        }

    }

    private void preencherCampos() {
        jTextFieldNome.setText(nome);
        jTextFieldNota1.setText(String.valueOf(n1));
        jTextFieldNota2.setText(String.valueOf(n2));
        jTextFieldNota3.setText(String.valueOf(n3));
        jTextFieldFrequencia.setText(String.valueOf(frequencia));
    }

}
