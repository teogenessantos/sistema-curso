package model.dao;

import java.util.List;

import model.entities.Aluno;
import model.entities.Turma;

public interface AlunoDao {
	void insert(Aluno obj);
	void update(Aluno obj);
	void deleteById(Integer id);
	Aluno findById(Integer id);
	List<Aluno> findAll();
	List<Aluno> findByTurma(Turma turma);
}
