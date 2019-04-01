package br.com.fiap.imposto.model;

import java.util.Observable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import br.com.fiap.imposto.util.AliquotaSingleton;



// O Model é um Observable
// A Classe Pis da camada Model não possui referência a View ou ao Controller
public class Pis extends Observable implements Imposto {

    // LogManager retorna uma instância de Logger, passando o nome
    // da classe que será logada como parâmetro
    private static final Logger LOGGER
            = LogManager.getLogger(Pis.class.getName());


    // Recebe do Singleton o valor da propriedade aliquotaPis
    final float ALIQUOTA = Float
            .parseFloat(AliquotaSingleton.getInstance().getProperty("aliquotaPis"));
    float valorDoPis = 0;

    public Pis() {
        System.out.println("Construtor do Model chamado");
    }

    public float getALIQUOTA() {
        return ALIQUOTA;
    }

    public float getValorDoPis() {
        return valorDoPis;
    }

    public void calcularImposto(float valor) {

        valorDoPis = valor * ALIQUOTA;

        // Grava o log, conforme parametrizado
        LOGGER.info("Valor: " + valor
                + " Alíquota: " + ALIQUOTA
                + " Valor do Pis: " + valorDoPis);

        // setChanged Altera o estado interno do objeto
        // para true, pois houve alteração no estado valorDoPis
        setChanged();

        // Os observadores devem ser notificados
        // Envia o valor do PIS como parte da mensagem de
        // notificação para a View que é um Observer
        notifyObservers(valorDoPis);
    }

    @Override
    public String toString() {
        return "Pis [ALIQUOTA=" + ALIQUOTA
                + ", valorDoPis=" + valorDoPis + "]";
    }

}