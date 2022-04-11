package com.project.DAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.DTO.Employee;
import com.project.DTO.Position;
import com.project.DTO.SearchCondition;
import com.project.DTO.Team;
import com.project.DTO.User;

@Repository
public class EmployeeDAO {
	
	@Autowired
	SqlSession session;
	String namespace = "com.project.DAO.EmployeeMapper.";
	
	public List<Employee> selectEmployee() throws Exception{
		return session.selectList(namespace+"selectEmployee");
	}
	
	public List<Employee> selectPage(SearchCondition sc) throws Exception{
		return session.selectList(namespace+"selectEmployeePage", sc);
	}
	
	public int count(SearchCondition sc) throws Exception {
        return session.selectOne(namespace+"count", sc);
    } 

	public Employee selectEmployee(int eno) throws Exception{
		return session.selectOne(namespace + "selectEmployeeOne", eno);
	}
	
	public int insertEmployee(Employee employee) throws Exception{
		return session.insert(namespace + "insertEmployee", employee);
	}
	
	public int removeEmployee(Integer EmployeeEno) throws Exception{
		return session.insert(namespace + "removeEmployee", EmployeeEno);
	}
	
	public int updateEmployee(Employee employee) throws Exception{
		return session.update(namespace + "updateEmployee", employee);
	}
	
	public List<Team> selectTeamAll() throws Exception{
		return session.selectList(namespace + "selectTeamAll");
	}
	
	public List<Position> selectPositionAll() throws Exception{
		return session.selectList(namespace + "selectPositionAll");
	}
	
	public int teamCount(SearchCondition sc) throws Exception{
		return session.selectOne(namespace + "teamCount", sc);
	}
	
	public int teamInsert(Team team) throws Exception{
		return session.insert(namespace + "teamInsert", team);
	}
	
	public List<Team> selectTeam(SearchCondition sc) throws Exception{
		return session.selectList(namespace + "selectTeam", sc);
	}
	
	public int selectTeamMemberCnt(SearchCondition sc) throws Exception{
		return session.selectOne(namespace + "selectTeamMemberCnt", sc);
	}
	
	public List<Employee> selectTeamMember(SearchCondition sc) throws Exception{
		return session.selectList(namespace + "selectTeamMember", sc);
	}
	
	public int positionCount(SearchCondition sc) throws Exception{
		return session.selectOne(namespace + "positionCount", sc);
	}
	
	public List<Position> selectPosition(SearchCondition sc) throws Exception{
		return session.selectList(namespace + "selectPosition", sc);
	}
	
	public int insertPosition(Position position) throws Exception{
		return session.insert(namespace + "insertPosition", position);
	}
	
	public int selectPositionMemberCnt(SearchCondition sc) throws Exception{
		return session.selectOne(namespace + "selectPositionMemberCnt", sc);
	}
	
	public List<Employee> selectPositionMember(SearchCondition sc) throws Exception{
		return session.selectList(namespace + "selectPositionMember", sc);
	}
}
