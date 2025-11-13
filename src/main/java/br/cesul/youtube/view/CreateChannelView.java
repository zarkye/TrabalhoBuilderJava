package br.cesul.youtube.view;

import br.cesul.youtube.viewmodel.CreateChannelViewModel;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.util.Duration;

public class CreateChannelView {

    @FXML private TextField channelNameField;
    @FXML private Button createButton;
    @FXML private Button backButton;

    private final CreateChannelViewModel viewModel = new CreateChannelViewModel();

    @FXML
    public void initialize() {
        createButton.setOnAction(e -> {
            if(!channelNameField.getText().isEmpty() && !channelNameField.getText().isBlank()){
                viewModel.createChannel(channelNameField.getText());
                openChannelList();
            }
        });

        backButton.setOnAction(e -> openChannelList());

        backButton.setOnMouseEntered(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), backButton);
            scaleUp.setToX(1.1);
            scaleUp.setToY(1.1);
            scaleUp.play();
        });
        backButton.setOnMouseExited(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), backButton);
            scaleUp.setToX(1.0);
            scaleUp.setToY(1.0);
            scaleUp.play();
        });

        createButton.setOnMouseEntered(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), createButton);
            scaleUp.setToX(1.1);
            scaleUp.setToY(1.1);
            scaleUp.play();
        });
        createButton.setOnMouseExited(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), createButton);
            scaleUp.setToX(1.0);
            scaleUp.setToY(1.0);
            scaleUp.play();
        });
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
