package model.services;

import java.util.List;

import model.dao.AlunoDao;
import model.dao.DaoFactory;
import model.entities.Aluno;

public class AlunoService {
	
	private AlunoDao dao = DaoFactory.createAlunoDao();

	public List<Aluno> findAll(){
		
		List<Aluno> listAluno = dao.findAll();
		
		return listAluno;
	}
	
	public void saveOrUpdate(Aluno aluno) {
		if(aluno.getId() == null) {
			dao.insert(aluno);			
		}
		else {
			dao.update(aluno);
		}
	}
}
