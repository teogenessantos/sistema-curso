package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Aluno;

public class AlunoService {

	public List<Aluno> findAll(){
		List<Aluno> listAluno = new ArrayList<>();
		listAluno.add(new Aluno(1L, "teogenes", "teogenes@gmail.com", "4189226578"));
		listAluno.add(new Aluno(2L, "tiago", "tiago@gmail.com", "85995151724"));
		listAluno.add(new Aluno(3L, "tobias", "tobias@gmail.com", "85993364521"));
		
		return listAluno;
	}
	
	public void saveOrUpdate(Aluno aluno) {
		if(aluno.getId() == null) {
			System.out.println("Interir objeto na base");
			System.out.println(aluno);
		}
		else {
			System.out.println("Atualizar objeto na base");
			System.out.println(aluno);
		}
	}
}
