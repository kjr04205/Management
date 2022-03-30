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
	
	public List<Employee> selectPage(Map map) throws Exception{
		return session.selectList(namespace+"selectEmployeePage", map);
	}
	
	public int count() throws Exception {
        return session.selectOne(namespace+"count");
    } 
	
	public int insertEmployee(Employee employee) throws Exception{
		return session.insert(namespace + "insertEmployee", employee);
	}
	
	public int removeEmployee(Integer EmployeeEno) throws Exception{
		return session.insert(namespace + "removeEmployee", EmployeeEno);
	}
	
	public List<Team> selectTeamAll() throws Exception{
		return session.selectList(namespace + "selectTeamAll");
	}
	
	public List<Position> selectPositionAll() throws Exception{
		return session.selectList(namespace + "selectPositionAll");
	}
	
	public int teamCount() throws Exception{
		return session.selectOne(namespace + "teamCount");
	}
	
	public int teamInsert(Team team) throws Exception{
		return session.insert(namespace + "teamInsert", team);
	}
	
	public List<Team> selectTeam(Map map) throws Exception{
		return session.selectList(namespace + "selectTeam", map);
	}
	
	public int selectTeamMemberCnt(SearchCondition sc) throws Exception{
		return session.selectOne(namespace + "selectTeamMemberCnt", sc);
	}
	
	public List<Employee> selectTeamMember(SearchCondition sc) throws Exception{
		return session.selectList(namespace + "selectTeamMember", sc);
	}
}
