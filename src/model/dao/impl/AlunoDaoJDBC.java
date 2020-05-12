package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.AlunoDao;
import model.entities.Aluno;
import model.entities.Turma;

public class AlunoDaoJDBC implements AlunoDao{
	
	private Connection conn;
	
	public AlunoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Aluno obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO alunos "+
					"(nome, email, telefone, id_turma) "+
					"VALUES "+
					"(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS
					);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getTelefone());
			st.setInt(4, 1);
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				else {
					throw new DbException("Unexpected error! No rows affected");
				}
				DB.closeResultSet(rs);
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Aluno obj) {
		PreparedStatement st = null;
	
		try {
			st = conn.prepareStatement(
					"UPDATE aluno " + 
					"SET nome = ?, email = ?, telefone = ?, id_turma = ? " + 
					"WHERE Id = ?"
					);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getTelefone());
			st.setInt(4, obj.getTurma().getId());
			
			st.executeUpdate();
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}	
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM aluno WHERE Id = ?");
			st.setInt(1, id);
			
			st.executeUpdate();
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Aluno findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT aluno.*, turmas.nome as turma_nome "
					+"FROM alunos INNER JOIN turmas "
					+"ON alunos.id_turma = turmas.id "
					+"WHERE alunos.id = ?"
				 );
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				 Turma turma = instantiateTurma(rs); 
				 Aluno aluno = instantiateAluno(rs,turma); 
				 
				 return aluno;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Aluno instantiateAluno(ResultSet rs, Turma turma) throws SQLException {
		 Aluno aluno = new Aluno();
		 aluno.setId(rs.getInt("id"));
		 aluno.setNome(rs.getString("nome"));
		 aluno.setEmail(rs.getString("email"));
		 aluno.setTelefone(rs.getString("telefone"));
		 aluno.setTurma(turma);
		 return aluno;
	}

	private Turma instantiateTurma(ResultSet rs) throws SQLException {
		Turma turma = new Turma();
		turma.setId(rs.getInt("id_turma"));
		turma.setNome(rs.getString("turma_nome"));
		return turma;
	}

	@Override
	public List<Aluno> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT alunos.*,turmas.nome as turma_nome " + 
					"FROM alunos INNER JOIN turmas " + 
					"ON alunos.id_turma = turmas.id " + 
					"ORDER BY nome"
				 );
			rs = st.executeQuery();
			
			List<Aluno> alunos = new ArrayList<>();
			Map<Integer, Turma> map = new HashMap<>();
			
			while(rs.next()) {
				Turma tur = map.get(rs.getInt("id_turma"));
				if(tur == null) {
					tur = instantiateTurma(rs);
					map.put(rs.getInt("id_turma"),tur);
				}
				
				Aluno aluno = instantiateAluno(rs,tur); 
				alunos.add(aluno);
			}
			return alunos;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Aluno> findByTurma(Turma turma) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT alunos.*,turmas.nome as turma_nome " + 
					"FROM alunos INNER JOIN turmas " + 
					"ON alunos.id_turma = turma.id " + 
					"WHERE turma.id = ? " + 
					"ORDER BY nome"
				 );
			st.setInt(1, turma.getId());
			rs = st.executeQuery();
			List<Aluno> alunos = new ArrayList<>();
			while(rs.next()) {
				 Aluno aluno = instantiateAluno(rs,turma); 
				 alunos.add(aluno);
			}
			return alunos;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
