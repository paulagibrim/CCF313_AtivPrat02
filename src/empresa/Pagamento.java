package empresa;

public class Pagamento {
    double nHoras;
    double salarioBruto;
    double salarioLiquido;
    double descontoINSS;
    double descontoIR;
    double valorSalario;

    // Set functions
    public void setNHoras(double n) {
        nHoras = n;
    }

    public void setSalBrutoHorista() {
        salarioBruto = nHoras * valorSalario;
    }

    public void setSalBrutoEfetivo(double n) {
        salarioBruto = n;
    }

    // Método para calcular desconto no INSS
    public void setDescontoINSS(){
        if (salarioBruto<1659.38){
            descontoINSS = 0.08*salarioBruto;
        }else if(salarioBruto<2765.67){
            descontoINSS = 0.09*salarioBruto;
        }else if(salarioBruto<5531.32){
            descontoINSS = 0.11*salarioBruto;
        }else{
            descontoINSS = 0.11*5531.31;
        }
    }

    // Método para calcular o desconto do Imposto de Renda
    public void setDescontoImpRenda(){
        if (salarioBruto<1903.99){
            descontoIR = 0;
        }else if(salarioBruto<2826.66){
            descontoIR = (0.075*salarioBruto) - 142.8;
        }else if(salarioBruto<3751.06){
            descontoIR = (0.15*salarioBruto) - 354.80;
        }else if(salarioBruto<4664.69){
            descontoIR = (0.225*salarioBruto) - 636.13;
        }else{
            descontoIR = (0.275*salarioBruto) - 869.36;
        }
    }

    // Método para calcular salário líquido
    public void setSalarioLiquido(){
        salarioLiquido = salarioBruto-descontoINSS-descontoIR;
    }













}



