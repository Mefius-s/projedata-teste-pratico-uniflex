package Model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;


    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public void aumentoDeSalario(List<Funcionario> funcionarios, BigDecimal percentualAumento) {
        for(Funcionario funcionario : funcionarios) {
            BigDecimal aumento = funcionario.getSalario().multiply(percentualAumento);
            BigDecimal novoSalario = funcionario.getSalario().add(aumento);
            funcionario.setSalario(novoSalario);
        }
    }

    @Override
    public String toString() {

        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat salarioFormatado = NumberFormat.getInstance(new Locale("pt", "br"));

        return "Funcionario{" +
                "Nome: '" + getNome() + '\'' +
                ", Data de nascimento: " + getDataNascimento().format(dataFormatada) +
                ", Salário: R$" + salarioFormatado.format(salario) +
                ", Função: '" + funcao + '\'' + "\n" +
                '}';
    }
}
