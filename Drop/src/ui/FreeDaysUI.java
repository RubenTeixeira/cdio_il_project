/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.FreeDaysController;
import domain.DropPoint;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vascopinho
 */
class FreeDaysUI {

    private controller.FreeDaysController controller;

    public FreeDaysUI() throws SQLException {
        this.controller = new FreeDaysController();
        run();
    }

    private void run() {
        List<DropPoint> initializeFreeDays = controller.initializeFreeDays();
        for (DropPoint dp : initializeFreeDays) {
            System.out.println(dp.getId() + ". " + dp.getNome() + " - Dias grátis: " + dp.getFree_Days());
        }

        System.out.println("Seleccione id do DropPoint: \n");
        int droppoint = utils.ReadFromKeyboard.read();
        controller.selectDroppoint(droppoint);
        System.out.println("Inserir número de dias grátis: \n");
        int freeDays = utils.ReadFromKeyboard.read();
        controller.setFreeDays(freeDays);

        if (controller.updateFreeDays()) {
            System.out.println("Atualizado com sucesso");
        }

        initializeFreeDays = controller.initializeFreeDays();
        for (DropPoint dp : initializeFreeDays) {
            System.out.println(dp.getId() + ". " + dp.getNome() + " - Dias grátis: " + dp.getFree_Days());
        }

    }

}
