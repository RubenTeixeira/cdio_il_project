/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.MaintenancePickupController;
import domain.Prateleira;
import java.util.List;

/**
 *
 * @author MarcoSousa
 */
class MaintenancePickupUI {

    private MaintenancePickupController controller;

    public MaintenancePickupUI() {
        controller = new MaintenancePickupController("settings/settings.txt");
    }

    public void run() {

        System.out.println("Insira um token válido: ");
        String tokenCode = utils.ReadFromKeyboard.readString();

        List<Prateleira> cells = this.controller.getListOfCellsWithDeliveriesOutOfDate(tokenCode);

        if(cells == null) {
            System.out.println("Não foram encontradas prateleira com encomendas por levantar para o token inserido");
            System.exit(0);
        }

        System.out.println("\nForam encontradas " + cells.size() + " prateleiras com reservas expiradas.");

        for (Prateleira cell : cells) {
            System.out.println(cell.getDesc());
            boolean open = utils.ReadFromKeyboard.confirma("Pretende abrir a prateleira indicada?(S/N)");
            if(open) {
                controller.selectCell(cell);

                controller.openCell();

                boolean close = utils.ReadFromKeyboard.confirma("Pretende fechar a prateleira? (S/N)");

                if(close) {
                    if(this.controller.closeShelf()) {
                        System.out.println("Ação efetuada com sucesso\n");
                    } else {
                        System.out.println("Erro ao fechar a prateleira\n");
                    }
                }

            }

        }

    }
}
