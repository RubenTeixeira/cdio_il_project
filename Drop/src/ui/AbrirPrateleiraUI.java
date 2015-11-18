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
            System.out.println("Prateleira impossivel de localizar para o token inserido\n");
        } else {
            System.out.println(prateleira + "\n");
            System.out.println("Pretende abrir a prateleira indicada?");
            boolean open = utils.ReadFromKeyboard.confirma();

            if(open) {
                this.controller.abrePrateleira();
            }
            
            System.out.println("Pretende fechar a prateleira indicada?");
            boolean close = utils.ReadFromKeyboard.confirma();

            if(close) {
                this.controller.fechaPrateleira();
            }
            
            System.out.println("Ação efetuada com sucesso");
        }
    }
}
