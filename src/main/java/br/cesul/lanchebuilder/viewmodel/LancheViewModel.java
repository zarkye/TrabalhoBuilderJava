package br.cesul.lanchebuilder.viewmodel;

import br.cesul.lanchebuilder.model.Lanche;
import br.cesul.lanchebuilder.repository.CounterRepository;
import br.cesul.lanchebuilder.repository.LancheRepository;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
    public StringProperty tomateProperty(){ return tomate; }
    public StringProperty alfaceProperty(){return alface;}

    private final LancheRepository repo = new LancheRepository();
    private final CounterRepository counter = new CounterRepository();

    private final ObservableList<Lanche> lanches = FXCollections.observableArrayList();
    public ObservableList<Lanche> getLanches(){return lanches;}

    public BooleanBinding camposInvalidos;
    public BooleanBinding paoInvalido;
    public BooleanBinding carneInvalido;
    public BooleanBinding tomateInvalido;
    public BooleanBinding alfaceInvalido;

    public LancheViewModel() {
        lanches.addAll(repo.findAll());
        lanches.addListener((ListChangeListener<? super Lanche>) c -> {
            while(c.next()){
                if(c.wasAdded()){
                    c.getAddedSubList().forEach(repo::insert);
                }
            }
        });

        // Sempre que mudar algum ingrediente, recalcula o valor

        pao.addListener((obs, oldV, newV) -> calcularValor());
        carne.addListener((obs, oldV, newV) -> calcularValor());
        tomate.addListener((obs, oldV, newV) -> calcularValor());
        alface.addListener((obs, oldV, newV) -> calcularValor());

        camposInvalidos = paoProperty().isNull()
                .or(carneProperty().isNull())
                .or(tomateProperty().isNull())
                .or(alfaceProperty().isNull());

        paoInvalido = paoProperty().isNull();
        carneInvalido = carneProperty().isNull();
        tomateInvalido = tomateProperty().isNull();
        alfaceInvalido = alfaceProperty().isNull();

    }
    public BooleanBinding camposInvalidos(){
        return camposInvalidos;
    }

    public BooleanBinding paoInvalido(){
        return paoInvalido;
    }
    public BooleanBinding carneInvalido(){
        return carneInvalido;
    }
    public BooleanBinding tomateInvalido(){
        return tomateInvalido;
    }
    public BooleanBinding alfaceInvalido(){
        return alfaceInvalido;
    }

    public void resetCampos(){
        pao.set(null);
        carne.set(null);
        tomate.set(null);
        alface.set(null);
    }

    public void adicionarLanche() {
        lanches.add(0, criarLanche());
        resetCampos();
    }

    private boolean getTomateBoolean(){
        return "Sim".equalsIgnoreCase(tomate.get());
    }

    private boolean getAlfaceBoolean(){
        return "Sim".equalsIgnoreCase(alface.get());
    }

    private Lanche criarLanche(){
        return new Lanche.Builder()
                .id(counter.getNextPedidoId())
                .comPao(pao.get())
                .comCarne(carne.get())
                .comTomate(getTomateBoolean())
                .comAlface(getAlfaceBoolean())
                .custo(valorPedido.get())
                .build();
    }

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
