package controller;

import dal.SeeInfoDAL;
import domain.DropPoint;
import java.util.List;

public class SeeInfoDPController {

    private SeeInfoDAL seeInfoDAL;
    private DropPoint dropPoint;

    public SeeInfoDPController() {
        seeInfoDAL = new SeeInfoDAL();   
    }

    public List<DropPoint> listDropPoints() {
        return seeInfoDAL.getListDropPoints();
    }

    public void closeConection() {
        seeInfoDAL.closeConection();
    }

    public void selectDropPoint(DropPoint dropChosen) {
        this.dropPoint = dropChosen;
    }

    public String getDropPointName()
    {
        return this.dropPoint.getNome();
    }

    public String getDropPointInfo() {
        return String.format("%s\n%s\n%s",
                this.dropPoint.getNome(),
                this.seeInfoDAL.getDropPointAdressByID(dropPoint.getIdMorada()),
                this.seeInfoDAL.getDropPointShelvesByDropPointID(dropPoint.getId()));
    }

    public String getDropPointCoor() {
        return this.seeInfoDAL.getDropPointCoorByAdressID(dropPoint.getIdMorada());
    }

      
}
