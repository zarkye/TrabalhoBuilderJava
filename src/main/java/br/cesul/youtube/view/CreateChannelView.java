package br.cesul.youtube.view;

import br.cesul.youtube.viewmodel.CreateChannelViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class CreateChannelView {

    @FXML private TextField channelNameField;
    @FXML private Button createButton;
    @FXML private Button backButton;

    private final CreateChannelViewModel viewModel = new CreateChannelViewModel();

    @FXML
    public void initialize() {
        createButton.setOnAction(e -> {
            viewModel.createChannel(channelNameField.getText());
            openChannelList();
        });

        backButton.setOnAction(e -> openChannelList());
    }

    private void openChannelList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/cesul/youtube/ChannelListView.fxml"));
            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
