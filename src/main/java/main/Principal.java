package main;

import java.util.Scanner;
import modelos.Aluno;
import modelos.Curso;

public class Principal {

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int option;

       do{
           System.out.println("\n1 - CADASTRAR ALUNO");
            System.out.println("2 - INFORMAR DADOS DE UM ALUNO");
            System.out.println("3 - CADASTRAR CURSO");
            System.out.println("4 - LISTAR CURSOS");
            System.out.println("5 - SAIR");
            
            System.out.print("--> Qual operacao deseja realizar? ");
           option = Integer.parseInt(sc.nextLine());


           switch (option){
               case 1:
                   System.out.println("Nome do aluno: ");
                   String nome = sc.nextLine();
                   System.out.println("ra: ");
                   String ra = sc.nextLine();
                   Curso.listarCursos();
                   System.out.print("Digite o ID do curso do aluno: ");
                   int cursoId = Integer.parseInt(sc.nextLine());
                   
                   Aluno novoAluno = new Aluno(nome, cursoId, ra);
                    if (novoAluno.salvar()) {
                        System.out.println("Aluno salvo com sucesso!");
                    } else {
                        System.out.println("Erro ao salvar aluno.");
                    }
                    break;


               case 2:
                   System.out.println("RA do aluno: ");
                   String raBusca = sc.nextLine();
                   Aluno alunoEncontrado = Aluno.buscarAluno(raBusca);

                   if (alunoEncontrado != null){
                       System.out.println(alunoEncontrado);
                   }else{
                       System.out.println("RA nao cadastrado!");
                   }
                   break;
                   
               case 3:
                   System.out.print("Nome do curso: ");
                   String nomeCurso = sc.nextLine();
                   Curso novoCurso = new Curso(nomeCurso);
                   
                   if (novoCurso.salvar()) {
                        System.out.println("Curso cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar curso.");
                    }
                    break;
                    
               case 4:
                   System.out.println("Lista de Cursos: ");
                   Curso.listarCursos();
           }
       }while (option != 5);
       System.out.println("Finalizando programa...");
    }
}