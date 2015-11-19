/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.AbrirPrateleiraController;

/**
 *
 * @author 1140864
 */
class AbrirPrateleiraUI {

    private controller.AbrirPrateleiraController controller;

    public AbrirPrateleiraUI() {
        controller = new AbrirPrateleiraController();
        run();
    }

    private void run() {

        System.out.println("Insira o token: ");
        String token = utils.ReadFromKeyboard.readString();

        String prateleira = this.controller.iniciaAberturaPrateleira(token);

        if(prateleira == null) {
            System.out.println("Prateleira impossivel de localizar para o token inserido ou token inválido\n");
        } else {
            System.out.println(prateleira + "\n");
            
            boolean open = utils.ReadFromKeyboard.confirma("Pretende abrir a prateleira indicada?(S/N)");

            if(open) {
                this.controller.abrePrateleira();
            }
            
            boolean close = utils.ReadFromKeyboard.confirma("Pretende fechar a prateleira? (S/N)");

            if(close) {
                this.controller.fechaPrateleira();
            }
            
            System.out.println("Ação efetuada com sucesso");
        }
    }
}
