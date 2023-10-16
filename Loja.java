public class Loja extends Enumerate.raridadedascartas {
    private String numeroCartao;
    private String codigoVerificador;
    private boolean promocao;

    public Loja(String numeroCartao, String codigoVerificador) {
        this.numeroCartao = numeroCartao;
        this.codigoVerificador = codigoVerificador;
        this.promocao = false; // A promoção começa como falsa por padrão.
    }

    public Loja(String numeroCartao, String codigoVerificador, raridadedascartas raridade) {
        super(raridade.name, raridade.dropProbability);
        this.numeroCartao = numeroCartao;
        this.codigoVerificador = codigoVerificador;
        this.promocao = false;
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
        int custoBoosterEspecial = (int)(1.5 * 120); // Custo do booster especial

        if (inventario.getCardcoins() >= custoBoosterEspecial) {
            inventario.setCardcoins(inventario.getCardcoins() - custoBoosterEspecial);
            
            for (int i = 0; i < 12; i++) {
                Carta cartaAleatoria = gerarCartaComRaridadeAleatoria();
                inventario.adicionarCarta(cartaAleatoria);
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

    private Carta gerarCartaComRaridadeAleatoria() {
        Enumerate.raridadedascartas raridade = Enumerate.raridadedascartas.getRandomRarity();
        String nomeCartaAleatoria = "Carta" + raridade.getName();
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

}
