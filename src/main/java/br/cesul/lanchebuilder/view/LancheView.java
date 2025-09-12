package br.cesul.lanchebuilder.view;

import br.cesul.lanchebuilder.model.Lanche;
import br.cesul.lanchebuilder.viewmodel.LancheViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
    @FXML private TableColumn<Lanche, String> pedidoCol;
    @FXML private TableColumn<Lanche, String> valorCol;

    private final LancheViewModel vm = new LancheViewModel();

    @FXML
    private void initialize(){
        paoBox.setItems(FXCollections.observableArrayList("Pão Brioche", "Pão Integral", "Pão Branco"));
        carneBox.setItems(FXCollections.observableArrayList("Mal Passado", "Ao ponto", "Bem Passado"));
        tomateBox.setItems(FXCollections.observableArrayList("Sim", "Não"));
        alfaceBox.setItems(FXCollections.observableArrayList("Sim", "Não"));

        Bindings.bindBidirectional(paoBox.valueProperty(), vm.paoProperty());
        Bindings.bindBidirectional(carneBox.valueProperty(), vm.carneProperty());
        Bindings.bindBidirectional(tomateBox.valueProperty(), vm.tomateProperty());
        Bindings.bindBidirectional(alfaceBox.valueProperty(), vm.alfaceProperty());

        pedidoCol.setCellValueFactory(new PropertyValueFactory<>("pedido"));
        valorCol.setCellValueFactory(cellData ->
             new SimpleStringProperty(cellData.getValue().getValorFormatado())
        );
        lancheTable.setItems(vm.getLanches());

        pedidoButton.disableProperty().bind(vm.camposInvalidos());
        carneBox.disableProperty().bind(vm.paoInvalido());
        tomateBox.disableProperty().bind(vm.carneInvalido());
        alfaceBox.disableProperty().bind(vm.tomateInvalido());

        pedidoCusto.textProperty().bind(
                Bindings.format("R$ %.2f", vm.valorPedidoProperty())
        );

        pedidoButton.setOnAction(x -> {
            vm.adicionarLanche();
        });
    }

    @FXML
    private void onHover(MouseEvent event) {
        pedidoButton.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");
        pedidoButton.setCursor(Cursor.HAND);
    }
    @FXML
    private void onExit(MouseEvent event) {
        pedidoButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");
        pedidoButton.setCursor(Cursor.DEFAULT);
    }
}
