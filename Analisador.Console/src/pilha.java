public class pilha {
    private char dados[] = new char[500000];
    private int topo = -1;

    public int getTamanho()
    {
        return topo+1;
    }

    public void empilha (char valor) throws Exception {
        if(getTamanho() != Integer.MAX_VALUE){
            topo++;
            dados[topo] = valor;
        }
        else{
            throw new Exception("Não a mais espaços na pilha");
        }
    }

    public void desempilha () throws Exception {
        if(getTamanho() > 0){
            topo--;
        }
        else{
            throw new Exception("Não a mais elementos na pilha");
        }
    }

    public char topo() throws Exception {
        if(getTamanho() == 0)
            throw new Exception("Não exista nenhum elemento na pilha!");
        else
            return dados[topo];
    }
}
