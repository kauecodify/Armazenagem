import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class InterfaceAlocacaoValores extends JFrame implements ActionListener {
    private JTextField txtDataPagamento, txtEmpresa, txtValor;
    private JButton btnSalvar;

    public InterfaceAlocacaoValores() {
        setTitle("Alocação de Valores");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(4, 2));

        JLabel lblDataPagamento = new JLabel("Data de Pagamento:");
        txtDataPagamento = new JTextField();
        add(lblDataPagamento);
        add(txtDataPagamento);

        JLabel lblEmpresa = new JLabel("Empresa:");
        txtEmpresa = new JTextField();
        add(lblEmpresa);
        add(txtEmpresa);

        JLabel lblValor = new JLabel("Valor:");
        txtValor = new JTextField();
        add(lblValor);
        add(txtValor);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this);
        add(btnSalvar);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalvar) {
            String dataPagamento = txtDataPagamento.getText();
            String empresa = txtEmpresa.getText();
            String valor = txtValor.getText();

            try {
                // cria uma conexão HTTP POST com o servidor PHP
                URL url = new URL("http://localhost/salvar_dados.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            
                // enviar dados para o servidor
                String dados = "dataPagamento=" + URLEncoder.encode(dataPagamento, "UTF-8") +
                               "&empresa=" + URLEncoder.encode(empresa, "UTF-8") +
                               "&valor=" + URLEncoder.encode(valor, "UTF-8");
            
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
                writer.write(dados);
                writer.flush();
                writer.close();
            
                // resposta do servidor
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String resposta;
                while ((resposta = reader.readLine()) != null) {
                    System.out.println(resposta);
                }
                reader.close();
            
                // fechar a conexão
                conn.disconnect();
            
                JOptionPane.showMessageDialog(this, "Valores salvos com sucesso!");
            
                // limpar campos após salvar
                txtDataPagamento.setText("");
                txtEmpresa.setText("");
                txtValor.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao salvar os valores.");
            }            
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfaceAlocacaoValores::new);
    }
}
