import java.util.Random;

public class Loja {
    private String numeroCartao;
    private String codigoVerificador;
    private boolean promocao;

    public Loja(String numeroCartao, String codigoVerificador) {
        this.numeroCartao = numeroCartao;
        this.codigoVerificador = codigoVerificador;
        this.promocao = false; // A promoção começa como falsa por padrão.
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getCodigoVerificador() {
        return codigoVerificador;
    }

    public boolean isPromocao() {
        return promocao;
    }

    public void setPromocao(boolean promocao) {
        this.promocao = promocao;
    }

    public void compraBooster(Inventario inventario) {
        int custoBooster = 120; // Valor hipotético

        if (inventario.getCardcoins() >= custoBooster) {
            inventario.setCardcoins(inventario.getCardcoins() - custoBooster);
            adicionarCartasAleatoriasAoInventario(inventario);
        } else {
            System.out.println("Saldo insuficiente de cardcoins.");
        }
    }

    public void compraBoosterEspecial(Inventario inventario) {
        int custoBoosterEspecial = 1.5 * 120;

        if (inventario.getCardcoins() >= custoBoosterEspecial) {
            inventario.setCardcoins(inventario.getCardcoins() - custoBoosterEspecial);
            if (promocao) {
                if (gerarNumeroAleatorio(1, 100) <= 1) {
                    Carta cartaUnica = gerarCartaUnica();
                    inventario.adicionarCarta(cartaUnica);
                } else {
                    adicionarCartasAleatoriasAoInventario(inventario);
                }
            } else {
                adicionarCartasAleatoriasAoInventario(inventario);
            }
        } else {
            System.out.println("Saldo insuficiente de cardcoins para o booster especial.");
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
        String[] nomesCartas = {"Carta1", "Carta2", "Carta3", "Carta4", "Carta5"};
        Random random = new Random();
        String nomeCartaAleatoria = nomesCartas[random.nextInt(nomesCartas.length)];
        return new Carta(nomeCartaAleatoria);
    }

    private int gerarNumeroAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private Carta gerarCartaUnica() {
        String nomeCartaUnica = "CartaUnica";
        return new Carta(nomeCartaUnica);
    }
}
