import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ListaContatosFrame extends JFrame {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    public ListaContatosFrame(List<Cliente> contatos) {
        setTitle("Lista de Contatos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 5));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));


        panel.add(new JLabel("ID"));
        panel.add(new JLabel("Nome"));
        panel.add(new JLabel("CPF"));
        panel.add(new JLabel("Celular"));
        panel.add(new JLabel("Email"));

        // Adiciona os contatos à lista
        for (Cliente contato : contatos) {
            panel.add(new JLabel(String.valueOf(idGenerator.getAndIncrement())));
            panel.add(new JLabel(contato.getNome()));
            panel.add(new JLabel(contato.getCpf()));
            panel.add(new JLabel(contato.getTelefone()));
            panel.add(new JLabel(contato.getEmail()));
        }

        getContentPane().add(new JScrollPane(panel));

        pack();
        setLocationRelativeTo(null);
    }
}
