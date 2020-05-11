package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Turma;

public class FormularioAlunoController implements Initializable {
	
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
	
	@FXML
	public void onBtSalvarAction() {
		System.out.println("onBtSalvarAction");
	}
	
	@FXML
	public void onBtCancelarAction() {
		System.out.println("onBtCancelarAction");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	public void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtNome, 70);
		Constraints.setTextFieldMaxLength(txtEmail, 70);
		Constraints.setTextFieldMaxLength(txtTelefone, 13);
		Constraints.setTextFieldInteger(txtTelefone);
	}

}
