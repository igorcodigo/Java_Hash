import javax.swing.*;
import java.awt.*;

public class SistemaGestaoClientesApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaGestaoClientesPanel panel = new SistemaGestaoClientesPanel();
            JFrame frame = new JFrame("Sistema de Gestão de Clientes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel);
            JButton mostrarTodosButton = new JButton("Mostrar Todos os Contatos");
            mostrarTodosButton.addActionListener(e -> mostrarTodosContatos(panel));
            frame.getContentPane().add(mostrarTodosButton, BorderLayout.SOUTH);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            panel.setListaContatosCallback(contatos -> {
                ListaContatosFrame listaContatosFrame = new ListaContatosFrame(contatos);
                listaContatosFrame.setVisible(true);
            });
        });
    }
    private static void mostrarTodosContatos(SistemaGestaoClientesPanel panel) {
        java.util.List<Cliente> todosContatos = panel.obterTodosContatos();

        ListaContatosFrame listaContatosFrame = new ListaContatosFrame(todosContatos);
        listaContatosFrame.setVisible(true);
    }
}
