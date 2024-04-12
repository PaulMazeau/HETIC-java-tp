public class Ligne {
    private int id;
    private int param1;
    private int param2;
    private String operateur;
    private int index;
    private int fichierId;

    public Ligne(int id, int param1, int param2, String operateur, int index, int fichierId) {
        this.id = id;
        this.param1 = param1;
        this.param2 = param2;
        this.operateur = operateur;
        this.index = index;
        this.fichierId = fichierId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParam1() {
        return param1;
    }

    public void setParam1(int param1) {
        this.param1 = param1;
    }

    public int getParam2() {
        return param2;
    }

    public void setParam2(int param2) {
        this.param2 = param2;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getFichierId() {
        return fichierId;
    }

    public void setFichierId(int fichierId) {
        this.fichierId = fichierId;
    }
}
