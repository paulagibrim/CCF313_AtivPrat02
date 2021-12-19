package empresa;

public class Data {
    int dia;
    int mes;
    int ano;

    public void setData(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int[] getData(){
        int[] data = new int[3];
        data[0] = this.dia;
        data[1] = this.mes;
        data[2] = this.ano;

        return data;
    }

}
