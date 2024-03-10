import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class SistemaGestaoClientesPanel extends JPanel {
    private final Map<String, Cliente> mapaClientes;
    private final JTextField cpfField;
    private final JTextField nomeField;
    private final JTextField emailField;
    private final JTextField telefoneField;
    private final JTextField cpfBuscaField;
    private final JLabel resultadoLabel;
    private Consumer<List<Cliente>> listaContatosCallback;

    public SistemaGestaoClientesPanel() {
        mapaClientes = new HashMap<>();

        setLayout(new GridLayout(0, 2, 10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Componentes para cadastro de clientes
        add(new JLabel("CPF:"));
        cpfField = new JTextField();
        add(cpfField);

        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        add(telefoneField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(e -> cadastrarCliente());
        add(cadastrarButton);

        add(new JLabel("Buscar por CPF:"));
        cpfBuscaField = new JTextField();
        add(cpfBuscaField);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(e -> buscarCliente());
        add(buscarButton);

        resultadoLabel = new JLabel();
        add(resultadoLabel);
    }

    private void cadastrarCliente() {
        String id = generateID();
        String cpf = cpfField.getText();
        if (mapaClientes.containsKey(cpf)) {
            resultadoLabel.setText("Cliente com este CPF já cadastrado.");		return;		
        }
        String nome = nomeField.getText();
        String email = emailField.getText();
        String telefone = telefoneField.getText();

        Cliente novoCliente = new Cliente(id, cpf, nome, email, telefone);

        mapaClientes.put(cpf, novoCliente);

        resultadoLabel.setText("Cliente cadastrado:\nID: " + id + "\nCPF: " + cpf + "\nNome: " + nome + "\nEmail: " + email + "\nTelefone: " + telefone);

        cpfField.setText("");
        nomeField.setText("");
        emailField.setText("");
        telefoneField.setText("");
    }

    private void buscarCliente() {
        String cpfBusca = cpfBuscaField.getText();
        Cliente cliente = mapaClientes.get(cpfBusca); // Busca direta por CPF

        if (cliente != null) {
            List<Cliente> contatos = Collections.singletonList(cliente);
            cpfBuscaField.setText("");
            mostrarListaContatos(contatos);
        } else {
            resultadoLabel.setText("Cliente não encontrado");
        }

        resultadoLabel.setText("Cliente não encontrado");

    }

    private void mostrarListaContatos(List<Cliente> contatos) {
        if (listaContatosCallback != null) {
            listaContatosCallback.accept(contatos);
        }
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }

    public void setListaContatosCallback(Consumer<List<Cliente>> callback) {
        this.listaContatosCallback = callback;
    }

    public List<Cliente> obterTodosContatos() {
        return new ArrayList<>(mapaClientes.values());
    }
}
