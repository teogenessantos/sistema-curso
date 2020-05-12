package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Aluno;
import model.entities.Turma;
import model.exceptions.ValidationException;
import model.services.AlunoService;

public class FormularioAlunoController implements Initializable {
	
	private Aluno aluno;
	
	private AlunoService alunoService;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
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
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	private Aluno getFormData() {
		Aluno objAluno = new Aluno();
		
		ValidationException exception = new ValidationException("Validação de erros");
		
		if(txtNome.getText() == null || txtNome.getText().trim().equals("")) {
			exception.addError("nome", "Campo não pode ser vazio");
		}
		if(txtEmail.getText() == null || txtEmail.getText().trim().equals("")) {
			exception.addError("email", "Campo não pode ser vazio");
		}
		if(txtTelefone.getText() == null || txtTelefone.getText().trim().equals("")) {
			exception.addError("telefone", "Campo não pode ser vazio");
		}
		
		objAluno.setId(Utils.tryParseToInt(txtId.getText()));
		objAluno.setNome(txtNome.getText());
		objAluno.setEmail(txtEmail.getText());
		objAluno.setTelefone(txtTelefone.getText());	
		
		if(exception.getErros().size()>0) {
			throw exception;
		}
		
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
			notifyDataChangeListeners(); 
			Utils.currentStage(event).close();			
		}
		catch (DbException e) {
			Alerts.showAlert("Erro ao salvar o objeto", null, e.getMessage(), AlertType.ERROR);
		}
		catch (ValidationException e) {
			setErrorMessages(e.getErros());
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
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
	
	public void setErrorMessages(Map<String , String> errors) {
		Set<String> fields = errors.keySet();
		
		if(fields.contains("nome")) {
			labelErrorNome.setText(errors.get("nome"));
		}
		if(fields.contains("email")) {
			labelErrorEmail.setText(errors.get("email"));
		}
		if(fields.contains("telefone")) {
			labelErrorTelefone.setText(errors.get("telefone"));
		}
	}
}
