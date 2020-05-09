package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Aluno;
import model.services.AlunoService;

public class ListagemAlunoController implements Initializable {

	private AlunoService alunoService;
	
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
	
	private ObservableList<Aluno> obsAlunoList;
	
	public void setAlunoService(AlunoService alunoService) {
		this.alunoService = alunoService;
	}
	
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
	
	public void updateTableView() {
		if(alunoService == null) {
			throw new IllegalStateException("O servi�o n�o foi iniciado");
		}
		
		List<Aluno> listAluno = alunoService.findAll();
		obsAlunoList = FXCollections.observableArrayList(listAluno);
		tableViewAluno.setItems(obsAlunoList);
	}

}
