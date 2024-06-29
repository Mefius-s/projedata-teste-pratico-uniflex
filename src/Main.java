import Model.Funcionario;
import Model.Pessoa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat salarioFormatado = NumberFormat.getInstance(new Locale("pt", "br"));

        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(criarFuncionario("Maria", "18/10/2000", dataFormatada, BigDecimal.valueOf(2009.44), "Operador"));
        funcionarios.add(criarFuncionario("João", "12/05/2000", dataFormatada, BigDecimal.valueOf(2284.38), "Operador"));
        funcionarios.add(criarFuncionario("Caio", "02/05/1961", dataFormatada, BigDecimal.valueOf(9836.14), "Coordenador"));
        funcionarios.add(criarFuncionario("Miguel", "14/10/1988", dataFormatada, BigDecimal.valueOf(19119.88), "Diretor"));
        funcionarios.add(criarFuncionario("Alice", "05/01/1995", dataFormatada, BigDecimal.valueOf(2234.68), "Recepcionista"));
        funcionarios.add(criarFuncionario("Heitor", "19/11/1999", dataFormatada, BigDecimal.valueOf(1582.72), "Operador"));
        funcionarios.add(criarFuncionario("Arthur", "31/03/1993", dataFormatada, BigDecimal.valueOf(4071.84), "Contador"));
        funcionarios.add(criarFuncionario("Laura", "08/07/1994", dataFormatada, BigDecimal.valueOf(3017.45), "Gerente"));
        funcionarios.add(criarFuncionario("Heloísa", "24/05/2003", dataFormatada, BigDecimal.valueOf(1606.85), "Eletricista"));
        funcionarios.add(criarFuncionario("Helena", "02/09/1996", dataFormatada, BigDecimal.valueOf(2799.93), "Gerente"));

        System.out.println("\n<----------------------------------------------------------------------------------------------------------------->");
        System.out.println("3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela.");
        System.out.println(funcionarios + "\n" +
                "<----------------------------------------------------------------------------------------------------------------->");



        System.out.println("\n<----------------------------------------------------------------------------------------------------------------->");
        funcionarios.remove(1);
        System.out.println("3.2 – Remover o funcionário “João” da lista.");
        System.out.println(funcionarios + "\n" +
                "<----------------------------------------------------------------------------------------------------------------->");



        System.out.println("\n<----------------------------------------------------------------------------------------------------------------->");
        System.out.println("3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:\n" +
                "• informação de data deve ser exibido no formato dd/mm/aaaa;\n" +
                "• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.");
        for(Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
        System.out.println("<----------------------------------------------------------------------------------------------------------------->");



        System.out.println("\n<----------------------------------------------------------------------------------------------------------------->");
        Funcionario funcionario = new Funcionario();
        funcionario.aumentoDeSalario(funcionarios, new BigDecimal("0.10"));

        System.out.println("3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.");
        for(Funcionario salario : funcionarios) {
            System.out.println(salario);
        }
        System.out.println("<----------------------------------------------------------------------------------------------------------------->");



        System.out.println("\n<----------------------------------------------------------------------------------------------------------------->");
        Map<String, List<Funcionario>> funcaoFuncionario = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.\n" +
                "3.6 – Imprimir os funcionários, agrupados por função.\"");
        for(String key : funcaoFuncionario.keySet()) {
            System.out.println("\nFunção: " + key + "\n" + funcaoFuncionario.get(key));
        }
        System.out.println("<----------------------------------------------------------------------------------------------------------------->");



        System.out.println("\n<----------------------------------------------------------------------------------------------------------------->");
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .toList();

        System.out.println("3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.");
        for (Funcionario f : aniversariantes) {
            System.out.println("Data: " + f.getDataNascimento().format(dataFormatada));
            System.out.println("Funcionário: " + f.getNome() + "\n");
        }
        System.out.println("<----------------------------------------------------------------------------------------------------------------->");



        System.out.println("\n<----------------------------------------------------------------------------------------------------------------->");
        System.out.println("3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.");
        Optional<Funcionario> funcionarioMaisVelho = funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento));

        LocalDate ano = LocalDate.now();
        int idade = Period.between(funcionarioMaisVelho.get().getDataNascimento(), ano).getYears();

        System.out.println("Funcionário com a maior idade");
        System.out.println("Nome: " + funcionarioMaisVelho.get().getNome());
        System.out.println("Idade: " + idade + " anos");
        System.out.println("<----------------------------------------------------------------------------------------------------------------->");



        System.out.println("\n<----------------------------------------------------------------------------------------------------------------->");
        System.out.println("3.10 – Imprimir a lista de funcionários por ordem alfabética.");
        funcionarios.sort(Comparator.comparing(Pessoa::getNome));
        for (Funcionario f : funcionarios){
            System.out.println(f);
        }
        System.out.println("<----------------------------------------------------------------------------------------------------------------->");



        System.out.println("\n<----------------------------------------------------------------------------------------------------------------->");
        System.out.println("3.11 – Imprimir o total dos salários dos funcionários.");
        int somaSalarios = funcionarios.stream().mapToInt(f -> f.getSalario().intValue()).sum();
        System.out.println("Valor total dos salários dos funcionários: R$" + salarioFormatado.format(somaSalarios));
        System.out.println("<----------------------------------------------------------------------------------------------------------------->");



        System.out.println("\n<----------------------------------------------------------------------------------------------------------------->");
        System.out.println("3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        for (Funcionario f : funcionarios){
            BigDecimal salarioMinimoPorFuncionario = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println("Funcionário: " + f.getNome() + ", recebe " + salarioMinimoPorFuncionario + " salários mínimos");
        }
        System.out.println("<----------------------------------------------------------------------------------------------------------------->");

    }

    public static Funcionario criarFuncionario(String nome, String dataNascimentoString, DateTimeFormatter dataFormatada, BigDecimal salario, String funcao) {
        Funcionario funcionario = new Funcionario();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, dataFormatada);
        funcionario.setDataNascimento(dataNascimento);
        funcionario.setNome(nome);
        funcionario.setSalario(salario);
        funcionario.setFuncao(funcao);
        return funcionario;
    }
}