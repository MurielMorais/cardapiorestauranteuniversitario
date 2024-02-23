package Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Restaurante extends JFrame {
    private Map<String, JTextField[]> menuItems;
    private JTextArea cardapioTextArea;

    public Restaurante() {
        setTitle("Restaurante Universitário");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuItems = new HashMap<>();

        JPanel mainPanel = new JPanel(new GridLayout(6, 5));
        String[] diasDaSemana = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};

        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel("Carboidratos"));
        mainPanel.add(new JLabel("Proteína"));
        mainPanel.add(new JLabel("Salada"));
        mainPanel.add(new JLabel("Suco"));

        for (String dia : diasDaSemana) {
            mainPanel.add(new JLabel(dia));

            JTextField[] fields = new JTextField[4];
            for (int i = 0; i < 4; i++) {
                fields[i] = new JTextField();
                mainPanel.add(fields[i]);
            }

            menuItems.put(dia, fields);
        }

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Salvar");
        JButton clearButton = new JButton("Limpar");
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);

        cardapioTextArea = new JTextArea(10, 30);
        cardapioTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cardapioTextArea);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCardapio();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        Container contentPane = getContentPane();
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        contentPane.add(scrollPane, BorderLayout.EAST);
    }

    private void salvarCardapio() {
        StringBuilder cardapio = new StringBuilder();
        for (Map.Entry<String, JTextField[]> entry : menuItems.entrySet()) {
            String dia = entry.getKey();
            JTextField[] fields = entry.getValue();

            String carboidratos = fields[0].getText();
            String proteina = fields[1].getText();
            String salada = fields[2].getText();
            String suco = fields[3].getText();

            cardapio.append(dia).append(":").append("\n");
            cardapio.append("Carboidratos: ").append(carboidratos).append("\n");
            cardapio.append("Proteína: ").append(proteina).append("\n");
            cardapio.append("Salada: ").append(salada).append("\n");
            cardapio.append("Suco: ").append(suco).append("\n\n");
        }
        cardapioTextArea.setText(cardapio.toString());
    }

    private void limparCampos() {
        for (JTextField[] fields : menuItems.values()) {
            for (JTextField field : fields) {
                field.setText("");
            }
        }
        cardapioTextArea.setText("");
    }
 public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Restaurante().setVisible(true);
            }
        });
    }
}