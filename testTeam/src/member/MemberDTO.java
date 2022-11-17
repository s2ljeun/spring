package member;

import team.TeamDTO;

public class MemberDTO {
	private Integer id;
	private String name;
	private TeamDTO team; //foreign키 -> team id로 TeamDTO객체가 만들어지면 id가 존재한다는 뜻이므로(무결성 확보) 여기서 TeamDTO타입으로 받는다.
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TeamDTO getTeam() {
		return team;
	}
	public void setTeam(TeamDTO team) {
		this.team = team;
	}

	
}
