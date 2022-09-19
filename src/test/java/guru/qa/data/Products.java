package guru.qa.data;

public class Products {
    private String designerName;
    private String color;
    private int cost;
    Availability availability;
    private String[] structure;


    public Products() {
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setStructure(String[] structure){
        this.structure=structure;
    }

    public String[] getStructure(){
        return structure;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
    public Availability getAvailability(){
        return availability;
    }

    public class Availability{
        private int size;
        private boolean inStock;
        public boolean isInStock() {
            return inStock;
        }
        public void setInStock(boolean inStock) {
            this.inStock = inStock;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
}
