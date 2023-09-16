import java.util.Random;

public class Loja {
    private String numeroCartao;
    private String codigoVerificador;

    public Loja(String numeroCartao, String codigoVerificador) {
        this.numeroCartao = numeroCartao;
        this.codigoVerificador = codigoVerificador;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getCodigoVerificador() {
        return codigoVerificador;
    }

    public void compraBooster(Inventario inventario) {
        int custoBooster = 120; //Valor hipotético
        
        if (inventario.getCardcoins() >= custoBooster) {
            inventario.setCardcoins(inventario.getCardcoins() - custoBooster);
            adicionarCartasAleatoriasAoInventario(inventario);
        } else {
            System.out.println("Saldo insuficiente de cardcoins.");
        }
    }

    private void adicionarCartasAleatoriasAoInventario(Inventario inventario) {
        Random random = new Random();
        int quantidadeCartasNoBooster = 12;

        for (int i = 0; i < quantidadeCartasNoBooster; i++) {

            Carta cartaAleatoria = gerarCartaAleatoria();

            if (inventario.contagemCarta(cartaAleatoria) < 3) {
                inventario.adicionarCarta(cartaAleatoria);
            } else {
                int valorCardcoins = 10; 
                inventario.setCardcoins(inventario.getCardcoins() + valorCardcoins);
            }
        }
    }

    private Carta gerarCartaAleatoria() {
        String[] nomesCartas = {"Carta1", "Carta2", "Carta3", "Carta4", "Carta5"};// Nomes hipotéticos para as cartas.
        Random random = new Random();
        String nomeCartaAleatoria = nomesCartas[random.nextInt(nomesCartas.length)];
        return new Carta(nomeCartaAleatoria);
    }
}
