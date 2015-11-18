
package ui;

import controller.RegistarClienteController;
import domain.Cliente;
import domain.Morada;

public class RegistarClienteUI {

    private controller.RegistarClienteController controller;

    public RegistarClienteUI() {
        controller = new RegistarClienteController();
        run();
    }

    private void run() {

        System.out.println("----Insira dados de contacto----\n");
        System.out.println("Email\n");
        String email = utils.ReadFromKeyboard.readString();
        System.out.println("Telemóvel\n");
        int telemovel = utils.ReadFromKeyboard.read();
        System.out.println("Username\n");
        String username = utils.ReadFromKeyboard.readString();
        System.out.println("Password\n");
        String password = utils.ReadFromKeyboard.readString();
        System.out.println("--Insira morada--\n");
        System.out.println("Rua:\n");
        String rua = utils.ReadFromKeyboard.readString();
        System.out.println("Código Postal:\n");
        String codigoPostal = utils.ReadFromKeyboard.readString();
        System.out.println("Localidade:\n");
        String localidade = utils.ReadFromKeyboard.readString();

        Morada morada=new Morada(rua,codigoPostal,localidade);
        
        //Cliente cliente=new Cliente(email,telemovel,username,password,morada);
        
    }
}
