package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import model.services.AlunoService;

public class MainViewController implements Initializable{
	
	@FXML
	private MenuItem menuItemAlunoNovo; 
	
	@FXML
	private MenuItem menuItemAlunoListagem;
	
	@FXML
	private MenuItem menuItemTurmaNova;
	
	@FXML
	private MenuItem menuItemTurmaListagem;
	
	@FXML
	private MenuItem menuItemConteudoNovo;
	
	@FXML
	private MenuItem menuItemConteudoListagem;
	
	@FXML
	private MenuItem menuItemPresenca;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void menuItemAlunoNovoAction() {
		loadView("/gui/FormularioAlunoView.fxml", x -> {});
	}
	
	@FXML
	public void menuItemAlunoListagemAction() {
		loadView("/gui/ListagemAlunoView.fxml", (ListagemAlunoController listagemAlunoController) -> {
			listagemAlunoController.setAlunoService(new AlunoService());
			listagemAlunoController.updateTableView();
		});
	}
	
	@FXML
	public void menuItemTurmaNovaAction() {
		System.out.println("teste3");
	}
	
	@FXML
	public void menuItemTurmaListagemAction() {
		System.out.println("teste4");
	}
	
	@FXML
	public void menuItemConteudoNovoAction() {
		System.out.println("teste5");
	}
	
	@FXML
	public void menuItemConteudoListagemAction() {
		System.out.println("teste6");
	}
	
	@FXML
	public void menuItemPresencaAction() {
		System.out.println("teste7");
	}
	
	@FXML
	public void menuItemAboutAction() {
		System.out.println("teste8");
	}
	
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) mainScene.getRoot();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao abrir a tela", e.getMessage(), AlertType.ERROR);
		}
		
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}
