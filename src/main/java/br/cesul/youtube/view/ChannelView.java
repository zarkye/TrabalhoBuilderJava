package br.cesul.youtube.view;

import br.cesul.youtube.viewmodel.ChannelViewModel;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ChannelView {
    @FXML
    public AnchorPane rootPane;

    @FXML
    private Label channelName;

    @FXML
    private Label subscribersLabel;

    @FXML
    private Button subscribeButton;

    @FXML
    private Button backButton;

    @FXML
    private ImageView channelImage;

    @FXML
    private ImageView channelBackgroundImage;

    private ChannelViewModel viewModel;

    @FXML
    public void initialize() {
        rootPane.sceneProperty().addListener((obs, oldscene, newscene) -> {
            if(newscene != null){
                newscene.setOnKeyPressed(e -> {
                    if(e.getCode() == javafx.scene.input.KeyCode.ESCAPE){
                        goBack();
                    }
                });
            }
        });
    }

    public void loadChannel(String channelNameStr) {
        this.viewModel = new ChannelViewModel(channelNameStr);

        // Bindings
        channelName.textProperty().bind(viewModel.nameProperty());
        subscribersLabel.textProperty().bind(viewModel.subscribersProperty().asString("Inscritos: %d"));

        subscribeButton.setOnAction(e -> {
            viewModel.subscribe();

            // Animação label inscritos
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(50), subscribersLabel);
            scaleUp.setToX(1.1);
            scaleUp.setToY(1.1);
            scaleUp.setAutoReverse(true);
            scaleUp.setCycleCount(2);
            scaleUp.play();

            // Botão
            ScaleTransition scaleUpButton = new ScaleTransition(Duration.millis(50), subscribeButton);
            scaleUpButton.setToX(1.2);
            scaleUpButton.setToY(1.2);

            ScaleTransition scaleDownButton = new ScaleTransition(Duration.millis(50), subscribeButton);
            scaleDownButton.setToX(1.1);
            scaleDownButton.setToY(1.1);

            scaleUpButton.setOnFinished(event -> scaleDownButton.play());
            scaleUpButton.play();
        });

        backButton.setOnAction(e -> {
            goBack();
        });

        subscribeButton.setOnMouseEntered(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), subscribeButton);
            scaleUp.setToX(1.1);
            scaleUp.setToY(1.1);
            scaleUp.play();

            subscribeButton.setStyle(
                    "-fx-background-color: #660e00;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 16px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 50;" +
                            "-fx-padding: 10 30 10 30;" +
                            "-fx-cursor: hand;"
            );
        });

        subscribeButton.setOnMouseExited(e -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), subscribeButton);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);
            scaleDown.play();

            subscribeButton.setStyle(
                    "-fx-background-color: red;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 16px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 50;" +
                            "-fx-padding: 10 30 10 30;"
            );
        });

        Image userImage = new Image(getClass().getResourceAsStream("/br/cesul/youtube/images/channel_photo.png"));
        Image backgroundImage = new Image(getClass().getResourceAsStream("/br/cesul/youtube/images/channel_background.png"));
        channelImage.setImage(userImage);
        channelBackgroundImage.setImage(backgroundImage);
    }
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/cesul/youtube/ChannelListView.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
