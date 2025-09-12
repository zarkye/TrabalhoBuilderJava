package br.cesul.lanchebuilder.view;

import br.cesul.lanchebuilder.model.Lanche;
import br.cesul.lanchebuilder.viewmodel.LancheViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;
import javax.naming.Binding;


public class LancheView {
    @FXML private ComboBox<String> paoBox;
    @FXML private ComboBox<String> carneBox;
    @FXML private ComboBox<String> tomateBox;
    @FXML private ComboBox<String> alfaceBox;
    @FXML private Button pedidoButton;
    @FXML private Label pedidoCusto;
    @FXML private TableView<Lanche> lancheTable;

    private final LancheViewModel vm = new LancheViewModel();

    @FXML
    private void initialize(){
        paoBox.setItems(FXCollections.observableArrayList("Pão Brioche", "Pão Integral", "Pão Branco"));
        carneBox.setItems(FXCollections.observableArrayList("Mal Passado", "Ao ponto", "Bem Passado"));
        tomateBox.setItems(FXCollections.observableArrayList("Sim", "Não"));
        alfaceBox.setItems(FXCollections.observableArrayList("Sim", "Não"));
        Bindings.bindBidirectional(vm.);
    }


}
