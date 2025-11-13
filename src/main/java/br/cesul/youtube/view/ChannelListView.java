package br.cesul.youtube.view;

import br.cesul.youtube.model.Channel;
import br.cesul.youtube.viewmodel.ChannelListViewModel;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.util.Duration;

public class ChannelListView {

    @FXML private ListView<Channel> channelList;
    @FXML private Button createChannelButton;
    @FXML
    private StackPane rootPane;

    private final ChannelListViewModel viewModel = new ChannelListViewModel();

    @FXML
    public void initialize() {
        channelList.setItems(viewModel.getChannels());
        channelList.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Channel channel, boolean empty) {
                super.updateItem(channel, empty);
                setText(empty || channel == null ? null : channel.getName());
            }
        });

        channelList.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && channelList.getSelectionModel().getSelectedItem() != null) {
                openChannel(channelList.getSelectionModel().getSelectedItem());
            }
        });

        createChannelButton.setOnAction(e -> openCreateChannel());
    }

    private void openChannel(Channel channel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/cesul/youtube/YoutubeView.fxml"));
            Stage stage = (Stage) channelList.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.sizeToScene();
            stage.centerOnScreen();
            ((ChannelView) loader.getController()).loadChannel(channel.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openCreateChannel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/cesul/youtube/CreateChannelView.fxml"));
            Parent popupContent = loader.load();

            StackPane overlay = new StackPane();
            overlay.setStyle("-fx-background-color: rgba(0,0,0,0.5);");

            overlay.getChildren().add(popupContent);
            StackPane.setAlignment(popupContent, javafx.geometry.Pos.CENTER);

            overlay.setOpacity(0);

            rootPane.getChildren().add(overlay);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(200), overlay);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
