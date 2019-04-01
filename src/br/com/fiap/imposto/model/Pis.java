package br.com.fiap.imposto.model;

import br.com.fiap.imposto.util.AliquotaSingleton;

import java.util.Observable;

// O Model é um Observable
// A Classe Pis da camada Model não possui referência a View ou ao // Controller
// Ao implementar Imposto, teremos maior flexibilidade no Controller
public class Pis extends Observable implements Imposto{

    final float ALIQUOTA = Float.parseFloat(AliquotaSingleton.getInstance().getProperty("aliquotaPis"));
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