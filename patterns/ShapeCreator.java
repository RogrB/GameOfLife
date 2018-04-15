package patterns;

public class ShapeCreator {
    
    public void Exploder() {
        Exploder exploder = new Exploder();
        exploder.createPattern();
    }
    
    public void Glider() {
        Glider glider = new Glider();
        glider.createPattern();
    }
    
    public void LighWeightSpaceShip() {
        LightWeightSpaceShip lws = new LightWeightSpaceShip();
        lws.createPattern();
    }
    
    public void SmallExploder() {
        SmallExploder smallExploder = new SmallExploder();
        smallExploder.createPattern();
    }
    
    public void TenCellRow() {
        System.out.println("jallo");
        TenCellRow tenCellRow = new TenCellRow();
        tenCellRow.createPattern();
    }
    
    public void Tumbler() {
        Tumbler tumbler = new Tumbler();
        tumbler.createPattern();
    }
    
}
