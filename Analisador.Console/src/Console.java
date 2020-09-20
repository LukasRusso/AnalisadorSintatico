import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    private static ArrayList<Character> abrir = new ArrayList<Character>();
    private static ArrayList<Character> fechar = new ArrayList<Character>();

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        boolean sair = false;
        criaVetor();

        while(!sair){
            sair = home();
        }

    }

    private static void criaVetor(){
        abrir.add('<');
        abrir.add('{');
        abrir.add('[');
        abrir.add('(');
        fechar.add('>');
        fechar.add('}');
        fechar.add(']');
        fechar.add(')');
    }

    public static boolean home()
    {
        int opcao = -1;

        while (true){
            try{
                System.out.println();
                System.out.println("Bem vindo ao Analisador Sintático!");
                System.out.println("0 - Sair!");
                System.out.println("1 - Realizar análise sintática!");
                opcao = scan.nextInt();
                scan.nextLine();

                switch (opcao){
                    case 0:
                        return true;
                    case 1:
                        analizadorLexico();
                        return false;
                    default:
                        System.out.println("Informe somente zero ou um!");
                }
            }
            catch(Exception e){
                System.out.println("Informe somente números!");
                scan.nextLine();
            }
        }
    }

    public static void analizadorLexico(){
        String alfabeto = "<{[()]}>";
        String sequencia = "";
        String n_alf = "";

        System.out.println();
        System.out.println("\tAlfabeto - <{[()]}>");
        System.out.println("Digite a sequencia de Caracteres a ser analisada:");
        sequencia = scan.nextLine();

        for(Character c : sequencia.toCharArray()){
            if(!alfabeto.contains(c.toString())){
                n_alf += c.toString() + " - ";
            }
        }

        if(n_alf.isEmpty())
            analizadorSintatico(sequencia);
        else
        {
            System.out.println();
            System.out.println(consoleColors.BLUE + "Caracteres fora do alfabeto:" + consoleColors.RESET);
            System.out.println(n_alf.substring(0, n_alf.length() -2));
            return;
        }

    }

    public static void analizadorSintatico(String seq){
        pilha p = new pilha();

        char ent[] = seq.toCharArray();
        try{
            for(int i = 0; i < seq.length(); i++){
                if(abrir.contains(ent[i])){
                    p.empilha(ent[i]);
                }
                else if(p.getTamanho() != 0){
                    if(abrir.indexOf(p.topo()) == fechar.indexOf(ent[i]))
                        p.desempilha();
                    else{
                        System.out.println(consoleColors.RED + "Parser Inválido!" + consoleColors.RESET);
                        return;
                    }
                }
                else{
                    System.out.println(consoleColors.RED + "Parser Inválido!" + consoleColors.RESET);
                    return;
                }
            }

            if(p.getTamanho() == 0)
                System.out.println(consoleColors.YELLOW + "Parser Correto!" + consoleColors.RESET);
            else
                System.out.println(consoleColors.RED + "Parser Inválido!" + consoleColors.RESET);
        }
        catch (Exception e){
            System.out.println(consoleColors.RED + e.getMessage() + consoleColors.RESET);
            return;
        }
    }
}
