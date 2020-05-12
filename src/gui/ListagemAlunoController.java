package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
	
	public void onBtNovoAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Aluno aluno = new Aluno();
		createDialogForm(aluno,"/gui/FormularioAlunoView.fxml", parentStage);
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
			throw new IllegalStateException("O serviço não foi iniciado");
		}
		
		List<Aluno> listAluno = alunoService.findAll();
		obsAlunoList = FXCollections.observableArrayList(listAluno);
		tableViewAluno.setItems(obsAlunoList);
	}
	
	public void createDialogForm(Aluno aluno, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			FormularioAlunoController formularioAlunoController = loader.getController();
			formularioAlunoController.setAluno(aluno);
			formularioAlunoController.updateData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Controle de Alunos");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro para abrir janela", e.getMessage(), AlertType.ERROR);
		}
	}

}
