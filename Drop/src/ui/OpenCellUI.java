/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.OpenCellController;

/**
 *
 * @author 1140864
 */
class OpenCellUI {

    private controller.OpenCellController controller;

    public OpenCellUI() {
        controller = new OpenCellController("settings/settings.txt");
        run();
    }

    private void run() {

        System.out.println("Please provide a token: ");
        String token = utils.ReadFromKeyboard.readString();

        String cell = this.controller.beginOpenCell(token);

        if(cell == null) {
            System.out.println("Cell not found or invalid token.\n");
        } else {
            System.out.println("Requested Cell ID: "+cell + "\n");
            
            boolean open = utils.ReadFromKeyboard.confirma("Would you like to open the cell?(S/N)");

            if(open) {
                this.controller.openCell();
            }
            else{
                System.exit(0);
            }
            boolean close = utils.ReadFromKeyboard.confirma("Would you like to finish the operation? (S/N)");

            if(close) {
                this.controller.closeCell();
            }
            
            System.out.println("Operation successfull.");
        }
    }
}
