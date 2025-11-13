package br.cesul.youtube.view;

import br.cesul.youtube.model.Channel;
import br.cesul.youtube.viewmodel.ChannelListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class ChannelListView {

    @FXML private ListView<Channel> channelList;
    @FXML private Button createChannelButton;

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
            ((ChannelView) loader.getController()).loadChannel(channel.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openCreateChannel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/cesul/youtube/CreateChannelView.fxml"));
            Stage stage = (Stage) channelList.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
