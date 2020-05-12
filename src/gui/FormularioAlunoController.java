package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entities.Aluno;
import model.entities.Turma;
import model.services.AlunoService;

public class FormularioAlunoController implements Initializable {
	
	private Aluno aluno;
	
	private AlunoService alunoService;
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private TextField txtTelefone;
	
	@FXML
	private ComboBox<Turma> comboBoxTurma;
	
	@FXML
	private Label labelErrorNome;
	
	@FXML
	private Label labelErrorEmail;
	
	@FXML
	private Label labelErrorTelefone;
	
	@FXML
	private Label labelErrorTurma;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancelar;
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public void setAlunoService(AlunoService alunoService) {
		this.alunoService = alunoService;
	}
	
	private Aluno getFormData() {
		Aluno objAluno = new Aluno();
		objAluno.setId((Long) Utils.parseToLong(txtId.getText()));
		objAluno.setNome(txtNome.getText());
		objAluno.setEmail(txtEmail.getText());
		objAluno.setTelefone(txtTelefone.getText());		
		
		return objAluno;
	}
	
	@FXML
	public void onBtSalvarAction(ActionEvent event) {
		if(aluno == null) {
			throw new IllegalStateException("Aluno está vazio.");
		}
		if(alunoService == null) {
			throw new IllegalStateException("Serviço está vazio.");
		}
		try {
			aluno = getFormData();
			alunoService.saveOrUpdate(aluno);
			Utils.currentStage(event).close();			
		}
		catch (Exception e) {
			Alerts.showAlert("Erro ao salvar o objeto", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	public void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtNome, 70);
		Constraints.setTextFieldMaxLength(txtEmail, 70);
		Constraints.setTextFieldMaxLength(txtTelefone, 13);
		Constraints.setTextFieldInteger(txtTelefone);
	}
	
	public void updateData() {
		if(aluno == null) {
			throw new IllegalStateException("Aluno está vazio.");
		}
		txtId.setText(String.valueOf(aluno.getId()));
		txtNome.setText(aluno.getNome());
		txtEmail.setText(aluno.getEmail());
		txtTelefone.setText(aluno.getTelefone());
	}
}
