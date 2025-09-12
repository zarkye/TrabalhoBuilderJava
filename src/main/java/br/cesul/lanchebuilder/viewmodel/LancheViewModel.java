package br.cesul.lanchebuilder.viewmodel;

import br.cesul.lanchebuilder.model.Lanche;
import br.cesul.lanchebuilder.repository.LancheRepository;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.NumberStringConverter;

public class LancheViewModel {


    private final StringProperty pao = new SimpleStringProperty();
    private final StringProperty carne = new SimpleStringProperty();
    private final DoubleProperty valorPedido = new SimpleDoubleProperty();
    private final StringProperty tomate = new SimpleStringProperty();
    private final StringProperty alface = new SimpleStringProperty();

    public StringProperty paoProperty(){return pao;}
    public StringProperty carneProperty(){return carne;}
    public DoubleProperty valorPedidoProperty(){return valorPedido;}

    private final ObservableList<Lanche>

    public LancheViewModel() {
        // Sempre que mudar algum ingrediente, recalcula o valor
        pao.addListener((obs, oldV, newV) -> calcularValor());
        carne.addListener((obs, oldV, newV) -> calcularValor());
        tomate.addListener((obs, oldV, newV) -> calcularValor());
        alface.addListener((obs, oldV, newV) -> calcularValor());

        lanches

    }

    private final LancheRepository repo = new LancheRepository();


    private void calcularValor() {
        double valor = 0.0;

        if (pao.get() != null) {
            switch (pao.get()) {
                case "Pão Brioche" -> valor += 5.0;
                case "Pão Integral" -> valor += 4.0;
                case "Pão Branco" -> valor += 3.0;
            }
        }

        if (carne.get() != null) {
            switch (carne.get()) {
                case "Mal Passado" -> valor += 8.0;
                case "Ao ponto" -> valor += 9.0;
                case "Bem Passado" -> valor += 10.0;
            }
        }

        if ("Sim".equals(tomate.get())) valor += 1.5;
        if ("Sim".equals(alface.get())) valor += 1.0;

        valorPedido.set(valor);
    }

}
