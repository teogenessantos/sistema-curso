package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Aluno;

public class ListagemAlunoController implements Initializable {

	@FXML
	private TableView<Aluno> tableViewAluno;
	
	@FXML
	private TableColumn<Aluno, Long> tableColumnId;
	
	@FXML
	private TableColumn<Aluno, String> tableColumnNome;
	
	@FXML
	private TableColumn<Aluno, String> tableColumnEmail;
	
	@FXML
	private TableColumn<Aluno, String> tableColumnTelefone;
	
	@FXML
	private Button btNovo;
	
	public void onBtNovoAction() {
		System.out.println("onBtNovoAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {	
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewAluno.prefHeightProperty().bind(stage.heightProperty());
		
	}

}
