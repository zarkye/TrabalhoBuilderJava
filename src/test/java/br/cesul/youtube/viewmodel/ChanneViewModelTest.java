package br.cesul.youtube.viewmodel;

import br.cesul.youtube.model.Channel;
import br.cesul.youtube.repository.ChannelRepositoryStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChanneViewModelTest {
    @Test
    @DisplayName("Construtor para teste, cria um canal com valores predefinidos, usa um repositorio stub para verificações")
    public void testConstructorLoadsCorrectChannel() {
        // Arrange
        ChannelRepositoryStub repo = new ChannelRepositoryStub();
        Channel c = new Channel("Fagas", 100);
        repo.insert(c);
        //--------

        ChannelViewModel vm = new ChannelViewModel("Fagas", repo);

        assertEquals("Fagas", vm.nameProperty().get(), "Nome deve ser igual ao predefinido");
        assertEquals(100, vm.subscribersProperty().get(), "Valor de inscritos deve ser igual ao predefinido");
    }

    @Test
    @DisplayName("Ao se inscrever em um canal, deve se aumentar o numero de inscritos em 1 e no repositorio também")
    public void testSubscribeIncrementsAndUpdatesRepository() {
        // Arrange
        ChannelRepositoryStub repo = new ChannelRepositoryStub();
        Channel c = new Channel("Fagas", 100);
        repo.insert(c);
        ChannelViewModel vm = new ChannelViewModel("Fagas", repo);
        //--------
        vm.subscribe();

        assertEquals(101, vm.subscribersProperty().get(), "Deve aumentar em +1 no ViewModel");
        assertEquals(101, repo.findByName("Fagas").getSubscribers(), "Deve atualizar o repositório também");
    }
}
