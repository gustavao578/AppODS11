package interfaces;

import classes.BancoDeDados;
import classes.Idioma;
import classes.Sugestao;
import classes.Tradutor;
import classes.UsuarioLogado;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ryans
 */
public class MinhasSugestões extends javax.swing.JFrame {
    private Tradutor tradutor;
    private BancoDeDados bancoDeDados;
    /**
     * Creates new form EnviarSugestoes
     */
    public MinhasSugestões() {
        // Inicializa o tradutor e o banco de dados
        tradutor = new Tradutor(Idioma.getIdiomaAtual());
        bancoDeDados = new BancoDeDados();
        initComponents();
        atualizarLabels();
        /* pega o email do usuario e carrega as sugestões obs:os codigos que envolvem
        enviar alguma coisa ao banco de voce precisara fazer login para que funcione
        porque eles utilizam o usuario logado caso voce só rode um dos jframes e não
        faça o login ira dar erro*/
        String emailUsuario = UsuarioLogado.getUsuario().getEmail(); // Aqui você deve pegar o email do usuário logado
        carregarSugestoes(emailUsuario);
        
        pt_btn.addActionListener(e -> {
            Idioma.setIdiomaAtual("pt");
                    atualizarLabels();
        });

        en_btn.addActionListener(e -> {
            Idioma.setIdiomaAtual("en");
                    atualizarLabels();
        });
         es_btn.addActionListener(e -> {
            Idioma.setIdiomaAtual("es");
                    atualizarLabels();
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ars_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sgt_tb = new javax.swing.JTable();
        del_btn = new javax.swing.JButton();
        label_img = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        optionsmenu = new javax.swing.JMenu();
        idiomamenu = new javax.swing.JMenu();
        pt_btn = new javax.swing.JMenuItem();
        en_btn = new javax.swing.JMenuItem();
        es_btn = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(443, 294));
        getContentPane().setLayout(null);

        ars_label.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ars_label.setForeground(new java.awt.Color(255, 255, 255));
        ars_label.setText("Minhas Sugestões");
        getContentPane().add(ars_label);
        ars_label.setBounds(140, 10, 160, 20);

        sgt_tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane2.setViewportView(sgt_tb);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(30, 40, 380, 210);

        del_btn.setText("apagar ");
        del_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_btnActionPerformed(evt);
            }
        });
        getContentPane().add(del_btn);
        del_btn.setBounds(340, 10, 72, 23);

        label_img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/360_F_823155337_DzGKTtbWRgXd5xGV42yLZqRyrX7fLsk0.jpg"))); // NOI18N
        getContentPane().add(label_img);
        label_img.setBounds(0, 0, 440, 270);

        optionsmenu.setText("opções");

        idiomamenu.setText("Idioma");

        pt_btn.setText("Português (pt-br)");
        idiomamenu.add(pt_btn);

        en_btn.setText("Inglês (en)");
        idiomamenu.add(en_btn);

        es_btn.setText("espanhol (es)");
        idiomamenu.add(es_btn);

        optionsmenu.add(idiomamenu);

        jMenuItem1.setText("Tela Inicial");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        optionsmenu.add(jMenuItem1);

        jMenuBar1.add(optionsmenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        TelaInicial tli = new TelaInicial();
        tli.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void del_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_btnActionPerformed
    int linhaSelecionada = sgt_tb.getSelectedRow();
        if (linhaSelecionada != -1) { // Verifica se alguma linha está selecionada
            String descricao = (String) sgt_tb.getValueAt(linhaSelecionada, 0); // Obtém a descrição da sugestão

            boolean sucesso = bancoDeDados.removerSugestao(descricao);

            if (sucesso) {
                 String emailUsuario = UsuarioLogado.getUsuario().getEmail();
                // Remove a linha da tabela
                DefaultTableModel model = (DefaultTableModel) sgt_tb.getModel();
                model.removeRow(linhaSelecionada);
                JOptionPane.showMessageDialog(this, "Sugestão removida com sucesso!");
                carregarSugestoes(emailUsuario);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao remover a sugestão.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma sugestão para remover.");
        }
    }//GEN-LAST:event_del_btnActionPerformed
// Método para carregar as sugestões na tabela
    private void carregarSugestoes(String emailUsuario) {
        List<Sugestao> minhasSugestoes = bancoDeDados.listarSugestoesPorUsuario(emailUsuario);
        // Limpar tabela antes de adicionar novos dados
        DefaultTableModel model = (DefaultTableModel) sgt_tb.getModel();
        model.setRowCount(0); // Limpa as linhas existentes

        // Adicionar as sugestões à tabela
        for (Sugestao sugestao : minhasSugestoes) {
           model.addRow(new Object[]{sugestao.getDescricao(), sugestao.getUsuario()});
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MinhasSugestões.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MinhasSugestões.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MinhasSugestões.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MinhasSugestões.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MinhasSugestões().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ars_label;
    private javax.swing.JButton del_btn;
    private javax.swing.JMenuItem en_btn;
    private javax.swing.JMenuItem es_btn;
    private javax.swing.JMenu idiomamenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_img;
    private javax.swing.JMenu optionsmenu;
    private javax.swing.JMenuItem pt_btn;
    private javax.swing.JTable sgt_tb;
    // End of variables declaration//GEN-END:variables

    private void atualizarLabels() {
        setTitle(tradutor.traduzir("minhasSugestoes"));
        ars_label.setText(tradutor.traduzir("minhasSugestoes"));
        optionsmenu.setText(tradutor.traduzir("opções"));
        idiomamenu.setText(tradutor.traduzir("Idioma"));
        // Atualize outros componentes com base no idioma
    }
}
