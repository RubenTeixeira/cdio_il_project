package ui;

import controller.ClientRegisterController;
import java.sql.SQLException;
import persistence.OracleDb;

public class ClientRegisterUI {

    private controller.ClientRegisterController controller;

    public ClientRegisterUI() throws SQLException {
        controller = new ClientRegisterController(Main.CREDENTIALS_FILE);
        run();
    }

    private void run() {

        System.out.println("----Insira dados de contacto----\n");
        
        System.out.println("\n----Nome: ");
        String nome = utils.ReadFromKeyboard.readString();
        
        System.out.println("\n----NIF: ");
        int NIF = utils.ReadFromKeyboard.read();
        
        System.out.println("\n----Email: ");
        String email = utils.ReadFromKeyboard.readString();
        
        System.out.println("\n----Telemóvel: ");
        int telemovel = utils.ReadFromKeyboard.read();
        
        System.out.println("\n----Username: ");
        String username = utils.ReadFromKeyboard.readString();
        
        System.out.println("\n----Password: ");
        String password = utils.ReadFromKeyboard.readString();
        
        System.out.println("----Insira Morada----\n");
        
        System.out.println("\n----Rua:");
        String rua = utils.ReadFromKeyboard.readString();
        
        System.out.println("\n----Código Postal:\n");
        String codigoPostal = utils.ReadFromKeyboard.readString();
        
        System.out.println("\n----Localidade:\n");
        String localidade = utils.ReadFromKeyboard.readString();

        controller.newAddress(rua, codigoPostal, localidade);
        
        if (controller.newClient(7, nome, NIF, username, password, email, telemovel)) {
            System.out.println("Registado com sucesso");
        } else {
            System.out.println("Algo de errado aconteceu. Tente mais tarde.");

        }

    }

}
