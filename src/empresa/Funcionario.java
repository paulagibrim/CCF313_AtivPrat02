package empresa;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Funcionario {
    String nome;
    Data dataNascimento; //dia,mes,ano
    String tipoContrato;
    //double valorSalario;
    int nFilhosMenores;
    Data dataAdmissao; //dia,mes,ano

    Pagamento pagamento = new Pagamento();

    // Inicializar um novo funcionário
    Funcionario(String nome, Data dataNascimento){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    // Set functions
    public void setTipoContrato(String contrato){
        Scanner input = new Scanner(System.in);
        while (!Objects.equals(contrato, "Efetivo") && !Objects.equals(contrato, "Horista")){
            System.out.println("[ ! ] ERROR: Tipo de contrato inválido!");
            System.out.println("Qual o tipo de contrato? (Efetivo ou Horista):");
            contrato = input.nextLine();
        }

        this.tipoContrato = contrato;
    }
    public void setValorSalario(double valor){
        this.pagamento.valorSalario = valor;
    }
    public void setnFilhosMenores(int n){
        this.nFilhosMenores = n;
    }
    public void dataAdmissao(Data admissao){
        this.dataAdmissao = admissao;
    }

    //
    public void setPagamento(double nHoras){
        pagamento.setNHoras(nHoras);

        if (Objects.equals(this.tipoContrato, "Horista")){
            pagamento.setSalBrutoHorista();
        }
        else{
            pagamento.setSalBrutoEfetivo(this.pagamento.valorSalario);
        }

        this.pagamento.setDescontoINSS();
        this.pagamento.setDescontoImpRenda();
        this.pagamento.setSalarioLiquido();
    }

    // Get functions
    public String getTipoContrato() {
        return this.tipoContrato;
    }
    public double getValorSalario() {
        return this.pagamento.valorSalario;
    }
    public int getNFilhosMenores() {
        return this.nFilhosMenores;
    }
    public String getDataAdmissao() {
        return String.format("%02d", dataAdmissao.dia) + "/" + String.format("%02d", dataAdmissao.mes) + "/" +
                String.format("%04d", dataAdmissao.ano);
    }
    public String getDataNascimento() {
        return String.format("%02d", dataNascimento.dia) + "/" + String.format("%02d", dataNascimento.mes) + "/" +
                String.format("%04d", dataNascimento.ano);
    }

    // Método para contratação
    public static Funcionario contratar(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--------- CADASTRE FUNCIONARIO ---------\n");

        System.out.println("Nome: ");
        String aux = input.nextLine();
        System.out.println("Data de nascimento (dd/mm/aaaa): ");
        String nascimento = input.nextLine();
        String[] data_nasc = nascimento.split("/");
        Data nasc = new Data();
        nasc.setData(parseInt(data_nasc[0]), parseInt(data_nasc[1]), parseInt(data_nasc[2]));

        Funcionario trabalhador = new Funcionario(aux, nasc);

        System.out.println("Qual o tipo de contrato? (Efetivo ou Horista):");
        aux = input.nextLine();
        trabalhador.setTipoContrato(aux);

        System.out.println("Qual o valor do salário? :");
        aux = input.nextLine();
        trabalhador.setValorSalario(Double.parseDouble(aux));

        System.out.println("Quantos filhos menores de 18 anos ele(a) possui? :");
        aux = input.nextLine();
        trabalhador.setnFilhosMenores(parseInt(aux));

        System.out.println("Data de admissao (dd/mm/aaaa): ");
        nascimento = input.nextLine();
        data_nasc = nascimento.split("/");
        Data admis = new Data();
        admis.setData(parseInt(data_nasc[0]), parseInt(data_nasc[1]), parseInt(data_nasc[2]));
        trabalhador.dataAdmissao(admis);

        System.out.println("Qual o número de horas trabalhadas? :");
        aux = input.nextLine();
        trabalhador.setPagamento(Double.parseDouble(aux));

        return trabalhador;
    }


    // Método para mostrar folha de pagamento
    public void mostrarFolhaPagamento(){


        System.out.println("\n---------- FOLHA DE PAGAMENTO ----------");
        System.out.printf(" Salario bruto: R$ %.2f %n", pagamento.salarioBruto);
        System.out.printf(" Desconto INSS: R$ %.2f %n", pagamento.descontoINSS);
        System.out.printf(" Desconto do Imposto de Renda: R$ %.2f %n", pagamento.descontoIR);
        System.out.println(" ");
        System.out.printf(" Salario líquido: R$ %.2f %n", pagamento.salarioLiquido);
    }

    // Método para mostrar perfil do funcionário
    public void mostrarPerfil(){
        System.out.println("-------- PERFIL DE FUNCIONARIO --------");
        System.out.println(
                "Nome: " + nome +
                "\nData de nascimento: " + getDataNascimento() +
                "\nTipo de contrato: " + getTipoContrato() );
        System.out.printf("Salario (por mes ou hora): R$ %.2f %n", getValorSalario());
        System.out.println("Quantidade de filhos com menos de 18 anos: " + getNFilhosMenores() +
                "\nData de admissao: " + getDataAdmissao()
        );

    }


    public static void main(String[] args) {
        Funcionario trabalhador = contratar();
        trabalhador.mostrarPerfil();
        trabalhador.mostrarFolhaPagamento();

    }
}
