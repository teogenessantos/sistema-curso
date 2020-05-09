package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
		System.out.println("teste1");
	}
	
	@FXML
	public void menuItemAlunoListagemAction() {
		System.out.println("teste2");
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

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}
